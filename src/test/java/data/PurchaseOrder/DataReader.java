package data.PurchaseOrder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String, String>> getJsonToMap() throws IOException {
        //read json to string
       String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder//PurchaseOrder.json"));

       //convert string to hashmap -jackson databind is used for that
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
        }
}
