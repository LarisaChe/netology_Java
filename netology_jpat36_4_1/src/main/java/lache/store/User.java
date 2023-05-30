package lache.store;

import java.util.ArrayList;
import java.util.List;

public class User {
    protected String userName;
    protected List<Order> orders;
    protected List<Product> basket;

    public User(String userName) {
        this.userName = userName;
        this.orders = new ArrayList<>(); // заполнить из json
        this.basket = new ArrayList<>(); // заполнить из json
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", orders=" + orders +
                ", basket=" + basket +
                '}';
    }

    public Order findOrder(String numOrder) {
        for (Order order : orders) {
            if (order.numOrder.equals(numOrder)) {
                return order;
            }
        }
        return null;
    }

    public OrderStatus getOrderStatus(String numOrder) {
        Order order = findOrder(numOrder);
        if (order != null) {
            return order.orderStatus;
        }
        else {
            return null;
        }
    }

    public boolean OrderCancel(String numOrder) {
        Order order = findOrder(numOrder);
        if (order != null) {
            if (order.orderStatus.cancel(order.orderStatus) == OrderStatus.CANCELLED) {
                return true;
            }
        }
        return false;
    }
}
