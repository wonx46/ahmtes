/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.model;

import id.co.ahm.jx.uam.app000.model.AhmipuamMstmenus;
import id.co.ahm.jxf.model.vid.BaseAuditVidVersioning;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author achmad.ha
 */
@Entity
@Table(name = "AHMIPSYS_HISAPPS")
public class AhmipsysHstapps extends BaseAuditVidVersioning implements Serializable{
    
    @Column(name="MMENUS_IIDMENUS",length=64,nullable = false)
    private long mmenusIidmenus;
    
    @ManyToOne(targetEntity=AhmipuamMstmenus.class,fetch= FetchType.LAZY)
    @JoinColumn(name="MMENUS_IIDMENUS",referencedColumnName="IINTERNALID",insertable=false,updatable=false)
    private AhmipuamMstmenus ahmipuamMstmenus;
    
    @Column(name="VUSERNAME",length=64,nullable = false)
    private String vusername;
        
    @Column(name="DTIME",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtime;

    public long getMmenusIidmenus() {
        return mmenusIidmenus;
    }

    public void setMmenusIidmenus(long mmenusIidmenus) {
        this.mmenusIidmenus = mmenusIidmenus;
    }

    public AhmipuamMstmenus getAhmipuamMstmenus() {
        return ahmipuamMstmenus;
    }

    public void setAhmipuamMstmenus(AhmipuamMstmenus ahmipuamMstmenus) {
        this.ahmipuamMstmenus = ahmipuamMstmenus;
    }

    public String getVusername() {
        return vusername;
    }

    public void setVusername(String vusername) {
        this.vusername = vusername;
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }
}
