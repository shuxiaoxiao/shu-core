package com.shupro.core.utils.xml;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



/**
 * 基于dom4j的xml工具类
 * 
 * @author	shu
 */
public class DomUtil {
	
	/**
	 * document 转 xmlStr
	 * @param document
	 */
	public static String document2XmlStr(Document document) {
		
		return document.asXML();
	}
	
	/**
	 * xmlStr 转 document
	 * @param xmlStr
	 * @throws DocumentException
	 */
	public static Document xmlStr2Document(String xmlStr) throws DocumentException {
		
		return DocumentHelper.parseText(xmlStr);
	}
	
	/**
	 * 通过文件路径获得document
	 * @param filePath	文件路径
	 * @throws DocumentException
	 */
	public static Document getDocument(String filePath) throws DocumentException {
		//获得解析流
		SAXReader reader = new SAXReader();
		//xml文件的解析
		Document document = reader.read(filePath);
		
		return document;
	}

	/**
	 * 获得根节点
	 * @param document
	 */
	public static Element getRoot(Document document){
		//获得根元素
		Element root = document.getRootElement();
		
		return root;
	}
	
	/**
	 * 获得所有子节点
	 * @param document
	 */
	public static List<Element> getChild(Document document){
		//获得根元素
		Element root = document.getRootElement();
		//获得子节点
		List<Element> childList = root.elements();
		
		return childList;
	}
	
	/**
	 * 获得指定子节点
	 * @param document
	 */
	public static List<Element> getChild(Document document, String childName){
		//获得根元素
		Element root = document.getRootElement();
		//获得子节点
		List<Element> childList = root.elements(childName);
		
		return childList;
	}
	
	/**
	 * 读取xml文件
	 * @param filePath
	 * @throws DocumentException
	 */
	public static List<Element> readByfilePath(String filePath) throws DocumentException{
		Document document = getDocument(filePath);
		
		return getChild(document);
	}
	
	/**
	 * 读取xml文件
	 * @param filePath
	 * @throws DocumentException
	 */
	public static List<Element> readByfilePath(String filePath, String childName) throws DocumentException{
		Document document = getDocument(filePath);
		
		return getChild(document, childName);
	}
	
	/**
	 * 读取xmlStr
	 * @param xmlStr
	 * @throws DocumentException
	 */
	public static List<Element> readByXmlStr(String xmlStr) throws DocumentException{
		Document document = xmlStr2Document(xmlStr);
		
		return getChild(document);
	}
	
	/**
	 * 读取xmlStr
	 * @param xmlStr
	 * @throws DocumentException
	 */
	public static List<Element> readByXmlStr(String xmlStr, String childName) throws DocumentException{
		Document document = xmlStr2Document(xmlStr);
		
		return getChild(document, childName);
	}

}
