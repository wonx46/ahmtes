/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sms.service.impl;

import id.co.ahm.jx.sms.dao.SmsDao;
import id.co.ahm.jx.sms.model.SmsPush;
import id.co.ahm.jx.sms.model.SmsPushSoap;
import id.co.ahm.jx.sms.service.SmsService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rachmat.Yulianto
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService{
    
    @Autowired
    @Qualifier("smsDao")
    private SmsDao smsDao;
    
    @Override
    public String sendSms(String msisdn, String message, String division, String batchName, String uploadBy, String serviceType) {
        try {
            URL uri = new URL(smsDao.getConfigSms());
            SmsPush service = new SmsPush(uri);
            SmsPushSoap port = service.getSmsPushSoap();
            
            String response = port.mgmSendMessage(msisdn, message,division, batchName, uploadBy, serviceType);
//            System.out.println("Response SMS =>"+response);
            return response;
        } catch (MalformedURLException ex) {
           ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public SmsDao getSmsDao() {
        return smsDao;
    }

    public void setSmsDao(SmsDao smsDao) {
        this.smsDao = smsDao;
    }
    
}
