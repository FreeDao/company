package com.era.util;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

public class Push {

	/**
	 * 发送给单个对象
	 * @throws Exception 
	 */
	public static void pushUser(String path,String password,String deviceToken,String content) throws Exception{
	     //被推送的iphone应用程序标示符      
	     PayLoad payLoad = new PayLoad();
	     payLoad.addAlert(content);
	     payLoad.addBadge(1);
	     payLoad.addSound("default");
	              
	     PushNotificationManager pushManager = PushNotificationManager.getInstance();
	     pushManager.addDevice("iphone", deviceToken);
	    
	  //Device c = pushManager.getDevice("iphone");
	     String host= "gateway.push.apple.com"; 
	     int port = 2195;
	    
	     path += "/xqzs.p12";
	     
	     pushManager.initializeConnection(host, port, path,password, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);//初始化tcp连接，公司网络代理上网，不能连上外网的tcp连接，坑死人
	               
	     //Send Push
	     Device client = pushManager.getDevice("iphone");
	     pushManager.sendNotification(client, payLoad); //推送消息
	     pushManager.stopConnection();
	     pushManager.removeDevice("iphone");
	}
	
	/**
	 * 发送给单个对象
	 */
	public static void pushAll(){
		
	}
	
}
