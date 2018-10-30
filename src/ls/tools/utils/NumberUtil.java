package ls.tools.utils;

import java.util.List;

public class NumberUtil {

	/**
	 * 从一组整形数据的list集合中获取最大值
	 * @param nums
	 */
	public static Integer getMaxValueFromNumSet(List<Integer> nums) {
		for(int i=0; i<nums.size()-1; i++) {
			if(nums.get(0)<nums.get(i+1)) nums.set(0, nums.get(i+1));
		}
		return nums.get(0);
	}
	
	/**
	 * 从一段数值中随机获取一个数值
	 * @param range
	 * @return
	 */
	public static float getRandomFromRange(String range){
		String params[] = range.split("-");
		float min = Float.parseFloat(params[0]);
		float max = Float.parseFloat(params[1]);		
		float random=(float) (Math.random()*(max-min)+min);
		return random;
	}
	
	public static void main(String[] args) {
		for(int i=0; i< 100; i++) {
			System.out.println((int) Math.floor(getRandomFromRange(new String("5.07-5.27")) * 30));
		}
	}
}
