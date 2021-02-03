/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.dao.impl;

import id.co.ahm.jx.sys.app000.dao.AhmmoerpTxnrunnumsDao;
import id.co.ahm.jx.sys.app000.model.AhmmoerpTxnrunnums;
import id.co.ahm.jx.sys.app000.model.AhmmoerpTxnrunnumsPk;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author okky.ms
 */
@Repository("ahmmoerpTxnrunnumsDao")
public class AhmmoerpTxnrunnumsDaoImpl extends DefaultHibernateDao<AhmmoerpTxnrunnums, AhmmoerpTxnrunnumsPk>
        implements AhmmoerpTxnrunnumsDao{
    
}
