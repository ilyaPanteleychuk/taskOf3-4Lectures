package ilya.profitsoft.task1.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
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
    
    public List<String> readFile(String fileName) {
        if(fileName == null){
            throw new NullPointerException("File can not be null");
        }
        List<String> output = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(streamReader)
        ) {
            //regex to find full person element till /> or if it`s end - find closed root tag
            String regex = "((.*?)<person(.*?)/>|.*</persons>)";
            String currentLine;
            String fileContent = "";
            Pattern fullPersonPattern = Pattern.compile(regex, Pattern.DOTALL);
            while ((currentLine = bufferedReader.readLine()) != null) {
                fileContent = fileContent.concat(currentLine + "\n");
            }
            Matcher fullPersonMatcher = fullPersonPattern.matcher(fileContent);
            while (fullPersonMatcher.find()) {
                output.add(fullPersonMatcher.group());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;
    }
    
    public void writeFile(List<String> input, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            for (String line : input) {
                bufferedWriter.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
