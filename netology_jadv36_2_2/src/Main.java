import java.util.*;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new TreeMap<>();
    public static int NUM_ROUTES = 1000;

    public static void main(String[] args) throws InterruptedException {

        Thread threadM = new MyThread();
        threadM.start();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < NUM_ROUTES; i++) {
            Thread thread = new Thread(() -> {
                String route = generateRoute("RLRFR", 100);
                int num = countLetterInStr(route, 'R');
                synchronized (sizeToFreq) {
                    int value = 0;
                    if (sizeToFreq.containsKey(num)) {
                        value = sizeToFreq.get(num);
                    }
                    sizeToFreq.put(num, value + 1);
                    sizeToFreq.notify();
                }
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            //System.out.println("Поток " + thread.getName() + " с id=" + thread.getId());
            thread.join();
        }

        threadM.interrupt();

        //System.out.println(sizeToFreq.toString());

        System.out.println("Статистика размеров: ");
        for (Map.Entry<Integer, Integer> item : sizeToFreq.entrySet()) {
            System.out.printf(" - %d (%d раз) \n", item.getKey(), item.getValue());
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
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

    private static class MyThread extends Thread {

        @Override
        public void run() {
            while (!isInterrupted()) {
                    int maxKey = 0;
                    int maxValue = 0;
                    synchronized (sizeToFreq) {
                        if (sizeToFreq.isEmpty()) {
                            try {
                                sizeToFreq.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        for (Map.Entry<Integer, Integer> item : sizeToFreq.entrySet()) {
                            if (maxValue < item.getValue()) {
                                maxKey = item.getKey();
                                maxValue = item.getValue();
                            }
                        }
                    }
                    System.out.printf("Текущий лидер среди частот %d (встретилось %d раз)\n", maxKey, maxValue);
                }
        }
    }
}