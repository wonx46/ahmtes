/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.model.base;

import java.util.Date;

/**
 *
 * @author AFI
 */
public interface BaseAudit {

    String getCreateBy();

    void setCreateBy(String createBy);

    Date getCreateDate();

    void setCreateDate(Date createDate);

    String getLastModBy();

    void setLastModBy(String lastModBy);

    Date getLastModDate();

    void setLastModDate(Date lastModDate);

    void setService(String idservice);

    String getService();

}
