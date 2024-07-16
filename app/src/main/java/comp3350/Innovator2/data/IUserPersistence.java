package comp3350.Innovator2.data;

import comp3350.Innovator2.objects.CreditCard;
import comp3350.Innovator2.objects.User;
import java.util.List;

public interface IUserPersistence
{
    void addUser(String firstName, String lastName, String username, String password, String email);

    boolean getUser(String username);

    User authenticateUser(String username, String password);

    List<CreditCard> getPaymentDetails(String username);

    void addPaymentInfo(CreditCard details, String username);
}
