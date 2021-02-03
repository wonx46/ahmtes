/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.dao;

import id.co.ahm.jx.uam.app000.model.AhmipuamMstservices;
import id.co.ahm.jx.uam.app000.vo.VoService;
import id.co.ahm.jxf.dao.GenericVidDao;
import java.util.List;

/**
 *
 * @author achmad.ha
 */
public interface AhmipuamMstservicesDao extends GenericVidDao<AhmipuamMstservices, String>{
    
    boolean validateUrlAndUsername(String url,String username);
    VoService getByUrlAndUsername(String url,String username);
    List<String> getByUsername(String username);
    
}
