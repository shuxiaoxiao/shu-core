注意：shu-core提出去了，模板中使用到的工具类导包就定死。

===========版本：1.0.1==============
更新时间：2016-10-10
增加内容：
	解决freemarker模板生成有多个库中表名是一样时的问题：model对象里面就有多个库该表的所有字段

===========版本：1.0.0==============
更新时间：2016-9-22
增加内容：
com.shupro.core.auto
	freemarker模板的使用，实现代码生成

com.shupro.core.common
	共用类或接口（核心类或接口）

com.shupro.core.mydemo
	自己编写的例子，依赖于jdk 1.7 【MapDemo可以留心多看看】
	集合、异常、内部类、网络、反射、socket(网络编程)、多线程、
	IO流
		字节流、字符流
		内存操作流(ByteArrayInputStream、ByteArrayOutputStream;CharArrayReader、CharArrayWriter;
			StringReader、StringWriter)
		properties类 【继承Hashtable】

com.shupro.core.vo
	Result		异步返回json对象的封装
	TreeNode	tree对象的封装

com.shupro.core.utils
    db
		jdbc工具类、获取bean工具类
    excel
		excel操作工具类
    guava
		google guava API的内容(Google 公司) 强大点：集合类 和 缓存
    http
		客户端模拟发http请求
		ftp 请求上传下载
    io
		io流、prop文件操作工具类
    jmail
		邮件发送
    jms	
		消息队列
    joda
		处理日期的工具类(Apache 公司)
    json
		json工具类，推荐使用fastjson 
		【如果有性能上面的要求可以使用Gson将bean转换json确保数据的正确，使用FastJson将Json转换Bean】
    jsoup
		爬虫 【思想很简单：就是通过Java访问的链接，然后拿到html字符串，然后就是解析链接等需要的数据。】
		http://blog.csdn.net/lmj623565791/article/details/23272657

    lang
		常用工具类：小数精确计算工具类、日期工具类、随机数、字符串工具类等
    lang3
		对java.lang的扩展和封装(Apache公司)
		日期处理、IO流、随机数、String工具类、单词工具类
    md5	
		MD5加密工具类
    page
		分页操作工具类
    redis
		redis工具类
    xml	
		xml操作工具类，4种方式,推荐使用dom4j 【未封装】
	
	MyBeanUtil 	对bean对象的操作
	MyConstant	常量类
	
		
		
		
	