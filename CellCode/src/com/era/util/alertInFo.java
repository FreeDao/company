package com.era.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

public class alertInFo {
	public static final String KEY_1 = "7d9fbeb43e975cd1e9477a7e5d5e192a";
	/**
	 * 弹出alert框
	 * GGJ
	 * 2010-08-31
	 * @param text
	 * @return
	 * @throws IOException 
	 */
	public static String alertReturn(String text) throws IOException{
		HttpServletResponse res = ServletActionContext.getResponse();

		PrintWriter printWriter;
		res.setContentType("text/html; charset=GBK");//解决乱码
		printWriter = res.getWriter();
	     printWriter.append("<script language=\"javascript\">");
	     printWriter.append("alert('"+text+"！');");
	     printWriter.append("</script>");
	     printWriter.flush();
	     return null;
		}
	/**
	 * 获取当前浏览器端IP
	 * @return
	 */
	public String ip;
	public String getIp() {
		if(ip == null){
			HttpServletRequest request = ServletActionContext.getRequest();
			ip = request.getHeader("x-forwarded-for");
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Real-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
				System.out.println(request.getRemoteAddr());
			}
			if (ip == null || ip.length() == 0
					|| "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
//				System.out.println(request.getRemoteAddr());
			}
		}
		Object[] roleString = ip.split("\\.");
		return roleString[0]+"."+roleString[1]+"."+roleString[2]+"."+"**";
	}
	public static String reditectinfo(String url){
		HttpServletResponse res = ServletActionContext.getResponse();
		try {
			
			PrintWriter out = res.getWriter();
			res.setContentType("text/html;charset=utf-8");
			out.write("<script>");
			if(url != null && !"".equals(url)){
				if("-1".equals(url)){
					out.write("history.go(-1);");
				}else if("0".equals(url)){
					out.write("location.reload();");
				}else{
					out.write("location.href='"+url+"';");
				}
			}
			out.write("</script>");
			out.close();
		} catch (IOException e) {
			
		}
		return null;
	}
	/**
	 * 返回输入地址的经纬度坐标
	 * key lng(经度),lat(纬度)
	 */
	public static Map<String,String> getGeocoderLatitude(String address){
		BufferedReader in = null;
		try {
			//将地址转换成utf-8的16进制
			address = URLEncoder.encode(address, "UTF-8");
//       如果有代理，要设置代理，没代理可注释
//		System.setProperty("http.proxyHost","192.168.1.188");
//		System.setProperty("http.proxyPort","3128");
			URL tirc = new URL("http://api.map.baidu.com/geocoder?address="+ address +"&output=json&key="+ KEY_1);
			
			in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while((res = in.readLine())!=null){
				sb.append(res.trim());
			}
			String str = sb.toString();
			Map<String,String> map = null;
			if(StringUtils.isNotEmpty(str)){
				int lngStart = str.indexOf("lng\":");
				int lngEnd = str.indexOf(",\"lat");
				int latEnd = str.indexOf("},\"precise");
				if(lngStart > 0 && lngEnd > 0 && latEnd > 0){
					String lng = str.substring(lngStart+5, lngEnd);
					String lat = str.substring(lngEnd+7, latEnd);
					map = new HashMap<String,String>();
					map.put("lng", lng);
					map.put("lat", lat);
					return map;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
