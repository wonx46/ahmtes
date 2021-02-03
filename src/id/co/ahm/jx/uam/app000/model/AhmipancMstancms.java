/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.vid.BaseAuditVidImpl;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hendra.fs
 */
@Entity
@Table(name = "AHMIPANC_MSTANCMS")
public class AhmipancMstancms extends BaseAuditVidImpl implements Serializable {

    @Column(name = "VTITLE")
    private String vtitle;
    @Lob
    @Column(name = "BTHUMBNAIL")
    private byte[] bthumbnail;
    @Lob
    @Column(name = "BIMAGE")
    private byte[] bimage;
    @Column(name = "CCONTENT")
    private String ccontent;
    
    @Column(name = "DBGNPUBLISH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dbgnpublish;
    
    @Column(name = "DENDPUBLISH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dendpublish;
    
    @Column(name = "DBGNEVENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dbgnevent;
    
    @Column(name = "DENDEVENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dendevent;
    
    @Column(name = "VMENUCODE")
    private String vmenucode;
    @Column(name = "VCHANNEL")
    private String vchannel;
    @Column(name = "BURGENT")
    private String burgent;

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public byte[] getBthumbnail() {
        return bthumbnail;
    }

    public void setBthumbnail(byte[] bthumbnail) {
        this.bthumbnail = bthumbnail;
    }

    public byte[] getBimage() {
        return bimage;
    }

    public void setBimage(byte[] bimage) {
        this.bimage = bimage;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public Date getDbgnpublish() {
        return dbgnpublish;
    }

    public void setDbgnpublish(Date dbgnpublish) {
        this.dbgnpublish = dbgnpublish;
    }

    public Date getDendpublish() {
        return dendpublish;
    }

    public void setDendpublish(Date dendpublish) {
        this.dendpublish = dendpublish;
    }

    public Date getDbgnevent() {
        return dbgnevent;
    }

    public void setDbgnevent(Date dbgnevent) {
        this.dbgnevent = dbgnevent;
    }

    public Date getDendevent() {
        return dendevent;
    }

    public void setDendevent(Date dendevent) {
        this.dendevent = dendevent;
    }

    public String getVmenucode() {
        return vmenucode;
    }

    public void setVmenucode(String vmenucode) {
        this.vmenucode = vmenucode;
    }

    public String getVchannel() {
        return vchannel;
    }

    public void setVchannel(String vchannel) {
        this.vchannel = vchannel;
    }

    public String getBurgent() {
        return burgent;
    }

    public void setBurgent(String burgent) {
        this.burgent = burgent;
    }

}
