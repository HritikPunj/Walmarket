package comp3350.Innovator2.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;

import comp3350.Innovator2.application.Services;
import comp3350.Innovator2.data.IUserPersistence;
import comp3350.Innovator2.logic.exceptions.InvalidSignInException;
import comp3350.Innovator2.logic.exceptions.InvalidUsernameException;
import comp3350.Innovator2.objects.User;

/**
 * Class which implements tests on the User Manager class
 */
public class TestUserManager {
    private IUserManager userManager;
    @Mock
    private IUserPersistence mock;

    @Before
    public void setUp() throws IOException {

        mock = mock(IUserPersistence.class);

        when(mock.getUser("testUser")).thenReturn(false);
        when(mock.getUser("JohnG")).thenReturn(true);
        when(mock.authenticateUser("JohnG", "strong#123")).thenReturn(new User("Test", "TestLast", "JohnG", "TEST", new ArrayList<>()));

        Services.setMockUserDB(mock);

        this.userManager = StoreHelper.getUserManager();
    }
    @Test(expected = InvalidSignInException.class)
    public void signInWhenUserDoesNotExist() throws Exception {
        System.out.println("\n -Testing signing in with invalid credentials");
        String username = "nonexistentUser";
        String password = "Password#123";

        when(mock.getUser(username)).thenReturn(false);

        userManager.signIn(username, password);
    }

    @Test(expected = InvalidSignInException.class)
    public void signInWhenAlreadySignedIn() throws Exception {
        System.out.println("\n -Testing signing when already signed in");

        String firstUsername = "firstUser";
        String firstPassword = "Password#123";

        User firstUser = new User(firstUsername, "First", "User", "email@example.com", null);

        when(mock.getUser(firstUsername)).thenReturn(true);
        when(mock.authenticateUser(firstUsername, firstPassword)).thenReturn(firstUser);

        userManager.signIn(firstUsername, firstPassword);

        // Trying to sign in again while already signed in
        String secondUsername = "secondUser";
        String secondPassword = "Password#122";
        userManager.signIn(secondUsername, secondPassword);
    }


    @Test(expected = InvalidUsernameException.class)
    public void createUserWithExistingUsername() throws Exception {
        System.out.println("\n -Testing creating user with existing username");

        String username = "existingUser";
        when(mock.getUser(username)).thenReturn(true);

        userManager.create("John", "Doe", username, "Passw@123", "Passw@123", "johndoe@example.com");
    }

    @Test
    public void checkSignInStatus() throws Exception {
        System.out.println("\n -Testing the sign in status");

        userManager.signOut();
        assertFalse(userManager.isSignedIn());

        String username = "user";
        String password = "Password@123";
        User newUser = new User("John", "Doe", username, "johndoe@example.com", null);

        when(mock.getUser(username)).thenReturn(true);
        when(mock.authenticateUser(username, password)).thenReturn(newUser);

        userManager.signIn(username, password);

        assertTrue(userManager.isSignedIn());
    }

    @After
    public void cleanUp(){

        //clear Services
        Services.clean();
        StoreHelper.clean();
    }
}