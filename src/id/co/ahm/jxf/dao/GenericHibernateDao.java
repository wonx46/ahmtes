/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.dao;

import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.model.BaseAuditVersioning;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author achmad.ha
 */
public class GenericHibernateDao<T, K extends Serializable> extends BaseColumnFilterDao implements GenericDao<T, K> {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    
    private Class<T> persistentClass;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public GenericHibernateDao() {
        persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T load(K key) {
        return (T) sessionFactory.getCurrentSession().load(persistentClass, key);
    }

    @Override
    public T findOne(K key) {
        return (T) sessionFactory.getCurrentSession().get(persistentClass, key);
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + persistentClass.getName()).list();
    }

    @Override
    public boolean deleteById(K key) {
        Object entity = getCurrentSession().get(persistentClass, key);
        getCurrentSession().delete(entity);
        return true;
    }

    @Override
    public boolean delete(BaseAuditVersioning entity) {
        getCurrentSession().delete(entity);
        return true;
    }

    @Override
    public boolean evict(Object entity) {
        getCurrentSession().evict(entity);
        return true;
    }

    @Override
    public void flush() {
        getCurrentSession().flush();
    }

    @Override
    public void clear() {
        getCurrentSession().clear();
    }

    public Criterion buildResctrictionsAndForAllProperty(String[] listProp, String[] listValueLike) {
        Criterion criterion = null;
        for (String valueLike : listValueLike) {
            if (criterion == null) {
                criterion = buildResctrictionsOrForAllProperty(listProp, valueLike);
            } else {
                criterion = Restrictions.and(criterion, buildResctrictionsOrForAllProperty(listProp, valueLike));
            }
        }
        return criterion;
    }

    public Criterion buildResctrictionsOrForAllProperty(String[] listProp, String valueLike) {
        Criterion criterion = null;
        for (String prop : listProp) {
            if (criterion == null) {
                criterion = Restrictions.ilike(prop, valueLike, MatchMode.ANYWHERE);
            } else {
                String valueLikeTemp = null;
                valueLikeTemp = valueLike;
                criterion = Restrictions.or(criterion, Restrictions.ilike(prop, valueLikeTemp, MatchMode.ANYWHERE));
            }
        }
        return criterion;
    }

    @Override
    public boolean save(BaseAuditVersioning newEntity, String user,String idservice) {
        Date date = new Date();
        newEntity.setCreateBy(user);
        newEntity.setCreateDate(date);
//        newEntity.setLastModBy(user);
//        newEntity.setLastModDate(date);
        newEntity.setService(idservice);
        getCurrentSession().save(newEntity);
        return true;
    }

    @Override
    public boolean update(BaseAuditVersioning editedEntity, String user,String idservice) {
        editedEntity.setLastModBy(user);
        editedEntity.setLastModDate(new Date());
        editedEntity.setService(idservice);
        getCurrentSession().update(editedEntity);        
        return true;
    }
    
    @Override
    public int count(DtoParamPaging param) {
        
//        Criteria count = getCriteria(param);
//        count.setProjection(Projections.rowCount());
//        Long total = (Long) count.uniqueResult();    
//        
//        return total.intValue();
        
        Criteria c = getCriteria(param);
        c.setFirstResult(0);
        c.setMaxResults(0);
        c.setProjection(Projections.rowCount());
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Long resultCount = (Long)c.uniqueResult();
        Integer total = Integer.parseInt(Long.toString(resultCount));
        return total;
    }

    @Override
    public Criteria getCriteria(DtoParamPaging param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
