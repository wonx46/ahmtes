package id.co.ahm.jx.sms.service;

/**
 *
 * @author Rachmat.Yulianto
 */
public interface SmsService {
    
    String sendSms(String msisdn, String message, String division, String batchName, String uploadBy, String serviceType);
}
