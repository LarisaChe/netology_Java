package lache;

import java.util.Objects;
import java.util.OptionalInt;

public class Person {
    private final String name;
    private final String surname;
    private OptionalInt age;
    private String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        if (age >= 0 && age < 110) {
            this.age = OptionalInt.of(age);
        }
    }

    public Person(String name, String surname, int age, String address) {
        this.name = name;
        this.surname = surname;
        if (age >= 0 && age < 110) {
            this.age = OptionalInt.of(age);
        }
        this.address = address;
    }

    public boolean hasAge() {
        return (age != null && age.getAsInt() != -1);
    }

    public boolean hasAddress() {
        // return !address.isEmpty();
        boolean result = !(address == null);
        if (!result) {
            this.setAddress("Аделаида");
        }
        return result;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        if (this.age != null) {
            this.age = OptionalInt.of(this.age.getAsInt() + 1);
        }
        else {
            throw new IllegalStateException("Возраст неизвестен, поэтому не может быть изменен");
        }
    }

    public PersonBuilder newChildBuilder() {
        return new PersonBuilder()
                .setSurname(this.getSurname())
                .setAddress(this.getAddress());
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, address);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ((age != null) ? (", age=" + age) : "") +
                (address != null ? (", address='" + address + '\'') : "") +
                '}';
    }
}
