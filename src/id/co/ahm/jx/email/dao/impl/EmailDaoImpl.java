/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.email.dao.impl;

import id.co.ahm.jx.email.dao.EmailDao;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hendra.fs
 */
@Repository("emailDao")
public class EmailDaoImpl extends DefaultHibernateDao<Object, Serializable> implements EmailDao {

    private static Logger logger = Logger.getLogger(EmailDaoImpl.class);

    @Override
    public String callProcSendMail(String subject, String emailFrom, String emailTo, String emailCcs, String content) {
        SendMailFromProcedure sendMail = new SendMailFromProcedure(emailFrom, subject, emailTo, emailCcs, content);
        getCurrentSession().doWork(sendMail);

        return sendMail.getResult();
    }

    @Override
    public String getConfigSmtp() {
        Query query = getCurrentSession().createSQLQuery("SELECT VITEMNAME || '.' || VITEMDESC AS SMTP " +
                                                         "FROM AHMMOERP_DTLSETTINGS " +
                                                         "WHERE RSET_VID = '_EMAIL_SETTING' " +
                                                         "AND VITEMCODE = 'SMTP_HOST'")
                                                         .addScalar("SMTP", StringType.INSTANCE);
        List<String> rows = query.list();
        String smtp = null;
        if (rows != null && !rows.isEmpty()) {
            for (String row : rows) {
                smtp = row;
            }
        }
        return smtp;
    }

    @Override
    public String getConfigPortSmtp() {
        Query query = getCurrentSession().createSQLQuery("SELECT VITEMNAME " +
                                                         "FROM AHMMOERP_DTLSETTINGS " +
                                                         "WHERE RSET_VID = '_EMAIL_SETTING' " +
                                                         "and VITEMCODE = 'SMTP_PORT_NOPWD'")
                                                         .addScalar("VITEMNAME", StringType.INSTANCE);
        List<String> rows = query.list();
        String port = null;
        if (rows != null && !rows.isEmpty()) {
            for (String row : rows) {
                port = row;
            }
        }
        return port;
    }
}
