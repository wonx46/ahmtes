/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app004.service;

import id.co.ahm.sd.nis.app000.model.AhmsdnisMstadmns;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstagerngs;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltns;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltnsPK;
import id.co.ahm.sd.nis.app000.vo.AhmsdnisMstadmnsVo;
import id.co.ahm.sd.nis.app000.vo.AhmsdnisTxnppltnsVo;
import java.util.List;

/**
 *
 * @author admin
 */
public interface Nis004Service {

    public void addMstagerngs(AhmsdnisMstagerngs agerngs);

    public List<AhmsdnisMstagerngs> getAllMstagerngs();

    public void deleteMstagerngs(String code);

    public AhmsdnisMstagerngs updateMstagerngs(AhmsdnisMstagerngs agerngs);

    public AhmsdnisMstagerngs getMstagerngs(String code);
    
    public List<AhmsdnisMstagerngs> searchMstagerngs(String search);
    
    public AhmsdnisMstagerngs getLastRowAgeRange();
    
    public void addTxnppltns(AhmsdnisTxnppltns txnppltns);

    public List<AhmsdnisTxnppltns> getAllTxnppltns();
    
    public List<AhmsdnisTxnppltnsVo> getTxnppltnsForExcell();

    public void deleteTxnppltns(String nyear);

    public AhmsdnisTxnppltns updateTxnppltns(AhmsdnisTxnppltns txnppltns);

    public AhmsdnisTxnppltns getTxnppltns(AhmsdnisTxnppltnsPK agerngsCode);
    
    public List<AhmsdnisTxnppltns> searchTxnppltns(String year,String province, String district);
    
    public void saveToDbPopulation(List<Object> list, String key);
    
    public String getId(Object b);
    
    public String getEmbedId(Object b);
    
    List<AhmsdnisMstadmnsVo> getAllMstadmnsForExcell();

}
