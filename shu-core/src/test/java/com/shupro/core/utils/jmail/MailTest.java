package com.shupro.core.utils.jmail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class MailTest {

	@Test
	public void test2() throws MessagingException, IOException{
		
		String username = "shu971322430@163.com";
		String password = "shu1991080400000";
		//mailServerHost 与fromAddress 对应，与是163协议，则发件人只能是163的
		String mailServerHost = "smtp.163.com";
		String fromAddress = "shu971322430@163.com";
		
		String toAddress = "971322430@qq.com";
		String ccAddress = "heng.shu@winphone.us";
		String subject = "会议20170117-1";
		String content = "shu971322430@163.com发送以下内容";
		
		String filePathPrefix = "D:/autotemp/";
		String fileName1 = "users.xls";
		String fileSource1 = filePathPrefix + fileName1;
		String fileName2 = "waybills.xlsx";
		String fileSource2 = filePathPrefix + fileName1;
		AttachBean attachBean1 = new AttachBean(fileName1, fileSource1);
		AttachBean attachBean2 = new AttachBean(fileName2, fileSource2);
		
		Mail mail = new Mail();
		mail.setUsername(username);
		mail.setPassword(password);
		mail.setMailServerHost(mailServerHost);
		mail.setFrom(fromAddress);
		mail.setSubject(subject);
		mail.setContent(content);
		mail.addToAddress(toAddress);
		mail.addCcAddress(ccAddress);
		//添加附件
		mail.addAttach(attachBean1);
		mail.addAttach(attachBean2);
		
		boolean flag = MailUtil.sendHasFile(mail);
		System.out.println(flag);
	}
	
	@Test
	public void test1() throws MessagingException, IOException{
		
		String username = "shu971322430@163.com";
		String password = "shu1991080400000";
		//mailServerHost 与fromAddress 对应，与是163协议，则发件人只能是163的
		String mailServerHost = "smtp.163.com";
		String fromAddress = "shu971322430@163.com";
		
		String toAddress = "971322430@qq.com";
		String ccAddress = "heng.shu@winphone.us";
		String subject = "会议20170117";
		String content = "shu971322430@163.com发送以下内容看";
		
		Mail mail = new Mail();
		mail.setUsername(username);
		mail.setPassword(password);
		mail.setMailServerHost(mailServerHost);
		mail.setFrom(fromAddress);
		mail.setSubject(subject);
		mail.setContent(content);
		mail.addToAddress(toAddress);
//		mail.addCcAddress(ccAddress);
		mail.addCcAddress(null);
		
		boolean flag = MailUtil.simpleSend(mail);
		System.out.println(flag);
	}
	
	
	@Test
	public void test() throws MessagingException {
		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名  
        props.setProperty("mail.host", "smtp.163.com");
//        props.setProperty("mail.host", "smtp.sina.com");//默认值
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props);
		session.setDebug(true);
		
		Message msg = new MimeMessage(session);
		msg.setSubject("标题");
		msg.setText("你好吗？");
		msg.setFrom(new InternetAddress("shu971322430@163.com"));
	
		Transport transport = session.getTransport();
		//连接邮件服务器
		transport.connect("shu971322430@163.com", "shu1991080400000");
//		transport.connect("smtp.sina.com", 25, "shu971322430@163.com", "shu1991080400000");
		transport.sendMessage(msg,
				new Address[]{new InternetAddress("971322430@qq.com")});

		//transport.send(msg,new Address[]{new InternetAddress("itcast_test@sohu.com")});
		transport.close();
	}

}
