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
import com.marck.common.model.User;
import com.marck.common.sms.Send;
import com.marck.in.service.RegisterService;

@Component("registerAction")
@Scope("prototype")
public class RegisterAction extends BaseAction{

	@Autowired
	private RegisterService registerService;
	
	private User user;
	private Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 手机注册
	 * @return
	 */
	public String phone(){
		try {
			
			if( null == user || CommonUtil.validParams(user.getPhone(),user.getNick(),user.getPassWord())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			registerService.addUser(user,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("手机注册异常",e);
			map.put("msg", "注册异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	
	/**
	 * 短信验证码注册
	 * @return
	 */
	public String sms(){
		try {
			
			if( null == user || CommonUtil.validParams(user.getPhone(),user.getNick(),user.getPassWord(),user.getCaptcha())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			if(user.getCaptcha().equals(SystemArguments.captcha)){
				registerService.addUser(user,map);
			}else{
				registerService.addSmsUser(user,map);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("短信注册异常",e);
			map.put("msg", "短信注册异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}

	/**
	 * 查询用户是否存在
	 * @return
	 */
	public String selAccount(){
		try {
			
			if( null == user || CommonUtil.validParams(user.getPhone())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			registerService.selAccoutByPhone(user,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询帐号异常",e);
			map.put("msg", "查询异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Object> getMap() {
		return map;//null == map?"":map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}
