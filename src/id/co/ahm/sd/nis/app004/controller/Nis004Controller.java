/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.sd.nis.app004.controller;

import id.co.ahm.sd.nis.app000.model.AhmsdnisMstadmns;
import id.co.ahm.sd.nis.app000.model.AhmsdnisMstagerngs;
import id.co.ahm.sd.nis.app000.model.AhmsdnisTxnppltnsPK;
import id.co.ahm.sd.nis.app000.utils.ClassUtils;
import id.co.ahm.sd.nis.app000.utils.MapXls;
import id.co.ahm.sd.nis.app000.utils.XlsUtils;
import id.co.ahm.sd.nis.app000.vo.AhmsdnisMstadmnsVo;
import id.co.ahm.sd.nis.app000.vo.AhmsdnisTxnppltnsVo;
import id.co.ahm.sd.nis.app004.service.Nis004Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.DateFormatConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author admin
 */
@Controller
public class Nis004Controller {

    private final String UPLOAD_DIR = "D:/upload/";
    private static String TEMPLATE_DIR = "D:/upload/template/";
    private static String DOWNLOAD_DIR = "/download";

    @Autowired
    private Nis004Service nis004Service;

    @RequestMapping(value = "/view-population")
    public ModelAndView viewPopulation() {
        ModelAndView andView = new ModelAndView("idcoahmdnispopulation/viewPopulation");
        return andView;
    }

    @PostMapping("/uploadxlstmppopulation")
    @ResponseBody
    public String uploadFileTmp(@RequestParam("file") MultipartFile file, @RequestParam("clazz") String clazz,
            @RequestParam("url") String url,
            RedirectAttributes attributes, HttpSession session) {
        // check if file is empty
        if (file.isEmpty()) {
            System.err.println("Please select a file to upload.");
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println("fileName: " + fileName);
        // save the file on the local file system
        try {
            SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
            Path path = Paths.get(UPLOAD_DIR + fileName);
            System.out.println("path: " + path);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            FileInputStream filexls = new FileInputStream(path.toFile());
            Workbook workbook = new XSSFWorkbook(filexls);
            String excelFormatPattern = DateFormatConverter.convert(Locale.US, "yyyy-MM-dd");
            Sheet sheet = workbook.getSheetAt(0);
            fillSheetAgeRange(workbook,session);

            CellStyle cellStyle = workbook.createCellStyle();

            DataFormat poiFormat = workbook.createDataFormat();
            cellStyle.setDataFormat(poiFormat.getFormat(excelFormatPattern));

            Map<Integer, List<Object>> data = new HashMap<>();
            int i = 0;
            for (Row row : sheet) {
                data.put(i, new ArrayList<Object>());
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cell.setCellStyle(cellStyle);
                                String datex = cell.getDateCellValue() + "";
                                Date dt = cell.getDateCellValue();
                                data.get(i).add(dt);
                            } else {
                                data.get(i).add(cell.getNumericCellValue());
                            }
                            break;
                    }
                }
                i++;
            }
            url = url.replaceAll("-", "/");
            session.setAttribute("urlpreview", url);
            fillProductsTmp(data, Class.forName(clazz), session);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("You successfully uploaded " + fileName);
        return url;
    }

    /*private void fillProductsTmp(Map<Integer, List<Object>> data, Class clazz, HttpSession session) {
        System.out.println("Enter fillProductsTmp");
        List<Object> list = new ArrayList<>();
        Collection<List<Object>> values = data.values();
        int i = 0;
        System.out.println("SIZE: " + values.size());
        for (List<Object> xls : values) {
            if (i > 0) {
                if (!xls.isEmpty()) {
                    Object b = getObjXlsTmp(xls, clazz);
                    System.out.println("Object : " + b);
                    String id = nis004Service.getId(b);
                    System.out.println("ID: " + id);
                    System.out.println("Test: " + clazz.getSimpleName() + id);
                    if (id != null) {
                        session.setAttribute("objxls" + clazz.getSimpleName() + id, b);
                    }
                    list.add(b);
                }
            }
            i++;
        }
        session.setAttribute("listtmpxls", list);
    }*/
    private void fillProductsTmp(Map<Integer, List<Object>> data, Class clazz, HttpSession session) {

        System.out.println("masuk fillProductsTmp");
        List<Object> list = new ArrayList<>();
        Collection<List<Object>> values = data.values();
        int i = 0;
        for (List<Object> xls : values) {
            if (i > 0) {
                if (!xls.isEmpty()) {
                    Object b = getObjXlsTmp(xls, clazz);
                    String id = nis004Service.getId(b);
                    if (id != null) {
                        session.setAttribute("objxls" + clazz.getSimpleName() + id, b);
                    }
                    list.add(b);
                }

            }
            i++;
        }
        session.setAttribute("listtmpxls", list);
    }

    private void fillSheetAgeRange(Workbook workbook, HttpSession session) {
        //======================================================
            List<String> ages = new ArrayList<>();
            Sheet sheetAge = workbook.getSheetAt(2);
            for (Row row : sheetAge) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            ages.add(cell.getStringCellValue());
                            break;
                    }
                }
            }
            session.setAttribute("listAge", ages);
            //======================================================
    }

    /*private Object getObjXlsTmp(List<Object> xls, Class clz) {
        try {
            Constructor construct = clz.getConstructor();
            Object obTmp = construct.newInstance();
            Field[] fields = obTmp.getClass().getDeclaredFields();
            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation ann : annotations) {
                    if (ann instanceof MapXls) {
                        MapXls mapxls = (MapXls) ann;
                        Object object1 = ClassUtils.convertDataType(xls.get(mapxls.indexXls()), field.getType(), false, "yyyy-MM-dd");
                        PropertyUtils.setProperty(obTmp, field.getName(), object1);
                    }
                }
            }
            return obTmp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/
    private Object getObjXlsTmp(List<Object> xls, Class clz) {
        try {
            Constructor construct = clz.getConstructor();
            Object obTmp = construct.newInstance();
            Field[] fields = obTmp.getClass().getDeclaredFields();
            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation ann : annotations) {
                    if (ann instanceof MapXls) {
                        MapXls mapxls = (MapXls) ann;
                        if (mapxls.indexXls() == 999) { // artinya ada embedid
                            if (mapxls.embedId().length() > 0 && mapxls.embedId().contains("#")) {
                                String[] split = mapxls.embedId().split("#");
                                for (String pk : split) {
                                    String[] fieldsplit = pk.split("-");
                                    String fieldIdName = fieldsplit[0];
                                    Integer indexXlsId = Integer.parseInt(fieldsplit[1]);
                                    String fieldIdnya = field.getName() + "." + fieldIdName;
                                    Class propertyType = PropertyUtils.getPropertyType(obTmp, fieldIdnya);
                                    Object object1 = ClassUtils.convertDataType(xls.get(indexXlsId), propertyType, false, "yyyy-MM-dd");
                                    PropertyUtils.setProperty(obTmp, fieldIdnya, object1);
                                }
                            }
                        } else {
                            Object object1 = ClassUtils.convertDataType(xls.get(mapxls.indexXls()), field.getType(), false, "yyyy-MM-dd");
                            PropertyUtils.setProperty(obTmp, field.getName(), object1);
                        }

                    }
                }

            }

            return obTmp;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/savexlstodbpopulation")
    public ModelAndView doSaveTmpXlsToDb(@RequestParam("key") String key,
            @RequestParam("urlview") String urlview,
            ModelAndView model, HttpSession session) {
        try {
            List<Object> list = (List<Object>) session.getAttribute("listtmpxls");
            if (list != null && !list.isEmpty()) {
                nis004Service.saveToDbPopulation(list, key);
            }
            System.out.println("Ini");
            model.setViewName(urlview);
        } catch (Exception e) {
            e.printStackTrace();
            model.setViewName("redirect:/error-page");
            return model;
        }
//        System.out.println("Lewat semua");
//        model.setViewName("previewtmpxlspopulation");
        return model;
    }

    @RequestMapping(value = "/previewtmpxlspopulation", method = RequestMethod.GET)
    public ModelAndView doPreviewXls(Model model, HttpSession session) {
        try {
            List<Object> list = (List<Object>) session.getAttribute("listtmpxls");
            String url = (String) session.getAttribute("urlpreview");
            
            List<Object> listAge = (List<Object>) session.getAttribute("listAge");
            ModelAndView modelview = new ModelAndView(url);
            modelview.addObject("objs", list);
            modelview.addObject("ages", listAge);
            return modelview;
        } catch (Exception e) {
            e.printStackTrace();
//           return new ModelAndView("/error-page");
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/writexls-population")
    public void writeXls(ModelAndView model, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String fileName = request.getParameter("filetemplate");
        Path path = Paths.get(TEMPLATE_DIR + fileName);

        FileInputStream filexls = new FileInputStream(path.toFile());

        List<AhmsdnisTxnppltnsVo> listPopulasi = nis004Service.getTxnppltnsForExcell();
        List<AhmsdnisMstagerngs> listAge = nis004Service.getAllMstagerngs();
        List<AhmsdnisMstadmnsVo> mstadmnses = nis004Service.getAllMstadmnsForExcell();
        try {
            String outputFileName = "Template_Upload_POPULATION_DATA.xlsx";
            String dataDirectory = request.getServletContext().getRealPath(DOWNLOAD_DIR);
            new File(dataDirectory).mkdir();
            System.out.println("download: " + dataDirectory);
            List<Object>[] lists = new List[3];
            lists[2] = getListObjPopulation(listPopulasi);
            lists[0] = getListObjMstAge(listAge);
            lists[1] = getListObjMstAdm(mstadmnses);
            XlsUtils.writeMultiple(lists, new XSSFWorkbook(filexls), new String[]{"a", "i", "w"}, new Integer[]{2, 1, 3}, dataDirectory, outputFileName);
            downloadFile(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/downloadxls-population")
    private void downloadFile(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String outputFileName = request.getParameter("outputfilename");

        String dataDirectory = request.getServletContext().getRealPath(DOWNLOAD_DIR);
        Path pathFile = Paths.get(dataDirectory, outputFileName);

        try {

            response.setContentType("application/*");
            response.addHeader("Content-Disposition", "attachment; filename=" + outputFileName);

            FileInputStream is = new FileInputStream(pathFile.toFile());
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
//            log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }

//        return list(model);
    }

    private List<Object> getListObjPopulation(List<AhmsdnisTxnppltnsVo> list) {
        List<Object> res = new ArrayList<>();
        res.addAll(list);
        return res;
    }

    private List<Object> getListObjMstAge(List<AhmsdnisMstagerngs> list) {
        List<Object> res = new ArrayList<>();
        res.addAll(list);
        return res;
    }

    private List<Object> getListObjMstAdm(List<AhmsdnisMstadmnsVo> list) {
        List<Object> res = new ArrayList<>();
        res.addAll(list);
        return res;
    }
    
    @RequestMapping("/error-page")
    public ModelAndView errorPage(){
        return new ModelAndView("idcoahmdnispopulation/failed_save_excell");
    }
}
