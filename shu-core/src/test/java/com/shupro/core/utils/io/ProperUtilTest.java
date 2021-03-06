package com.shupro.core.utils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

import com.shupro.core.utils.io.ProperUtil;
import com.shupro.core.utils.io.ZipUtil;

public class ProperUtilTest {
	
	@Test
	public void properUtil_3() throws IOException {
		String filePath = "src/test/resources/resource/config.properties";
		ProperUtil.write(filePath, "username", "username33");
		ProperUtil.write(filePath, "username2", "username11");
//		System.out.println(map);
	}
	
	@Test
	public void properUtil_2() throws IOException {
		String filePath = "resource/config.properties";
		Map<String, String> map = ProperUtil.read2Map(filePath);
		System.out.println(map);
	}
	
	@Test
	public void properUtil_1() throws IOException {
		String filePath = "resource/config.properties";
		String val = ProperUtil.read(filePath, "username");
		System.out.println(val);
		String val2 = ProperUtil.read(filePath, "58");
		System.out.println(val2);
	}
	
}
