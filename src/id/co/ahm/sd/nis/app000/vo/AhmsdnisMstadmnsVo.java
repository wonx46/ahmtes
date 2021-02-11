/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app000.vo;

import id.co.ahm.sd.nis.app000.utils.MapXls;

/**
 *
 * @author admin
 */
public class AhmsdnisMstadmnsVo {
    @MapXls(indexXls = 0)
    private String vbpsprvcd;
    @MapXls(indexXls = 2)
    private String vbpsdstrcd;
    @MapXls(indexXls = 1)
    private String vbpsprvnm;
    @MapXls(indexXls = 3)
    private String vbpsdstrnm;

    public String getVbpsprvcd() {
        return vbpsprvcd;
    }

    public void setVbpsprvcd(String vbpsprvcd) {
        this.vbpsprvcd = vbpsprvcd;
    }

    public String getVbpsdstrcd() {
        return vbpsdstrcd;
    }

    public void setVbpsdstrcd(String vbpsdstrcd) {
        this.vbpsdstrcd = vbpsdstrcd;
    }

    public String getVbpsprvnm() {
        return vbpsprvnm;
    }

    public void setVbpsprvnm(String vbpsprvnm) {
        this.vbpsprvnm = vbpsprvnm;
    }

    public String getVbpsdstrnm() {
        return vbpsdstrnm;
    }

    public void setVbpsdstrnm(String vbpsdstrnm) {
        this.vbpsdstrnm = vbpsdstrnm;
    }
    
}
