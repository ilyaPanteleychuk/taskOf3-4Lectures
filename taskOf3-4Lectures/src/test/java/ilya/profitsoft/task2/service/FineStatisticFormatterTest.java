package ilya.profitsoft.task2.service;

import ilya.profitsoft.task2.model.Fine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FineStatisticFormatterTest {
    
    private FineStatisticFormatter fineStatisticFormatter;
    
    @BeforeEach
    void init(){
        fineStatisticFormatter = new FineStatisticFormatter();
    }
    
    @Test
    void sortFinesByAmount_shouldCalculateAmountByFineType_whenListHasFinesOfSimilarType() {
        LocalDateTime localDateTime1 = LocalDateTime.parse("2010-05-05T15:39:03");
        LocalDateTime localDateTime2 = LocalDateTime.parse("2010-05-05T15:39:03");
        List<Fine> fines = List.of(new Fine(localDateTime1, "Jon", "Doe",
                "SPEEDING", 2044),
                new Fine(localDateTime2, "Foe", "Foe",
                        "SPEEDING", 20000));
        Map<String, Double> expected = new LinkedHashMap<>();
        expected.put("SPEEDING", 22044.0);
        assertEquals(expected, fineStatisticFormatter.sortFinesByAmount(fines));
    }
    
    @Test
    void sortFinesByAmount_shouldSortElementsByAmount_whenListHasFinesOfDifferentType() {
        LocalDateTime localDateTime1 = LocalDateTime.parse("2010-05-05T15:39:03");
        LocalDateTime localDateTime2 = LocalDateTime.parse("2010-05-05T15:39:03");
        List<Fine> fines = List.of(new Fine(localDateTime1, "Jon", "Doe",
                        "SPEEDING", 2044),
                new Fine(localDateTime2, "Foe", "Foe",
                        "SPEEDING", 20000),
                new Fine(localDateTime1, "Ivan", "Ivanov",
                        "DRUNK DRIVING", 2044),
                new Fine(localDateTime2, "Neo", "Chosen",
                        "DRUNK DRIVING", 2000),
                new Fine(localDateTime1, "Trinity", "Fatherless",
                "DRIVING WITHOUT SEATBELT", 100),
                new Fine(localDateTime2, "Morfius", "Brutal",
                        "DRIVING WITHOUT SEATBELT", 150));
        Map<String, Double> actualMap = fineStatisticFormatter.sortFinesByAmount(fines);
        List<Double> expectedValues = List.of(22044.0, 4044.0, 250.0);
        List<Double> actualValues = new ArrayList<>(actualMap.values());
        Double[] valuesArray = actualValues.toArray(new Double[0]);
        for (int i = 0; i < valuesArray.length; i++) {
            assertEquals(expectedValues.get(i), valuesArray[i]);
        }
    }
    
    @Test
    void sortFinesByAmount_shouldThrowNullPointerException_whenInputIsNull() {
        assertThrows(NullPointerException.class, () ->
                fineStatisticFormatter.sortFinesByAmount(null));
    }
}
