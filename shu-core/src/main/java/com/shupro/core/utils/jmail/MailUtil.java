package com.shupro.core.utils.jmail;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailUtil {

	/**
	 * 创建邮件连接对象
	 * @param host
	 * @param username
	 * @param password
	 * @return
	 */
	public static Session createSession(final Mail mail) {
		
		Authenticator auth = new Authenticator() {

			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail.getUsername(), mail.getPassword());
			}

		};
		return Session.getInstance(mail.getProperties(), auth);
	}

	/**
	 * 简单内容 发送
	 * @param session
	 * @param mail
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void simpleSend(Session session, Mail mail) throws MessagingException, IOException {
		//创建消息
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(mail.getFrom()));
		msg.addRecipients(Message.RecipientType.TO, mail.getToAddress());
		String cc = mail.getCcAddress();
		if (!cc.isEmpty())
			msg.addRecipients(Message.RecipientType.CC, cc);
		String bcc = mail.getBccAddress();
		if (!bcc.isEmpty())
			msg.addRecipients(Message.RecipientType.BCC, bcc);
		msg.setSubject(mail.getSubject());
		msg.setSentDate(new Date());
		msg.setContent(mail.getContent(), "text/html;charset=utf-8");
		
		//发送
		Transport.send(msg);
	}
	/**
	 * 
	 * @param session
	 * @param mail
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void send(Session session, Mail mail) throws MessagingException, IOException {
		//创建消息
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(mail.getFrom()));
		msg.addRecipients(Message.RecipientType.TO, mail.getToAddress());
		String cc = mail.getCcAddress();
		if (!cc.isEmpty())
			msg.addRecipients(Message.RecipientType.CC, cc);
		String bcc = mail.getBccAddress();
		if (!bcc.isEmpty())
			msg.addRecipients(Message.RecipientType.BCC, bcc);
		msg.setSubject(mail.getSubject());
		msg.setSentDate(new Date());
		
		MimeMultipart parts = new MimeMultipart();
		MimeBodyPart bodypart = new MimeBodyPart();
		bodypart.setContent(mail.getContent(), "text/html;charset=utf-8");
		parts.addBodyPart(bodypart);
		List attachBeanList = mail.getAttachs();
		if (attachBeanList != null) {
			MimeBodyPart attachPart;
			for (Iterator iterator = attachBeanList.iterator(); iterator.hasNext(); parts.addBodyPart(attachPart)) {
				AttachBean attach = (AttachBean) iterator.next();
				attachPart = new MimeBodyPart();
				attachPart.attachFile(attach.getFile());
				attachPart.setFileName(MimeUtility.encodeText(attach.getFileName()));
				String cid = attach.getCid();
				if (cid != null)
					attachPart.setContentID(cid);
			}
			
		}
		msg.setContent(parts);
		
		//发送
		Transport.send(msg);
	}

}
