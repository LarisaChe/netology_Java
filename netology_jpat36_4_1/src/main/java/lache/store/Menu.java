package lache.store;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<ItemMenu> menuList = new ArrayList<>();
    /*public Menu() {
        this.menuList = new ArrayList<>();
    }
    public Menu addMenu(int level, int belongTo, String item) {
        this.menuList.add(new ItemMenu(item, level, belongTo));
        return this;
    }*/
    public Menu getItemsFromCSV() {
        String[] columnMapping = {"levelItem", "belongToItem", "item"};
        String fileNameCSV = "menu.csv";
        this.menuList = WorkWithFiles.parseCSV(columnMapping, fileNameCSV, ItemMenu.class);
        return this;
    }

    public void printByLevel(int level) {
        this.menuList.stream().filter(x -> x.levelItem == level).forEach(System.out::println);
    }
    public void printByBelongTo(int belongTo) {
        this.menuList.stream().filter(x -> x.belongToItem == belongTo).forEach(System.out::println);
    }
    public void printFullMenu() {
        this.menuList.forEach(System.out::println);
    }

    public void printBriefMenu() {
        printByLevel(1);
    }
}
