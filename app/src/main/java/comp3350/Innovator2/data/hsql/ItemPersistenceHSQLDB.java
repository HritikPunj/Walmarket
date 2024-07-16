package comp3350.Innovator2.data.hsql;

import android.graphics.Bitmap;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import comp3350.Innovator2.data.IItemPersistence;
import comp3350.Innovator2.data.utils.DBHelper;
import comp3350.Innovator2.logic.exceptions.DataException;
import comp3350.Innovator2.objects.utils.Category;
import comp3350.Innovator2.objects.Item;
import comp3350.Innovator2.data.utils.Item_Status;


/**
 * Class which contains the HSQL implementation of Item Persistence
 */
public class ItemPersistenceHSQLDB implements IItemPersistence{

    private final String dbPath;

    /**
     * #### ItemPersistence(final String dbPath)
     * Constructor for ItemPersistence class
     * Sets the database path to access the database
     */
    public ItemPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }

    /**
     * #### Connection connection()
     * Private method to establish connection with database to execute queries
     */
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    /**
     * #### Item formItem(final ResultSet rs)
     * Private method to form Item objects form the result set obtained by running query
     */
    private Item formItem(final ResultSet rs) throws SQLException {
        //Form an item
        final int itemID = rs.getInt("itemID");
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
        return new Item(itemID, sellerID, title, description, cost, stock, category, img);
    }

    /**
     * #### List<Item> getAllItems()
     * This method returns a list of all items that are in database
     */
    @Override
    public List<Item> getAllItems(){
        final List<Item> items = new ArrayList<>();

        String sql = "SELECT * FROM Items WHERE status = ?";

        //Make a connection to the database
        try(final Connection conn = connection()){
            //Form a statement to execute query
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set parameter to ENUM ACTIVE
            pst.setString(1, Item_Status.Active.name());

            final ResultSet rs = pst.executeQuery();

            //Iterate through results and form Item objects
            while(rs.next())
            {
                final Item currItem = formItem(rs);
                items.add(currItem);
            }

            //Close connections
            rs.close();
            pst.close();

            return items;
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }


    /**
     * #### List<Item> getSelectedItems(String)
     * This method returns a list of items based on the search string
     */
    @Override
    public List<Item> getSelectedItems(String searchString){
        final List<Item> items = new ArrayList<>();

        String wildCardSearch = "%" + searchString + "%";
        String sql = "SELECT * FROM Items WHERE (LOWER(title) LIKE LOWER(?)) AND status = ?";

        try(final Connection conn = connection()){
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set the parameters for pst
            pst.setString(1, wildCardSearch);
            pst.setString(2, Item_Status.Active.name());

            final ResultSet rs = pst.executeQuery();

            while(rs.next()){
                final Item currItem = formItem(rs);
                items.add(currItem);
            }

            //Close connections
            rs.close();
            pst.close();

            return items;
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }

    /**
     * #### List<Item> getSelectedItems(Category)
     * This method returns a list of items based on the category
     */
    @Override
    public List<Item> getSelectedItems(Category searchCategory){
        List<Item> items = new ArrayList<>();
        String category = searchCategory.name();

        String sql = "SELECT * FROM Items WHERE category = ? AND status = ?";

        try(final Connection conn = connection()){
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set the parameters for pst
            pst.setString(1, category);
            pst.setString(2, Item_Status.Active.name());

            final ResultSet rs = pst.executeQuery();

            while(rs.next()){
                final Item currItem = formItem(rs);
                items.add(currItem);
            }

            //Close connections
            rs.close();
            pst.close();

            return items;
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }

    /**
     * #### Item getItem(int ItemID)
     * This method returns an item based on itemID, if there is no item with that ID, it returns null
     */
    @Override
    public Item getItem(int itemID){
        Item item = null;

        String sql = "SELECT * FROM Items WHERE itemID = ?";

        try(final Connection conn = connection()){
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set the parameters for pst
            pst.setInt(1, itemID);

            final ResultSet rs = pst.executeQuery();

            if (rs.next()){
                item = formItem(rs);
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


    /**
     * #### List<Item> getSelectedItems(String, Category)
     * This method returns a list of items based on the search string and category
     */
    @Override
    public List<Item> getSelectedItems(String searchString, Category searchCategory){
        List<Item> items = new ArrayList<>();
        String wildCardSearch = "%" + searchString + "%";
        String category = searchCategory.name();

        String sql = "SELECT * FROM Items WHERE (LOWER(title) LIKE LOWER(?)) AND category = ? AND status = ?";

        try(final Connection conn = connection()){
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set the parameters for pst
            pst.setString(1, wildCardSearch);
            pst.setString(2, category);
            pst.setString(3, Item_Status.Active.name());

            final ResultSet rs = pst.executeQuery();

            while(rs.next()){
                final Item currItem = formItem(rs);
                items.add(currItem);
            }

            //Close connections
            rs.close();
            pst.close();

            return items;
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }

    /**
     * #### void updateStock(int itemID, int amount)
     * This method updates the stock of a given item
     */
    @Override
    public void updateStock(int itemID, int amount) {
        String sql = "UPDATE Items SET stock = GREATEST(0, stock + ?) WHERE itemID = ?";

        try (final Connection conn = connection()) {
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set parameters
            pst.setInt(1, amount);
            pst.setInt(2, itemID);

            //Execute the query
            pst.executeUpdate();

            pst.close();
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }
}
