package com.shupro.core.utils.lang;

import java.io.UnsupportedEncodingException;

/**
 * String 的使用例子
 * 
 * @ClassName: StringUtil
 * @author shuheng
 */
public class StringUtil {
	
	/**
	 * 将ISO-8859-1格式 转为其他编码
	 * @param str 需要转码的字符串
	 * @param encoding 编码
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String transcodage(String str, String encoding) throws UnsupportedEncodingException {
		String resultStr = "";
		if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
			resultStr = new String(str.getBytes("ISO-8859-1"), encoding);
		} else {
			resultStr = str;
		}
		return resultStr;
	}
	
	/**
	 * 获得文件后缀,null表示无后缀,如返回doc 或 xls等
	 * @param str
	 * @return
	 */
	public static String getSuffix(String str) {
		int index = str.lastIndexOf(".");
		if (index != -1) {
			String suffix = str.substring(index + 1);
			return suffix;
		} else {
			return null;
		}
	}
	
	/**
	 * 判断是否为null或空串（去空格了），是返回 true
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		//当str = null时为true，后面的不执行了，所以str = null时不会执行trim()，所以就没问题
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * 判断是否不为null或非空串（去空格了），是返回 true
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		
		return !isEmpty(str);
	}
	
	/**
	 * 转换空串，如str是空串或null 则转成num，不为空就是本身
	 * @param str
	 * @param num
	 * @return
	 */
	public static String parseEmpty(String str, String num){
		if(isEmpty(str)){
//			if(!isEmpty(num)){
//				return num;
//			}
			return num;
		}
		
		return str;
	}
}
