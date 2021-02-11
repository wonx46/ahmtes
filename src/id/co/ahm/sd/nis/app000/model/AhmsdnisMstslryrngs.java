/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app000.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "AHMSDNIS_MSTSLRYRNGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AhmsdnisMstslryrngs.findAll", query = "SELECT a FROM AhmsdnisMstslryrngs a")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByVlevel", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.vlevel = :vlevel")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByNlwrlmt", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.nlwrlmt = :nlwrlmt")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByNuplmt", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.nuplmt = :nuplmt")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByVclr", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.vclr = :vclr")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByVdsrcptn", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.vdsrcptn = :vdsrcptn")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByDbgneff", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.dbgneff = :dbgneff")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByDlasteff", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.dlasteff = :dlasteff")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByVcrea", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.vcrea = :vcrea")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByDcrea", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.dcrea = :dcrea")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByVmodi", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.vmodi = :vmodi")
    , @NamedQuery(name = "AhmsdnisMstslryrngs.findByDmodi", query = "SELECT a FROM AhmsdnisMstslryrngs a WHERE a.dmodi = :dmodi")})
public class AhmsdnisMstslryrngs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "VLEVEL")
    private String vlevel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NLWRLMT")
    private int nlwrlmt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUPLMT")
    private int nuplmt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "VCLR")
    private String vclr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "VDSRCPTN")
    private String vdsrcptn;
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

    public AhmsdnisMstslryrngs() {
    }

    public AhmsdnisMstslryrngs(String vlevel) {
        this.vlevel = vlevel;
    }

    public AhmsdnisMstslryrngs(String vlevel, int nlwrlmt, int nuplmt, String vclr, String vdsrcptn) {
        this.vlevel = vlevel;
        this.nlwrlmt = nlwrlmt;
        this.nuplmt = nuplmt;
        this.vclr = vclr;
        this.vdsrcptn = vdsrcptn;
    }

    public String getVlevel() {
        return vlevel;
    }

    public void setVlevel(String vlevel) {
        this.vlevel = vlevel;
    }

    public int getNlwrlmt() {
        return nlwrlmt;
    }

    public void setNlwrlmt(int nlwrlmt) {
        this.nlwrlmt = nlwrlmt;
    }

    public int getNuplmt() {
        return nuplmt;
    }

    public void setNuplmt(int nuplmt) {
        this.nuplmt = nuplmt;
    }

    public String getVclr() {
        return vclr;
    }

    public void setVclr(String vclr) {
        this.vclr = vclr;
    }

    public String getVdsrcptn() {
        return vdsrcptn;
    }

    public void setVdsrcptn(String vdsrcptn) {
        this.vdsrcptn = vdsrcptn;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vlevel != null ? vlevel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AhmsdnisMstslryrngs)) {
            return false;
        }
        AhmsdnisMstslryrngs other = (AhmsdnisMstslryrngs) object;
        if ((this.vlevel == null && other.vlevel != null) || (this.vlevel != null && !this.vlevel.equals(other.vlevel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.co.ahm.sd.nis.app004.rest.AhmsdnisMstslryrngs[ vlevel=" + vlevel + " ]";
    }
    
}
