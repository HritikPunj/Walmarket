package comp3350.Innovator2.logic;

import comp3350.Innovator2.logic.exceptions.InvalidCardNumberException;
import comp3350.Innovator2.logic.exceptions.InvalidCvvException;
import comp3350.Innovator2.logic.exceptions.InvalidExpiryDateException;
import comp3350.Innovator2.objects.CreditCard;

public class CardValidator implements ICardValidator {
    private static final int VALID_CARD_LENGTH = 16; // 16 characters long
    private static final int VALID_CVV_LENGTH = 3; // 3 characters
    private static final int VALID_EXPIRY_LENGTH = 4; // 4 characters long: "MMYY"
    private static final int CURRENT_YEAR = 2024; // Project due in 2024
    private static final int CURRENT_MONTH = 4; // Project due in April

    // invalid error messages set as constants
    private static final String INVALID_CARD_NUMBER_MSG = "The credit card number you entered is invalid.";
    private static final String INVALID_CVV_MSG = "The CVV you entered is invalid.";
    private static final String INVALID_EXPIRY_DATE_MSG = "The expiry date you entered is invalid.";
    private String cardNumber, expiryDate, cvv, validCardPrefix;

    /**
     * #### void validateCards(CreditCard creditCard)
     *          throws InvalidCardNumberException, InvalidCvvException, InvalidExpiryDateException
     * Sets payment details from parameter: creditCard
     * Validate the credit card based on its number, CVV and expiry date
     * Throw appropriate exceptions
     * No exceptions thrown implies the card was valid
     */
    @Override
    public boolean validateCard(CreditCard creditCard) throws
            InvalidCardNumberException, InvalidCvvException, InvalidExpiryDateException {
        setPaymentDetails(creditCard); // set all details

        if (!isCardNumberValid()) {
            throw new InvalidCardNumberException(INVALID_CARD_NUMBER_MSG);
        }

        if (!isCvvValid()) {
            throw new InvalidCvvException(INVALID_CVV_MSG);
        }

        if (!isExpiryDateValid()) {
            throw new InvalidExpiryDateException(INVALID_EXPIRY_DATE_MSG);
        }

        return true;
    }

    /**
     * #### void setPaymentDetails(CreditCard creditCard)
     * set the variables based on the given payment details
     */
    private void setPaymentDetails(CreditCard creditCard) {
        cardNumber = creditCard.getCardNumber();
        expiryDate = creditCard.getExpiryDate();
        cvv = creditCard.getCvv();
        validCardPrefix = creditCard.getPrefix(); // "4" for Visa and "5" for Mastercard
    }

    /**
     * #### boolean isCardNumberValid()
     * Validate the credit card number based on its length, if it's numeric and prefix
     * Returns validity
     */
    private boolean isCardNumberValid() {
        return (cardNumber.length() == VALID_CARD_LENGTH
                && isInputNumeric(cardNumber)
                && cardNumber.startsWith(validCardPrefix));
    }

    /**
     * #### boolean isCvvValid()
     * Validate the CVV based on its length and if it's numeric
     * Returns validity
     */
    private boolean isCvvValid() {
        return (cvv.length() == VALID_CVV_LENGTH && isInputNumeric(cvv));
    }

    /**
     * #### boolean isExpiryDateValid()
     * Validate the expiry date based on its length, if it's numeric and range
     * Returns validity
     */
    private boolean isExpiryDateValid() {
        int month, year;
        // first check length and numeric input
        if (expiryDate.length() != VALID_EXPIRY_LENGTH || !isInputNumeric(expiryDate)) {
            return false;
        } else {
            // check the range based on the month and year
            month = Integer.parseInt(expiryDate.substring(0, 2));
            year = Integer.parseInt(expiryDate.substring(2)) + 2000; // change to 20yy
            return isDateValid(month, year);
        }
    }

    /**
     * #### boolean isInputNumeric(String input)
     * Checks if the input is numeric, ensure edge cases of empty input
     * Returns validity
     */
    private boolean isInputNumeric(String input) {
        return (!input.isEmpty() && input.matches("\\d+"));
    }

    /**
     * #### boolean isDateValid(int month, int year)
     * Checks if the passed in expiry date is valid
     * Ensure that current years are valid is they are after current month+1
     * Returns validity
     */
    private boolean isDateValid(int month, int year) {
        // check if the month is valid
        if (month < 1 || month > 12) {
            return false;
        }

        // check based on the year
        if (year == CURRENT_YEAR) {
            return month > CURRENT_MONTH; // cannot expire before the current month
        } else {
            return year > CURRENT_YEAR; // cannot expire before the current year
        }
    }
}