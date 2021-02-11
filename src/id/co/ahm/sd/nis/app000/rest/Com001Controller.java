package id.co.ahm.sd.nis.app000.rest;

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

import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd;
import id.co.ahm.sd.nis.app000.service.Com001Service;
import id.co.ahm.sd.nis.app000.utils.ObjectUtiliti;

@RestController
@RequestMapping("jx/com001")
public class Com001Controller {

    private static final Logger logger = Logger
            .getLogger(Com001Controller.class);

    public Com001Controller() {
        System.out.println("Com001Controller()");
    }

    @Autowired
    @Qualifier(value = "com001Service")
    private Com001Service com001Service;

    @RequestMapping(value = "/getSampleData", method = RequestMethod.GET)
    public List<AhmsdnisMstbrnd> getSampleData() {
        List<AhmsdnisMstbrnd> list = new ArrayList<>();
        AhmsdnisMstbrnd o = new AhmsdnisMstbrnd();
        o.setVbrndcd("A9373");
        o.setVbrndnm("Joini");
        o.setVbrndtyp("Playbos");
        list.add(o);
        return list;
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
    public DtoResponse getProduk() {
        List<AhmsdnisMstbrnd> list = com001Service.getAllBrand();
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, list);
    }

    @RequestMapping(value = "/findbycode", method = RequestMethod.GET)
    public DtoResponse getBrandbycode(@RequestParam(value = "code") String code) {
        List<AhmsdnisMstbrnd> list = new ArrayList<AhmsdnisMstbrnd>();
        AhmsdnisMstbrnd brand = com001Service.getBrand(code);
        list.add(brand);
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, list);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public DtoResponse delete(@PathVariable String id) {

        try {
            com001Service.deleteBrand(id);
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
        }

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public DtoResponse save(@RequestBody AhmsdnisMstbrnd obj) {
        System.out.println(obj.getVbrndcd() + " - " + obj.getVbrndnm());
        try {
            com001Service.addMstBrand(obj);
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
        } catch (Exception e) {
            e.printStackTrace();
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
        }

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DtoResponse update(@RequestBody AhmsdnisMstbrnd obj) {
        System.out.println(obj.getVbrndcd() + " - " + obj.getVbrndnm());
        try {
            AhmsdnisMstbrnd exist = com001Service.getBrand(obj.getVbrndcd());
            if (exist != null) {
                exist = (AhmsdnisMstbrnd) ObjectUtiliti.copyObject(obj, exist);
                com001Service.addMstBrand(exist);
                return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
            } else {
                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
        }

    }

}
