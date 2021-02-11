/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app004.dao.impl;

import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.sd.nis.app004.dao.Nis004AhmsdnisTxnppltnsDao;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltns;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltnsPK;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository("nis004AhmsdnisTxnppltnsDao")
public class Nis004AhmsdnisTxnppltnsDaoImpl extends DefaultHibernateDao<Object, Serializable> implements Nis004AhmsdnisTxnppltnsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTxnppltns(AhmsdnisTxnppltns txnppltns) {
        sessionFactory.getCurrentSession().saveOrUpdate(txnppltns);
    }

    @Override
    public List<AhmsdnisTxnppltns> getAllTxnppltns() {
        return sessionFactory.getCurrentSession().createQuery("from AhmsdnisTxnppltns")
                .list();
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteTxnppltns(String txnppltns) {
        AhmsdnisTxnppltns ox = (AhmsdnisTxnppltns) sessionFactory.getCurrentSession().load(
                AhmsdnisTxnppltns.class, txnppltns);
        if (null != ox) {
            this.sessionFactory.getCurrentSession().delete(ox);
        }
    }

    @Transactional(readOnly = false)
    @Override
    public AhmsdnisTxnppltns updateTxnppltns(AhmsdnisTxnppltns txnppltns) {
        sessionFactory.getCurrentSession().update(txnppltns);
        return txnppltns;
    }

    @Override
    public AhmsdnisTxnppltns getTxnppltns(AhmsdnisTxnppltnsPK txnppltns) {
        return (AhmsdnisTxnppltns) sessionFactory.getCurrentSession().get(
                AhmsdnisTxnppltns.class, txnppltns);
    }

    @Override
    public List<AhmsdnisTxnppltns> searchTxnppltns(String year, String province, String district) {
        String sql = "select a from AhmsdnisTxnppltns a where a.nyear = :year and a.vbpsprvnm = :province and a.vbpsdstrnm= :district";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("year", year);
        query.setParameter("province", province);
        query.setParameter("district", district);
        List<AhmsdnisTxnppltns> ahmsdnisTxnppltnses = query.list();
        return ahmsdnisTxnppltnses;
    }

    @Override
    @Transactional(readOnly = false)
    public void saveAll(List<AhmsdnisTxnppltns> ats) {
        for (AhmsdnisTxnppltns at : ats) {
            addTxnppltns(at);
        }
        this.sessionFactory.getCurrentSession().flush();
    }

}
