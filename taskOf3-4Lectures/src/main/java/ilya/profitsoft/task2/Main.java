package ilya.profitsoft.task2;

import ilya.profitsoft.task2.service.FineStatisticFormatter;
import ilya.profitsoft.task2.service.JsonFineFileProcessor;
import ilya.profitsoft.task2.service.XmlFineFileProcessor;
import ilya.profitsoft.task2.utils.PropertyReader;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Main {
    
    public static void main(String[] args) {
        Properties properties = PropertyReader
                .readProperties
                        ("taskOf3-4Lectures/src/main/resources/application.properties");
        JsonFineFileProcessor jsonFineFileProcessor = new JsonFineFileProcessor();
        FineStatisticFormatter fineStatisticFormatter = new FineStatisticFormatter();
        Map<String, Double> statistic = fineStatisticFormatter
                .sortFinesByAmount(jsonFineFileProcessor.parseFinesFromFolder(
                        List.of(properties.getProperty("task2.inputJson1"),
                                properties.getProperty("task2.inputJson2"))));
        XmlFineFileProcessor xmlFineFileProcessor = new XmlFineFileProcessor();
        xmlFineFileProcessor.writeFinesToXml(statistic,
                new File(properties.getProperty("task2.output")));
    }
}
