package comp3350.Innovator2.objects;

import comp3350.Innovator2.objects.utils.CardType;

public class VisaCard extends CreditCard {
    public VisaCard(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        super(cardNumber, cardHolderName, expiryDate, cvv);
    }

    @Override
    public String getPrefix() {
        return VISA_FIRST_DIGIT;
    }

    public CardType getType(){
        return CardType.Visa;
    }
}