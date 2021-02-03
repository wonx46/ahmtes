/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.service;

import id.co.ahm.jx.uam.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.uam.app000.model.AhmmoerpHdrsettings;
import id.co.ahm.jx.uam.app000.vo.SortField;
import java.util.List;

/**
 *
 * @author achmad.ha
 */
public interface AhmmoerpHdrsettingsService {
    
    AhmmoerpHdrsettings retrieveSettingById(String settingId);

    AhmmoerpDtlsettings retrieveDetailSetting(String headerId, String detailId);

    List<AhmmoerpDtlsettings> retrieveDetailSetting(String headerId);

    List<AhmmoerpDtlsettings> retrieveDetailSetting(String headerId, SortField sort);
    
}
