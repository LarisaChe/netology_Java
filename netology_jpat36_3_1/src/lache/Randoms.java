package lache;

import java.util.Iterator;
import java.util.Random;

public class Randoms implements Iterable<Integer> {
    protected Random random;
    int max;
    int min;

    public Randoms(int min, int max) {
        random = new Random();
        this.max = max;
        this.min = min;
        this.iterator().next();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                return random.nextInt(max - min + 1) + min;
            }
        };
    }
}
