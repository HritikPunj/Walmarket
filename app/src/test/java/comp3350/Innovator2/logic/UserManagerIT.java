package comp3350.Innovator2.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import comp3350.Innovator2.application.Services;
import comp3350.Innovator2.logic.exceptions.InvalidSignInException;
import comp3350.Innovator2.logic.exceptions.InvalidUsernameException;
import comp3350.Innovator2.objects.CreditCard;
import comp3350.Innovator2.objects.MasterCard;
import comp3350.Innovator2.utils.TestUtils;

/**
 * Class which implements tests on the SignInValidator class
 */
public class UserManagerIT {
    private UserManager userManager;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        System.out.println("Starting integration test for UserManager");

        //Creating a temp DB for testing
        this.tempDB = TestUtils.copyDB();

        //Create an instance of class to be tested
        this.userManager = new UserManager();

        assertNotNull(this.userManager);
    }

    @Test
    public void testCreateAndSignInUser() throws Exception {
        System.out.println("\n -Testing creating and signing in user");

        //Creating test user
        String username = "testUser";
        String password = "TestPass@123";
        String firstname = "Test";
        String lastname = "User";
        String email = "test@example.com";

        //Test for already signed in
        assertFalse(userManager.isSignedIn());

        // Test user creation
        userManager.create(firstname, lastname, username, password, password, email);
        userManager.signIn(username, password);

        assertTrue(Services.getUserPersistence().getUser(username));
        assertTrue(userManager.isSignedIn());

        System.out.println("\n -Finished testing creating and signing in user");
    }

    @Test(expected = InvalidUsernameException.class)
    public void testCreateDuplicateUser() throws Exception {
        System.out.println("\n -Testing creating duplicate users");

        String username = "duplicateUser";
        String password = "TestPass@123";
        String firstname = "Duplicate";
        String lastname = "User";
        String email = "duplicate@example.com";

        userManager.create(firstname, lastname, username, password, password, email);

        // Attempt to create a user with the same username
        userManager.create(firstname, lastname, username, password, password, email);

        System.out.println("\n -Finished testing creating duplicate users");
    }

    @Test(expected = InvalidSignInException.class)
    public void testSignInWithInvalidCredentials() throws Exception {
        System.out.println("\n -Testing signing in with invalid credentials");

        String username = "nonExistentUser";
        String password = "wrongPass";

        System.out.println("\n -Testing signing in with invalid username");
        // Attempt to sign in with a username that does not exist
        userManager.signIn(username, password);
        System.out.println(" -Finished testing signing in with invalid username");

        //Attempt to sign in with wrong password
        String validUsername = "JohnG";
        String incPassword = "InternetFriend";

        System.out.println("\n -Testing signing in with invalid password but valid username");
        userManager.signIn(validUsername, incPassword);
        System.out.println(" -Finished testing signing in with invalid password but valid username");
    }

    @Test
    public void testSignOutWithoutSignIn() {
        System.out.println("\n -Testing signing out");

        userManager.signOut(); // This should not throw any exceptions
        assertFalse(userManager.isSignedIn());

        System.out.println("\n -Testing signing out");
    }

    @Test
    public void testGetCard() throws Exception {
        System.out.println("\n -Testing saving and getting credit card");

        // First,sign in
        String username = "ADMIN";
        String password = "Comp3350!";

        userManager.signIn(username, password);

        assertTrue(userManager.isSignedIn());

        // Save a new card for the signed-in user
        String cardName = "Payment User";
        String cardNumber = "5234567890123456";
        String expiryDate = "12/25";
        String cvv = "123";
        boolean isMastercard = true; // For this test, we use a MasterCard
        userManager.saveCard(cardName, cardNumber, expiryDate, cvv, isMastercard);

        // Attempt to retrieve the saved card
        CreditCard retrievedCard = userManager.getCard(0);

        assertNotNull(retrievedCard);
        assertEquals(cardName, retrievedCard.getCardHolderName());
        assertEquals(cardNumber, retrievedCard.getCardNumber());
        assertEquals(expiryDate, retrievedCard.getExpiryDate());
        assertEquals(cvv, retrievedCard.getCvv());
        assertTrue(retrievedCard instanceof MasterCard); // Check card type

        // Sign out after the test
        userManager.signOut();
        assertFalse(userManager.isSignedIn());

        System.out.println("\n -Finished testing saving and getting credit card");
    }

    @After
    public void tearDown() {
        System.out.println("\nReset database.");
        // reset DB
        this.tempDB.delete();

        //clear Services
        Services.clean();
        StoreHelper.clean();

        System.out.println("\nFinished integration test for UserManager.\n");
    }
}