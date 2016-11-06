package com.shupro.core.utils.jmail;

import java.util.Properties;

public class Mailinfo {
	// 发送邮件的服务器的IP(或主机地址) 
	private String mailServerHost = "smtp.sina.com";
	 // 发送邮件的服务器的端口 
	private String mailServerPort = "25";
	 // 发件人邮箱地址 
	private String fromAddress="f1019907496@sina.com";
	 // 收件人邮箱地址 
	private String toAddress;
	 // 登陆邮件发送服务器的用户名 
	private String userName="f1019907496@sina.com";
	 // 登陆邮件发送服务器的密码 
	private String password="tian1234";
	 // 是否需要身份验证 
	private boolean validate = false;
	 // 邮件主题 
	private String subject;
	 // 邮件的文本内容 
	private String content;
	 // 邮件附件的文件名 
	private String[] attachFileNames;
	  
	public Properties getProperties() {  
		  Properties p = new Properties();  
		  p.put("mail.smtp.host", this.mailServerHost);  
		  p.put("mail.smtp.port", this.mailServerPort);  
		  p.put("mail.smtp.auth", validate ? "true" : "false");  
		  return p; 
	 }

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
	  
	
}
