package lache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PhoneBookTest {

    @ParameterizedTest
    @CsvSource({
            "1, Jhon, +79011012233",
            "2, Jack, 89052024455",
            "3, Fil, +79153036677"
    })
    public void testAddSimple(int resultNum, String name, String phone) {

        Assertions.assertEquals(resultNum, PhoneBook.add(name, phone));
    }

    @Test
    public void testAdd_DoubleName() {
        int num = PhoneBook.add("Mary", "+79094048899");
        Assertions.assertEquals(num, PhoneBook.add("Mary", "+79094048899"));
    }

    @Test
    public void testAdd_nullName_throwsException() {
        Assertions.assertThrows(Exception.class, () -> {
            int i = PhoneBook.add(null, "+7777");
        });
    }


}
