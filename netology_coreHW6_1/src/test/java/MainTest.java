import javacore36.lache.Main;
import javacore36.lache.User;
import javacore36.lache.exception.AccessDeniedException;
import javacore36.lache.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static javacore36.lache.Main.getUserByLoginAndPassword;
import static javacore36.lache.Main.validateUser;

public class MainTest {

    @ParameterizedTest
    @CsvSource({
            "Jhon, pass1",
            "Jack, pass2",
            "Fil, pass4"
    })
    public void testGetUserByLoginAndPasswordWithParams(String login, String password)  {
        Assertions.assertDoesNotThrow(() -> Main.getUserByLoginAndPassword(login, password));
    }

    @Test
    public void testGetUserByLoginAndPassword() throws UserNotFoundException {
        User expectedUser = new User("Jhon", "jhon@gmail.com", "pass1", 24);

        User testUser = getUserByLoginAndPassword(expectedUser.getLogin(), expectedUser.getPassword());

        Assertions.assertEquals(expectedUser, testUser);
    }

    @Test
    public void testGetUserByLoginAndPassword_throwsException() {
        Assertions.assertThrows(UserNotFoundException.class,() ->{
            getUserByLoginAndPassword("Mary", "Ann");
                    });
    }

    @Test
    public void testValidateUser() throws AccessDeniedException, UserNotFoundException {
        User testUser = new User("Jhon", "jhon@gmail.com", "pass1", 24);
        Assertions.assertTrue(validateUser(testUser));
    }

    @Test
    public void testValidateUser_throwsException()  {
        User testUser = new User("Jack", "jack@gmail.com", "pass2", 17);
        Assertions.assertThrows(AccessDeniedException.class,() ->{ validateUser(testUser);
        });
    }
}
