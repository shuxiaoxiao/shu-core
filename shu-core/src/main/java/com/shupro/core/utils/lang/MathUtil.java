package com.shupro.core.utils.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * 数学工具类，如随机数、幂计算等
 *
 * @ClassName: MathUtil
 * @author	shuheng
 */
public class MathUtil {

	/**
	 * 返回的范围是[0,num)
	 * @Title: random
	 * @param @param num
	 * @return int    返回类型
	 */
	public static int random(int num){
		//Math.random() 返回的范围是[0,1)
		return (int)(Math.random()*num);
	}
	
	/**
	 * 返回的范围是[start,end]
	 * @Title: random
	 * @param  start	范围起始值
	 * @param  end	范围终止值
	 * @return int    返回类型
	 */
	public static int random(int start,int end){
		return (int) (Math.random() * (end - start + 1)) + start;
	}
	
	/**
	 * 返回18位随机数, 组成格式：当前日期（yyyyMMddHHmmss）+ 4位随机数
	 * 
	 * @Title: getNewFilename
	 * @param @param oldFilename
	 * @return String    返回类型
	 */
	public static String random() {
		return DateUtil.todayStr() + MathUtil.random(1000,9999);
	}
	
	/**
	 * 显示某个范围内的质数
	 * @param range
	 * @return
	 */
	public static List<Integer> showPrimeList(int range){
		
		return showPrimeList(2, range);
	}
	
	/**
	 * 显示某个范围内的质数
	 * @param range
	 * @return
	 */
	public static List<Integer> showPrimeList(int startRange, int endRange){
		List<Integer> primeList = new ArrayList<>();
		if (startRange < 2 || endRange < 2 ){
			System.out.println("求质数的范围range必须大于2！");
			return null;
		}
		long startTime =System.currentTimeMillis();
		for (int i = startRange; i <= endRange; i++) {
			if(isPrime(i)){
				primeList.add(i);
			}
		}
		long endTime =System.currentTimeMillis();
		System.out.println(endTime - startTime);
		return primeList;
	}

	/**
	 * 判断某个数是不是质数
	 * @param num
	 * @return
	 */
	public static boolean isPrime(int num) {
		boolean flag = true;
		//Math的sqrt方法用于求开方(
		for (int j = 2; j <= Math.sqrt(num); j++) {
			if (num % j == 0) {
				flag = false;
				break;// 跳出循环
			}
		}
		return flag;
	}
	
	/**
	 * 显示某个范围内的质数(第二种方法 效率太低)
	 * @param range
	 * @return
	 */
	@Deprecated
	private static List<Integer> showPrimeList2(int range){
		List<Integer> primeList = new ArrayList<>();
		long startTime =System.currentTimeMillis();
		for (int i = 2; i <= range; i++) {
			for (int j = 2; j <= i; j++) {
				if (i % j == 0 && i != j) {
					break;
				}
				// 输出所有在 i=k 且 i%k=0的数
				if (i % j == 0 && i == j) {
					primeList.add(i);
				}
			}
		}
		long endTime =System.currentTimeMillis();
		System.out.println(endTime - startTime);
		return primeList;
	}

}
