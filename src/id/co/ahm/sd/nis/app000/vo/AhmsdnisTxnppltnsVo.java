/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app000.vo;

import id.co.ahm.sd.nis.app000.utils.MapXls;
import java.util.Date;

/**
 *
 * @author admin
 */
public class AhmsdnisTxnppltnsVo {

    @MapXls(indexXls = 0)
    private String nyear;

    @MapXls(indexXls = 1)
    private String vbpsprvnm;

    @MapXls(indexXls = 2)
    private String vbpsprvcd;

    @MapXls(indexXls = 3)
    private String vbpsdstrnm;

    @MapXls(indexXls = 4)
    private String vbpsdstrcd;

    @MapXls(indexXls = 5)
    private String vagerng;

    @MapXls(indexXls = 6)
    private Integer nmale;

    @MapXls(indexXls = 7)
    private Integer nfemale;

    @MapXls(indexXls = 8)
    private Date dbgneff;

    @MapXls(indexXls = 9)
    private Date dlasteff;

    public Date getDbgneff() {
        return dbgneff;
    }

    public void setDbgneff(Date dbgneff) {
        this.dbgneff = dbgneff;
    }

    public Date getDlasteff() {
        return dlasteff;
    }

    public void setDlasteff(Date dlasteff) {
        this.dlasteff = dlasteff;
    }


    public String getNyear() {
        return nyear;
    }

    public void setNyear(String nyear) {
        this.nyear = nyear;
    }

    public String getVbpsprvnm() {
        return vbpsprvnm;
    }

    public void setVbpsprvnm(String vbpsprvnm) {
        this.vbpsprvnm = vbpsprvnm;
    }

    public String getVbpsprvcd() {
        return vbpsprvcd;
    }

    public void setVbpsprvcd(String vbpsprvcd) {
        this.vbpsprvcd = vbpsprvcd;
    }

    public String getVbpsdstrnm() {
        return vbpsdstrnm;
    }

    public void setVbpsdstrnm(String vbpsdstrnm) {
        this.vbpsdstrnm = vbpsdstrnm;
    }

    public String getVbpsdstrcd() {
        return vbpsdstrcd;
    }

    public void setVbpsdstrcd(String vbpsdstrcd) {
        this.vbpsdstrcd = vbpsdstrcd;
    }

    public String getVagerng() {
        return vagerng;
    }

    public void setVagerng(String vagerng) {
        this.vagerng = vagerng;
    }

    public Integer getNmale() {
        return nmale;
    }

    public void setNmale(Integer nmale) {
        this.nmale = nmale;
    }

    public Integer getNfemale() {
        return nfemale;
    }

    public void setNfemale(Integer nfemale) {
        this.nfemale = nfemale;
    }

}
