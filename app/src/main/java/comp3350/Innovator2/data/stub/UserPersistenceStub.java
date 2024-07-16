package comp3350.Innovator2.data.stub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import comp3350.Innovator2.data.IUserPersistence;
import comp3350.Innovator2.logic.exceptions.DataException;
import comp3350.Innovator2.objects.MasterCard;
import comp3350.Innovator2.objects.User;
import comp3350.Innovator2.objects.CreditCard;


/**
 * Class which contains the stub implementation of User Persistence
 */

public class UserPersistenceStub implements IUserPersistence {
    private List<User> users;
    //Hashmap of passwords, maps usernames to passwords
    Map<String, String> userPasswords = new HashMap<>();


    public UserPersistenceStub(){
        userInit();
    }

    private void userInit(){
        //Make a bunch of payment infos
        List<CreditCard> payInfo1 = new ArrayList<>();
        payInfo1.add(new MasterCard("4536234056560001", "john", "11/24", "123"));

        List<CreditCard> payInfo2 = new ArrayList<>();
        payInfo2.add(new MasterCard("4536234056560002", "Dale", "11/25", "125"));

        User[] list = {
                new User("John", "Gayle", "JohnG", "john@email.com", payInfo1),
                new User("Steve", "Smith", "SmithS",  "smith@email.com", new ArrayList<>()),
                new User("ADMIN", "ADMIN", "ADMIN", "admin@email.com", payInfo2)
        };
        this.users = new ArrayList<>();
        this.users.addAll(Arrays.asList(list));

        //Add respective passwords to Hashmap
        userPasswords.put("JohnG", "strong#123");
        userPasswords.put("SmithS", "weak@24");
        userPasswords.put("ADMIN", "Comp3350!");
    }


    /**
     * #### void addUser(String firstName, String lastName, String username, String password, String email)
     * This method adds the user to DB
     */
    @Override
    public void addUser(String firstName, String lastName, String username, String password, String email) throws DataException {
        boolean alreadyExists = false;

        //Check if the username already exists
        for(int i = 0; i < users.size(); i ++){
            User currUser = users.get(i);

            //If the user already exists
            if(currUser.getUsername().equals(username)){
                alreadyExists = true;
            }
        }

        if(alreadyExists){
            throw new DataException("User already exists");
        }
        else{
            users.add(new User(firstName, lastName, username, email, new ArrayList<>()));
            userPasswords.put(username, password);
        }
    }


    /**
     * #### User getUser(String username)
     * This method returns the true if username exists, and false otherwise
     */
    @Override
    public boolean getUser(String username){
        boolean result = false;

        for(int i = 0; i < users.size(); i ++){
            User currUser = users.get(i);

            //If the user exists
            if(currUser.getUsername().equals(username)){
                result = true;
            }
        }

        return result;
    }


    /**
     * #### List<CreditCard> getPaymentDetails(String username)
     * This method returns the stored payment details for a user
     */
    @Override
    public List<CreditCard> getPaymentDetails(String username){
        List<CreditCard> cards = new ArrayList<>();

        for(int i = 0; i < users.size(); i ++){
            User currUser = users.get(i);

            //If the user exists
            if(currUser.getUsername().equals(username)){

                //Iterate through the payment info
                if(currUser.getPaymentInfo() != null){
                    cards = currUser.getPaymentInfo();
                }
            }
        }
        return cards;
    }

    /**
     * #### void addPaymentInfo(CreditCard details, String username)
     * This method adds paymentInfo for a user
     */
    @Override
    public void addPaymentInfo(CreditCard details, String username){

        for(int i = 0; i < users.size(); i ++){
            User currUser = users.get(i);

            //If the user exists
            if(currUser.getUsername().equals(username)){
                //Add the details
                currUser.getPaymentInfo().add(details);
            }
        }
    }

    /**
     * #### User authenticateUser(String username, String password)
     * This method authenticates if username and password match a record, then return a user object, and null otherwise
     */
    @Override
    public User authenticateUser(String username, String password){
        User user = null;

        for(int i = 0; i < users.size(); i ++){
            User currUser = users.get(i);

            //If the user exists, and we have the password stored for that user
            if(userPasswords.get(username) != null)
            {
                if (currUser.getUsername().equals(username) && userPasswords.get(username).equals(password)) {
                    user = new User(currUser.getFirstName(), currUser.getLastName(), currUser.getUsername(), currUser.getEmail(), currUser.getPaymentInfo());
                }
            }
        }

        return user;
    }

}
