/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.dao;


import id.co.ahm.jxf.model.vid.BaseAuditVidVersioning;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author AFI
 */
public interface GenericVidDao <T, K extends Serializable>{
    
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
    public boolean delete(BaseAuditVidVersioning entity);
    
    public boolean save(BaseAuditVidVersioning newEntity,String user,String idservice);   
    public boolean evict(Object entity);    
    public boolean update(BaseAuditVidVersioning editedEntity,String user,String idservice);    
    public void flush();    
    public void clear();
    
    
}
