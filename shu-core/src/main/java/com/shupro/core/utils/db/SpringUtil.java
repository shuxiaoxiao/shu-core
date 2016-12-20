package com.shupro.core.utils.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {

	/**
	 * 获得spring/spring.xml 目录下 service注解对应的类<br>
	 * 规律：@service则对应类名首字母小写，@service("xxx")则名称为xxx
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		String filePath = "spring/spring.xml";
		return getBean(filePath, beanName);
	}
	
	/**
	 * 获得service注解对应的类<br>
	 * 规律：@service则对应类名首字母小写，@service("xxx")则名称为xxx
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String filePath, String beanName) {
		//通过classpath载入并使用ClassPathResource
		ApplicationContext ac = new ClassPathXmlApplicationContext(filePath);
		
		return ac.getBean(beanName);
	}
}
