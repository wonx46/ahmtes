/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author achmad.ha
 */
public class CustomyyyyMMddHHmmssDeserializer extends JsonDeserializer<Date>{

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
            return sdf.parse(jp.getText());
        } catch (ParseException ex) {
            Logger.getLogger(CustomyyyyMMddHHmmssDeserializer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }    
}
