package id.co.ahm.jx.ldap.service.impl;

import id.co.ahm.jx.ldap.LdapCommon;
import id.co.ahm.jx.ldap.LdapProperties;
import id.co.ahm.jx.ldap.service.LdapExternalService;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("ldapExternalService")
@Scope("prototype")
public class LdapExternalServiceImpl extends GenericLdapServiceImpl implements
        LdapExternalService {

    private static final long serialVersionUID = -3679141251255044900L;

    @Autowired
    @Qualifier("ldapExternalProperties")
    @Override
    public void setLdapProperties(LdapProperties ldapProperties) {
        this.ldapProperties = ldapProperties;
    }

    @PostConstruct
    public void postConstruct() {
//        System.out.println("Construct LdapExternalService====================");
        ldapCommon = new LdapCommon(ldapProperties);
    }

    @PreDestroy
    public void preDestroy() {
//        System.out.println("Destroy LdapExternalService======================");
        ldapCommon.close();
        ldapCommon = null;
    }
}
