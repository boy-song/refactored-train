package com.icss.yierjia.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderId {
	
	public static String getOrderId(int uid){
		//�����λ��
		int code = (int) ((Math.random()*9+1)*100000);
		//��ȡ������ʱ����
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		String time=df.format(date);
		//ƴ�����ɶ�����
		String Ordercode = time + Integer.toString(code) + Integer.toString(uid);
		return Ordercode;
	}
	
	
	
}
