package comp3350.Innovator2.objects;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

import comp3350.Innovator2.objects.utils.Category;

/**
 * Class to test the Order class
 */
public class OrderTest {
    @Test
    public void testOrder()
    {
        Order order;

        System.out.println("\nStarting testOrder");

        //Form an array of items
        Item[] items = new Item[2];
        items[0] = new Item(12, 1, "Carrot", "Vegetable", 12.99, 8, Category.FreshProduce);
        items[1] = new Item(13, 1, "Bread", "Fresh baked Bread", 4.99, 18, Category.Bakery);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(items[0], 2));
        orderItems.add(new OrderItem(items[1], 1));

        order = new Order(1, orderItems, new Date(), 100);
        assertNotNull(order);
        assertEquals(1, order.getOrderID());
        assertEquals(2, order.getOrderItems().size());
        assertTrue(Math.abs((new Date()).getTime() - order.getOrderDate().getTime()) <= 1000);
        assertEquals(100, order.getOrderTotal(), 0.01);

        System.out.println("Finished testOrder");
    }
}
