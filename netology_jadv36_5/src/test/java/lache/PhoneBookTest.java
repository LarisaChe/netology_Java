package lache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PhoneBookTest {

    @BeforeAll
    public static void testAdd_FirstRecord() {
        Assertions.assertEquals(1, PhoneBook.add("Helen", "+79256065544"));
    }
    @ParameterizedTest
    @CsvSource({
            "Jhon, +79011012233",
            "Jack, 89052024455",
            "Fil, +79153036677"
    })
    public void testAdd_SomeRecords(String name, String phone) {
        int resultNum = PhoneBook.size() + 1;
        Assertions.assertEquals(resultNum, PhoneBook.add(name, phone));
    }

    @Test
    public void testAdd_DoubleName() {
        int num = PhoneBook.add("Mary", "+79094048899");
        Assertions.assertEquals(num, PhoneBook.add("Mary", "+79094048899"));
    }

    @Test
    public void testAdd_nullName_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            int i = PhoneBook.add(null, "+7777");
        });
    }

    @Test
    public void testFindByNumber_Found() {
        String name = "Helen";
        String phone = "+79256065544";
        Assertions.assertEquals(name, PhoneBook.findByNumber(phone));
    }

    @Test
    public void testFindByNumber_NotFound() {
        String phone = "81112223344";
        String result = String.format(PhoneBook.MSG_NUMBER_NOT_FOUND, phone);//"Number 81112223344 isn't in PhoneBook";
        Assertions.assertEquals(result, PhoneBook.findByNumber(phone));
    }
    @Test
    public void testFindByNumber_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            String s = PhoneBook.findByNumber(null);
        });
    }
}
