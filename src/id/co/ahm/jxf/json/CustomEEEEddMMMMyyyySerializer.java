/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author achmad.ha
 */
public class CustomEEEEddMMMMyyyySerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {

        Locale locale = new Locale("id", "ID");
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE dd MMMM yyyy", locale);
        String formattedDate = formatter.format(t);
        jg.writeString(formattedDate);
    }

}
