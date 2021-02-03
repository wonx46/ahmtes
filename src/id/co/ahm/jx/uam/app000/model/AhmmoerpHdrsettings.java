/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author susanto
 */
@Table(name = "AHMMOERP_HDRSETTINGS")
@Entity
public class AhmmoerpHdrsettings extends DefaultEntityImpl implements Serializable {

    private static final long serialVersionUID = -7517762039666598692L;
    @Id
    @Column(name = "VID")
    private String vid;
    @Column(name = "VDESCID")
    private String vdescid;
    
    @OneToMany(mappedBy = "headerSetting", cascade = CascadeType.ALL)
    private List<AhmmoerpDtlsettings> detailSettings;

    public String getVdescid() {
        return vdescid;
    }

    public void setVdescid(String vdescid) {
        this.vdescid = vdescid;
    }

    public List<AhmmoerpDtlsettings> getDetailSettings() {
        return detailSettings;
    }

    public void setDetailSettings(List<AhmmoerpDtlsettings> detailSettings) {
        this.detailSettings = detailSettings;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}
