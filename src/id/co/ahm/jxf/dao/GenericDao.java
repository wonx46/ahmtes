/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.dao;

import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.model.BaseAuditVersioning;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;

/**
 *
 * @author achmad.ha
 */
public interface GenericDao <T, K extends Serializable>{
    
    
    /**
     * might not hit db, return proxy placeholder, throw ex if no rec found
     */
    public T load(K key);
        
    /**
     * always hit db, might return null if no rec found
     */
    public T findOne(K key);
    public List<T> findAll();
    public boolean deleteById(K key);
    public boolean delete(BaseAuditVersioning entity);
    
    public boolean save(BaseAuditVersioning newEntity,String user,String idservice);   
    public boolean evict(Object entity);    
    public boolean update(BaseAuditVersioning editedEntity,String user,String idservice);    
    public void flush();    
    public void clear();
    
    public int count(DtoParamPaging param);
    public abstract Criteria getCriteria(DtoParamPaging param);
    
}
