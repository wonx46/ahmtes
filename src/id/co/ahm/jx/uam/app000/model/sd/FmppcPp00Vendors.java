/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model.sd;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author achmad.ha
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "FMPPC_PP00_VENDORS")
public class FmppcPp00Vendors implements Serializable {

    @EmbeddedId
    private FmppcPp00VendorsPk fmppcPp00VendorsPk;

    @Column(name = "VND_NAME")
    private String vndName;

    public FmppcPp00VendorsPk getFmppcPp00VendorsPk() {
        return fmppcPp00VendorsPk;
    }

    public void setFmppcPp00VendorsPk(FmppcPp00VendorsPk fmppcPp00VendorsPk) {
        this.fmppcPp00VendorsPk = fmppcPp00VendorsPk;
    }

    public String getVndName() {
        return vndName;
    }

    public void setVndName(String vndName) {
        this.vndName = vndName;
    }

}
