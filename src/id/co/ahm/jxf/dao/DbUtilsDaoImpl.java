/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.dao;

import id.co.ahm.jxf.service.DbUtilsService.ConvertedResult;
import id.co.ahm.jxf.util.AhmStringUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author achmad.ha
 */
@Repository("dbUtilsDao")
public class DbUtilsDaoImpl extends DefaultHibernateDao<Object, Serializable> implements DbUtilsDao {
    
    private static Logger logger = Logger.getLogger(DbUtilsDaoImpl.class);

    @Override
    public int sqlExecute(String sql, Map<String, Object> params) {
        SQLQuery query = getCurrentSession().createSQLQuery(
                sql);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.executeUpdate();
    }

    @Override
    public List hqlResults(String hql,
            Integer first, Integer pageSize,
            Map<String, Object> params) {

        Query query = getCurrentSession().createQuery(hql);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        if (first != null) {
            query.setFirstResult(first);
        }
        if (pageSize != null) {
            query.setMaxResults(pageSize);
        }

        try {
            List result =
                    query.list();
            return result;

        } catch (Exception ex) {
            logger.error("Error querying hql data [" + ex.getMessage() + "]", ex);
            throw new RuntimeException("Error querying hql data [" + ex.getMessage() + "]", ex);
        }
    }

    @Override
    public List sqlResults(String sql, Integer first, Integer pageSize, Map<String, Object> params) {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);
        if (first != null) {
            query.setFirstResult(first);
        }
        if (pageSize != null) {
            query.setMaxResults(pageSize);
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        try {
            return query.list();
        } catch (Exception ex) {
            logger.error("Error querying search sql data [" + ex.getMessage() + "]", ex);
            throw new RuntimeException("Error querying sql data [" + ex.getMessage() + "]", ex);
        }

    }

    @Override
    public Object hqlUniqueResult(String hql, Map<String, Object> params) {
        Query query = getCurrentSession().createQuery(hql);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        try {
            Number res = (Number) query.uniqueResult();
            return res.longValue();
        } catch (Exception ex) {
            logger.error("Error querying unique result hql [" + ex.getMessage() + "]", ex);
            throw new RuntimeException("Error querying unique result hql [" + ex.getMessage() + "]", ex);
        }
    }

    @Override
    public Object sqlUniqueResult(String sql, Map<String, Object> params) {
        SQLQuery query = getCurrentSession().createSQLQuery(sql);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        try {
            Object res = query.uniqueResult();
            if (res instanceof Number) {
                return ((Number) res).longValue();
            } else {
                return res;
            }
        } catch (Exception ex) {
            logger.error("Error querying unique result sql [" + ex.getMessage() + "]", ex);
            throw new RuntimeException("Error querying unique result sql [" + ex.getMessage() + "]", ex);
        }
    }

    public ConvertedResult getStringDescFromEntityId(Class persistentClass, Object id, AhmStringUtil stringUtils)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (id != null) {
            Session session = getCurrentSession();
            Object dbEntity = session.get(persistentClass, (Serializable) id);
            try {
                String result = stringUtils
                        .convertEntityToString(dbEntity, dbEntity);
                if (dbEntity != null) {
                    session.evict(dbEntity);
                }
                ConvertedResult cres = new ConvertedResult();
                cres.entity = dbEntity;
                cres.entityDesc = result;
                return cres;

            } catch (Exception ex) {
                if (dbEntity != null) {
                    session.evict(dbEntity);
                }
                
                throw new RuntimeException(ex);
            }
        } else {
            return null;
        }
    }

    @Override
    public String getStringDescFromEntity(Object entityId, Object entity, AhmStringUtil stringUtils)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if (entity != null) {
            Session session = getCurrentSession();
            Object dbEntity = session.get(entity.getClass(), (Serializable) entityId);
            try {
                String strEditedEntity = stringUtils
                        .convertEntityToString(entity, dbEntity);

                session.evict(entity);
                session.evict(dbEntity);
                return strEditedEntity;

            } catch (Exception ex) {
                if (dbEntity != null) {
                    session.evict(dbEntity);
                }
                if (entity != null) {
                    session.evict(entity);
                }
                
                throw new RuntimeException(ex);
            }
        } else {
            return null;
        }
    }
}
