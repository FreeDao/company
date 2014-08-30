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
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>,
ServletRequestAware{

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
	
	private String userName;//用户名
	private String userPwd;//密码
	private String email;
	private String sex;
	private String result;//用于组装json
	private String responseCode;//响应参数
	
	/**
	 * 查询所有的用户
	 * @return
	 */
	public String selUser()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		pagecount = userService.numberUser(request.getParameter("id"),request.getParameter("userNames"));
		if(pagecount<15){
			pageCount=1;
		}else{
			pageCount = pagecount/15;
			int i  = pagecount%15;
			if(i>0)
			{
				pageCount+=1;
			}
		}
		if (pagenum < 1) {
			pagenum = 1;
		}
		if (pagenum > pagecount) {
			if(pagecount == 0)
			{
				
			}
			else
			{
				pagenum = pageCount;
			}
		}
		list = userService.selUser(request.getParameter("id"),request.getParameter("userNames"),pagenum,15);
		return SUCCESS;
	}
	
	/**
	 * 删除用户
	 * @return
	 * @throws IOException 
	 */
	public String delUser() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = userService.delUser(Integer.valueOf(request.getParameter("did")));
		if(bool)
		{
			selUser();
		}
		else
		{
			alertInFo.alertReturn("删除失败！");
		}
		return SUCCESS;
	}

	
	/**
	 * TODO 客户端用户登录
	 * @return
	 */
	public void loginUserInfo(){
		System.out.println("---userName---->"+userName+"<--------------");
		System.out.println("---userPwd---->"+userPwd+"<--------------");
		
		if(userName !=null && userPwd !=null){
			//处理用户名
			boolean torf = BaseUtils.isChinaese(userName);
			System.out.println("torf--->"+torf+"<---");
			if(torf){
				try {
					//用于IOS客户端传递过来的用户名
					userName = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
					System.out.println("---isChinaese---userName--IOS-->"+userName+"<--------------");
					//用于网页传递过来的用户名
//					userName = new String(userName.getBytes("ISO-8859-1"));
//					System.out.println("---isChinaese---userName--IE-->"+userName+"<--------------");
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}else{
				userName = userName;
			}			
			User user = userService.loginUserInfo(userName, userPwd);
			if(user!=null){
				JSONArray array = JSONArray.fromObject(user);
				result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
			}else{
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		}else{
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}
	
	
	/**
	 * TODO 客户端用户注册
	 * @return
	 */
	public void registerUserInfo(){
		System.out.println("---userName---->"+userName+"<--------------");
		System.out.println("---userPwd---->"+userPwd+"<--------------");
		System.out.println("---sex---->"+sex+"<--------------");
		System.out.println("---email---->"+email+"<--------------");
		
		String sex_str = request.getParameter("sex");
		String email_str = request.getParameter("email");
		
		System.out.println("---sex_str---->"+sex_str+"<--------------");
		System.out.println("---email_str---->"+email_str+"<--------------");
		
		if(userName != null && userPwd != null && email_str !=null){
			boolean torf = BaseUtils.isChinaese(userName);
			if(torf){
				try {
					//用于IOS客户端传递过来的用户名
					userName = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
					System.out.println("---isChinaese---userName--IOS-->"+userName+"<--------------");
					//用于网页传递过来的用户名
//					userName = new String(userName.getBytes("ISO-8859-1"));
//					System.out.println("---isChinaese---userName--IE-->"+userName+"<--------------");
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}else{
				userName = userName;
			}	
			System.out.println("--torf---userName--->"+userName+"<--------------");

			boolean flag_email = userService.isEmail(email_str);
			if(!flag_email){
				boolean flag = userService.isUser(userName);
				if(!flag){
					sex = sex!=null ? sex:"1";//1 男 2女
					email = email!=null ? email:email_str;	

					User user = new User();
					user.setNickName(userName);
					user.setPassWord(userPwd);
					user.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
					user.setEmail(email);
					user.setSex(Integer.parseInt(sex));
					user.setAge(20);//default age
//					user.setImgUrl("");//default imgUrl
					
					User register_user = userService.registerUserInfo(user);
					
					if (register_user != null){
						JSONArray array = JSONArray.fromObject(register_user);
						result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
					}else{
						result = "{\"responseCode\":\"" + 1 + "\"}";
					}
				}else {
					result = "{\"responseCode\":\"" + 2 + "\"}";
				}
			}else{
				result = "{\"responseCode\":\"" + 3 + "\"}";
			}
		}else{
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}
	
	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	@Override
	public User getModel()
	{
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
