/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.ldap;

import id.co.ahm.jxf.security.CryptoSecurity;
import java.io.Serializable;
import java.security.Key;
import org.apache.log4j.Logger;

public class LdapProperties implements Serializable {

    private static final long serialVersionUID = -7395874522702130196L;
    static Logger logger = Logger.getLogger(LdapProperties.class);
    private String domainName;
    private String domainRoot;
    private String domainUrl;
    private String adminName;
    private String adminPass;
    private String adminPassEncrypted;
    private String searchBase;
    private Boolean isBypass;

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainRoot() {
        return domainRoot;
    }

    public void setDomainRoot(String domainRoot) {
        this.domainRoot = domainRoot;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPass() {
        try {
            if (adminPass == null && adminPassEncrypted != null) {                
                this.adminPass = CryptoSecurity.decrypt(this.adminPassEncrypted);
            }
        } catch (Exception ex) {
            logger.error("unable to decrypt admin password", ex);
        }
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getDomainUrl() {
        return domainUrl;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    public String getSearchBase() {
        return searchBase;
    }

    public void setSearchBase(String searchBase) {
        this.searchBase = searchBase;
    }

    public Boolean getIsBypass() {
        return isBypass;
    }

    public void setIsBypass(Boolean isBypass) {
        this.isBypass = isBypass;
    }

    public String getAdminPassEncrypted() {
        return adminPassEncrypted;
    }

    public void setAdminPassEncrypted(String adminPassEncrypted) {
        this.adminPassEncrypted = adminPassEncrypted;
    }
}
