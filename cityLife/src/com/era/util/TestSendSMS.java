package com.era.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

public class TestSendSMS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calendar calendar = Calendar.getInstance();
		String now = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		if(now.compareTo("2014-05-20") < 0 || now.compareTo("2014-06-25") > 0  ){
			System.out.println(now.compareTo("2014-05-20"));
		}
		/*SendSMS ss = new SendSMS();
		ss.setUsername("644308");
		ss.setPassword(PiaoJuTong.Md5("245599107"));
		ss.setMessage("你注册的验证码为4587【同城生活圈】");
		ss.setMobiles("15696120885");
		ss.setServicesRequestAddRess("http://sms.c8686.com/Api/BayouSmsApiEx.aspx");
		ss.setSmstype(0);
		ss.setTimerid("0");
		ss.setTimertype(0);
		Map<String, String> map = ss.sendSMS();
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}*/
		
	}

}