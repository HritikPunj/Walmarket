package comp3350.Innovator2.logic.exceptions;

public class InvalidPasswordLengthException extends UIException {
    public InvalidPasswordLengthException(String errorMessage) {
        // error thrown if the new password is not long enough
        super(errorMessage);
    }
}
