package ilya.profitsoft.task2.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ilya.profitsoft.task2.model.Fine;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Class provides functionality of reading
 * {@link Fine} from JSON file
 *
 * @author Ilya Panteleychuk
 * */
public class JsonFineFileProcessor {
    
    public List<Fine> parseFinesFromFile(String path){
        if(path == null){
            throw new NullPointerException();
        }
        List<Fine> output;
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(path));
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            output = objectMapper.readValue(jsonData,
                    new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;
    }
    
    public List<Fine> parseFinesFromFolder(List<String> paths){
        if(paths == null){
            throw new NullPointerException();
        }
        List<Fine> output = new ArrayList<>();
        for(String path: paths){
            output.addAll(parseFinesFromFile(path));
        }
        return output;
    }
}
