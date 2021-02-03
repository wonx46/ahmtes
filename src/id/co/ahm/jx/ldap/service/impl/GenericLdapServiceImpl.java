package id.co.ahm.jx.ldap.service.impl;

import id.co.ahm.jx.ldap.LdapCommon;
import id.co.ahm.jx.ldap.LdapProperties;
import id.co.ahm.jx.ldap.service.GenericLdapService;
import id.co.ahm.jx.uam.app000.vo.User;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class GenericLdapServiceImpl implements GenericLdapService, Serializable {

    private static final long serialVersionUID = -6159459561982710817L;
    protected LdapProperties ldapProperties;
    protected LdapCommon ldapCommon;

    public void getLdapContext() throws NamingException {
        if (ldapProperties.getIsBypass() != null
                && ldapProperties.getIsBypass()) {
            return;
        }

        ldapCommon.getLdapContext();
    }

    public boolean addUser(String userName, String firstName, String lastName,
            String password, String email) throws NamingException,
            UnsupportedEncodingException {
        if (ldapProperties.getIsBypass() != null
                && ldapProperties.getIsBypass()) {
            return true;
        }

        return ldapCommon.addUser(userName, firstName, lastName, password,
                email);
    }

    public boolean deleteUser(String username) throws NamingException {
        if (ldapProperties.getIsBypass() != null
                && ldapProperties.getIsBypass()) {
            return true;
        }

        return ldapCommon.deleteUser(username);
    }

    public boolean updateUserPassword(String username, String password)
            throws NamingException, UnsupportedEncodingException {
        if (ldapProperties.getIsBypass() != null
                && ldapProperties.getIsBypass()) {
            return true;
        }

        return ldapCommon.updateUserPassword(username, password);
    }

    public boolean updateUserEmail(String username, String email)
            throws NamingException {
        if (ldapProperties.getIsBypass() != null
                && ldapProperties.getIsBypass()) {
            return true;
        }

        return ldapCommon.updateUserEmail(username, email);
    }

    public boolean updateUserPasswordAndEmail(String username, String password,
            String email) throws NamingException, UnsupportedEncodingException {
        if (ldapProperties.getIsBypass() != null
                && ldapProperties.getIsBypass()) {
            return true;
        }

        return ldapCommon.updateUserPasswordAndEmail(username, password, email);
    }

    public List<User> getListUser(String searchUsername) throws NamingException {
        if (ldapProperties.getIsBypass() != null
                && ldapProperties.getIsBypass()) {
            return new ArrayList<User>();
        }

        return ldapCommon.getListUser(searchUsername);
    }

    public boolean isUserExist(String username) throws NamingException {
        if (ldapProperties.getIsBypass() != null
                && ldapProperties.getIsBypass()) {
            return false;
        }

        return ldapCommon.isUserExist(username);
    }

    public User retrieveUser(String username) throws NamingException {
        if (ldapProperties.getIsBypass() != null
                && ldapProperties.getIsBypass()) {
            User user = new User();
            user.setUsername(username);
            user.setvEmail("dummy@domain.com");
            return user;
        }

        return ldapCommon.retrieveUser(username);
    }

    @Override
    public boolean userPasswordIsValid(String username, String password) throws Exception {
        return ldapCommon.validUserPassword(username, password);
    }

    public void close() {
        ldapCommon.close();
    }

    public LdapProperties getLdapProperties() {
        return ldapProperties;
    }

    public void setLdapProperties(LdapProperties ldapProperties) {
        this.ldapProperties = ldapProperties;
    }

    public LdapCommon getLdapCommon() {
        return ldapCommon;
    }

    public void setLdapCommon(LdapCommon ldapCommon) {
        this.ldapCommon = ldapCommon;
    }
}
