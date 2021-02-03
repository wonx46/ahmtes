/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.model.auditor;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContext;

/**
 *
 * @author achmad
 */
public class AuditLogListener implements RevisionListener {

    @Override
    public void newRevision(Object o) {
        AuditLog obj = (AuditLog) o;
        // sementara hardcoded dulu, nantinya ambil dari user yang sedang login
        obj.setUsername("System");        
    }

}
