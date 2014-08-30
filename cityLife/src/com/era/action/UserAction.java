package com.era.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.User;
import com.era.service.UserService;
import com.era.util.BaseUtils;
import com.era.util.PiaoJuTong;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>,
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService;
	private List<User> list;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;

	HttpServletRequest request;

	User model = new User();

	private String userName;// 用户名
	private String userPwd;// 密码
	private String email;
	private String sex;
	private String result;// 用于组装json
	private String responseCode;// 响应参数
	private String defaultImgUrl = "http://imgstatic.baidu.com/img/image/shouye/wuqilong.jpg";

	/**
	 * 查询所有的用户
	 * 
	 * @return
	 */
	public String selUser() {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		pagecount = userService.numberUser(request.getParameter("id"),
				request.getParameter("userNames"));
		if (pagecount < 15) {
			pageCount = 1;
		} else {
			pageCount = pagecount / 15;
			int i = pagecount % 15;
			if (i > 0) {
				pageCount += 1;
			}
		}
		if (pagenum < 1) {
			pagenum = 1;
		}
		if (pagenum > pagecount) {
			if (pagecount == 0) {

			} else {
				pagenum = pagecount;
			}
		}
		list = userService.selUser(request.getParameter("id"),
				request.getParameter("userNames"), pagenum, 15);
		return SUCCESS;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delUser() throws IOException {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		boolean bool = userService.delUser(Integer.valueOf(request
				.getParameter("did")));
		if (bool) {
			selUser();
		} else {
			alertInFo.alertReturn("删除失败！");
		}
		return SUCCESS;
	}

	/**
	 * TODO 客户端用户登录
	 * 
	 * @return
	 */
	public void loginUserInfo() {
		String userName_str = request.getParameter("userName");
		String userPwd_str = request.getParameter("userPwd");
		String pengyou = request.getParameter("type");
		String email = request.getParameter("email");
		if(pengyou == "qq" || pengyou.equals("qq"))
		{
			
			model = userService.login(email);
			JSONArray array = JSONArray.fromObject(model);
			result = "{\"responseCode\":\"" + 0 + "\",\"results\":"
					+ array + "}";
		}
		else
		{
			if (userName_str != null && userPwd_str != null) {
				// 处理用户名
				boolean torf = BaseUtils.isChinaese(userName_str);
				if (torf) {
					try {
						// 用于IOS客户端传递过来的用户名
						userName_str = new String(
								userName_str.getBytes("ISO-8859-1"), "UTF-8");
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					userName_str = userName_str+"";
				}
				User user = userService.loginUserInfo(userName_str,
						PiaoJuTong.Md5(userPwd_str));
				if (user != null) {
					User u = new User();
					u.setId(user.getId());
					u.setNickName(user.getNickName());
					u.setPassWord(userPwd_str);
					u.setSex(user.getSex());
					u.setName(user.getName());
					u.setCharacterName(user.getCharacterName());
					u.setAge(user.getAge());
					u.setAddtime(user.getAddtime());
					u.setType(user.getType());
					u.setImgUrl(user.getImgUrl());
					u.setLog(user.getLog());
					u.setDim(user.getDim());
					u.setPhone(user.getPhone());
					u.setEmail(user.getEmail());

					JSONArray array = JSONArray.fromObject(u);
					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"
							+ array.toString() + "}";
				} else {
					result = "{\"responseCode\":\"" + 1 + "\"}";
				}
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 客户端用户注册
	 * 
	 * @return
	 */
	public void registerUserInfo() {
		
		try {
			String userName_str = request.getParameter("userName");
			String userPwd_str = request.getParameter("userPwd");
			String sex_str = request.getParameter("sex");
			String email_str = request.getParameter("email");
			String headIco_str = request.getParameter("headIco");

			String pengyou = request.getParameter("type");
			String email = request.getParameter("email");
			if(pengyou == "qq" || pengyou.equals("qq"))
			{
				
				model = userService.login(email);
				if( model== null || model.equals(""))
				{
					if (userName_str != null && email_str != null) {
						boolean torf = BaseUtils.isChinaese(userName_str);
						if (torf) {
							try {
								// 用于IOS客户端传递过来的用户名
								userName_str = new String(
										userName_str.getBytes("ISO-8859-1"), "UTF-8");
								// 用于网页传递过来的用户名
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							userName_str = userName_str;
						}
						System.out.println("--torf---userName_str--->" + userName_str
								+ "<--------------");

						/**
						 * 1、QQ 登录，先调用登录方法，如果当前QQ此前登录注册有信息，则不做任何操作 否则如果没有的话，则调用注册方法进行注册使用
						 */
						boolean flag_email = userService.isEmail(email_str);
						if (!flag_email) {
							sex = sex_str != null ? sex_str : "1";// 1 男 2女
							// TODO 修改默认头像
							headIco_str = headIco_str != null ? headIco_str
									: defaultImgUrl;

							User user = new User();
							user.setNickName(userName_str);
//							user.setPassWord(PiaoJuTong.Md5(userPwd_str));
							user.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
							user.setEmail(email_str);
							user.setSex(Integer.parseInt(sex));
							user.setAge(20);// default age
							user.setImgUrl(headIco_str);// default imgUrl

							User register_user = userService.registerUserInfo(user);

							if (register_user != null) {
								User u = new User();
								u.setId(register_user.getId());
								u.setNickName(register_user.getNickName());
//								u.setPassWord(userPwd_str);
								u.setSex(register_user.getSex());
								u.setName(register_user.getName());
								u.setCharacterName(register_user.getCharacterName());
								u.setAge(register_user.getAge());
								u.setAddtime(register_user.getAddtime());
								u.setType(register_user.getType());
								u.setImgUrl(register_user.getImgUrl());
								u.setLog(register_user.getLog());
								u.setDim(register_user.getDim());
								u.setPhone(register_user.getPhone());
								u.setEmail(register_user.getEmail());

								JSONArray array = JSONArray.fromObject(u);
								result = "{\"responseCode\":\"" + 0 + "\",\"results\":"
										+ array.toString() + "}";
							} 
							else 
							{
								result = "{\"responseCode\":\"" + 1 + "\"}";
							}
						} else {
							result = "{\"responseCode\":\"" + 3 + "\"}";
						}
					} else {
						result = "{\"responseCode\":\"" + 1 + "\"}";
					}
				}
				else
				{
					User user = new User();
					user.setId(model.getId());
					user.setAddtime(model.getAddtime());
					if(BaseUtils.isChinaese(userName_str))
					{
						userName_str = new String(
								userName_str.getBytes("ISO-8859-1"), "UTF-8");
						user.setNickName(userName_str);
					}
					else
					{
						user.setNickName(userName_str);
					}
//					user.setPassWord(PiaoJuTong.Md5(userPwd_str));
					user.setSex(Integer.valueOf(sex_str));
					user.setEmail(email_str);
					// TODO 修改默认头像
					headIco_str = headIco_str != null ? headIco_str
							: defaultImgUrl;
					user.setImgUrl(headIco_str);
					boolean bool = userService.addUser(user);
					if(bool)
					{
						JSONArray array = JSONArray.fromObject(user);
						result = "{\"responseCode\":\"" + 0 + "\",\"results\":"
								+ array + "}";
					}
					else
					{
						result = "{\"responseCode\":\"" + 1 + "\"}";
					}
				}
			}
			else if(pengyou == "py" || pengyou.equals("py"))
			{
				if (userName_str != null && userPwd_str != null && email_str != null) {
					boolean torf = BaseUtils.isChinaese(userName_str);
					if (torf) {
						try {
							// 用于IOS客户端传递过来的用户名
							userName_str = new String(
									userName_str.getBytes("ISO-8859-1"), "UTF-8");
							System.out.println("---isChinaese---userName_str--IOS-->"
									+ userName_str + "<--------------");
							// 用于网页传递过来的用户名
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						userName_str = userName_str;
					}
					System.out.println("--torf---userName_str--->" + userName_str
							+ "<--------------");

					/**
					 * 1、QQ 登录，先调用登录方法，如果当前QQ此前登录注册有信息，则不做任何操作 否则如果没有的话，则调用注册方法进行注册使用
					 */
					boolean flag_email = userService.isEmail(email_str);
					if (!flag_email) {
						sex = sex_str != null ? sex_str : "1";// 1 男 2女
						// TODO 修改默认头像
						headIco_str = headIco_str != null ? headIco_str
								: defaultImgUrl;

						User user = new User();
						user.setNickName(userName_str);
						user.setPassWord(PiaoJuTong.Md5(userPwd_str));
						user.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
						user.setEmail(email_str);
						user.setSex(Integer.parseInt(sex));
						user.setAge(20);// default age
						user.setImgUrl(headIco_str);// default imgUrl

						User register_user = userService.registerUserInfo(user);

						if (register_user != null) {
							User u = new User();
							u.setId(register_user.getId());
							u.setNickName(register_user.getNickName());
							u.setPassWord(userPwd_str);
							u.setSex(register_user.getSex());
							u.setName(register_user.getName());
							u.setCharacterName(register_user.getCharacterName());
							u.setAge(register_user.getAge());
							u.setAddtime(register_user.getAddtime());
							u.setType(register_user.getType());
							u.setImgUrl(register_user.getImgUrl());
							u.setLog(register_user.getLog());
							u.setDim(register_user.getDim());
							u.setPhone(register_user.getPhone());
							u.setEmail(register_user.getEmail());

							JSONArray array = JSONArray.fromObject(u);
							result = "{\"responseCode\":\"" + 0 + "\",\"results\":"
									+ array.toString() + "}";
						} else {
							result = "{\"responseCode\":\"" + 1 + "\"}";
						}
					} else {
						result = "{\"responseCode\":\"" + 3 + "\"}";
					}
				} else {
					result = "{\"responseCode\":\"" + 1 + "\"}";
				}
			}
			
		} catch (Exception e) {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 升级版 客户端用户注册
	 * 
	 * @return
	 */
	public void registerUser() {

		String userName_str = request.getParameter("userName");
		String userPwd_str = request.getParameter("userPwd");
		String sex_str = request.getParameter("sex");
		String email_str = request.getParameter("email");
		String headIco_str = request.getParameter("he adIco");

		System.out.println("---userName_str---->" + userName_str
				+ "<--------------");
		System.out.println("---userPwd_str---->" + userPwd_str
				+ "<--------------");
		System.out.println("---sex_str---->" + sex_str + "<--------------");
		System.out.println("---email_str---->" + email_str + "<--------------");
		System.out.println("---email_str---->" + email_str + "<--------------");
		System.out.println("---headIco_str---->" + headIco_str
				+ "<--------------");

		/*
		 * 判断是QQ注册还是普通注册 1、如果传递的头像不为空，说明是QQ注册
		 */
		if (userName_str != null && email_str != null) {
			boolean torf = BaseUtils.isChinaese(userName_str);
			if (torf) {
				try {
					// 用于IOS客户端传递过来的用户名
					userName_str = new String(
							userName_str.getBytes("ISO-8859-1"), "UTF-8");
					System.out.println("---isChinaese---userName_str--IOS-->"
							+ userName_str + "<--------------");
					// 用于网页传递过来的用户名
					// userName = new String(userName.getBytes("ISO-8859-1"));
					// System.out.println("---isChinaese---userName--IE-->"+userName+"<--------------");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				userName_str = userName_str + "";
			}
			System.out.println("--torf---userName_str--->" + userName_str+ "<--------------");

			/**
			 * 普通用户注册
			 */
			if ((userPwd_str != null && !userPwd_str.isEmpty()) && headIco_str.isEmpty()) {
				boolean flag_email = userService.isEmail(email_str);
				if (!flag_email) {
					boolean flag = userService.isUser(userName_str);
					if (!flag) {
						sex = sex_str != null ? sex_str : "1";// 1 男 2女
						// TODO 修改默认头像
						headIco_str = headIco_str != null ? headIco_str
								: defaultImgUrl;

						User user = new User();
						user.setNickName(userName_str);
						user.setPassWord(PiaoJuTong.Md5(userPwd_str));
						user.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
						user.setEmail(email_str);
						user.setSex(Integer.parseInt(sex));
						user.setAge(20);// default age
						user.setImgUrl(headIco_str);// default imgUrl

						User register_user = userService.registerUserInfo(user);

						if (register_user != null) {
							User u = new User();
							u.setId(register_user.getId());
							u.setNickName(register_user.getNickName());
							u.setPassWord(userPwd_str);
							u.setSex(register_user.getSex());
							u.setName(register_user.getName());
							u.setCharacterName(register_user.getCharacterName());
							u.setAge(register_user.getAge());
							u.setAddtime(register_user.getAddtime());
							u.setType(register_user.getType());
							u.setImgUrl(register_user.getImgUrl());
							u.setLog(register_user.getLog());
							u.setDim(register_user.getDim());
							u.setPhone(register_user.getPhone());
							u.setEmail(register_user.getEmail());

							JSONArray array = JSONArray.fromObject(u);
							result = "{\"responseCode\":\"" + 0
									+ "\",\"results\":" + array.toString()
									+ "}";
						} else {
							result = "{\"responseCode\":\"" + 1 + "\"}";
						}
					} else {
						result = "{\"responseCode\":\"" + 2 + "\"}";
					}
				} else {
					result = "{\"responseCode\":\"" + 3 + "\"}";
				}
			} 
			/*
			 * QQ 用户注册
			 */
			else if (headIco_str != null && (userPwd_str == null|| userPwd_str.isEmpty())) {
				
				boolean flag_email = userService.isEmail(email_str);
				boolean flag_name = userService.isUser(userName_str);
				//如果验证email和name都存在，则说名已经存在了对应QQ的消息，否则的话，进行注册
				if(flag_email == true && flag_name == true){
					User user = userService.getUserInfo(userName_str, email_str);

					if(user!=null){
						JSONArray array = JSONArray.fromObject(user);
						result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
					}else{
						result = "{\"responseCode\":\"" + 1 + "\"}";
					}
				}else{
					System.out.println("3");
					sex = sex_str != null ? sex_str : "1";// 1 男 2女
					// TODO 修改默认头像
					headIco_str = headIco_str != null ? headIco_str : defaultImgUrl;

					User user = new User();
					user.setNickName(userName_str);
					user.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
					user.setEmail(email_str);
					user.setSex(Integer.parseInt(sex));
					user.setAge(20);// default age
					user.setImgUrl(headIco_str);// default imgUrl

					User register_user = userService.registerUserInfo(user);

					if (register_user != null) {
						User u = new User();
						u.setId(register_user.getId());
						u.setNickName(register_user.getNickName());
						u.setPassWord(userPwd_str);
						u.setSex(register_user.getSex());
						u.setName(register_user.getName());
						u.setCharacterName(register_user.getCharacterName());
						u.setAge(register_user.getAge());
						u.setAddtime(BaseUtils.getNowStringDateTime(register_user.getAddtime()));
						u.setType(register_user.getType());
						u.setImgUrl(register_user.getImgUrl());
						u.setLog(register_user.getLog());
						u.setDim(register_user.getDim());
						u.setPhone(register_user.getPhone());
						u.setEmail(register_user.getEmail());

						JSONArray array = JSONArray.fromObject(u);
						result = "{\"responseCode\":\"" + 0 + "\",\"results\":"
								+ array.toString() + "}";
					} else {
						result = "{\"responseCode\":\"" + 1 + "\"}";
					}
				}
			}
		} else {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}

		BaseUtils.responseInfo(result);
	}
	/**
	 * 访问图片
	 * @return
	 */
	public String visitImage()
	{
		String image = request.getParameter("iamge");
		
//		QQ20130730153059.png
		return SUCCESS;
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public User getModel() {
		return model;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getPagesum() {
		return pagesum;
	}

	public void setPagesum(int pagesum) {
		this.pagesum = pagesum;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
