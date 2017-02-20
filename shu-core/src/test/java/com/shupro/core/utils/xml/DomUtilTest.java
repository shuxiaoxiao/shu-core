package com.shupro.core.utils.xml;

import static org.junit.Assert.*;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Test;

public class DomUtilTest {

	@Test
	public void document2XmlStr_test() throws DocumentException {
		String filePath = "D:/autotemp/books.xml";
		Document document = DomUtil.getDocument(filePath);
		String xmlStr = DomUtil.document2XmlStr(document);
		System.out.println(xmlStr);
	}

	@Test
	public void readByfilePath_test() throws DocumentException {
		String filePath = "D:/autotemp/books.xml";
		Document document = DomUtil.getDocument(filePath);
		List<Element> list = DomUtil.getChild(document, "book");
		System.out.println("list-size:"+list.size());
		System.out.println(list);
//		Book book = null;
//		for (Element element : list) {
//			book = new Book();
//			String attrId = element.attributeValue("id");
//			String name = element.getName();
//			String text = element.getText();
//			System.out.println(attrId + "," + name + "," + text);
//		}
	}
	
	@Test
	public void test() throws DocumentException {
		String filePath = "D:/autotemp/books.xml";
		List<Element> list = DomUtil.readByfilePath(filePath);
		System.out.println("list-size:"+list.size());
		System.out.println(list);
		Book book = null;
		for (Element element : list) {
			book = new Book();
			String attrId = element.attributeValue("id");
			String name = element.getName();
			String text = element.getText();
			System.out.println(attrId + "," + name + "," + text);
		}
	}


}
