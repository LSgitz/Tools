package ls.tools.file;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.NumberFormat;

/**
 * 
 * @author LS
 * 
 */
public class LargeMappedFiles {

	static int length = 0x8FFFFFF; // 128 Mb

	public static void main(String[] args) throws Exception {
		MappedByteBuffer out = new RandomAccessFile("test.dat", "rw")
				.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
		for (int i = 0; i < length; i++)
			out.put((byte) 'x');
		System.out.println("Finished writing");
		for (int i = length / 2; i < length / 2 + 6; i++)
			System.out.print((char) out.get(i)); // read file
//		System.out.println(percent(13,44));
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return  p1/p2的百分比
	 */
	public static String percent(double p1, double p2) {
		String str;
		double p3 = p1 / p2;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		str = nf.format(p3);
		return str;
	}

}
