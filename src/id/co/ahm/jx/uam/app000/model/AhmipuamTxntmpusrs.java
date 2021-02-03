/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author achmad.ha
 */
@Table(name = "AHMIPUAM_TXNTMPUSRS")
@Entity
public class AhmipuamTxntmpusrs extends DefaultEntityImpl {
    
    @Id
    @SequenceGenerator(name = "seqTxnTmpUsers", sequenceName = "SE_TXNTMPUSRS")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqTxnTmpUsers")
    @Column(name = "IINTERNALID")
    private Long internalId;
    
    @Column(name = "VUSERID")
    private String vuserid;
    
    @Column(name = "VFULLNAME")
    private String vfullname;
    
    @Column(name = "VEMAIL")
    private String vemail;
    
    @Column(name = "VPARTNERTYP")
    private String vpartnertyp;
    
    @Column(name = "VPARENTID")
    private String vparentid;
    
    @Column(name = "VPARTNERID")
    private String vpartnerid;
    
    @Column(name = "VCONTACT")
    private String vcontact;
    
    @Column(name = "VSTAT")
    private String vstat;
    
    @Column(name = "VTOKEN")
    private String vtoken;
    
    @Column(name = "VREASON")
    private String vreason;
    
    @Column(name = "VNOSAR")
    private String vnosar;
    
    @Column(name = "VUSERAREA")
    private String vuserarea;

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

    public String getVuserid() {
        return vuserid;
    }

    public void setVuserid(String vuserid) {
        this.vuserid = vuserid;
    }

    public String getVfullname() {
        return vfullname;
    }

    public void setVfullname(String vfullname) {
        this.vfullname = vfullname;
    }

    public String getVemail() {
        return vemail;
    }

    public void setVemail(String vemail) {
        this.vemail = vemail;
    }

    public String getVpartnertyp() {
        return vpartnertyp;
    }

    public void setVpartnertyp(String vpartnertyp) {
        this.vpartnertyp = vpartnertyp;
    }

    public String getVparentid() {
        return vparentid;
    }

    public void setVparentid(String vparentid) {
        this.vparentid = vparentid;
    }

    public String getVpartnerid() {
        return vpartnerid;
    }

    public void setVpartnerid(String vpartnerid) {
        this.vpartnerid = vpartnerid;
    }

    public String getVcontact() {
        return vcontact;
    }

    public void setVcontact(String vcontact) {
        this.vcontact = vcontact;
    }

    public String getVstat() {
        return vstat;
    }

    public void setVstat(String vstat) {
        this.vstat = vstat;
    }

    public String getVtoken() {
        return vtoken;
    }

    public void setVtoken(String vtoken) {
        this.vtoken = vtoken;
    }

    public String getVreason() {
        return vreason;
    }

    public void setVreason(String vreason) {
        this.vreason = vreason;
    }

    public String getVnosar() {
        return vnosar;
    }

    public void setVnosar(String vnosar) {
        this.vnosar = vnosar;
    }

    public String getVuserarea() {
        return vuserarea;
    }

    public void setVuserarea(String vuserarea) {
        this.vuserarea = vuserarea;
    }
}
