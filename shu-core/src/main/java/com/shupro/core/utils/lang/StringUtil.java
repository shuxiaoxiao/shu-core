package com.shupro.core.utils.lang;

import java.io.UnsupportedEncodingException;

/**
 * String 的工具类
 * 
 * @author shu
 */
public class StringUtil {
	
    /**
     * A String for a space character.
     */
    public static final String SPACE = " ";

    /**
     * The empty String {@code ""}.
     */
    public static final String EMPTY = "";
	
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

    /**
     * <p>Joins the elements of the provided array into a single String
     * containing the provided list of elements.</p>
     *
     * <p>No delimiter is added before or after the list.
     * Null objects or empty strings within the array are represented by
     * empty strings.</p>
     *
     * <pre>
     * StringUtils.join(null, *)               = null
     * StringUtils.join([], *)                 = ""
     * StringUtils.join([null], *)             = ""
     * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
     * StringUtils.join(["a", "b", "c"], null) = "abc"
     * StringUtils.join([null, "", "a"], ';')  = ";;a"
     * </pre>
     *
     * @param array  the array of values to join together, may be null
     * @param separator  the separator character to use
     * @return the joined String, {@code null} if null array input
     * @since 2.0
     */
    public static String join(final Object[] array, final char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }
	
    
	public static <T> String join(final T... elements) {
        return join(elements, null);
    }
	
    /**
     * <p>
     * Joins the elements of the provided array into a single String containing the provided list of elements.
     * </p>
     *
     * <pre>
     * StringUtils.join(null, *)            = null
     * StringUtils.join([], *)              = ""
     * StringUtils.join([null], *)          = ""
     * StringUtils.join([1, 2, 3], ';')  	= "1;2;3"
     * StringUtils.join([1, 2, 3], null) 	= "123"
     * </pre>
     *
     * @param array 		数组
     * @param separator 	分隔符
     * @param startIndex 	the first index to start joining from
     * @param endIndex 		the index to stop joining from 
     * @return the joined String, {@code null} if null array input
     */
    public static String join(final byte[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            buf.append(array[i]);
        }
        return buf.toString();
    }
    
}
