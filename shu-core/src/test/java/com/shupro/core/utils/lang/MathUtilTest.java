package com.shupro.core.utils.lang;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathUtilTest {

	@Test
	public void test() {
		int num2_1 = MathUtil.random(100);// [0, 100)即[0, 99]
		int num2_2 = MathUtil.random(10,99);//	[10, 99]
		String num2_3 = MathUtil.random2(2);//	[10, 100)即[10, 99]
		
		int num3_1 = MathUtil.random(1000);// [0, 1000)即[0, 999]
		int num3_2 = MathUtil.random(100,999);//	[100, 999]
		String num3_3 = MathUtil.random2(3);//	[10, 100)即[100, 999]
		
		System.out.println(num2_1);
		System.out.println(num2_2);
		System.out.println(num2_3);
		System.out.println("=====");
		System.out.println(num3_1);
		System.out.println(num3_2);
		System.out.println(num3_3);
	}

	@Test
	public void test2() {
		String num = MathUtil.random2(10);
		System.out.println(num);
		String num2 = MathUtil.random2(12);
		System.out.println(num2);
		String num3 = MathUtil.random2(20);
		System.out.println(num3);
		String num4 = MathUtil.random2(25);
		System.out.println(num4);
		String num5 = MathUtil.random2(5);
		System.out.println(num5);
//		for (int i = 0; i < 100; i++) {
//			long num = MathUtil.random2(11);
//			System.out.println(num);
//		}
	}
	
}
