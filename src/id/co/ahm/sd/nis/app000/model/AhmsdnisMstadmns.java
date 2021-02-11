/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app000.model;

import id.co.ahm.sd.nis.app000.utils.MapXls;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "AHMSDNIS_MSTADMNS")
@NamedQueries({
    @NamedQuery(name = "AhmsdnisMstadmns.findAll", query = "SELECT a FROM AhmsdnisMstadmns a")})
public class AhmsdnisMstadmns implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AhmsdnisMstadmnsPK ahmsdnisMstadmnsPK;
    @Size(max = 4)
    @Column(name = "VODPRVCD")
    private String vodprvcd;
    @Size(max = 25)
    @Column(name = "VBPSPRVNM")
    private String vbpsprvnm;
    @Size(max = 2)
    @Column(name = "VKMDGPRVCD")
    private String vkmdgprvcd;
    @Size(max = 2)
    @Column(name = "VPLRGPRVCD")
    private String vplrgprvcd;
    @Size(max = 4)
    @Column(name = "VODDSTRCD")
    private String voddstrcd;
    @Size(max = 25)
    @Column(name = "VBPSDSTRNM")
    private String vbpsdstrnm;
    @Size(max = 2)
    @Column(name = "VKMDGDSTRCD")
    private String vkmdgdstrcd;
    @Size(max = 4)
    @Column(name = "VPLRGDSTRCD")
    private String vplrgdstrcd;
    @Column(name = "DBGNEFF")
    @Temporal(TemporalType.DATE)
    private Date dbgneff;
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
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ahmsdnisMstadmns")
//    private Collection<AhmsdnisTxnppltns> ahmsdnisTxnppltnsCollection;
//    @JoinColumn(name = "VBPSPRVCD", referencedColumnName = "VBPSPRVCD", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private AhmsdnisMstadmnprvs ahmsdnisMstadmnprvs;

    public AhmsdnisMstadmns() {
    }

    public AhmsdnisMstadmns(AhmsdnisMstadmnsPK ahmsdnisMstadmnsPK) {
        this.ahmsdnisMstadmnsPK = ahmsdnisMstadmnsPK;
    }

    public AhmsdnisMstadmns(String vbpsprvcd, String vbpsdstrcd) {
        this.ahmsdnisMstadmnsPK = new AhmsdnisMstadmnsPK(vbpsprvcd, vbpsdstrcd);
    }

    public AhmsdnisMstadmnsPK getAhmsdnisMstadmnsPK() {
        return ahmsdnisMstadmnsPK;
    }

    public void setAhmsdnisMstadmnsPK(AhmsdnisMstadmnsPK ahmsdnisMstadmnsPK) {
        this.ahmsdnisMstadmnsPK = ahmsdnisMstadmnsPK;
    }

    public String getVodprvcd() {
        return vodprvcd;
    }

    public void setVodprvcd(String vodprvcd) {
        this.vodprvcd = vodprvcd;
    }

    public String getVbpsprvnm() {
        return vbpsprvnm;
    }

    public void setVbpsprvnm(String vbpsprvnm) {
        this.vbpsprvnm = vbpsprvnm;
    }

    public String getVkmdgprvcd() {
        return vkmdgprvcd;
    }

    public void setVkmdgprvcd(String vkmdgprvcd) {
        this.vkmdgprvcd = vkmdgprvcd;
    }

    public String getVplrgprvcd() {
        return vplrgprvcd;
    }

    public void setVplrgprvcd(String vplrgprvcd) {
        this.vplrgprvcd = vplrgprvcd;
    }

    public String getVoddstrcd() {
        return voddstrcd;
    }

    public void setVoddstrcd(String voddstrcd) {
        this.voddstrcd = voddstrcd;
    }

    public String getVbpsdstrnm() {
        return vbpsdstrnm;
    }

    public void setVbpsdstrnm(String vbpsdstrnm) {
        this.vbpsdstrnm = vbpsdstrnm;
    }

    public String getVkmdgdstrcd() {
        return vkmdgdstrcd;
    }

    public void setVkmdgdstrcd(String vkmdgdstrcd) {
        this.vkmdgdstrcd = vkmdgdstrcd;
    }

    public String getVplrgdstrcd() {
        return vplrgdstrcd;
    }

    public void setVplrgdstrcd(String vplrgdstrcd) {
        this.vplrgdstrcd = vplrgdstrcd;
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

//    public Collection<AhmsdnisTxnppltns> getAhmsdnisTxnppltnsCollection() {
//        return ahmsdnisTxnppltnsCollection;
//    }
//
//    public void setAhmsdnisTxnppltnsCollection(Collection<AhmsdnisTxnppltns> ahmsdnisTxnppltnsCollection) {
//        this.ahmsdnisTxnppltnsCollection = ahmsdnisTxnppltnsCollection;
//    }
//
//    public AhmsdnisMstadmnprvs getAhmsdnisMstadmnprvs() {
//        return ahmsdnisMstadmnprvs;
//    }
//
//    public void setAhmsdnisMstadmnprvs(AhmsdnisMstadmnprvs ahmsdnisMstadmnprvs) {
//        this.ahmsdnisMstadmnprvs = ahmsdnisMstadmnprvs;
//    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (ahmsdnisMstadmnsPK != null ? ahmsdnisMstadmnsPK.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof AhmsdnisMstadmns)) {
//            return false;
//        }
//        AhmsdnisMstadmns other = (AhmsdnisMstadmns) object;
//        if ((this.ahmsdnisMstadmnsPK == null && other.ahmsdnisMstadmnsPK != null) || (this.ahmsdnisMstadmnsPK != null && !this.ahmsdnisMstadmnsPK.equals(other.ahmsdnisMstadmnsPK))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "id.co.ahm.sd.nis.app000.model.AhmsdnisMstadmns[ ahmsdnisMstadmnsPK=" + ahmsdnisMstadmnsPK + " ]";
    }
    
}
