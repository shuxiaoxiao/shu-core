package com.shupro.core.utils.jmail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Mail {

	private String mailServerHost;//发送邮件的服务器
	private String username;//用户名
	private String password;//密码
	
	private String from;
	private StringBuilder toAddress;
	private StringBuilder ccAddress;
	private StringBuilder bccAddress;
	private String subject;
	private String content;
	private List attachList;

	public Properties getProperties() {  
		  Properties p = new Properties();  
		  p.put("mail.smtp.host", this.mailServerHost);  
//		  p.put("mail.smtp.port", this.mailServerPort);  
		  p.put("mail.smtp.auth", "true");  
		  return p; 
	 }
	
//	public Mail() {
//		toAddress = new StringBuilder();
//		ccAddress = new StringBuilder();
//		bccAddress = new StringBuilder();
//		attachList = new ArrayList();
//	}
//
//	public Mail(String from, String to) {
//		this(from, to, null, null);
//	}
//
//	public Mail(String from, String to, String subject, String content) {
//		toAddress = new StringBuilder();
//		ccAddress = new StringBuilder();
//		bccAddress = new StringBuilder();
//		attachList = new ArrayList();
//		this.from = from;
//		toAddress.append(to);
//		this.subject = subject;
//		this.content = content;
//	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
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

	public String getToAddress() {
		return toAddress.toString();
	}

	public String getCcAddress() {
		return ccAddress.toString();
	}

	public String getBccAddress() {
		return bccAddress.toString();
	}

	public void addToAddress(String to) {
		if (toAddress.length() > 0)
			toAddress.append(",");
		toAddress.append(to);
	}

	public void addCcAddress(String cc) {
		if (ccAddress.length() > 0)
			ccAddress.append(",");
		ccAddress.append(cc);
	}

	public void addBccAddress(String bcc) {
		if (bccAddress.length() > 0)
			bccAddress.append(",");
		bccAddress.append(bcc);
	}

	public void addAttach(AttachBean attachBean) {
		attachList.add(attachBean);
	}

	public List getAttachs() {
		return attachList;
	}
}
