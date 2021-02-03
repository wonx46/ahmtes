/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.vid.BaseAuditVidVersioning;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author achmad.ha
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "AHMIPUAM_MSTSERVICES"
        , uniqueConstraints = @UniqueConstraint(columnNames = {"VURL"}))
public class AhmipuamMstservices extends BaseAuditVidVersioning implements Serializable{
    
    @Column(name = "VURL",length = 128,nullable = false)
    private String vurl;
    
    @Column(name = "VMETHOD",length = 10,nullable = false)
    private String vmethod;

    @Column(name = "VSVCNAME",length = 128,nullable = false)
    private String vsvcname;

    @Column(name = "VSVCDESC",length = 256,nullable = false)
    private String vsvcdesc;
    
    @Column(name = "BEXPIRED",length = 1)
    private String bexpired;

    @Column(name = "DBEGINEFF",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dbegineff;

    @Column(name = "DENDEFF",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dendeff;

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

    public String getVsvcname() {
        return vsvcname;
    }

    public void setVsvcname(String vsvcname) {
        this.vsvcname = vsvcname;
    }

    public String getVsvcdesc() {
        return vsvcdesc;
    }

    public void setVsvcdesc(String vsvcdesc) {
        this.vsvcdesc = vsvcdesc;
    }

    public Date getDbegineff() {
        return dbegineff;
    }

    public void setDbegineff(Date dbegineff) {
        this.dbegineff = dbegineff;
    }

    public Date getDendeff() {
        return dendeff;
    }

    public void setDendeff(Date dendeff) {
        this.dendeff = dendeff;
    }

    public String getBexpired() {
        return bexpired;
    }

    public void setBexpired(String bexpired) {
        this.bexpired = bexpired;
    }
    
    
}
