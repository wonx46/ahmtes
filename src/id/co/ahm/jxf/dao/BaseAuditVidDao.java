/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.dao;

import id.co.ahm.jxf.model.vid.BaseAuditVidImpl;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author achmad.ha
 */
public interface BaseAuditVidDao<T, K extends Serializable> {

    /**
     * might not hit db, return proxy placeholder, throw ex if no rec found
     */
    T load(K key);

    /**
     * always hit db, might return null if no rec found
     */
    T findOne(K key);

    List<T> findAll();

    boolean deleteById(K key);

    boolean delete(BaseAuditVidImpl entity);

    boolean save(BaseAuditVidImpl newEntity, String user, String idservice);

    boolean evict(Object entity);

    boolean update(BaseAuditVidImpl editedEntity, String user, String idservice);

    void flush();

    void clear();
}
