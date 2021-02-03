/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.dao;

import id.co.ahm.jx.uam.app000.model.AhmipuamMstroleaas;
import id.co.ahm.jx.uam.app000.vo.VoRole;
import id.co.ahm.jxf.dao.DefaultDao;
import java.util.List;

/**
 *
 * @author hendra.fs
 */
public interface AhmipuamMstroleaasDao extends DefaultDao<AhmipuamMstroleaas, Long> {

    List<VoRole> getCustomRolesByApplication(String menuCode, String username);

    List<VoRole> getRolesByApplication(String menuCode, String username);

    List<VoRole> getRolesByService(String url, String username);

}
