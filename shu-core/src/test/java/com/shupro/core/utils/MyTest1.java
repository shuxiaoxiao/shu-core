package com.shupro.core.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.shupro.core.utils.digest.DigestUtil;
import com.shupro.core.utils.lang.DateUtil;
import com.shupro.core.utils.lang.MathUtil;
import com.shupro.core.utils.lang.StringUtil;

public class MyTest1 {

	@Test
	public void test2() {
		
	}
	
	@Test
	public void test1() {
		int length = 3;
		int num = (int) Math.pow(10, length);
		System.out.println(num);
	}
	
	@Test
	public void stringUtil_test() {
		String str = "123.dds";
		String suffix = StringUtil.getSuffix(str);
		System.out.println(suffix);
	}
	
	@Test
	public void digestUtil_test() {
		String pwd = "123";
		String pwd2Md5 = DigestUtil.md5(pwd);
		System.out.println(pwd2Md5);
	}
	
	@Test
	public void mathUtil_test2() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			int num = MathUtil.random2(2);
			list.add(num);
		}
		System.out.println(list.contains(10));
		System.out.println(list.contains(99));
		for (Integer integer : list) {
			System.out.print(integer + ", ");
		}
	}
	@Test
	public void mathUtil_test() {
		
		int range = 100000;
		MathUtil.showPrimeList(range);
//		MathUtil.showPrimeList2(range);
//		System.out.println();
//		System.out.println();
	}
	
	@Test
	public void systemUtil_test2() {
//		String[] strArr = null;
		String[] strArr = new String[2];
		System.out.println(SystemUtil.isEmpty(strArr));
	}
	
	@Test
	public void systemUtil_test1() {
//		List<String> list = null;
		List<String> list = new ArrayList<>();
		list.add("1");
		System.out.println(SystemUtil.isEmpty(list));
	}
	
}
