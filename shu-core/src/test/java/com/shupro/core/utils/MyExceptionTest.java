package com.shupro.core.utils;

import org.junit.Test;

public class MyExceptionTest {
	
	private int div(int a, int b) {
		try {
			return a/b;
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		} catch (ArithmeticException e) {
			System.out.println("ArithmeticException");
		} catch (Exception e) {
			System.out.println("Exception");
		}finally {
			System.out.println("finally");
		}
		
		return 0;
	}
	
	@Test
	public void test1() {
		System.out.println("商是：" + div(9, 1));
	}

}
