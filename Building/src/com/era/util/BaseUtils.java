package com.era.util;

import java.io.IOException;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mysql.jdbc.Connection;

public class BaseUtils {


	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	public static final String SERVER_IP_STR = "http://" + "www.tcshenghuo.org:8806/JunKeting/uploadImgs/";
	public static final String SERVER_IP_STR = "E://Company//.metadata//.me_tcat//webapps//Mineralsa//uploadImgs";
	public static final Integer DEFAULT_PAGENUM = 6;
	private static final double EARTH_RADIUS = 6378137.0;// 地球半径

	/**
	 * 去掉IP字符串前后所有的空格
	 * 
	 * @param IP
	 * @return
	 */
	public static String trimSpaces(String IP) {
		while (IP.startsWith(" ")) {
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" ")) {
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}

	/**
	 * 判断是否是一个IP
	 * 
	 * @param IP
	 * @return
	 */
	public static boolean isIp(String IP) {
		boolean b = false;
		IP = trimSpaces(IP);
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = IP.split("\\.");
			if (Integer.parseInt(s[0]) < 255)
				if (Integer.parseInt(s[1]) < 255)
					if (Integer.parseInt(s[2]) < 255)
						if (Integer.parseInt(s[3]) < 255)
							b = true;
		}
		return b;
	}

	/**
	 * 计算距离
	 * 
	 * @param d
	 * @return
	 */
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 
	 * @param longitude1
	 *            商家经度
	 * @param latitude1
	 *            商家纬度
	 * @param longitude2
	 *            对应当前经度
	 * @param latitude2
	 *            对应当前纬度
	 * @return
	 */
	public synchronized static double getDistance(double longitude1,
			double latitude1, double longitude2, double latitude2) {
		double Lat1 = rad(latitude1);
		double Lat2 = rad(latitude2);
		double a = Lat1 - Lat2;
		double b = rad(longitude1) - rad(longitude2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(Lat1) * Math.cos(Lat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;

		System.out.println("s "+s);
		return s;
	}

	/**
	 * 
	 * @param dis
	 * @return
	 */
	public static String getDistanceStr(Double dis) {

		String dis_str = dis.toString();
		int index = dis_str.indexOf(".");
		dis_str = index > 0 ? dis_str.substring(0, index) : dis_str;
		if (Integer.parseInt(dis_str) > 1000) {
			DecimalFormat dt = (DecimalFormat) DecimalFormat.getInstance(); // 获得格式化类对象
			dt.applyPattern("0.00");// 设置小数点位数(两位) 余下的会四舍五入
			dis_str = dt.format(Double.valueOf(dis_str) / 1000) + "km";
		} else {
			dis_str = dis_str + "m";
		}
		return dis_str;
	}

	/**
	 * 去掉字符串中间的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String del_space(String str) {
		if (str == null) {
			return null;
		}
		char[] str_old = str.toCharArray();
		StringBuffer str_new = new StringBuffer();

		int i = 0;
		for (char a : str_old) {
			if (a != ' ') {
				str_new.append(a);
				i++;
			}
		}
		return str_new.toString();
	}

	/**
	 * 判断是否是汉字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChinaese(String str) {
		boolean flag = false;
		byte[] temp = null;
		try {
			temp = str.getBytes("ISO-8859-1"); 
		} catch (Exception e) {
			flag = false;
		}
		int i = 0;
		for (i = 0; i < temp.length; i++) {
			if (temp[i] < 0) {
				flag = true;
				i = temp.length;
			}
		}
		return flag;
	}
	
	// 根据Unicode编码完美的判断中文汉字和符号
	private static boolean isChinese1(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
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
	 * @param 传递String
	 * @return
	 */
	public static String getNowStringDateTime(String date) {
		
		String newTime = "";
		try {
			if (!"".equals(date) || date != null) {
				newTime = format.format(format.parse(date));
			} else {
				newTime = format.format(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	 * 获取JDBC的Connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {
//		// 连接数据库URL地址
		String url = "jdbc:mysql://localhost:3306/building?useUnicode=true&amp;characterEncoding=UTF-8";
		// 连接数据库的用户名
		String user = "root";
		// 连接数据库的密码
//		 String password = "root";
		String password = "pengyou.com";
		// 连接数据库URL地址
//		String url = "jdbc:mysql://localhost:3306/Mineralsa?useUnicode=true&amp;characterEncoding=gb2312";
//		// 连接数据库的用户名
//		String user = "root";
//		// 连接数据库的密码
//		 String password = "root";
//		String password = "pengyou.com";

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 根据驱动治理类，得到数据库的连接对象
			conn = (Connection) DriverManager
					.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
