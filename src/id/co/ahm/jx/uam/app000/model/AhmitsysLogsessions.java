/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author okky.ms
 */
@Entity
@Table(name="AHMITSYS_LOGSESSIONS")
public class AhmitsysLogsessions extends DefaultEntityImpl implements Serializable{
    @Id
    @Column(name = "VSESSIONID")
    private String vsessionid;
    @Column(name = "VNAMALDAP")
    private String vnamaldap;
    @Column(name = "VBUSAREA")
    private String vbusarea;

    public String getVsessionid() {
        return vsessionid;
    }

    public void setVsessionid(String vsessionid) {
        this.vsessionid = vsessionid;
    }

    public String getVnamaldap() {
        return vnamaldap;
    }

    public void setVnamaldap(String vnamaldap) {
        this.vnamaldap = vnamaldap;
    }

    public String getVbusarea() {
        return vbusarea;
    }

    public void setVbusarea(String vbusarea) {
        this.vbusarea = vbusarea;
    }
    
    
}
