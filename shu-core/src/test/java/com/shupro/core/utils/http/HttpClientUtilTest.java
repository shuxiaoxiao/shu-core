package com.shupro.core.utils.http;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HttpClientUtilTest {

	@Test
	public void doGet_test() {
		String url = "http://localhost/sysUser/list";
		Map<String, Object> param = new HashMap<>();
		param.put("page", 1);
		param.put("rows", 10);
		
		String jsonStr = HttpClientUtil.doGet(url, param);
		System.out.println(jsonStr);
	}

}
