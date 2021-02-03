/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author achmad.ha
 */
@Component(value = "tokenOwoUtil")
public class TokenOwoUtil {
    
    public boolean validateToken(String token){
        boolean result = false;
        if(token!=null){
            VoUserCred voUserCred = getUserCred(token);
            if(voUserCred!=null){
                Date dateNow = new Date();
                Timestamp timestampNow = new Timestamp(dateNow.getTime());
                long diffInMiliSecond = Math.abs(timestampNow.getTime() - voUserCred.getReqdate().getTime());
                long diffInMinute = diffInMiliSecond/1000/60;
                if((diffInMinute >= 0)&&(diffInMinute <= 20)){
                    return true;
                }
            }
        }
        return result;
    }    
    
    public VoUserCred getUserCred(String token){
        VoUserCred voUserCred = null;
        ObjectMapper objectMapper = new ObjectMapper();        
        try {
            String jsonUserCred = CryptoSecurity.decrypt(token);
            voUserCred = objectMapper.readValue(jsonUserCred, VoUserCred.class);
            return voUserCred;
        } catch (IOException ex) {
            Logger.getLogger(TokenOwoUtil.class.getName()).log(Level.SEVERE, null, ex);            
        } 
        return voUserCred;
    }
    
    
}
