/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

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
@Table(name="AHMMOERP_TXNCOUNTAPPS")
public class AhmmoerpTxncountapps extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    protected AhmmoerpTxncountappsPk ahmmoerpTxncountappsPk;
    
    @Column(name = "ICOUNTER")
    private int icounter;

    public int getIcounter() {
        return icounter;
    }

    public void setIcounter(int icounter) {
        this.icounter = icounter;
    }
}
