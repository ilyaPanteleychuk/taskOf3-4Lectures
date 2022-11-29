package ilya.profitsoft.task1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class provides functionality of processing
 * data in XML file with Person tags
 *
 * @author Ilya Panteleychuk
 * */
public class XmlPersonParser {
    
    public List<String> parseXmlData(List<String> fileContent){
        if (fileContent == null){
            throw new NullPointerException();
        }
        //regex to find attribute surname in the line
        String surnameRegex = "\\s+surname\\s*=\\s*\"(\\S*)\"";
        //regex to find attribute name in the line
        String nameRegex = "\\s+name\\s*=\\s*\"(\\S*)\"";
        List<String> parsedData = new ArrayList<>();
        Pattern surnamePattern = Pattern.compile(surnameRegex);
        Pattern namePattern = Pattern.compile(nameRegex);
        for (String currentLine : fileContent) {
            Matcher surnameMatcher = surnamePattern.matcher(currentLine);
            Matcher nameMatcher = namePattern.matcher(currentLine);
            if (surnameMatcher.find() && nameMatcher.find()) {
                String name = nameMatcher.group(1);
                String surname = surnameMatcher.group(1);
                String fullName = name + " " + surname;
                String replacedString = currentLine
                        .replaceAll(surnameRegex, "")
                        .replace(name, fullName);
                parsedData.add(replacedString);
            }else{
                parsedData.add(currentLine);
            }
        }
        return parsedData;
    }
}
