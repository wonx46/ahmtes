/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author achmad.ha
 */
public interface PlmDao <T,K extends Serializable>{
 
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
    boolean delete(T domain);
    
    boolean save(T domain);   
    boolean evict(Object entity);    
    boolean update(T domain);    
    void flush();    
    void clear();
    
}