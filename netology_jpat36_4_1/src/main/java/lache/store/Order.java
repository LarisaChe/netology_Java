package lache.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    protected String numOrder;
    protected OrderStatus orderStatus;
    protected List<Product> products;
    public Order(List<Product> products) throws IOException {
        this.numOrder = String.valueOf(WorkWithFiles.getNextOrderNum());
        this.orderStatus = OrderStatus.CREATED;
        this.products = new ArrayList<>();
        this.products.addAll(products);
    }

    public Order(String numOrder, OrderStatus orderStatus, List<Product> products) {
        this.numOrder = numOrder;
        this.orderStatus = orderStatus;
        this.products = products;
    }

    public String getNumOrder() {
        return numOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void nextStatus() {
        this.orderStatus.next(this.orderStatus);
    }

    /*public boolean addProduct(Product product) {
        return this.products.add(product);
    }

    public boolean removeProduct(Product product) {
        return this.products.remove(product);
    } */

    @Override
    public String toString() {
        return "Order{" +
                "numOrder='" + numOrder + '\'' +
                ", orderStatus=" + orderStatus +
                ", products=" + products +
                '}';
    }
}
