/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.email.dao;

/**
 *
 * @author hendra.fs test
 */
public interface EmailDao {    
        
    public String callProcSendMail(String subject, String emailFrom, String emailTo, 
            String emailCcs, String content);
    
    public String getConfigSmtp();
    
    public String getConfigPortSmtp();
}
