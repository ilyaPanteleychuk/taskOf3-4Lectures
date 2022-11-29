package ilya.profitsoft.task1;

import ilya.profitsoft.task1.repository.PersonFileProcessor;
import ilya.profitsoft.task2.utils.PropertyReader;
import java.util.List;
import java.util.Properties;


public class Main {
    
    public static void main( String[] args ) {
        Properties properties = PropertyReader
                .readProperties
                        ("taskOf3-4Lectures/src/main/resources/application.properties");
        PersonFileProcessor personFileProcessor = new PersonFileProcessor();
        personFileProcessor.processFile(properties.getProperty("task1.input"),
                properties.getProperty("task1.output"));
    }
}
