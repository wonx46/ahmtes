/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author achmad.ha
 */
@Embeddable
public class AhmmoerpTxncountappsPk implements Serializable{
    
    @Column(name = "IBULAN")
    private int ibulan;
    
    @Column(name = "ITAHUN")
    private int itahun;
    
    @Column(name = "VMODULENAME")
    private String vmodulename;

    public AhmmoerpTxncountappsPk() {
    }

    public AhmmoerpTxncountappsPk(int ibulan, int itahun, String vmodulename) {
        this.ibulan = ibulan;
        this.itahun = itahun;
        this.vmodulename = vmodulename;
    }
    
    public int getIbulan() {
        return ibulan;
    }

    public void setIbulan(int ibulan) {
        this.ibulan = ibulan;
    }

    public int getItahun() {
        return itahun;
    }

    public void setItahun(int itahun) {
        this.itahun = itahun;
    }

    public String getVmodulename() {
        return vmodulename;
    }

    public void setVmodulename(String vmodulename) {
        this.vmodulename = vmodulename;
    }
    
    
    
}
