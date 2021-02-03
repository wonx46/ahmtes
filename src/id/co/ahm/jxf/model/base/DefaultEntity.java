/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.model.base;

import java.util.Date;

/**
 *
 * @author hendra.fs
 */
public interface DefaultEntity {

    public String getCreateBy();

    public void setCreateBy(String createBy);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public String getLastModBy();

    public void setLastModBy(String lastModBy);

    public Date getLastModDate();

    public void setLastModDate(Date lastModDate);

}
