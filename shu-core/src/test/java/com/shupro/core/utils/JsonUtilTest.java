package com.shupro.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shupro.core.utils.json.JsonUtil;

public class JsonUtilTest {

	@Test
	public void obj2JsonStr_test() {
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "val1");
		map.put("key2", "val2");
		
		String jsonStr = JsonUtil.obj2JsonStr(map);
		//{"key2":"val2","key1":"val1"}
		System.out.println(jsonStr);
	}
	
	@Test
	public void jsonStr2Map_test() {
		String jsonStr = "{\"key1\":\"val1\",\"key2\":\"val2\"}";
		Map map = JsonUtil.jsonStr2Map(jsonStr);
		//{key2=val2, key1=val1}
		System.out.println(map);
	}

}
