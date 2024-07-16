package comp3350.Innovator2.logic;

import comp3350.Innovator2.logic.exceptions.DataException;
import comp3350.Innovator2.logic.exceptions.DataUIException;
import comp3350.Innovator2.logic.exceptions.InvalidCardNumberException;
import comp3350.Innovator2.logic.exceptions.InvalidCvvException;
import comp3350.Innovator2.logic.exceptions.InvalidExpiryDateException;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestExceptions {

    @Test
    public void testInvalidCardNumberException() {
        System.out.println("\nStarting testInvalidCardNumberException");
        boolean caught = false;

        try {
            invalidCardNumberException();
        } catch (InvalidCardNumberException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testInvalidCardNumberException");
    }

    private void invalidCardNumberException() throws InvalidCardNumberException {
        throw new InvalidCardNumberException("Invalid");
    }

    @Test
    public void testInvalidCvvException() {
        System.out.println("\nStarting testInvalidCvvException");
        boolean caught = false;

        try {
            invalidCvvException();
        } catch (InvalidCvvException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testInvalidCvvException");
    }

    private void invalidCvvException() throws InvalidCvvException {
        throw new InvalidCvvException("Invalid");
    }

    @Test
    public void testInvalidExpiryDateException() {
        System.out.println("\nStarting testInvalidExpiryDateException");
        boolean caught = false;

        try {
            invalidExpiryDateException();
        } catch (InvalidExpiryDateException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testInvalidExpiryDateException");
    }

    private void invalidExpiryDateException() throws InvalidExpiryDateException {
        throw new InvalidExpiryDateException("Invalid");
    }

    @Test
    public void testDataUIException() {
        System.out.println("\nStarting testDataUIException");
        boolean caught = false;

        try {
            dataUIException();
        } catch (DataUIException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testDataUIException");
    }

    private void dataUIException() throws DataUIException {
        throw new DataUIException("Invalid");
    }

    @Test
    public void testDataException() {
        System.out.println("\nStarting testDataException");
        boolean caught = false;

        try {
            dataException();
        } catch (DataException e){
            caught = true;
        }
        assertTrue(caught);
        System.out.println("Finished testDataException");
    }

    private void dataException() {
        throw new DataException("Invalid");
    }
}

