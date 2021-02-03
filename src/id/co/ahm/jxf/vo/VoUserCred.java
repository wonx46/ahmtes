/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.co.ahm.jxf.json.CustomddMMyyyyHHmmssDeserializer;
import id.co.ahm.jxf.json.CustomddMMyyyyHHmmssSerializer;
import java.util.Date;
import java.util.List;

/**
 *
 * @author achmad.ha
 */
public class VoUserCred {

    private String username;
    private String userid;
    private String email;

    @JsonSerialize(using = CustomddMMyyyyHHmmssSerializer.class)
    @JsonDeserialize(using = CustomddMMyyyyHHmmssDeserializer.class)
    private Date reqdate;

    private String appid;

    private String domain;

    private String name;

    private List<String> listRole;

    public Date getReqdate() {
        return reqdate;
    }

    public void setReqdate(Date reqdate) {
        this.reqdate = reqdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListRole() {
        return listRole;
    }

    public void setListRole(List<String> listRole) {
        this.listRole = listRole;
    }

}
