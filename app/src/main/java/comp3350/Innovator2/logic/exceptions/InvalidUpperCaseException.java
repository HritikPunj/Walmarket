package comp3350.Innovator2.logic.exceptions;

public class InvalidUpperCaseException extends UIException {
    public InvalidUpperCaseException(String errorMessage) {
        // error thrown if the new password does not have at least 1 upper case character
        super(errorMessage);
    }
}
