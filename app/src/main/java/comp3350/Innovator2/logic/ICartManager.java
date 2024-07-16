package comp3350.Innovator2.logic;

import java.util.List;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.Item;

/**
 * #### iCart
 * Manages a list of items in the cart.
 */
public interface ICartManager {

    void addItems(int quantity, Item item) throws UIException;

    /**
     * #### remove(Item)
     * Remove item from cart.
     */
    public void remove(Item item);

    /**
     * #### count(Item)
     * Count item type in cart.
     */
    public int count(Item item);

    /**
     * #### itemTotal(Item)
     * Cost of item type in cart.
     */
    public double itemTotal(Item item);

    /**
     * #### total()
     * Total cost of cart.
     */
    public double total();

    /**
     * #### getItems()
     * List items in cart.
     */
    public List<Item> getItems();

    /**
     * #### empty()
     * Empties the cart.
     */
    public void empty();

    public void purchaseCart();
}
