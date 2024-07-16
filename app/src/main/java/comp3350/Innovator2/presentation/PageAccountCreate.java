package comp3350.Innovator2.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import comp3350.Innovator2.R;
import comp3350.Innovator2.logic.IUserManager;
import comp3350.Innovator2.logic.StoreHelper;

/**
 * #### PageAccountCreate
 * Interface to create an account.
 */
public class PageAccountCreate extends AppCompatActivity {

    //================================================== Variables

    //UI Elements
    private TextView firstName;
    private TextView lastName;
    private TextView email;
    private TextView username;
    private TextView password;
    private TextView confirmPassword;

    //Class Links
    private IUserManager userManager;

    //User Messages
    private final String onSuccess = "Your account has been successfully created!";

    //================================================== Creation

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Display Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount_view);

        //UI Elements
        firstName =         findViewById(R.id.firstName);
        lastName =          findViewById(R.id.lastName);
        email =             findViewById(R.id.email);
        username =          findViewById(R.id.username);
        password =          findViewById(R.id.password);
        confirmPassword =   findViewById(R.id.confirmPassword);

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
     * #### public void onCreateAccount(View)
     * Create a new account using given input.
     */
    public void onCreateAccount(View view)
    {
        requestCreateAccount();
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
        !firstName.getText().toString().isEmpty() &&
        !lastName.getText().toString().isEmpty() &&
        !email.getText().toString().isEmpty() &&
        !username.getText().toString().isEmpty() &&
        !password.getText().toString().isEmpty() &&
        !confirmPassword.getText().toString().isEmpty();
    }

    //Request query to create account.
    private void requestCreateAccount()
    {
        try //Attempt to create new account.
        {
            //Make creation request.
            userManager.create
                    (
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            username.getText().toString(),
                            password.getText().toString(),
                            confirmPassword.getText().toString(),
                            email.getText().toString()
                    );

            //Automatically sign in.
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
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
