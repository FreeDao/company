package com.era.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.User;
import com.era.orm.UserFeedBack;
import com.era.service.UserService;
import com.era.util.BaseUtils;
import com.era.util.PiaoJuTong;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>,
ServletRequestAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Map<String,Object> map  = new HashMap<String,Object>();
	
	private User model = new User();
	
	private UserService userService;
	
	/**
	 * 用户登陆
	 * @return
	 */
	public String login()
	{
		String userName = null;
		try 
		{
			String pengyou = request.getParameter("type");
			String email = request.getParameter("email");
			String head = request.getParameter("head");
			if(pengyou != null)
			{
				if(pengyou == "qq" || pengyou.equals("qq"))
				{
					User user = new User();
					model = userService.loginQq(email);
					if(model == null || model.equals(""))
					{
						
					}
					else
					{
						user.setId(model.getId());
					}
					if(BaseUtils.isChinaese(request.getParameter("userName")))
					{
						userName = new String(
								request.getParameter("userName").getBytes("ISO-8859-1"), "utf-8");
					}
					else
					{
						userName = request.getParameter("userName");
					}
					user.setEmail(email);
					user.setUserName(userName);
					user.setHead(head);
					boolean bool = userService.addUser(user);
					if(bool)
					{
						JSONArray array = JSONArray.fromObject(user);
						map.put("lists",array);
						map.put("responseCode", "0");
					}
					else
					{
						map.put("msg", "QQ登陆失败");
						map.put("responseCode", "1");
					}
					return SUCCESS;
				}
			}
			User user = userService.login(email,PiaoJuTong.Md5(request.getParameter("passWord")));
			if(user == null || user.equals(""))
			{
				map.put("responseCode", "1");
				map.put("msg", "用户名或密码错误");
			}
			else
			{
				JSONArray array = JSONArray.fromObject(user);
				map.put("responseCode", "0");
				map.put("lists",array);
			}
		}
		catch (Exception e) 
		{
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 注册
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String register() throws UnsupportedEncodingException
	{
		String userName = "";
		try {
			model = userService.loginQq(request.getParameter("email"));
			if(model == null || model.equals(""))
			{
				User user = new User();
				user.setPassWord(PiaoJuTong.Md5(request.getParameter("passWord")));
				user.setEmail(request.getParameter("email"));
				if(BaseUtils.isChinaese(request.getParameter("userName")))
				{
					userName = new String(
							request.getParameter("userName").getBytes("ISO-8859-1"), "utf-8");
				}
				else
				{
					userName = request.getParameter("userName");
				}
				user.setUserName(userName);
				boolean bool = userService.addUser(user);
				if(bool)
				{
					JSONArray array = JSONArray.fromObject(user);
					map.put("lists",array);
					map.put("responseCode", "0");
				}
				else
				{
					map.put("responseCode", "1");
				}
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "邮箱已存在");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * TODO 用户反馈信息
	 * @throws UnsupportedEncodingException 
	 */
	public String putBackMessage() throws UnsupportedEncodingException
	{
		UserFeedBack back = new UserFeedBack();
		String content = "";
		try {
			if(BaseUtils.isChinaese(request.getParameter("content")))
			{
				content = new String(
						request.getParameter("content").getBytes("ISO-8859-1"), "utf-8");
			}
			else
			{
				content = request.getParameter("content");
			}
			back.setContent(content);
			back.setIphone(request.getParameter("email"));
			boolean bool = userService.addUserFeedBack(back);
			if(bool)
			{
				map.put("responseCode", "0");
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "反馈失败");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "反馈失败");
		}
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	@Override
	public User getModel() {
		return model;
	}


	public Map<String, Object> getMap() {
		return map;
	}


	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
