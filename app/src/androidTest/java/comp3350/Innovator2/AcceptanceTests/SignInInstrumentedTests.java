package comp3350.Innovator2.AcceptanceTests;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.not;

import comp3350.Innovator2.IndexMatching;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.*;
import org.junit.runner.RunWith;

import comp3350.Innovator2.R;
import comp3350.Innovator2.presentation.PageBrowser;

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class SignInInstrumentedTests {

    /**
     * Initializes the activity without launching
     */
    @Rule
    public ActivityTestRule<PageBrowser> rule = new ActivityTestRule<>(
            PageBrowser.class,
            true,     // initialTouchMode
            false);

    /**
     * Handles launching the activity and passing the correct Intent to
     * initialize the StubDB, additionally should navigate any menus not
     * relevant to Sign In tests
     */
    @Before
    public void setup() {
        Intent intent = new Intent();
        intent.putExtra("USING_STUB", "TRUE");
        rule.launchActivity(intent);
    }

    /**
     * Testing the account creation process to ensure that
     * an account can be created with valid input (and cannot
     * with invalid input).
     */
    @Test
    public void createAccountTest() {
        onView(withId(R.id.signInOrCreateAccount)).perform(click());
        onView(withId(R.id.signInView)).check(matches(isDisplayed()));
        onView(withId(R.id.toCreatePageButton)).perform(click());
        onView(withId(R.id.createAccountView)).check(matches(isDisplayed()));
        onView(withId(R.id.firstName)).perform(typeText("John"));
        onView(withId(R.id.firstName)).check(matches(withText("John")));
        Espresso.pressBack();
        onView(withId(R.id.lastName)).perform(typeText("Adam"));
        onView(withId(R.id.lastName)).check(matches(withText("Adam")));
        Espresso.pressBack();
        onView(withId(R.id.email)).perform(typeText("johnadam@gmail.com"));
        onView(withId(R.id.email)).check(matches(withText("johnadam@gmail.com")));
        Espresso.pressBack();
        onView(withId(R.id.username)).perform(typeText("johnadam1"));
        Espresso.pressBack();
        onView(withId(R.id.password)).perform(typeText("Password123!"));
        onView(withId(R.id.password)).check(matches(withText("Password123!")));
        Espresso.pressBack();
        onView(withId(R.id.confirmPassword)).perform(typeText("Password123"));
        onView(withId(R.id.confirmPassword)).check(matches(withText("Password123")));
        Espresso.pressBack();
        //The confirm password field does not match the password field, should not continue to the next page
        onView(withId(R.id.toCreatePageButton)).perform(click());
        onView(withId(R.id.createAccountView)).check(matches(isDisplayed()));
        //Fix confirm password field
        onView(withId(R.id.confirmPassword)).perform(clearText());
        onView(withId(R.id.confirmPassword)).perform(typeText("Password123!"));
        onView(withId(R.id.confirmPassword)).check(matches(withText("Password123!")));
        Espresso.pressBack();
        onView(withId(R.id.toCreatePageButton)).perform(click());
    }

    /**
     * Testing the sign in functionality, ensures that a sign in operation
     * is possible on a dummy account
     */
    @Test
    public void signInTest() {
        onView(withId(R.id.signInOrCreateAccount)).perform(click());
        onView(withId(R.id.signInView)).check(matches(isDisplayed()));
        onView(withId(R.id.username)).perform(typeText("SmithS"));
        onView(withId(R.id.username)).check(matches(withText("SmithS")));
        Espresso.pressBack();
        onView(withId(R.id.password)).perform(typeText("weak24"));
        onView(withId(R.id.password)).check(matches(withText("weak24")));
        Espresso.pressBack();
        //Password is incorrect, should not go to the next page
        onView(withId(R.id.enterButton)).perform(click());
        onView(withId(R.id.signInView)).check(matches(isDisplayed()));
        //Fix password and sign in
        onView(withId(R.id.password)).perform(clearText());
        onView(withId(R.id.password)).perform(typeText("weak@24"));
        onView(withId(R.id.password)).check(matches(withText("weak@24")));
        Espresso.pressBack();
        onView(withId(R.id.enterButton)).perform(click());
        onView(withId(R.id.itemBrowserView)).check(matches(isDisplayed()));
        //Sign out
        onView(withId(R.id.signOutButton)).perform(click());
        addItemToCartAndProceedToPayment();
        //Sign in with popup
        onView(withId(R.id.signInPopup)).check(matches(isDisplayed()));
        onView(withId(R.id.enterButton)).perform(click());
        onView(withId(R.id.signInView)).check(matches(isDisplayed()));
        onView(withId(R.id.username)).perform(typeText("SmithS"));
        onView(withId(R.id.username)).check(matches(withText("SmithS")));
        Espresso.pressBack();
        onView(withId(R.id.password)).perform(typeText("weak@24"));
        onView(withId(R.id.password)).check(matches(withText("weak@24")));
        Espresso.pressBack();
        onView(withId(R.id.enterButton)).perform(click());
        onView(withId(R.id.layout)).check(matches(isDisplayed()));
        onView(withId(R.id.goBackButton)).perform(click());
        onView(withId(R.id.cartView)).check(matches(isDisplayed()));
        //Check that popup doesn't reappear
        onView(withId(R.id.proceedToPayment)).perform(click());
        onView(withId(R.id.layout)).check(matches(isDisplayed()));
        //Sign out
        onView(withId(R.id.goBackButton)).perform(click());
        onView(withId(R.id.cartView)).check(matches(isDisplayed()));
        onView(withId(R.id.itemBrowserPageButton)).perform(click());
    }

    /**
     * Testing the payment method saving process to ensure that
     * an account can have credit cards added with valid input (and cannot
     * with invalid input).
     */
    @Test
    public void addPaymentMethodTest() {
        addItemToCartAndProceedToPayment();
        onView(withId(R.id.signInPopup)).check(matches(isDisplayed()));
        onView(withId(R.id.guestButton)).perform(click());
        onView(withId(R.id.layout)).check(matches(isDisplayed()));
        //Check that save card payment option is not displayed for guest users
        onView(withId(R.id.savePaymentCheckbox)).check(matches(not(isDisplayed())));
        onView(withId(R.id.goBackButton)).perform(click());
        onView(withId(R.id.cartView)).check(matches(isDisplayed()));
        onView(withId(R.id.proceedToPayment)).perform(click());
        onView(withId(R.id.enterButton)).perform(click());
        onView(withId(R.id.signInView)).check(matches(isDisplayed()));
        onView(withId(R.id.username)).perform(typeText("SmithS"));
        onView(withId(R.id.username)).check(matches(withText("SmithS")));
        Espresso.pressBack();
        onView(withId(R.id.password)).perform(typeText("weak@24"));
        onView(withId(R.id.password)).check(matches(withText("weak@24")));
        Espresso.pressBack();
        onView(withId(R.id.enterButton)).perform(click());
        onView(withId(R.id.layout)).check(matches(isDisplayed()));
        onView(withId(R.id.nameofCardHolder)).perform(typeText("Steve Smith"));
        onView(withId(R.id.nameofCardHolder)).check(matches(withText("Steve Smith")));
        Espresso.pressBack();
        onView(withId(R.id.creditCardNum)).perform(typeText("4123452341234532"));
        onView(withId(R.id.creditCardNum)).check(matches(withText("4123452341234532")));
        Espresso.pressBack();
        onView(withId(R.id.expiryDate)).perform(typeText("1225"));
        onView(withId(R.id.expiryDate)).check(matches(withText("1225")));
        Espresso.pressBack();
        onView(withId(R.id.cvv)).perform(typeText("123"));
        onView(withId(R.id.cvv)).check(matches(withText("123")));
        Espresso.pressBack();
        onView(withText(R.string.visaString)).perform(click());
        onView(withText(R.string.visaString)).check(matches(isChecked()));
        onView(withId(R.id.savePaymentCheckbox)).perform(click());
        onView(withId(R.id.savePaymentCheckbox)).check(matches(isChecked()));
        onView(withId(R.id.paymentButton)).perform(click());
        onView(withId(R.id.cartView)).check(matches(isDisplayed()));
        onView(withId(R.id.itemBrowserPageButton)).perform(click());
        onView(withId(R.id.itemBrowserView)).check(matches(isDisplayed()));
        //Check that payment info was saved
        addItemToCartAndProceedToPayment();
        onView(withId(R.id.layout)).check(matches(isDisplayed()));
        onView(withId(R.id.savePaymentCheckbox)).check(matches(not(isDisplayed())));
        onView(withId(R.id.nameofCardHolder)).check(matches(withText("Steve Smith")));
        onView(withId(R.id.creditCardNum)).check(matches(withText("4123452341234532")));
        onView(withId(R.id.expiryDate)).check(matches(withText("1225")));
        onView(withId(R.id.cvv)).check(matches(withText("123")));
        onView(withText(R.string.visaString)).check(matches(isChecked()));
        onView(withId(R.id.paymentButton)).perform(click());
        onView(withId(R.id.cartView)).check(matches(isDisplayed()));
        onView(withId(R.id.itemBrowserPageButton)).perform(click());
    }

    public void addItemToCartAndProceedToPayment(){
        onView(IndexMatching.withIndex(withId(R.id.incrementCartQuantity), 0)).perform(click());
        onView(IndexMatching.withIndex(withId(R.id.specialButton), 0)).perform(click());
        onView(withId(R.id.myCartPageButton)).perform(click());
        onView(withId(R.id.proceedToPayment)).perform(click());
    }

    @After
    public void tearDown() {
        //Sign out
        onView(withId(R.id.itemBrowserView)).check(matches(isDisplayed()));
        onView(withId(R.id.signOutButton)).perform(click());
    }

}