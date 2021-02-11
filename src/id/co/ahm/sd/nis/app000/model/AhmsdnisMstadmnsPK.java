/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app000.model;

import id.co.ahm.sd.nis.app000.utils.MapXls;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Embeddable
public class AhmsdnisMstadmnsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "VBPSPRVCD")
    private String vbpsprvcd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "VBPSDSTRCD")
    private String vbpsdstrcd;

    public AhmsdnisMstadmnsPK() {
    }

    public AhmsdnisMstadmnsPK(String vbpsprvcd, String vbpsdstrcd) {
        this.vbpsprvcd = vbpsprvcd;
        this.vbpsdstrcd = vbpsdstrcd;
    }

    public String getVbpsprvcd() {
        return vbpsprvcd;
    }

    public void setVbpsprvcd(String vbpsprvcd) {
        this.vbpsprvcd = vbpsprvcd;
    }

    public String getVbpsdstrcd() {
        return vbpsdstrcd;
    }

    public void setVbpsdstrcd(String vbpsdstrcd) {
        this.vbpsdstrcd = vbpsdstrcd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vbpsprvcd != null ? vbpsprvcd.hashCode() : 0);
        hash += (vbpsdstrcd != null ? vbpsdstrcd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AhmsdnisMstadmnsPK)) {
            return false;
        }
        AhmsdnisMstadmnsPK other = (AhmsdnisMstadmnsPK) object;
        if ((this.vbpsprvcd == null && other.vbpsprvcd != null) || (this.vbpsprvcd != null && !this.vbpsprvcd.equals(other.vbpsprvcd))) {
            return false;
        }
        if ((this.vbpsdstrcd == null && other.vbpsdstrcd != null) || (this.vbpsdstrcd != null && !this.vbpsdstrcd.equals(other.vbpsdstrcd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.co.ahm.sd.nis.app000.model.AhmsdnisMstadmnsPK[ vbpsprvcd=" + vbpsprvcd + ", vbpsdstrcd=" + vbpsdstrcd + " ]";
    }
    
}
