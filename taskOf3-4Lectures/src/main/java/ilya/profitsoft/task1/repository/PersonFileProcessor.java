package ilya.profitsoft.task1.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Class provides functionality of reading and writing
 * XML files with Person tags
 *
 * @author Ilya Panteleychuk
 * */
public class PersonFileProcessor {
    
    public List<String> processFile(String fromFile, String toFile) {
        if(fromFile == null){
            throw new NullPointerException("File can not be null");
        }
        List<String> output = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fromFile);
             InputStreamReader streamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(streamReader);
             FileWriter fileWriter = new FileWriter(toFile);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            //regex to find full person element till /> or if it`s end - find closed root tag
            String regex = "((.*?)<person(.*?)/>|.*</persons>)";
            String currentLine;
            String fileContent = "";
            Pattern fullPersonPattern = Pattern.compile(regex, Pattern.DOTALL);
            while ((currentLine = bufferedReader.readLine()) != null) {
                fileContent = fileContent.concat(  currentLine + "\n");
                Matcher fullPersonMatcher = fullPersonPattern.matcher(fileContent);
                while (fullPersonMatcher.find()){
                    bufferedWriter.write(formatXmlData(fullPersonMatcher.group()));
                    fileContent = "\n";
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;
    }
    
    public String formatXmlData(String currentLine){
        if (currentLine == null){
            throw new NullPointerException();
        }
        //regex to find attribute surname in the line
        String surnameRegex = "\\s+surname\\s*=\\s*\"(\\S*)\"";
        //regex to find attribute name in the line
        String nameRegex = "\\s+name\\s*=\\s*\"(\\S*)\"";
        Pattern surnamePattern = Pattern.compile(surnameRegex);
        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher surnameMatcher = surnamePattern.matcher(currentLine);
        Matcher nameMatcher = namePattern.matcher(currentLine);
        String outputString = "";
        if (surnameMatcher.find() && nameMatcher.find()) {
            String name = nameMatcher.group(1);
            String surname = surnameMatcher.group(1);
            String fullName = name + " " + surname;
            outputString = currentLine
                    .replaceAll(surnameRegex, "")
                    .replace(name, fullName);
        }else{
            outputString = currentLine;
        }
        return outputString;
    }
}
