package com.era.util;



import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

public class MainApnsSend {
    public static void main(String[] args) throws Exception {
       
        try {
            String deviceToken = "b39c686253cc2ecaa035e8575999507f5244308533dd341106acec249a379b8d";

            
            PayLoad payLoad = new PayLoad();
            payLoad.addAlert("彩通万岁！OLALA!!AAAA");
            payLoad.addBadge(1);
            payLoad.addSound("default");
            
            PushNotificationManager pushManager = PushNotificationManager.getInstance();
            pushManager.addDevice("iPhone", deviceToken);
            
            String host= "gateway.push.apple.com";  //测试用的苹果推送服务器
            int port = 2195;
            String certificatePath = "C://Users//Administrator//Workspaces//MyEclipse 10_test//.metadata//.me_tcat//webapps//City//js//MyApnsCert_Pro.p12"; //刚才在mac系统下导出的证书
              
            String certificatePassword= "62504517";
            
            pushManager.initializeConnection(host, port, certificatePath,certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
              
            //Send Push
            Device client = pushManager.getDevice("iPhone");
            pushManager.sendNotification(client, payLoad); 
            pushManager.stopConnection();
            pushManager.removeDevice("iPhone");
            System.out.println("push succeed!");
        }
        catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
             
    }
}

