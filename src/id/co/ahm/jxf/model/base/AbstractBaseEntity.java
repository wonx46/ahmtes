/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.model.base;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author AFI
 */
@MappedSuperclass
public class AbstractBaseEntity implements Serializable{   
    
    @Id
    @Column(name="VID", length=64, unique=true)
    @GeneratedValue(generator ="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "id.co.ahm.jxf.model.base.UUIDCustomGenerator")
    private String vid;

    public AbstractBaseEntity() {
    }

    public AbstractBaseEntity(String vid) {
        this.vid = vid;
    }   

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    
}
