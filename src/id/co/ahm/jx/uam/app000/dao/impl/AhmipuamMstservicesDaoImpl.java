/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.dao.impl;

import id.co.ahm.jxf.dao.GenericVidHibernateDao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import id.co.ahm.jx.uam.app000.dao.AhmipuamMstservicesDao;
import id.co.ahm.jx.uam.app000.model.AhmipuamMstrlsvcs;
import id.co.ahm.jx.uam.app000.model.AhmipuamMstservices;
import id.co.ahm.jx.uam.app000.vo.VoService;

/**
 *
 * @author achmad.ha
 */
@Repository("ahmipuamMstservicesDao")
public class AhmipuamMstservicesDaoImpl extends GenericVidHibernateDao<AhmipuamMstservices, String>
        implements AhmipuamMstservicesDao{
    
    @Override
    public boolean validateUrlAndUsername(String url,String username){
        String hql = "SELECT service.vmethod , service.vurl  "
                + "FROM AhmipuamMstservices service , AhmipuamMstrlsvcs rolsvc  "
                + ", AhmipuamMstuserrls usrrls , AhmipuamMstroles roles "
                + "WHERE service.vid = rolsvc.vidAhmjxuamMstservices "
                + "AND rolsvc.mroleIidrole = usrrls.iroleintid "
                + "AND roles.iinternalid = usrrls.iroleintid "
                + "AND usrrls.vusername = :username "
                + "AND rolsvc.dbegineff <= :now  "
                + "AND rolsvc.dendeff >= :now "
                + "AND roles.venableflag = 'Y' "
                + "AND service.vurl = :url ";
        Query query = getCurrentSession().createQuery(hql);
        query.setDate("now", new DateTime().toDate());
        query.setString("username", username);
        query.setString("url", url);   
        List<Object[]> temp = query.list();
        if (temp != null) {
            if(temp.size()>0){
                return true;
            }
        }
        return false;
    }
    
    public VoService getByUrlAndUsername(String url,String username){
        String hql = "SELECT service.vmethod , service.vurl , service.bexpired "
                + "FROM AhmipuamMstservices service , AhmipuamMstrlsvcs rolsvc "
                + ", AhmipuamMstuserrls usrrls , AhmipuamMstroles roles "
                + "WHERE service.vid = rolsvc.vidAhmjxuamMstservices "
                + "AND rolsvc.mroleIidrole = usrrls.iroleintid "
                + "AND roles.iinternalid = usrrls.iroleintid "
                + "AND usrrls.vusername = :username "
                + "AND rolsvc.dbegineff <= :now  "
                + "AND rolsvc.dendeff >= :now "
                + "AND roles.venableflag = 'Y' "
                + "AND service.vurl = :url ";
        Query query = getCurrentSession().createQuery(hql);
        query.setDate("now", new DateTime().toDate());
        query.setString("username", username);
        query.setString("url", url);   
        List<Object[]> temp = query.list();
        if (temp != null) {
            if(temp.size()>0){
                Object[] obj = temp.get(0);
                VoService voService = new VoService();
                voService.setMethod(obj[0].toString());
                voService.setUrl(obj[1].toString());
                voService.setFlagExp(obj[2].toString());
                return voService;
            }
        }
        return null;
    }
    
    public List<String> getByUsername(String username){        
        List<String> result = new ArrayList<String>();
        String hql = "SELECT service.vmethod , service.vurl  "
                + "FROM AhmipuamMstservices service , AhmipuamMstrlsvcs rolsvc  "
                + ", AhmipuamMstuserrls usrrls , AhmipuamMstroles roles "
                + "WHERE service.vid = rolsvc.vidAhmjxuamMstservices "
                + "AND rolsvc.mroleIidrole = usrrls.iroleintid "
                + "AND roles.iinternalid = usrrls.iroleintid "
                + "AND usrrls.vusername = :username "
                + "AND rolsvc.dbegineff <= :now  "
                + "AND rolsvc.dendeff >= :now "
                + "AND roles.venableflag = 'Y' ";
        Query query = getCurrentSession().createQuery(hql);
        query.setDate("now", new DateTime().toDate());
        query.setString("username", username);
        List<Object[]> temp = query.list();
        if (temp != null) {
            for(Object[] obj : temp){
                result.add(obj[0]+""+obj[1]);
            }
        }
        return result;
    }
    
}
