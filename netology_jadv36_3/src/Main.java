import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger count3 = new AtomicInteger(0);
    static AtomicInteger count4 = new AtomicInteger(0);
    static AtomicInteger count5 = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
            //System.out.println(texts[i]);
        }

        Thread thread1 = new Thread(() -> {
            for (String word : texts) {
                if (checkPalindrome(word)) {
                    increaseCount(word.length());
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (String word : texts) {
                if (checkOneLetter(word)) {
                    increaseCount(word.length());
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            for (String word : texts) {
                if (checkIncreaseLetters(word)) {
                    increaseCount(word.length());
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread3.join();
        thread2.join();
        thread1.join();

        System.out.printf("Красивых слов с длиной 3: %d шт \n", count3.get());
        System.out.printf("Красивых слов с длиной 4: %d шт\n", count4.get());
        System.out.printf("Красивых слов с длиной 5: %d шт\n", count5.get());

    }

    public static void increaseCount(int wordLength) {
        switch (wordLength) {
            case 3:
                count3.getAndIncrement();
                break;
            case 4:
                count4.getAndIncrement();
                break;
            case 5:
                count5.getAndIncrement();
                break;
            default:
                System.out.println("Некорректный размер слова: " + wordLength + ". Должно быть 3, 4 или 5");
        }
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean checkPalindrome(String word) {
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkOneLetter(String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(0) != word.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIncreaseLetters(String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i) > word.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}