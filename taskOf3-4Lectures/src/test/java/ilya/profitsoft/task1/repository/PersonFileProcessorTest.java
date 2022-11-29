package ilya.profitsoft.task1.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PersonFileProcessorTest {
    
    private PersonFileProcessor fileProcessor;
    
    @BeforeEach
    void init(){
        fileProcessor = new PersonFileProcessor();
    }
    
    @Test
    void readFile_shouldReturnRightFormattedList_whenAttributesAreInOneLine() {
        List<String> actual = fileProcessor
                .readFile("inOneLineAttributesTest.xml");
        List<String> expected = new ArrayList<>();
        expected.add("<persons>\n" +
                "    <person name=\"Іван\" surname=\"Котляревський\" birthDate=\"09.09.1769\" />");
        expected.add("\n" + "    <person surname=\"Шевченко\" name=\"Тарас\" birthDate=\"09.03.1814\" />");
        expected.add("\n" + "</persons>");
        assertEquals(expected, actual);
    }
    
    @Test
    void readFile_shouldReturnRightFormattedList_whenAttributesAreInSeveralLines() {
        List<String> actual = fileProcessor
                .readFile("inSeveralLinesAttributesTest.xml");
        List<String> expected = new ArrayList<>();
        expected.add("<persons>\n" +
                "    <person\n" +
                "            birthData=\"27.08.1856\"\n" +
                "            name = \"Іван\"\n" +
                "            surname = \"Франко\" />");
        expected.add("\n" + "    <person name=\"Леся\"\n" +
                "            surname=\"Українка\"\n" +
                "            birthData=\"13.02.1871\" />");
        expected.add("\n" + "</persons>");
        assertEquals(expected, actual);
    }
    
    @Test
    void readFile_shouldReturnRightFormattedList_whenEmptyLinesExist() {
        List<String> actual = fileProcessor
                .readFile("hasEmptyLinesTest.xml");
        List<String> expected = new ArrayList<>();
        expected.add("<persons>\n" +
                "    <person\n" +
                "            birthData=\"27.08.1856\"\n" +
                "            name = \"Іван\"\n" +
                "            surname = \"Франко\" />");
        expected.add("\n" + "    <person name=\"Леся\"\n" +
                "            surname=\"Українка\"\n" +
                "\n" +
                "\n" +
                "            birthData=\"13.02.1871\" />");
        expected.add("\n" + "</persons>");
        assertEquals(expected, actual);
    }
    
    @Test
    void readFile_shouldReturnRightFormattedList_whenElementsAreInSingleLine() {
        List<String> actual = fileProcessor
                .readFile("elementsAreInSingleLine.xml");
        List<String> expected = new ArrayList<>();
        expected.add("<persons><person name=\"Іван\" surname=\"Котляревський\" birthDate=\"09.09.1769\" />");
        expected.add("<person surname=\"Шевченко\" name=\"Тарас\" birthDate=\"09.03.1814\" />");
        expected.add("</persons>");
        assertEquals(expected, actual);
    }
    
    @Test
    void readFile_shouldThrowNullPointerException_whenInputIsNull() {
        String expectedMessage = "Collection can not be null";
        assertThrows(NullPointerException.class, () ->
                fileProcessor.readFile(null), expectedMessage);
    }
    
    @Test
    void readFile_shouldThrowNullPointerException_whenFileDoesNotExist() {
        assertThrows(NullPointerException.class, () ->
                fileProcessor.readFile("nonExistingFile"));
    }
}
