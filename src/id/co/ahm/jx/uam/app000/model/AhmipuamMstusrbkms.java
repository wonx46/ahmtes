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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author achmad.ha
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="AHMIPUAM_MSTUSRBKMS")
public class AhmipuamMstusrbkms extends DefaultEntityImpl implements Serializable{
        
    @Id
    @SequenceGenerator(name = "seqUserBookmark", sequenceName = "SE_MSTUSRBKMS")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqUserBookmark")
    @Column(name = "IINTERNALID")
    private Long iinternalid;

    @ManyToOne(targetEntity = AhmipuamMstmenus.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "VMENUCODE", referencedColumnName = "VMENUCODE",insertable = false,updatable = false)
    private AhmipuamMstmenus ahmipuamMstmenus;
    
    @Column(name = "VMENUCODE")
    private String vmenucode;
    
    @Column(name = "VUSERNAME")
    private String vusername;

    public Long getIinternalid() {
        return iinternalid;
    }

    public void setIinternalid(Long iinternalid) {
        this.iinternalid = iinternalid;
    }

    public AhmipuamMstmenus getAhmipuamMstmenus() {
        return ahmipuamMstmenus;
    }

    public void setAhmipuamMstmenus(AhmipuamMstmenus ahmipuamMstmenus) {
        this.ahmipuamMstmenus = ahmipuamMstmenus;
    }

    public String getVmenucode() {
        return vmenucode;
    }

    public void setVmenucode(String vmenucode) {
        this.vmenucode = vmenucode;
    }    

    public String getVusername() {
        return vusername;
    }

    public void setVusername(String vusername) {
        this.vusername = vusername;
    }
}
