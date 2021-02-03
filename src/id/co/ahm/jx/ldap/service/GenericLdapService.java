package id.co.ahm.jx.ldap.service;


import id.co.ahm.jx.uam.app000.vo.User;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.naming.NamingException;

public interface GenericLdapService {

    void getLdapContext() throws NamingException;

    boolean addUser(String userName, String firstName, String lastName,
            String password, String email) throws NamingException,
            UnsupportedEncodingException;

    boolean deleteUser(String username) throws NamingException;

    boolean updateUserPassword(String username, String password)
            throws NamingException, UnsupportedEncodingException;

    boolean updateUserEmail(String username, String email)
            throws NamingException;

    public boolean updateUserPasswordAndEmail(String username, String password,
            String email) throws NamingException, UnsupportedEncodingException;

    List<User> getListUser(String searchUsername) throws NamingException;

    boolean isUserExist(String username) throws NamingException;

    User retrieveUser(String username) throws NamingException;

    void close();

    boolean userPasswordIsValid(String username, String password) throws Exception;
}
