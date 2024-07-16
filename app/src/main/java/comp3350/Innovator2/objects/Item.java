package comp3350.Innovator2.objects;

import android.graphics.Bitmap;

import comp3350.Innovator2.objects.utils.Category;

/**
 * Class to store item with all properties
 */
public class Item {
    private int itemID;
    private int sellerID;
    private String title;
    private String description;
    private double cost;
    private int stock;
    private Category category;
    private Bitmap image;

    //Constructor
    public Item(int itemID, int sellerID, final String title, final String description, final double cost, final int stock, final Category category, final Bitmap image)
    {
        this.itemID = itemID;
        this.sellerID = sellerID;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.stock = stock;
        this.category = category;
        this.image = image;
    }

    public Item(int itemID, int sellerID, final String title, final String description, final double cost, final int stock, final Category category)
    {
        this.itemID = itemID;
        this.sellerID = sellerID;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.stock = stock;
        this.category = category;
        this.image = null;
    }

    public int getItemID(){
        return this.itemID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public double getCost(){
        return this.cost;
    }

    public int getStock(){
        return this.stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public void setImage(Bitmap image){
        this.image = image;
    }

    public Category getCategory(){
        return this.category;
    }

    public Bitmap getImage(){
        return this.image;
    }
}
