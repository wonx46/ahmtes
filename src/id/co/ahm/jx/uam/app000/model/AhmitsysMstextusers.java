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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author achmad.ha
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="AHMITSYS_MSTEXTUSERS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"VNAMALDAP"}))
public class AhmitsysMstextusers extends DefaultEntityImpl implements Serializable{
    
    @Id
    @Column(name = "VUSERID",length = 12,nullable = false)
    private String vuserid;
    
    @Column(name = "VUSERNAME",length = 64,nullable = false)
    private String vusername;
    
    @Column(name = "VNAMALDAP",length = 32)
    private String vnamaldap;
    
    @Column(name = "VPARTNERID",length = 10,nullable = false)
    private String vpartnerid;
        
    @Column(name = "VUSERAREA",length = 12,nullable = false)
    private String vuserarea;
        
    @Column(name = "VEMAIL",length = 100)
    private String vemail;
    
    @Column(name = "bnonaktif",precision = 1)
    private int bnonaktif;
    
    @Transient
    private String password;
    @Transient
    private String passwordConfirm;
    @Transient
    private String vDataType;
    @Transient
    private String vUserType;	//MD, dealer or vendor
    @Transient
    private String vLoginType;	//INT or EXT
    

    public String getVuserid() {
        return vuserid;
    }

    public void setVuserid(String vuserid) {
        this.vuserid = vuserid;
    }

    public String getVusername() {
        return vusername;
    }

    public void setVusername(String vusername) {
        this.vusername = vusername;
    }

    public String getVnamaldap() {
        return vnamaldap;
    }

    public void setVnamaldap(String vnamaldap) {
        this.vnamaldap = vnamaldap;
    }

    public String getVpartnerid() {
        return vpartnerid;
    }

    public void setVpartnerid(String vpartnerid) {
        this.vpartnerid = vpartnerid;
    }

    public String getVuserarea() {
        return vuserarea;
    }

    public void setVuserarea(String vuserarea) {
        this.vuserarea = vuserarea;
    }

    public String getVemail() {
        return vemail;
    }

    public void setVemail(String vemail) {
        this.vemail = vemail;
    }

    public int getBnonaktif() {
        return bnonaktif;
    }

    public void setBnonaktif(int bnonaktif) {
        this.bnonaktif = bnonaktif;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getvDataType() {
        return vDataType;
    }

    public void setvDataType(String vDataType) {
        this.vDataType = vDataType;
    }

    public String getvUserType() {
        return vUserType;
    }

    public void setvUserType(String vUserType) {
        this.vUserType = vUserType;
    }

    public String getvLoginType() {
        return vLoginType;
    }

    public void setvLoginType(String vLoginType) {
        this.vLoginType = vLoginType;
    }
}
