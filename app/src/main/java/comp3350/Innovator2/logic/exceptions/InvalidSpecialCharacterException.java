package comp3350.Innovator2.logic.exceptions;

public class InvalidSpecialCharacterException extends UIException {
    public InvalidSpecialCharacterException(String errorMessage) {
        // error thrown if the new password does not have at least 1 special character
        super(errorMessage);
    }
}
