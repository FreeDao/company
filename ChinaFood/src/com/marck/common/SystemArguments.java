package com.marck.common;

public class SystemArguments {

	/**
	 * 商品对应类型参数
	 */
	public static Integer commodityType = 1;//默认商品类型
	public static Integer commoditySize = 2;//商品尺码
	public static Integer commodityColor = 3;//商品颜色
	
	//验证码生存周期默认10分钟
	public static Integer captchaTime = 600000;
	//万能验证码
	public static String captcha = "8888888888";
	//指定图片，文件保存路径(服务器)
	public static String serverPath = "/home/tomcat_img/";
	//指定图片，文件保存路径(本地)
	public static String localPath = "c:/home/tomcat_img/";
	//项目名称
	public static String projectName = "";
}
