package lache.store;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CommunicationWithUser {
    public static Menu MENU = new Menu().getItemsFromCSV();
    final public static int POSITION_NUM_ITEM = 0;
    final public static int POSITION_NUM_SUBITEM = 1;

    final public static String SEPARATOR_SPACE = " ";
    final public static String SEPARATOR_COLON = ":";

    public static User greeting(String userName) throws IOException {
        System.out.print("Здравствуйте");
        if (userName != null) {
            System.out.print(", " + userName);
            WorkWithFiles.checkUsersFromFile(userName);
            User user = new User(userName);
            //TODO: чтение из json данных о заказах
            return user;
        } else {
            System.out.print(". Представьтесь, пожалуйста");
        }
        System.out.println("!");
        return null;
    }

    public static void farewell(String userName) {
        System.out.print("До скорой встречи");
        if (userName != null) {
            System.out.print(", " + userName);
        }
        System.out.println("!");
    }

    public static boolean selectMenu(String s, int prevMenuItem, User user) throws IOException {
        if (s.length() > 2) {
            commands(s, prevMenuItem, user);
            return true;
        } else {
            switch (s) {
                case "0":
                    return false;
                case "1":
                    ProductsList.printProductsList();
                    MENU.printByBelongTo(Integer.valueOf(s));
                    return true;
                case "10":
                    ProductsList.printAllProductsList();
                    MENU.printByBelongTo(Integer.valueOf(s));
                    return true;
                case "2":
                    user.basket.forEach(System.out::println);
                    MENU.printByBelongTo(Integer.valueOf(s));
                    return true;
                case "20":
                    user.basket.clear();
                    return true;
                case "25":
                    createOrder(user);
                    return true;
                case "3":
                    user.orders.stream()
                            .filter(x -> !x.orderStatus.equals(OrderStatus.CANCELLED) && !x.orderStatus.equals(OrderStatus.COMPLETED))
                            .map(x -> x.numOrder + ": " + x.orderStatus.getDescription() + ".")
                            .forEach(System.out::println);
                    MENU.printByBelongTo(Integer.valueOf(s));
                    return true;
                case "4":
                    user.orders.stream().map(x -> x.numOrder + ": " + x.orderStatus.getDescription() + ".")
                            .forEach(System.out::println);
                    MENU.printByBelongTo(Integer.valueOf(s));
                    return true;
                case "5":
                    MENU.printFullMenu();
                    return true;
                case "55":
                    MENU.printBriefMenu();
                    return true;
                case "51":
                case "52":
                case "53":
                case "54":
                    MENU.printByBelongTo(Integer.valueOf(s.substring(POSITION_NUM_SUBITEM)));
                    return true;
                default:
                    return true;
            }
        }
    }

    private static void createOrder(User user) throws IOException {
        Order order = new Order(user.basket);
        user.orders.add(order);
        user.basket.clear();
    }

    private static void commands(String s, int prevMenuItem, User user) {
        String[] ss = s.split(SEPARATOR_COLON);
        if (ss.length <= 1) {
            ss = s.split(SEPARATOR_SPACE);
        }
        int command = Integer.valueOf(ss[0]);
        String param1 = ss.length >= 2 ? ss[1] : null;
        int param2 = ss.length >= 3 ? Integer.valueOf(ss[2]) : 0;

        switch (command) {
            case 11:
                ProductsList.findProducts(param1);
                break;
            case 12:
                addProductToBasket(param1, param2, user.basket);
                user.basket.forEach(System.out::println);
                break;
            case 21:
                decreaseProductInBasket(param1, param2, user.basket);
                user.basket.forEach(System.out::println);
                break;
            case 22:
                increaseProductInBasket(param1, param2, user.basket);
                user.basket.forEach(System.out::println);
                break;
            case 23:
                deleteProductFromBasket(param1, user.basket);
                user.basket.forEach(System.out::println);
                break;
            case 3:
                System.out.println("Заказ " + param1 + user.getOrderStatus(param1).getDescription() + ".");
                break;
            case 30:
                System.out.println("Отменить заказ " + param1 +"? (Y/N или Да/Нет)");
                Scanner scanner = new Scanner(System.in);
                String resp = scanner.nextLine();
                if (resp.trim().toUpperCase().equals("Y") ||
                        resp.trim().toUpperCase().equals("ДА")) {
                    if (user.OrderCancel(param1)) {
                        System.out.println("Заказ " + param1 + " отменен.");
                    }
                    /*else {
                        System.out.println("Заказ " + param1 + " отменен.");
                    }*/
                }
                break;
            case 4:
                MENU.printByBelongTo(Integer.valueOf(s));

            default:

        }
    }

    private static void increaseProductInBasket(String productKey, int num, List<Product> basket) {
        Product product = basket.stream().filter(x -> x.key.contains(productKey)).findFirst().get();
        if (product != null) {
            basket.remove(product);
            addProductToBasket(productKey, num, basket);
        } else {
            System.out.println("Продукт с ключом " + productKey + " не найден");
        }
    }

    private static void decreaseProductInBasket(String productKey, int num, List<Product> basket) {
        Product product = basket.stream().filter(x -> x.key.contains(productKey)).findFirst().get();
        if (product != null) {
            basket.remove(product);
            if (product.num > num) {
                product.num = product.num - num;
                }
            basket.add(product);
        } else {
            System.out.println("Продукт с ключом " + productKey + " не найден");
        }
    }

    private static void deleteProductFromBasket(String productKey, List<Product> basket) {
        Optional<Product> product = basket.stream().filter(x -> x.key.contains(productKey)).findFirst();
        if (product != null) {
            basket.remove(product);
        } else {
            System.out.println("Продукт с ключом " + productKey + " не найден");
        }
    }

    private static void addProductToBasket(String productKey, int num, List<Product> basket) {
        Product product = ProductsList.findProductByKey(productKey);
        if (product != null) {
            if (product.num < num) {
                System.out.println("В наличии нет требуемого количества. В корзину будет добавлено только " + product.num);
                num = product.num;
            }
            product.num = num;
            basket.add(product);
        } else {
            System.out.println("Продукт с ключом " + productKey + " не найден");
        }
    }
}
