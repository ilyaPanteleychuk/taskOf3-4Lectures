package ilya.profitsoft.task2.service;

import ilya.profitsoft.task2.model.Fine;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;


/**
 * Class provides functionality of sorting
 * {@link Fine} by particular criteria
 *
 * @author Ilya Panteleychuk
 * */
public class FineStatisticFormatter {

    public Map<String, Double> sortFinesByAmount(List<Fine> fines){
        if(fines == null){
            throw new NullPointerException();
        }
        Map<String, Double> statisticMap = new HashMap<>();
        for (Fine fine: fines) {
            statisticMap.compute(fine.getType(), (k, v) ->
                    (v == null) ? fine.getFineAmount() : v + fine.getFineAmount());
        }
        return statisticMap.entrySet()
                .stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
