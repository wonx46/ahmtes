/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.model;

import id.co.ahm.jxf.model.vid.BaseAuditVidVersioning;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author achmad.ha
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMJXSYS_LOGSERVICES")
public class AhmjxsysLogservices extends BaseAuditVidVersioning implements Serializable {
    
    @Column(name = "VURL", length = 512, nullable = false)
    private String vurl;
    
    @Column(name = "VMETHOD", length = 10, nullable = false)
    private String vmethod;
    
    @Column(name = "VIPADDRESS", length = 30, nullable = false)
    private String vipaddress;
    
    @Column(name = "VAGENT", length = 255, nullable = false)
    private String vagent;
    
    @Column(name = "IEXECTIME",  nullable = false )
    private long iexectime;
    
    @Column(name = "VUSERNAME", length = 30, nullable = false)
    private String vusername;
    
    @Column(name = "VORIGIN", length = 255)
    private String vorigin;

    public String getVurl() {
        return vurl;
    }

    public void setVurl(String vurl) {
        this.vurl = vurl;
    }

    public String getVmethod() {
        return vmethod;
    }

    public void setVmethod(String vmethod) {
        this.vmethod = vmethod;
    }    

    public String getVipaddress() {
        return vipaddress;
    }

    public void setVipaddress(String vipaddress) {
        this.vipaddress = vipaddress;
    }

    public long getIexectime() {
        return iexectime;
    }

    public void setIexectime(long iexectime) {
        this.iexectime = iexectime;
    }

    public String getVusername() {
        return vusername;
    }

    public void setVusername(String vusername) {
        this.vusername = vusername;
    }

    public String getVagent() {
        return vagent;
    }

    public void setVagent(String vagent) {
        this.vagent = vagent;
    }

    public String getVorigin() {
        return vorigin;
    }

    public void setVorigin(String vorigin) {
        this.vorigin = vorigin;
    }
    
    
}
