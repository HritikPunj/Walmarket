package comp3350.Innovator2.logic;

import comp3350.Innovator2.data.IUserPersistence;
import comp3350.Innovator2.logic.exceptions.InvalidSignInException;

public class SignInValidator implements ISignInValidator {
    IUserPersistence userPersistence;
    private static final String INVALID_SIGN_IN_MSG = "The username or password you entered in invalid.";

    public SignInValidator(IUserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    /**
     * #### boolean validateSignIn(String username, String password) throws InvalidSignInException
     * Validates if a username and password exist together
     * Uses the IUserPersistence methods
     * returns true if the user is exists, otherwise throw an invalid sign-in exception
     */
    @Override
    public boolean validateSignIn(String username, String password) throws InvalidSignInException {
        // authenticate returns the user if the username and password match
        if (userPersistence.authenticateUser(username, password) != null) {
            return true;
        }
        // otherwise null, therefore invalid details entered
        else {
            throw new InvalidSignInException(INVALID_SIGN_IN_MSG);
        }
    }
}