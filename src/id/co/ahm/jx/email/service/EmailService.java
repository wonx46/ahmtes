/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.email.service;

/**
 *
 * @author achmad.ha
 */
public interface EmailService {
    
    public String callProcSendMail(String subject, String emailFrom, String emailTo, 
            String emailCcs, String content);
    
    public String sendMailWithAttachment(String subject, String emailFrom, String emailTo, String content, byte[] file, String filename, String mimeType);
    
    public String sendMailWithOutAttachment(String subject, String emailFrom, String emailTo, String content);
  
}
