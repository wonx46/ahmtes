package id.co.ahm.jx.sms.dao.impl;

import id.co.ahm.jx.sms.dao.SmsDao;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rachmat.Yulianto
 */
@Repository("smsDao")
public class SmsDaoImpl extends DefaultHibernateDao<Object, Serializable> implements SmsDao{

    @Override
    public String getConfigSms() {
        Query query = getCurrentSession().createSQLQuery("SELECT VITEMDESC AS END_POINT " +
                                                         "FROM AHMMOERP_DTLSETTINGS " +
                                                         "WHERE RSET_VID = '_SMS_SETTING' " +
                                                         "AND VITEMCODE = 'END_POINT'")
                                                         .addScalar("END_POINT", StringType.INSTANCE);
        List<String> rows = query.list();
        String epoint = null;
        if (rows != null && !rows.isEmpty()) {
            for (String row : rows) {
                epoint = row;
            }
        }
        return epoint;
    }
    
}
