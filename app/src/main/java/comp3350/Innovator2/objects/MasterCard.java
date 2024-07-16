package comp3350.Innovator2.objects;

import comp3350.Innovator2.objects.utils.CardType;

public class MasterCard extends CreditCard {

    public MasterCard(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        super(cardNumber, cardHolderName, expiryDate, cvv);
    }

    @Override
    public String getPrefix() {
        return MASTERCARD_FIRST_DIGIT;
    }

    public CardType getType(){
        return CardType.Mastercard;
    }
}