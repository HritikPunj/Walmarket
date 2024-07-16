package comp3350.Innovator2.logic;

import java.util.ArrayList;
import java.util.List;

import comp3350.Innovator2.application.Services;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.Item;

//Manage list of items in cart.
public class CartManager implements ICartManager {

    //================================================== Variables

    //Data
    private static List<CartItem> cartItems;

    //================================================== Creation

    public CartManager()
    {
        cartItems = new ArrayList<>();
    }

    //================================================== Interface

    public void addItems(int quantity, Item item) throws UIException {
        CartItem ci = findInCart(item);
        if (ci == null) addNode(item);                                              //If not in cart, add node.
        else if (ci.getCount() >= item.getStock()) ci.setCount(item.getStock());    //Enforce item's stock.
        else ci.setCount(ci.getCount() + 1);
    }

    public void purchaseCart() {
        for (int i = 0; i < cartItems.size(); i++) {
            Services.getItemPersistence().updateStock(cartItems.get(i).getItem().getItemID(), -cartItems.get(i).getCount());
        }
    }

    //Remove 1 item from cart.
    @Override
    public void remove(Item item)
    {
        CartItem ci = findInCart(item);
        if (ci != null && ci.getCount() > 1) ci.setCount(ci.getCount() - 1);    //If more than 1, remove 1.
        else if (ci != null) cartItems.remove(ci);                              //If only 1, remove node.
    }

    //Count item type in cart.
    @Override
    public int count(Item item)
    {
        CartItem ci = findInCart(item);
        if (ci == null) return 0;       //If no node, 0 in cart.
        else return ci.getCount();      //Return count in cart.
    }

    //Total price of item type.
    @Override
    public double itemTotal(Item item)
    {
        CartItem ci = findInCart(item);
        if (ci == null) return 0;                           //No cost if not in cart.
        else return ci.getItem().getCost() * ci.getCount(); //Calc the total cost.
    }

    //Total overall price.
    @Override
    public double total()
    {
        double total = 0;
        for (int i = 0; i < cartItems.size(); i++)
        {
            total += itemTotal(cartItems.get(i).getItem());
        }
        return total;
    }

    //Get items in cart.
    public List<Item> getItems()
    {
        List<Item> list = new ArrayList<Item>();
        for (int i = 0; i < cartItems.size(); i++)
            list.add(cartItems.get(i).getItem());
        return list;
    }

    //Empties the cart.
    public void empty()
    {
        while(cartItems.size() > 0)
            cartItems.remove(0);
    }

    //================================================== Internals

    //Check if item is in cart.
    private CartItem findInCart(Item item)
    {
        //Check item integrity.
        if (item == null) return null;

        //Loop through items in cart.
        for (int i = 0; i < cartItems.size(); i++)
        {
            if (cartItems.get(i).getItem().getItemID() == item.getItemID())
                return cartItems.get(i);
        }
        return null;
    }

    //Add node to the list.
    private void addNode(Item item)
    {
        //Check item integrity.
        if (item == null || item.getStock() <= 0) return;

        CartItem newItem = new CartItem(item, 1);
        cartItems.add(newItem);
    }

    //================================================== List Item

    //Track items and quantity.
    private class CartItem
    {
        //Variables
        private final Item item;
        private int count;

        //Constructor
        public CartItem(Item item, int count)
        {
            this.item = item;
            this.count = count;
        }

        //Getters
        public Item getItem() {return item;}
        public int getCount() {return count;}

        //Setters
        public void setCount(int count) {this.count = count;}
    }
}
