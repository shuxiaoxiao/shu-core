package com.shupro.core.utils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 读取proper配置文件
 * @author shu
 *
 */
public class ProperUtil {
//	public static Properties prop = null;
	private static Map<String, Properties> filePathMap = new HashMap<>();
	
	/**
	 * 创建Properties对象
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static Properties newInstall(String filePath) throws IOException {
		Properties prop = filePathMap.get(filePath);
		if(prop == null){
			prop= new Properties();
			//加入缓存map
			filePathMap.put(filePath, prop);
		}
		
		return prop;
	}
	
	/**
	 * 创建Properties对象，并加载
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static Properties newInstallAndLoad(String filePath) throws IOException {
		Properties prop = newInstall(filePath);
		InputStream input = ProperUtil.class.getClassLoader().getResourceAsStream(filePath);
		prop.load(input);
		input.close();
		
		return prop;
	}
	
	/**
	 * 读取
	 * @param filePath
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	public static String read(String filePath, String key) throws IOException{
		Properties prop = newInstallAndLoad(filePath);
		
    	return prop.getProperty(key);
    }
	
	/**
	 * 读取所有
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> read2Map(String filePath) throws IOException{
		Properties prop = newInstallAndLoad(filePath);
//		Map<String, String> map = read2Map(filePath, prop);

		return read2Map(filePath, prop);
	}
	
	/**
	 * 读取所有
	 * @param filePath
	 * @param prop
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> read2Map(String filePath, Properties prop) throws IOException{
		Map<String, String> map = new HashMap<>();
		//这种都能拿到所有
//		prop.propertyNames();
		//返回的是 Map<String, String>
		Set<String> set = prop.stringPropertyNames();
		for (String key : set) {
			String value = prop.getProperty(key);
			map.put(key, value);
		}
		//返回的是 Map<Object, Object>
//		Set<Object> set = prop.keySet();
//		for (Object key : set) {
//			Object value = prop.get(key);
//			map.put(key, value);
//		}
		
		return map;
	}
	
	/**
	 * 写入, 如果key已存在则覆盖，没有就追加在最后<br>
	 * filePath 的路径需要特别处理下,见下面的main示例
	 * @param filePath
	 * @param key
	 * @param value
	 * @throws IOException
	 */
	public static void write(String filePath, String key, String value) throws IOException{
		Properties prop = newInstall(filePath);
		InputStream input = new FileInputStream(new File(filePath));
		prop.load(input);
		input.close();
		//写入
		prop.setProperty(key, value);
		
		// 把集合中的数据重新存储到文件中
		OutputStream w = new FileOutputStream(filePath);
		prop.store(w, null);
		w.close();
	}
	
	public static void main(String[] args) {
		String filePath = "src/test/resources/resource/config.properties";
		try {
			ProperUtil.write(filePath, "username", "username33");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
