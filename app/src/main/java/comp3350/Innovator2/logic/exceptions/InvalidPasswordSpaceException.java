package comp3350.Innovator2.logic.exceptions;

public class InvalidPasswordSpaceException extends UIException {
    public InvalidPasswordSpaceException(String errorMessage) {
        // error thrown if the new password is not long enough
        super(errorMessage);
    }
}
