package comp3350.Innovator2.logic.exceptions;

public abstract class NewPasswordException extends UIException {
    public NewPasswordException(String errorMessage) {
        super(errorMessage); // encapsulate all new password exceptions under one super exception
    }
}
