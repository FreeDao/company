package com.marck.in.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.SystemArguments;
import com.marck.common.model.Captcha;
import com.marck.common.model.User;
import com.marck.common.sms.Send;
import com.marck.in.service.RegisterService;
import com.marck.in.service.SmsService;

@Component("smsAction")
@Scope("prototype")
public class SmsAction extends BaseAction{

	@Autowired
	private RegisterService registerService;
	@Autowired
	private SmsService smsService;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private String phone;
	private Integer type;
	private String captcha;
	
	/**
	 * 获取验证码
	 * @return
	 */
	public String get(){
		try {
			String content = "";
			
			if(CommonUtil.validParams(phone,type)){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			int temp = (int) (Math.random()*100000+10000);
			
			String captcha = temp+"";
			
			
			User user = registerService.checkUser(phone);
			
			if( null != user && type == 1){
				map.put("msg", "该手机已经注册");
				map.put("code", 0);
				return SUCCESS;
			}else if( null == user && type == 2){
				map.put("msg", "该手机未注册");
				map.put("code", 0);
				return SUCCESS;
			}
			
			if( "1".equals(type)){
				content="你好，欢迎注册同城生活圈，你的验证码是："+captcha+"【同城生活圈】";
			}else{
				content="你好，你的密码找回验证码是："+captcha+"【同城生活圈】";
			}
			
			Send.send(phone,content);
			
			smsService.saveCaptcha(phone,captcha,type);
			
			map.put("msg", "获取验证成功");
			map.put("code", 1);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("获取验证码异常",e);
			map.put("msg", "获取验证码异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 验证是否有效
	 * @return
	 */
	public String valid(){
		try {
			
			if(CommonUtil.validParams(phone,type,captcha)){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			 if(captcha.equals(SystemArguments.captcha)){
				map.put("msg", "你爸妈造你在作弊吗？");
				map.put("code", 1);
				return SUCCESS;
			 }
			
			smsService.checkCaptcha(phone, captcha, type,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("验证码验证异常",e);
			map.put("msg", "验证码验证异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}

	public Map<String, Object> getMap() {
		return map;
	
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getPhone() {
		return phone;
	
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCaptcha() {
		return captcha;
	
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	
	
}
