package comp3350.Innovator2.logic;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

import comp3350.Innovator2.objects.utils.Category;
import comp3350.Innovator2.objects.Item;

public class TestCartManager {

    //================================================== Test items

    private final ICartManager cart = StoreHelper.getCartManager();

    private final Item apple = new Item
            (
                    1,
                    1,
                    "Apple",
                    "Red & Shiny",
                    3.5,
                    2,
                    Category.Fruit
            );

    private final Item bread = new Item
            (
                    2,
                    1,
                    "Bread",
                    "White",
                    4.75,
                    10,
                    Category.Bakery
            );

    @Test
    public void runTests() {

        System.out.println("\nStarting Cart Tests");

        //================================================== Empty cart

        System.out.println("\nTesting empty cart");
        testRemove(apple, 0);
        testCount(apple, 0);
        testItemTotal(apple, 0);
        testTotal(0);
        System.out.println("Finished testing empty cart");

        //================================================== Passing null

        System.out.println("Testing against null parameters");
        testAdd(null, 0);
        testRemove(null, 0);
        testCount(null, 0);
        testItemTotal(null, 0);
        System.out.println("Finished testing against null parameters");

        //================================================== Items in cart

        //------------------------------ Add items

        System.out.println("\nTesting adding items to cart");
        testAdd(apple, 1);
        testAdd(apple, 2);
        testAdd(apple, 2); //Stock limit

        testAdd(bread, 1);
        testAdd(bread, 2);
        System.out.println("Finished testing adding items to cart");

        //------------------------------ Remove items

        System.out.println("\nTesting removing items from cart");
        testRemove(bread, 1);
        testRemove(bread, 0);
        testRemove(bread, 0); //Non-negative
        System.out.println("Finished testing removing items from cart");

        //------------------------------ Calc prices

        testAdd(bread, 1); //Re-add bread

        System.out.println("\nTesting Items total cost in cart");
        testItemTotal(apple, 7.0);
        testItemTotal(bread, 4.75);
        testTotal(11.75);
        System.out.println("Finished testing Items total cost in cart");

        //------------------------------ Empty cart

        testEmpty();

        System.out.println("\nFinished Cart Tests\n");
    }

    //================================================== Test Methods

    private void testAdd(Item item, int expectedCount) {
        try {
            cart.addItems(1, item);
        } catch (Exception e) {}
        assertEquals(cart.count(item), expectedCount);
    }

    private void testRemove(Item item, int expectedCount)
    {
        cart.remove(item);
        assertEquals(cart.count(item), expectedCount);
    }

    private void testCount(Item item, int expectedCount)
    {
        assertEquals(cart.count(item), expectedCount);
    }

    private void testItemTotal(Item item, double expectedTotal)
    {
        assertEquals(cart.itemTotal(item), expectedTotal);
    }

    private void testTotal(double expectedTotal)
    {
        assertEquals(cart.total(), expectedTotal);
    }

    private void testEmpty()
    {
        cart.empty();
        assertEquals(cart.total(), 0.0);
    }
}
