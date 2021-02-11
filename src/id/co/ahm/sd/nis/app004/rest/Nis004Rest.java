/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app004.rest;

import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstadmns;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstagerngs;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltns;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltnsPK;
import id.co.ahm.sd.nis.app000.utils.ObjectUtiliti;
import id.co.ahm.sd.nis.app000.vo.AhmsdnisMstadmnsVo;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import id.co.ahm.sd.nis.app004.service.Nis004Service;
import java.util.Collections;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("jx/ahmsdnis000")
public class Nis004Rest {

    private static final Logger logger = Logger
            .getLogger(Nis004Rest.class);

    public Nis004Rest() {
        System.out.println("Nis004Rest()");
    }

    @Autowired
    @Qualifier(value = "nis004Service")
    public Nis004Service nis004Service;

    @RequestMapping(value = "/retrieve-mstagerngs", method = RequestMethod.GET)
    public DtoResponse getMstagerngs() {
        List<AhmsdnisMstagerngs> list = nis004Service.getAllMstagerngs();
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, list);
    }

    @RequestMapping(value = "/save-mstagerngs", method = RequestMethod.POST)
    public DtoResponse saveMstagerngs(@RequestBody AhmsdnisMstagerngs obj) {
        System.out.println(obj.getVcdagerng() + " - " + obj.getVcrea());
        try {
            if (nis004Service.getMstagerngs(obj.getVcdagerng()) != null) {
                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, Collections.singletonMap("code", "02"), null);
            }
            if (nis004Service.getLastRowAgeRange() != null) {
                if (nis004Service.getLastRowAgeRange().getNend() >= obj.getNstart()) {
                    return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, Collections.singletonMap("code", "01"), null);
                }
            }
            nis004Service.addMstagerngs(obj);
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @RequestMapping(value = "/findbycode-mstagerngs", method = RequestMethod.GET)
    public DtoResponse getAgeRangebycode(@RequestParam(value = "code") String code) {
        List<AhmsdnisMstagerngs> list = new ArrayList<AhmsdnisMstagerngs>();
        AhmsdnisMstagerngs ahmsdnisMstagerngs = nis004Service.getMstagerngs(code);
        list.add(ahmsdnisMstagerngs);
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, list);
    }

    @RequestMapping(value = "/delete-mstagerngs/{id}", method = RequestMethod.GET)
    public DtoResponse deleteMstagerngs(@PathVariable String id) {
        try {
            nis004Service.deleteMstagerngs(id);
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @RequestMapping(value = "/update-mstagerngs", method = RequestMethod.POST)
    public DtoResponse updateMstagerngs(@RequestBody AhmsdnisMstagerngs obj) {
        System.out.println(obj.getVcdagerng() + " - " + obj.getVcdagerng());
        try {
            AhmsdnisMstagerngs exist = nis004Service.getMstagerngs(obj.getVcdagerng());
            if (exist != null) {
                exist = (AhmsdnisMstagerngs) ObjectUtiliti.copyObject(obj, exist);
                nis004Service.updateMstagerngs(exist);
                return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
            } else {
                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
        }
    }

    //txnppltns start
    @RequestMapping(value = "/retrieve-txnppltns", method = RequestMethod.GET)
    public DtoResponse getTxnppltns() {
        List<AhmsdnisTxnppltns> list = nis004Service.getAllTxnppltns();
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, list);
    }

    @RequestMapping(value = "/save-txnppltns", method = RequestMethod.POST)
    public DtoResponse saveTxnppltns(@RequestBody AhmsdnisTxnppltns obj) {
        try {
            nis004Service.addTxnppltns(obj);
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @RequestMapping(value = "/findbycode-txnppltns", method = RequestMethod.GET)
    public DtoResponse getTxnppltnsbycode(
            @RequestParam(value = "nyear") String nyear,
            @RequestParam(value = "vbpsprvcd") String vbpsprvcd,
            @RequestParam(value = "vbpsdstrcd") String vbpsdstrcd,
            @RequestParam(value = "vagerng") String vagerng) {
        List<AhmsdnisTxnppltns> list = new ArrayList<AhmsdnisTxnppltns>();

        AhmsdnisTxnppltnsPK atpk = new AhmsdnisTxnppltnsPK();
        atpk.setNyear(nyear);
        atpk.setVagerng(vagerng);
        atpk.setVbpsdstrcd(vbpsdstrcd);
        atpk.setVbpsprvcd(vbpsprvcd);

        AhmsdnisTxnppltns ahmsdnisTxnppltns = nis004Service.getTxnppltns(atpk);
        list.add(ahmsdnisTxnppltns);
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, list);
    }

    @RequestMapping(value = "/delete-txnppltns/{nyear}", method = RequestMethod.GET)
    public DtoResponse deleteTxnppltns(@PathVariable String nyear) {
        try {
            nis004Service.deleteTxnppltns(nyear);
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @RequestMapping(value = "/update-txnppltns", method = RequestMethod.POST)
    public DtoResponse updateTxnppltns(@RequestBody AhmsdnisTxnppltns obj) {
        System.out.println("sasa:"+obj);
        try {
            AhmsdnisTxnppltnsPK atpk = getPK(obj);
            AhmsdnisTxnppltns exist = nis004Service.getTxnppltns(atpk);
            if (exist != null) {
                exist = (AhmsdnisTxnppltns) ObjectUtiliti.copyObject(obj, exist);
                nis004Service.updateTxnppltns(exist);
                return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
            } else {
                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
        }
    }

    @RequestMapping(value = "/search-mstagerngs", method = RequestMethod.GET)
    public DtoResponse searchMstagerngs(@RequestParam("search") String search) {
        List<AhmsdnisMstagerngs> list = nis004Service.searchMstagerngs(search);
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, list);
    }

    @RequestMapping(value = "/search-txnppltns", method = RequestMethod.GET)
    public DtoResponse searchTxnppltns(
            @RequestParam("year") String year,
            @RequestParam("province") String province,
            @RequestParam("district") String district) {
        List<AhmsdnisTxnppltns> list = nis004Service.searchTxnppltns(year, province, district);
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, list);
    }

    @RequestMapping(value = "/retrieve-mstadmns", method = RequestMethod.GET)
    public DtoResponse getAhmsdnisMstadmns() {
        List<AhmsdnisMstadmnsVo> list = nis004Service.getAllMstadmnsForExcell();
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, list);
    }

    public AhmsdnisTxnppltnsPK getPK(AhmsdnisTxnppltns obj) {
        AhmsdnisTxnppltnsPK atpk = new AhmsdnisTxnppltnsPK();
        System.out.println("ATPK: "+atpk);
        atpk.setNyear(obj.getAhmsdnisTxnppltnsPK().getNyear());
        atpk.setVagerng(obj.getAhmsdnisTxnppltnsPK().getVagerng());
        atpk.setVbpsdstrcd(obj.getAhmsdnisTxnppltnsPK().getVbpsdstrcd());
        atpk.setVbpsprvcd(obj.getAhmsdnisTxnppltnsPK().getVbpsprvcd());
        return atpk;
    }
}
