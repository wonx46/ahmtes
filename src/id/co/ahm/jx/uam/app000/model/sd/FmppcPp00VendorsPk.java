/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model.sd;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author achmadha
 */
@Embeddable
public class FmppcPp00VendorsPk implements Serializable{
    
    @Column(name = "VND_CODE")
    private String vndCode;
    
    @Column(name = "VNDTYP_VND_TYPE")
    private String vndtypVndType;

    public FmppcPp00VendorsPk() {
    }

    public FmppcPp00VendorsPk(String vndCode, String vndtypVndType) {
        this.vndCode = vndCode;
        this.vndtypVndType = vndtypVndType;
    }

    public String getVndCode() {
        return vndCode;
    }

    public void setVndCode(String vndCode) {
        this.vndCode = vndCode;
    }

    public String getVndtypVndType() {
        return vndtypVndType;
    }

    public void setVndtypVndType(String vndtypVndType) {
        this.vndtypVndType = vndtypVndType;
    }
}
