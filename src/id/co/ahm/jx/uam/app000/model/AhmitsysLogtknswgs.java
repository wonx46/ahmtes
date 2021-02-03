/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author achmad.ha
 */
@Table(name = "AHMITSYS_LOGTKNSWGS")
@Entity
public class AhmitsysLogtknswgs extends DefaultEntityImpl implements Serializable{
    
    @Id
    @Column(name = "VTOKEN")
    private String vtoken;
    
    @Column(name = "VAPPSID")
    private String vappsid;
    
    @Column(name = "VIPADDRESS")
    private String vipaddress;

    public String getVtoken() {
        return vtoken;
    }

    public void setVtoken(String vtoken) {
        this.vtoken = vtoken;
    }

    public String getVappsid() {
        return vappsid;
    }

    public void setVappsid(String vappsid) {
        this.vappsid = vappsid;
    }

    public String getVipaddress() {
        return vipaddress;
    }

    public void setVipaddress(String vipaddress) {
        this.vipaddress = vipaddress;
    }
    
    
    
    
}
