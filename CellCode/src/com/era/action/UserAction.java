package com.era.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Address;
import com.era.orm.BusinessMan;
import com.era.orm.Consumption;
import com.era.orm.Custom;
import com.era.orm.Images;
import com.era.orm.Mall;
import com.era.orm.Message;
import com.era.orm.Order;
import com.era.orm.SystemAgrs;
import com.era.orm.User;
import com.era.service.UserService;
import com.era.util.BaseUtils;
import com.era.util.Client;
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
	private Map<String,Object> map  = new HashMap<String,Object>();
	private String userName;// 用户名
	private String userPwd;// 密码
	private String email;
	private String sex;
	private String defaultImgUrl = "http://imgstatic.baidu.com/img/image/shouye/wuqilong.jpg";

	private List<File> file = new ArrayList<File>();
	private List<String> fileContentType;
	private List<String> fileFileName;
	
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

	public String updateAvatars(){
		try {
			
			String id = request.getParameter("id");
			StringBuffer url = request.getRequestURL();
			String projectUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
			File img = file.get(0);
			String contentType = fileContentType.get(0).substring(fileContentType.get(0).indexOf("/")+1);
			if("pjpeg".equals(contentType)){
				contentType = "jpg";
			}
			String filepath=request.getRealPath("/uploadImgs");
			//图片上传
			String temp = BaseUtils.Md5(img);
			File f = new File(filepath+"/"+temp+"."+contentType);
			if(!f.exists()){
				f.createNewFile();
				FileUtils.copyFile(img, f);
			}
			String logo = projectUrl + "/uploadImgs/"+temp+"."+contentType;
			
			User u = userService.updateAvatars(id,logo,map);
			
			map.put("user", u);
			map.put("responseCode", "0");
			map.put("msg", "更新成功");
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("responseCode", "1");
			map.put("msg", "更新失败");
		}
		return SUCCESS;
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
					/*if(Integer.valueOf(seller) == 3)
					{
						BusinessMan man = userService.loginUserMan(user.getId(),request.getParameter("manUserName"),PiaoJuTong.Md5(request.getParameter("manUserPwd")));
						if(man == null ||man.equals(""))
						{
							
						}
						else
						{
							map.put("man",man);
						}
					}*/
					if(user == null)
					{
						map.put("responseCode", "1");
						map.put("msg", "未找到用户名");
					}
					else
					{
						map.put("list",user);
						map.put("responseCode", "0");
						map.put("msg", "登陆成功");
					}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("responseCode", "1");
			map.put("msg", "登陆异常");
		}
		return SUCCESS;
	}
	/**
	 * 发送验证码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String sendCode() throws UnsupportedEncodingException
	{
		boolean flag_email = userService.isEmail(request.getParameter("iphone"));
		if(flag_email){
			map.put("msg", "用户已存在");
			map.put("responseCode", "1");
			return SUCCESS;
		}
		
		String sn = "SDK-DLS-010-00484";
		String pwd = "143146";
		Client client = new Client(sn, pwd);
		String yzm = BaseUtils.RandomString();
		String result=client.mt(request.getParameter("iphone"),"您好，注册码验证码为："+yzm+"【小区助手】","","","");
		if(result.length()>8)
		{
			map.put("code",yzm);
			map.put("msg", "发送成功");
			map.put("responseCode", "0");
		}
		else
		{
			map.put("msg", "发送失败");
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	/**
	 * TODO 客户端用户注册
	 * 
	 * @return
	 */
	public String register() {
		
		try {
			String userName_str = request.getParameter("userName");
			String iphone = request.getParameter("iphone");
			String userPwd_str = request.getParameter("userPwd");
			String recommended = request.getParameter("recommended");
			String sex_str = request.getParameter("sex");
			String headIco_str = "";
			String villageId = request.getParameter("villageId");
			
			boolean flag_email = userService.isEmail(iphone);
			if (!flag_email) {
					sex = sex_str != null ? sex_str : "1";// 1 男 2女updateInfo
					// TODO 修改默认头像
					headIco_str = headIco_str != null ? headIco_str
							: defaultImgUrl;
					User user = new User();
					if(recommended != null && !recommended.equals(""))
					{
						user.setIntegral(100);
						userService.recommendIntegral(recommended);
					}
					user.setEmail(iphone);
					user.setPassWord(PiaoJuTong.Md5(userPwd_str));
					user.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
					user.setSex(Integer.parseInt(sex));
					//user.setAge(20);// default age
					user.setNickName(BaseUtils.changeIos8859ToUtf8(userName_str));
					user.setSellIsNo(0);
					user.setDim("");
					user.setDoorplate("");
					user.setSex(0);
					user.setType("");
					user.setPhone("");
					user.setVillageId(0);
					if( null != villageId && !"".equals(villageId)){
						user.setVillageId(Integer.parseInt(villageId));
					}
					user.setVillageName("0");
					user.setSellerPassWord("");
					user.setManId(0);
					user.setImgUrl(headIco_str);// default imgUrl
					if(request.getParameter("isNo") == null || request.getParameter("isNo").equals(""))
					{
						
					}
					else
					{
						user.setSellIsNo(Integer.valueOf(request.getParameter("isNo")));
					}
					User register_user = userService.registerUserInfo(user);
					map.put("list", register_user);
					map.put("msg", "注册成功");
					map.put("responseCode", "0");
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
	}
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
	 * 添加收货地址
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addAddress() throws UnsupportedEncodingException
	{
		try {
			Address add = new Address();
			String name="";//用户ID
			String area = "";//小区ID
			String address = "";//小区ID
			String iphone = request.getParameter("iphone");//小区ID
			String email = request.getParameter("email");//小区ID
			String code = request.getParameter("code");//小区ID
			String userId = request.getParameter("userId");//小区ID
			if(request.getParameter("id")==null || request.getParameter("id").equals(""))
			{
				
			}
			else
			{
				add.setId(Integer.valueOf(request.getParameter("id")));
			}
			if ( BaseUtils.isChinaese(request.getParameter("name"))) {
				// 用于IOS客户端传递过来的用户名
				name = new String(
					request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				name = request.getParameter("name");
			}
			if ( BaseUtils.isChinaese(request.getParameter("name"))) {
				// 用于IOS客户端传递过来的用户名
				area = new String(
						request.getParameter("area").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				area = request.getParameter("area");
			}
			if ( BaseUtils.isChinaese(request.getParameter("name"))) {
				// 用于IOS客户端传递过来的用户名
				address = new String(
						request.getParameter("address").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				address = request.getParameter("address");
			}
			add.setName(name);
			add.setAddress(address);
			add.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			if(code == null || code.equals(""))
			{
				add.setCode("");
			}
			else
			{
				add.setCode(code);
			}
			if(email == null || email.equals(""))
			{
				add.setEmali("");
			}
			else
			{
				add.setEmali(email);
			}
			add.setThedefault(0);
			add.setArea(area);
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
	 * 用户查询地址
	 * @return
	 */
	public String selAddress()
	{
		try {
			String pageNo = request.getParameter("pageNo");
			String pageNum = request.getParameter("pageNum");
			List<Address> list = userService.selAddress(request.getParameter("userId"),request.getParameter("thedefault"),pageNo,pageNum);
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
			map.put("msg", "查询地址异常");
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
			String shopId = request.getParameter("shopId");
			JSONArray ja = JSONArray.fromObject(shopId);
			for(int i = 0 ; i < ja.size(); i++){
				JSONObject jo = ja.getJSONObject(i);
				
				Order order = new Order();
				order.setShopId(jo.getString("shopId"));
				order.setNumber(jo.getInt("number"));
				order.setAddressId(Integer.valueOf(request.getParameter("addressId")));
				order.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
				order.setPrice(request.getParameter("price"));
				order.setBuy(1);
				if(request.getParameter("yf") == null || request.getParameter("yf").equals(""))
				{
					order.setYf("0");//运费
				}
				else
				{
					order.setYf(request.getParameter("yf"));//运费
				}
				if(request.getParameter("buy") == null || request.getParameter("buy").equals(""))
				{
					
				}
				else
				{
					order.setBuy(Integer.valueOf(request.getParameter("buy")));
				}
				order.setState("订单已提交");
				order.setUserId(Integer.valueOf(request.getParameter("userId")));
				userService.addOrder(order);
			}
			map.put("responseCode", "0");
			map.put("msg", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
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
			List<Message> list = userService.selMyMessage(request.getParameter("userId"),request.getParameter("friendId"));
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
			List<Custom> list = userService.selCustom(request.getParameter("villageId"), request.getParameter("pageNo"), request.getParameter("pageNum"));
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
	 * 我要消费
	 * @return
	 */
	public String myConsumption()
	{
		try {
			String winId = request.getParameter("winId");
			if( null != winId && !"".equals(winId)){
				userService.useWin(winId);
			}
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
				con.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
				con.setUserId(Integer.valueOf(request.getParameter("userId")));
				User us = userService.seluserId(Integer.valueOf(request.getParameter("userId")));
				con.setUserName(us.getNickName());
				con.setIphone(us.getEmail());
				con.setLog(us.getImgUrl());
				con.setConsumption(Integer.valueOf(request.getParameter("consumption")));
				boolean bool = userService.addConsumption(con);
				if(bool)
				{
					Integer total = userService.updateUserMoney(Integer.valueOf(request.getParameter("money")),us.getId());
					if(null != total)
					{
							map.put("responseCode", "0");
							map.put("msg", "消费成功,获得"+request.getParameter("money")+"积分");
							map.put("total", total);
					}
					else
					{
						map.put("msg","消费成功,.未获得积分");
						map.put("responseCode", "0");
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
	 * 查询用户通过id
	 * @return
	 */
	public String selUserById(){
		try {
			String id = request.getParameter("id");
			User u = userService.selUserById(id);
			map.put("list", u);
			map.put("responseCode", "0");
			map.put("msg", "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			map.put("responseCode", "1");
			map.put("msg", "查询失败");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 更新个人信息
	 * @return
	 */
	public String updateInfo()
	{
		try {
			String id = request.getParameter("id");
			String nickName = BaseUtils.changeIos8859ToUtf8(request.getParameter("nickName"));
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			String iphone = request.getParameter("iphone");
			String villageId = request.getParameter("villageId");
			String doorplate = BaseUtils.changeIos8859ToUtf8(request.getParameter("doorplate"));//门牌
			String emotion = BaseUtils.changeIos8859ToUtf8(request.getParameter("emotion"));//感情状态
			String job = BaseUtils.changeIos8859ToUtf8(request.getParameter("job"));//职业
			String interest = BaseUtils.changeIos8859ToUtf8(request.getParameter("interest"));//兴趣爱好
			String interestplace = BaseUtils.changeIos8859ToUtf8(request.getParameter("interestplace"));//常去地方
			String signature = BaseUtils.changeIos8859ToUtf8(request.getParameter("signature"));//个性签名
			
			String name = BaseUtils.changeIos8859ToUtf8(request.getParameter("name"));
			String householder = request.getParameter("householder");//（1户主2租户3家属）
			
			boolean bool = userService.updateUserInfo(id,iphone,nickName,name,villageId,householder,doorplate,age,emotion,job,interest,interestplace,signature,sex);
			if(bool)
			{
				String logo = null;
				StringBuffer url = request.getRequestURL();
				String projectUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
				int i = 0;
				for(File img : file){
					String contentType = fileContentType.get(i).substring(fileContentType.get(i).indexOf("/")+1);
					if("pjpeg".equals(contentType)){
						contentType = "jpg";
					}
					String filepath=request.getRealPath("/uploadImgs");
					//图片上传
					String temp = BaseUtils.Md5(img);
					File f = new File(filepath+"/"+temp+"."+contentType);
					if(!f.exists()){
						f.createNewFile();
						FileUtils.copyFile(img, f);
					}
					if(i == 0){
						logo = projectUrl + "/uploadImgs/"+temp+"."+contentType;
					}else{
						Images images = new Images();
						images.setCompositeId(Integer.parseInt(id));
						images.setImgUrl(projectUrl + "/uploadImgs/"+temp+"."+contentType);
						images.setType(4);
						userService.saveOrUpdate(images);
					}
					i++;
				}
				
				if( null == logo){
					logo = "";
				}
				User u = userService.updateLogo(id,logo);
				map.put("list", u);
				map.put("responseCode", "0");
				map.put("msg", "更新成功");
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "更新失败");
			}
			
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "更新失败");
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 邀请好友
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String invitationFriends() throws UnsupportedEncodingException
	{
		try {
			String iphone = request.getParameter("iphone");
			String name = "";
			String sn = "SDK-DLS-010-00484";
			String pwd = "143146";
			Client client = new Client(sn, pwd);
			if ( BaseUtils.isChinaese(request.getParameter("name"))) {
				// 用于IOS客户端传递过来的用户名
				name = new String(
					request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				name = request.getParameter("name");
			}
			String result=client.mt(iphone,"您好，您的好友"+name+"邀请您注册小区助手，下载地址http://www.tcshenghuo.org:8806/CellCode/appHtml/download.action【小区助手】","","","");
			if(result.length()>8)
			{
				map.put("responseCode", "0");
				map.put("msg", "邀请成功");
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "邀请失败");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "邀请失败");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 我的会员
	 * @return
	 */
	public String myMember()
	{
		try {
			String sellerId = request.getParameter("sellerId");//商户ID
			List<Consumption> list= userService.selMyMemberSellerId(sellerId);
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
			map.put("msg", "查询异常");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 全部汇总
	 * @return
	 */
	public String mySummary()
	{
		try {
			String sellerId = request.getParameter("sellerId");//商户ID
			List<Consumption> list= userService.selMyMember(sellerId,request.getParameter("consumption"),request.getParameter("addtime"),request.getParameter("overtime"),request.getParameter("villageId"));
			int num = userService.selSumAry(request.getParameter("sellerId"));
			if(list.size()<1)
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("num",num);//共计多少比
				map.put("number",list.size());//共计多少比
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 积分商城兑换
	 * @return
	 */
	public String exchange()
	{
		try {
			String userId = request.getParameter("userId");
			Integer num = Integer.parseInt(request.getParameter("num"));
			List<Address> list = userService.selAddress(userId,null,null,null);
			if(list.size()>0)
			{
				String mallId = request.getParameter("mallId");
				
				Mall ma = userService.selMallId(Integer.valueOf(mallId)); 
				
				String msg = userService.updateUserIntegral(ma,num,Integer.valueOf(userId));
				
				if(msg == null){
					Order order = new Order();
					order.setAddtime(BaseUtils.getNowStringDateTime(new Date()));//下单时间
					order.setState("订单已提交");
					order.setNumber(num);
					order.setUserId(Integer.valueOf(userId));
					order.setBuy(2);
					order.setPrice(ma.getIntegral()+"");
					order.setShopId(mallId);
					order.setYf("0");
					//order.setAddressId(Integer.valueOf(request.getParameter("addressId")));
					order.setAddressId(list.get(0).getId());
					boolean bool = userService.addOrder(order);
					if(bool)
					{
						map.put("responseCode", "0");
						map.put("msg", "下单成功");
						//userService.updateMall(Integer.valueOf(request.getParameter("mallId")),ma.getNumber()-1);
					}
					else
					{
						map.put("responseCode", "1");
						map.put("msg", "下单异常");
					}
				}
				else
				{
					map.put("responseCode", "1");
					map.put("msg", msg);
				}
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "请选择收货地址！");
			}
			
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "兑换异常");
		}
		return SUCCESS;
	}
//	/**
//	 * 我的订单
//	 * @return
//	 */
//	public String myOrder()
//	{
//		try {
//			List<Order> list = userService.selMyOrder(request.getParameter("userId"));
//			if(list.size()<1)
//			{
//				map.put("responseCode", "-2");
//			}
//			else
//			{
//				map.put("responseCode", "0");
//				map.put("list", list);
//			}
//		} catch (Exception e) {
//			map.put("responseCode", "1");
//			map.put("msg", "订单异常");
//		}
//		return SUCCESS;
//	}
//	
	/**
	 * 我的积分
	 * @return
	 */
	public String myNumber()
	{
		try {
			User u= userService.seluserId(Integer.valueOf(request.getParameter("userId")));
			if(u==null || u.equals(""))
			{
				map.put("responseCode", "1");
				map.put("msg", "未找到此用户");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("number", u.getIntegral());
			}
			
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "积分异常");
		}
		return SUCCESS;
	}
	/**
	 *更新地址
	 * @return
	 */
	public String upateAddress()
	{
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
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "登陆异常");
		}
		return SUCCESS;
	}
	/**
	 * 小区主登陆
	 * @return
	 */
	public String loginBackstage()
	{
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
					if(Integer.valueOf(seller) == 3)
					{
						BusinessMan man = userService.loginUserMan(user.getId(),request.getParameter("manUserName"),PiaoJuTong.Md5(request.getParameter("manUserPwd")));
						if(man == null ||man.equals(""))
						{
							
						}
						else
						{
							map.put("man",man);
						}
					}
					if(user == null ||user.equals(""))
					{
						map.put("responseCode", "1");
						map.put("msg", "未找到用户名");
					}
					else
					{
						map.put("user",user);
					}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "登陆异常");
		}
		return SUCCESS;
	}
	/**
	 * 市场主商户
	 * @return
	 */
	public String selUserMan()
	{
		try {
			List<User> u= userService.selUserMan(Integer.valueOf(request.getParameter("userId")));
			map.put("list",u);
			map.put("responseCode", "0");
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "积分异常");
		}
		return SUCCESS;
	}
	/**
	 * 小区主开通账号
	 * @return
	 */
	public String addUser()
	{
		try {
			User user = userService.loginUserInfo(request.getParameter("userName"),
					PiaoJuTong.Md5(request.getParameter("userPwd")),"2");
			if(user == null || user.equals(""))
			{
				map.put("responseCode", "1");
				map.put("msg", "未找到此用户");
			}
			else
			{
				User u = new User();
				u.setEmail(request.getParameter("userMan"));
				u.setPassWord(request.getParameter("userPwd"));
				u.setSellIsNo(1);
				boolean bool = userService.addUser(u);
				if(bool)
				{
					map.put("responseCode", "0");
				}
				else
				{
					map.put("responseCode", "1");
					map.put("msg", "添加异常");
				}
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "添加异常");
		}
		return SUCCESS;
	}
	/**
	 * 设置默认地址
	 * @return
	 */
	public String updateMoren()
	{
		try {
			boolean bool = userService.updateAddress(request.getParameter("thedefault"),Integer.parseInt(request.getParameter("id"))); 
			if(bool)
			{
				map.put("responseCode", "0");
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "设置默认地址异常");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "设置默认地址异常");
		}
		return SUCCESS;
	}
	/**
	 * 删除地址
	 * @return
	 */
	public String delAddress()
	{
		try {
			boolean bool = userService.delAddress(Integer.parseInt(request.getParameter("id"))); 
			if(bool)
			{
				map.put("responseCode", "0");
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "删除异常");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "删除异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 冻结账户开关
	 * @return
	 */
	public String freezeToggle()
	{
		try {
			String userId = request.getParameter("userId");
			userService.freezeToggle(userId);
			map.put("responseCode", "0");
			map.put("msg", "操作成功");
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "操作失败");
		}
		return SUCCESS;
	}
	
	/**
	 * 冻结账户开关
	 * @return
	 */
	public String report()
	{
		try {
			String shareId = request.getParameter("shareId");
			userService.report(shareId,request.getParameter("type"));
			map.put("responseCode", "0");
			map.put("msg", "操作成功");
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "操作失败");
		}
		return SUCCESS;
	}
	
	/**
	 * 获取400电话
	 * @return
	 */
	public String fetchCall()
	{
		try {
			SystemAgrs sa = userService.findTel();
			map.put("phone", sa.getTel());
			map.put("responseCode", "0");
			map.put("msg", "操作成功");
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "操作失败");
		}
		return SUCCESS;
	}
	
	/**
	 * 接收用户token
	 */
	public String updateToken(){
		try {
			
			String userId = request.getParameter("userId");
			String token = request.getParameter("token");
			
			userService.updateToken(userId,token,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("msg", "更新异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 推送消息(ios)
	 */
	public String myPush(){
		try {
			
			String userId = request.getParameter("userId");
			String path = request.getRealPath("/app");
			String content = request.getParameter("content");
			
			userService.myPush(userId,path,content,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("msg", "更新异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}

	/**
	 * 查询推送(安卓)
	 * @return
	 */
	public String selPush(){
		try {
			
			String userId = request.getParameter("userId");
			String cityId = request.getParameter("cityId");
			
			userService.selPush(userId,cityId,map);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("msg", "查询异常");
			map.put("code", 0);
		}
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

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
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

}
