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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author achmad.ha
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="AHMMOMSC_MSTVENDORS")
public class AhmmomscMstvendors extends DefaultEntityImpl implements Serializable{
    
    @Id
    @Column(name = "VVENDORID")
    private String vvendorid;
    
    @Column(name = "VVENDORDESC")
    private String vvendordesc;
    
    @Column(name="VREFFCODE")
    private String vreffcode;
    
    @Column(name = "VACCGROUP")
    private String vaccgroup;
    
    @Column(name = "VACCDESC")
    private String vaccdesc;

    public String getVreffcode() {
        return vreffcode;
    }

    public void setVreffcode(String vreffcode) {
        this.vreffcode = vreffcode;
    }

    public String getVvendorid() {
        return vvendorid;
    }

    public void setVvendorid(String vvendorid) {
        this.vvendorid = vvendorid;
    }

    public String getVvendordesc() {
        return vvendordesc;
    }

    public void setVvendordesc(String vvendordesc) {
        this.vvendordesc = vvendordesc;
    }

    public String getVaccgroup() {
        return vaccgroup;
    }

    public void setVaccgroup(String vaccgroup) {
        this.vaccgroup = vaccgroup;
    }

    public String getVaccdesc() {
        return vaccdesc;
    }

    public void setVaccdesc(String vaccdesc) {
        this.vaccdesc = vaccdesc;
    }    
}
