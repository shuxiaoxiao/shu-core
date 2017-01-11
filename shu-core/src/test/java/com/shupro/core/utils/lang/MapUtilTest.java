package com.shupro.core.utils.lang;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MapUtilTest {

	Map<String, String> map;
	Map<String, Double> map2;
	
	@Before
	public void init() {
		map = new HashMap<>();
		map.put("k1", "40");
		map.put("k2", "50");
		map.put("k3", "60.3");
		map.put("k4", "55.3");
		map.put("k5", "100");
//		map.put("k6", "40");
		
		map2 = new HashMap<>();
		map2.put("k1", 50d);
		map2.put("k2", 40d);
		map2.put("k3", 60.3);
		map2.put("k4", 55.3);
		map2.put("k5", 100d);
	}
	
	@Test
	public void show_test() {
		MapUtil.show1(map);
		System.out.println("=====");
		MapUtil.show2(map);
	}
	
	@Test
	public void getKey_test() {
		String value = "40";
		String key = MapUtil.getKey(map, value);
		System.out.println(key);//k2,k6
	}
	
	@Test
	public void remove_test() {
		System.out.println("map 前："+map);
		//不存在的key
		boolean isSuccess = MapUtil.remove(map, "key1");
		System.out.println("map remove："+isSuccess);
		System.out.println("map 后："+map);
//		map 前：{k3=60.3, k4=55.3, k5=100, k1=50, k2=40}
//		map remove：false
//		map 后：{k3=60.3, k4=55.3, k5=100, k1=50, k2=40}
		
		//存在的key
		boolean isSuccess2 = MapUtil.remove(map, "k1");
		System.out.println("map remove："+isSuccess2);
		System.out.println("map 后："+map);
//		map 前：{k3=60.3, k4=55.3, k5=100, k1=50, k2=40}
//		map remove：true
//		map 后：{k3=60.3, k4=55.3, k5=100, k2=40}
	}
	
	@Test
	public void test() {
		String minKey = MapUtil.getMinKey(map);
		System.out.println(minKey);//k1
		
		String maxKey = MapUtil.getMaxKey(map);
		System.out.println(maxKey);//k5
		
		String minValue = (String) MapUtil.getMinValue(map);
		System.out.println(minValue);//100
		
		String maxValue = (String) MapUtil.getMaxValue(map);
		System.out.println(maxValue);//60.3
		
		double minValue2 = (double) MapUtil.getMinValue(map2);
		System.out.println(minValue2);//40.0
		
		double maxValue2 = (double) MapUtil.getMaxValue(map2);
		System.out.println(maxValue2);//100.0
	}

}
