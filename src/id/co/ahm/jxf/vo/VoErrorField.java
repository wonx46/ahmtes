/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.vo;

import java.util.Map;

/**
 *
 * @author okky.ms
 */
public class VoErrorField {
    private String f;
    private String m;
    private Map<String, Object> msg;

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public Map<String, Object> getMsg() {
        return msg;
    }

    public void setMsg(Map<String, Object> msg) {
        this.msg = msg;
    }

}
