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
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMIPSYS_MSTCAPTCHAS")
public class AhmipsysMstcaptchas extends BaseAuditVidVersioning 
        implements Serializable{    
    
    @Column(name="VIDCAPT",length=64,nullable = false)
    private String vidcapt;
    
    @Column(name="VCAPTVAL",length=10,nullable = false)
    private String vcaptval;

    public String getVidcapt() {
        return vidcapt;
    }

    public void setVidcapt(String vidcapt) {
        this.vidcapt = vidcapt;
    }

    public String getVcaptval() {
        return vcaptval;
    }

    public void setVcaptval(String vcaptval) {
        this.vcaptval = vcaptval;
    }
    
    
    
    
}
