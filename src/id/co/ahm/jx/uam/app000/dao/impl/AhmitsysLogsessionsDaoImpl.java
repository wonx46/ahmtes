/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.dao.impl;

import id.co.ahm.jx.uam.app000.dao.AhmitsysLogsessionsDao;
import id.co.ahm.jx.uam.app000.model.AhmitsysLogsessions;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author okky.ms
 */
@Repository("ahmitsysLogsessionsDao")
public class AhmitsysLogsessionsDaoImpl extends DefaultHibernateDao<AhmitsysLogsessions, String>
        implements AhmitsysLogsessionsDao{
    
}
