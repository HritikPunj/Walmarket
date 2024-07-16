package comp3350.Innovator2.logic;

import comp3350.Innovator2.objects.CreditCard;
import comp3350.Innovator2.objects.MasterCard;
import comp3350.Innovator2.objects.VisaCard;

public class CreditCardFactory {
    public static CreditCard Create(String cardHolderName, String creditCardNumber, String expiryDate, String cvv, boolean mastercardSelected) {
        if (mastercardSelected) return new MasterCard(creditCardNumber, cardHolderName, expiryDate, cvv);
        else return new VisaCard(creditCardNumber, cardHolderName, expiryDate, cvv);
    }
}
