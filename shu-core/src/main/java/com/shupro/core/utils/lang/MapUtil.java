package com.shupro.core.utils.lang;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * map的常用操作，map与bean的转换见MyBeanUtil
 * @author shu
 *
 */
public class MapUtil {
	
	/**
	 * map对象为空 或 map里面没有数据 就返回true
	 * @param map
	 * @return
	 */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }
    
	/**
	 * 获取map key的最小值<br>
	 * 利用Arrays.sort排序(推荐)
	 * Collections.sort(list),不能对进行排序set(通过循环将key放入list，然后排序)
	 */
	public static String getMinKey(Map map) {
		Set<String> set = map.keySet();
		Object[] obj = set.toArray();
		Arrays.sort(obj);
		
		return obj[0].toString();
	}
	
	/**
	 * 获取map key的最大值<br>
	 * 利用Arrays.sort排序(推荐)
	 * Collections.sort(list),不能对进行排序set(通过循环将key放入list，然后排序)
	 */
	public static String getMaxKey(Map map) {
		Set<String> set = map.keySet();
		Object[] obj = set.toArray();
		Arrays.sort(obj);
		
		return obj[obj.length-1].toString();
	}
	
	/**
	 * 获取map value的最小值<br>
	 * 利用Arrays.sort排序(推荐)
	 * Collections.sort(list),不能对进行排序set(通过循环将key放入list，然后排序)
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
	 */
	public static void remove(Map map, String key) {
		System.out.println(map);
		//map迭代删除
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String mapKey = it.next();
			if (key.equals(mapKey)) {
				it.remove();
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
		System.out.println(map);
	}
	
	/**
	 * 通过value 获得key
	 */
	public static String getKey(Map map, String value) {
		Set<String> set = map.keySet();
		String returnKey = "";
		for(String key : set) {
			String mapValue = map.get(key).toString();
			if (mapValue.equals(value)) {
				returnKey = key;
			}
		}
		
		return returnKey;
	}
	
	//方式1：键找值
	public static void show1(Map map) {
		//键 对象
		Set<String> set = map.keySet();
		for(String key : set) {
			String value = map.get(key).toString();
			System.out.println(key+"---"+value);
		}
	}
	
	//方式2：键值对对象找键和值
	public static void show2(Map map) {
		//键值对 对象
		Set<Map.Entry<String,Object>> set2 = map.entrySet();
		for(Map.Entry<String,Object> me : set2) {
			String key = me.getKey();
			String value = me.getValue().toString();
			System.out.println(key+"---"+value);
		}
	}
	
}
