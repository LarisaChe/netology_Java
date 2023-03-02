import javacore36.lache.User;
import javacore36.lache.exception.AccessDeniedException;
import javacore36.lache.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static javacore36.lache.Main.getUserByLoginAndPassword;
import static javacore36.lache.Main.validateUser;

public class Main {

    @ParameterizedTest
    @CsvSource({
            "Jhon, pass1",
            "Jack, pass2",
            "Fil, pass4"
    })
    public void testGetUserByLoginAndPasswordWithParams(String login, String password) throws UserNotFoundException {
        User testUser = getUserByLoginAndPassword(login, password);

//        Assertions.assertDoesNotThrow();
        System.out.println("Тест testGetUserByLoginAndPasswordWithParams завершен");
    }

    @Test
    public void testGetUserByLoginAndPassword() throws UserNotFoundException {
        User expectedUser = new User("Jhon", "jhon@gmail.com", "pass1", 24);

        //User testUser = getUserByLoginAndPassword("Jhon", "pass1");
        User testUser = getUserByLoginAndPassword(expectedUser.getLogin(), expectedUser.getPassword());

        Assertions.assertEquals(expectedUser, testUser);
        System.out.println("Тест testGetUserByLoginAndPassword завершен");
    }

    @Test
    public void testGetUserByLoginAndPassword_throwsException() {
        Assertions.assertThrows(UserNotFoundException.class,() ->{
            getUserByLoginAndPassword("Mary", "Ann");
                    });
        System.out.println("Тест testGetUserByLoginAndPassword_throwsException завершен");
    }

    @Test
    public void testValidateUser() throws AccessDeniedException, UserNotFoundException {
        User testUser = new User("Jhon", "jhon@gmail.com", "pass1", 24);
        Assertions.assertTrue(validateUser(testUser));
        System.out.println("Тест testValidateUser завершен");
    }

    @Test
    public void testValidateUser_throwsException()  {
        User testUser = new User("Jack", "jack@gmail.com", "pass2", 17);
        Assertions.assertThrows(AccessDeniedException.class,() ->{ validateUser(testUser);
        });
        System.out.println("Тест testValidateUser_throwsException завершен");
    }
}
