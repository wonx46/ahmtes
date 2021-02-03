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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author achmad.ha
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="AHMIPUAM_MSTUSERRLS"
        ,uniqueConstraints = @UniqueConstraint(columnNames = {"IROLEINTID", "VUSERNAME"}))
public class AhmipuamMstuserrls extends DefaultEntityImpl implements Serializable{
    
    @Id
    @SequenceGenerator(name = "userRoleSeq", sequenceName = "SE_MSTUSERRLS")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userRoleSeq")
    @Column(name = "IINTERNALID")
    private Long iinternalid;           
    
    @ManyToOne(targetEntity = AhmipuamMstroles.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "IROLEINTID", referencedColumnName = "IINTERNALID",insertable = false, updatable = false)
    private AhmipuamMstroles ahmipuamMstroles;
    
    @Column(name = "IROLEINTID")
    private long iroleintid;
    
    @Column(name = "VUSERNAME")
    private String vusername;

    public Long getIinternalid() {
        return iinternalid;
    }

    public void setIinternalid(Long iinternalid) {
        this.iinternalid = iinternalid;
    }

    public AhmipuamMstroles getAhmipuamMstroles() {
        return ahmipuamMstroles;
    }

    public void setAhmipuamMstroles(AhmipuamMstroles ahmipuamMstroles) {
        this.ahmipuamMstroles = ahmipuamMstroles;
    }

    public long getIroleintid() {
        return iroleintid;
    }

    public void setIroleintid(long iroleintid) {
        this.iroleintid = iroleintid;
    }

    public String getVusername() {
        return vusername;
    }

    public void setVusername(String vusername) {
        this.vusername = vusername;
    }
}
