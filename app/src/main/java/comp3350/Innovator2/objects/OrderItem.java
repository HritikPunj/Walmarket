package comp3350.Innovator2.objects;

public class OrderItem {
    private Item item;
    private int quantity;

    public OrderItem(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
