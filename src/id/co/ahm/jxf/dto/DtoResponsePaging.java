/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.dto;

/**
 *
 * @author achmad.ha
 */
public class DtoResponsePaging extends DtoResponse{
    
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
