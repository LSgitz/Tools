package ls.tools.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
public class WriteFile {

    public static void main(String[] args) {  	
        String fileName = "D:/newTemp.txt";
        File file = null;
		try {
			file = new File(fileName);
			if(!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
				
        String content = "hello woshiLS!";
        //按方法1追加文件
        WriteFile.writeFileOnAppend1(file, content);
        //按方法2追加文件
        WriteFile.writeFileOnAppend2(file, content);
    }
	
	/**
     * 使用RandomAccessFile追加内容到文件
     */
    public static void writeFileOnAppend1(File file, String content) {
    	
    	RandomAccessFile randomFile = null;
        try {
            // 打开一个随机访问文件流，按读写方式
            randomFile = new RandomAccessFile(file, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if(randomFile!=null) {
        		try {
					randomFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
		}
    }

    /**
     * 使用FileWriter追加内容到文件
     */
    public static void writeFileOnAppend2(File file, String content) {
    	FileWriter fw = null;
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            fw = new FileWriter(file, true);
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if(fw!=null) {
        		try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
		}
    }

}
