package comp3350.Innovator2.logic.exceptions;

public class InvalidUsernameException extends UIException {
    public InvalidUsernameException(String errorMessage) {
        // error thrown if account created with existing username.
        super(errorMessage);
    }
}
