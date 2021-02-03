/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author achmad.ha
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="AHMIPUAM_MSTMENUS")
public class AhmipuamMstmenus extends DefaultEntityImpl implements Serializable{
        
    @Id
    @SequenceGenerator(name = "menuSeq", sequenceName = "SE_MSTMENUS")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "menuSeq")
    @Column(name = "IINTERNALID")
    private Long iinternalid;
    
    @Column(name = "VMENUCODE")
    private String vmenucode;
    
    @Column(name = "IAPPTARGET")
    private Integer iapptarget;
    
    @Column(name = "VMENUTITLE")
    private String vmenutitle;
    
    @Column(name = "VMENUDESC")
    private String vmenudesc;
    
    @Column(name = "VMENUURL")
    private String vmenuurl;
    
    @Column(name = "VONCLICK")
    private String vonclick;
    
    @Column(name = "VDEVICEFLAG")
    private String vdeviceflag;
    
    @Column(name = "VENABLEFLAG")
    private String venableflag;
    
    /*
     * Menu has parent. In database, root menu has parent = "" instead of NULL !!! 
     * Hibernate will try to find menu with id = "", resulting Entity Not Found, then throw exception.
     * Hibernate annot has @NotFound, but no equivalent annot exist in JPA.
     * either fetch set to lazy, or fix db value if want to stick with jpa annotation.
     * or use mixed hibernate/jpa annotation.
     */
    @ManyToOne(targetEntity = AhmipuamMstmenus.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "IPARENTID", referencedColumnName = "IINTERNALID",insertable=false,updatable=false)
    @NotFound(action = NotFoundAction.IGNORE)
    private AhmipuamMstmenus parent;
    
    @OneToMany(targetEntity = AhmipuamMstmenus.class,fetch = FetchType.LAZY)
    @JoinColumn(name="IPARENTID", referencedColumnName="IINTERNALID",insertable = false,updatable = false)    
    @NotFound(action= NotFoundAction.IGNORE)    
    private List<AhmipuamMstmenus> child;
    
    @Column(name = "IPARENTID")
    private Long iparentid;
    
    @Column(name = "VPARENTCODE")
    private String vparentcode;
    
    @Column(name = "IMENULEVEL")
    private Integer imenulevel;
    
    @Column(name = "IMENUORDER")
    private Integer imenuorder;

    public Long getIinternalid() {
        return iinternalid;
    }

    public void setIinternalid(Long iinternalid) {
        this.iinternalid = iinternalid;
    }

    public String getVmenucode() {
        return vmenucode;
    }

    public void setVmenucode(String vmenucode) {
        this.vmenucode = vmenucode;
    }

    public Integer getIapptarget() {
        return iapptarget;
    }

    public void setIapptarget(Integer iapptarget) {
        this.iapptarget = iapptarget;
    }

    public String getVmenutitle() {
        return vmenutitle;
    }

    public void setVmenutitle(String vmenutitle) {
        this.vmenutitle = vmenutitle;
    }

    public String getVmenudesc() {
        return vmenudesc;
    }

    public void setVmenudesc(String vmenudesc) {
        this.vmenudesc = vmenudesc;
    }

    public String getVmenuurl() {
        return vmenuurl;
    }

    public void setVmenuurl(String vmenuurl) {
        this.vmenuurl = vmenuurl;
    }

    public String getVonclick() {
        return vonclick;
    }

    public void setVonclick(String vonclick) {
        this.vonclick = vonclick;
    }

    public String getVdeviceflag() {
        return vdeviceflag;
    }

    public void setVdeviceflag(String vdeviceflag) {
        this.vdeviceflag = vdeviceflag;
    }

    public String getVenableflag() {
        return venableflag;
    }

    public void setVenableflag(String venableflag) {
        this.venableflag = venableflag;
    }

    public AhmipuamMstmenus getParent() {
        return parent;
    }

    public void setParent(AhmipuamMstmenus parent) {
        this.parent = parent;
    }

    public List<AhmipuamMstmenus> getChild() {
        return child;
    }

    public void setChild(List<AhmipuamMstmenus> child) {
        this.child = child;
    }
    
    public Long getIparentid() {
        return iparentid;
    }

    public void setIparentid(Long iparentid) {
        this.iparentid = iparentid;
    }

    public String getVparentcode() {
        return vparentcode;
    }

    public void setVparentcode(String vparentcode) {
        this.vparentcode = vparentcode;
    }

    public Integer getImenulevel() {
        return imenulevel;
    }

    public void setImenulevel(Integer imenulevel) {
        this.imenulevel = imenulevel;
    }

    public Integer getImenuorder() {
        return imenuorder;
    }

    public void setImenuorder(Integer imenuorder) {
        this.imenuorder = imenuorder;
    }

}
