package ilya.profitsoft.task1.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PersonFileProcessorTest {
    
    private PersonFileProcessor fileProcessor;
    
    @BeforeEach
    void init(){
        fileProcessor = new PersonFileProcessor();
    }
    
    @Test
    void formatXmlData_shouldConcatSurnameTagWithNameSavingFormatting_whenAttributesAreInOneLine() {
        String expected = "<person name=\"Іван Котляревський\" " +
                "birthDate=\"09.09.1769\" />";
        String notParsedString = "<person name=\"Іван\" " +
                "surname=\"Котляревський\" birthDate=\"09.09.1769\" />";
        String actual = fileProcessor.formatXmlData(notParsedString);
        assertEquals(expected, actual);
    }
    
    @Test
    void formatXmlData_shouldConcatSurnameTagWithNameSavingFormatting_whenAttributesAreInDifferentLines() {
        String expected = "<person\n" +
                "            birthData=\"27.08.1856\"\n" +
                "            name = \"Іван Франко\" />";
        String notParsedLine = "<person\n" +
                "            birthData=\"27.08.1856\"\n" +
                "            name = \"Іван\"\n" +
                "            surname = \"Франко\" />";
        String actual = fileProcessor.formatXmlData(notParsedLine);
        assertEquals(expected, actual);
    }
    
    @Test
    void formatXmlData_shouldConcatSurnameTagWithNameSavingFormatting_whenElementsHaveSpaces() {
        String expected = "<person\n" +
                "            birthData=\"27.08.1856\"\n" +
                "            name = \"Ів  ан Франк о\" />";
        String notParsedLine = "<person\n" +
                "            birthData=\"27.08.1856\"\n" +
                "            name = \"Ів  ан\"\n" +
                "            surname = \"Франк о\" />";
        String actual = fileProcessor.formatXmlData(notParsedLine);
        assertEquals(expected, actual);
    }
    
    @Test
    void formatXmlData_shouldThrowNullPointerException_whenInputIsNull() {
        assertThrows(NullPointerException.class, () ->
                fileProcessor.formatXmlData(null));
    }
}
