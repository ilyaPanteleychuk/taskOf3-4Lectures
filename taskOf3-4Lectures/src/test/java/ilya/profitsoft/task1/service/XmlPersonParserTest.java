package ilya.profitsoft.task1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class XmlPersonParserTest {
    
    private XmlPersonParser xmlPersonParser;
    
    @BeforeEach
    void init(){
        xmlPersonParser = new XmlPersonParser();
    }
    
    @Test
    void parseXmlData_shouldConcatSurnameTagWithNameSavingFormatting_whenAttributesAreInOneLine() {
        List<String> expected = List.of("<person name=\"Іван Котляревський\" " +
                "birthDate=\"09.09.1769\" />");
        List<String> notParsedList = List.of("<person name=\"Іван\" " +
                "surname=\"Котляревський\" birthDate=\"09.09.1769\" />");
        List<String> actual = xmlPersonParser.parseXmlData(notParsedList);
        assertEquals(expected, actual);
    }
    
    @Test
    void parseXmlData_shouldConcatSurnameTagWithNameSavingFormatting_whenAttributesAreInDifferentLines() {
        List<String> expected = List.of("<person\n" +
                "            birthData=\"27.08.1856\"\n" +
                "            name = \"Іван Франко\" />");
        List<String> notParsedList = List.of("<person\n" +
                "            birthData=\"27.08.1856\"\n" +
                "            name = \"Іван\"\n" +
                "            surname = \"Франко\" />");
        List<String> actual = xmlPersonParser.parseXmlData(notParsedList);
        assertEquals(expected, actual);
    }
    
    @Test
    void parseXmlData_shouldThrowNullPointerException_whenInputIsNull() {
        assertThrows(NullPointerException.class, () ->
                xmlPersonParser.parseXmlData(null));
    }
}
