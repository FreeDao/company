package com.era.util;

import java.io.IOException;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mysql.jdbc.Connection;

public class BaseUtils {
	
	public static final String SERVERSIPADDRESS = "localhost";
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String SERVER_IP_STR = "http://api.pymob.cn:8091/City/uploadImgs/";
	public static final Integer DEFAULT_PAGENUM = 6;
	
	/**
	 * 判断是否是汉字
	 * @param str
	 * @return
	 */
	public static boolean isChinaese(String str){
		boolean flag = false; 
		byte[] temp = null;
		try {
			temp = str.getBytes("ISO-8859-1");
		} catch (Exception e) {
			flag = false;
		}		
		int i = 0;
		for (i = 0; i < temp.length; i++){
			if (temp[i] < 0) {
				flag = true;
				i = temp.length;
			}
		}
		return flag;
	}
	/**
	 * 根据执行结果，返回数据信息
	 * 
	 * @param result
	 */
	public static void responseInfo(String result) {
		try {
			System.out.println("responseInfo " + result);

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @param 传递进来的参数
	 * @return
	 */
	public static String getNowStringDateTime(String date) {

		String newTime = "";
		if (!"".equals(date) || date != null) {
			newTime = format.format(date);
		} else {
			newTime = format.format(new Date());
		}
		return newTime;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @param 传递Date对象
	 * @return
	 */
	public static String getNowStringDateTime(Date date) {

		String newTime = "";

		if (date != null) {
			newTime = format.format(date);
		} else {
			newTime = format.format(new Date());
		}
		System.out.println("getNowStringDateTime " + newTime);

		return newTime;
	}
	
	/**
	 * 获取当前时间，返回字符串类型
	 * @return
	 */
	public static String getNowString(String format) {
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String time = sdf.format(now);
			return time;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * 获取当前时间，返回Date类型
	 * @return
	 */
	public static Date getNowDate(String format){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String temp = getNowString(format);
			return sdf.parse(temp);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * 获取JDBC的Connection
	 * @return
	 */
	public static Connection getConnection(){

		// 连接数据库URL地址
		String url = "jdbc:mysql://"+SERVERSIPADDRESS+":3306/city";
		// 连接数据库的用户名
		String user = "root";
		// 连接数据库的密码
		String password = "pengyou.com";
//		String password = "pengyou.com";
		
		Connection conn = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 根据驱动治理类，得到数据库的连接对象
			conn = (Connection) DriverManager.getConnection(url, user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	
	
}
