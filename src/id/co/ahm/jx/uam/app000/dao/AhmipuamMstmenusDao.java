/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.dao;

import id.co.ahm.jx.uam.app000.model.AhmipuamMstmenus;
import id.co.ahm.jxf.dao.DefaultDao;
import java.util.List;

/**
 *
 * @author achmad.ha
 */
public interface AhmipuamMstmenusDao extends DefaultDao<AhmipuamMstmenus, Long> {

    List<String> getCustomRole(String menuCode, String username);

    List<String> getCustomRoleName(String username);
}
