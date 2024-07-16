package comp3350.Innovator2.data.hsql;

import android.graphics.Bitmap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import comp3350.Innovator2.data.IOrderPersistence;
import comp3350.Innovator2.data.utils.DBHelper;
import comp3350.Innovator2.logic.exceptions.DataException;
import comp3350.Innovator2.objects.Item;
import comp3350.Innovator2.objects.Order;
import comp3350.Innovator2.objects.OrderItem;
import comp3350.Innovator2.objects.utils.Category;


/**
 * Class which contains the HSQL implementation of Order Persistence
 */
public class OrderPersistenceHSQLDB implements IOrderPersistence{

    private final String dbPath;

    public OrderPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    /**
     * #### List<Order> getOrders(String username)
     * This method returns a list of all orders for a particular user
     */
    @Override
    public List<Order> getOrders(String username)
    {
        //Initialize list of orders
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM Orders WHERE Users_username = ?";

        //Make a connection to the database
        try(final Connection conn = connection()){
            //Form a statement to execute query
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set parameter to username
            pst.setString(1, username);

            final ResultSet rs = pst.executeQuery();

            //Iterate through result to form the orders
            while(rs.next())
            {
                //Form an order
                Order currOrder = formOrder(rs);

                //Add the order to the array list of orders
                orders.add(currOrder);
            }

            //Close connections
            rs.close();
            pst.close();

            return orders;
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }

    }


    /**
     * #### void addOrder(Order order, String username)
     * This method adds new order for user
     */
    @Override
    public void addOrder(Order order, String username)
    {
        //Form an array list
        List<OrderItem> currItems = new ArrayList<>();

        String sql = "INSERT INTO Orders (date, Users_username, orderTotal) VALUES (?, ?, ?)";

        try(final Connection conn = connection()){
            final PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Get date from order
            java.sql.Date sqlDate = new java.sql.Date(order.getOrderDate().getTime());

            //Set the parameters for pst
            pst.setDate(1, sqlDate);
            pst.setString(2, username);
            pst.setDouble(3, order.getOrderTotal());

            pst.executeUpdate();

            // Retrieve the auto-generated keys
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Retrieve the generated orderID
                    int orderId = generatedKeys.getInt(1);

                    //Add the respective entries to the Order_items table
                    addOrderItems(order, orderId);
                } else {
                    throw new DataException("Creating order failed, no ID obtained.");
                }
            }

            pst.close();
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }

    private void addOrderItems(Order order, int orderID)
    {
        //Insert all items in the order to the table
        for (int i = 0; i < order.getOrderItems().size(); i ++)
        {
            //Get the current item
            OrderItem curr = order.getOrderItems().get(i);

            String sql = "INSERT INTO Order_Item (Orders_orderID, Items_itemID, quantity) VALUES (?, ?, ?)";

            try (final Connection conn = connection()) {
                final PreparedStatement pst = conn.prepareStatement(sql);

                //Get date from order
                java.sql.Date sqlDate = new java.sql.Date(order.getOrderDate().getTime());

                //Set the parameters for pst
                pst.setInt(1, orderID);
                pst.setInt(2, curr.getItem().getItemID());
                pst.setInt(3, curr.getQuantity());

                pst.executeUpdate();
                pst.close();
            } catch (final SQLException e) {
                throw new DataException(e);
            }
        }
    }


    private List<OrderItem> formOrderItems(final int orderID) throws SQLException{
        //Form an array list
        List<OrderItem> currItems = new ArrayList<>();

        String sql = "SELECT * FROM Order_Item WHERE Orders_orderID = ?";

        try(final Connection conn = connection()){
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set the parameters for pst
            pst.setInt(1, orderID);

            final ResultSet rs = pst.executeQuery();

            //Form the orderItems
            while(rs.next()){
                //Get the itemID
                int itemID = rs.getInt("Items_itemID");
                //Get the quantity
                int quantity = rs.getInt("quantity");

                Item currItem = getItem(itemID);

                //Add the item to arraylist
                currItems.add(new OrderItem(currItem, quantity));
            }

            //Close connections
            rs.close();
            pst.close();

            return currItems;
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }

    private Order formOrder(final ResultSet rs) throws SQLException {
        //Form an order
        final int orderID = rs.getInt("orderID");
        final java.sql.Date date = rs.getDate("date"); //Get the date

        final double orderTotal = rs.getDouble("orderTotal");

        //Get the items in order
        List<OrderItem> items = formOrderItems(orderID);

        //Return created order
        return new Order(orderID, items, date, orderTotal);
    }

    private Item getItem(int itemID){
        Item item = null;

        String sql = "SELECT * FROM Items WHERE itemID = ?";

        //Make a connection to the database
        try(final Connection conn = connection()){
            //Form a statement to execute query
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set parameter to itemID
            pst.setInt(1, itemID);

            final ResultSet rs = pst.executeQuery();

            //Iterate through result to form the item
            while(rs.next())
            {
                //Form an item
                final int ItemID = rs.getInt("itemID");
                final int sellerID = rs.getInt("Seller_sellerID");
                final String title = rs.getString("title");
                final String description = rs.getString("description");
                final double cost = rs.getDouble("cost");
                final int stock = rs.getInt("stock");
                final Category category = Category.valueOf(rs.getString("category"));

                //Get the image and convert it to bitmap
                final byte[] image = rs.getBytes("image");
                final Bitmap img = DBHelper.convertByteArrayToBitmap(image);

                //Return created item
                item = new Item(ItemID, sellerID, title, description, cost, stock, category, img);
            }

            //Close connections
            rs.close();
            pst.close();

            return item;
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }
}
