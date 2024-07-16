package comp3350.Innovator2.objects;

import comp3350.Innovator2.objects.utils.CardType;

public abstract class CreditCard {
    protected static final String VISA_FIRST_DIGIT = "4";
    protected static final String MASTERCARD_FIRST_DIGIT = "5";
    protected String cardNumber, cardHolderName, expiryDate, cvv;

    public CreditCard(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    // Getters
    public String getCardNumber() {return cardNumber;}
    public String getCardHolderName() {return cardHolderName;}
    public String getExpiryDate() {return expiryDate;}
    public String getCvv() {return cvv;}

    public abstract String getPrefix(); // get prefix based on card type (Visa vs MasterCard)

    public abstract CardType getType();
}