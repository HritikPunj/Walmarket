package comp3350.Innovator2.logic.exceptions;

public class InvalidCvvException extends CreditCardException {
    public InvalidCvvException(String errorMessage) {
        // error thrown if the CVV is invalid
        super(errorMessage);
    }
}
