package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Address;
import com.era.orm.Consumption;
import com.era.orm.Custom;
import com.era.orm.Message;
import com.era.orm.Order;
import com.era.orm.ShopType;
import com.era.orm.ShopTypeTwo;
import com.era.orm.User;
import com.era.service.UserService;
import com.era.util.BaseUtils;
import com.era.util.Client;
import com.era.util.LoginUser;
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
	private String rows; 
	private String page;

	HttpServletRequest request;

	User model = new User();
	private Map<String,Object> map  = new HashMap<String,Object>();
	private String userName;// 用户名
	private String userPwd;// 密码
	private String email;
	private String sex;
	private String defaultImgUrl = "http://imgstatic.baidu.com/img/image/shouye/wuqilong.jpg";
	
	private File picture;
	private String pictureContentType;
	private String pictureFileName;

	/**
	 * 查询所有的用户
	 * 
	 * @return
	 */
	public String selUser() 
	{
		list = userService.selUser(request.getParameter("name"),"0",
				null, Integer.valueOf(page),Integer.valueOf(rows));
   	 	int total=userService.numberUser(request.getParameter("name"),"0",null);
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	/**
	 * 查询所有的用户
	 * 
	 * @return
	 */
	public String selUserSeller() 
	{
		list = userService.selUser(request.getParameter("name"),"1",
				null, Integer.valueOf(page),Integer.valueOf(rows));
   	 	int total=userService.numberUser(request.getParameter("name"),"1",null);
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	/**
	 * 查询小区主
	 * @return
	 */
	public String selUserCell()
	{
		list = userService.selUser(request.getParameter("name"),"2",
				null, Integer.valueOf(page),Integer.valueOf(rows));
   	 	int total=userService.numberUser(request.getParameter("name"),"2",null);
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	
	/**
	 * 用户查询地址
	 * @return
	 */
	public String selAddress()
	{
		try {
			List<Address> list = userService.selAddress(request.getParameter("name"),Integer.valueOf(page),Integer.valueOf(rows));
			int total=userService.numberAddress(request.getParameter("name"));
			 map.put("total", total);
		   	 	map.put("rows", list);
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询邻居异常");
		}
		
		return SUCCESS;
	}
	/**
	 * 删除地址
	 * @return
	 */
	public String delAddress()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		userService.delAddress(Integer.parseInt(ids[i])); 
        } 
		return SUCCESS;
	}
	
	/**
	 * 删除用户
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delUser() throws IOException {
		
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		userService.delUser(Integer.parseInt(ids[i])); 
        } 
		return null;
	}

	/**
	 * TODO 客户端用户登录
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String loginUser() throws UnsupportedEncodingException {
		try {
			String userName_str = request.getParameter("userName");
			String userPwd_str = request.getParameter("userPwd");
			String seller = request.getParameter("seller");
					if (BaseUtils.isChinaese(userName_str)) {
							userName_str = new String(
									userName_str.getBytes("ISO-8859-1"), "UTF-8");
					} else {
						userName_str = userName_str+"";
					}
					User user = userService.loginUserInfo(userName_str,
							PiaoJuTong.Md5(userPwd_str),seller);
					if(user == null ||user.equals(""))
					{
						map.put("responseCode", "1");
						map.put("msg", "未找到用户名");
					}
					else
					{
						map.put("user",user);
					}
					request.getSession().setAttribute("adminname",userName_str);
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "登陆异常");
		}
		return SUCCESS;
	}

	/**
	 * TODO 客户端用户注册
	 * 
	 * @return
	 *//*
	public String register() {
		
		try {
			String userName_str = request.getParameter("userName");
			String userPwd_str = request.getParameter("userPwd");
			String sex_str = request.getParameter("sex");
			String headIco_str = "";
			
			boolean flag_email = userService.isEmail(userName_str);
			if (!flag_email) {
				
				String sn = "SDK-DLS-010-00484";
				String pwd = "143146";
				Client client = new Client(sn, pwd);
				String yzm = BaseUtils.RandomString();
				String result=client.mt(request.getParameter("iphone"),"您好，注册码验证码为："+yzm+"【同城生活圈】","","","");
				if(result.length()>8)
				{
					sex = sex_str != null ? sex_str : "1";// 1 男 2女
					// TODO 修改默认头像
					headIco_str = headIco_str != null ? headIco_str
							: defaultImgUrl;
					User user = new User();
					user.setEmail(userName_str);
					user.setPassWord(PiaoJuTong.Md5(userPwd_str));
					user.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
					user.setSex(Integer.parseInt(sex));
					user.setAge(20);// default age
					user.setImgUrl(headIco_str);// default imgUrl
					if(request.getParameter("isNo") == null || request.getParameter("isNo").equals(""))
					{
						
					}
					else
					{
						user.setSellIsNo(Integer.valueOf(request.getParameter("isNo")));
					}
					User register_user = userService.registerUserInfo(user);
					map.put("user", register_user);
					map.put("code",yzm);
				} else {
					map.put("msg", "发送验证码异常");
					map.put("responseCode", "1");
				}
			} 
			else
			{
				map.put("msg", "用户名重复");
				map.put("responseCode", "1");
			}
			
					
			
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "注册异常");
		}
		return SUCCESS;
	}*/
	/**
	 * 我的邻居
	 * @return
	 */
	public String myGuarantee()
	{
		try {
			String tee = request.getParameter("villageId");
			String log = request.getParameter("log");
			String dim = request.getParameter("dim");
			String pageNo = request.getParameter("pageNo");
			String pageNum = request.getParameter("pageNum");
			List<User> user = userService.selGuarantee(tee,log,dim,pageNo,pageNum);
			if(user == null ||user.equals(""))
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", user);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询邻居异常");
		}
		return SUCCESS;
	}
	/**
	 * 更新小区到用户
	 * @return
	 */
	public String updateUser()
	{
		try {
			String id=request.getParameter("id");//用户ID
			String villageId = request.getParameter("villageId");//小区ID
			String log = request.getParameter("log");//小区ID
			String dim = request.getParameter("dim");//小区ID
			boolean bool = userService.updateUser(id,villageId,log,dim);
			if(bool)
			{
				map.put("responseCode", "0");
			}
			else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询邻居异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 添加商户
	 * @return
	 * @throws IOException 
	 
	public String addUser() throws IOException
	{
		try {
			User user = new User();
			if(request.getParameter("id") == null || request.getParameter("id").equals(""))
			{
				
			}
			else
			{
				user.setId(Integer.valueOf(request.getParameter("id")));
			}
			user.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			if(request.getParameter("age") == null || request.getParameter("age").equals(""))
			{
				user.setAge(0);
			}
			else
			{
				user.setAge(Integer.valueOf(request.getParameter("age")));
			}
			if(request.getParameter("address") == null || request.getParameter("address").equals(""))
			{
				
			}
			else
			{
				Map<String, String> json = alertInFo.getGeocoderLatitude(request.getParameter("address"));
				if(json == null || json.equals(""))
				{
					alertInFo.alertReturn("地图不存在此地址!");
					return SUCCESS;
				}
				user.setLog(json.get("lng"));
				user.setDim(json.get("lat"));
			}
			user.setEmail(request.getParameter("email"));
			if(getPictureFileName() != null)
			{
				String StreamFos = getPictureFileName().substring(getPictureFileName().indexOf("."));
				long picturefis = getPicture().length();
				if (picturefis > 100000) {
				alertInFo.alertReturn("你上传的文件过大！");
				return SUCCESS;
				}

				if (!StreamFos.equals(".gif") && !StreamFos.equals(".jpg")
				&& !StreamFos.equals(".bmp")) {
				alertInFo.alertReturn("你上传的文件过大！");
				return SUCCESS;
				}

				FileOutputStream fos = new FileOutputStream(ServletActionContext.getRequest().getRealPath(
				"/images" + "/"+getPictureFileName()));
				
				FileInputStream fis = new FileInputStream(getPicture());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
						}
				user.setImgUrl("http://localhost:8080/City/images/"+getPictureFileName());
			}
			else
			{
				user.setImgUrl("");
			}
			if(request.getParameter("sellerIt") == null || request.getParameter("sellerIt").equals(""))
			{
				
			}
			else
			{
				user.setSellIsNo(Integer.valueOf(request.getParameter("sellerIt")));
			}
			
			
			user.setNickName(request.getParameter("nickName"));
			user.setName(request.getParameter("name"));
			user.setPassWord(PiaoJuTong.Md5(request.getParameter("passWord")));
			if(request.getParameter("householder") == null || request.getParameter("householder").equals(""))
			{
				user.setHouseholder(0);
			}
			else
			{
				user.setHouseholder(Integer.valueOf(request.getParameter("householder")));
			}
			
			if(request.getParameter("number") == null || request.getParameter("number").equals(""))
			{
				user.setNumber(1);
			}
			else
			{
				user.setNumber(Integer.valueOf(request.getParameter("number")));
			}
			user.setDoorplate(request.getParameter("doorplate"));
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	*/
	/**
	 * 添加收货地址
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addAddress() throws UnsupportedEncodingException
	{
		try {
			Address add = new Address();
			String area = "";//小区ID
			String address = "";//小区ID
			String iphone = request.getParameter("iphone");//小区ID
			String email = request.getParameter("email");//小区ID
			String code = request.getParameter("code");//小区ID
			String userId = request.getParameter("userId");//小区ID
			
			if ( BaseUtils.isChinaese(request.getParameter("name"))) {
				new String(
					request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				request.getParameter("name");
			}
			if ( BaseUtils.isChinaese(request.getParameter("name"))) {
				// 用于IOS客户端传递过来的用户名
				area = new String(
						request.getParameter("area").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				address = request.getParameter("area");
			}
			if ( BaseUtils.isChinaese(request.getParameter("name"))) {
				// 用于IOS客户端传递过来的用户名
				area = new String(
						request.getParameter("address").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				address = request.getParameter("address");
			}
			add.setAddress(address);
			add.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			add.setCode(code);
			add.setArea(area);
			add.setEmali(email);
			add.setIphone(iphone);
			add.setUserId(Integer.valueOf(userId));
			boolean bool = userService.addAddress(add);
			if(bool)
			{
				map.put("responseCode", "0");
			}
			else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "添加地址异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 生成订单
	 * @return
	 */
	public String addOrder()
	{
		try {
			Order order = new Order();
			order.setAddressId(Integer.valueOf(request.getParameter("addressId")));
			order.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			order.setPrice(request.getParameter("price"));
			order.setShopId(request.getParameter("shopId"));
			order.setYf(request.getParameter("yf"));//运费
			boolean bool = userService.addOrder(order);
			if(bool)
			{
				map.put("responseCode", "0");
			}
			else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "生成订单异常");
		}
		return SUCCESS;
	}
	/**
	 * 聊天
	 * @return
	 */
	public String addMessage()
	{
		try {
			Message message = new Message();
			message.setTitle(request.getParameter("title"));
			message.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			message.setUserId(Integer.valueOf(request.getParameter("userId")));
			message.setFriendId(Integer.valueOf(request.getParameter("friendId")));
			boolean bool = userService.addMessage(message);
			if(bool)
			{
				map.put("responseCode", "0");
				map.put("message",message);
			}
			else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "发送消息异常");
		}
		return SUCCESS;
	}
	/**
	 * 我的消息
	 * @return
	 */
	public String myMessage()
	{
		try {
			List<Message> list = userService.selMyMessage(request.getParameter("userId"));
			if(list.size()<1)
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询邻居异常");
		}
		
		return SUCCESS;
	}
	/**
	 * 物业客服
	 * @return
	 */
	public String selCustom()
	{
		try {
			String village = request.getParameter("villageId");
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			if( lu.getRole() == 5){
				village = lu.getVillageId()+"";
			}
			List<Custom> list = userService.selCustom(village, request.getParameter("page"), request.getParameter("rows"));
			Integer total = userService.selCustomTotal(village);
			map.put("rows", list);
			map.put("total", total);
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询邻居异常");
		}
		return SUCCESS;
	}
	/**
	 * 我要消费
	 * @return
	 */
	public String myConsumption()
	{
		try {
			User user = userService.getUserInfo(request.getParameter("userName"),request.getParameter("passWord"));
			if(user == null ||user.equals(""))
			{
				map.put("responseCode", "1");
				map.put("msg", "未找到商户");
			}
			else
			{
				Consumption con = new Consumption();
				con.setMoney(Integer.valueOf(request.getParameter("money")));
				con.setSellId(user.getId());
				boolean bool = userService.addConsumption(con);
				if(bool)
				{
					boolean ban = userService.updateUserMoney(Integer.valueOf(request.getParameter("money")),user.getId());
					if(ban)
					{
						map.put("responseCode", "0");
						map.put("msg", "消费成功");
					}
					else
					{
						map.put("msg", "消费异常");
						map.put("responseCode", "1");
					}
				}
				else
				{
					map.put("responseCode", "1");
					map.put("msg", "消费异常");
				}
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "消费异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有订单
	 * @return
	 */
	public String selOrder()
	{
		String st = null;
		if(request.getParameter("state") == null || request.getParameter("state").equals(""))
		{
			
		}
		else
		{
			if(Integer.valueOf(request.getParameter("state")) == 2)
			{
				st = "已下单";
			}
			if(Integer.valueOf(request.getParameter("state")) == 1)
			{
				st = "正在送货";
			}
			if(Integer.valueOf(request.getParameter("state")) == 3)
			{
				st = "完成";
			}
		}
		List<Object> list = userService.selOrder(request.getParameter("buy"),st,request.getParameter("addtime"),request.getParameter("overtimes"), Integer.valueOf(page),Integer.valueOf(rows));
   	 	int total=userService.numberOrder(request.getParameter("buy"),st,request.getParameter("addtime"),request.getParameter("overtimes"));
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	/**
	 * 删除订单
	 * @return
	 */
	public String delOrder()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		userService.delOrder(Integer.parseInt(ids[i])); 
        } 
		return SUCCESS;
	}
	/**
	 * 商城一级分类
	 * @return
	 */
	public String selShoptype()
	{
		List<ShopType> list = userService.selShoptype(request.getParameter("name"), Integer.valueOf(page),Integer.valueOf(rows));
		int total=userService.numberShoptype(request.getParameter("name"));
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	/**
	 * 删除一级分类
	 * @return
	 */
	public String delShoptype()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		userService.delShoptype(Integer.parseInt(ids[i])); 
        } 
		return SUCCESS;
	}
	/**
	 * 添加一级栏目
	 * @return
	 */
	public String addShoptype()
	{
		ShopType shop = new ShopType();
		shop.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
		shop.setType(request.getParameter("name"));
		userService.addShoptype(shop);
		return SUCCESS;
	}
	/**
	 * 查询全部一级栏目
	 * @return
	 */
	public String selShoptypeAll()
	{
		List<ShopType> list = userService.selShoptypeAll();
		map.put("msg", list);
		return SUCCESS;
	}
	/**
	 * 查询二级栏目
	 * @return
	 */
	public String selShoptypetwo()
	{
		List<ShopTypeTwo> list = userService.selShoptypetwo(request.getParameter("ShopTypeId"),request.getParameter("name"), Integer.valueOf(page),Integer.valueOf(rows));
		int total=userService.numberShoptypetwo(request.getParameter("ShopTypeId"),request.getParameter("name"));
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}
	/**
	 * 删除二级分类
	 * @return
	 */
	public String delShoptypetwo()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		userService.delShoptypetwo(Integer.parseInt(ids[i])); 
        } 
		return SUCCESS;
	}
	/**
	 * 添加二级栏目
	 * @return
	 */
	public String addShoptypetwo()
	{
		ShopTypeTwo two = new ShopTypeTwo();
		two.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
		two.setName(request.getParameter("name"));
		two.setShopTypeId(Integer.valueOf(request.getParameter("shopTypeId")));
		userService.addShoptypetwo(two);
		return SUCCESS;
	}
	/**
	 * 查询小区
	 * @return
	 */
	public String selvideo()
	{
		
		return SUCCESS;
	}
	
	public String selVideo()
	{
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

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	public File getPicture() {
		return picture;
	}
	public void setPicture(File picture) {
		this.picture = picture;
	}
	public String getPictureContentType() {
		return pictureContentType;
	}
	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}
	public String getPictureFileName() {
		return pictureFileName;
	}
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

}
