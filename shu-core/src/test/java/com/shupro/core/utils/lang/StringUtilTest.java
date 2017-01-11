package com.shupro.core.utils.lang;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void test() {
		String str = "aa.xls";
		String suffix = StringUtil.getSuffix(str);
		System.out.println(suffix);//xls
	}
	
	@Test
	public void parseEmpty_test() {
		String str1 = "";
		String returnStr1 = StringUtil.parseEmpty(str1, "1");
		System.out.println(returnStr1);//1
		String str2 = "  ";
		String returnStr2 = StringUtil.parseEmpty(str2, "1");
		System.out.println(returnStr2);//1
		String str3 = null;
		String returnStr3 = StringUtil.parseEmpty(str3, "1");
		System.out.println(returnStr3);//1
		
		String str4 = "aa";
		String returnStr4 = StringUtil.parseEmpty(str4, "1");
		System.out.println(returnStr4);//aa
	}

}
