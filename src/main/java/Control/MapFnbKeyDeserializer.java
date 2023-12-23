package Control;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import Entity.FnB;

public class MapFnbKeyDeserializer extends KeyDeserializer {
    @Override
    public FnB deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // Find fnbID in the json object of FnB in orderQty
        String id = "";
        int firstIndex = key.indexOf("fnbID");
        // Extract the id value
        for (int i = firstIndex + 8; i< key.length(); i++) {
            char chari = key.charAt(i);
            if (chari == ',') {
                // Stop when end of id reached
                break;
            } else if (chari != ',' && chari != ' ') {
                // Add character if it is a number
                id = id + chari;
            }
        }
        // Split use the created id value to get actual FnB object
        return FnB.getFnb(Integer.parseInt(id));
    }
}