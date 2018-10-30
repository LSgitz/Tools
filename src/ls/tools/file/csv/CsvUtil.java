package ls.tools.file.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * 使用JAVA CSV实现读写csv文件
 * @author LS
 *
 */
public class CsvUtil {
	
	/**
	 * 读取CSV文件
	 * @param csvFilePath
	 * @return csvFileList -- 行为list元素、列为数组的有序集合
	 */
	public static ArrayList<String[]> readCSV(String csvFilePath) {  
		// 用来保存数据  
		ArrayList<String[]> csvData = new ArrayList<String[]>();  
	    try { 
	        // 创建CSV读对象 例如:CsvReader(文件路径，分隔符，编码格式);  
	        CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("GBK"));  
	        // 逐行读入数据  
	        while (reader.readRecord()) { 
	        	csvData.add(reader.getValues());   
	        }  
	        reader.close();  
	        System.out.println("读取CSV文件[csvFilePath]成功！");
	    } catch (IOException e) {  
	        System.out.println("读取CSV文件[csvFilePath]失败！");
	        e.printStackTrace();
	    }  
	   return csvData;
	}  

	/**
	 * 覆盖写入数据至CSV文件
	 * @param csvFilePath
	 * @param content
	 * @param csvHeaders
	 * @return
	 */
	public static boolean writeCSVCover(String csvFilePath, ArrayList<String[]> content, String[] csvHeaders) {  
	   try {  
	       // 创建CSV写对象 参数:CsvWriter(文件路径，分隔符，编码格式);  
	       CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("GBK"));  
	       // 写表头  
	       if(csvHeaders!=null && csvHeaders.length>0){
	    	   csvWriter.writeRecord(csvHeaders);  
	       }
	       // 写内容  
	       for (String[] line: content) {  
	    	   csvWriter.writeRecord(line);
	       }  
	       csvWriter.close();  
	       System.out.println("写入数据至CSV文件[csvFilePath]成功！");
	    } catch (IOException e) {  
	    	System.out.println("写入数据至CSV文件[csvFilePath]失败！");
	    	e.printStackTrace();
	    	return false;
	    }  
	   return true;
	}  
	
	/**
	 * 追加写入数据至CSV文件
	 * @param csvFilePath
	 * @param content
	 * @param csvHeaders
	 * @return
	 */
	public static boolean writeCSVAdd(String csvFilePath, ArrayList<String[]> content) {  
	   try { 
		   File file = new File(csvFilePath);
		   if(file.exists()) {
			   if(!file.isDirectory()) {
				   FileOutputStream fos = new FileOutputStream(file, true);
				   OutputStreamWriter os = new OutputStreamWriter(fos, "GBK");
				   BufferedWriter out = new BufferedWriter(os, 1024);
				   // 创建CSV写对象 参数:CsvWriter(输出流，分隔符);  
			       CsvWriter csvWriter = new CsvWriter(out, ','); 
			       // 写内容  
			       for (String[] line: content) {  
			    	   csvWriter.writeRecord(line);
			       }  
			       csvWriter.close();  
			       System.out.println("写入数据至CSV文件["+csvFilePath+"]成功！");
			   }else {
				   System.out.println("写入数据至CSV文件[csvFilePath]失败！");
				   return false;
			   }
		   }else {
			   System.out.println("写入数据至CSV文件[csvFilePath]失败！");
			   return false;
		   }
	    } catch (IOException e) {  
	    	System.out.println("写入数据至CSV文件[csvFilePath]失败！");
	    	return false;
	    }  
	   return true;
	}  
	
	public static void main(String[] args) {
		// 定义一个CSV路径 
        String csvFilePath = "D://AcctAdtAaaRq.csv";
        ArrayList<String[]> content = new ArrayList<String[]>();
        String[] line2 = new String[] {"fyy", "24", "170", "145"};
        String[] line3 = new String[] {"zjc", "25", "170", "112"};
        String[] line4 = new String[] {"zh", "28", "175", "130"};
        content.add(line2);
        content.add(line3);
        content.add(line4);
        writeCSVCover(csvFilePath, content, new String[] {"姓名", "年龄", "身高", "体重"});
//        writeCSVAdd(csvFilePath, content);
        
//        ArrayList<String[]> csvData = CsvUtil.readCSV(csvFilePath);
//        for(String[] strArr : csvData) {
//        	System.out.println(strArr[0]);
//        }
	}
}
