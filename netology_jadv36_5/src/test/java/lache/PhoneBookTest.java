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
        PhoneBook.add("Jhon", "+79011012233");
        Assertions.assertEquals(1, PhoneBook.add("Jhon", "+79011012233"));
    }

    @Test
    public void testAdd_nullName_throwsException() {
        Assertions.assertThrows(Exception.class, () -> {
            int i = PhoneBook.add(null, "+7777");
        });
    }


}
