package com.era.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class alertInFo {
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
}
