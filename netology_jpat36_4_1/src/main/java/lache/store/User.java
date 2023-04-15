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
}
