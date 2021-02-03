/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.service.impl;

import id.co.ahm.jx.uam.app000.dao.AhmipuamMstroleaasDao;
import id.co.ahm.jx.uam.app000.dao.AhmitsysMstextusersDao;
import id.co.ahm.jx.uam.app000.dao.AhmmoerpMstkaryawansDao;
import id.co.ahm.jx.uam.app000.service.*;
import id.co.ahm.jx.uam.app000.vo.VoOrganization;
import id.co.ahm.jx.uam.app000.vo.VoRole;
import id.co.ahm.jxf.security.CryptoSecurity;
import id.co.ahm.jxf.security.TokenBearerAuth;
import id.co.ahm.jxf.security.TokenPstUtil;
import id.co.ahm.jxf.vo.VoPstUserCred;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author achmad.ha
 */
@Transactional(readOnly = true)
@Service("userUtilsService")
public class UserUtilsServiceImpl implements UserUtilsService {

    @Autowired
    @Qualifier(value = "tokenPstUtil")
    private TokenPstUtil tokenPstUtil;

    @Autowired
    @Qualifier(value = "ahmipuamMstroleaasDao")
    private AhmipuamMstroleaasDao ahmipuamMstroleaasDao;
    
    @Autowired
    @Qualifier(value = "ahmmoerpMstkaryawansDao")
    private AhmmoerpMstkaryawansDao ahmmoerpMstkaryawansDao;

    @Override
    public VoPstUserCred getUserCred(String token) {
        if(TokenBearerAuth.isBearer(token)){
            token = TokenBearerAuth.getToken(token);
        }
        VoPstUserCred voPstUserCred = null;
        Jws<Claims> claims = tokenPstUtil.extractToken(token);
        if (claims != null) {
            voPstUserCred = new VoPstUserCred();
            voPstUserCred.setUsername(CryptoSecurity.decrypt((String) claims.getBody().get("username"), 2));
            voPstUserCred.setEmail(CryptoSecurity.decrypt((String) claims.getBody().get("email"), 2));
            voPstUserCred.setFullname(CryptoSecurity.decrypt((String) claims.getBody().get("fullname"), 2));
            voPstUserCred.setArea(CryptoSecurity.decrypt((String) claims.getBody().get("area"), 2));
            voPstUserCred.setUserid(CryptoSecurity.decrypt((String) claims.getBody().get("userid"), 2));
            if (StringUtils.isNotBlank((String) claims.getBody().get("partnerid"))) {
                voPstUserCred.setPartnerid(CryptoSecurity.decrypt((String) claims.getBody().get("partnerid"), 2));
            }
            if (StringUtils.isNotBlank((String) claims.getBody().get("partnerName"))) {
                voPstUserCred.setPartnerName(CryptoSecurity.decrypt((String) claims.getBody().get("partnerName"), 2));
            }
            if (StringUtils.isNotBlank((String) claims.getBody().get("costCenter"))) {
                voPstUserCred.setCostCenter(CryptoSecurity.decrypt((String) claims.getBody().get("costCenter"), 2));
            }
            voPstUserCred.setType(CryptoSecurity.decrypt((String) claims.getBody().get("type"), 2));
            voPstUserCred.setMdH1((List<String>) (claims.getBody().get("mdH1")));
            voPstUserCred.setMdH2((List<String>) (claims.getBody().get("mdH2")));
            voPstUserCred.setMdH3((List<String>) (claims.getBody().get("mdH3")));
            voPstUserCred.setPlant(CryptoSecurity.decrypt((String) claims.getBody().get("plant"), 2));
            voPstUserCred.setPlantDesc(CryptoSecurity.decrypt((String) claims.getBody().get("plantDesc"), 2));            
        }
        return voPstUserCred;
    }

    @Override
    public List<VoRole> getCustomRolesByApplication(String menuCode, String token) {
        VoPstUserCred voPstUserCred = this.getUserCred(token);
        return ahmipuamMstroleaasDao.getCustomRolesByApplication(menuCode, voPstUserCred.getUsername());
    }

    @Override
    public List<VoRole> getRolesByApplication(String menuCode, String token) {
        VoPstUserCred voPstUserCred = this.getUserCred(token);
        return ahmipuamMstroleaasDao.getRolesByApplication(menuCode, voPstUserCred.getUsername());
    }

    @Override
    public List<VoRole> getRolesByService(String url, String token) {
        VoPstUserCred voPstUserCred = this.getUserCred(token);
        return ahmipuamMstroleaasDao.getRolesByService(url, voPstUserCred.getUsername());
    }
    
    @Override
    public VoOrganization getOrganization(int nrp){        
        return ahmmoerpMstkaryawansDao.getUserOrganization(nrp);
    }

}
