/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app000.model;

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
public class AhmsdnisTxnrmsprvsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "NYEAR")
    private short nyear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "VBPSPRVCD")
    private String vbpsprvcd;

    public AhmsdnisTxnrmsprvsPK() {
    }

    public AhmsdnisTxnrmsprvsPK(short nyear, String vbpsprvcd) {
        this.nyear = nyear;
        this.vbpsprvcd = vbpsprvcd;
    }

    public short getNyear() {
        return nyear;
    }

    public void setNyear(short nyear) {
        this.nyear = nyear;
    }

    public String getVbpsprvcd() {
        return vbpsprvcd;
    }

    public void setVbpsprvcd(String vbpsprvcd) {
        this.vbpsprvcd = vbpsprvcd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nyear;
        hash += (vbpsprvcd != null ? vbpsprvcd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AhmsdnisTxnrmsprvsPK)) {
            return false;
        }
        AhmsdnisTxnrmsprvsPK other = (AhmsdnisTxnrmsprvsPK) object;
        if (this.nyear != other.nyear) {
            return false;
        }
        if ((this.vbpsprvcd == null && other.vbpsprvcd != null) || (this.vbpsprvcd != null && !this.vbpsprvcd.equals(other.vbpsprvcd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.co.ahm.sd.nis.app004.rest.AhmsdnisTxnrmsprvsPK[ nyear=" + nyear + ", vbpsprvcd=" + vbpsprvcd + " ]";
    }
    
}
