package lache.store;

import java.util.ArrayList;
import java.util.List;

public class ProductsList {
    public static List<Product> productsList = new ArrayList<>();

    public static void printProductsList() {
        if (productsList.size() == 0) {
            getProductsFromCSV();
        }
        productsList.stream().filter(x -> x.num > 0).forEach(System.out::println);
        /*for (int i = 1; i <= productsList.size(); i++) {
            if (productsList.get(i-1).num > 0) {
                    System.out.println(i + ") " + productsList.get(i-1));
                }
        } */
    }

    public static void printAllProductsList() {
        if (productsList.size() == 0) {
            getProductsFromCSV();
        }
        //productsList.stream().map(x -> x.num == 0 ? "Отсутствует " + x.name : x.name).forEach(System.out::println);
        productsList.stream().forEach(System.out::println);
    }
    public static Product  findProductByKey(String key) {
        if (productsList.size() == 0) {
            getProductsFromCSV();
        }
        List<Product> productFound = productsList.stream()
                .filter(x -> x.num > 0)
                .filter(x -> x.key.substring(0,3).equals(key) || x.key.substring(4).equals(key) )
                .toList();
        if (productFound.size() == 0) {
            return null;
        } else if (productFound.size() > 1) {
            //throw Exception
            System.out.println("Найдено несколько продуктов с одинаковым ключом");
        }
        return productFound.get(0);
    }
    private static void getProductsFromCSV() {
        String[] columnMapping = {"key", "name", "madeBy", "weight", "measure", "tags", "price", "num"};
        String fileNameCSV = "products.csv";
        productsList = WorkWithFiles.parseCSV(columnMapping, fileNameCSV, Product.class);
    }

    public static void findProducts(String searchString) {
        if (productsList.size() == 0) {
            getProductsFromCSV();
        }
        List<Product> productFound = productsList.stream()
                .filter(x -> x.key.indexOf(searchString, 0) != -1
                        || x.name.indexOf(searchString, 0) != -1
                        || x.tags.indexOf(searchString, 0) != -1 )
                .toList();
        if (productFound.size() == 0) {
            System.out.println("Не найден ни один продукт по поиску " + searchString);
        }
        else {
            productFound.forEach(System.out::println);
        }
    }
}
