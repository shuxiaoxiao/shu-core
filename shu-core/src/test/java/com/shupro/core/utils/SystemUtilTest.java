package com.shupro.core.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class SystemUtilTest {

	@Test
	public void getNewFilename_test() {
		String oldFileName = "aa.xls";
		//17011114403356760983.xls
		String newFilename = SystemUtil.getNewFilename(oldFileName);
		
		System.out.println(newFilename);
	}
	
	@Test
	public void getRandomId_test() {
		//32位：17011910161513845271701830725454
		String randomId1 = SystemUtil.getRandomId2();
		//37位：1701191016151314756505125288145395978
		String randomId2 = SystemUtil.getRandomId2(25);
		
		//32位：17011910161534619773494472961210
		String randomId3 = SystemUtil.getRandomId3();
		//40位：1701191016153461114856486186800356197591
		String randomId4 = SystemUtil.getRandomId3(25);
		
		System.out.println(randomId1.length() + "位：" + randomId1);
		System.out.println(randomId2.length() + "位：" + randomId2);
		System.out.println(randomId3.length() + "位：" + randomId3);
		System.out.println(randomId4.length() + "位：" + randomId4);
	}
	
	@Test
	public void test() {
		//36位: 66ac8bbc-0176-4262-9619-69fb08ae2ebe
		String uuid1 = SystemUtil.getUUId();
		//32位: 618eeb645e1e4dbeb7dd08ac3c82b2b3
		String uuid2 = SystemUtil.getUUId32();
		
		System.out.println(uuid1.length() + ":" + uuid1);
		System.out.println(uuid2.length() + ":" + uuid2);
	}

}
