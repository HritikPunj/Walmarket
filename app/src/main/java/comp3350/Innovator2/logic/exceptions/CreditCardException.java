package comp3350.Innovator2.logic.exceptions;

public abstract class CreditCardException extends UIException
{
    public CreditCardException(String errorMessage) {
        super(errorMessage);
    }
}
