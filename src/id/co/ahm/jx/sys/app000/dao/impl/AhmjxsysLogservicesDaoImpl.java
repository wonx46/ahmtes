/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.dao.impl;

import id.co.ahm.jx.sys.app000.dao.AhmjxsysLogservicesDao;
import id.co.ahm.jx.sys.app000.model.AhmjxsysLogservices;
import id.co.ahm.jxf.dao.GenericVidHibernateDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author achmad.ha
 */
@Repository("ahmjxsysLogservicesDao")
public class AhmjxsysLogservicesDaoImpl extends GenericVidHibernateDao<AhmjxsysLogservices, String>
        implements AhmjxsysLogservicesDao {
    
    
}
