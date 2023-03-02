package javacore36.lache;

import java.util.Objects;

public class User {
    protected String login;
    protected String password;
    protected String email;
    protected int age;

    public User (String login, String email, String password, int age) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && login.equals(user.login) && password.equals(user.password) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email, age);
    }
}
