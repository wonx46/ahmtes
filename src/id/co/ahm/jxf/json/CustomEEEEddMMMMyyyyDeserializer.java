/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author achmad.ha
 */
public class CustomEEEEddMMMMyyyyDeserializer extends JsonDeserializer<Date>{

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        try {
            Locale locale = new Locale("id","ID");
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy",locale);
            return sdf.parse(jp.getText());
        } catch (ParseException ex) {
            Logger.getLogger(CustomddMMMyyyyHHmmssDeserializer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
