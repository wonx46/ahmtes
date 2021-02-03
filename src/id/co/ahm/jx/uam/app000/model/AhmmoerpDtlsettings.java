/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author susanto
 */
@Table(name = "AHMMOERP_DTLSETTINGS")
@Entity
public class AhmmoerpDtlsettings extends DefaultEntityImpl implements Serializable {

    private static final long serialVersionUID = 2136084093840500285L;
    @Id
    @ManyToOne(targetEntity = AhmmoerpHdrsettings.class)
    @JoinColumn(name = "RSET_VID", referencedColumnName = "VID")
    private AhmmoerpHdrsettings headerSetting;
    
    @Id
    @Column(name = "VITEMCODE")
    private String vitemcode;
    
    @Column(name = "VITEMNAME")
    private String vitemname;
    
    @Column(name = "VITEMDESC")
    private String vitemdesc;
    
    @Column(name = "BVALID")
    private String bvalid;

    public AhmmoerpHdrsettings getHeaderSetting() {
        return headerSetting;
    }

    public void setHeaderSetting(AhmmoerpHdrsettings headerSetting) {
        this.headerSetting = headerSetting;
    }

    public String getVitemcode() {
        return vitemcode;
    }

    public void setVitemcode(String vitemcode) {
        this.vitemcode = vitemcode;
    }

    public String getVitemdesc() {
        return vitemdesc;
    }

    public void setVitemdesc(String vitemdesc) {
        this.vitemdesc = vitemdesc;
    }

    public String getVitemname() {
        return vitemname;
    }

    public void setVitemname(String vitemname) {
        this.vitemname = vitemname;
    }

    public String getBvalid() {
        return bvalid;
    }

    public void setBvalid(String bvalid) {
        this.bvalid = bvalid;
    }
}
