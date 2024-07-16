package comp3350.Innovator2.logic;

import org.junit.Test;

import static org.junit.Assert.*;

import comp3350.Innovator2.logic.exceptions.CreditCardException;
import comp3350.Innovator2.logic.exceptions.InvalidCardNumberException;
import comp3350.Innovator2.logic.exceptions.InvalidCvvException;
import comp3350.Innovator2.logic.exceptions.InvalidExpiryDateException;
import comp3350.Innovator2.objects.CreditCard;
import comp3350.Innovator2.objects.MasterCard;
import comp3350.Innovator2.objects.VisaCard;

/**
 * Class which implements tests on the CardValidator class
 */

public class TestCardValidator {
    @Test
    public void testValidVisaCards() throws CreditCardException {
        System.out.println("\nStarting testValidVisaCards");

        ICardValidator cardValidator = StoreHelper.getCardValidator();

        String[] validVisaCards = {
                "4123456789012345", "4234567890123456", "4345678901234567",
                "4456789012345678", "4567890123456789", "4678901234567890",
                "4789012345678901", "4890123456789012", "4901234567890123",
                "4012345678901234",
        };
        for (String cardNumber : validVisaCards) {
            CreditCard visaCard = new VisaCard(cardNumber, "John Doe", "1225", "123");
            cardValidator.validateCard(visaCard);
        }
        System.out.println("Finished testValidVisaCards");
    }

    @Test
    public void testValidMasterCards() throws CreditCardException {
        System.out.println("\nStarting testValidMasterCards");

        ICardValidator cardValidator = StoreHelper.getCardValidator();

        String[] validMasterCards = {
                "5123456789012345", "5234567890123456", "5345678901234567",
                "5456789012345678", "5567890123456789", "5678901234567890",
                "5789012345678901", "5890123456789012", "5901234567890123",
                "5012345678901234",
        };
        for (String cardNumber : validMasterCards) {
            CreditCard masterCard = new MasterCard(cardNumber, "John Doe", "1225", "123");
            assertTrue(cardValidator.validateCard(masterCard)); // will fail if any exceptions are returned, otherwise valid
        }
        System.out.println("Finished testValidMasterCards");
    }

    @Test
    public void testInvalidCardNumbers() throws CreditCardException {
        System.out.println("\nStarting testInvalidCardNumbers");

        ICardValidator cardValidator = StoreHelper.getCardValidator();

        String[] invalidCardNumbers = {
                "1234", "567890123456", "123456789012", "2345678901",
                "34567890123", "456789012", "56789012", "678901",
                "7890", "890", "", " ", "401234567890123x", "abcdefghijklmnop",
        };
        for (String cardNumber : invalidCardNumbers) {
            try {
                CreditCard invalidCard = new VisaCard(cardNumber, "John Doe", "1225", "123");
                cardValidator.validateCard(invalidCard);
            }
            catch (InvalidCardNumberException e) {
                // expected
            }
            catch (Exception e) {
                fail("Unexpected exception of password: " + invalidCardNumbers);
            }
        }
        System.out.println("Finished testInvalidCardNumbers");
    }

    @Test
    public void testExpiredCards() throws CreditCardException {
        System.out.println("\nStarting testExpiredCards");

        ICardValidator cardValidator = StoreHelper.getCardValidator();

        String[] expiredCards = {
                "0324", "0423", "1122", "0923", "0424", " ",
                "0123", "0722", "0821", "0519", "1020", "", "abcd"
        };
        for (String expiryDate : expiredCards) {
            try {
                CreditCard expiredCard = new VisaCard("4123456789012345", "Jane Doe", expiryDate, "321");
                cardValidator.validateCard(expiredCard);
            }
            catch (InvalidExpiryDateException e) {
                // expected
            }
            catch (Exception e) {
                fail("Unexpected exception of password: " + expiryDate);
            }
        }
        System.out.println("Finished testExpiredCards");
    }

    @Test
    public void testInvalidCvvLength() throws CreditCardException {
        System.out.println("\nStarting testInvalidCvvLength");

        ICardValidator cardValidator = StoreHelper.getCardValidator();

        String[] invalidCvvs = {
                "12", "1234", "1", "23456", "12345","0",
                "9876", "abc", "99999", "1111", "", " ",
        };
        for (String cvv : invalidCvvs) {
            try {
                CreditCard invalidCard = new VisaCard("4123456789012345", "John Doe", "1225", cvv);
                cardValidator.validateCard(invalidCard);
            }
            catch (InvalidCvvException e) {
                // expected
            }
            catch (Exception e) {
                fail("Unexpected exception of password: " + invalidCvvs);
            }
        }
        System.out.println("Finished testInvalidCvvLength");
    }
}