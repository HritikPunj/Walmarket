package comp3350.Innovator2.logic;

import comp3350.Innovator2.logic.exceptions.InvalidLowerCaseException;
import comp3350.Innovator2.logic.exceptions.InvalidPasswordLengthException;
import comp3350.Innovator2.logic.exceptions.InvalidPasswordSpaceException;
import comp3350.Innovator2.logic.exceptions.InvalidSpecialCharacterException;
import comp3350.Innovator2.logic.exceptions.InvalidUpperCaseException;

public class NewPasswordValidator implements INewPasswordValidator {
    private static final int VALID_PASSWORD_LENGTH = 8; // set to at least 8 characters long

    // invalid error messages set as constants
    private static final String INVALID_PASSWORD_SPACE_MSG = "The password cannot contain any spaces.";
    private static final String INVALID_PASSWORD_LENGTH_MSG = "The password must be at least 8 characters long.";
    private static final String INVALID_UPPER_CASE_MSG = "The password must be at least 1 upper case character.";
    private static final String INVALID_LOWER_CASE_MSG = "The password must be at least 1 lower case character.";
    private static final String INVALID_SPECIAL_CHAR_MSG = "The password must be at least 1 special character.";

    /**
     * #### void validateNewPassword(String password)
     *          throws InvalidPasswordSpaceException, InvalidPasswordLengthException,
     *              InvalidUpperCaseException, InvalidLowerCaseException, InvalidSpecialCharacterException
     * Validate the new password to make sure it has no spaces, is long enough and has at least
     * one lower case, upper case and special character
     * Throw appropriate exceptions
     * Return true is the new password is valid
     */
    @Override
    public boolean validateNewPassword(String password)
     throws InvalidPasswordSpaceException, InvalidPasswordLengthException,
             InvalidUpperCaseException, InvalidLowerCaseException, InvalidSpecialCharacterException {

         if (!hasNoSpaces(password)) {
             throw new InvalidPasswordSpaceException(INVALID_PASSWORD_SPACE_MSG);
         }
         if (!isLengthValid(password)) {
            throw new InvalidPasswordLengthException(INVALID_PASSWORD_LENGTH_MSG);
         }
         if (!hasUpperCase(password)) {
            throw new InvalidUpperCaseException(INVALID_UPPER_CASE_MSG);
         }
         if (!hasLowerCase(password)) {
            throw new InvalidLowerCaseException(INVALID_LOWER_CASE_MSG);
         }
         if (!hasSpecialCharacter(password)) {
            throw new InvalidSpecialCharacterException(INVALID_SPECIAL_CHAR_MSG);
         }

         return true;
     }

     private boolean hasNoSpaces(String password) {
        // return if the password has no spaces
         return !password.matches(".*\\s+.*"); // checks for any white spaces
     }

     private boolean isLengthValid(String password) {
         // return if the password is long enough
         return password.length() >= VALID_PASSWORD_LENGTH;
     }

     private boolean hasUpperCase(String password) {
        // return if the password has an upper case
         return !password.equals(password.toLowerCase());
     }

     private boolean hasLowerCase(String password) {
        // return if the password has a lower case
         return !password.equals(password.toUpperCase());
     }

     private boolean hasSpecialCharacter(String password) {
        // return if the password has a special character
         return password.matches(".*[^a-zA-Z0-9].*"); // checks for any character that is not a-z, A-Z, 0-9
     }
}
