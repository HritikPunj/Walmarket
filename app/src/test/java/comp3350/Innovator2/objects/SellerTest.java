package comp3350.Innovator2.objects;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.List;

import java.util.ArrayList;

import comp3350.Innovator2.objects.utils.AccountType;

public class SellerTest {

    @Test
    public void testSeller()
    {
        Seller seller;

        System.out.println("\nStarting testSeller");

        List<CreditCard> payInfo = new ArrayList<>();
        payInfo.add(new MasterCard("1234567890123456", "Test", "12/24", "123"));

        seller = new Seller("John", "Seller", 1, "Test", "test@test.com", payInfo, new BankingInfo(1234567, 305, 002));
        assertNotNull(seller);

        assertEquals("John", seller.getFirstName());
        assertEquals("Seller", seller.getLastName());
        assertEquals(1, seller.getSellerID());
        assertEquals("Test", seller.getUsername());
        assertEquals("test@test.com", seller.getEmail());
        assertNotNull(seller.getPaymentInfo());
        assertEquals("1234567890123456", seller.getPaymentInfo().get(0).getCardNumber());
        assertEquals("Test", seller.getPaymentInfo().get(0).getCardHolderName());
        assertEquals("12/24", seller.getPaymentInfo().get(0).getExpiryDate());
        assertEquals("123", seller.getPaymentInfo().get(0).getCvv());
        assertNotNull(seller.getDebitDetails());
        assertEquals(1234567, seller.getDebitDetails().getAccountNumber());
        assertEquals(305, seller.getDebitDetails().getTransitCode());
        assertEquals(002, seller.getDebitDetails().getBranchCode());
        assertEquals(AccountType.Seller.name(), seller.getAccountType().name());

        System.out.println("Finished testSeller");
    }

}
