package comp3350.Innovator2.logic;

import comp3350.Innovator2.logic.exceptions.*;
import comp3350.Innovator2.objects.CreditCard;

public interface ICardValidator {
    // throws errors based on validity, if none thrown, the card is valid
    boolean validateCard(CreditCard creditCard)
            throws InvalidCardNumberException,
            InvalidCvvException,
            InvalidExpiryDateException;
}