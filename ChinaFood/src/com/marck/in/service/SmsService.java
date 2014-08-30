package com.marck.in.service;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.SystemArguments;
import com.marck.common.dao.HDB;
import com.marck.common.model.Captcha;

@Component("smsService")
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class SmsService {
	
	@Autowired
	private HDB hdb;

	/**
	 * 保存验证码
	 * @param phone
	 * @param captcha
	 * @param type 
	 */
	public void saveCaptcha(String phone, String captcha, Integer type) {
		// TODO Auto-generated method stub
		String hql = "from Captcha c where c.phone="+phone;
		
		List<Captcha> captchas = (List<Captcha>) hdb.findHql(hql);
		
		Captcha c = new Captcha();
		
		if(captchas.size() > 0){
			c = captchas.get(0);
		}
		
		c.setPhone(phone);
		c.setType(type);
		c.setCaptcha(captcha);
		c.setCaptchaTime(new Date().getTime()+"");
		hdb.saveOrUpdate(c);
		
	}

	/**
	 * 检测验证码
	 * @param phone
	 * @param captcha
	 * @param type
	 * @param map
	 */
	public void checkCaptcha(String phone, String captcha, Integer type,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		String hql = "from Captcha c where c.phone='"+phone+"'";
		
		List<Captcha> captchas = (List<Captcha>) hdb.findHql(hql);
		
		if(captchas.size() <= 0){
			map.put("msg", "没有验证码信息，请先获取验证码");
			map.put("code", 0);
			return;
		}
		
		Captcha c = captchas.get(0);
		
		Long now = new Date().getTime();
		
		if( (now - Long.parseLong(c.getCaptchaTime())) > SystemArguments.captchaTime ){
			map.put("msg", "验证码已过期,请重新获取验证码");
			map.put("code", 0);
		}else if( captcha.equals(c.getCaptcha()) && phone.equals(c.getPhone()) && type.equals(c.getType()) ){
			map.put("msg", "验证成功");
			map.put("code", 1);
		}else{
			map.put("msg", "验证码不正确");
			map.put("code", 0);
		}
	}

}
