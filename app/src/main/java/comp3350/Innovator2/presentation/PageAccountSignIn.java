package comp3350.Innovator2.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.Innovator2.R;
import comp3350.Innovator2.logic.IUserManager;
import comp3350.Innovator2.logic.StoreHelper;

/**
 * #### PageAccountSignIn
 * Interface to sign in an account.
 */
public class PageAccountSignIn extends AppCompatActivity {

    //================================================== Variables

    //UI Elements
    private Button signInButton;
    private TextView username;
    private TextView password;

    //Class Links
    private IUserManager userManager;

    //User Messages
    private final String onSuccess = "You have successfully signed in!";
    private final String emptyFields = "Please fill all fields before signing in.";

    //Other Data
    static final int REQUEST_CODE = 1;

    //================================================== Creation

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Display Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_view);

        //UI Elements
        signInButton = findViewById(R.id.enterButton);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        //Class Links
        userManager = StoreHelper.getUserManager();
    }

    //================================================== UI Interaction

    /**
     * #### public void onGoBack(View)
     * Leaves the current activity.
     */
    public void onGoBack(View view)
    {
        leavePage(Activity.RESULT_CANCELED);
    }

    /**
     * #### public void toCreateAccount(View)
     * Go to account creation page.
     */
    public void toCreateAccount(View view)
    {
        Intent intent = new Intent(PageAccountSignIn.this, PageAccountCreate.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * #### public void onSignIn(View)
     * Attempt to sign into an account.
     */
    public void onSignIn(View view) {
        if (!nonEmptyFields())
            Toast.makeText(signInButton.getContext(), emptyFields, Toast.LENGTH_SHORT).show();
        else
            requestSignIn();
    }

    //================================================== Interface

    /**
     * #### protected void onActivityResult(int, int, Intent)
     * Respond to result of PageAccountCreate when opened in toCreateAccount().
     * Automatically leave this page if an account was successfully created.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            leavePage(Activity.RESULT_OK);
    }

    //================================================== Internals

    //Exit the current activity.
    private void leavePage(int resultCode)
    {
        Intent intent = new Intent();
        setResult(resultCode, intent);
        finish();
    }

    //Return true if all fields full.
    private boolean nonEmptyFields()
    {
        return
        !username.getText().toString().isEmpty() &&
        !password.getText().toString().isEmpty();
    }

    //Request sign in to account.
    private void requestSignIn() //!!!
    {
        try //Attempt to sign in to account.
        {
            //Make sign in request.
            userManager.signIn
                    (
                          username.getText().toString(),
                          password.getText().toString()
                    );

            //Leave activity on success.
            Toast.makeText(this, onSuccess, Toast.LENGTH_SHORT).show();
            leavePage(Activity.RESULT_OK);
        }
        catch (Exception e) //Display error message.
        {
            Toast.makeText(signInButton.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
