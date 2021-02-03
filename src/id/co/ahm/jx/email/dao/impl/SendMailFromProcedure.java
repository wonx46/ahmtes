/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.email.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.jdbc.Work;

/**
 *
 * @author hendra.fs
 */
public class SendMailFromProcedure implements Work {

    private String from;
    private String subject;
    private String to;
    private String cc;
    private String content;
    private String result;

    public SendMailFromProcedure(String from, String subject, String to, String cc, String content) {
        this.from = from;
        this.subject = subject;
        this.to = to;
        this.cc = cc;
        this.content = content;
    }
    
    public String getResult() {
        return result;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        CallableStatement call = connection.prepareCall("{call PROCAHMPSSENDMAIL(?,?,?,?,?,?)}");
    
        call.setString(1, from); // Parameter IN
        call.setString(2, subject); // Parameter IN
        call.setString(3, to); // Parameter IN
        call.setString(4, cc); // Parameter IN
        call.setString(5, content); // Parameter IN
        call.registerOutParameter(6, Types.VARCHAR); // Parameter OUT
        
        // Execute PROCAHMPSSENDMAIL store procedure
        call.executeUpdate();
        result = call.getString(6);
    }
}
