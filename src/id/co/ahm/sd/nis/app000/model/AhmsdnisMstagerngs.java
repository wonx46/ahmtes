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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "AHMSDNIS_MSTAGERNGS")
@NamedQueries({
    @NamedQuery(name = "AhmsdnisMstagerngs.findAll", query = "SELECT a FROM AhmsdnisMstagerngs a")})
public class AhmsdnisMstagerngs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "vcdagerng")
    private String vcdagerng;
    @Column(name = "dbegineff")
    @Temporal(TemporalType.DATE)
    private Date dbegineff;
    @Column(name = "dcrea")
    @Temporal(TemporalType.DATE)
    private Date dcrea;
    @Column(name = "dendeff")
    @Temporal(TemporalType.DATE)
    private Date dendeff;
    @Column(name = "dmodi")
    @Temporal(TemporalType.DATE)
    private Date dmodi;
    @Column(name = "nend")
    private Integer nend;
    @Column(name = "nstart")
    private Integer nstart;
    @Size(max = 255)
    @Column(name = "vcrea")
    private String vcrea;
    @Size(max = 255)
    @MapXls(indexXls = 0)
    @Column(name = "vdscrptn")
    private String vdscrptn;
    @Size(max = 255)
    @Column(name = "vmodi")
    private String vmodi;

    public AhmsdnisMstagerngs() {
    }

    public AhmsdnisMstagerngs(String vcdagerng) {
        this.vcdagerng = vcdagerng;
    }

    public String getVcdagerng() {
        return vcdagerng;
    }

    public void setVcdagerng(String vcdagerng) {
        this.vcdagerng = vcdagerng;
    }

    public Date getDbegineff() {
        return dbegineff;
    }

    public void setDbegineff(Date dbegineff) {
        this.dbegineff = dbegineff;
    }

    public Date getDcrea() {
        return dcrea;
    }

    public void setDcrea(Date dcrea) {
        this.dcrea = dcrea;
    }

    public Date getDendeff() {
        return dendeff;
    }

    public void setDendeff(Date dendeff) {
        this.dendeff = dendeff;
    }

    public Date getDmodi() {
        return dmodi;
    }

    public void setDmodi(Date dmodi) {
        this.dmodi = dmodi;
    }

    public Integer getNend() {
        return nend;
    }

    public void setNend(Integer nend) {
        this.nend = nend;
    }

    public Integer getNstart() {
        return nstart;
    }

    public void setNstart(Integer nstart) {
        this.nstart = nstart;
    }

    public String getVcrea() {
        return vcrea;
    }

    public void setVcrea(String vcrea) {
        this.vcrea = vcrea;
    }

    public String getVdscrptn() {
        return vdscrptn;
    }

    public void setVdscrptn(String vdscrptn) {
        this.vdscrptn = vdscrptn;
    }

    public String getVmodi() {
        return vmodi;
    }

    public void setVmodi(String vmodi) {
        this.vmodi = vmodi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vcdagerng != null ? vcdagerng.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AhmsdnisMstagerngs)) {
            return false;
        }
        AhmsdnisMstagerngs other = (AhmsdnisMstagerngs) object;
        if ((this.vcdagerng == null && other.vcdagerng != null) || (this.vcdagerng != null && !this.vcdagerng.equals(other.vcdagerng))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.co.ahm.sd.nis.app004.dao.impl.AhmsdnisMstagerngs[ vcdagerng=" + vcdagerng + " ]";
    }
    
}
