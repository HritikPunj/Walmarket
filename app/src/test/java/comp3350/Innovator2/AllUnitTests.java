package comp3350.Innovator2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.Innovator2.logic.TestCardValidator;
import comp3350.Innovator2.logic.TestCartManager;
import comp3350.Innovator2.logic.TestExceptions;
import comp3350.Innovator2.logic.TestNewPasswordValidator;
import comp3350.Innovator2.logic.TestUserManager;
import comp3350.Innovator2.objects.ItemTest;
import comp3350.Innovator2.objects.OrderTest;
import comp3350.Innovator2.objects.SellerTest;
import comp3350.Innovator2.objects.UserTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestCardValidator.class,
        TestNewPasswordValidator.class,
        ItemTest.class,
        OrderTest.class,
        UserTest.class,
        SellerTest.class,
        TestExceptions.class,
        TestCartManager.class,
        TestUserManager.class
})

/**
 * Class for running all other testing classes through the JUnit Interface
 */
public class AllUnitTests {
}
