package ls.tools.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * 剪切文件
 * @author LS
 *
 */
public class CutFile {

	/**
	 * 剪切文件
	 * @param sourcePath 源文件路径
	 * @param targetDir 目标路径
	 * @throws IOException
	 */
	public static void cutfile(String sourcePath, String targetDir) throws IOException {
		FileInputStream f_in = null;
		FileOutputStream f_out = null;
		
		sourcePath = sourcePath.replace("\\", "/");
		targetDir = targetDir.replace("\\", "/");
		sourcePath = sourcePath.replace("\\\\", "/");
		targetDir = targetDir.replace("\\\\", "/");
		
		int sepLastIndex = sourcePath.lastIndexOf("/");
		// 源文件名
		String sourceFileName = sourcePath.substring(sepLastIndex + 1);
		// 新文件全路径 
		String newFileAbsPath = targetDir + "/" + sourceFileName;
		// 源文件的 文件夹
		String sourceFileDir = sourcePath.substring(0, sepLastIndex);

		File newFile = new File(newFileAbsPath); 
		File sourceFile = new File(sourcePath);
		if (sourceFile.exists()) {
			if (targetDir.equals(sourceFileDir)) {
				// 原位置剪切不执行任何操作
				System.out.println("目标文件夹和源文件夹相同！不进行剪切！");
				return;
			} else if (newFile.exists()) {
				// 目标文件夹中已经存在同名的文件，则剪切成带标记的新文件
				System.out.println("目标文件夹已经存在相同文件名 的文件！");
				newFileAbsPath = targetDir + "/cut-" + sourceFileName;
				try {
					f_in = new FileInputStream(sourcePath);
					f_out = new FileOutputStream(newFileAbsPath);
					byte[] b = new byte[2048];
					int length = f_in.read(b);
					while (length > 0) {
						f_out.write(b, 0, length);
						length = f_in.read(b);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					f_in.close();
					f_out.close();
				}

				File del = new File(sourcePath);
				del.delete();
			} else {
				File path = new File(targetDir);
				if (!path.exists()) {
					System.out.println("目的文件夹不存在，已自动创建文件夹！");
					path.mkdirs();
				}
				try {
					f_in = new FileInputStream(sourcePath);
					f_out = new FileOutputStream(newFileAbsPath);
					byte[] b = new byte[2048];
					int length = f_in.read(b);
					while (length > 0) {
						f_out.write(b, 0, length);
						length = f_in.read(b);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					f_in.close();
					f_out.close();
				}
				File del = new File(sourcePath);
				del.delete();
			}
			System.out.println("剪切已完成");
		} else {
			System.out.println("源文件不存在");
			return;
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		System.out.println("请输入要剪切的源文件的地址,如:d:/abc/as/xxx.txt");
		Scanner in = new Scanner(System.in);
		String str_s = in.next();

		System.out.println("请输入目的地址,如:   d:/abc/as  或者   c:   ");
		String str_d = in.next();
		CutFile.cutfile(str_s,str_d);
	}
}
