/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.model.base;

import id.co.ahm.jxf.model.vid.BaseAuditVidImpl;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDGenerator;

/**
 *
 * @author george
 */
public class UUIDCustomGenerator extends UUIDGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        if (object == null) {
            throw new HibernateException(new NullPointerException());
        }

        if ((((BaseAuditVidImpl) object).getVid()) == null) {
            Serializable id = super.generate(session, object);
            return id;
        } else {
            return ((BaseAuditVidImpl) object).getVid();

        }
    }
}
