package comp3350.Innovator2.logic;

import comp3350.Innovator2.logic.exceptions.InvalidLowerCaseException;
import comp3350.Innovator2.logic.exceptions.InvalidPasswordLengthException;
import comp3350.Innovator2.logic.exceptions.InvalidPasswordSpaceException;
import comp3350.Innovator2.logic.exceptions.InvalidSpecialCharacterException;
import comp3350.Innovator2.logic.exceptions.InvalidUpperCaseException;

public interface INewPasswordValidator {
    // throws errors based on validity, returns true if it was valid
    boolean validateNewPassword(String password)
            throws InvalidPasswordSpaceException,
            InvalidPasswordLengthException,
            InvalidUpperCaseException,
            InvalidLowerCaseException,
            InvalidSpecialCharacterException;
}