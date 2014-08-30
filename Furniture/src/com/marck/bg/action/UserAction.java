package com.marck.bg.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.bg.service.UserService;
import com.marck.common.BaseAction;
import com.marck.common.model.Menu;
import com.marck.common.model.User;

@Component("userAction")
@Scope("prototype")
public class UserAction extends BaseAction {

	private User user;
	
	@Autowired
	private UserService userService;

	public String login(){
		try{
			User u = userService.getUser(user);
			if( null != u ){
				List<Menu> tree = userService.getTree(u);
				request.getSession().setAttribute("tree", tree);
				request.getSession().setAttribute("userSession", u);
				return "loginSuccess";
			}else{
				request.setAttribute("msg", "*帐号或密码错误！");
				return "loginFail";
			}
		}catch (Exception e) {
			// TODO: handle exception
			log.error("登录错误！");
		}
		return ERROR;
	}
	
	public User getUser() {
		return user;
	
	}

	public void setUser(User user) {
		this.user = user;
	}
}
