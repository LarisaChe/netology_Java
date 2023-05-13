package lache.store;

public class Product {
    String key;
    String name;
    double price;
    int num;

    @Override
    public String toString() {
        return key + " / " +
                name +  " / " +
                price + " / " +
                num;
    }

}
