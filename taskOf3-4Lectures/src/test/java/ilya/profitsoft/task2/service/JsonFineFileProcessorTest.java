package ilya.profitsoft.task2.service;

import ilya.profitsoft.task2.model.Fine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class JsonFineFileProcessorTest {
    
    private JsonFineFileProcessor fileProcessor;
    
    @BeforeEach
    void init(){
        fileProcessor = new JsonFineFileProcessor();
    }
    
    @Test
    void parseFinesFromFile_shouldReadJsonToFineObject_whenFileExists() {
        LocalDateTime localDateTime1 = LocalDateTime.parse("2010-05-05T15:39:03");
        LocalDateTime localDateTime2 = LocalDateTime.parse("2010-05-05T15:39:03");
        List<Fine> expected = List.of(new Fine(localDateTime1, "Legolas",
                "Fatherless", "SPEEDING", 340.0),
                new Fine(localDateTime2, "Aragorn", "Son of Aratorn",
                        "SPEEDING", 500.0));
        List<Fine> actual = fileProcessor
                .parseFinesFromFile("src/test/resources/task2/input1.json");
        assertEquals(expected, actual);
    }
    
    @Test
    void parseFinesFromFile_shouldThrowNullPointerException_whenInputIsNull() {
        assertThrows(NullPointerException.class, () ->
                fileProcessor.parseFinesFromFile(null));
    }
    
    @Test
    void parseFinesFromFile_shouldThrowRuntimeException_whenFileDoesNotExist() {
        assertThrows(RuntimeException.class, () ->
                fileProcessor.parseFinesFromFile("nonExistingFile"));
    }
    
    @Test
    void parseFinesFromFolder_shouldReadJsonsToFineObject_whenInputIsListOfPaths() {
        LocalDateTime localDateTime1 = LocalDateTime.parse("2010-05-05T15:39:03");
        LocalDateTime localDateTime2 = LocalDateTime.parse("2010-05-05T15:39:03");
        LocalDateTime localDateTime3 = LocalDateTime.parse("2010-05-05T15:39:03");
        LocalDateTime localDateTime4 = LocalDateTime.parse("2010-05-05T15:39:03");
        List<Fine> expected = List.of(new Fine(localDateTime1, "Legolas",
                        "Fatherless", "SPEEDING", 340.0),
                new Fine(localDateTime2, "Aragorn", "Son of Aratorn",
                        "SPEEDING", 500.0),
                new Fine(localDateTime3, "Dominik", "Torenta",
                        "DRUNK DRIVING", 2500.0),
                new Fine(localDateTime4, "Letti", "Ortis",
                        "DRUNK DRIVING", 2500242.0));
        List<String> paths = List.of("src/test/resources/task2/input1.json",
                "src/test/resources/task2/input2.json");
        List<Fine> actual = fileProcessor
                .parseFinesFromFolder(paths);
        assertEquals(expected, actual);
    }
    
    @Test
    void parseFinesFromFolder_shouldThrowNullPointerException_whenInputIsNull() {
        assertThrows(NullPointerException.class, () ->
                fileProcessor.parseFinesFromFolder(null));
    }
}
