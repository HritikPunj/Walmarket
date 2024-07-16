package comp3350.Innovator2.data.stub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comp3350.Innovator2.application.Main;
import comp3350.Innovator2.data.IItemPersistence;
import comp3350.Innovator2.data.utils.DBHelper;
import comp3350.Innovator2.objects.utils.Category;
import comp3350.Innovator2.objects.Item;


/**
 * Class which contains the stub implementation of Item Persistence
 */
public class ItemPersistenceStub implements IItemPersistence{
    private List<Item> items;

    public ItemPersistenceStub(){
        //Initialize the items list
        itemInit();
    }

    private void itemInit() {
        Item[] list = {
                new Item(1, 1, "Carrot", "Freshly grown right here in Manitoba", 3.99, 12, Category.Misc, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_1")),
                new Item(2, 1, "Apple", "Crisp and juicy, sourced locally", 2.49, 20, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_2")),
                new Item(3, 1, "Salmon", "Wild-caught from pristine waters, rich in omega-3", 9.99, 8, Category.Seafood, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_3")),
                new Item(4, 1, "Spinach", "Organically grown for maximum freshness", 1.99, 15, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_4")),
                new Item(5, 1, "Chicken Breast", "Free-range, boneless, and skinless", 5.99, 10, Category.Poultry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_5")),
                new Item(6, 1, "Blueberries", "Packed with antioxidants, freshly harvested", 5.49, 15, Category.Berry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_6")),
                new Item(7, 1, "Avocado", "Creamy and nutritious, ripe and ready to eat", 1.99, 10, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_7")),
                new Item(8, 1, "Tomato", "Vine-ripened and bursting with flavor", 2.29, 18, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_8")),
                new Item(9, 1, "Shrimp", "Delicious and succulent, wild-caught", 12.99, 7, Category.Seafood, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_9")),
                new Item(10, 1, "Broccoli", "Nutrient-rich and perfect for a healthy diet", 1.79, 20, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_10")),
                new Item(11, 1, "Mango", "Exotic and sweet, sourced from tropical regions", 3.99, 14, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_11")),
                new Item(12, 1, "Salad Mix", "A blend of fresh greens for a crisp salad", 4.49, 16, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_12")),
                new Item(13, 1, "Ground Beef", "High-quality, lean beef for your favorite recipes", 7.99, 10, Category.Meat, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_13")),
                new Item(14, 1, "Strawberries", "Juicy and red, a classic summer treat", 4.99, 12, Category.Berry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_14")),
                new Item(15, 1, "Eggplant", "Versatile vegetable for roasting or grilling", 2.79, 14, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_15")),
                new Item(16, 1, "Peaches", "Sweet and juicy peaches for a delightful snack", 3.49, 18, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_16")),
                new Item(17, 1, "Pork Chops", "Tender and flavorful, perfect for grilling", 6.49, 8, Category.Meat, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_17")),
                new Item(18, 1, "Cucumber", "Crisp and refreshing, great for salads", 1.29, 22, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_18")),
                new Item(19, 1, "Raspberries", "Tiny bursts of sweetness, rich in antioxidants", 6.99, 10, Category.Berry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_19")),
                new Item(20, 1, "Ground Turkey", "Lean and versatile protein for various dishes", 5.99, 12, Category.Poultry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_20")),
                new Item(21, 1, "Butternut Squash", "Sweet and nutty, great for soups and roasting", 2.99, 10, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_21")),
                new Item(22, 1, "Pineapple", "Tropical delight, ripe and ready to enjoy", 3.79, 15, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_22")),
                new Item(23, 1, "Lamb Chops", "Delicious and tender cuts for a gourmet meal", 9.49, 8, Category.Meat, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_23")),
                new Item(24, 1, "Blackberries", "Rich in antioxidants, perfect for snacking", 5.29, 14, Category.Berry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_24")),
                new Item(25, 1, "Zucchini", "Versatile vegetable for grilling, sautéing, or baking", 1.89, 16, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_25")),
                new Item(26, 1, "Watermelon", "Refreshing and hydrating, a summer staple", 4.99, 10, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_26")),
                new Item(27, 1, "Turkey Breast", "Lean and protein-packed slices for sandwiches", 8.99, 12, Category.Poultry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_27")),
                new Item(28, 1, "Bell Peppers", "Colorful and crisp, great for salads and stir-fries", 2.49, 20, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_28")),
                new Item(29, 1, "Cantaloupe", "Sweet and fragrant melon, perfect for breakfast", 3.99, 12, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_29")),
                new Item(30, 1, "Salami", "Savory cured meat, ideal for charcuterie boards", 6.79, 10, Category.Misc, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_30")),
                new Item(31, 1, "Asparagus", "Nutrient-rich spears, great for grilling or roasting", 2.19, 18, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_31")),
                new Item(32, 1, "Grapes", "Sweet and seedless, a perfect snack any time", 3.49, 14, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_32")),
                new Item(33, 1, "Bacon", "Smoky and delicious, a classic breakfast favorite", 5.99, 10, Category.Misc, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_33")),
                new Item(34, 1, "Cherries", "Juicy and vibrant, a delightful summer treat", 4.79, 16, Category.Berry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_34")),
                new Item(35, 1, "Cauliflower", "Versatile vegetable for low-carb recipes", 2.99, 12, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_35")),
                new Item(36, 1, "Oranges", "Citrusy and refreshing, packed with vitamin C", 1.99, 20, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_36")),
                new Item(37, 1, "Pork Ribs", "Tender and flavorful ribs for barbecue enthusiasts", 10.99, 8, Category.Meat, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_37")),
                new Item(38, 1, "Kiwi", "Exotic and tangy, a unique addition to fruit salads", 1.79, 15, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_38")),
                new Item(39, 1, "Cabbage", "Crisp and versatile, perfect for coleslaw or stir-fries", 1.49, 16, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_39")),
                new Item(40, 1, "Quinoa", "Nutritious and gluten-free grain, a healthy pantry staple", 4.29, 10, Category.Misc, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_40")),
                new Item(41, 1, "Pears", "Sweet and juicy, a classic fruit for snacks", 2.69, 18, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_41")),
                new Item(42, 1, "Ground Chicken", "Lean and versatile, perfect for various recipes", 5.49, 12, Category.Poultry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_42")),
                new Item(43, 1, "Artichokes", "Tender hearts, great for dips and appetizers", 3.99, 10, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_43")),
                new Item(44, 1, "Pomegranates", "Seeds packed with antioxidants, a healthy snack", 4.89, 14, Category.Berry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_44")),
                new Item(45, 1, "Ground Pork", "Flavorful and versatile, ideal for meatballs and burgers", 6.29, 10, Category.Meat, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_45")),
                new Item(46, 1, "Mushrooms", "Earthy and savory, perfect for sautéing or grilling", 2.79, 15, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_46")),
                new Item(47, 1, "Nectarines", "Sweet and aromatic, a delightful summer fruit", 3.29, 16, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_47")),
                new Item(48, 1, "Lettuce", "Crisp and fresh, essential for salads and wraps", 1.39, 20, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_48")),
                new Item(49, 1, "Beef Jerky", "Portable and protein-packed, a savory snack", 7.49, 12, Category.Misc, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_49")),
                new Item(50, 1, "Cranberries", "Tart and tangy, perfect for sauces and baking", 4.09, 14, Category.Berry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_50")),
                new Item(51, 1, "Sweet Potatoes", "Nutrient-rich and versatile, great for fries or mashes", 1.99, 18, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_51")),
                new Item(52, 1, "Grapefruit", "Citrusy and slightly tart, a refreshing breakfast option", 2.29, 14, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_52")),
                new Item(53, 1, "Lobster Tails", "Indulgent and succulent, perfect for special occasions", 19.99, 6, Category.Seafood, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_53")),
                new Item(54, 1, "Cucamelon", "Tiny cucumber-like fruits, a unique and refreshing snack", 3.79, 20, Category.Misc, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_54")),
                new Item(55, 1, "Green Beans", "Crunchy and vibrant, a classic side dish option", 1.89, 15, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_55")),
                new Item(56, 1, "Apricots", "Sweet and velvety, a delicious stone fruit", 3.49, 16, Category.Fruit, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_56")),
                new Item(57, 1, "Duck Breast", "Rich and flavorful, ideal for gourmet dishes", 12.99, 8, Category.Poultry, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_57")),
                new Item(58, 1, "Cauliflower Rice", "Low-carb alternative, great for healthy recipes", 2.49, 14, Category.Vegetable, DBHelper.getBitmap(Main.getResources(), Main.getContext(), "id_58"))
        };

        this.items = new ArrayList<>();
        this.items.addAll(Arrays.asList(list));
    }


    /**
     * #### List<Item> getAllItems()
     * This method returns a list of all items that are in database
     */
    @Override
    public List<Item> getAllItems(){
        return items;
    }


    /**
     * #### List<Item> getSelectedItems(String)
     * This method returns a list of items based on the search string
     */
    @Override
    public List<Item> getSelectedItems(String searchString){
        List<Item> selectedItems = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            //Current item
            Item currItem = items.get(i);

            //If it matches the title
            if ((currItem.getTitle().equalsIgnoreCase(searchString)) || (currItem.getTitle().toLowerCase().contains(searchString.toLowerCase()))) {
                selectedItems.add(currItem);
            }
        }
        return selectedItems;
    }


    /**
     * #### List<Item> getSelectedItems(Category)
     * This method returns a list of items based on the category
     */
    @Override
    public List<Item> getSelectedItems(Category searchCategory){
        List<Item> selectedItems = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            //Current item
            Item currItem = items.get(i);

            //If it matches the category
            if (currItem.getCategory() == searchCategory) {
                selectedItems.add(currItem);
            }
        }
        return selectedItems;
    }

    /**
     * #### List<Item> getSelectedItems(String, Category)
     * This method returns a list of items based on the search string and category
     */
    @Override
    public List<Item> getSelectedItems(String searchString, Category searchCategory){
        List<Item> selectedItems = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            //Current item
            Item currItem = items.get(i);

            //If it matches the category and title
            if (((currItem.getCategory() == searchCategory) || (currItem.getTitle().toLowerCase().contains(searchString.toLowerCase()))) && (currItem.getTitle().equalsIgnoreCase(searchString))) {
                selectedItems.add(currItem);
            }
        }
        return selectedItems;
    }


    /**
     * #### void updateStock(int itemID, int amount)
     * This method updates the stock of a given item
     */
    @Override
    public void updateStock(int itemID, int amount){

        for(int i = 0; i < items.size(); i ++){
            Item currItem = items.get(i);

            //If it is the item are looking for
            if(currItem.getItemID() == itemID){
                //Update the stock
                currItem.setStock(currItem.getStock()+amount);
            }
        }
    }

    /**
     * #### Item getItem(int itemID)
     * This method returns an item with a given ID, and null if item with that ID do not exist
     */
    @Override
    public Item getItem(int itemID){
        Item item = null;

        for (int i = 0; i < items.size(); i++) {
            //Current item
            Item currItem = items.get(i);

            //If it matches the title
            if (currItem.getItemID() == itemID)
            {
                item = currItem;
            }
        }
        return item;
    }
}

