/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app000.model;

import id.co.ahm.sd.nis.app000.model.AhmsdnisMstadmnprvs;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "AHMSDNIS_TXNRMSPRVS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AhmsdnisTxnrmsprvs.findAll", query = "SELECT a FROM AhmsdnisTxnrmsprvs a")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByNyear", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.ahmsdnisTxnrmsprvsPK.nyear = :nyear")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByVbpsprvcd", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.ahmsdnisTxnrmsprvsPK.vbpsprvcd = :vbpsprvcd")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByVbpsprvnm", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.vbpsprvnm = :vbpsprvnm")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByNrmsrp", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.nrmsrp = :nrmsrp")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByVsource", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.vsource = :vsource")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByDbgneff", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.dbgneff = :dbgneff")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByDlasteff", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.dlasteff = :dlasteff")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByVcrea", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.vcrea = :vcrea")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByDcrea", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.dcrea = :dcrea")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByVmodi", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.vmodi = :vmodi")
    , @NamedQuery(name = "AhmsdnisTxnrmsprvs.findByDmodi", query = "SELECT a FROM AhmsdnisTxnrmsprvs a WHERE a.dmodi = :dmodi")})
public class AhmsdnisTxnrmsprvs implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AhmsdnisTxnrmsprvsPK ahmsdnisTxnrmsprvsPK;
    @Size(max = 50)
    @Column(name = "VBPSPRVNM")
    private String vbpsprvnm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NRMSRP")
    private int nrmsrp;
    @Size(max = 25)
    @Column(name = "VSOURCE")
    private String vsource;
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
    @JoinColumn(name = "VBPSPRVCD", referencedColumnName = "VBPSPRVCD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AhmsdnisMstadmnprvs ahmsdnisMstadmnprvs;

    public AhmsdnisTxnrmsprvs() {
    }

    public AhmsdnisTxnrmsprvs(AhmsdnisTxnrmsprvsPK ahmsdnisTxnrmsprvsPK) {
        this.ahmsdnisTxnrmsprvsPK = ahmsdnisTxnrmsprvsPK;
    }

    public AhmsdnisTxnrmsprvs(AhmsdnisTxnrmsprvsPK ahmsdnisTxnrmsprvsPK, int nrmsrp) {
        this.ahmsdnisTxnrmsprvsPK = ahmsdnisTxnrmsprvsPK;
        this.nrmsrp = nrmsrp;
    }

    public AhmsdnisTxnrmsprvs(short nyear, String vbpsprvcd) {
        this.ahmsdnisTxnrmsprvsPK = new AhmsdnisTxnrmsprvsPK(nyear, vbpsprvcd);
    }

    public AhmsdnisTxnrmsprvsPK getAhmsdnisTxnrmsprvsPK() {
        return ahmsdnisTxnrmsprvsPK;
    }

    public void setAhmsdnisTxnrmsprvsPK(AhmsdnisTxnrmsprvsPK ahmsdnisTxnrmsprvsPK) {
        this.ahmsdnisTxnrmsprvsPK = ahmsdnisTxnrmsprvsPK;
    }

    public String getVbpsprvnm() {
        return vbpsprvnm;
    }

    public void setVbpsprvnm(String vbpsprvnm) {
        this.vbpsprvnm = vbpsprvnm;
    }

    public int getNrmsrp() {
        return nrmsrp;
    }

    public void setNrmsrp(int nrmsrp) {
        this.nrmsrp = nrmsrp;
    }

    public String getVsource() {
        return vsource;
    }

    public void setVsource(String vsource) {
        this.vsource = vsource;
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

    public AhmsdnisMstadmnprvs getAhmsdnisMstadmnprvs() {
        return ahmsdnisMstadmnprvs;
    }

    public void setAhmsdnisMstadmnprvs(AhmsdnisMstadmnprvs ahmsdnisMstadmnprvs) {
        this.ahmsdnisMstadmnprvs = ahmsdnisMstadmnprvs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ahmsdnisTxnrmsprvsPK != null ? ahmsdnisTxnrmsprvsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AhmsdnisTxnrmsprvs)) {
            return false;
        }
        AhmsdnisTxnrmsprvs other = (AhmsdnisTxnrmsprvs) object;
        if ((this.ahmsdnisTxnrmsprvsPK == null && other.ahmsdnisTxnrmsprvsPK != null) || (this.ahmsdnisTxnrmsprvsPK != null && !this.ahmsdnisTxnrmsprvsPK.equals(other.ahmsdnisTxnrmsprvsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.co.ahm.sd.nis.app004.rest.AhmsdnisTxnrmsprvs[ ahmsdnisTxnrmsprvsPK=" + ahmsdnisTxnrmsprvsPK + " ]";
    }
    
}
