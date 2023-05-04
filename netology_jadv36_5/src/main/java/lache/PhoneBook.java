package lache;

import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    private static Map<String, String> phoneBook = new TreeMap<>();
    public static int add(String name, String number) {
        if (!phoneBook.containsKey(name)) {
            phoneBook.put(name, number);
        }
        return phoneBook.size();
    }

    public static String findByNumber(String number) {
        return null;
    }

    public static int size() {
        return phoneBook.size();
    }


}
