package id.co.ahm.sd.nis.app000.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.sd.nis.app000.dao.Com001AhmitMstBrndDao;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd;

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
	public void deleteBrand(String brandId) {
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
	public AhmsdnisMstbrnd getBrand(String brandId) {
		return (AhmsdnisMstbrnd) sessionFactory.getCurrentSession().get(
				AhmsdnisMstbrnd.class, brandId);
	}

}
