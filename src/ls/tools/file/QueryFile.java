package ls.tools.file;

import java.io.File;
import java.util.List;

/**
 * 查询文件或文件夹的相关操作
 * @author LS
 *
 */
public class QueryFile {

	/**
	 * 获取某个目录下及其子目录下所有文件的绝对路径
	 * @param dirPath
	 * @return
	 */
	public static void getAllFilePath(String filePath, List<String> res){
		File file = new File(filePath);
		if(file.exists()){
			if(file.isDirectory()){
				File[] files = file.listFiles();
				for(File fi : files){
					String fip = fi.getAbsolutePath();
					getAllFilePath(fip, res);
				}
			}else{
				String path = file.getAbsolutePath();
				res.add(path);
			}
		}else{
			System.err.println("目标路径：["+filePath+"]不存在！");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
