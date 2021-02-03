/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.dao.impl;

import id.co.ahm.jx.uam.app000.dao.AhmipuamMstrolesDao;
import id.co.ahm.jx.uam.app000.model.AhmipuamMstroles;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author achmad.ha
 */
@Repository("ahmipuamMstrolesDao")
public class AhmipuamMstrolesDaoImpl  extends DefaultHibernateDao<AhmipuamMstroles, Long>
        implements AhmipuamMstrolesDao {
    
}
