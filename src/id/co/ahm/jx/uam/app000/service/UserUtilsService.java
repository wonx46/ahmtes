/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.service;

import id.co.ahm.jx.uam.app000.vo.VoOrganization;
import id.co.ahm.jx.uam.app000.vo.VoRole;
import id.co.ahm.jxf.vo.VoPstUserCred;
import java.util.List;

/**
 *
 * @author achmad.ha
 */
public interface UserUtilsService {

    VoPstUserCred getUserCred(String token);

    List<VoRole> getCustomRolesByApplication(String menuCode, String token);

    List<VoRole> getRolesByApplication(String menuCode, String token);

    List<VoRole> getRolesByService(String url, String token);
    
    VoOrganization getOrganization(int nrp);

}
