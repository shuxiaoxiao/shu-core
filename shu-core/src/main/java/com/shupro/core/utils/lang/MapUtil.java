package com.shupro.core.utils.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * map的常用操作，map与bean的转换见MyBeanUtil
 * @author shu
 *
 */
public class MapUtil {
	
	/**
	 * 获取map key的最小值<br>
	 * 利用Arrays.sort排序(推荐)
	 * Collections.sort(list),不能对进行排序set(通过循环将key放入list，然后排序)
	 * @param map
	 */
	public static String getMinKey(Map<String, ?> map) {
		Set<String> set = map.keySet();
		Object[] obj = set.toArray();
		Arrays.sort(obj);
		
		return obj[0].toString();
	}
	
	/**
	 * 获取map key的最大值<br>
	 * 利用Arrays.sort排序(推荐)
	 * Collections.sort(list),不能对进行排序set(通过循环将key放入list，然后排序)
	 * @param map
	 */
	public static String getMaxKey(Map<String, ?> map) {
		Set<String> set = map.keySet();
		Object[] obj = set.toArray();
		Arrays.sort(obj);
		
		return obj[obj.length-1].toString();
	}
	
	/**
	 * 获取map value的最小值<br>
	 * 利用Arrays.sort排序(推荐)
	 * Collections.sort(list),不能对进行排序set(通过循环将key放入list，然后排序)
	 * @param map
	 */
	public static Object getMinValue(Map map) {
		Collection<Object> valcoll = map.values();
		Object[] obj = valcoll.toArray();
		Arrays.sort(obj);
		
		return obj[0];
	}
	
	/**
	 * 获取map value的最大值<br>
	 * 利用Arrays.sort排序(推荐)
	 * Collections.sort(list),不能对进行排序set(通过循环将key放入list，然后排序)
	 * @param map
	 */
	public static Object getMaxValue(Map map) {
		Collection<Object> valcoll = map.values();
		Object[] obj = valcoll.toArray();
		Arrays.sort(obj);
		
		return obj[obj.length-1];
	}
	
	/**
	 * 获取map value的最大值<br>
	 * 利用Arrays.sort排序(推荐)
	 * Collections.sort(list),不能对进行排序set(通过循环将key放入list，然后排序)
	 * @param map
	 */
	public static Number getMaxValue2Number(Map map) {
		Object obj = getMaxValue(map);
		if (obj != null) {
            if (obj instanceof Number) {
                return (Number) obj;
            }
		}
		
		return null;
	}
	
	/**
	 * 通过key 删除某项
	 * @param map
	 * @param key
	 * @return
	 */
	public static boolean remove(Map<String, ?> map, String key) {
		boolean isSuccess = false;
		//map迭代删除
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String mapKey = it.next();
			if (key.equals(mapKey)) {
				it.remove();
				isSuccess = true;
			}
		}
//		//错误方式
//		Set<String> keySet = map.keySet();
//		for (String key : keySet) {
//			//java.util.ConcurrentModificationException  at java.util.HashMap$HashIterator
//			if (key.equals("k5")) {
//				map.remove(key);
//			}
//		}
		return isSuccess;
	}
	
	/**
	 * 通过value 获得key, 如果有多个key返回, 用逗号隔开。格式是key1,key2
	 * @param map
	 * @param value
	 * @return
	 */
	public static String getKey(Map<String, ?> map, String value) {
		String returnKey = "";
		//方便理解的map循环
		Set<String> set = map.keySet();
		for(String key : set) {
			String mapValue = map.get(key).toString();
			if (mapValue.equals(value)) {
				returnKey = returnKey + "," + key;
			}
		}
		
		return returnKey.substring(1);
	}
	
	//方式1：键找值
	public static void show1(Map<String, ?> map) {
		//键 对象
		Set<String> set = map.keySet();
		for(String key : set) {
			String value = map.get(key).toString();
			System.out.println(key+"--"+value);
		}
	}
	
	//方式2：键值对对象找键和值
	public static void show2(Map map) {
		//键值对 对象
		Set<Entry> set2 = map.entrySet();
		for(Entry me : set2) {
			String key = me.getKey().toString();
			String value = me.getValue().toString();
			System.out.println(key+"--"+value);
		}
	}
	
}
