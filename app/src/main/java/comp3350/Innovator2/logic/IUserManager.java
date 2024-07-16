package comp3350.Innovator2.logic;

import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.CreditCard;
import comp3350.Innovator2.objects.User;

public interface IUserManager {

    //================================================== Interface

    //Sign in user with given name.
    public void signIn(String username, String password) throws UIException;

    //Create an account with given info.
    public void create(String firstname, String lastname, String username, String password, String confirmPassword, String email) throws UIException;

    //Sign out the current user.
    public void signOut();

    //Check if a user is signed in.
    public boolean isSignedIn();

    //Get username of current user.
    public String getUsername();

    //Get saved form of payment.
    public CreditCard getCard(int cardChoice);

    //Save a form of payment.
    public void saveCard(String name, String number, String expiry, String cvv, boolean checked);
}
