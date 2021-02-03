package id.co.ahm.jx.ldap.service.impl;

import id.co.ahm.jx.ldap.LdapCommon;
import id.co.ahm.jx.ldap.LdapProperties;
import id.co.ahm.jx.ldap.service.LdapInternalService;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("ldapInternalService")
@Scope("prototype")
public class LdapInternalServiceImpl extends GenericLdapServiceImpl implements
		LdapInternalService {
	private static final long serialVersionUID = 4523576005712557723L;

	@Autowired
	@Qualifier("ldapInternalProperties")
	@Override
	public void setLdapProperties(LdapProperties ldapProperties) {
		this.ldapProperties = ldapProperties;
	}
	
	@PostConstruct
	public void postConstruct() {
//		System.out.println("Construct LdapInternalService============");
		ldapCommon = new LdapCommon(ldapProperties);
	}

	@PreDestroy
	public void preDestroy() {
//		System.out.println("Destroy LdapInternalService=============");
		ldapCommon.close();
		ldapCommon = null;
	}
}
