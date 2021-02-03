/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.service.impl;

import id.co.ahm.jx.sys.app000.dao.AhmmoerpTxnrunnumsDao;
import id.co.ahm.jx.sys.app000.model.AhmmoerpTxnrunnums;
import id.co.ahm.jx.sys.app000.model.AhmmoerpTxnrunnumsPk;
import id.co.ahm.jx.sys.app000.service.AhmmoerpTxnrunnumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author okky.ms
 */
@Service(value = "ahmmoerpTxnrunnumsService")
@Transactional(readOnly = true)
public class AhmmoerpTxnrunnumsServiceImpl implements AhmmoerpTxnrunnumsService{
    
    @Autowired
    @Qualifier("ahmmoerpTxnrunnumsDao")
    private AhmmoerpTxnrunnumsDao ahmmoerpTxnrunnumsDao;

    @Override
    @Transactional
    public int getNewValue(String vidname, String vreset, String user, String idservice) {
        int newValue;
        System.out.println("runnummmm: "+vidname+vreset);
        AhmmoerpTxnrunnumsPk ahmmoerpTxnrunnumsPk = new AhmmoerpTxnrunnumsPk();
        ahmmoerpTxnrunnumsPk.setVidname(vidname);
        ahmmoerpTxnrunnumsPk.setVreset(vreset);
        AhmmoerpTxnrunnums ahmmoerpTxnrunnums = ahmmoerpTxnrunnumsDao.findOne(ahmmoerpTxnrunnumsPk);
        if (ahmmoerpTxnrunnums == null) { // Insert new running number
            newValue = 1;
            ahmmoerpTxnrunnums = new AhmmoerpTxnrunnums();
            ahmmoerpTxnrunnums.setAhmmoerpTxnrunnumsPk(ahmmoerpTxnrunnumsPk);
            ahmmoerpTxnrunnums.setIvalue(newValue);
            ahmmoerpTxnrunnumsDao.save(ahmmoerpTxnrunnums, user);
        } else { // Update existing running number
            newValue = ahmmoerpTxnrunnums.getIvalue() + 1;
            ahmmoerpTxnrunnums.setIvalue(newValue);
            ahmmoerpTxnrunnumsDao.update(ahmmoerpTxnrunnums, user);
        }

        return newValue;
    }
    
}
