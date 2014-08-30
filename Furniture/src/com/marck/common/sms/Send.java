package com.marck.common.sms;

import com.marck.common.CommonUtil;

public class Send {

	private static String userName = "644308";
	private static String passWord = CommonUtil.Md5("245599107");
	
	public static void send(String phone,String content){
		SendSMS ss = new SendSMS();
		ss.setUsername(userName);
		ss.setPassword(passWord);
		ss.setMessage(content);
		ss.setMobiles(phone);
		ss.setServicesRequestAddRess("http://sms.c8686.com/Api/BayouSmsApiEx.aspx");
		ss.setSmstype(0);
		ss.setTimerid("0");
		ss.setTimertype(0);
		ss.sendSMS();
		/*Map<String, String> map = ss.sendSMS();
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}*/
	}

}