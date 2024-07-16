package comp3350.Innovator2.presentation;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import comp3350.Innovator2.R;
import comp3350.Innovator2.logic.IUserManager;
import comp3350.Innovator2.logic.StoreHelper;

/**
 * #### HelperUser
 * Service class for the Browser Page.
 * Manage the sign in and out buttons.
 */
public class HelperUser {

    //================================================== Variables

    //UI Elements
    private final Button signInButton;
    private final Button signOutButton;

    //Class Links
    private final IUserManager userManager;
    private final PageBrowser pageBrowser;

    //UI Text
    private final String loginText = "Sign In / Create Account";
    private final String failLogout = "You must log in before you may log out";

    //================================================== Creation

    public HelperUser(PageBrowser browser)
    {
        //UI Elements
        signInButton = browser.findViewById(R.id.signInOrCreateAccount);
        signOutButton = browser.findViewById(R.id.signOutButton);

        //Class Links
        userManager = StoreHelper.getUserManager();
        pageBrowser = browser;

        //Create Listeners
        createListeners();
    }

    //================================================== UI Interaction

    //Open listeners for button clicks.
    private void createListeners()
    {
        //Listener for sign in button.
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to sign in page, if not signed in.
                if (!userManager.isSignedIn()) toSignIn();
            }
        });

        //Listener for sign out button.
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userManager.isSignedIn())
                {
                    //Sign out the current user.
                    userManager.signOut();
                    updateButtons();
                }
                else //If no user is signed in.
                {
                    //Error message if already signed out.
                    Toast.makeText(pageBrowser, failLogout, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //================================================== Interface

    /**
     * #### public void updateButtons()
     * Enable/disable buttons for current user.
     */
    public void updateButtons()
    {
        if (userManager.isSignedIn())
        {
            signInButton.setEnabled(false);
            signOutButton.setEnabled(true);

            signInButton.setText(userManager.getUsername());
        }
        else //Not signed in.
        {
            signInButton.setEnabled(true);

            signInButton.setText(loginText);
        }
    }

    //================================================== Internals

    //Move to sign in page.
    private void toSignIn()
    {
        Intent intent = new Intent(pageBrowser, PageAccountSignIn.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        pageBrowser.startActivity(intent);
    }

}
