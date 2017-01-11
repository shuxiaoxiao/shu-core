package com.shupro.core.utils.io;

import org.junit.Test;

public class ZipUtilTest {

	
	@Test
	public void unzip_test() throws Exception {
		String zipFilePath = "D:\\autotemp\\autotemp.zip";
		String unzipFilePath = "D:\\autotemp";

		ZipUtil.unzip(zipFilePath, unzipFilePath, true);
	}
	
	@Test
	public void zip_test() throws Exception {
		String dir = "D:\\autotemp";
		String zipPath = "D:\\";
		String zipFileName = "autotemp.zip";
		
		ZipUtil.zip(dir, zipPath, zipFileName);
	}

}
