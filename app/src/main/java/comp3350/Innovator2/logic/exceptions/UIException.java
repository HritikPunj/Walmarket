package comp3350.Innovator2.logic.exceptions;

/**
 * Base class for all exceptions thrown to the UI for Users to see.
 * All extensions of this class should provide readable messages in the message() field.
 */
public abstract class UIException extends Exception
{
    public UIException(String errorMessage) {
        super(errorMessage);
    }
}
