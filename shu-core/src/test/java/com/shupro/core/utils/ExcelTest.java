package com.shupro.core.utils;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.shupro.core.utils.excel.ExcelUtil;

public class ExcelTest {

	@Test
	public void read_test() throws IOException {
		String filePath = "D:\\autotemp\\aa.xlsx";
		List<String[]> list =  ExcelUtil.read(filePath);
		for (String[] stringArr : list) {
			for (String str : stringArr) {
				System.out.print(str + "  ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void write_test() throws IOException {
		String value = "null";
//		String value = "test";
//		String value = "测试账号";
		int cellLength = value.getBytes().length;
		System.out.println(cellLength);
	}
}
