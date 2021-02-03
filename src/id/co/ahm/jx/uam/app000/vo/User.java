/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.vo;

import java.io.Serializable;

/**
 *
 * @author destri.hs
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3700602803022970069L;
    public static final String USER_DATA_TYPE_ALL = "ALL";
    public static final String USER_DATA_TYPE_EXT = "EXT";

    private String extUser_UserId;
    private String username;
    private String vFullName;
    private String vEmail;
    private String vType;
    private String vVendor;
    private String vMdType;
    private String vDtype;
    private String vUserType;
    private String password;
    private String passwordConfirm;
    private String vDataType;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getvFullName() {
        return vFullName;
    }

    public void setvFullName(String vFullName) {
        this.vFullName = vFullName;
    }

    public String getvEmail() {
        return vEmail;
    }

    public void setvEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public String getvVendor() {
        return vVendor;
    }

    public void setvVendor(String vVendor) {
        this.vVendor = vVendor;
    }

    public String getvMdType() {
        return vMdType;
    }

    public void setvMdType(String vMdType) {
        this.vMdType = vMdType;
    }

    public String getvDtype() {
        return vDtype;
    }

    public void setvDtype(String vDtype) {
        this.vDtype = vDtype;
    }

    public String getvUserType() {
        return vUserType;
    }

    public void setvUserType(String vUserType) {
        this.vUserType = vUserType;
    }

    public String getvDataType() {
        return vDataType;
    }

    public void setvDataType(String vDataType) {
        this.vDataType = vDataType;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getExtUser_UserId() {
        return extUser_UserId;
    }

    public void setExtUser_UserId(String extUser_UserId) {
        this.extUser_UserId = extUser_UserId;
    }
}
