package comp3350.Innovator2.objects;

import org.junit.Test;

import static org.junit.Assert.*;

import android.accounts.Account;

import java.util.List;

import java.util.ArrayList;

import comp3350.Innovator2.objects.utils.AccountType;

/**
 * Class to test the User class
 */
public class UserTest {
    @Test
    public void testUser()
    {
        User user;

        System.out.println("\nStarting testUser");

        List<CreditCard> payInfo = new ArrayList<>();
        payInfo.add(new MasterCard("1234567890123456", "Test", "12/24", "123"));

        user = new User("John", "Smith", "Test", "test@test.com", payInfo);
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("Test", user.getUsername());
        assertEquals("test@test.com", user.getEmail());
        assertNotNull(user.getPaymentInfo());
        assertEquals("1234567890123456", user.getPaymentInfo().get(0).getCardNumber());
        assertEquals("Test", user.getPaymentInfo().get(0).getCardHolderName());
        assertEquals("12/24", user.getPaymentInfo().get(0).getExpiryDate());
        assertEquals("123", user.getPaymentInfo().get(0).getCvv());
        assertEquals(AccountType.User.name(), user.getAccountType().name());

        System.out.println("Finished testUser");
    }
}

