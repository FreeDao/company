package com.era.action;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.BusmarsetManager;
import com.era.service.UserService;
import com.era.util.PiaoJuTong;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<BusmarsetManager>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BusmarsetManager model = new BusmarsetManager();
	private UserService userService;
	
	private HttpServletRequest request;

	/**
	 * 登录
	 * @return
	 * @throws IOException 
	 */
	public String login() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String data="";
		try{
			if(request.getSession().getAttribute("userId") == null)
			{
				System.out.println(PiaoJuTong.Md5(request.getParameter("passWord")));
				model = userService.login(request.getParameter("userName"),PiaoJuTong.Md5(request.getParameter("passWord")));
				if(model == null)
				{
					data = "{msg:\"1\"}";
					response.getWriter().write(data);
					return null;
				}
				request.getSession().setAttribute("userId", model.getId());
				request.getSession().setAttribute("sellerId", model.getBmsmId());
				request.getSession().setAttribute("root", model.getRoot());
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			data = "{msg:\"2\"}";
			response.getWriter().write(data);
			return null;
		}
		data = "{msg:\"3\"}";
		response.getWriter().write(data);
		return null;
	}
	/**
	 * 退出
	 */
	@SuppressWarnings("rawtypes")
	public String UserLoginOut() throws Exception
	{
		request.getSession().invalidate();
		Enumeration e = request.getSession().getAttributeNames();
		while (e.hasMoreElements()) 
		{
			String sessionName = (String) e.nextElement();
			request.getSession().removeAttribute(sessionName);
		}
		return SUCCESS;
	}
	/**
	 * 跳转到主页
	 * @return
	 */
	public String pageUser()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		return SUCCESS;
	}
	/**
	 * 用户信息
	 * @return
	 */
	public String selPageUser()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		model = userService.selUserId((Integer) request.getSession().getAttribute("userId"));
		return SUCCESS;
	}
	/**
	 * 跳转到修改密码
	 * @return
	 */
	public String updatePassPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		return SUCCESS;
	}
	/**
	 * 修改所有信息
	 * @return
	 * @throws Exception 
	 */
	public String updatePass() throws Exception
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		String data="";
		model = userService.selUserId((Integer) request.getSession().getAttribute("userId"));
		model.setId(model.getId());
		if(request.getParameter("passWord") == null || request.getParameter("passWord").equals(""))
		{
			model.setUserPwd(model.getUserPwd());
			model.setEmail(request.getParameter("email"));
			model.setQq(request.getParameter("qq"));
			model.setTelePhone(request.getParameter("phone"));
		}
		else
		{
			if(PiaoJuTong.Md5(request.getParameter("oPassWord")).equals(model.getUserPwd()))
			{
				model.setUserPwd(PiaoJuTong.Md5(request.getParameter("passWord")));
			}
			else
			{
				data = "{msg:\"2\"}";
				response.getWriter().write(data);
			}
			model.setEmail(model.getEmail());
			model.setQq(model.getQq());
			model.setTelePhone(model.getTelePhone());
		}
		model.setAddTime(model.getAddTime());
		model.setBmsmId(model.getBmsmId());
		model.setUserName(model.getUserName());
		boolean bool = userService.updateUser(model);
		if(bool)
		{
			if(request.getParameter("oPassWord") == null ||request.getParameter("oPassWord").equals(""))
			{
				data = "{msg:\"1\"}";
				response.getWriter().write(data);
				return null;
			}
			else
			{
				UserLoginOut();
				data = "{msg:\"1\"}";
				response.getWriter().write(data);
				return null;
			}
		}
		else
		{
			data = "{msg:\"2\"}";
			response.getWriter().write(data);
		}
		return null;
	}
	/**
	 * 跳转到修改个人信息
	 * @return
	 */
	public String updateMe()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		model = userService.selUserId((Integer) request.getSession().getAttribute("userId"));
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	@Override
	public BusmarsetManager getModel() 
	{
		return model;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
