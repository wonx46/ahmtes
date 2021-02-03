/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.model;

import id.co.ahm.jxf.model.vid.BaseAuditVidVersioning;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author achmad.ha
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "AHMJXSYS_MSTFILES")
public class AhmjxsysMstfiles 
        extends BaseAuditVidVersioning 
        implements Serializable{
    
    @Column(name = "VMIMETYPE" , length = 128 , nullable = false)
    private String vmimetype;
    
    @Column(name = "VFILENAME" , length = 128 , nullable = false)
    private String vfilename;
    
    @Column(name = "NFILESIZE" , scale = 20 ,nullable = false )
    private long nfilesize;
    
    @Lob
    @Column(name="BFILECONTENT",nullable=false)
    private byte[] bfilecontent;

    public String getVmimetype() {
        return vmimetype;
    }

    public void setVmimetype(String vmimetype) {
        this.vmimetype = vmimetype;
    }

    public String getVfilename() {
        return vfilename;
    }

    public void setVfilename(String vfilename) {
        this.vfilename = vfilename;
    }

    public long getNfilesize() {
        return nfilesize;
    }

    public void setNfilesize(long nfilesize) {
        this.nfilesize = nfilesize;
    }

    public byte[] getBfilecontent() {
        return bfilecontent;
    }

    public void setBfilecontent(byte[] bfilecontent) {
        this.bfilecontent = bfilecontent;
    }
}
