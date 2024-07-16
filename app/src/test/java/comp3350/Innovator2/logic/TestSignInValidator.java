package comp3350.Innovator2.logic;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import comp3350.Innovator2.data.IUserPersistence;
import comp3350.Innovator2.logic.exceptions.InvalidSignInException;
import comp3350.Innovator2.objects.User;

/**
 * Class which implements tests on the SignInValidator class
 */
public class TestSignInValidator {
    SignInValidator signInValidator;
    @Test
    public void testSignInValidator() throws InvalidSignInException {
        System.out.println("\nStarting testSignInValidator");
        IUserPersistence stub = mock(IUserPersistence.class);

        when(stub.authenticateUser("JohnG", "strong#123")).thenReturn(new User("Test", "TestLast", "JohnG", "TEST", null));
        when(stub.authenticateUser("ADMIN", "Comp3350!")).thenReturn(new User("Test", "TestLast", "ADMIN", "TEST", null));
        when(stub.authenticateUser("this should", "be null")).thenReturn(null);
        when(stub.authenticateUser("testing getters", "means nothing")).thenReturn(null);

        signInValidator = new SignInValidator(stub);

        test_ValidCases();
        test_InvalidCases();

        System.out.println("\nFinished testSignInValidator");
    }

    private void test_ValidCases() throws InvalidSignInException {
        assertTrue(signInValidator.validateSignIn("JohnG", "strong#123"));
        assertTrue(signInValidator.validateSignIn("ADMIN", "Comp3350!"));
    }

    private void test_InvalidCases() {
        try {
            signInValidator.validateSignIn("this should", "be null");
            signInValidator.validateSignIn("testing getters", "means nothing");
        }
        catch (InvalidSignInException e) {
            // this is expected
        }
    }
}