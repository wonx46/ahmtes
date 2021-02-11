/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app004.dao;

import id.co.ahm.sd.nis.app000.model.AhmsdnisMstagerngs;
import java.util.List;

/**
 *
 * @author admin
 */
public interface Nis004AhmsdnisMstagerngsDao {

    public void addMstagerngs(AhmsdnisMstagerngs agerngs);

    public List<AhmsdnisMstagerngs> getAllAgerngs();

    public void deleteAgerngs(String agerngsCode);

    public AhmsdnisMstagerngs updateAgerngs(AhmsdnisMstagerngs mstagerngs);

    public AhmsdnisMstagerngs getAgerngs(String agerngsCode);
    
    public List<AhmsdnisMstagerngs> searchMstagerngs(String search);
    
    public AhmsdnisMstagerngs getLastRowAgeRange();
}
