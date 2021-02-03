/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.uam.app000.dao;

import id.co.ahm.jx.uam.app000.model.AhmmoerpMstkaryawans;
import id.co.ahm.jx.uam.app000.vo.VoOrganization;
import id.co.ahm.jxf.dao.DefaultDao;

/**
 *
 * @author achmad.ha
 */
public interface AhmmoerpMstkaryawansDao extends DefaultDao<AhmmoerpMstkaryawans, Integer>{
    VoOrganization getUserOrganization(int nrp);
}
