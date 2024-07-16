# **__Walmarket__** **__Documentation__**

## **Source Code Documentation**

## **AndroidTest\java\comp3350\Innovator2**

### ExampleInstrumentedTest
-------



 Instrumented test, which will execute on an Android device.




    @RunWith(AndroidJUnit4.class)

## **Main**

## **Main\java\comp3350\Innovator2\data**

### QueryFormatter
-------

### QueryRunner
-------



 #### static ArrayList<Item> getItems(int, int)

 Stub database accessor

 Retrieves the pageNum multiple of num items from the Item database


    public static ArrayList<Item> getItems(int num, int pageNum)



 #### static ArrayList<Item> getItems(int, int, String)

 Stub database accessor

 Retrieves the pageNum multiple of num items from the Item database who's title matches the search string

 Not case sensitive


    public static ArrayList<Item> getItems(int num, int pageNum, String searchString)



 #### static ArrayList<Item> getItems(int, int, Category)

 Stub database accessor

 Retrieves the pageNum multiple of num items from the Item database who's category matches the search term


    public static ArrayList<Item> getItems(int num, int pageNum, Category searchCategory)



 #### static ArrayList<Item> getItems(int, int, int)

 Stub database accessor

 Retrieves the pageNum multiple of num items from the Item database who's sellerID matches the search ID


    public static ArrayList<Item> getItems(int num, int pageNum, int searchSellerID)



 #### static Seller getSeller(Item)

 Stub database accessor

 Retrieves the Seller for a specified Item object


    public static Seller getSeller(Item item)



 #### static Seller getSeller(int)

 Stub database accessor

 Retrieves the Seller for an Item object specified by ID


    public static Seller getSeller(int itemID)

### StubDatabase
-------



 For the purposes of Iteration 1, will contain all stub database data

 Also for the purposes of Iteration 1, random andor AI namedesc.image generators

 have been used to construct the data below (Note: all code itself has been written

 by the team and not generated in any way).


    public class StubDatabase



 #### initializeDatabase()

 Called during main activity initialization to create the database objects


    public static void initializeDatabase() {

## **Main\java\comp3350\Innovator2\logic**

### CardValidator
-------



 boolean validateCard(String, String)

 Validates a given credit card

 Returns the validity of the card


    private boolean validateCard(String card, String cardType) {



 boolean parseDigits(String)

 Tries to convert a string into a long

 Upon failure, catch the exception and set it to an invalid entry

 Returns the card as a long


    private long parseDigits(String entry) {



 boolean validateCardType(String, String)

 Validates a given credit card based on its card type (Visa, Mastercard)

 Returns the validity of the card based its type


    private boolean validateCardType(String card, String cardType) {

### StoreHelper
-------

### StoreSearcher
-------

### VerifyPayment
-------



 boolean isValid(String, String)

 Validates a given credit card

 Returns the validity of the card


    boolean isValid(String card, String cardType);  cardType can be either "Visa" or "Mastercard"

## **Main\java\comp3350\Innovator2\objects**

### AccountType
-------

### Category
-------

### Item
-------



 Class to store item with all properties


    public class Item {

### Order
-------



 Class to store order with all properties


    public class Order {

### OrderStatus
-------

### PaymentDetails
-------



 Helper class to store whatever information is considered relevant for payment


    public class PaymentDetails {

### Seller
-------



 Class to store seller information


    public class Seller {

### User
-------



 Class to store user information


    public class User {

## **Main\java\comp3350\Innovator2\presentation**

### BrowserHelper
-------



 #### BrowserHelper

 The constructor will load a page of default items.

 Manages which items are displayed in the item browser.


    public class BrowserHelper



 #### public void nextPage()

 Updates the page forwards.


    public void nextPage()



 #### public void prevPage()

 Updates the page backwards.


    public void prevPage()



 #### public void refresh()

 Sets page to 0 and updates search.

 WARNING: Allows empty pages.


    public void refresh()



 #### public void linkCriteriaHelper()

 Link a CriteriaHelper to BrowserHelper.


    public void linkCriteriaHelper(CriteriaHelper critHelper)



 #### public int pageNum()

 Getter for page number.


    public int pageNum()



 #### public int pageSize()

 Getter for pageSize variable.


    public int pageSize()



 #### public void setPageSize(int)

 Setter for pageSize variable.


    public void setPageSize(int newSize)



 #### private boolean getPageItems()

 Updates itemList to items on current page.

 Returns false on null return or empty list.

 Param allowEmpty allows empty lists.

 Param checkCriteria will update search details.


    private boolean getPageItems(boolean allowEmpty, boolean checkCriteria)



 #### private void updateDisplay()

 Displays a list of items in the browser.


    private void updateDisplay()

### BrowserItemRVAdapter
-------



 #### BrowserItemRVAdapter

 Displays a list of items in the browser UI.


    public class BrowserItemRVAdapter extends RecyclerView.Adapter<BrowserItemRVAdapter.MyViewHolder>



 #### onCreateViewHolder(ViewGroup, int)

 Format the rows of the recycler view.


    @NonNull



 #### public void onBindViewHolder(BrowserItemRVAdapter.MyViewHolder, int)

 Apply item values to textViews.


    @Override



 #### public int getItemCount()

 Returns the number of displayed items.


    @Override



 #### MyViewHolder

 Holds data for UI item.


    public static class MyViewHolder extends RecyclerView.ViewHolder

### CriteriaHelper
-------



 #### CriteriaHelper

 Service class for the browserHelper.

 Reads and stores search criteria.


    public class CriteriaHelper



 #### public List<Item> search()

 Sends request based on criteria.

 Param newCriteria will update search.


    public List<Item> search(boolean newCriteria)



 #### public void readSearchBar()

 Sets flag and reads from searchBar.


    private void readSearchBar()

### MainActivity
-------



 Sets pageTextView text to "Page x" where x is the next page number.

 Need to know how many items are available to limit the page numbers.

 @param view


    public void onNextPageButtonClick(View view) {



 Sets pageTextView text to "Page x" where x is the last page number.

 The button should be disabled when the page number is 1 - can't have <1 page.

 @param view


    public void onLastPageButtonClick(View view) {



 When search button is pressed read the text in the search bar.

 Forward the text to browserHelper to complete the search.

 @param view


    public void onSearch(View view) {

## **Test\java\comp3350\Innovator2**

### AllTests
-------



 Class for running all other testing classes through the JUnit Interface


    public class AllTests {

## **Test\java\comp3350\Innovator2\data**

### QueryFormatterTest
-------



 Class which implements tests on the QueryFormatter class


    public class QueryFormatterTest {



 #### QueryFormatterTest()

 TEMPLATE

 Sample test method, to be removed once actual tests are written


    public void QueryFormatterTest() { assertEquals("Always True", 1, 1); }

### QueryRunnerTest
-------



 Class which implements tests on the QueryHelper class


    public class QueryRunnerTest {

## **Test\java\comp3350\Innovator2\logic**

### TestCardValidator
-------



 Class which implements tests on the CardValidator class


    

### TestStoreSearcher
-------



 Class which implements tests on the StoreSearcher class


    public class TestStoreSearcher {

## **Test\java\comp3350\Innovator2\objects**

### ItemTest
-------



 Class to test the Item class


    public class ItemTest {

### OrderTest
-------



 Class to test the Order class


    public class OrderTest {

### PaymentDetailsTest
-------



 Class to test the Payment Details class


    public class PaymentDetailsTest {

### UserTest
-------



 Class to test the User class


    public class UserTest {

## **Test\java\comp3350\Innovator2\presentation**

### BrowserHelperTest
-------



 Class which implements tests on the BrowserHelper class


    public class BrowserHelperTest {



 #### BrowserHelperPageChangeTests()

 Ensures safety of nextPage method.


    public void BrowserHelperPageChangeTests()

