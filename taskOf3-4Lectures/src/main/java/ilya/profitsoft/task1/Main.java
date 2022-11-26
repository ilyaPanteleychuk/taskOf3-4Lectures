package ilya.profitsoft.task1;

import ilya.profitsoft.task1.repository.PersonFileProcessor;
import ilya.profitsoft.task1.service.XmlPersonParser;
import ilya.profitsoft.task2.utils.PropertyReader;
import java.util.List;
import java.util.Properties;


public class Main {
    
    public static void main( String[] args ) {
        Properties properties = PropertyReader
                .readProperties
                        ("taskOf3-4Lectures/src/main/resources/application.properties");
        PersonFileProcessor fileProcessor = new PersonFileProcessor();
        XmlPersonParser xmlPersonParser = new XmlPersonParser();
        List<String> fileContent = fileProcessor.readFile(properties.getProperty("task1.input"));
        List<String> parsedContent = xmlPersonParser.parseXmlData(fileContent);
        fileProcessor.writeFile(parsedContent, properties.getProperty("task1.output"));
    }
}
