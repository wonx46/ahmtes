/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.service;

import id.co.ahm.jxf.util.AhmStringUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author achmad.ha
 */
public interface DbUtilsService {
    
    Object hqlUniqueResult(String hql, Map<String, Object> params);

    Object sqlUniqueResult(String sql, Map<String, Object> params);

    List hqlResults(String hql, Integer first, Integer pageSize, Map<String, Object> params);

    List sqlResults(String sql, Integer first, Integer pageSize, Map<String, Object> params);

    String getStringDescFromEntity(Object entity, AhmStringUtil stringUtils)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    ConvertedResult getStringDescFromEntityId(Class persistentClass, Object id, AhmStringUtil stringUtils)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Object getEntityId(Object entity) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException;

    public static class ConvertedResult {
        public Object entity;
        public String entityDesc;
    }
    
}
