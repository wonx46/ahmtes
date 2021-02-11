/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app004.dao;

import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltns;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltnsPK;
import java.util.List;

/**
 *
 * @author admin
 */
public interface Nis004AhmsdnisTxnppltnsDao {
    public void addTxnppltns(AhmsdnisTxnppltns txnppltns);

    public List<AhmsdnisTxnppltns> getAllTxnppltns();

    public void deleteTxnppltns(String txnppltns);

    public AhmsdnisTxnppltns updateTxnppltns(AhmsdnisTxnppltns txnppltns);

    public AhmsdnisTxnppltns getTxnppltns(AhmsdnisTxnppltnsPK txnppltns);
    
    public List<AhmsdnisTxnppltns> searchTxnppltns(String year,String province, String district);
    
    public void saveAll(List<AhmsdnisTxnppltns> ats);
}
