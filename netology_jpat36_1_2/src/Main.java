import lache.Filter;
import lache.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        Logger logger = Logger.getInstance();
        logger.log("Запускаем программу ");
        System.out.println("Привет!");

        Scanner scanner = new Scanner(System.in);

        logger.log("Просим пользователя ввести входные данные для списка");
        System.out.print("Введите размер списка: ");
        int sizeArr = scanner.nextInt();

        System.out.print("Введите верхнюю границу значений элементов в списке: ");
        int max_value = scanner.nextInt();

        logger.log("");
        logger.log("Создаём и наполняем список");
        ArrayList<Integer> arr = new ArrayList<>(sizeArr);

        Random random = new Random();
        for (int i = 0; i < sizeArr; i++) {
            arr.add(random.nextInt(max_value));
        }

        System.out.print("Вот случайный список: ");
        for (int i = 0; i < sizeArr; i++) {
            System.out.print(arr.get(i) + (i + 1 != sizeArr ? ", " : ""));
        }
        System.out.println();

        logger.log("Просим пользователя ввести входные данные для фильтрации");
        System.out.print("Введите число для фильтрации списка: ");
        int f = scanner.nextInt();

        Filter filter = new Filter(f);

        List<Integer> filteredArr = new ArrayList<>();
        filteredArr = filter.filterOut(arr);

        logger.log("Выводим результат на экран ");
        System.out.print("Отфильтрованный список: ");
        for (int i = 0; i < filteredArr.size(); i++) {
            System.out.print(filteredArr.get(i) + (i + 1 != sizeArr ? ", " : ""));
        }
        System.out.println();

        logger.log("Завершаем программу");
    }
}