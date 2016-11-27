package com.shupro.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MyTest1 {

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
