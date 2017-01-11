package com.shupro.core.utils.lang;

import java.math.BigDecimal;

import org.junit.Test;

public class BigDecimalUtilTest {

	@Test
	public void add_test1() {
		String num1 = "1";
		String num2 = "";
		BigDecimal rs = BigDecimalUtil.add(num1, num2);
		System.out.println(rs);//1
	}
	
	@Test
	public void add_test2() {
		String num1 = "1";
		String num2 = "2";
		BigDecimal rs = BigDecimalUtil.add(num1, num2);
		System.out.println(rs);
	}
	
	@Test
	public void subtract_test() {
		String num1 = "3";
		String num2 = "2";
		BigDecimal rs = BigDecimalUtil.subtract(num1, num2);
		System.out.println(rs);//1
	}
	
	@Test
	public void multiply_test() {
		String num1 = "3";
		String num2 = "";
		BigDecimal rs = BigDecimalUtil.multiply(num1, num2);
		System.out.println(rs);//0
	}
	
	@Test
	public void divide_test() {
		String num1 = "3";
		String num2 = "2";
		BigDecimal rs = BigDecimalUtil.divide(num1, num2, 2);
		System.out.println(rs);//1.50
	}
	
	@Test
	public void round_test() {
		String num1 = "3.1415926";
		BigDecimal rs = BigDecimalUtil.round(num1, 2);
		System.out.println(rs);//3.14
	}

}
