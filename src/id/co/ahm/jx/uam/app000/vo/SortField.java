/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.vo;

/**
 *
 * @author achmad.ha
 */
public class SortField {
    
    private String field;
    private SortField.ORDER order;

    public SortField(String field, ORDER order) {
        super();
        this.field = field;
        this.order = order;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public SortField.ORDER getOrder() {
        return order;
    }

    public void setOrder(SortField.ORDER order) {
        this.order = order;
    }

    public static enum ORDER {

        ASC,
        DESC,
        NONE
    }
    
}
