package id.co.ahm.sd.nis.app000.controller;

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
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd;
import id.co.ahm.sd.nis.app000.service.Com001Service;
import id.co.ahm.sd.nis.app000.utils.ClassUtils;
//import id.co.ahm.sd.nis.app000.utils.GlobalVariable;
import id.co.ahm.sd.nis.app000.utils.MapXls;
import id.co.ahm.sd.nis.app000.utils.XlsUtils;



@Controller
//@RequestMapping("/brand")
public class SampleController {

	private final String UPLOAD_DIR = "/upload/";
	private static String TEMPLATE_DIR = "/Users/iwan/Documents/BOZREZA/tmp/template/";
	private static String DOWNLOAD_DIR = "/download/";
	
	@Autowired
	Com001Service com001Service;
	
	@PostMapping("/uploadpic")
	@ResponseBody
    public ModelAndView uploadPic(ModelAndView model, HttpServletRequest request, @RequestParam("file") MultipartFile file,
    		RedirectAttributes attributes,HttpSession session) throws Exception {

        if (file.isEmpty()) {
        	 session.setAttribute("msgpic", "Please select a file to upload.");
            return list(model, session);
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
        	 String dataDirectory = request.getServletContext().getRealPath(UPLOAD_DIR);
            Path path = Paths.get(dataDirectory, fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            
            String fileNameNoExt = fileName.substring(0,fileName
	                .lastIndexOf(".") );
            String formatName = fileName.substring(fileName
	                .lastIndexOf(".") + 1);
            
            resize(dataDirectory+"/"+fileName, dataDirectory+"/"+fileNameNoExt+"_300x300."+formatName, 300, 300);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return success response
        session.setAttribute("msgpic", "You successfully uploaded " + fileName + "! size 300x300");
        return list(model, session);
    }
	
	 public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight)
	            throws IOException {
	        // reads input image
	        File inputFile = new File(inputImagePath);
	        BufferedImage inputImage = ImageIO.read(inputFile);
	 
	        // creates output image
	        BufferedImage outputImage = new BufferedImage(scaledWidth,
	                scaledHeight, inputImage.getType());
	 
	        // scales the input image to the output image
	        Graphics2D g2d = outputImage.createGraphics();
	        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
	        g2d.dispose();
	 
	        // extracts extension of output file
	        String formatName = outputImagePath.substring(outputImagePath
	                .lastIndexOf(".") + 1);
	 
	        // writes to output file
	        ImageIO.write(outputImage, formatName, new File(outputImagePath));
	    }

	@RequestMapping(value = "/writexls")
	public void writeXls(ModelAndView model, HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		String fileName = request.getParameter("filetemplate");
		 Path path = Paths.get(TEMPLATE_DIR + fileName);
         
         FileInputStream filexls = new FileInputStream(path.toFile());
		
		List<AhmsdnisMstbrnd> list = com001Service.getAllBrand();
		try {
			String outputFileName = "Testing.xlsx"; 
			 String dataDirectory = request.getServletContext().getRealPath(DOWNLOAD_DIR);
			 new File(dataDirectory).mkdir();
			 System.out.println("download: "+dataDirectory);
			XlsUtils.write(getListObj(list), new XSSFWorkbook(filexls), "Tes Sheet", 2,dataDirectory,outputFileName);
			downloadFile(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/downloadxls")
	private void downloadFile(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		
		String outputFileName = request.getParameter("outputfilename");
	
	 String dataDirectory = request.getServletContext().getRealPath(DOWNLOAD_DIR);
	 Path pathFile = Paths.get(dataDirectory, outputFileName);

        try {
      
          response.setContentType("application/*");
          response.addHeader("Content-Disposition", "attachment; filename="+outputFileName);
        	
        	  FileInputStream is = new FileInputStream(pathFile.toFile());
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
          } catch (IOException ex) {
        	  ex.printStackTrace();
            throw new RuntimeException("IOError writing file to output stream");
          }
        
	}

	private List<Object> getListObj(List<AhmsdnisMstbrnd> list) {
		List<Object> res = new ArrayList<>();
		res.addAll(list);
		
		return res;
	}

	@RequestMapping(value = "/getDataSample", method = RequestMethod.GET)
	public String getAllEmployees(Model model) {
		model.addAttribute("data", com001Service.getSampleData());
		return "dataSampleDisplay";
	}
	
	@RequestMapping(value = "/")
	public ModelAndView list(ModelAndView model,HttpSession session) throws IOException {
		List<AhmsdnisMstbrnd> list = com001Service.getAllBrand();
		model.addObject("list", list);
		model.addObject("msgpic", session.getAttribute("msgpic") == null ? "":session.getAttribute("msgpic") );
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newBrand", method = RequestMethod.GET)
	public ModelAndView createNew(ModelAndView model) {
		AhmsdnisMstbrnd o = new AhmsdnisMstbrnd();
		model.addObject("obj", o);
		model.setViewName("idcoahmsdnismstbrand/IdcoahmsdnismstbrandFormNew");
		return model;
	}

	@RequestMapping(value = "/updateBrand", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute AhmsdnisMstbrnd obj) {
		
			obj.setVmodi("adminupdate");
			obj.setDmodi(getNow());
			com001Service.updateBrand(obj);
		
		return new ModelAndView("redirect:/");
	}

	
	
	@RequestMapping(value = "/saveBrand", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute AhmsdnisMstbrnd obj) {
			obj.setVcrea("admincreate");
			obj.setDcrea(getNow());
			com001Service.addMstBrand(obj);
		return new ModelAndView("home");
	}

	private Date getNow() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	@RequestMapping(value = "/deleteBrand", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		com001Service.deleteBrand(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editBrand", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request) {
		String id = request.getParameter("id");
		
		ModelAndView model = new ModelAndView("idcoahmsdnismstbrand/IdcoahmsdnismstbrandFormUpdate");
		model.addObject("idnya", id);

		return model;
	}
	
	 @RequestMapping(value = "/previewtmpxls", method = RequestMethod.GET)
	    public ModelAndView doPreviewXls(Model model, HttpSession session) {
	    	try {

	    		List<Object> list = (List<Object>)  session.getAttribute("listtmpxls");
	    		String url = (String) session.getAttribute("urlpreview");
	    	    
	    	    ModelAndView modelview = new ModelAndView(url);
	    	    modelview.addObject("objs", list);

	    		return modelview;
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return new ModelAndView("redirect:/");
	    	    
	    	
	    	
	    }
	
	@PostMapping("/uploadxlstmp")
	@ResponseBody
    public String uploadFileTmp(@RequestParam("file") MultipartFile file,@RequestParam("clazz")String clazz,
    		@RequestParam("url")String url,
    		RedirectAttributes attributes, HttpSession session) {

		System.out.println("masuk uploadFileTmp "+clazz+" "+url);
		
        // check if file is empty
        if (file.isEmpty()) {
        	System.err.println("Please select a file to upload.");
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println("fileName: "+fileName);
        // save the file on the local file system
        try {
        	SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
            Path path = Paths.get(UPLOAD_DIR + fileName);
            System.out.println("path: "+path);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            
            FileInputStream filexls = new FileInputStream(path.toFile());
            Workbook workbook = new XSSFWorkbook(filexls);

            String excelFormatPattern = DateFormatConverter.convert(Locale.US, "yyyy-MM-dd");
            Sheet sheet = workbook.getSheetAt(0);
            CellStyle cellStyle = workbook.createCellStyle();

            DataFormat poiFormat = workbook.createDataFormat();
            cellStyle.setDataFormat(poiFormat.getFormat(excelFormatPattern));

            Map<Integer, List<Object>> data = new HashMap<>();
            int i = 0;
            for (Row row : sheet) {
                data.put(i, new ArrayList<Object>());
                for (Cell cell : row) {
                    switch (cell.getCellTypeEnum()) {
                        case STRING: 
                        	data.get(new Integer(i)).add(cell.getRichStringCellValue().getString()); 
                        	break;
                        case NUMERIC: 
                        	if (DateUtil.isCellDateFormatted(cell)) {
                        		cell.setCellStyle(cellStyle);
                        		String datex = cell.getDateCellValue() + "";
                        		Date dt = cell.getDateCellValue();
                        		data.get(i).add(dt);
                        		
                        		
                        	  
                        	} else {
                        	    data.get(i).add(cell.getNumericCellValue() + "");
                        	}
                        	break;
//                        case BOOLEAN: ... break;
//                        case FORMULA: ... break;
//                        default: data.get(new Integer(i)).add(" ");
                    }
                }
                i++;
            }
            
//           String code =  (String) session.getAttribute("idloginsession");
           url  = url.replaceAll("-", "/");
//           GlobalVariable.getInstance().put("urlpreview"+code, url);
           session.setAttribute("urlpreview", url);
            
            fillProductsTmp(data,Class.forName(clazz), session );
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("You successfully uploaded " + fileName );

        // return success response
//        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return url;
//        ModelAndView model = new ModelAndView(url);

//		return model;
    }
	
	 private void fillProductsTmp(Map<Integer, List<Object>> data, Class clazz, HttpSession session) {
	    System.out.println("masuk fillProductsTmp");	
		 List<Object> list = new ArrayList<>();
	    	
	    	
	    	Collection<List<Object>> values = data.values();
	    	int i=0;
	    	for (List<Object> xls : values) {
				if(i>0){
					if(!xls.isEmpty()){
						Object b = getObjXlsTmp(xls, clazz);
						String id = com001Service.getId(b);
						if(id != null){
//							GlobalVariable.getInstance().put("objxls"+clazz.getSimpleName()+id+idloginsession, b);	
							  session.setAttribute("objxls"+clazz.getSimpleName()+id, b);
						}
						
						list.add(b);
					}
					
				}
	    		i++;
			}
//	    	System.out.println("idloginsession: "+idloginsession);
//	    	for (Object dt : list) {
//				AhmsdnisMstbrnd x = (AhmsdnisMstbrnd)dt;
//				System.out.println(x.getVbrndcd()+" "+x.getVbrndnm());
//			}
	    	
//	    	GlobalVariable.getInstance().put("listtmpxls"+idloginsession, list);
	    	 session.setAttribute("listtmpxls", list);
			
		}
	 
	 private Object getObjXlsTmp(List<Object> xls, Class clz) {
		  
			try {
				
				Constructor construct         = clz.getConstructor();
				Object obTmp				  = construct.newInstance();
				  
				  Field[] fields        = obTmp.getClass().getDeclaredFields();
				  for (Field field : fields) {
					  Annotation[] annotations = field.getAnnotations();
					  for (Annotation ann : annotations) {
						if( ann instanceof MapXls){
							MapXls mapxls = (MapXls) ann;
							Object object1	= ClassUtils.convertDataType(xls.get(mapxls.indexXls()), field.getType(),false, "yyyy-MM-dd");
						    PropertyUtils.setProperty(obTmp, field.getName(), object1);
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
	 
	 
	  @GetMapping("/savexlstodb")
	    public ModelAndView doSaveTmpXlsToDb(@RequestParam("key")String key,
	    		@RequestParam("urlview")String urlview,
	    		ModelAndView model, HttpSession session) {
	    	try {
	    		System.out.println("masuk doSaveTmpXlsToDb");
		    	List<Object> list = (List<Object>) session.getAttribute("listtmpxls");
		    	
	    		
	        	if(list != null && !list.isEmpty()){
	        		System.out.println("list tidak kosong");
	        		com001Service.saveToDb(list, key);
	        	}
//	        	return urlview.equalsIgnoreCase("home")?"":urlview;
	        	model.setViewName(urlview);
	        	return model;
	    	   
			} catch (Exception e) {
				e.printStackTrace();
				
			}
	    	    
	    	model.setViewName("previewtmpxls");
	    	return model;
	    }


	  
	    @GetMapping(value="/xlseditnew")
	    public ModelAndView doEditBarangXls(@RequestParam("id")String id
	    		,@RequestParam("clazzname")String clazzname,
	    		@RequestParam("urlpageedit")String urlpageedit,
	    		Model model, HttpSession session) {

	    	try {
	    		
	    		urlpageedit  = urlpageedit.replaceAll("-", "/");
	    		
	    		 Object obTmp	=  session.getAttribute("objxls"+clazzname+id);	
					
	    		
	    		 
	    		 ModelAndView modelnya = new ModelAndView(urlpageedit);
	    		 if(obTmp != null){
	    			 modelnya.addObject("obj", obTmp);
	    			
	    		 }
	    		 return modelnya;
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	  
	    	
	    
	        return  new ModelAndView("home");
	    }
	    
	    @PostMapping("/savebrandxls")
	    public ModelAndView doSaveBrandXls(@RequestBody AhmsdnisMstbrnd obj,
	    		Model model, HttpSession session) {
	    	
	    	System.out.println("masuk doSaveBrandXls");
	    	List<Object> list = (List<Object>) session.getAttribute("listtmpxls");
	    	String err = null;
	    	if(list == null){
	    		list = new ArrayList<>();
	    	}
	    	
	    	
	    		System.out.println(ReflectionToStringBuilder.toString(obj, ToStringStyle.MULTI_LINE_STYLE));
	    		
	    		List<Object> list2 = new ArrayList<>();
	    		for (Object b : list) {
	    			String idnya = com001Service.getId(b);
	    			
					if(idnya.equalsIgnoreCase(obj.getVbrndcd())){
						list2.add(obj);
					}else{
						list2.add(b);
					}
				}
	    		
	    		session.setAttribute("listtmpxls", list2);
	    	
	    	
	    		session.setAttribute("objxls"+obj.getClass().getSimpleName()+obj.getVbrndcd(), obj);
	    

	    	return doPreviewXls(model, session);
	    }
}
