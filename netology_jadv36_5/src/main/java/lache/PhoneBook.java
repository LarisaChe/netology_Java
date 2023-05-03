package lache;

import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    private static Map<String, String> phoneBook = new TreeMap<>();
    public static int add(String name, String phone) {
        if (!phoneBook.containsKey(name)) {
            phoneBook.put(name, phone);
        }
        return phoneBook.size();
    }
}
