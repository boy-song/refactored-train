package com.icss.yierjia.test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.icss.yierjia.util.MailUtil;

public class EmailTest {
	
	public static void main(String[] args) {
		String emailMsg = "��<a href='#'>��˼���</a>�����ļ�����Ϊ��";
		try {
			MailUtil.sendMail("20787182@qq.com", emailMsg);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
