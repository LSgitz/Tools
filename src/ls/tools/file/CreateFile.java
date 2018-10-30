package ls.tools.file;

import java.io.File;
import java.io.IOException;

/**
 * 创建文件或文件夹
 * @author LS
 *
 */
public class CreateFile {

	/**
	 * 创建文件或文件夹
	 * @param filePath 文件路径
	 * @param fileType 文件类别：dir or file
	 * @param isCover 如果文件已存在，是否覆盖
	 * @return
	 */
	public static boolean createFile(String filePath, String fileType, boolean isCover){
		File file = new File(filePath);
		try {
			if(file.exists()) {
				if(isCover) {
					file.delete();
				}else{
					return true;
				}
			}
			if(fileType.equals("dir")){
				file.mkdirs();
			}else if(fileType.equals("file")){
				int endIndex = filePath.lastIndexOf("\\");
				if(endIndex==-1) endIndex = filePath.lastIndexOf("/");
				String dirPath = filePath.substring(0, endIndex);
				File folder = new File(dirPath);
				folder.mkdirs();
				File purFile = new File(filePath);
				purFile.createNewFile();
			}else{
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
