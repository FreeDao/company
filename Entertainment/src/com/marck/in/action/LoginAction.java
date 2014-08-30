package com.marck.in.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.model.User;
import com.marck.in.service.LoginService;

@Component("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction{

	@Autowired
	private LoginService loginService;
	
	private User user;
	private Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 帐号密码登录
	 */
	public String account(){
		try {
			
			if(null == user  || CommonUtil.validParams(user.getPhone(),user.getPassWord())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			user = loginService.getUser(user);
			
			if( null != user ){
				map.put("msg", "登录成功");
				map.put("userInfo", user);
				map.put("code", 1);
			}else{
				map.put("msg", "帐号或密码不正确");
				map.put("code", 0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("登录异常",e);
			map.put("msg", "登录异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * qq登录
	 */
	public void qq(){
		
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
