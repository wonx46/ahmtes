/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author achmad.ha
 */
@Table(name = "AHMIPUAM_TXNPASSREQS")
@Entity
public class AhmipuamTxnpassreqs extends DefaultEntityImpl{
    
    @Id
    @SequenceGenerator(name = "seqTrxPassReqs", sequenceName = "SE_TXNPASSREQS")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqTrxPassReqs")
    @Column(name = "IINTERNALID")
    private Long internalId;
    
    @Column(name = "VEMAILSEND")
    private String vemailsend;
    
    @Column(name = "VFORGOTWHAT")
    private String vforgotwhat;
    
    @Column(name = "VUSERNAME")
    private String vusername;
    
    @Column(name = "VLINKTOKEN")
    private String vlinktoken;
    
    @Column(name = "DLASTEFFDT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dlasteffdt;
    
    @Column(name = "VFULLNAME")
    private String vfullname;
    
    @Column(name = "VUSED")
    private String vused;

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

    public String getVemailsend() {
        return vemailsend;
    }

    public void setVemailsend(String vemailsend) {
        this.vemailsend = vemailsend;
    }

    public String getVforgotwhat() {
        return vforgotwhat;
    }

    public void setVforgotwhat(String vforgotwhat) {
        this.vforgotwhat = vforgotwhat;
    }

    public String getVusername() {
        return vusername;
    }

    public void setVusername(String vusername) {
        this.vusername = vusername;
    }

    public String getVlinktoken() {
        return vlinktoken;
    }

    public void setVlinktoken(String vlinktoken) {
        this.vlinktoken = vlinktoken;
    }

    public Date getDlasteffdt() {
        return dlasteffdt;
    }

    public void setDlasteffdt(Date dlasteffdt) {
        this.dlasteffdt = dlasteffdt;
    }

    public String getVfullname() {
        return vfullname;
    }

    public void setVfullname(String vfullname) {
        this.vfullname = vfullname;
    }

    public String getVused() {
        return vused;
    }

    public void setVused(String vused) {
        this.vused = vused;
    }    
}
