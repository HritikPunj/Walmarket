package comp3350.Innovator2.objects;

import java.util.List;

import comp3350.Innovator2.objects.utils.AccountType;

/**
 * Class to store user information
 */
public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String email; //May turn into a struct/class later if we want to handle actual emails
    private List<CreditCard> paymentInfo;

    public User(String firstName, String lastName, String username, String email, List<CreditCard> payInfo){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.paymentInfo = payInfo;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public AccountType getAccountType() {
        return AccountType.User;
    }

    public List<CreditCard> getPaymentInfo() {
        return paymentInfo;
    }
}
