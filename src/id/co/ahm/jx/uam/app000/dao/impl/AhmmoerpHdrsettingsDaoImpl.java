/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.dao.impl;


import id.co.ahm.jx.uam.app000.dao.AhmmoerpHdrsettingsDao;
import id.co.ahm.jx.uam.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.uam.app000.model.AhmmoerpHdrsettings;
import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jx.uam.app000.vo.SortField;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author susanto
 */
@Repository("ahmmoerpHdrsettingsDao")
public class AhmmoerpHdrsettingsDaoImpl extends DefaultHibernateDao<AhmmoerpHdrsettings, String> implements AhmmoerpHdrsettingsDao {

    @Override
    public AhmmoerpHdrsettings retrieveSettingById(String settingId) {
        return findOne(settingId);
    }

    @Override
    public AhmmoerpDtlsettings retrieveDetailSetting(String headerId, String detailId) {
        
        String hql = "select det from AhmmoerpDtlsettings det, AhmmoerpHdrsettings head"
                + " where det.vitemcode = :detId and "
                + " head.vid = :headId and "
                + " det.headerSetting.vid = head.vid";

        Query query = getCurrentSession().createQuery(hql);

        query.setParameter("detId", detailId);
        query.setParameter("headId", headerId);

        return (AhmmoerpDtlsettings) query.uniqueResult();

    }

    @Override
    public List<AhmmoerpDtlsettings> retrieveDetailSettings(String headerId) {
        String hql = "select det from AhmmoerpDtlsettings det "
                + " where "
                + " det.headerSetting.vid = :headId";

        Query query = getCurrentSession().createQuery(hql);

        query.setParameter("headId", headerId);

        return query.list();

    }

    @Override
    public List<AhmmoerpDtlsettings> retrieveDetailSettings(String headerId,
            SortField sort) {
        String hql = "select det from AhmmoerpDtlsettings det "
                + " where "
                + " det.headerSetting.vid = :headId";

        if (sort == null || !org.apache.commons.lang3.StringUtils.isBlank(sort.getField())
                || sort.getOrder() != SortField.ORDER.NONE) {
            hql = hql + " order by " + sort.getField() + " " + sort.getOrder().name();
        }

        Query query = getCurrentSession().createQuery(hql);

        query.setParameter("headId", headerId);

        return query.list();

    }
}
