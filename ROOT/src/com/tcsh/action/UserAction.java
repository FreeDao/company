package com.tcsh.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tcsh.common.BaseAction;
import com.tcsh.common.CommonUtil;
import com.tcsh.common.DateUtil;
import com.tcsh.common.PageUtil;
import com.tcsh.model.local.User;
import com.tcsh.service.UserService;


@Component("userAction")
@Scope("prototype")
public class UserAction extends BaseAction{

	@Autowired
	private UserService userService;
	
	private User user; 
	
	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	
	/**
	 * 系统首页
	 * @return
	 */
	public String index(){
		try {
			pu = new PageUtil();
			pu.setUrl("bg/user!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 后台登陆页面
	 * @return
	 */
	public String bg(){
		try {
			pu = new PageUtil();
			pu.setUrl("bg/user!bg");
			return "bg";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 用户登陆
	 * @return
	 */
	public String login(){
		try {
			/*if(!"admin".equals(user.getUserName())){
				return "fail";
			}*/
			user = userService.findUser(user.getUserName(),CommonUtil.Md5(user.getPassWord()));
			if( null == user ){
				request.setAttribute("msg", "账号或密码错误！");
				return "fail";
			}else{
				request.getSession().setAttribute("userSession", user);
				return "login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法login出错");
		}
		return ERROR;
	}

	/**
	 * 查询自己的信�?
	 * @return
	 */
	public String myInfo(){
		try {
			user = (User) userService.findObjectById(User.class,user.getId());
			return "info";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法myInfo出错");
		}
		return ERROR;
	}
	
	
	/**
	 * 查询�?��用户
	 * @return
	 */
	public String selAllAccount(){
		try {
			pu = userService.findAllList(User.class,pageNow,limit);
			pu.setUrl("bg/user!selAllAccount");
			return "AccountList";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("方法selAllAccount出错");
		}
		return ERROR;
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEditAccount(){
		try {
			if( null != user ){
				user = (User) userService.findObjById(User.class,user.getId());
			}
			return "addOrEditAccount";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信�?
	 * @return
	 */
	public String addOrEditAccount(){
		try {
			User u = new User();
			if( null != user.getId() ){
				u = (User) userService.findObjById(User.class, user.getId());
				user.setUserName(u.getUserName());
				user.setPassWord(u.getPassWord());
			}else{
				user.setAddTime(DateUtil.getNowDate("yyyy-MM-dd"));
			}
			
			userService.saveOrUpdateObj(user);
			request.setAttribute("msg", 1);
			return selAllAccount();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("UserAction中方法addOrEditAccount出错");
			return selAllAccount();
		}
	}
	
	
	/**
	 * 用户注销
	 * @return
	 */
	public String logout(){
		try {
			session.invalidate();
			return "logout";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("UserAction中方法logout出错");
		}
		return ERROR;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PageUtil getPu() {
		return pu;
	}

	public void setPu(PageUtil pu) {
		this.pu = pu;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
