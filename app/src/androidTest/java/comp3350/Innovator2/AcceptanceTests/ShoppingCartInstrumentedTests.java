package comp3350.Innovator2.AcceptanceTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import android.content.Intent;
import comp3350.Innovator2.IndexMatching;
import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import comp3350.Innovator2.R;
import comp3350.Innovator2.presentation.PageBrowser;

/**
 * Instrumented test, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4.class)
public class ShoppingCartInstrumentedTests {

    @Rule
    public ActivityTestRule<PageBrowser> rule = new ActivityTestRule<>(
            PageBrowser.class,
            true,     // initialTouchMode
            false);

    /**
     * Handles launching the activity and passing the correct Intent to
     * initialize the StubDB. Also handles adding some items to the cart
     * and navigating to the cart page before beginning the tests.
     */
    @Before
    public void setup() {
        Intent intent = new Intent();
        intent.putExtra("USING_STUB", "TRUE");
        rule.launchActivity(intent);

        try {
            onView(withId(R.id.incrementCartQuantity)).perform(click());
        } catch (AmbiguousViewMatcherException e) {
            onView(IndexMatching.withIndex(withId(R.id.incrementCartQuantity), 0)).perform(click());
        }
        onView(IndexMatching.withIndex(withId(R.id.specialButton), 0)).perform(click());
    }


    @Test
    public void purchaseTest() {
        onView(withId(R.id.myCartPageButton)).perform(click());
        onView(withId(R.id.cartView)).check(matches(isDisplayed()));
        onView(withId(R.id.proceedToPayment)).perform(click());
        onView(withId(R.id.signInPopup)).check(matches(isDisplayed()));
        onView(withId(R.id.guestButton)).perform(click());
        onView(withId(R.id.layout)).check(matches(isDisplayed()));
        onView(withId(R.id.nameofCardHolder)).perform(typeText("John Adam"));
        onView(withId(R.id.nameofCardHolder)).check(matches(withText("John Adam")));
        Espresso.pressBack();
        onView(withId(R.id.creditCardNum)).perform(typeText("4512345665432123"));
        onView(withId(R.id.creditCardNum)).check(matches(withText("4512345665432123")));
        Espresso.pressBack();
        onView(withId(R.id.expiryDate)).perform(typeText("0726"));
        onView(withId(R.id.expiryDate)).check(matches(withText("0726")));
        Espresso.pressBack();
        onView(withId(R.id.cvv)).perform(typeText("101"));
        onView(withId(R.id.cvv)).check(matches(withText("101")));
        Espresso.pressBack();
        onView(withText(R.string.masterCardString)).perform(click());
        onView(withText(R.string.masterCardString)).check(matches(isChecked()));
        //Should be Visa not MasterCard should not proceed to cart
        onView(withId(R.id.paymentButton)).perform(click());
        onView(withId(R.id.layout)).check(matches(isDisplayed()));
        //Fix card type selection
        onView(withText(R.string.visaString)).perform(click());
        onView(withText(R.string.visaString)).check(matches(isChecked()));
        onView(withId(R.id.paymentButton)).perform(click());
        onView(withId(R.id.cartView)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {}

}