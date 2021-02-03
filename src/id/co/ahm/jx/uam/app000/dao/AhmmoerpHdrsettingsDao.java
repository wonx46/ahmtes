/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.dao;

import id.co.ahm.jx.uam.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.uam.app000.model.AhmmoerpHdrsettings;
import id.co.ahm.jx.uam.app000.vo.SortField;
import id.co.ahm.jxf.dao.DefaultDao;
import java.util.List;

/**
 *
 * @author susanto
 */
public interface AhmmoerpHdrsettingsDao extends DefaultDao<AhmmoerpHdrsettings, String> {

    AhmmoerpHdrsettings retrieveSettingById(String settingId);

    AhmmoerpDtlsettings retrieveDetailSetting(String headerId, String detailId);

    List<AhmmoerpDtlsettings> retrieveDetailSettings(String headerId);

    List<AhmmoerpDtlsettings> retrieveDetailSettings(String headerId, SortField sort);
    
}
