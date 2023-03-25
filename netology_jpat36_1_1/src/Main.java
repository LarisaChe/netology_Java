import lache.Person;
import lache.PersonBuilder;

public class Main {
    public static void main(String[] args) {
        Person mom = new PersonBuilder()
                .setName("Анна")
                .setSurname("Вольф")
                .setAge(31)
                .setAddress("Сидней")
                .build();
        Person son = mom.newChildBuilder()
                .setName("Антошка")
                .build();
        System.out.println("У " + mom + " есть сын, " + son);

        if (!son.hasAge())
            System.out.println("Возраст неизвестен");

        checkAddress(mom);

        Person strange = new PersonBuilder()
                .setName("Николай")
                .setSurname("Пак")
                .setAge(27)
                .build();
        System.out.println(strange);
        checkAddress(strange);
        System.out.println(strange.getAddress());
        strange.happyBirthday();
        System.out.println(strange);

        try {
            // Не хватает обязательных полей
            new PersonBuilder().build();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        try {
            // Возраст недопустимый
            new PersonBuilder().setAge(-100).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    public static void checkAddress (Person person) {
        if (person.hasAddress()) {
            System.out.println("Место жительства известно: " + person.getAddress());
        } else {
            System.out.println("Неизвестно где живет");

            System.out.println("Адрес изменен на адрес по умолчанию");
        }
    }
}