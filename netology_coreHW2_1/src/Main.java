import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> intListResult = new ArrayList<Integer>();

        for(int i = 0; i < intList.size(); i++) {
            if (intList.get(i) > 0) {
                if (intList.get(i)% 2 == 0) {
                    intListResult.add(intList.get(i));
                }
            }
        }
        Comparator<Integer> comparator = Comparator.naturalOrder();
        intListResult.sort(comparator);

        System.out.println(intListResult);
        
    }
}
