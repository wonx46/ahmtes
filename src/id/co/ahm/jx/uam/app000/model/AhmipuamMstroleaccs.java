/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author achmad.ha
 */
@Entity
@Table(name = "AHMIPUAM_MSTROLEACCS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"IMENUINTID", "IROLEINTID"}))
public class AhmipuamMstroleaccs extends DefaultEntityImpl implements Serializable {

    @Id
    @SequenceGenerator(name = "roleAccessSeq", sequenceName = "SE_MSTROLEACCS")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "roleAccessSeq")
    @Column(name = "IINTERNALID")
    private Long iinternalid;

    @ManyToOne(targetEntity = AhmipuamMstmenus.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "IMENUINTID", referencedColumnName = "IINTERNALID", insertable = false, updatable = false)
    private AhmipuamMstmenus ahmipuamMstmenus;
    
    @Column(name = "IMENUINTID")
    private Long imenuintid;

    @ManyToOne(targetEntity = AhmipuamMstroles.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "IROLEINTID", referencedColumnName = "IINTERNALID", insertable = false, updatable = false)
    private AhmipuamMstroles ahmipuamMstroles;

    @Column(name = "IROLEINTID")
    private Long iroleintid;

    @Column(name = "VENABLEFLAG")
    private String venableflag;

    @OneToMany(mappedBy = "iroleaccsid", cascade = CascadeType.ALL)
    private List<AhmipuamMstroleaas> listCustomRole;

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

    public Long getImenuintid() {
        return imenuintid;
    }

    public void setImenuintid(Long imenuintid) {
        this.imenuintid = imenuintid;
    }

    public AhmipuamMstroles getAhmipuamMstroles() {
        return ahmipuamMstroles;
    }

    public void setAhmipuamMstroles(AhmipuamMstroles ahmipuamMstroles) {
        this.ahmipuamMstroles = ahmipuamMstroles;
    }

    public Long getIroleintid() {
        return iroleintid;
    }

    public void setIroleintid(Long iroleintid) {
        this.iroleintid = iroleintid;
    }

    public String getVenableflag() {
        return venableflag;
    }

    public void setVenableflag(String venableflag) {
        this.venableflag = venableflag;
    }

    public List<AhmipuamMstroleaas> getListCustomRole() {
        return listCustomRole;
    }

    public void setListCustomRole(List<AhmipuamMstroleaas> listCustomRole) {
        this.listCustomRole = listCustomRole;
    }

}
