package lache;

public class PersonBuilder {

    private String name;
    private String surname;
    private int age = -1;
    private String address;

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }
    public PersonBuilder setAge(int age) {
        if (age >= 0 && age < 110) {
            this.age = age;
        }
        else {
            throw new IllegalArgumentException("Некорректно указан возраст: " + age);
        }
        return this;
    }
    public PersonBuilder setAddress(String address) {
        if (!address.isEmpty()) {
            this.address = address;
        }
        return this;
    }

    public Person build() {
        if (name == null || surname == null) {
            throw new IllegalStateException("Имя и отчество должны быть указаны");
        }
        if (age == -1 && address == null)
            return new Person(name, surname);
        if (age >= 0 && age < 110 && address == null) {
            return new Person(name, surname, age);
        }
        else
            return new Person(name, surname, age, address);
    }

}
