/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.ahm.jxf.security.CryptoSecurity;
import id.co.ahm.jxf.security.TokenOwoUtil;
import id.co.ahm.jxf.vo.VoUserCred;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author achmad.ha
 */
@Controller
@RequestMapping("/rest-explore")
public class RestExplore {
    
    
    @Autowired
    @Qualifier(value = "tokenOwoUtil")
    private TokenOwoUtil tokenOwoUtil;
    
    @RequestMapping(value = "get-header", method = RequestMethod.POST,
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getHeader(@RequestHeader(value="token", defaultValue="") String token) {
        VoUserCred voUserCred = tokenOwoUtil.getUserCred(token);        
        return "";
    }

    public TokenOwoUtil getTokenOwoUtil() {
        return tokenOwoUtil;
    }

    public void setTokenOwoUtil(TokenOwoUtil tokenOwoUtil) {
        this.tokenOwoUtil = tokenOwoUtil;
    }

    
}
