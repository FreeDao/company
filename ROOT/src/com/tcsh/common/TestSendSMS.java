package com.tcsh.common;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TestSendSMS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SendSMS ss = new SendSMS();
		ss.setUsername("644308");
		ss.setPassword(CommonUtil.Md5("245599107"));
		ss.setMessage("test");
		ss.setMobiles("15696122124");
		ss.setServicesRequestAddRess("http://sms.c8686.com/Api/BayouSmsApiEx.aspx");
		ss.setSmstype(0);
		ss.setTimerid("0");
		ss.setTimertype(0);
		Map<String, String> map = ss.sendSMS();
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}

}