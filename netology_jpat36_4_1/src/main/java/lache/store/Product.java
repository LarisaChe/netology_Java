package lache.store;

public class Product {
    String key;
    String name;
    String madeBy;
    double weight;
    String measure;
    String tags;
    double price;
    int num;

    @Override
    public String toString() {
        return key + " / " +
                 name +  " / " +
                madeBy +  " / " +
                weight +
                measure +  " / "  +
                tags +  " / "  +
                price + " / " +
                num;
    }

}
