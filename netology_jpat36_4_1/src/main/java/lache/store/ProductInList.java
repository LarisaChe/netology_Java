package lache.store;

public class ProductInList extends Product {
    String madeBy;
    double weight;
    String measure;
    String tags;

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
