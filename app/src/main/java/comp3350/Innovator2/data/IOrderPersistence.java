package comp3350.Innovator2.data;

import java.util.List;

import comp3350.Innovator2.objects.Order;

public interface IOrderPersistence {
    List<Order> getOrders(String username);

    void addOrder(Order order, String username);
}
