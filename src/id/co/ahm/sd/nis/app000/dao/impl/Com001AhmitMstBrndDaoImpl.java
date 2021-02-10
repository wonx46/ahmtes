package id.co.ahm.sd.nis.app000.dao.impl;

import java.io.Serializable;
import java.util.List;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.sd.nis.app000.dao.Com001AhmitMstBrndDao;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrndId;

@Repository("com001AhmitMstBrndDao")
public class Com001AhmitMstBrndDaoImpl extends DefaultHibernateDao<Object, Serializable> implements Com001AhmitMstBrndDao {

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public void addMstBrand(AhmsdnisMstbrnd brand) {
		sessionFactory.getCurrentSession().saveOrUpdate(brand);
		
	}

	@Override
	public List<AhmsdnisMstbrnd> getAllBrand() {
		return sessionFactory.getCurrentSession().createQuery("from AhmsdnisMstbrnd")
				.list();
	}

	@Override
	public void deleteBrand(AhmsdnisMstbrndId brandId) {
		AhmsdnisMstbrnd ox = (AhmsdnisMstbrnd) sessionFactory.getCurrentSession().load(
				AhmsdnisMstbrnd.class, brandId);
		if (null != ox) {
			this.sessionFactory.getCurrentSession().delete(ox);
		}
		
	}

	@Override
	public AhmsdnisMstbrnd updateBrand(AhmsdnisMstbrnd brand) {
		sessionFactory.getCurrentSession().update(brand);
		return brand;
	}

	@Override
	public AhmsdnisMstbrnd getBrand(AhmsdnisMstbrndId brandId) {
		return (AhmsdnisMstbrnd) sessionFactory.getCurrentSession().get(
				AhmsdnisMstbrnd.class, brandId);
	}

	@Override
	@Transactional(readOnly=false)
	public void saveAll(List<AhmsdnisMstbrnd> brand) {
		for (AhmsdnisMstbrnd o : brand) {
			System.out.println(ReflectionToStringBuilder.toString(o, ToStringStyle.MULTI_LINE_STYLE));
			addMstBrand(o);
		}
		this.sessionFactory.getCurrentSession().flush();
		
	}

}
