/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app999.rest;

import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.dto.DtoResponseWorkspace;
import id.co.ahm.jxf.security.TokenPstUtil;
import id.co.ahm.jxf.util.DtoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author achmad.ha
 */
@RestController
@RequestMapping("sd/nis999")
public class Nis999Rest {
    
    @Autowired
    @Qualifier(value = "tokenPstUtil")
    private TokenPstUtil tokenPstUtil;
    
    @RequestMapping(value = "test", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoResponseWorkspace test(@RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token) {
        return DtoHelper.constructResponseWorkspace(StatusMsgEnum.SUKSES, "", null, tokenPstUtil.getUserCred(token));
    }
        
    
}
