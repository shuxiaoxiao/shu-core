package com.shupro.core.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shupro.core.utils.lang.DateUtil;
import com.shupro.core.utils.lang.MathUtil;
import com.shupro.core.utils.lang.StringUtil;

/**
 * 系统通用的判断<br>
 * 1.大部分类型 判断是否为null或为空
 * 2.手动生成的id，主要用于编码或主键
 * @author shu
 *
 */
public class SystemUtil {

	/**
	 * 获得36位长度的字符串, 里面包含字符 "-"
	 */
	public static String getUUId() {
		// java.util.UUID 是jdk 提供的类
		String str = java.util.UUID.randomUUID().toString();
		return str;
	}
	
	/**
	 * 获得32位长度的字符串, 实际是将getUUId() 中的字符 "-"去掉后的结果
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
	 * 返回32位随机数, 组成格式：当前日期（yyMMddHHmmss）+ 20位随机数
	 */
	public static String getRandomId2() {
		return getRandomId2(20);
	}
	
	/**
	 * 返回(12+length)位随机数, 组成格式：当前日期（yyMMddHHmmss）+ length位随机数
	 * 
	 * @param @param length 	随机位长度
	 */
	public static String getRandomId2(int length) {
		
		return DateUtil.today2YyMMddHHmmss() + MathUtil.random2(length);
	}
	
	/**
	 * 返回32位随机数, 组成格式：当前日期（yyMMddHHmmssSSS）+ 17位随机数
	 */
	public static String getRandomId3() {
		return getRandomId3(17);
	}
	
	/**
	 * 返回(15+length)位随机数, 组成格式：当前日期（yyMMddHHmmssSSS）+ length位随机数
	 * 
	 * @param @param length 	随机位长度
	 */
	public static String getRandomId3(int length) {
		return DateUtil.today2YyMMddHHmmssSSS() + MathUtil.random2(length);
	}

	/**
	 * 文件重命名（20位+后缀），组成格式：当前日期（yyMMddHHmmssSSS）+ 5位随机数
	 * null表示重命名失败，无后缀
	 * 
	 * @param oldFilename
	 */
	public static String getNewFilename(String oldFilename) {
		String suffix = StringUtil.getSuffix(oldFilename);
		if(suffix != null){
			return getRandomId3(5) + "." + suffix;
		}else{
			return null;
		}
	}
	
	/**
	 * 判断str是否为null或空串（去空格了）,是返回 true
	 * 
	 * @param str
	 */
	public static boolean isEmpty(String str){
		//当str = null时为true，后面的不执行了，所以str = null时不会执行trim()，所以就没问题
		return str == null || str.trim().length() == 0;
	}
	/**
	 * 判断str是否不为null或非空串（去空格了），是返回 true
	 * 
	 * @param str
	 */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	/**
	 * 判断list是否为null或空,是返回 true
	 * 
	 * @param list
	 */
	public static boolean isEmpty(List<?> list){
		return list == null || list.size() == 0;
	}
	/**
	 * 判断list是否不为null或非空串（去空格了），是返回 true
	 * 
	 * @param str
	 */
	public static boolean isNotEmpty(List<?> list){
		return !isEmpty(list);
	}
	
	/**
	 * 判断数组对象 是否为null或空,是返回 true
	 * 
	 * @param list
	 */
	public static boolean isEmpty(Object[] array){
		return array == null || array.length == 0;
	}
	/**
	 * 判断数组对象 是否不为null或非空串（去空格了），是返回 true
	 * 
	 * @param str
	 */
	public static boolean isNotEmpty(Object[] array){
		return !isEmpty(array);
	}
	
	/**
	 * 判断map对象 是否为null或空,是返回 true
	 * 
	 * @param map
	 */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }
    /**
     * 判断map对象 是否不为null或非空,是返回 true
     * 
     * @param map
     */
    public static boolean isNotEmpty(Map map) {
    	return !isEmpty(map);
    }
	
}
