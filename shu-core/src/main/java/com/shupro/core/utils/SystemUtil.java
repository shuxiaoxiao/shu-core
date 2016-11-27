package com.shupro.core.utils;

import java.util.List;

/**
 * 系统通用的判断<br>
 * 1.大部分类型 判断是否为null或为空
 * @author shu
 *
 */
public class SystemUtil {

	/**
	 * 获得32位长度的字符串
	 * 
	 * @Title: getUUId32
	 * @param @return 设定文件
	 * @return String 返回类型
	 */
	public static String getUUId32() {
		// java.util.UUID 是jdk 提供的类
		String str = java.util.UUID.randomUUID().toString();
		// String uuids = str.substring(0, 8) + str.substring(9, 13)
		// + str.substring(14, 18) + str.substring(19, 23)
		// + str.substring(24);
		// 方法二：与截取字符串的速度差不多
		String uuids = str.replace("-", "");
		return uuids;
	}
	
	/**
	 * 判断str是否为null或空串（去空格了）,是返回 true
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		//当str = null时为true，后面的不执行了，所以str = null时不会执行trim()，所以就没问题
		return str == null || str.trim().length() == 0;
	}
	public static boolean isNotEmpty(String str){
		
		return !isEmpty(str);
	}
	
	/**
	 * 判断list是否为null或空,是返回 true
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<?> list){
		return list == null || list.size() == 0;
	}
	public static boolean isNotEmpty(List<Object> list){
		return isEmpty(list);
	}
	
	/**
	 * 判断array是否为null或空,是返回 true
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(Object[] array){
		return array == null || array.length == 0;
	}
	public static boolean isNotEmpty(Object[] array){
		return isEmpty(array);
	}
	
}
