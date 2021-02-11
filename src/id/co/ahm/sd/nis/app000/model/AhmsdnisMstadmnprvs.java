/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app000.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "AHMSDNIS_MSTADMNPRVS")
@NamedQueries({
    @NamedQuery(name = "AhmsdnisMstadmnprvs.findAll", query = "SELECT a FROM AhmsdnisMstadmnprvs a")})
public class AhmsdnisMstadmnprvs implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ahmsdnisMstadmnprvs")
    private Collection<AhmsdnisTxnrmsprvs> ahmsdnisTxnrmsprvsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "VBPSPRVCD")
    private String vbpsprvcd;
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
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ahmsdnisMstadmnprvs")
//    private Collection<AhmsdnisMstadmns> ahmsdnisMstadmnsCollection;

    public AhmsdnisMstadmnprvs() {
    }

    public AhmsdnisMstadmnprvs(String vbpsprvcd) {
        this.vbpsprvcd = vbpsprvcd;
    }

    public String getVbpsprvcd() {
        return vbpsprvcd;
    }

    public void setVbpsprvcd(String vbpsprvcd) {
        this.vbpsprvcd = vbpsprvcd;
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

//    public Collection<AhmsdnisMstadmns> getAhmsdnisMstadmnsCollection() {
//        return ahmsdnisMstadmnsCollection;
//    }
//
//    public void setAhmsdnisMstadmnsCollection(Collection<AhmsdnisMstadmns> ahmsdnisMstadmnsCollection) {
//        this.ahmsdnisMstadmnsCollection = ahmsdnisMstadmnsCollection;
//    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (vbpsprvcd != null ? vbpsprvcd.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof AhmsdnisMstadmnprvs)) {
//            return false;
//        }
//        AhmsdnisMstadmnprvs other = (AhmsdnisMstadmnprvs) object;
//        if ((this.vbpsprvcd == null && other.vbpsprvcd != null) || (this.vbpsprvcd != null && !this.vbpsprvcd.equals(other.vbpsprvcd))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "id.co.ahm.sd.nis.app000.model.AhmsdnisMstadmnprvs[ vbpsprvcd=" + vbpsprvcd + " ]";
    }

    @XmlTransient
    public Collection<AhmsdnisTxnrmsprvs> getAhmsdnisTxnrmsprvsCollection() {
        return ahmsdnisTxnrmsprvsCollection;
    }

    public void setAhmsdnisTxnrmsprvsCollection(Collection<AhmsdnisTxnrmsprvs> ahmsdnisTxnrmsprvsCollection) {
        this.ahmsdnisTxnrmsprvsCollection = ahmsdnisTxnrmsprvsCollection;
    }
    
}
