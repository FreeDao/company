package com.era.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Backmessage;
import com.era.service.BackMessageService;
import com.era.util.BaseUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BackmessageAction extends ActionSupport implements ModelDriven<Backmessage>,ServletRequestAware{
	
	private HttpServletRequest request;
	private Backmessage backMessage = new Backmessage();
	private BackMessageService backMessageService;
	private String backContent;
	private String addTime;
	private String backPhone;
	private String user;
	private String result;
	
	/**
	 * TODO 用户反馈信息
	 */
	public void putBackMessage(){
		String backContent_str = request.getParameter("backContent");
		String backPhone_str = request.getParameter("backPhone");
		String user_str = request.getParameter("user");
		
		System.out.println();
		
		if(backContent_str != null && backPhone_str!= null){
			//反馈内容
			boolean torf_content = BaseUtils.isChinaese(backContent_str);
			if (torf_content) {
				try {
					// 用于IOS客户端传递过来的用户名
					backContent_str = new String(backContent_str.getBytes("ISO-8859-1"),"UTF-8");
					System.out.println("---isChinaese---backContent_str--IOS-->"+backContent_str+"<--------------");
					// 用于IE网页传递过来的用户名
//					content = new String(content.getBytes("ISO-8859-1"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				backContent_str = backContent_str;
			}
			System.out.println("---isChinaese---backContent_str--end-->"+backContent_str+"<--------------");
			
			//登录用户的反馈信息
			boolean torf_user = BaseUtils.isChinaese(user_str);
			if (torf_user) {
				try {
					// 用于IOS客户端传递过来的用户名
					user_str = new String(user_str.getBytes("ISO-8859-1"),"UTF-8");
					System.out.println("---isChinaese---user_str--IOS-->"+user_str+"<--------------");
					// 用于IE网页传递过来的用户名
//					content = new String(content.getBytes("ISO-8859-1"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				user_str = user_str+"";
			}
			System.out.println("---isChinaese---user_str--end-->"+user_str+"<--------------");
			
			user_str = user_str != null ? user_str : "";
			backMessage.setBackContent(backContent_str);
			backMessage.setBackPhone(backPhone_str);
			backMessage.setAddTime(BaseUtils.getNowStringDateTime(new Date()));
			backMessage.setUser(user_str);
			
			boolean flag = backMessageService.putBackMessage(backMessage);
			if(flag){
				result = "{\"responseCode\":\"" + 0 + "\"}";
			}else{
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		}else{
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Backmessage getModel() {
		return backMessage;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Backmessage getBackMessage() {
		return backMessage;
	}

	public void setBackMessage(Backmessage backMessage) {
		this.backMessage = backMessage;
	}

	public String getBackContent() {
		return backContent;
	}

	public void setBackContent(String backContent) {
		this.backContent = backContent;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getBackPhone() {
		return backPhone;
	}

	public void setBackPhone(String backPhone) {
		this.backPhone = backPhone;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public BackMessageService getBackMessageService() {
		return backMessageService;
	}

	public void setBackMessageService(BackMessageService backMessageService) {
		this.backMessageService = backMessageService;
	}
}
