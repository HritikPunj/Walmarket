package comp3350.Innovator2.data.stub;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import comp3350.Innovator2.application.Main;
import comp3350.Innovator2.data.IOrderPersistence;
import comp3350.Innovator2.objects.Order;
import comp3350.Innovator2.data.utils.DBHelper;
import comp3350.Innovator2.objects.Item;
import comp3350.Innovator2.objects.OrderItem;
import comp3350.Innovator2.objects.utils.Category;

/**
 * Class which contains the stub implementation of Order Persistence
 */
public class OrderPersistenceStub implements IOrderPersistence
{
    //List of orders
    private List<Order> orders;
    //Map of usernames, and respective orderID's
    Map<String, List<Integer>> userOrders = new HashMap<>();

    //Integer to keep track of number of orders placed, for the purpose of assigning orderIDs
    private int numOrders;

    public OrderPersistenceStub(){
        orderInit();
    }

    private void orderInit()
    {
        //Initialize num orders to 0
        this.numOrders = 0;

        //Items for order 1
        Item[] items_1 = {
                new Item(1, 1, "Carrot", "Freshly grown right here in Manitoba", 3.99, 12, Category.Misc, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_1")),
                new Item(3, 1, "Salmon", "Wild-caught from pristine waters, rich in omega-3", 9.99, 8, Category.Seafood, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_3"))
        };
        //Order Items for order 1
        List<OrderItem> orderItems_1 = new ArrayList<>();
        orderItems_1.add(new OrderItem(items_1[0], 2));
        orderItems_1.add(new OrderItem(items_1[1], 1));

        this.numOrders ++;

        //Items for order 2
        Item[] items_2 = {
                new Item(2, 1, "Apple", "Crisp and juicy, sourced locally", 2.49, 20, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_2")),
                new Item(4, 1, "Spinach", "Organically grown for maximum freshness", 1.99, 15, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_4")),
                new Item(5, 1, "Chicken Breast", "Free-range, boneless, and skinless", 5.99, 10, Category.Poultry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_5"))
        };
        //Order Items for order 2
        List<OrderItem> orderItems_2 = new ArrayList<>();
        orderItems_2.add(new OrderItem(items_2[0], 1));
        orderItems_2.add(new OrderItem(items_2[1], 2));
        orderItems_2.add(new OrderItem(items_2[2], 3));

        this.numOrders++;

        //Order list
        Order[] list = {
                new Order(1, orderItems_1, parseDate("2024-03-15"), 20.13),
                new Order(2, orderItems_2, parseDate("2024-02-15"), 27.37)
        };

        this.orders = new ArrayList<>();
        this.orders.addAll(Arrays.asList(list));

        //List of orders
        List<Integer> listOfOrders = new ArrayList<>();
        listOfOrders.add(1);
        listOfOrders.add(2);

        //Add orders with the respective users to the map
        userOrders.put("ADMIN", listOfOrders);
    }


    /**
     * #### List<Order> getOrders(String username)
     * This method returns a list of all orders for a particular user
     */
    @Override
    public List<Order> getOrders(String username) {
        //Initialize list of orders
        List<Order> order = new ArrayList<>();

        // Check if the user has any orders
        if (userOrders.containsKey(username)) {
            // Retrieve the list of order IDs for the user
            List<Integer> orderIds = userOrders.get(username);

            //Add the orders with those ID's to the list
            for(int i = 0; i < orderIds.size(); i ++){
                Order currOrder = orders.get(orderIds.get(i));

                order.add(currOrder); //Add that order to the order list
            }
        }
        // Return the list of orders
        return order;
    }


    /**
     * #### void addOrder(Order order, String username)
     * This method adds new order for user
     */
    @Override
    public void addOrder(Order order, String username) {
        // Increment the order ID to ensure it is unique
        numOrders++;

        // Set the order's ID to the new value
        order.setOrderID(numOrders);
        // Add the order to the list of all orders
        orders.add(order);

        // Check if the username already has orders
        if (userOrders.containsKey(username)) {
            // If yes, add the new order ID to their list of orders
            userOrders.get(username).add(order.getOrderID());
        } else {
            // If no, create a new list and add the order ID, then put it in the map
            List<Integer> newUserOrders = new ArrayList<>();
            newUserOrders.add(order.getOrderID());
            userOrders.put(username, newUserOrders);
        }
    }


    private static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
