package com.doumob.email;

import java.util.Date;

import com.doumob.util.DateUtil;

/**
 * Created by Killer on 2015/1/29.
 */
public enum MailTemplate {

	/**
     * 
     */
	Salary_notice() {
		protected String title = DateUtil.getLastMonth(new Date()) + "月工资明细";
		protected String mailBody = "<!DOCTYPE html><html><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"
				+"<style type='text/css'>"
				+".bg>td{BACKGROUND-COLOR: #DDDDDD;}"
				+ "td{text-align:center;border:solid #add9c0; border-width:0px 1px 1px 0px;padding:5px 0px;font-family:cursive;min-width: 50px;width:100px;height:28px;}"
				+ "table{border:solid #add9c0; border-width:1px 0px 0px 1px;}.p1{margin-left:40px;font-family:cursive;color:#000000; }.p2{font-family:cursive;color: #FF0000;}"
				+ "</style><body><p class='p1'>您好，以下为"+ title
				+ "， 如有问题可找我当面沟通 ，祝您生活愉快!</p>";
		protected String footer = "<p class='p2'>注意:该邮件是系统自动发送，若您发现有任何问题，请及时联系公司行政人员，切勿以任何形式进行讨论，传播。"
				+ "若系统出现其它问题，请联系作者。</p></body></html>";

		public String getTitle() {
			return this.title;
		}

		public String getMailBody() {
			return this.mailBody;
		}

		public String getFooter() {
			return this.footer;
		}
	};

	public String getTitle() {
		return "";
	}

	public String getMailBody() {
		return "";
	}

	public String getFooter() {
		return "";
	}
}
