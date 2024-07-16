package comp3350.Innovator2.logic.exceptions;

public class InvalidLowerCaseException extends UIException {
    public InvalidLowerCaseException(String errorMessage) {
        // error thrown if the new password does not have at least 1 lower case character
        super(errorMessage);
    }
}
