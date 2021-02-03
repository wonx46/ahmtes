/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.model.vid;

import id.co.ahm.jxf.model.base.AbstractBaseEntity;
import id.co.ahm.jxf.model.base.BaseAudit;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.Audited;

/**
 *
 * @author AFI
 */
@MappedSuperclass
@Audited
public class BaseAuditVidImpl extends AbstractBaseEntity implements BaseAudit {

    @Column(name = "VCREA",length = 12,nullable = false)
    private String createBy;

    @Column(name = "VMODI",length=12)
    private String lastModBy;

    @Column(name = "DCREA",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "DMODI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModDate;

    @Column(name = "VIDSERVICE")
    private String vidservice;
    
    @Override
    public String getCreateBy() {
        return createBy;
    }
    
    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String getLastModBy() {
        return this.lastModBy;
    }

    @Override
    public void setLastModBy(String lastModBy) {
        this.lastModBy = lastModBy;
    }

    @Override
    public Date getLastModDate() {
        return this.lastModDate;
    }

    @Override
    public void setLastModDate(Date lastModDate) {
        this.lastModDate = lastModDate;
    }

    @Override
    public void setService(String idservice){
        this.vidservice = idservice;
    }

    @Override
    public String getService(){
        return this.vidservice;
    }
}
