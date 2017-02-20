package com.shupro.core.utils.jmail;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.shupro.core.utils.SystemUtil;

/**
 * java 邮件工具类
 * javax.mail.Session：上下文环境信息，如服务器的主机名、端口号、协议名称等  
 * javax.mail.Message：邮件模型，发送邮件和接收邮件的媒介，封装了邮件的信息，如发件人、收件人、邮件标题、邮件内容等  
 * javax.mail.Transport：连接邮件SMTP服务器，发送邮件  
 * javax.mail.Store：连接邮件POP3、IMAP服务器，收取邮件  
 *
 * @author shu
 */
public class MailUtil {

	/**
	 * 简单内容 发送(支持多个收件人、抄送人、暗送人)
	 * 
	 * @param mail
	 */
	public static boolean simpleSend(Mail mail) {
		boolean flag = false;
		try {
			//创建消息
			MimeMessage msg = initMsg(mail);
			msg.setContent(mail.getContent(), "text/html;charset=utf-8");
			
			//发送
			Transport.send(msg);
			flag = true;
			
		} catch (MessagingException e) {
	        e.printStackTrace();  
		}
		
		return flag;
	}
	
	/**
	 * 含附件的发送
	 * 
	 * @param mail
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static boolean sendHasFile(Mail mail) throws IOException {
		boolean flag = false;
		try {
			//创建消息
			MimeMessage msg = initMsg(mail);
			
			MimeMultipart parts = new MimeMultipart();
			MimeBodyPart bodypart = new MimeBodyPart();
			bodypart.setContent(mail.getContent(), "text/html;charset=utf-8");
			parts.addBodyPart(bodypart);
			
			List<AttachBean> attachBeanList = mail.getAttachs();
			if (attachBeanList != null) {
				MimeBodyPart attachPart;
				for (AttachBean attachBean : attachBeanList) {
					attachPart = new MimeBodyPart();
					attachPart.setDataHandler(new DataHandler(new FileDataSource(attachBean.getFileSource())));  
					//MimeUtility.encodeText 是对fileName 进行加密
					attachPart.setFileName(MimeUtility.encodeText(attachBean.getFileName()));
//					//第二种方式
//					attachPart.attachFile(attachBean.getFile());
//					String cid = attachBean.getCid();
//					if (cid != null){
//						attachPart.setContentID(cid);
//					}
					parts.addBodyPart(attachPart);
				}
			}
			msg.setContent(parts);
			
			//发送
			Transport.send(msg);
			flag = true;
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * 创建邮件连接对象
	 * @param host
	 * @param username
	 * @param password
	 * @return
	 */
	private static Session createSession(final Mail mail) {
		
		Authenticator auth = new Authenticator() {
			
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail.getUsername(), mail.getPassword());
			}
			
		};
		return Session.getInstance(mail.getProperties(), auth);
	}

	/**
	 * 创建消息
	 * @param session
	 * @param mail
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 */
	private static MimeMessage initMsg(Mail mail) throws MessagingException {
		Session session = createSession(mail);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(mail.getFrom()));
		//添加收件人
		msg.addRecipients(Message.RecipientType.TO,  new InternetAddress().parse(mail.getToAddress()));//多个
//		msg.addRecipients(Message.RecipientType.TO, mail.getToAddress());//单个
		String cc = mail.getCcAddress();
		if (SystemUtil.isNotEmpty(cc)){
			//添加抄送人
			msg.addRecipients(Message.RecipientType.CC, new InternetAddress().parse(cc));
//			msg.addRecipients(Message.RecipientType.CC, cc);
		}
		String bcc = mail.getBccAddress();
		if (SystemUtil.isNotEmpty(bcc)){
			//添加 密送人（暗抄送）
			msg.addRecipients(Message.RecipientType.BCC, new InternetAddress().parse(bcc));
//			msg.addRecipients(Message.RecipientType.BCC, bcc);
		}
		msg.setSubject(mail.getSubject());
		msg.setSentDate(new Date());
		return msg;
	}

}
