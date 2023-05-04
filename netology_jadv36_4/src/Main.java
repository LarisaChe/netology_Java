import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static int numTexts = 10_000;

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> countAs = new ArrayBlockingQueue<>(100);
        BlockingQueue<String> countBs = new ArrayBlockingQueue<>(100);
        BlockingQueue<String> countCs = new ArrayBlockingQueue<>(100);

        AtomicInteger maxA = new AtomicInteger(0);
        AtomicInteger maxB = new AtomicInteger(0);
        AtomicInteger maxC = new AtomicInteger(0);

        Thread threadCreateTexts = new Thread(() -> {
            for (int i = 0; i < numTexts; i++) {
                String text = generateText("abc", 100_000);
                try {
                    countAs.put(text);
                    countBs.put(text);
                    countCs.put(text);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadAs = new Thread(() -> {
            for (int i = 0; i < numTexts; i++) {
                try {
                    String str = countAs.take();
                    int n = countLetterInStr(str, 'a');
                    if (maxA.get() < n) {
                        maxA.set(n);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadBs = new Thread(() -> {
            for (int i = 0; i < numTexts; i++) {
                try {
                    String str = countBs.take();
                    int n = countLetterInStr(str, 'b');
                    if (maxB.get() < n) {
                        maxB.set(n);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadCs = new Thread(() -> {
            for (int i = 0; i < numTexts; i++) {
                try {
                    String str = countCs.take();
                    int n = countLetterInStr(str, 'c');
                    if (maxC.get() < n) {
                        maxC.set(n);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadCreateTexts.start();
        threadAs.start();
        threadBs.start();
        threadCs.start();

        threadCreateTexts.join();
        threadAs.join();
        threadBs.join();
        threadCs.join();

        /*System.out.println("countAs.size(): " + countAs.size());
        System.out.println("countCs.size(): " + countBs.size());
        System.out.println("countBs.size(): " + countCs.size());*/
        System.out.println("Максимальное количество символов a в строке: " + maxA.get());
        System.out.println("Максимальное количество символов b в строке: " + maxB.get());
        System.out.println("Максимальное количество символов c в строке: " + maxC.get());
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static int countLetterInStr(String str, char letter) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == letter) {
                result++;
            }
        }
        return result;
    }
}