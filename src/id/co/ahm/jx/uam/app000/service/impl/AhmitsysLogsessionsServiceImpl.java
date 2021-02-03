/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.service.impl;

import id.co.ahm.jx.uam.app000.dao.AhmitsysLogsessionsDao;
import id.co.ahm.jx.uam.app000.model.AhmitsysLogsessions;
import id.co.ahm.jx.uam.app000.model.AhmmoerpDtlsettings;
import id.co.ahm.jx.uam.app000.service.AhmitsysLogsessionsService;
import id.co.ahm.jx.uam.app000.service.AhmmoerpHdrsettingsService;
import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author okky.ms
 */
@Service("ahmitsysLogsessionsService")
public class AhmitsysLogsessionsServiceImpl implements AhmitsysLogsessionsService{
    
    @Autowired
    @Qualifier("ahmitsysLogsessionsDao")
    private AhmitsysLogsessionsDao ahmitsysLogsessionsDao;
    
    @Autowired
    @Qualifier("ahmmoerpHdrsettingsService")
    private AhmmoerpHdrsettingsService ahmmoerpHdrsettingsService;
    
    protected void updateCreationInfo(DefaultEntityImpl entity, String user) {
        Date currentTime = new Date();
        entity.setCreateBy(user);
        entity.setCreateDate(currentTime);
    }
    
    @Override
    @Transactional(readOnly = false)
    public void insertLogSession(AhmitsysLogsessions ahmitsysLogsessions, String user) {
        updateCreationInfo(ahmitsysLogsessions, user);
        ahmitsysLogsessionsDao.save(ahmitsysLogsessions, user);
        ahmitsysLogsessionsDao.flush();
    }


    @Override
    public String generateLinkReport(String appId, String ldap, String supplierId,
            String reportName, String destTitle, String paramToPrint, String user, String fileType) {
        try {
            String link;
            AhmmoerpDtlsettings dtlSettings;
            UUID id = UUID.randomUUID();
            AhmitsysLogsessions logSession = new AhmitsysLogsessions();
            logSession.setVsessionid(id.toString().replace("-", ""));
            logSession.setVnamaldap(ldap);
            logSession.setVbusarea(supplierId);
            this.insertLogSession(logSession, appId);

            dtlSettings = ahmmoerpHdrsettingsService.retrieveDetailSetting("_SERVER_SETTING", "HOST_REPX");
            String host = dtlSettings.getVitemname() + "." + dtlSettings.getVitemdesc();
            dtlSettings = ahmmoerpHdrsettingsService.retrieveDetailSetting("_SERVER_SETTING", "REPORT_APP");
            String reportApps = dtlSettings.getVitemname() + dtlSettings.getVitemdesc();
            String report = "&report=" + reportName;
            String destType = "&destType=" + "file"; // Isinya selalu file
            String destName = "&destName=" + ""; // Nama file pdf report hasil generate , contoh : semoga_benar.pdf
            String type = "&filetype=" + fileType; // Extensi file report hasil generate , contoh : (pdf, spreadsheet, honda1, honda2)
            String prop = "&prop=" + "EXT"; // Koneksi database isinya : ahmps , sd , portal, EXT
            String reportSource = "&source=" + "pasti"; // pasti = Portal PASTI
            String vUserId = "&vUserId=" + user;
            String vModuleName = "&vModuleName=" + "0";
            String JSessionId = "&JSessionId=" + logSession.getVsessionid();

            link = "https://" + host + "/" + reportApps + destTitle.replace(" ", "+")
                    + report + destType + destName + type + prop + reportSource
                    + vUserId + vModuleName + JSessionId + paramToPrint;
            return link;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ("Error in generateLinkReport : " + ex.getMessage());
        }
    }
}
