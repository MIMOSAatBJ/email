package com.doumob.email;

import org.apache.log4j.Logger;

import jodd.mail.Email;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpSslServer;

import com.doumob.bean.Salary;
import com.doumob.util.BeanUtil;
import com.doumob.util.Property;

/**
 * Created by Killer on 2015/1/29.
 */
public class EmailService {
	private Logger logger = Logger.getLogger(getClass());
	private static EmailService service;

	private SendMailSession mailSession;

	private EmailService() {
		SmtpSslServer server = new SmtpSslServer(Property.getValue("host"));
		server.authenticateWith(Property.getValue("auth_name"),Property.getValue("auth_pass"));
		mailSession = server.createSession();
		mailSession.open();
		logger.info("己成功创建连接------");
	}

	public static EmailService getService() {
		if (service == null) {
			service = new EmailService();
		}
		return service;
	}

	public void close() {
		mailSession.close();
		service = null;
		logger.info("连接己关闭------");
	}

	/**
	 * 发送重置密码通知
	 * 
	 * @param mailTo
	 */
	private boolean send(String mailTo, String content) {
		try {
			Email email = Email.create().from(Property.getValue("auth_name"))
					.to(mailTo).subject(MailTemplate.Salary_notice.getTitle())// 主题
					.addHtml(content);
			mailSession.sendMail(email);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	public boolean send(Salary s){
		String header=MailTemplate.Salary_notice.getMailBody();
		String body=BeanUtil.buildContent(s);
		String footer=MailTemplate.Salary_notice.getFooter();
		String content=header+body+footer;
		return send(s.getEmail(),content);
	}
	

}
