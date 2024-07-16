package comp3350.Innovator2.AcceptanceTests;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

import comp3350.Innovator2.IndexMatching;

import android.content.Intent;

import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.*;
import org.junit.runner.RunWith;

import comp3350.Innovator2.R;
import comp3350.Innovator2.presentation.PageBrowser;

/**
 * Instrumented test, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4.class)
public class ItemBrowserInstrumentedTests {

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
     * initialize the StubDB
     */
    @Before
    public void setup() {
        Intent intent = new Intent();
        intent.putExtra("USING_STUB", "TRUE");
        rule.launchActivity(intent);
    }

    /**
     * Testing the search bar functionality, ensures that item(s) with name "Carrot"
     * exist and are displayed when searched for
     */
    @Test
    public void searchTest() {
        onView(withId(R.id.searchBar)).perform(typeText("Carrot"));
        Espresso.pressBack();
        onView(withId(R.id.searchButton)).perform(click());
        try {
            onView(allOf(withId(R.id.itemName), withText("Carrot"))).check(matches(isDisplayed()));
        } catch (AmbiguousViewMatcherException e) {
            onView(IndexMatching.withIndex(allOf(withId(R.id.itemName), withText("Carrot")), 0)).check(matches(isDisplayed()));
        }
    }

    /**
     * Testing the category functionality, ensures that item(s) matching the
     * selected category exist and are displayed
     */
    @Test
    public void categoryTest() {
        onView(withId(R.id.categoriesDropDown)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.categoriesDropDown)).check(matches(withSpinnerText("Fresh Produce")));
    }

    /**
     * Testing the category and search functionality in tandem
     */
    @Test
    public void categoryAndSearchTest() {
        onView(withId(R.id.categoriesDropDown)).perform(click());
        onData(anything()).atPosition(6).perform(click());
        onView(withId(R.id.categoriesDropDown)).check(matches(withSpinnerText("Miscellaneous")));

        onView(withId(R.id.searchBar)).perform(typeText("Carrot"));
        Espresso.pressBack();
        onView(withId(R.id.searchButton)).perform(click());

        try {
            onView(allOf(withId(R.id.itemName), withText("Carrot"))).check(matches(isDisplayed()));
        } catch (AmbiguousViewMatcherException e) {
            onView(IndexMatching.withIndex(allOf(withId(R.id.itemName), withText("Carrot")), 0)).check(matches(isDisplayed()));
        }
    }

    @After
    public void tearDown() {
        onView(withId(R.id.categoriesDropDown)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.categoriesDropDown)).check(matches(withSpinnerText("Default")));
        onView(withId(R.id.clearSearchButton)).perform(click());
    }

}