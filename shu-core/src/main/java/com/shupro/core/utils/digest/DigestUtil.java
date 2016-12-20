package com.shupro.core.utils.digest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类<br>
 * 使用前判断下是否为空，如SystemUtil.isEmpty(str);
 * @author shu
 *
 */
public class DigestUtil {

	/**
	 * md5 加密，返回32位
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		
		return DigestUtils.md5Hex(str);
	}
	
	/**
	 * sha256 加密，返回64位
	 * @param str
	 * @return
	 */
	public String sha256(String str) {
		
		return DigestUtils.sha256Hex(str);
	}
	
}
