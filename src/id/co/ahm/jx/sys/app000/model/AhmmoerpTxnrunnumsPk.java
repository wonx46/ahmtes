/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.sys.app000.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author okky.ms
 */
@Embeddable
public class AhmmoerpTxnrunnumsPk implements Serializable {
    
    @Column(name = "VIDNAME")
    private String vidname;
    @Column(name = "VRESET")
    private String vreset;

    public String getVidname() {
        return vidname;
    }

    public void setVidname(String vidname) {
        this.vidname = vidname;
    }

    public String getVreset() {
        return vreset;
    }

    public void setVreset(String vreset) {
        this.vreset = vreset;
    }

}
