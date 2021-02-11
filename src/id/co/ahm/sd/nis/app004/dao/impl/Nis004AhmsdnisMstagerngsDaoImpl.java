/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app004.dao.impl;

import id.co.ahm.jxf.dao.DefaultHibernateDao;
import java.io.Serializable;
import org.springframework.stereotype.Repository;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstagerngs;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import id.co.ahm.sd.nis.app004.dao.Nis004AhmsdnisMstagerngsDao;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository("nis004AhmsdnisMstagerngsDao")
public class Nis004AhmsdnisMstagerngsDaoImpl extends DefaultHibernateDao<Object, Serializable> implements Nis004AhmsdnisMstagerngsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addMstagerngs(AhmsdnisMstagerngs agerngs) {
        sessionFactory.getCurrentSession().saveOrUpdate(agerngs);
    }

    @Override
    public List<AhmsdnisMstagerngs> getAllAgerngs() {
        return sessionFactory.getCurrentSession().createQuery("from AhmsdnisMstagerngs")
                .list();
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAgerngs(String agerngsCode) {
        AhmsdnisMstagerngs ox = (AhmsdnisMstagerngs) sessionFactory.getCurrentSession().load(
                AhmsdnisMstagerngs.class, agerngsCode);
        if (null != ox) {
            this.sessionFactory.getCurrentSession().delete(ox);
        }
    }

    @Override
    public AhmsdnisMstagerngs updateAgerngs(AhmsdnisMstagerngs mstagerngs) {
        sessionFactory.getCurrentSession().update(mstagerngs);
        return mstagerngs;
    }

    @Override
    public AhmsdnisMstagerngs getAgerngs(String agerngsCode) {
        return (AhmsdnisMstagerngs) sessionFactory.getCurrentSession().get(
                AhmsdnisMstagerngs.class, agerngsCode);
    }

    @Override
    public List<AhmsdnisMstagerngs> searchMstagerngs(String search) {
        String sql = "select a from AhmsdnisMstagerngs a where a.vcdagerng = :search";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("search", search);
        List<AhmsdnisMstagerngs> ahmsdnisMstagerngses = query.list();
        return ahmsdnisMstagerngses;
    }

    @Override
    public AhmsdnisMstagerngs getLastRowAgeRange() {
        String sql = "select a from AhmsdnisMstagerngs a order by vcdagerng desc  ";
        Query query = getCurrentSession().createQuery(sql);
        query.setMaxResults(1);
        return (AhmsdnisMstagerngs)query.uniqueResult();
    }

}
