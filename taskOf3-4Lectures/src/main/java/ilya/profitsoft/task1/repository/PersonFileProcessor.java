package ilya.profitsoft.task1.repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Class provides functionality of reading
 * XML files with Person tags
 *
 * @author Ilya Panteleychuk
 * */
public class PersonFileProcessor {
    
    public List<String> readFile(String fileName) {
        if(fileName == null){
            throw new NullPointerException("File can not be null");
        }
        List<String> fileContent = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(streamReader)
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //read full person element till />
                while (!line.endsWith("/>") && !(line.equals("</persons>"))) {
                    line = line.concat("\n" + bufferedReader.readLine());
                }
                fileContent.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileContent;
    }
    
    public void writeFile(List<String> input, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            for (String line : input) {
                bufferedWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
