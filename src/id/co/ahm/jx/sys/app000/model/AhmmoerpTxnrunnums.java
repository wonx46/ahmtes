/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author okky.ms
 */
@Entity
@Table(name = "AHMMOERP_TXNRUNNUMS")
public class AhmmoerpTxnrunnums extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    protected AhmmoerpTxnrunnumsPk ahmmoerpTxnrunnumsPk;
    @Column(name = "IVALUE")
    private int ivalue;

    public AhmmoerpTxnrunnumsPk getAhmmoerpTxnrunnumsPk() {
        return ahmmoerpTxnrunnumsPk;
    }

    public void setAhmmoerpTxnrunnumsPk(AhmmoerpTxnrunnumsPk ahmmoerpTxnrunnumsPk) {
        this.ahmmoerpTxnrunnumsPk = ahmmoerpTxnrunnumsPk;
    }

    public int getIvalue() {
        return ivalue;
    }

    public void setIvalue(int ivalue) {
        this.ivalue = ivalue;
    }
    
}
