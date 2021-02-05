package id.co.ahm.sd.nis.app000.utils;

import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsUtils {
	

	public final static void write(List<Object> list, XSSFWorkbook workbook,String sheetName, 
			Integer sheetIndex,String path,String outputFileName)throws Exception{
		// jika sheetIndex == null, artinya create new sheet..kalau tidak null akan menuli dimana sheetIndex di set
		
        String outputFile = path+"/"+outputFileName;
         
		if(workbook == null){
			workbook = new XSSFWorkbook();
			sheetIndex = null; // file xlsx baru, jadi hanya ada 1 sheet
		}
		 XSSFSheet sheet = null;
		if(sheetIndex == null){
			sheet = workbook.createSheet(sheetName);
		}else{
			sheet = workbook.getSheetAt(sheetIndex);
		}
         
		SimpleDateFormat formter = new SimpleDateFormat("yyyy-MM-dd");
        int rowCount = 0;
        for (Object obj : list) {
			
            Row row = sheet.createRow(++rowCount);
            Field[] fields        = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
            	 Annotation[] annotations = field.getAnnotations();
            	 for (Annotation ann : annotations) {
            		 if( ann instanceof MapXls){
            			 MapXls mapxls = (MapXls) ann;
            			 Cell cell = row.createCell(mapxls.indexXls());
            			try {
            				 Object ox =PropertyUtils.getProperty(obj, field.getName());
                			 if (field.getType().getSimpleName().equalsIgnoreCase("string") ) {
                                 cell.setCellValue((String)ox );
                             } else  if (field.getType().getSimpleName().equalsIgnoreCase("integer") ) {
                                 cell.setCellValue((Integer) ox);
                             } else  if (field.getType().getSimpleName().equalsIgnoreCase("date") ) {
                                 cell.setCellValue(formter.format((Date) ox));
                             }
						} catch (Exception e) {
							e.printStackTrace();
						}
            		 }
            		 
            	 }
            	 
			}
            
             
        }
         
       
       
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            workbook.write(outputStream);
            System.out.println("Write xls to "+outputFile);
            outputStream.flush();
            outputStream.close();
        }
	}
}
