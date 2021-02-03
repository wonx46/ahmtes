/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.service.impl;

import id.co.ahm.jxf.dao.DbUtilsDao;
import id.co.ahm.jxf.service.DbUtilsService;
import id.co.ahm.jxf.service.DbUtilsService.ConvertedResult;
import id.co.ahm.jxf.util.AhmStringUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author achmad.ha
 */
@Service("dbUtilsService")
public class DbUtilsServiceImpl implements DbUtilsService{
    
    @Autowired
    @Qualifier("dbUtilsDao")
    private DbUtilsDao dbUtilsDao;

    @Override
    @Transactional(readOnly = true)
    public Object hqlUniqueResult(String hql, Map<String, Object> params) {
        return dbUtilsDao.hqlUniqueResult(hql, params);
    }

    @Override
    @Transactional(readOnly = true)
    public Object sqlUniqueResult(String sql, Map<String, Object> params) {
        return dbUtilsDao.sqlUniqueResult(sql, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List hqlResults(String hql, Integer first, Integer pageSize, Map<String, Object> params) {
        return dbUtilsDao.hqlResults(hql, first, pageSize, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List sqlResults(String sql, Integer first, Integer pageSize, Map<String, Object> params) {
        return dbUtilsDao.sqlResults(sql, first, pageSize, params);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ConvertedResult getStringDescFromEntityId(Class persistentClass, Object id, AhmStringUtil stringUtils)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return dbUtilsDao.getStringDescFromEntityId(persistentClass, id, stringUtils);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getStringDescFromEntity(Object entity, AhmStringUtil stringUtils)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object id = getEntityId(entity);
        return dbUtilsDao.getStringDescFromEntity(id, entity, stringUtils);
    }

    @Override
    public Object getEntityId(Object entity) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        if (entity == null) {
            return null;
        }

        AhmStringUtil stringUtils = AhmStringUtil.getInstance();
        Class<Object> clazz = (Class<Object>) entity.getClass();
        Field[] fields = clazz.getDeclaredFields();



        if (fields != null) {
            for (int i = 0; i < fields.length; i++) {
                if (fieldIsAnnotatedWithId(fields[i])) {
                    String fieldName = fields[i].getName();
                    String methodName = stringUtils
                            .getEntityGetterMethodName(fieldName);

                    Method method = clazz.getDeclaredMethod(methodName);
                    return method.invoke(entity);

                } else if (fieldIsAnnotatedWithEmbeddable(fields[i])) {
                    String fieldName = fields[i].getName();
                    String methodName = stringUtils
                            .getEntityGetterMethodName(fieldName);

                    Method method = clazz.getDeclaredMethod(methodName);
                    return method.invoke(entity);
                }
            }
        }

        return null;
    }

    private boolean fieldIsAnnotatedWithEmbeddable(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations != null) {
            for (int j = 0; j < annotations.length; j++) {
                if (annotations[j].annotationType().equals(EmbeddedId.class)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean fieldIsAnnotatedWithId(Field field) {
        boolean isColumnId = false;
//		logger.debug("field = " + fields[i].getName());
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations != null) {
            for (int j = 0; j < annotations.length; j++) {
//				logger.debug("annotation = "
//						+ annotations[j].annotationType().getName());
                if (annotations[j].annotationType().equals(Id.class)) {
                    isColumnId = true;
                }
            }
        }
        return isColumnId;
    }

    public DbUtilsDao getDbUtilsDao() {
        return dbUtilsDao;
    }

    public void setDbUtilsDao(DbUtilsDao dbUtilsDao) {
        this.dbUtilsDao = dbUtilsDao;
    }
    
}
