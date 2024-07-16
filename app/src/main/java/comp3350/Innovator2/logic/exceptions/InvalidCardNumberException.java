package comp3350.Innovator2.logic.exceptions;

public class InvalidCardNumberException extends CreditCardException {
    public InvalidCardNumberException(String errorMessage) {
        // error thrown if the card number is invalid
        super(errorMessage);
    }
}
