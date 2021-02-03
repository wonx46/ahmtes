/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.security;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import static id.co.ahm.jxf.security.CryptoSecurity.key;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.IOException;
import java.security.Key;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

/**
 *
 * @author achmad.ha
 */
@Component(value = "tokenPshUtil")
public class TokenPshUtil {

    public boolean validateToken(String token) {
        boolean result = false;
        if (token != null) {
            VoUserCred voUserCred = getUserCred(token);
            if (voUserCred != null) {
                Date dateNow = new Date();
                Timestamp timestampNow = new Timestamp(dateNow.getTime());
                long diffInMiliSecond = Math.abs(timestampNow.getTime() - voUserCred.getReqdate().getTime());
                long diffInMinute = diffInMiliSecond / 1000 / 60;
                if ((diffInMinute >= 0) && (diffInMinute <= 20)) {
                    return true;
                }
            }
        }
        return result;
    }

    public VoUserCred getUserCred(String token) {        
        VoUserCred voUserCred = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonUserCred = CryptoSecurity.decrypt(token);
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            voUserCred = objectMapper.readValue(jsonUserCred, VoUserCred.class);
            return voUserCred;
        } catch (IOException ex) {
            Logger.getLogger(TokenPshUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voUserCred;
    }

    
    public static void main(String[] args) {
        String token = "Nb+Z+b22Bkoseqfh4N+zsCRBgqPPCc86yld1MweK7PtLlq9p1ujTCpeBrxVEMSaprL7jK+JCsZT6Hap4cUjRF8I4JMZ6V9zAsMICl9iuN0fXcHDuquaK1uqIrEnZtu5Ov3JYx+NfsLMAR4S1Hg10TrAWoOsHqqvv/Z3pdrvJZkxVTzUXrBilAplU6qrkYw27";
        TokenPshUtil tokenPshUtil = new TokenPshUtil();
        VoUserCred voUserCred = tokenPshUtil.getUserCred(token);
        System.out.println("");
    }
    

}
