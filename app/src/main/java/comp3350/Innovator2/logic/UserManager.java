package comp3350.Innovator2.logic;

import android.widget.Toast;

import comp3350.Innovator2.application.Services;
import comp3350.Innovator2.data.IUserPersistence;
import comp3350.Innovator2.data.stub.UserPersistenceStub;
import comp3350.Innovator2.logic.exceptions.InvalidSignInException;
import comp3350.Innovator2.logic.exceptions.InvalidUsernameException;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.CreditCard;
import comp3350.Innovator2.objects.MasterCard;
import comp3350.Innovator2.objects.User;
import comp3350.Innovator2.objects.VisaCard;

public class UserManager implements IUserManager{

    //================================================== Variables

    //Data
    private User currentUser;

    //Class Links
    private final IUserPersistence userDB;

    //Exceptions
    private final String alreadySignedIn = "Error: A user is already signed in";
    private final String userDoesntExist = "Error: No user with that username exists";
    private final String incorrectInput = "Error: Username or password is incorrect";
    private final String userAlreadyExists = "Error: This username is already in use";
    private final String emptyFields = "Cannot create an account, please fill in all fields";
    private final String nonMatch = "Both of your passwords must match";

    //================================================== Creation

    public UserManager()
    {
        //Data
        currentUser = null;

        //Class Links
        userDB = Services.getUserPersistence();
    }

    //================================================== Interface

    //Sign in user with given name.
    public void signIn(String username, String password) throws UIException
    {
        if (currentUser != null) //Already signed in.
            throw new InvalidSignInException(alreadySignedIn);

        if (!userDB.getUser(username)) //No user with name.
            throw new InvalidSignInException(userDoesntExist);

        User newUser = null;
        //Attempt to authenticate user.
        if (StoreHelper.getSignInValidator().validateSignIn(username, password)) {
            newUser = userDB.authenticateUser(username, password);
        }

        //Login the authenticated user.
        if (newUser != null) currentUser = newUser;
    }

    //Create a new user.
    public void create(String firstname, String lastname, String username, String password, String confirmPassword, String email) throws UIException
    {
        //Ensure all fields are filled.
        if (firstname.isEmpty() || lastname.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty())
            throw new InvalidSignInException(emptyFields);

        //Ensure that username is free.
        if (userDB.getUser(username))
            throw new InvalidUsernameException(userAlreadyExists);

        //Ensure that password is valid.
        try {StoreHelper.getPasswordValidator().validateNewPassword(password);}
        catch (UIException e) { throw e; }

        //Ensure that both passwords match.
        if (!password.equals(confirmPassword))
            throw new InvalidSignInException(nonMatch);

        //Log out any current user.
        currentUser = null;

        //Create the account.
        userDB.addUser(firstname, lastname, username, password, email);
    }

    //Sign out the current user.
    public void signOut()
    {
        currentUser = null;
    }

    //Check if a user is signed in.
    public boolean isSignedIn()
    {
        return (currentUser != null);
    }

    //Get username of current user.
    public String getUsername()
    {
        if (isSignedIn()){
            return currentUser.getUsername();
        }
        else {
            return "Guest User";
        }
    }

    //Get saved form of payment.
    public CreditCard getCard(int cardChoice)
    {
        if (isSignedIn()) {
            return userDB.getPaymentDetails(getUsername()).get(cardChoice);
        }
        else {
            return null;
        }
    }

    //Save a form of payment.
    public void saveCard(String name, String number, String expiry, String cvv, boolean mastercard)
    {
        CreditCard newCard;

        if (mastercard) {
            newCard = new MasterCard
                    (
                            number,
                            name,
                            expiry,
                            cvv
                    );
        }
        else {
            newCard = new VisaCard
                    (
                            number,
                            name,
                            expiry,
                            cvv
                    );
        }

        userDB.addPaymentInfo(newCard, currentUser.getUsername());
    }
}
