/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.service.impl;

import id.co.ahm.jx.uam.app000.dao.AhmmoerpHdrsettingsDao;
import id.co.ahm.jx.uam.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.uam.app000.model.AhmmoerpHdrsettings;
import id.co.ahm.jx.uam.app000.service.AhmmoerpHdrsettingsService;
import id.co.ahm.jx.uam.app000.vo.SortField;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author susanto
 */
@Service("ahmmoerpHdrsettingsService")
public class AhmmoerpHdrsettingsServiceImpl implements AhmmoerpHdrsettingsService {

    @Autowired
    @Qualifier("ahmmoerpHdrsettingsDao")
    private AhmmoerpHdrsettingsDao ahmmoerpHdrsettingsDao;

    @Override
    @Transactional(readOnly = true)
    public AhmmoerpHdrsettings retrieveSettingById(String settingId) {
        return ahmmoerpHdrsettingsDao.retrieveSettingById(settingId);
    }

    @Override
    @Transactional(readOnly = true)
    public AhmmoerpDtlsettings retrieveDetailSetting(String headerId, String detailId) {
        return ahmmoerpHdrsettingsDao.retrieveDetailSetting(headerId, detailId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AhmmoerpDtlsettings> retrieveDetailSetting(String headerId) {
        return ahmmoerpHdrsettingsDao.retrieveDetailSettings(headerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AhmmoerpDtlsettings> retrieveDetailSetting(String headerId,
            SortField sort) {
        return ahmmoerpHdrsettingsDao.retrieveDetailSettings(headerId, sort);
    }

    public void setAhmmoerpHdrsettingsDao(
            AhmmoerpHdrsettingsDao ahmmoerpHdrsettingsDao) {
        this.ahmmoerpHdrsettingsDao = ahmmoerpHdrsettingsDao;
    }
}
