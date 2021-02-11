/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app000.model;

import id.co.ahm.sd.nis.app000.utils.MapXls;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "AHMSDNIS_TXNPPLTNS")
@NamedQueries({
    @NamedQuery(name = "AhmsdnisTxnppltns.findAll", query = "SELECT a FROM AhmsdnisTxnppltns a")})
public class AhmsdnisTxnppltns implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @MapXls(embedId = "nyear-0#vbpsprvcd-2#vbpsdstrcd-4#vagerng-5")
    protected AhmsdnisTxnppltnsPK ahmsdnisTxnppltnsPK;
    @Size(max = 50)
    @MapXls(indexXls = 1)
    @Column(name = "VBPSPRVNM")
    private String vbpsprvnm;
    @Size(max = 50)
    @MapXls(indexXls = 3)
    @Column(name = "VBPSDSTRNM")
    private String vbpsdstrnm;
    @Basic(optional = false)
    @NotNull
    @MapXls(indexXls = 6)
    @Column(name = "NMALE")
    private Integer nmale;
    @Basic(optional = false)
    @NotNull
    @MapXls(indexXls = 7)
    @Column(name = "NFEMALE")
    private Integer nfemale;
    @MapXls(indexXls = 8)
    @Column(name = "DBGNEFF")
    @Temporal(TemporalType.DATE)
    private Date dbgneff;
    @MapXls(indexXls = 9)
    @Column(name = "DLASTEFF")
    @Temporal(TemporalType.DATE)
    private Date dlasteff;
    @Size(max = 20)
    @Column(name = "VCREA")
    private String vcrea;
    @Column(name = "DCREA")
    @Temporal(TemporalType.DATE)
    private Date dcrea;
    @Size(max = 20)
    @Column(name = "VMODI")
    private String vmodi;
    @Column(name = "DMODI")
    @Temporal(TemporalType.DATE)
    private Date dmodi;
//    @JoinColumns({
//        @JoinColumn(name = "VBPSPRVCD", referencedColumnName = "VBPSPRVCD", insertable = false, updatable = false)
//        , @JoinColumn(name = "VBPSDSTRCD", referencedColumnName = "VBPSDSTRCD", insertable = false, updatable = false)})
//    @ManyToOne(optional = false)
//    private AhmsdnisMstadmns ahmsdnisMstadmns;

    public AhmsdnisTxnppltns() {
        ahmsdnisTxnppltnsPK = new AhmsdnisTxnppltnsPK();
    }

    public AhmsdnisTxnppltns(AhmsdnisTxnppltnsPK ahmsdnisTxnppltnsPK) {
        this.ahmsdnisTxnppltnsPK = ahmsdnisTxnppltnsPK;
    }

    public AhmsdnisTxnppltns(AhmsdnisTxnppltnsPK ahmsdnisTxnppltnsPK, Integer nmale, Integer nfemale) {
        this.ahmsdnisTxnppltnsPK = ahmsdnisTxnppltnsPK;
        this.nmale = nmale;
        this.nfemale = nfemale;
    }

    public AhmsdnisTxnppltns(String nyear, String vbpsprvcd, String vbpsdstrcd, String vagerng) {
        this.ahmsdnisTxnppltnsPK = new AhmsdnisTxnppltnsPK(nyear, vbpsprvcd, vbpsdstrcd, vagerng);
    }

    public AhmsdnisTxnppltnsPK getAhmsdnisTxnppltnsPK() {
        return ahmsdnisTxnppltnsPK;
    }

    public void setAhmsdnisTxnppltnsPK(AhmsdnisTxnppltnsPK ahmsdnisTxnppltnsPK) {
        this.ahmsdnisTxnppltnsPK = ahmsdnisTxnppltnsPK;
    }

    public String getVbpsprvnm() {
        return vbpsprvnm;
    }

    public void setVbpsprvnm(String vbpsprvnm) {
        this.vbpsprvnm = vbpsprvnm;
    }

    public String getVbpsdstrnm() {
        return vbpsdstrnm;
    }

    public void setVbpsdstrnm(String vbpsdstrnm) {
        this.vbpsdstrnm = vbpsdstrnm;
    }



    public Date getDbgneff() {
        return dbgneff;
    }

    public void setDbgneff(Date dbgneff) {
        this.dbgneff = dbgneff;
    }

    public Date getDlasteff() {
        return dlasteff;
    }

    public void setDlasteff(Date dlasteff) {
        this.dlasteff = dlasteff;
    }

    public String getVcrea() {
        return vcrea;
    }

    public void setVcrea(String vcrea) {
        this.vcrea = vcrea;
    }

    public Date getDcrea() {
        return dcrea;
    }

    public void setDcrea(Date dcrea) {
        this.dcrea = dcrea;
    }

    public String getVmodi() {
        return vmodi;
    }

    public void setVmodi(String vmodi) {
        this.vmodi = vmodi;
    }

    public Date getDmodi() {
        return dmodi;
    }

    public void setDmodi(Date dmodi) {
        this.dmodi = dmodi;
    }

//    public AhmsdnisMstadmns getAhmsdnisMstadmns() {
//        return ahmsdnisMstadmns;
//    }
//
//    public void setAhmsdnisMstadmns(AhmsdnisMstadmns ahmsdnisMstadmns) {
//        this.ahmsdnisMstadmns = ahmsdnisMstadmns;
//    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (ahmsdnisTxnppltnsPK != null ? ahmsdnisTxnppltnsPK.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof AhmsdnisTxnppltns)) {
//            return false;
//        }
//        AhmsdnisTxnppltns other = (AhmsdnisTxnppltns) object;
//        if ((this.ahmsdnisTxnppltnsPK == null && other.ahmsdnisTxnppltnsPK != null) || (this.ahmsdnisTxnppltnsPK != null && !this.ahmsdnisTxnppltnsPK.equals(other.ahmsdnisTxnppltnsPK))) {
//            return false;
//        }
//        return true;
//    }

    public Integer getNmale() {
        return nmale;
    }

    public void setNmale(Integer nmale) {
        this.nmale = nmale;
    }

    public Integer getNfemale() {
        return nfemale;
    }

    public void setNfemale(Integer nfemale) {
        this.nfemale = nfemale;
    }

    @Override
    public String toString() {
        return "AhmsdnisTxnppltns{" + "ahmsdnisTxnppltnsPK=" + ahmsdnisTxnppltnsPK + ", vbpsprvnm=" + vbpsprvnm + ", vbpsdstrnm=" + vbpsdstrnm + ", nmale=" + nmale + ", nfemale=" + nfemale + ", dbgneff=" + dbgneff + ", dlasteff=" + dlasteff + ", vcrea=" + vcrea + ", dcrea=" + dcrea + ", vmodi=" + vmodi + ", dmodi=" + dmodi + '}';
    }
    
    
    
    
    
    
    

}
