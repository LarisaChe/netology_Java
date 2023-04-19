import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String[] texts = new String[25];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }

        long startTs = System.currentTimeMillis(); // start time

        final ExecutorService threadPool = Executors.newFixedThreadPool(5);
        List<Future<Integer>> tasks = new ArrayList<>();

        for (String text : texts) {
            Callable<Integer> myCallable = new MyCallable(text);
            final Future<Integer> task = threadPool.submit(myCallable);
            tasks.add(task);
        }

        int resulMaxString = 0;
        for (Future<Integer> task : tasks) {
            final Integer resultOfTask = task.get();
            resulMaxString = resulMaxString < resultOfTask ? resultOfTask : resulMaxString;
        }

        System.out.println("Максимальная длина строки: " + resulMaxString);

        threadPool.shutdown();

        long endTs = System.currentTimeMillis(); // end time

        System.out.println("Time: " + (endTs - startTs) + "ms");
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    private static class MyCallable implements Callable<Integer> {
        public String text;

        public MyCallable(String text) {
            this.text = text;
        }

        @Override
        public Integer call() throws Exception {
            int maxSize = 0;
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < text.length(); j++) {
                    if (i >= j) {
                        continue;
                    }
                    boolean bFound = false;
                    for (int k = i; k < j; k++) {
                        if (text.charAt(k) == 'b') {
                            bFound = true;
                            break;
                        }
                    }
                    if (!bFound && maxSize < j - i) {
                        maxSize = j - i;
                    }
                }
            }
            System.out.println(text.substring(0, 100) + " -> " + maxSize);
            return maxSize;
        }
    }
}

