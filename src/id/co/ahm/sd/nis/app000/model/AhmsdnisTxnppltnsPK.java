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
public class AhmsdnisTxnppltnsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "NYEAR")
    private String nyear;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "VAGERNG")
    private String vagerng;

    public AhmsdnisTxnppltnsPK() {
    }

    public AhmsdnisTxnppltnsPK(String nyear, String vbpsprvcd, String vbpsdstrcd, String vagerng) {
        this.nyear = nyear;
        this.vbpsprvcd = vbpsprvcd;
        this.vbpsdstrcd = vbpsdstrcd;
        this.vagerng = vagerng;
    }

    public String getNyear() {
        return nyear;
    }

    public void setNyear(String nyear) {
        this.nyear = nyear;
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

    public String getVagerng() {
        return vagerng;
    }

    public void setVagerng(String vagerng) {
        this.vagerng = vagerng;
    }

    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AhmsdnisTxnppltnsPK)) {
            return false;
        }
        AhmsdnisTxnppltnsPK other = (AhmsdnisTxnppltnsPK) object;
        if (this.nyear != other.nyear) {
            return false;
        }
        if ((this.vbpsprvcd == null && other.vbpsprvcd != null) || (this.vbpsprvcd != null && !this.vbpsprvcd.equals(other.vbpsprvcd))) {
            return false;
        }
        if ((this.vbpsdstrcd == null && other.vbpsdstrcd != null) || (this.vbpsdstrcd != null && !this.vbpsdstrcd.equals(other.vbpsdstrcd))) {
            return false;
        }
        if ((this.vagerng == null && other.vagerng != null) || (this.vagerng != null && !this.vagerng.equals(other.vagerng))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltnsPK[ nyear=" + nyear + ", vbpsprvcd=" + vbpsprvcd + ", vbpsdstrcd=" + vbpsdstrcd + ", vagerng=" + vagerng + " ]";
    }
    
}
