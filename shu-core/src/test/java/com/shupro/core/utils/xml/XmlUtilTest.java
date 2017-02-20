package com.shupro.core.utils.xml;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class XmlUtilTest {
	
	@Test
	public void test1() throws DocumentException {
		//book类加上了注解@XStreamAlias("book")，否则生成是含包名的类
		Book book = new Book("1", "book1", "12.3");
		String xmlStr = XmlUtil.toXml(book);
		System.out.println(xmlStr);
	}

	@Test
	public void test() throws DocumentException {
//		xmlStr  里面有<?xml version=\"1.0\" encoding=\"UTF-8\"?>也不影响转换
//		String xmlStr = "<book>"
//				+ "<id>1</id>"
//				+ "<title>book1</title>"
//				+ "<price>12.3</price>"
//			+ "</book>";
		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<book>"
				+ "<id>1</id>"
				+ "<title>book1</title>"
				+ "<price>12.3</price>"
			+ "</book>";
		
		Book book = XmlUtil.toBean(xmlStr, Book.class);
		System.out.println(book);
	}

}

