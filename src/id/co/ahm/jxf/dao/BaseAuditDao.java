/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.dao;

import id.co.ahm.jxf.model.base.BaseAudit;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author achmad.ha
 */
public interface BaseAuditDao <T, K extends Serializable>{
    
    /**
     * might not hit db, return proxy placeholder, throw ex if no rec found
     */
    public T load(K key);
        
    /**
     * always hit db, might return null if no rec found
     */
    T findOne(K key);
    List<T> findAll();
    boolean deleteById(K key);
    boolean delete(BaseAudit entity);
    
    boolean save(BaseAudit newEntity,String user,String idservice);   
    boolean evict(Object entity);    
    boolean update(BaseAudit editedEntity,String user,String idservice);    
    void flush();    
    void clear();
    
}
