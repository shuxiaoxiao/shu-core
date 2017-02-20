package com.shupro.core.utils.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * 基于xstream 解析xml的工具类
 * @author: shu
 */
public class XmlUtil{
	
    /**
     * java 转换成xml
     * 
     * @param obj 对象实例
     */
    public static String toXml(Object obj){
        XStream xstream=new XStream();
//        XStream xstream=new XStream(new DomDriver()); //直接用jaxp dom来解释
//        XStream xstream=new XStream(new DomDriver("utf-8")); //指定编码解析器,直接用jaxp dom来解释
        
        ////如果没有这句，xml中的根元素会是<包.类名>；或者说：注解根本就没生效，所以的元素名就是类的属性
        xstream.processAnnotations(obj.getClass()); //通过注解方式的，一定要有这句话
        return xstream.toXML(obj);
    }
    
    /**
     *  将传入xml文本转换成Java对象
     * @param xmlStr
     * @param cls  xml对应的class类
     * 
     * 调用的方法实例：PersonBean person=XmlUtil.toBean(xmlStr, PersonBean.class);
     */
    public static <T> T  toBean(String xmlStr,Class<T> cls){
        //注意：不是new Xstream(); 否则报错：java.lang.NoClassDefFoundError: org/xmlpull/v1/XmlPullParserFactory
        XStream xstream=new XStream(new DomDriver());
        xstream.processAnnotations(cls);
        T obj=(T)xstream.fromXML(xmlStr);
        return obj;            
    } 

    /**
     * 写到xml文件中去
     * @param obj 对象
     * @param absPath 绝对路径
     * @param fileName    文件名
     */
    public static boolean toXMLFile(Object obj, String absPath, String fileName ){
        String strXml = toXml(obj);
        String filePath = absPath + fileName;
        File file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
//                log.error("创建{"+ filePath +"}文件失败!!!" + Strings.getStackTrace(e));
                return false ;
            }
        }// end if 
        OutputStream ous = null ;
        try {
            ous = new FileOutputStream(file);
            ous.write(strXml.getBytes());
            ous.flush();
        } catch (Exception e1) {
//            log.error("写{"+ filePath +"}文件失败!!!" + Strings.getStackTrace(e1));
            return false;
        }finally{
            if(ous != null )
                try {
                    ous.close();
                } catch (IOException e) {
//                    log.error("写{"+ filePath +"}文件关闭输出流异常!!!" + Strings.getStackTrace(e));
                }
        }
        return true ;
    }
    
    /**
     * 从xml文件读取报文
     * @param absPath 绝对路径
     * @param fileName 文件名
     * @param cls
     * @throws Exception 
     */
    public static <T> T  toBeanFromFile(String absPath, String fileName, Class<T> cls) throws Exception{
        String filePath = absPath +fileName;
        InputStream ins = null ;
        try {
            ins = new FileInputStream(new File(filePath ));
        } catch (Exception e) {
            throw new Exception("读{"+ filePath +"}文件失败！", e);
        }
        
//        String encode = useEncode(cls);
        String encode = "utf-8";
        XStream xstream=new XStream(new DomDriver(encode));
        xstream.processAnnotations(cls);
        T obj =null;
        try {
            obj = (T)xstream.fromXML(ins);
        } catch (Exception e) {
            throw new Exception("解析{"+ filePath +"}文件失败！",e);
        }
        if(ins != null)
            ins.close();
        return obj;            
    }

//	private static String useEncode(Class<T> cls) {
//		
//		return null;
//	} 
    
}
