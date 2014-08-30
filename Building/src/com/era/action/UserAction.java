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
			if(pengyou == "qq" || pengyou.equals("qq"))
			{
				model = userService.isHave(email);
				if(model == null || model.equals(""))
				{
					User user = new User();
					if(BaseUtils.isChinaese(request.getParameter("email")))
					{
						userName = new String(
								request.getParameter("email").getBytes("ISO-8859-1"), "utf-8");
					}
					else
					{
						userName = request.getParameter("email");
					}
					user.setEmail(email);
					user.setQq(userName);
					boolean bool1 = userService.addUser(user);
					if(bool1)
					{
						JSONArray array = JSONArray.fromObject(user);
						map.put("result",array);
						map.put("responseCode", "0");
					}
					else
					{
						map.put("msg", "QQ登陆失败");
						map.put("responseCode", "1");
					}
				}
				else 
				{
					JSONArray array = JSONArray.fromObject(model);
					map.put("result",array);
					map.put("responseCode", "0");
				}
				return SUCCESS;
			}
			else if(pengyou == "pengyou" || pengyou.equals("pengyou"))
			{
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
					map.put("result",array);
				}
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
			model = userService.isHave(request.getParameter("email"));
			if(model == null || model.equals(""))
			{
				User user = new User();
				if(BaseUtils.isChinaese(request.getParameter("email")))
				{
					userName = new String(
							request.getParameter("email").getBytes("ISO-8859-1"), "utf-8");
				}
				else
				{
					userName = request.getParameter("email");
				}
				user.setEmail(userName);
				user.setPassWord(PiaoJuTong.Md5(request.getParameter("passWord")));
				boolean bool = userService.addUser(user);
				if(bool)
				{
					JSONArray array = JSONArray.fromObject(user);
					map.put("result",array);
					map.put("responseCode", "0");
				}
				else
				{
					map.put("responseCode", "1");
				}
			}
			else
			{
				map.put("responseCode", "2");
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
			
			if(request.getParameter("userName")==null||request.getParameter("userName").equals(""))
			{
				back.setUserName("");
			}else
			{
				if(BaseUtils.isChinaese(request.getParameter("userName")))
				{
					back.setUserName(new String(request.getParameter("userName").getBytes("ISO-8859-1"), "utf-8"));
				}else
				{
					back.setUserName(request.getParameter("userName"));
				}
			}
			
			boolean bool = userService.addUserFeedBack(back);
			if(bool)
			{
				map.put("responseCode", "0");
				map.put("lists", back);
			}
			else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
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
