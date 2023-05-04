package lache;

import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
    final public static String MSG_NUMBER_NOT_FOUND = "Number %s isn't in PhoneBook";
    final public static String MSG_NAME_NOT_FOUND = "Name %s isn't in PhoneBook";
    private static Map<String, String> phoneBookNames = new TreeMap<>();
    private static Map<String, String> phoneBookNumbers = new TreeMap<>();
    public static int add(String name, String number) {
        if (!phoneBookNames.containsKey(name)
            && !phoneBookNumbers.containsKey(number) ) {
            phoneBookNames.put(name, number);
            phoneBookNumbers.put(number, name);
        }
        return phoneBookNames.size();
    }

    public static String findByNumber(String number) {
        String result =  phoneBookNumbers.get(number);
        if (result == null) {
            result = String.format(PhoneBook.MSG_NUMBER_NOT_FOUND, number);
        }
        return result;
    }

    public static int size() {
        return phoneBookNames.size();
    }

    public static String findByName(String name) {
        return null;
    }

}
