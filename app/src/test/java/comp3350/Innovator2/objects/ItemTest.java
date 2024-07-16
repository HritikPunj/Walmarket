package comp3350.Innovator2.objects;

import org.junit.Test;

import static org.junit.Assert.*;

import comp3350.Innovator2.objects.utils.Category;

/**
 * Class to test the Item class
 */
public class ItemTest {

    @Test
    public void testItem()
    {
        Item item;

        System.out.println("\nStarting testItem");

        item = new Item(12, 1, "Carrot", "Vegetable", 12.99, 8, Category.FreshProduce);
        assertNotNull(item);
        assertEquals(12, item.getItemID());
        assertEquals("Carrot", item.getTitle());
        assertEquals("Vegetable", item.getDescription());
        assertEquals(12.99, item.getCost(), 0.001);
        assertEquals(8, item.getStock());
        assertEquals(Category.FreshProduce, item.getCategory());

        System.out.println("Finished testItem");
    }

}
