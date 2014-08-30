package com.era.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.era.orm.Luck;
import com.era.orm.User;
import com.era.orm.Winning;
import com.era.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class DwrAction   extends ActionSupport implements SessionAware,ServletRequestAware
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> session=new HashMap<String, Object>();
	private HttpServletRequest request;
	private UserService userService;
	public Map<String,Object> map = new HashMap<String, Object>();
	private List<User> listUser;
	private List<Luck> listLotterdraw;
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	/**
	 * 抽奖
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException
	{
		try {
				listLotterdraw = userService.selListLotterdraw(request.getParameter("ttt"));
				map.put("msg", listLotterdraw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 跳转到抽奖页面
	 * @return
	 */
	public String pageFind()
	{
		return SUCCESS;
	}
	
	/**
	 * 测试dwr
	 * 
	 * @return
	 */
	public String pageFindText()
	{
		return SUCCESS;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Luck> getListLotterdraw() {
		return listLotterdraw;
	}

	public void setListLotterdraw(List<Luck> listLotterdraw) {
		this.listLotterdraw = listLotterdraw;
	}

	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}
}
