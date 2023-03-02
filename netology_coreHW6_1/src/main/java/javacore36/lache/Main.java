package javacore36.lache;

import javacore36.lache.exception.AccessDeniedException;
import javacore36.lache.exception.UserNotFoundException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws UserNotFoundException, AccessDeniedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите логин "); //login
        String login = scanner.nextLine();
        System.out.println("Введите пароль"); // pass
        String password = scanner.nextLine();

        //Проверить логин и пароль
        User user = getUserByLoginAndPassword(login, password);

        //Вызвать методы валидации пользователя
        if (validateUser(user)) {
            System.out.println("Доступ предоставлен");
        }
    }

    public static User[] getUsers() {
        User user1 = new User("Jhon", "jhon@gmail.com", "pass1", 24);
        User user2 = new User("Jack", "jack@gmail.com", "pass2", 17);
        User user3 = new User("Tim", "tim@gmail.com", "pass3", 20);
        User user4 = new User("Fil", "fil@gmail.com", "pass4", 85);
        return new User[]{user1, user2, user3, user4};
    }

    public static User getUserByLoginAndPassword(String login, String password) throws UserNotFoundException {
        User[] users = getUsers();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException("User not found");
    }

    public static boolean validateUser(User user) throws AccessDeniedException {
        if (user.getAge() >= 18) {
            return true;
        }
        throw new AccessDeniedException("Access denied because of age");
    }
}