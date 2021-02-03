/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author achmad.ha
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="AHMIPUAM_MSTROLES")
public class AhmipuamMstroles extends DefaultEntityImpl implements Serializable{    
    
    @Id
    @SequenceGenerator(name = "roleSeq", sequenceName = "SE_MSTROLES")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "roleSeq")
    @Column(name = "IINTERNALID")
    private Long iinternalid;
    
    @Column(name = "VROLENAME",unique = true)
    private String vrolename;
    
    @Column(name = "VROLEDESC")
    private String vroledesc;
    
    @Column(name = "VENABLEFLAG")
    private String venableflag;

    public Long getIinternalid() {
        return iinternalid;
    }

    public void setIinternalid(Long iinternalid) {
        this.iinternalid = iinternalid;
    }

    public String getVrolename() {
        return vrolename;
    }

    public void setVrolename(String vrolename) {
        this.vrolename = vrolename;
    }

    public String getVroledesc() {
        return vroledesc;
    }

    public void setVroledesc(String vroledesc) {
        this.vroledesc = vroledesc;
    }

    public String getVenableflag() {
        return venableflag;
    }

    public void setVenableflag(String venableflag) {
        this.venableflag = venableflag;
    }
}
