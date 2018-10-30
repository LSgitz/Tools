package ls.tools.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

/**
 * 读取文件
 * @author LS
 *
 */
public class ReadFile {
	
	
	public static void main(String[] args) {
		String pjPath = System.getProperty("user.dir");
		File file = new File(pjPath + File.separator + "data" + File.separator + "metadata.xml");
		ReadFile t = new ReadFile();
		
		long start = System.currentTimeMillis();
		System.out.println("*****************readFileByByte1****************");
		t.readFileByByte1(file);
		System.out.println("----read耗时:" + (System.currentTimeMillis() - start) + "MS\n");
		
		start = System.currentTimeMillis();
		System.out.println("*****************readFileByByte2****************");
		t.readFileByByte2(file);
		System.out.println("----read耗时:" + (System.currentTimeMillis() - start) + "MS\n");
		
		start = System.currentTimeMillis();
		System.out.println("*****************readFileByByte3****************");
		t.readFileByByte3(file);
		System.out.println("----read耗时:" + (System.currentTimeMillis() - start) + "MS\n");
		
		start = System.currentTimeMillis();
		System.out.println("*****************readFileByChar1****************");
		t.readFileByChar1(file);
		System.out.println("----read耗时:" + (System.currentTimeMillis() - start) + "MS\n");
		
		start = System.currentTimeMillis();
		System.out.println("*****************readFileByChar2****************");
		t.readFileByChar2(file);
		System.out.println("----read耗时:" + (System.currentTimeMillis() - start) + "MS\n");
		
		start = System.currentTimeMillis();
		System.out.println("*****************readFileByLine****************");
		t.readFileByLine(file);
		System.out.println("----read耗时:" + (System.currentTimeMillis() - start) + "MS\n");
		
		start = System.currentTimeMillis();
		System.out.println("*****************readFileByRandomAccess****************");
		t.readFileByRandomAccess(file);
		System.out.println("----read耗时:" + (System.currentTimeMillis() - start) + "MS\n");
	}
	
	/**
	 * 以字节为单位读取文件，逐个读取字节
	 * @param file
	 */
	public String readFileByByte1(File file) { 
		System.out.println("以字节为单位读取文件,逐个读取字节...");
		FileInputStream fis = null;
		byte[] resByte = null;
		String res = null;
		try {
			fis = new FileInputStream(file);
			resByte = new byte[fis.available()];
			// 结果集中元素下标
			int btIndex = 0;
			int bt;
			while ((bt = fis.read()) != -1) {
				resByte[btIndex] = (byte) bt;
				btIndex++;
			}
			res = new String(resByte, "utf-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * 以字节为单位读取文件,使用缓冲流,从缓冲区中读取字节,大大减少通过底层的I/O操作
	 * @param file
	 */
	public String readFileByByte2(File file) { 
		System.out.println("以字节为单位读取文件,使用缓冲流,从缓冲区中读取字节...");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		byte[] resByte = null;
		String res = null;
		try {
			fis = new FileInputStream(file);
			resByte = new byte[fis.available()];
			bis = new BufferedInputStream(fis);
			int btIndex = 0;
			int bt;
			while ((bt = bis.read()) != -1) {
				resByte[btIndex] = (byte) bt;
				btIndex++;
			}
			System.out.println("文件内容:");
			res = new String(resByte, "utf-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 不用关闭fileinputstream
				if(bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return res;
	}
	
	/**
	 * 以字节为单位读取文件,块读取,直接将数据存入缓冲区中
	 * @param file
	 */
	public String readFileByByte3(File file) { 
		System.out.println("以字节为单位读取文件,块读取,直接将数据存入缓冲区中,一次读多个字节...");
		FileInputStream fis = null;
		byte[] resByte = null;
		String res = null;
		try {
			fis = new FileInputStream(file);
			resByte = new byte[fis.available()];
			byte[] buf = new byte[10240];
			// 结果集中的元素下标
			int btIndex = 0;
			// 每次读入的字节数
			int oneReadNum = -1; 
			while ((oneReadNum = fis.read(buf)) != -1) {
				for (int i = 0; i < oneReadNum; i++) {
					resByte[btIndex] = buf[i];
					btIndex++;
				}
			}
			res = new String(resByte, "utf-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public String readFileByChar1(File file) {
    	System.out.println("以字符为单位读取文件内容,一次读一个字符...");
        Reader reader = null;
        StringBuilder res = new StringBuilder();
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar = -1;
            // 一次读一个字符
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行
                // 但如果这两个字符分开显示时，会换两次行
                // 因此，屏蔽掉\r，或者屏蔽\n；否则，将会多出很多空行
                if (((char) tempchar) != '\r') {
                    res.append((char) tempchar);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
        		if(reader != null) {
        			reader.close();
        		}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return res.toString();
    }
    
    /**
     * 以字符为单位读取文件内容，一次读多个字符
     * @param file
     * @return
     */
	public String readFileByChar2(File file) {
		System.out.println("以字符为单位读取文件内容,一次读多个字符...");
		Reader reader = null;
		StringBuilder res = new StringBuilder();
		try {
			reader = new InputStreamReader(new FileInputStream(file));
			// 一次读多个字符
			char[] tempchars = new char[10240];
			int oneReadNum = -1;
			// 读入多个字符到字符数组中，oneReadNum为一次读取字符数
			while ((oneReadNum = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉\r不显示
				if ((oneReadNum == tempchars.length) && (tempchars[tempchars.length-1] != '\r')) {
					res.append(tempchars);
				} else {
					for (int i = 0; i < oneReadNum; i++) {
						if (tempchars[i] == '\r') {
							continue;
						} else {
							res.append(tempchars[i]);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return res.toString();
	}

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     * @param fileName
     */
    public String readFileByLine(File file) {
    	System.out.println("以行为单位读取文件内容,一次读一整行...");
    	StringBuilder res = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	res.append(tempString).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                	System.out.println("close BufferedReader fail!");
                }
            }
        }
        return res.toString();
    }

    /**
     * 使用随机访问文件流，随机读取文件内容
     */
    public String readFileByRandomAccess(File file) {
    	System.out.println("随机读取一段文件内容...");
        RandomAccessFile randomFile = null;
        byte[] resByte = null;
        String res = null;
        try {
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(file, "r");
            // 文件长度，字节数
            int fileLength = (int) randomFile.length();
            resByte = new byte[fileLength];
            // 读文件的起始位置
            int beginIndex = 0;
            // 将读文件的开始位置移到beginIndex位置
            randomFile.seek(beginIndex);
            byte[] tempBytes = new byte[10240];
            int oneReadNum = -1;
            int btIndex = 0;
            // 一次读10240个字节，如果文件内容不足10240个字节，则读剩下的字节
            while ((oneReadNum = randomFile.read(tempBytes)) != -1) {
            	for(int i=0; i<oneReadNum; i++) {
            		resByte[btIndex] = tempBytes[i];
            		btIndex++;
            	}
            }
            res = new String(resByte, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                	e1.printStackTrace();
                }
            }
        }
        return res;
    }
    
    /**
     * 以字节为单位读取文件，一次读一个字节，直接打印
     * 常用于读二进制文件，如图片、声音、影像等文件
     */
    @SuppressWarnings("unused")
	private void read1_syswrite(File file) {
    	System.out.println("以字节为单位读取文件内容，一次读一个字节：");
    	FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

    /**
     * 以字节为单位读取文件，一次读多个字节，直接打印
     * @param file
     */
    @SuppressWarnings("unused")
	private void read3_syswrite(File file) {
		System.out.println("以字节为单位读取文件内容，一次读多个字节：");
		FileInputStream fis = null;
		try {
			byte[] tempbytes = new byte[10240];
			int oneReadNum = 0;
			fis = new FileInputStream(file);
			// 读入多个字节到字节数组中，oneReadNum为一次读入的字节数
			while ((oneReadNum = fis.read(tempbytes)) != -1) {
				System.out.write(tempbytes, 0, oneReadNum);
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
