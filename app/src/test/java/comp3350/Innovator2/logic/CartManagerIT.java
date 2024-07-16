package comp3350.Innovator2.logic;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import comp3350.Innovator2.application.Services;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.Item;
import comp3350.Innovator2.utils.TestUtils;

public class CartManagerIT
{
    private ICartManager cart;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        System.out.println("Starting integration test for Cart Manager");

        //Creating a temp DB for testing
        this.tempDB = TestUtils.copyDB();

        //Create an instance of class to be tested
        this.cart = StoreHelper.getCartManager();

        assertNotNull(this.cart);
    }

    @Test
    public void testPurchaseCart(){
        System.out.println("\n -Adding items in cart to test purchase\n");

        Item item1 = Services.getItemPersistence().getItem(1);
        Item item2 = Services.getItemPersistence().getItem(2);

        try {
            //Add two units of item 1
            cart.addItems(1, item1);
            cart.addItems(1, item1);

            //Add 1 unit of item 2
            cart.addItems(1, item2);
        } catch (UIException e) {
            System.out.println("Error inserting items to cart" + e);
        }

        cart.purchaseCart();

        System.out.println("\n -Testing quantity after purchase\n");

        assertEquals(10, Services.getItemPersistence().getItem(1).getStock());
        assertEquals(19, Services.getItemPersistence().getItem(2).getStock());

        //Empty the cart
        cart.empty();
    }

    @After
    public void tearDown() {
        System.out.println("\nReset database.");
        // reset DB
        this.tempDB.delete();

        //clear Services
        Services.clean();

        System.out.println("\nFinished integration test for Cart Manager.\n");
    }
}
