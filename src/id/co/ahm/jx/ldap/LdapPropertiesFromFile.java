package id.co.ahm.jx.ldap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.annotation.PostConstruct;

public class LdapPropertiesFromFile extends LdapProperties {

    private String filePath;

    @PostConstruct
    public void postConstruct() {
        if (filePath == null) {
            throw new RuntimeException("Can not continue without filePath configuration");
        }
        readValueFromProperties();
    }

    private void readValueFromProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(filePath);

            // load a properties file
            prop.load(input);

            updateValueFromProperties(prop);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Error while trying to read from properties file", ex);
            throw new RuntimeException("Error while trying to read from properties file", ex);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("Error while trying to close stream from properties file", e);
                    throw new RuntimeException("Error while trying to close stream from properties file", e);
                }
            }
        }

    }

    private void updateValueFromProperties(Properties prop) {
        String domainName = prop.getProperty("domainName");
        String domainRoot = prop.getProperty("domainRoot");
        String domainUrl = prop.getProperty("domainUrl");
        String adminName = prop.getProperty("adminName");
        String adminPass = prop.getProperty("adminPass");
        String adminPassEncrypted = prop.getProperty("adminPassEncrypted");
        String searchBase = prop.getProperty("searchBase");
        String isBypass = prop.getProperty("isBypass");

        setDomainName(domainName);
        setDomainRoot(domainRoot);
        setDomainUrl(domainUrl);
        setAdminName(adminName);
        setAdminPass(adminPass);
        setAdminPassEncrypted(adminPassEncrypted);
        setSearchBase(searchBase);
        setIsBypass(Boolean.parseBoolean(isBypass));
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
