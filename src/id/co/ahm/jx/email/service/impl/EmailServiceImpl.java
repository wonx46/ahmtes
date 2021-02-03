package id.co.ahm.jx.email.service.impl;


import id.co.ahm.jx.email.dao.EmailDao;
import id.co.ahm.jx.email.service.EmailService;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mailService")
public class EmailServiceImpl implements EmailService {

    static Logger logger = Logger.getLogger(EmailServiceImpl.class);
    
    @Autowired
    @Qualifier("emailDao")
    private EmailDao emailDao;

    private JavaMailSenderImpl mailSender;

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
    
    @Override
    public String callProcSendMail(String subject, String emailFrom, String emailTo, 
            String emailCcs, String content) {
        return (emailDao.callProcSendMail(subject, emailFrom, emailTo, emailCcs, content));
    }

    public EmailDao getEmailDao() {
        return emailDao;
    }

    public void setEmailDao(EmailDao emailDao) {
        this.emailDao = emailDao;
    }

    @Override
    public String sendMailWithAttachment(String subject, String emailFrom, String emailTo, String content, byte[] file, String filename, String mimeType) {
        String result = null;
        String host = /*"localhost";*/ emailDao.getConfigSmtp();
        String port = /*"25";*/ emailDao.getConfigPortSmtp();
        try {
            
            Properties prop = System.getProperties();
            prop.setProperty("mail.smtp.host", host);
            prop.setProperty("mail.smtp.port", port);
            //if using gmail uncomment below code
            //prop.setProperty("mail.smtp.auth", "true");
            //prop.setProperty("mail.smtp.starttls.enable", "true");
            //comment below code if not need authentication
            Session session = Session.getDefaultInstance(prop);
            // If email need authentication (using gmail) uncomment below code
//            final String from = "ahm@localhost";
//            final String pass = "password";
//            Session session = Session.getDefaultInstance(prop,
//                    new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(from, pass);
//                }
//            });

            MimeMessage message = new MimeMessage(session);
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
            message.addHeaderLine("method=REQUEST");
            message.addHeaderLine("charset=UTF-8");
            message.addHeaderLine("component=VEVENT");
            //set from and to
            message.setFrom(new InternetAddress(emailFrom));
            message.setSender(new InternetAddress(emailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject(subject);
            //body part for attachment
            BodyPart attachmentBodyPart = new MimeBodyPart();
//            DataSource source = null;
//            if(file != null){
                DataSource source = new ByteArrayDataSource(file, mimeType);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(filename);
//            }
            
            //body part for message
            BodyPart messagePart = new MimeBodyPart();
            // Fill the message
            messagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(content, "text/html; charset=utf-8")));// very important
            // Create a Multipart
            Multipart multipart = new MimeMultipart();
             //Adding Part
            if(attachmentBodyPart != null)
            multipart.addBodyPart(attachmentBodyPart);
            if(messagePart != null)
            multipart.addBodyPart(messagePart);
            // Put parts in message
            message.setContent(multipart);
            // send message
            Transport.send(message);
            result = "Success";
            
        } catch (MessagingException ex) {
            ex.printStackTrace();
            result = ex.getMessage();
            
        } catch (Exception me) {
            me.printStackTrace();
            result = me.getMessage();
        } finally {
        }
        
        return result;
    }

    @Override
    public String sendMailWithOutAttachment(String subject, String emailFrom, String emailTo, String content) {
        String result = null;
        String host = emailDao.getConfigSmtp();
        String port = emailDao.getConfigPortSmtp();
        try {
            
            Properties prop = System.getProperties();
            prop.setProperty("mail.smtp.host", host);
            prop.setProperty("mail.smtp.port", port);
            //if using gmail uncomment below code
            //prop.setProperty("mail.smtp.auth", "true");
            //prop.setProperty("mail.smtp.starttls.enable", "true");
            //comment below code if not need authentication
            Session session = Session.getDefaultInstance(prop);
            // If email need authentication (using gmail) uncomment below code
//            final String from = "ahm@localhost.com";
//            final String pass = "password";
//            Session session = Session.getDefaultInstance(prop,
//                    new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(from, pass);
//                }
//            });

            MimeMessage message = new MimeMessage(session);
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
            message.addHeaderLine("method=REQUEST");
            message.addHeaderLine("charset=UTF-8");
            message.addHeaderLine("component=VEVENT");
            //set from and to
            message.setFrom(new InternetAddress(emailFrom));
            message.setSender(new InternetAddress(emailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject(subject);
            //body part for message
            BodyPart messagePart = new MimeBodyPart();
            // Fill the message
            messagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(content, "text/html; charset=utf-8")));// very important
            // Create a Multipart
            Multipart multipart = new MimeMultipart("alternative");
             //Adding Part
            if(messagePart != null)
            multipart.addBodyPart(messagePart);
            // Put parts in message
            message.setContent(multipart);
            // send message
            Transport.send(message);
            result = "Success";
        } catch (MessagingException ex) {
            ex.printStackTrace();
            result = ex.getMessage();
        } catch (Exception me) {
            me.printStackTrace();
            result = me.getMessage();
        }
        
        return result;
    }
}
