package comp3350.Innovator2.logic;

import comp3350.Innovator2.logic.exceptions.InvalidSignInException;

public interface ISignInValidator
{
    // returns true if a username and password exist together, if not, throw an exception for the UI
    boolean validateSignIn(String username, String password) throws InvalidSignInException;
}
