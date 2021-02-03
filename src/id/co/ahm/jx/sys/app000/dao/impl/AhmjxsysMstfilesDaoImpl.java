/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.dao.impl;

import id.co.ahm.jx.sys.app000.dao.AhmjxsysMstfilesDao;
import id.co.ahm.jx.sys.app000.model.AhmjxsysMstfiles;
import id.co.ahm.jxf.dao.GenericVidHibernateDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author achmad.ha
 */
@Repository("ahmjxsysMstfilesDao")
public class AhmjxsysMstfilesDaoImpl extends GenericVidHibernateDao<AhmjxsysMstfiles, String>
        implements AhmjxsysMstfilesDao{
    
}
