package ls.tools.file.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 读写excel
 * @author LS
 *
 */
public class ExcelUtil {
	private static DecimalFormat df = new DecimalFormat("0");
	private static SimpleDateFormat sdf = new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss"); 
	private static DecimalFormat nf = new DecimalFormat("0.00");  
	
	public static ArrayList<ArrayList<Object>> readExcel(File file){
		if(file == null){
			return null;
		}
		if(file.getName().endsWith("xlsx")){
			return readExcel2007(file);
		}else{
			return readExcel2003(file);
		}
	}
	
	public static ArrayList<ArrayList<Object>> readExcel2003(File file){
		try{
			ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> colList;
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;
			Object value;
			for(int i = sheet.getFirstRowNum() , rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows() ; i++ ){
				row = sheet.getRow(i);
				colList = new ArrayList<Object>();
				if(row == null){
					if(i != sheet.getPhysicalNumberOfRows()){
						rowList.add(colList);
					}
					continue;
				}else{
					rowCount++;
				}
				for( int j = row.getFirstCellNum() ; j <= row.getLastCellNum() ;j++){
					cell = row.getCell(j);
					if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
						if(j != row.getLastCellNum()){
							colList.add("");
						}
						continue;
					}
					switch(cell.getCellType()){
					 case XSSFCell.CELL_TYPE_STRING:  
		                    value = cell.getStringCellValue();  
		                    break;  
		                case XSSFCell.CELL_TYPE_NUMERIC:  
		                    if ("@".equals(cell.getCellStyle().getDataFormatString())) {  
		                        value = df.format(cell.getNumericCellValue());  
		                    } else if ("General".equals(cell.getCellStyle()  
		                            .getDataFormatString())) {  
		                        value = nf.format(cell.getNumericCellValue());  
		                    } else {  
		                        value = sdf.format(HSSFDateUtil.getJavaDate(cell  
		                                .getNumericCellValue()));  
		                    }  
		                    break;  
		                case XSSFCell.CELL_TYPE_BOOLEAN:  
		                    value = Boolean.valueOf(cell.getBooleanCellValue());
		                    break;  
		                case XSSFCell.CELL_TYPE_BLANK:  
		                    value = "";  
		                    break;  
		                default:  
		                    value = cell.toString();  
					}// end switch
					colList.add(value);
				}//end for j
				rowList.add(colList);
			}//end for i
			
			return rowList;
		}catch(Exception e){
			return null;
		}
	}
	
	public static ArrayList<ArrayList<Object>> readExcel2007(File file){
		try{
			ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> colList;
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			Object value;
			for(int i = sheet.getFirstRowNum() , rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows() ; i++ ){
				row = sheet.getRow(i);
				colList = new ArrayList<Object>();
				if(row == null){
					if(i != sheet.getPhysicalNumberOfRows()){
						rowList.add(colList);
					}
					continue;
				}else{
					rowCount++;
				}
				for( int j = row.getFirstCellNum() ; j <= row.getLastCellNum() ;j++){
					cell = row.getCell(j);
					if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
						if(j != row.getLastCellNum()){
							colList.add("");
						}
						continue;
					}
					switch(cell.getCellType()){
					 case XSSFCell.CELL_TYPE_STRING:  
		                    value = cell.getStringCellValue();  
		                    break;  
		                case XSSFCell.CELL_TYPE_NUMERIC:  
		                    if ("@".equals(cell.getCellStyle().getDataFormatString())) {  
		                        value = df.format(cell.getNumericCellValue());  
		                    } else if ("General".equals(cell.getCellStyle()  
		                            .getDataFormatString())) {  
		                        value = nf.format(cell.getNumericCellValue());  
		                    } else {  
		                        value = sdf.format(HSSFDateUtil.getJavaDate(cell  
		                                .getNumericCellValue()));  
		                    }  
		                    break;  
		                case XSSFCell.CELL_TYPE_BOOLEAN:  
		                    value = Boolean.valueOf(cell.getBooleanCellValue());
		                    break;  
		                case XSSFCell.CELL_TYPE_BLANK:  
		                    value = "";  
		                    break;  
		                default:  
		                    value = cell.toString();  
					}// end switch
					colList.add(value);
				}//end for j
				rowList.add(colList);
			}//end for i
			
			return rowList;
		}catch(Exception e){
			System.out.println("exception");
			return null;
		}
	}
	
	public static void writeExcel(ArrayList<ArrayList<Object>> result,String path){
		if(result == null){
			return;
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		for(int i = 0 ;i < result.size() ; i++){
			 HSSFRow row = sheet.createRow(i);
			if(result.get(i) != null){
				for(int j = 0; j < result.get(i).size() ; j ++){
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(result.get(i).get(j).toString());
				}
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        try
        {
            wb.write(os);
        } catch (IOException e){
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        File file = new File(path);
        OutputStream fos  = null;
        try
        {
            fos = new FileOutputStream(file);
            fos.write(content);
            os.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }           
	}
	
	public static DecimalFormat getDf() {
		return df;
	}
	public static void setDf(DecimalFormat df) {
		ExcelUtil.df = df;
	}
	public static SimpleDateFormat getSdf() {
		return sdf;
	}
	public static void setSdf(SimpleDateFormat sdf) {
		ExcelUtil.sdf = sdf;
	}
	public static DecimalFormat getNf() {
		return nf;
	}
	public static void setNf(DecimalFormat nf) {
		ExcelUtil.nf = nf;
	}
}