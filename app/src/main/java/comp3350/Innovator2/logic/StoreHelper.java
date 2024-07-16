package comp3350.Innovator2.logic;

import comp3350.Innovator2.application.Services;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.CreditCard;

public class StoreHelper// implements IStoreHelper
{
    private static IStoreSearcher searcher;
    private static ICartManager cartManager;
    private static ICardValidator cardValidator;
    private static IUserManager userManager;
    private static ISignInValidator signInValidator;
    private static INewPasswordValidator passwordValidator;
    public static IStoreSearcher getStoreSearcher() {
        if (searcher == null) searcher = new StoreSearcher();
        return searcher;
    }

    public static ICartManager getCartManager() {
        if (cartManager == null) cartManager = new CartManager();
        return cartManager;
    }

    public static ICardValidator getCardValidator()
    {
        if (cardValidator == null) cardValidator = new CardValidator();
        return cardValidator;
    }

    public static IUserManager getUserManager()
    {
        if (userManager == null) userManager = new UserManager();
        return userManager;
    }

    public static INewPasswordValidator getPasswordValidator()
    {
        if (passwordValidator == null) passwordValidator = new NewPasswordValidator();
        return passwordValidator;
    }

    public static ISignInValidator getSignInValidator()
    {
        if (signInValidator == null) signInValidator = new SignInValidator(Services.getUserPersistence());
        return signInValidator;
    }

    public static void runCreditCardPayment(String cardHolderName, String creditCardNumber, String expiryDate, String cvv, boolean mastercardSelected) throws UIException {
        CreditCard inputCard = CreditCardFactory.Create(cardHolderName, creditCardNumber, expiryDate, cvv, mastercardSelected);
        getCardValidator().validateCard(inputCard); //Can throw exceptions, have custom messages for UI
        getCartManager().purchaseCart();
    }

    public static void clean(){
        searcher = null;
        cartManager = null;
        cardValidator = null;
        userManager = null;
        signInValidator = null;
        passwordValidator = null;
    }
}
