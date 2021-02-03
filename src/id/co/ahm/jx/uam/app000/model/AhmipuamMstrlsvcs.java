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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "AHMIPUAM_MSTRLSVCS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"MROLE_IIDROLE", "MSRVC_VIDSERVICE"}))
public class AhmipuamMstrlsvcs extends BaseAuditVidVersioning implements Serializable {

    @Column(name="MROLE_IIDROLE")     
    private Long mroleIidrole;
    
    @Column(name="MSRVC_VIDSERVICE",length = 64,nullable = false)     
    private String vidAhmjxuamMstservices;    
    
    @Column(name = "DBEGINEFF", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dbegineff;

    @Column(name = "DENDEFF", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dendeff;
    
    @ManyToOne(targetEntity=AhmipuamMstroles.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "MROLE_IIDROLE",referencedColumnName = "IINTERNALID",insertable = false,updatable = false)
    private AhmipuamMstroles ahmipuamMstroles;
    
    @ManyToOne(targetEntity=AhmipuamMstservices.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "MSRVC_VIDSERVICE",referencedColumnName = "VID",insertable = false,updatable = false)
    private AhmipuamMstservices ahmipuamMstservices;

    public Long getMroleIidrole() {
        return mroleIidrole;
    }

    public void setMroleIidrole(Long mroleIidrole) {
        this.mroleIidrole = mroleIidrole;
    }

    public String getVidAhmjxuamMstservices() {
        return vidAhmjxuamMstservices;
    }

    public void setVidAhmjxuamMstservices(String vidAhmjxuamMstservices) {
        this.vidAhmjxuamMstservices = vidAhmjxuamMstservices;
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

    public AhmipuamMstroles getAhmipuamMstroles() {
        return ahmipuamMstroles;
    }

    public void setAhmipuamMstroles(AhmipuamMstroles ahmipuamMstroles) {
        this.ahmipuamMstroles = ahmipuamMstroles;
    }

    public AhmipuamMstservices getAhmipuamMstservices() {
        return ahmipuamMstservices;
    }

    public void setAhmipuamMstservices(AhmipuamMstservices ahmipuamMstservices) {
        this.ahmipuamMstservices = ahmipuamMstservices;
    }
}
