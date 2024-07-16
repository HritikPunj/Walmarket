package comp3350.Innovator2.logic.exceptions;

public class InvalidExpiryDateException extends CreditCardException {
    public InvalidExpiryDateException(String errorMessage) {
        // error thrown if the expiry date is invalid
        super(errorMessage);
    }
}
