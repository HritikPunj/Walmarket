package comp3350.Innovator2.data.hsql;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import comp3350.Innovator2.data.IUserPersistence;
import comp3350.Innovator2.logic.exceptions.DataException;
import comp3350.Innovator2.objects.utils.CardType;
import comp3350.Innovator2.objects.CreditCard;
import comp3350.Innovator2.objects.MasterCard;
import comp3350.Innovator2.objects.User;
import comp3350.Innovator2.objects.VisaCard;

/**
 * Class which contains the HSQL implementation of User Persistence
 */

public class UserPersistenceHSQLDB implements IUserPersistence
{
    private final String dbPath;

    /**
     * #### UserPersistence(final String dbPath)
     * Constructor for UserPersistence class
     * Sets the database path to access the database
     */
    public UserPersistenceHSQLDB(final String dbPath){
        this.dbPath = dbPath;
    }

    /**
     * #### Connection connection()
     * Private method to establish connection with database to execute queries
     */
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private User formUser(final ResultSet rs) throws SQLException {
        final String firstName = rs.getString("firstname");
        final String lastName = rs.getString("lastname");
        final String username = rs.getString("username");
        final String email = rs.getString("email");
        final List<CreditCard> payInfo = getPaymentDetails(username);

        return new User(firstName, lastName, username, email, payInfo);
    }

    private CreditCard formCard(final ResultSet rs) throws SQLException {
        CreditCard card;

        final String cardNumber = rs.getString("cardNumber");
        final String cardHolderName = rs.getString("holderName");
        final String expiryDate = rs.getString("expiryDate");
        final String cvv = rs.getString("CVV");
        final String cardType = rs.getString("cardType");

        if(cardType.equals(CardType.Visa.name())){
            card = new VisaCard(cardNumber, cardHolderName, expiryDate, cvv);
        }
        else {
            card = new MasterCard(cardNumber, cardHolderName, expiryDate, cvv);
        }

        return card;
    }

    /**
     * #### void addUser(String firstName, String lastName, String username, String password, String email)
     * This method adds the user to DB
     */
    public void addUser(String firstName, String lastName, String username, String password, String email) throws DataException
    {
        String sql = "INSERT INTO Users (firstname, lastname, username, password, email) VALUES (?, ?, ?, ?, ?)";

        try(final Connection conn = connection()){
            //Form a statement to execute query
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set parameters
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setString(5, email);

            pst.executeUpdate();

            pst.close();
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }

    /**
     * #### List<CreditCard> getPaymentDetails(String username)
     * This method returns the stored payment details for a user
     */
    public List<CreditCard> getPaymentDetails(String username) throws DataException{
        final List<CreditCard> payDetails = new ArrayList<>();

        //SQL to join paymentDetails table and Users table based on username
        String sql = "SELECT * FROM Users JOIN PaymentDetails ON Users.username = PaymentDetails.Users_username WHERE Users.username = ?";

        try(final Connection conn = connection()){
            //Form a statement to execute query
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set parameter to ENUM ACTIVE
            pst.setString(1, username);

            final ResultSet rs = pst.executeQuery();

            //Iterate through results and form CreditCard objects
            while(rs.next())
            {
                final CreditCard currItem = formCard(rs);
                payDetails.add(currItem);
            }

            //Close connections
            rs.close();
            pst.close();

            return payDetails;
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }

    /**
     * #### User getUser(String username)
     * This method returns the true if username exists, and false otherwise
     */
    public boolean getUser(String username) throws DataException{
        User currUser = null;

        boolean result = false;

        String sql = "SELECT * FROM Users WHERE username = ?";

        try(final Connection conn = connection()){
            //Form a statement to execute query
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set parameter to ENUM ACTIVE
            pst.setString(1, username);

            final ResultSet rs = pst.executeQuery();

            //Form user object
            if(rs.next())
            {
                currUser = formUser(rs);
            }

            //Close connections
            rs.close();
            pst.close();

            if(currUser != null)
            {
                result = true;
            }

            return result;
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }

    /**
     * #### void addPaymentInfo(CreditCard details, String username)
     * This method adds paymentInfo for a user
     */
    public void addPaymentInfo(CreditCard details, String username) throws DataException
    {
        String sql = "INSERT INTO PaymentDetails (cardNumber, CVV, expiryDate, holderName, cardType, Users_username) VALUES (?, ?, ?, ?, ?, ?)";

        try(final Connection conn = connection()){
            //Form a statement to execute query
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set parameters
            pst.setString(1, details.getCardNumber());
            pst.setString(2, details.getCvv());
            pst.setString(3, details.getExpiryDate());
            pst.setString(4, details.getCardHolderName());
            pst.setString(5, details.getType().name());
            pst.setString(6, username);

            pst.executeUpdate();

            pst.close();
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }

    /**
     * #### User authenticateUser(String username, String password)
     * This method authenticates if username and password match a record, then return a user object, and null otherwise
     */
    public User authenticateUser(String username, String password){
        User currUser = null;
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try(final Connection conn = connection()){
            //Form a statement to execute query
            final PreparedStatement pst = conn.prepareStatement(sql);

            //Set parameter to ENUM ACTIVE
            pst.setString(1, username);
            pst.setString(2, password);

            final ResultSet rs = pst.executeQuery();

            //Form user object
            if(rs.next())
            {
                currUser = formUser(rs);
            }

            //Close connections
            rs.close();
            pst.close();

            return currUser;
        }
        catch(final SQLException e)
        {
            throw new DataException(e);
        }
    }
}
