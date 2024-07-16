package comp3350.Innovator2.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import comp3350.Innovator2.application.Services;
import comp3350.Innovator2.logic.exceptions.InvalidSignInException;
import comp3350.Innovator2.utils.TestUtils;

/**
 * Class which implements tests on the SignInValidator class
 */
public class SignInValidatorIT {
    ISignInValidator signInValidator;

    private File tempDB;

    @Before
    public void setUp() throws IOException {
        System.out.println("Starting integration test for SignInValidator");

        //Creating a temp DB for testing
        this.tempDB = TestUtils.copyDB();

        //Create an instance of class to be tested
        this.signInValidator = new SignInValidator(Services.getUserPersistence());

        assertNotNull(this.signInValidator);
    }

    @Test
    public void testValidCases() throws InvalidSignInException {
        System.out.println("\n -Testing valid passwords");

        //Test valid passwords
        assertTrue(signInValidator.validateSignIn("JohnG", "strong#123"));
        assertTrue(signInValidator.validateSignIn("ADMIN", "Comp3350!"));

        System.out.println(" -Finished testing valid passwords");
    }

    @Test (expected = InvalidSignInException.class)
    public void testInvalidCases() throws InvalidSignInException{
        System.out.println("\n -Testing invalid passwords");

        signInValidator.validateSignIn("this should", "be null");
        signInValidator.validateSignIn("testing getters", "means nothing");

        System.out.println(" -Finished testing invalid passwords");

    }

    @After
    public void tearDown() {
        System.out.println("\nReset database.");
        // reset DB
        this.tempDB.delete();

        //clear Services
        Services.clean();
        StoreHelper.clean();

        System.out.println("\nFinished integration test for SignInValidator.\n");
    }
}