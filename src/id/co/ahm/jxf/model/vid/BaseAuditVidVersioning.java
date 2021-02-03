/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.model.vid;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import org.hibernate.envers.Audited;

/**
 *
 * @author achmad.ha
 */
@MappedSuperclass
@Audited
public class BaseAuditVidVersioning extends BaseAuditVidImpl implements Serializable{
    
    @Version
    @Column(name="IVER",nullable=false)
    private Integer iver;

    public Integer getIver() {
        return iver;
    }

    public void setIver(Integer iver) {
        this.iver = iver;
    }
}
