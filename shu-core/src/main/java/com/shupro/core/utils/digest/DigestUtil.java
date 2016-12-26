package com.shupro.core.utils.digest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类<br>
 * 使用前判断下是否为空，如SystemUtil.isEmpty(str);
 * @author shu
 *
 */
public class DigestUtil {

	/**
	 * md5 加密，返回32位（不可逆）
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		
		return DigestUtils.md5Hex(str);
	}
	
	/**
	 * sha256 加密，返回64位（不可逆）
	 * @param str
	 * @return
	 */
	public static String sha256(String str) {
		
		return DigestUtils.sha256Hex(str);
	}
	
	/**
	 * base64加密
	 * @param str
	 * @return
	 */
	public static String encodeBase64(String str){
		byte[] b = Base64.encodeBase64(str.getBytes(), true);
		
		return new String(b);
	}
	
	/**
	 * base64解密
	 * @param str
	 * @return
	 */
	public static String decodeBase64(String str){
		byte[] b = Base64.decodeBase64(str.getBytes());
		
		return new String(b);
	}
	
}
