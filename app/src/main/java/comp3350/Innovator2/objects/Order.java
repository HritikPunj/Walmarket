package comp3350.Innovator2.objects;

import java.util.Date;
import java.util.List;

/**
 * Class to store order with all properties
 */
public class Order {
    private int orderID;
    private List<OrderItem> items;

    private Date orderDate;

    private double orderTotal;

    //Constructor
    public Order(int orderID, List <OrderItem> items, Date orderDate, double orderTotal){
        this.orderID = orderID;
        this.items = items;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }

    public Order(List <OrderItem> items, Date orderDate, double orderTotal){
        this.items = items;
        this.orderTotal = orderTotal;
        this.orderDate = orderDate;
    }

    public int getOrderID(){
        return this.orderID;
    }

    public List<OrderItem> getOrderItems(){
        return this.items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderID(int id){
        this.orderID = id;
    }
}
