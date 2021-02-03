/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.lov;

import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.util.DtoHelper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author HP
 */
public class LovUtil {
    
    private static String FIX_PATH = "/ahmsvitlov000-pst/rest/lov/";
    
    private LovUtil() {}
    
    private static String getUrl()  {        
        
        InputStream input = null;
        try {
            Properties prop = new Properties();
            String pathConfig = System.getProperty("jxconfig");
            input = new FileInputStream(pathConfig);
            // load a properties file
            prop.load(input);
            String server = prop.getProperty("auth-server");
            server = ( StringUtils.isEmpty(server) ? "http://localhost:8080" : server ) + FIX_PATH;
            return server;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LovUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LovUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(LovUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public static DtoResponse find(String lovName,DtoParamPaging dtoParamPaging) {
        
        String url = "";
        
        try {
            url = getUrl();
        }catch(Exception _e) {
            _e.printStackTrace();
            Map<String,Object> map = new HashMap<>();
            map.put("message", "Tidak dapat menemukan url server");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, map, null);
        }
        
        RestTemplate rTemplate = new RestTemplate();
        ResponseEntity<DtoResponse> respEntity = 
                rTemplate.postForEntity( url + "search/" + lovName, 
                        dtoParamPaging, DtoResponse.class );
        
        return respEntity.getBody();
        
    }
    
    public static DtoResponse findById(String lovName,String id) {
        
        String url = "";
        
        try {
            url = getUrl();
        }catch(Exception _e) {
            _e.printStackTrace();
            Map<String,Object> map = new HashMap<>();
            map.put("message", "Tidak dapat menemukan url server");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, map, null);
        }
        
        RestTemplate rTemplate = new RestTemplate();
        ResponseEntity<DtoResponse> respEntity = 
                rTemplate.getForEntity(url + "by-id/" + lovName + "/" + id, DtoResponse.class );
        
        return respEntity.getBody();
        
    }
    
}
