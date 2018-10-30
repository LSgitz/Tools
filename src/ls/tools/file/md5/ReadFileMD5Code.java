package ls.tools.file.md5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 递归读取某个文件夹下的所有文件的MD5码
 * @author LS
 *
 */
public class ReadFileMD5Code {

	public static void main(String[] args) {
		try {
			String folderPath = System.getProperty("user.dir") + File.separator + "data";
			File folder = new File(folderPath);
			List<String> md5List = new ArrayList<String>();
			readFolder(folder, md5List);
			if(md5List.isEmpty()) {
				System.out.println("该文件夹下没有文件！");
			}else {
				for (String md5 : md5List) {  
				    System.out.println(md5);  
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
    }  
		
	/**
	 * 递归遍历文件夹，计算每个文件的MD5码并存入md5List
	 * @param folder
	 * @param md5List
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void readFolder(File folder, List<String> md5List) throws FileNotFoundException, IOException {
		File[] files = folder.listFiles();
		if(files == null) {
			return;
		}
		for(File file : files) {
			if(file.isFile()) {
				String md5Code = DigestUtils.md5Hex(new FileInputStream(file));
				md5List.add(md5Code);
			}else if(file.isDirectory()) {
				readFolder(file, md5List);
			}
		}
	}

}
