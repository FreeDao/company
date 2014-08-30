package com.era.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mysql.jdbc.Connection;

public class BaseUtils {

	public static final String SERVERSIPADDRESS = "171.34.32.126";
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String SERVER_IP_STR = "http://api.pymob.cn:8091/cityLife/uploadImgs/";
	public static final Integer DEFAULT_PAGENUM = 6;
	private static final double EARTH_RADIUS = 6378137.0;// 地球半径
	public static final Integer surplus = 2;//默认抽奖次数

	/**
	    * 对文件全文生成MD5摘要
	    *
	    * @param file
	    *            要加密的文件
	    * @return MD5摘要码
	    */

	   static char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',

	        '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	/**
	 * MD5加密算法
	 * @param plainText 要加密的字符�?
	 * @return 加密后的字符�?
	 */
	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return plainText;
	}
	
	/**
	 * md5文件算法
	 * @param file
	 * @return
	 */
	public static String Md5(File file) {

		 

	      FileInputStream fis = null;

	      try {

	        MessageDigest md = MessageDigest.getInstance("MD5");

	        fis = new FileInputStream(file);

	        byte[] buffer = new byte[2048];

	        int length = -1;

	        long s = System.currentTimeMillis();

	        while ((length = fis.read(buffer)) != -1) {

	           md.update(buffer, 0, length);

	        }

	        byte[] b = md.digest();

	        return byteToHexString(b);

	        // 16位加密

	        // return buf.toString().substring(8, 24);

	      } catch (Exception ex) {

	        ex.printStackTrace();

	        return null;

	      } finally {

	        try {

	           fis.close();

	        } catch (IOException ex) {

	           ex.printStackTrace();

	        }

	      }

	   }
	
	
	/**
	 * md5文件算法
	 * @param file
	 * @return
	 */
	public static String Md5(InputStream inputStream) {


	      try {

	        MessageDigest md = MessageDigest.getInstance("MD5");

	        byte[] buffer = new byte[2048];

	        int length = -1;

	        long s = System.currentTimeMillis();

	        while ((length = inputStream.read(buffer)) != -1) {

	           md.update(buffer, 0, length);

	        }

	        byte[] b = md.digest();

	        return byteToHexString(b);

	        // 16位加密

	        // return buf.toString().substring(8, 24);

	      } catch (Exception ex) {

	        ex.printStackTrace();

	        return null;

	      } finally {

	        try {

	        	inputStream.close();

	        } catch (IOException ex) {

	           ex.printStackTrace();

	        }

	      }

	   }
	
	/** */

	   /**

	    * 把byte[]数组转换成十六进制字符串表示形式

	    * @param tmp    要转换的byte[]

	    * @return 十六进制字符串表示形式

	    */

	   private static String byteToHexString(byte[] tmp) {

	      String s;

	      // 用字节表示就是 16 个字节

	      char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，

	      // 所以表示成 16 进制需要 32 个字符

	      int k = 0; // 表示转换结果中对应的字符位置

	      for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节

	        // 转换成 16 进制字符的转换

	        byte byte0 = tmp[i]; // 取第 i 个字节

	        str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,

	        // >>> 为逻辑右移，将符号位一起右移

	        str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换

	      }

	      s = new String(str); // 换后的结果转换为字符串

	      return s;

	   }
	
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

	// 完整的判断中文汉字和符号
	public static boolean isChinaese1(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese1(c)) {
				return true;
			}
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
	 * 获取系统当前时间
	 * 
	 * @param 传递Date对象
	 * @return
	 */
	public static String getNowStringDateTimeRQ(Date date) {

		String newTime = "";

		if (date != null) {
			java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
			newTime = format1.format(date);
		} else {
			java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
			newTime = format1.format(new Date());
		}
		
		return newTime;
	}

	/**
	 * 获取JDBC的Connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {

		// 连接数据库URL地址
		String url = "jdbc:mysql://219.232.246.182:3306/cellcode?useUnicode=true&characterEncoding=UTF-8";
		// 连接数据库的用户名
		String user = "pyxx";
		// 连接数据库的密码
		// String password = "123456";
		String password = "pyxx.com";

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
	
	public static String RandomStringTo()
	 {
		 int[] array = {0,1,2,3,4,5,6,7,8,9};
		 Random rand = new Random();
		 for (int i = 10; i > 1; i--) {
		     int index = rand.nextInt(i);
		     int tmp = array[index];
		     array[index] = array[i - 1];
		     array[i - 1] = tmp;
		 }
		 int result = 0;
		 for(int i = 0; i < 8; i++)
		 {
		     result = result * 10 + array[i];
		 }
	  return String.valueOf(result);
	 }
	 
	 public static String RandomString()
	 {
		 int[] array = {0,1,2,3,4,5,6,7,8,9};
		 Random rand = new Random();
		 for (int i = 10; i > 1; i--) {
		     int index = rand.nextInt(i);
		     int tmp = array[index];
		     array[index] = array[i - 1];
		     array[i - 1] = tmp;
		 }
		 int result = 0;
		 for(int i = 0; i < 6; i++)
		 {
		     result = result * 10 + array[i];
		 }
	  return String.valueOf(result);
	 }
	 
	 public static String geocodeAddr(String latitude, String longitude) {
			String addr = "";

			// 也可以是http://maps.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s，不过解析出来的是英文地址
			// 密钥可以随便写一个key=abc
			// output=csv,也可以是xml或json，不过使用csv返回的数据最简洁方便解析
			String url = String.format(
					"http://ditu.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s",
					latitude, longitude);
			URL myURL = null;
			URLConnection httpsConn = null;
			try {
				myURL = new URL(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
			try {
				httpsConn = (URLConnection) myURL.openConnection();
				if (httpsConn != null) {
					InputStreamReader insr = new InputStreamReader(
							httpsConn.getInputStream(), "UTF-8");
					BufferedReader br = new BufferedReader(insr);
					String data = null;
					if ((data = br.readLine()) != null) {
						String[] retList = data.split(",");
						if (retList.length > 2 && ("200".equals(retList[0]))) {
							addr = retList[2];
							addr = addr.replace("/", "");
						} else {
							addr = "";
						}
					}
					insr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			return addr;
		}
	 public static String getaddressforxybyGooglehttpconnection(String x,String y) throws IOException
	 {
	  URL url = null;
	  String mapurl="http://api.map.baidu.com/geocoder/v2/?ak=16c1b7ff4e7d968678f132f20d05c2dd&callback=renderReverse&location="+x+","+y+"&output=json&pois=1";
	  
	  String address="";//取地图的地址
	  HttpURLConnection connection = null;    
	  DataInputStream in = null;
	  try {
	   url = new URL(mapurl);          
	    connection = (HttpURLConnection) url.openConnection();
	    connection.setRequestMethod("GET"); 
	    connection.setUseCaches(false);   
	    connection.setDefaultUseCaches(false);                    
	    connection.setDoInput(true);  
	    connection.setDoOutput(true);       
	      connection.setConnectTimeout(1000);
	      connection.setReadTimeout(1000);    
	      in = new DataInputStream(connection.getInputStream());               
	      int   all=   in.available();  
	      int   code   =   connection.getResponseCode();   
	      if(code   !=   connection.HTTP_OK)   
	            {                           
	                return address ;
	            }   
	            else   
	            {   
	             byte[]   b=   new   byte[all];   
	            in.read(b);     
	              String strAddress=   new   String(b,"UTF-8"); //GBK  2011-04-28
	               String[] m_sAddress = strAddress.split("city");
	               String[] ss = m_sAddress[1].split("district");
	               String[] tt = ss[0].split(":");
	               String[] ff = tt[1].split(",");
	               String newStr =ff[0].replaceAll("\"",""); 
	               address = newStr;
	   }                                                           
	      connection.disconnect();
	      in.close();         
	     } catch (Exception e)
	     {       
	      address="无法从地理信息服务器上获得此位置的地理信息";//取地图的地址
	     } 
	     finally
	     { 
	      connection.disconnect();
	      in.close();
	     }
	  address = address.replace('"', ' ');
	  address = address.replace("<?xml version= 1.0  encoding= GBK ?><R><code>0</code><msg>", "");
	  address = address.replace("</msg></R>", "");
	  return address;
	 }
	 
	 /**
	  * 判断是否为汉字，如果不是汉字则进行转�?
	  * @param str
	  * @return
	  */
	public static String changeIos8859ToUtf8(String str) {
		if(null == str){
			return null;
		}
		try {
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
			
			if(flag){
				return new String(str.getBytes("ISO-8859-1"),"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}
}
