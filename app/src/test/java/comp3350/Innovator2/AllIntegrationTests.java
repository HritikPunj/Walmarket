package comp3350.Innovator2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.Innovator2.logic.SignInValidatorIT;
import comp3350.Innovator2.logic.StoreSearcherIT;
import comp3350.Innovator2.logic.CartManagerIT;
import comp3350.Innovator2.logic.UserManagerIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StoreSearcherIT.class,
        CartManagerIT.class,
        SignInValidatorIT.class,
        UserManagerIT.class
})

/**
 * Class for running all other testing classes through the JUnit Interface
 */
public class AllIntegrationTests {
}
