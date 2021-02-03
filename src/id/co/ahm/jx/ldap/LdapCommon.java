/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.ldap;

import id.co.ahm.jx.uam.app000.vo.User;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author destri.hs
 */
public class LdapCommon {

    static Logger logger = Logger.getLogger(LdapCommon.class);
//	private static final String DOMAIN_NAME = "DOMAINDEV";
//	private static final String DOMAIN_ROOT = "DC=CORP,DC=DOMAINDEV,DC=COM";
//	private static final String DOMAIN_URL = "ldaps://WIN-JK2D38WC9KM:636";
//	private static final String ADMIN_NAME = "CN=Administrator,CN=Users,DC=CORP,DC=DOMAINDEV,DC=COM";
//	private static final String ADMIN_PASS = "Whiteopen123";
//	private static final String SEARCH_BASE = "CN=USERS,DC=CORP,DC=DOMAINDEV,DC=COM";
//	private static final String ORGANIZATION_UNIT = "USERS";
    private static final String AD_ATTR_NAME_TOKEN_GROUPS = "tokenGroups";
    private static final String AD_ATTR_NAME_OBJECT_CLASS = "objectClass";
    private static final String AD_ATTR_NAME_OBJECT_CATEGORY = "objectCategory";
    private static final String AD_ATTR_NAME_MEMBER = "member";
    private static final String AD_ATTR_NAME_MEMBER_OF = "memberOf";
    private static final String AD_ATTR_NAME_DESCRIPTION = "description";
    private static final String AD_ATTR_NAME_OBJECT_GUID = "objectGUID";
    private static final String AD_ATTR_NAME_OBJECT_SID = "objectSid";
    private static final String AD_ATTR_NAME_DISTINGUISHED_NAME = "distinguishedName";
    private static final String AD_ATTR_NAME_CN = "cn";
    private static final String AD_ATTR_NAME_SN = "sn";
    private static final String AD_ATTR_NAME_UID = "uid";
    private static final String AD_ATTR_NAME_USER_PRINCIPAL_NAME = "userPrincipalName";
    private static final String AD_ATTR_NAME_EMAIL = "mail";
    private static final String AD_ATTR_NAME_GROUP_TYPE = "groupType";
    private static final String AD_ATTR_NAME_SAM_ACCOUNT_NAME = "sAMAccountName";
    private static final String AD_ATTR_NAME_SAM_ACCOUNT_TYPE = "sAMAccountType";
    private static final String AD_ATTR_NAME_USER_ACCOUNT_CONTROL = "userAccountControl";
    private static final String AD_ATTR_NAME_GIVEN_NAME = "givenName";
    private static final String AD_ATTR_NAME_UNICODE_PWD = "UnicodePwd";
    private String domainName;
    private String domainRoot;
    private String domainUrl;
    private String adminName;
    private String adminPass;
    private String searchBase;
    private LdapContext context;

    public LdapCommon(String domainName, String domainRoot, String domainUrl,
            String adminName, String adminPass, String searchBase,
            String organizationUnit) {
        super();
        this.domainName = domainName;
        this.domainRoot = domainRoot;
        this.domainUrl = domainUrl;
        this.adminName = adminName;
        this.adminPass = adminPass;
        this.searchBase = searchBase;
    }

    public LdapCommon(LdapProperties ldapProperties) {
        super();
        this.domainName = ldapProperties.getDomainName();
        this.domainRoot = ldapProperties.getDomainRoot();
        this.domainUrl = ldapProperties.getDomainUrl();
        this.adminName = ldapProperties.getAdminName();
        this.adminPass = ldapProperties.getAdminPass();
        this.searchBase = ldapProperties.getSearchBase();
    }

    public void getLdapContext() throws NamingException {        
        context = createLdapContext(adminName, adminPass);
    }

    private LdapContext createLdapContext(String name, String pass) throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
//        System.out.println("pass : "+pass);
//        System.out.println("name : "+name);
        // set security credentials, note using simple cleartext authentication
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, name);
        env.put(Context.SECURITY_CREDENTIALS, pass);

        // connect to my domain controller
        env.put(Context.PROVIDER_URL, domainUrl);
        // System.setProperty("java.naming.ldap.factory.socket",
        // "com.whiteopen.ldap.common.DummySSLSocketFactory");

        if (StringUtils.startsWith(domainUrl, "ldaps:")) {
            env.put(Context.SECURITY_PROTOCOL, "ssl");
        }


        // set the environment
        // super.setupAuthenticatedEnvironment(env);
        // set the base environment again
        // super.setBaseEnvironmentProperties(env);

        // System.setProperty("javax.net.ssl.trustStore", trustStore);
        // System.setProperty("javax.net.ssl.keyStore", keyStore);
        // System.setProperty("javax.net.ssl.keyStorePassword",
        // keyStorePassword);

        // it is necessary to call super.afterPropertiesSet() again!!!
        // super.afterPropertiesSet();
        LdapContext context = null;
        try {
//            System.out.println("Create new InitialLdapContext..");
            context = new InitialLdapContext(env, null);
        } catch (NamingException e) {
            System.err.println("Problem creating object: ");
            e.printStackTrace();
            throw e;
        }
        return context;
    }

    public boolean addUser(String userName, String firstName, String lastName,
            String password, String email) throws NamingException,
            UnsupportedEncodingException {
        System.out.println("userName : "+userName);
        System.out.println("password : "+password);
        // Create a container set of attributes
        Attributes container = new BasicAttributes();

        // Create the objectclass to add
        Attribute objClasses = new BasicAttribute(AD_ATTR_NAME_OBJECT_CLASS);
        objClasses.add("top");
        objClasses.add("person");
        objClasses.add("organizationalPerson");
        objClasses.add("user");

        // Assign the username, first name, and last name
        String cnValue = userName;
        Attribute cn = new BasicAttribute(AD_ATTR_NAME_CN, cnValue);
        Attribute sAMAccountName = new BasicAttribute(
                AD_ATTR_NAME_SAM_ACCOUNT_NAME, userName);
        Attribute principalName = new BasicAttribute(
                AD_ATTR_NAME_USER_PRINCIPAL_NAME, userName + "@" + domainName);
        Attribute givenName = new BasicAttribute(AD_ATTR_NAME_GIVEN_NAME,
                firstName);
        Attribute sn = new BasicAttribute(AD_ATTR_NAME_SN, lastName);
        Attribute uid = new BasicAttribute(AD_ATTR_NAME_UID, userName);
        Attribute attrEmail = new BasicAttribute(AD_ATTR_NAME_EMAIL, email);

        // Add password
        try {
//			int UF_ACCOUNTDISABLE = 0x0002;
//			int UF_PASSWD_NOTREQD = 0x0020;
//			int UF_PASSWD_CANT_CHANGE = 0x0040;
//			int UF_NORMAL_ACCOUNT = 0x0200;
//			int UF_DONT_EXPIRE_PASSWD = 0x10000;
//			int UF_PASSWORD_EXPIRED = 0x800000;
            // Attribute userPassword = new BasicAttribute("userpassword",
            // encodePassword(password));
            Attribute userPassword = new BasicAttribute(
                    AD_ATTR_NAME_UNICODE_PWD, encodePassword(password));
            // Attribute accountEnable = new
            // BasicAttribute("msDS-UserAccountDisabled", "FALSE");
            // Attribute donEx = new
            // BasicAttribute("msDS-UserDontExpirePassword", "TRUE");
//            Attribute accountEnable = new BasicAttribute(
//                    AD_ATTR_NAME_USER_ACCOUNT_CONTROL, "512");
            Attribute accountEnable = new BasicAttribute(
                    AD_ATTR_NAME_USER_ACCOUNT_CONTROL, "66080");
            // Attribute userPassword = new BasicAttribute("userpassword",
            // password);
			/*
             * Attribute userControl = new BasicAttribute(AD_ATTR_NAME_USER_ACCOUNT_CONTROL,
             * Integer.toString(UF_NORMAL_ACCOUNT + UF_PASSWD_NOTREQD +
             * UF_PASSWORD_EXPIRED + UF_ACCOUNTDISABLE));
             */

            // Add these to the container
            container.put(objClasses);
            container.put(sAMAccountName);
            container.put(principalName);
            container.put(cn);
            container.put(sn);
            container.put(givenName);
            container.put(uid);
            container.put(userPassword);
            container.put(accountEnable);
            container.put(attrEmail);
            // container.put(donEx);

            // Create the entry

            context.createSubcontext(getUserDN(cnValue), container);
//            System.out.println("successfuly add user to AD...");

            return true;
        } catch (NamingException e) {
            e.printStackTrace();
            throw e;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public boolean deleteUser(String username) throws NamingException {
        context.destroySubcontext(getUserDN(username));
//        System.out.println("successfuly delete user from AD...");

        return true;
    }

    public boolean updateUserPassword(String username, String password)
            throws NamingException, UnsupportedEncodingException {
        if (context == null) {
            getLdapContext();
        }

        Attribute userPassword = new BasicAttribute(AD_ATTR_NAME_UNICODE_PWD,
                encodePassword(password));
        ModificationItem mod = new ModificationItem(
                DirContext.REPLACE_ATTRIBUTE, userPassword);
        
        context.modifyAttributes(getUserDN(username),
                new ModificationItem[]{mod});
        
        System.out.println("successfuly update user to AD...");

        return true;
    }

    public boolean updateUserEmail(String username, String email)
            throws NamingException {

        if (context == null) {
            getLdapContext();
        }

        Attribute attrEmail = new BasicAttribute(AD_ATTR_NAME_EMAIL, email);
        ModificationItem mod = new ModificationItem(
                DirContext.REPLACE_ATTRIBUTE, attrEmail);

        context.modifyAttributes(getUserDN(username),
                new ModificationItem[]{mod});

//        System.out.println("successfuly update user to AD...");
        return true;
    }

    public boolean updateUserPasswordAndEmail(String username, String password,
            String email) throws NamingException, UnsupportedEncodingException {
        if (context == null) {
            getLdapContext();
        }
        Attribute userPassword = new BasicAttribute(AD_ATTR_NAME_UNICODE_PWD,
                encodePassword(password));
        ModificationItem modPassword = new ModificationItem(
                DirContext.REPLACE_ATTRIBUTE, userPassword);

        Attribute attrEmail = new BasicAttribute(AD_ATTR_NAME_EMAIL, email);
        ModificationItem modEmail = new ModificationItem(
                DirContext.REPLACE_ATTRIBUTE, attrEmail);

        context.modifyAttributes(getUserDN(username), new ModificationItem[]{
                    modPassword, modEmail});

        System.out.println("successfuly update user to AD...");

        return true;
    }

    private String getUserDN(String username) {
        return "cn=" + username + "," + domainRoot;
    }

    private byte[] encodePassword(String password)
            throws UnsupportedEncodingException {
//		 nb : step encode base on (unicode password Active Directory format) :
//				1. modify password value :  
//		      	- add double quote char at first and last index
//		      	- insert space char between ever char, so password char length become double
//		 			example :
//		 			1. abc => " a b c "
//		 			2. whiteopen => " w h i t e o p e n "
//				2. convert to charset to unicode byte[]

        String quotedPassword = "\"" + password + "\"";
        char unicodePwd[] = quotedPassword.toCharArray();
        byte pwdArray[] = new byte[unicodePwd.length * 2];
        for (int i = 0; i < unicodePwd.length; i++) {
            pwdArray[i * 2 + 1] = (byte) (unicodePwd[i] >>> 8);
            pwdArray[i * 2 + 0] = (byte) (unicodePwd[i] & 0xff);
        }
        return pwdArray;
    }

    private static String decodePassword(byte[] encodePassword) {
//		 nb : step decode base on (unicode password Active Directory format) :
//				1. convert charset unicode byte[] to UTF-8 string
//				2. modify result value :  
//		      	- remote first and last char
//		      	- remove all even index char
//		 			example :
//		 			1. " a b c " => abc
//		 			2. " w h i t e o p e n " => whiteopen

        String result = new String(encodePassword, Charset.forName("UTF-8"));
        if (!StringUtils.isEmpty(result)) {
            String temp = StringUtils.EMPTY;
            // remove all even index char
            for (int i = 0; i < result.length(); i++) {
                // get all odd index char
                if (i % 2 == 0) {
                    temp += result.charAt(i);
                }
            }
            result = temp;

            // remove char first and last index
            char[] tempByte = new char[result.length() - 2];
            result.getChars(1, result.length() - 1, tempByte, 0);

            result = String.valueOf(tempByte);
        }

        return result;
    }

    public boolean isUserExist(String username) throws NamingException {
        try {
            // Create the search controls
            SearchControls searchCtls = new SearchControls();

            // Specify the attributes to return
            String returnedAtts[] = {AD_ATTR_NAME_SN, AD_ATTR_NAME_CN};
            searchCtls.setReturningAttributes(returnedAtts);

            // Specify the search scope
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // specify the LDAP search filter
            String searchFilter = "(&(objectClass=person))";

            // Specify the Base for the search
            // String searchBase = "CN=USERS,DC=CORP,DC=DOMAINDEV,DC=COM";

            // Search for objects using the filter
            NamingEnumeration answer = context.search(AD_ATTR_NAME_CN + "="
                    + username + "," + searchBase, searchFilter, searchCtls);
//            System.out.println("answer: " + answer);

//            System.out.println("successfuly check user existing at AD...");

            return answer.hasMoreElements();
        } catch (NameNotFoundException e) {
            return false;

        } catch (NamingException e) {
            e.printStackTrace();
            // return null;
            throw e;
        }
    }

    public User retrieveUser(String username) throws NamingException {
        try {
            User result = null;
            // Create the search controls
            SearchControls searchCtls = new SearchControls();

            // Specify the attributes to return
            String returnedAtts[] = {AD_ATTR_NAME_SN, AD_ATTR_NAME_CN,
                AD_ATTR_NAME_EMAIL, AD_ATTR_NAME_UNICODE_PWD};
            searchCtls.setReturningAttributes(returnedAtts);
            searchCtls.setReturningObjFlag(true);

            // Specify the search scope
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // specify the LDAP search filter
            String searchFilter = "(&(objectClass=person))";

            // Specify the Base for the search
            // String searchBase = "CN=USERS,DC=CORP,DC=DOMAINDEV,DC=COM";

            // Search for objects using the filter
            NamingEnumeration answer = context.search(AD_ATTR_NAME_CN + "="
                    + username + "," + searchBase, searchFilter, searchCtls);
//            System.out.println("answer: " + answer);

            // Loop through the search results
            while (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.nextElement();

//				System.out.println(">>>" + sr.getName());

                // Print out some of the attributes, catch the exception if the
                // attributes have no values
                Attributes attrs = sr.getAttributes();
                printOutAttributes(attrs);

                if (attrs != null) {
                    Attribute attr = null;
                    try {
                        result = new User();

                        attr = attrs.get(AD_ATTR_NAME_CN);
//						System.out.println("attr cn  = " + attr);
                        if (attr != null) {
//							System.out.println("attr cn value = " + attr.get());
                            result.setUsername((String) attr.get());
                        }

                        attr = attrs.get(AD_ATTR_NAME_EMAIL);
//						System.out.println("attr email  = " + attr);
                        if (attr != null) {
//							System.out.println("attr email value = "
//									+ attr.get());
                            result.setvEmail((String) attr.get());
                        }

                        attr = attrs.get(AD_ATTR_NAME_UNICODE_PWD);
//						TODO don't know why cannot get unicode password
//						server AD doesn't return Attribute Unicode Password, attrs.get(AD_ATTR_NAME_UNICODE_PWD) always null
//						System.out.println("attr unicode password  = " + attr);
                        if (attr != null) {
//							System.out.println("attr unicode password value = "
//									+ attr.get());
                            result.setPassword((String) attr.get());
                        }
                    } catch (NullPointerException e) {
//                        System.out.println("Errors listing attributes: " + e);
                        e.printStackTrace();
                        throw e;
                    }
                }
            }

//            System.out.println("successfuly retreive user from AD...");

            return result;
        } catch (NameNotFoundException e) {
            return null;

        } catch (NamingException e) {
            e.printStackTrace();
            // return null;
            throw e;
        }
    }

    public List<User> getListUser(String searchUsername) throws NamingException {
        try {
            List<User> result = new ArrayList<User>();
            // Create the search controls
            SearchControls searchCtls = new SearchControls();

            // Specify the attributes to return
            String returnedAtts[] = {AD_ATTR_NAME_SN, AD_ATTR_NAME_CN};
            searchCtls.setReturningAttributes(returnedAtts);

            // Specify the search scope
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            // specify the LDAP search filter
            String searchFilter = null;
            if (StringUtils.isBlank(searchUsername)) {
                searchFilter = "(&(objectClass=person))";
            } else {
                searchFilter = "(&(objectClass=person)(cn=*" + searchUsername
                        + "*))";
            }

            // Specify the Base for the search
            // String searchBase = "CN=USERS,DC=CORP,DC=DOMAINDEV,DC=COM";

            // initialize counter to total the results
            int totalResults = 0;

            // Search for objects using the filter
            NamingEnumeration answer = context.search(searchBase, searchFilter,
                    searchCtls);
//            System.out.println("answer: " + answer);

            // Loop through the search results
            while (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.next();

                totalResults++;

//                System.out.println(">>>" + sr.getName());

                // Print out some of the attributes, catch the exception if the
                // attributes have no values
                Attributes attrs = sr.getAttributes();
                if (attrs != null) {
                    try {
//                        System.out.println("   surname: "
//                                + attrs.get(AD_ATTR_NAME_CN).get());
                        User user = new User();
                        user.setUsername((String) attrs.get(AD_ATTR_NAME_CN)
                                .get());
                        result.add(user);

                    } catch (NullPointerException e) {
//                        System.out.println("Errors listing attributes: " + e);
                        e.printStackTrace();
                        throw e;
                    }
                }

            }

//            System.out.println("Total results: " + totalResults);
//			context.close();

//            System.out.println("successfuly retreive user list from AD...");

            return result;
        } catch (NamingException e) {
            e.printStackTrace();
            // return null;
            throw e;
        }
    }

    public void close() {
        try {
            context.close();
        } catch (Exception e) {
            // do nothing
        }
    }

    private void printOutAttributes(Attributes attrs) {
//        System.out.println("Attributes :");
//        System.out.println("============");
        NamingEnumeration ne = attrs.getAll();
        Attribute attr = null;
        while (ne.hasMoreElements()) {
            attr = (Attribute) ne.nextElement();
            if (attr != null) {
//                System.out.println("Attribute Id = " + attr.getID());
                try {
                    if (attr.get() != null) {
//                        System.out.println("Attribute Type = "
//                                + attr.get().getClass().getName());
                    }
//                    System.out.println("Attribute Value = " + attr.get());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
//                System.out.println();
            } else {
//                System.out.println("Attribute is null");
            }
        }
    }

    public boolean validUserPassword(String username, String password)
            throws Exception {
        try {
            LdapContext ctx = createLdapContext(username + "@" + domainName, password);
            ctx.close();
            return true;
        } catch (Exception ex) {
            logger.error("Error check valid user password for : [" + username + "], " + ex.getMessage(), ex);
            return false;
        }
    }
}
