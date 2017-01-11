package com.shupro.core.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.shupro.core.utils.digest.DigestUtil;

public class DigestUtilTest {

	@Test
	public void md5_test() {
		String str = "test";
		String md5Str = DigestUtil.md5(str);
		//32位,098f6bcd4621d373cade4e832627b4f6
		System.out.println(md5Str);
	}
	
	@Test
	public void sha256_test() {
		String str = "test";
		String sha256Str = DigestUtil.sha256(str);
		//64位,9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08
		System.out.println(sha256Str);
	}
	
	@Test
	public void encodeBase64_test() {
		String str = "test";
		String base64Str = DigestUtil.encodeBase64(str);
		//dGVzdA==
		System.out.println(base64Str);
	}
	
	@Test
	public void decodeBase64_test() {
		String str = "dGVzdA==";
		String base64Str = DigestUtil.decodeBase64(str);
		//test
		System.out.println(base64Str);
	}
	

	public void test1() {
		String str = "test";
		String md5 = DigestUtils.md5Hex(str);
		//32位,098f6bcd4621d373cade4e832627b4f6
		System.out.println(md5.length() +"位,"+md5);
		
		String sha256 = DigestUtils.sha256Hex(str);
		//64位,9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08
		System.out.println(sha256.length() +"位,"+sha256);
		
		String sha1 = DigestUtils.sha1Hex(str);
		//40位,a94a8fe5ccb19ba61c4c0873d391e987982fbbd3
		System.out.println(sha1.length() +"位,"+sha1);
		
//		Base64.
//		//加密
//		String str= "test,123"; // abc为要加密的字符串
//		byte[] b = Base64.encodeBase64(str.getBytes(), true);
//		System.out.println(new String(b));

		//解密
//		String decodeStr = "YWJj"; // YWJj为要解密的字符串
//		byte[] b2 = Base64.decodeBase64(decodeStr.getBytes());
//		System.out.println(new String(b2));
	}
}
