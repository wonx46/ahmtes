/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app004.dao.impl;

import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstadmns;
import id.co.ahm.sd.nis.app004.dao.Nis004AhmsdnisMstadmnsDao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author admin
 */
@Repository("nis004AhmsdnisMstadmnsDao")
public class Nis004AhmsdnisMstadmnsDaoImpl extends DefaultHibernateDao<Object, Serializable> implements Nis004AhmsdnisMstadmnsDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<AhmsdnisMstadmns> getAllMstadmns() {
        return sessionFactory.getCurrentSession().createQuery("FROM AhmsdnisMstadmns").list();
    }
    
}
