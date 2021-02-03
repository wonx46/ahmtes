/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.security;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author achmad.ha
 */
public class BasicAuth implements Serializable{
    
    public static String getUserName(HttpServletRequest httpServletRequest){
        String result = "";
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null) {
            header = header.replaceAll("Basic ", "");
            String[] values = new String(Base64.decodeBase64(header)).split(":");
            result = values[0];
        }
        return result;
    }
    
    public static String getPassword(HttpServletRequest httpServletRequest){
        String result = "";
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null) {
            header = header.replaceAll("Basic ", "");
            String values = new String(Base64.decodeBase64(header));
            result = values.substring(values.indexOf(":")+1);
        }
        return result;
    }
    
}
