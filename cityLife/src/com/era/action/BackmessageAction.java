package com.era.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Backmessage;
import com.era.service.BackMessageService;
import com.era.util.BaseUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BackmessageAction extends ActionSupport implements ModelDriven<Backmessage>,ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * @throws UnsupportedEncodingException 
	 */
	public void putBackMessage() throws UnsupportedEncodingException{
		String backContent_str = request.getParameter("backContent");
		if(backContent_str == null || backContent_str.equals(""))
		{
			
		}
		else
		{
			if(BaseUtils.isChinaese(backContent_str))
			{
				backContent_str = new String(backContent_str.getBytes("ISO-8859-1"),"UTF-8");
			}
			else
			{
				backContent_str = request.getParameter("backContent");
			}
		}
			backMessage.setBackContent(backContent_str);
			backMessage.setBackPhone(request.getParameter("backPhone"));
			backMessage.setAddTime(BaseUtils.getNowStringDateTime(new Date()));
			backMessage.setUser(request.getParameter("user"));
			boolean flag = backMessageService.putBackMessage(backMessage);
			if(flag){
				result = "{\"responseCode\":\"" + 0 + "\"}";
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
