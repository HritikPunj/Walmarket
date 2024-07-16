package comp3350.Innovator2.logic.exceptions;

public class InvalidSignInException extends NewPasswordException {
    public InvalidSignInException(String errorMessage) {
        // error thrown if the inputted username or password is incorrect
        super(errorMessage);
    }
}
