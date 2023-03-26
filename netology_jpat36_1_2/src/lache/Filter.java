package lache;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Filter {

    private int filterNum;

    public Filter(int filterNum) {
        this.filterNum = filterNum;
    }

    /*public List<Integer> filterOut(List<Integer> list) {
        return list.stream().filter(x -> x < filterNum).collect(Collectors.toList());
    }*/
    public List<Integer> filterOut(List<Integer> list) throws ParseException {
        Logger logger = Logger.getInstance();
        logger.log("Запускаем фильтрацию");
        List<Integer> listF = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < filterNum) {
                listF.add(list.get(i));
                logger.log(format("Элемент %d проходит", list.get(i)));
            }
            else {
                logger.log(format("Элемент %d не проходит", list.get(i)));
            }
        }
        logger.log(format("Прошло в фильтр %d элемент из %d", listF.size(), list.size()));
        return listF;
    }
}
