/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author achmad.ha
 */
public class ScadaHibernateDao<T, K extends Serializable> implements ScadaDao<T, K>{
    @SuppressWarnings("unchecked")
    protected Class persistentClass;

    @Autowired
    @Qualifier("sessionFactoryScada")
    protected SessionFactory sessionFactoryScada;

    @SuppressWarnings("unchecked")
    public ScadaHibernateDao() {
        persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactoryScada.getCurrentSession();
    }

    @Override
    public T load(K key) {
        return (T) getCurrentSession().load(persistentClass, key);
    }

    @Override
    public T findOne(K key) {
        return (T) getCurrentSession().get(persistentClass, key);
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
    public boolean delete(T domain) {
        getCurrentSession().delete(domain);
        return true;
    }

    @Override
    public boolean save(T domain) {
        getCurrentSession().save(domain);
        return true;
    }

    @Override
    public boolean evict(Object entity) {
        getCurrentSession().evict(entity);
        return true;
    }

    @Override
    public boolean update(T domain) {
        getCurrentSession().update(domain);
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
}
