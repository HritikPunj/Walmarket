package comp3350.Innovator2.logic.exceptions;

public class DataUIException extends UIException{
    public DataUIException(String errorMessage) {
        super("Sorry, something went wrong in the back-end. Please try again or restart your application.");
    }
}
