/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app004.service.impl;

import id.co.ahm.sd.nis.app000.model.AhmsdnisMstagerngs;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.co.ahm.sd.nis.app004.dao.Nis004AhmsdnisMstagerngsDao;
import id.co.ahm.sd.nis.app004.dao.Nis004AhmsdnisTxnppltnsDao;
import id.co.ahm.sd.nis.app000.enumz.EnumKey;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstadmns;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltns;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltnsPK;
import id.co.ahm.sd.nis.app000.utils.MapXls;
import id.co.ahm.sd.nis.app000.vo.AhmsdnisMstadmnsVo;
import id.co.ahm.sd.nis.app000.vo.AhmsdnisTxnppltnsVo;
import id.co.ahm.sd.nis.app004.dao.Nis004AhmsdnisMstadmnsDao;
import id.co.ahm.sd.nis.app004.service.Nis004Service;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author admin
 */
@Service("nis004Service")
@Transactional(readOnly = true)
public class Nis004ServiceImpl implements Nis004Service{
    
    @Autowired
    private Nis004AhmsdnisMstagerngsDao ahmsdnis000MstagerngsDao;

    @Autowired
    private Nis004AhmsdnisTxnppltnsDao ahmsdnisTxnppltnsDao;
    
    @Autowired
    private Nis004AhmsdnisMstadmnsDao ahmsdnisMstadmnsDao;

    @Override
    @Transactional
    public void addMstagerngs(AhmsdnisMstagerngs agerngs) {
        agerngs.setVdscrptn(agerngs.getNstart()+" - "+agerngs.getNend());
        ahmsdnis000MstagerngsDao.addMstagerngs(agerngs);
    }

    @Override
    public List<AhmsdnisMstagerngs> getAllMstagerngs() {
        return ahmsdnis000MstagerngsDao.getAllAgerngs();
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteMstagerngs(String code) {
        ahmsdnis000MstagerngsDao.deleteAgerngs(code);
    }

    @Transactional(readOnly = false)
    @Override
    public AhmsdnisMstagerngs updateMstagerngs(AhmsdnisMstagerngs agerngs) {
        agerngs.setVdscrptn(agerngs.getNstart()+" - "+agerngs.getNend());
        return ahmsdnis000MstagerngsDao.updateAgerngs(agerngs);
    }

    @Override
    public AhmsdnisMstagerngs getMstagerngs(String code) {
        return ahmsdnis000MstagerngsDao.getAgerngs(code);
    }

    @Override
    @Transactional(readOnly = false)
    public void addTxnppltns(AhmsdnisTxnppltns txnppltns) {
        ahmsdnisTxnppltnsDao.addTxnppltns(txnppltns);
    }

    @Override
    public List<AhmsdnisTxnppltns> getAllTxnppltns() {
        return ahmsdnisTxnppltnsDao.getAllTxnppltns();
    }

    @Override
    public void deleteTxnppltns(String nyear) {
        ahmsdnisTxnppltnsDao.deleteTxnppltns(nyear);
    }

    @Transactional(readOnly = false)
    @Override
    public AhmsdnisTxnppltns updateTxnppltns(AhmsdnisTxnppltns txnppltns) {
       return ahmsdnisTxnppltnsDao.updateTxnppltns(txnppltns);
    }

    @Override
    public AhmsdnisTxnppltns getTxnppltns(AhmsdnisTxnppltnsPK atpk) {
        return ahmsdnisTxnppltnsDao.getTxnppltns(atpk);
    }

    @Override
    public List<AhmsdnisMstagerngs> searchMstagerngs(String search) {
        return ahmsdnis000MstagerngsDao.searchMstagerngs(search);
    }

    @Override
    public List<AhmsdnisTxnppltns> searchTxnppltns(String year, String province, String district) {
        return ahmsdnisTxnppltnsDao.searchTxnppltns(year, province, district);
    }

    @Transactional
    @Override
    public void saveToDbPopulation(List<Object> list, String key) {
        if (key.equalsIgnoreCase(EnumKey.POPULATION.getString())) {
            saveAllPopulation(list);
        }
    }
    
    public String getEmbedId(Object b){
        Field[] declaredFields = b.getClass().getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation ann : annotations) {
                    if (ann instanceof EmbeddedId) {
                        return String.valueOf(PropertyUtils.getProperty(b, field.getName()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getId(Object b) {
        Field[] declaredFields = b.getClass().getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation ann : annotations) {
                    if (ann instanceof Id) {
                        return String.valueOf(PropertyUtils.getProperty(b, field.getName()));
                    } else if (ann instanceof EmbeddedId) {
                        String[] id = getIdEmbed(field);
                        if (id != null) {
                            String idembed = "";
                            for (String fname : id) {
                                idembed = idembed + String.valueOf(PropertyUtils.getProperty(b, field.getName() + "." + fname)) + "#";
                            }
                            return idembed.substring(0, idembed.length() - 1);
                        } else {
                            return null;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private String[] getIdEmbed(Field field) {
        Annotation[] annotations = field.getAnnotations();
        for (Annotation ann : annotations) {
            if (ann instanceof MapXls) {
                MapXls map = (MapXls) ann;
                if (map.embedId().length() > 0 && map.embedId().contains("#")) {
                    String[] split = map.embedId().split("#");
                    String[] idnya = new String[split.length];
                    int i = 0;
                    for (String s : split) {
                        String name = s.split("-")[0];
                        idnya[i] = name;
                        i++;
                    }
                    return idnya;
                }
            }
        }
        return null;
    }
    
    @Transactional
    private void saveAllPopulation(List<Object> list) {
        System.out.println("Save all population");
        List<AhmsdnisTxnppltns> lobj = new ArrayList<>();
        for (Object object : list) {
            AhmsdnisTxnppltns b = (AhmsdnisTxnppltns) object;
            lobj.add(b);
        }
        ahmsdnisTxnppltnsDao.saveAll(lobj);
    }

    @Override
    public List<AhmsdnisTxnppltnsVo> getTxnppltnsForExcell() {
        List<AhmsdnisTxnppltns> ahmsdnisTxnppltnses = ahmsdnisTxnppltnsDao.getAllTxnppltns();
        List<AhmsdnisTxnppltnsVo> atvs = new ArrayList<>();
        for(AhmsdnisTxnppltns at : ahmsdnisTxnppltnses){
            AhmsdnisTxnppltnsVo atv =  new AhmsdnisTxnppltnsVo();
            atv.setNyear(at.getAhmsdnisTxnppltnsPK().getNyear());
            atv.setDbgneff(at.getDbgneff());
            atv.setDlasteff(at.getDlasteff());
            atv.setNfemale(at.getNfemale());
            atv.setNmale(at.getNmale());
            atv.setVagerng(at.getAhmsdnisTxnppltnsPK().getVagerng());
            atv.setVbpsdstrcd(at.getAhmsdnisTxnppltnsPK().getVbpsdstrcd());
            atv.setVbpsdstrnm(at.getVbpsdstrnm());
            atv.setVbpsprvcd(at.getAhmsdnisTxnppltnsPK().getVbpsprvcd());
            atv.setVbpsprvnm(at.getVbpsprvnm());
            atvs.add(atv);
        }
        return atvs;
    }

    @Override
    public AhmsdnisMstagerngs getLastRowAgeRange() {
        return ahmsdnis000MstagerngsDao.getLastRowAgeRange();
    }

    @Override
    public List<AhmsdnisMstadmnsVo> getAllMstadmnsForExcell() {
        List<AhmsdnisMstadmnsVo> amvs = new ArrayList<>();
        for(AhmsdnisMstadmns am: ahmsdnisMstadmnsDao.getAllMstadmns()){
            AhmsdnisMstadmnsVo amv = new AhmsdnisMstadmnsVo();
            amv.setVbpsdstrcd(am.getAhmsdnisMstadmnsPK().getVbpsdstrcd());
            amv.setVbpsprvcd(am.getAhmsdnisMstadmnsPK().getVbpsprvcd());
            amv.setVbpsdstrnm(am.getVbpsdstrnm());
            amv.setVbpsprvnm(am.getVbpsprvnm());
            amvs.add(amv);
        }
        return amvs;
    }

}
