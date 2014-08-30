package com.era.serviceImpl;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.era.dao.BaseDAO;
import com.era.orm.Address;
import com.era.orm.Admin;
import com.era.orm.BusinessMan;
import com.era.orm.Consumption;
import com.era.orm.Custom;
import com.era.orm.Images;
import com.era.orm.Mall;
import com.era.orm.Message;
import com.era.orm.Order;
import com.era.orm.Seller;
import com.era.orm.Share;
import com.era.orm.SystemAgrs;
import com.era.orm.User;
import com.era.orm.UserMsg;
import com.era.orm.UserToken;
import com.era.orm.Village;
import com.era.orm.Winning;
import com.era.service.UserService;
import com.era.util.BaseUtils;
import com.era.util.DateUtil;
import com.era.util.PiaoJuTong;

public class UserServiceImpl implements UserService {
	private BaseDAO dao;
	private String hql = "";

	@Override
	public int numberUser(String id, String userName) {
		String hql = "";
		if (id == null || id.equals("")) {
			if (userName == null || userName.equals("")) {
				hql = "select count(*) from User";
			} else {
				hql = "select count(*) from User where nickName='" + userName
						+ "'";
			}

		} else {
			if (userName == null || userName.equals("")) {
				hql = "select count(*) from User where id="
						+ Integer.valueOf(id);
			} else {
				hql = "select count(*) from User where id="
						+ Integer.valueOf(id) + " and nickName='" + userName
						+ "'";
			}
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<User> selUser(String id, String userName, int pageNo,
			int pageSize) {
		String hql = "";
		if (id == null || id.equals("")) {
			if (userName == null || userName.equals("")) {
				hql = "from User";
			} else {
				hql = "from User where nickName='" + userName + "'";
			}

		} else {
			if (userName == null || userName.equals("")) {
				hql = "from User where id=" + Integer.valueOf(id);
			} else {
				hql = "from User where id=" + Integer.valueOf(id)
						+ " and nickName='" + userName + "'";
			}
		}
		List<User> list = dao.query(hql, pageNo, pageSize);
		return list;
	}

	@Override
	public boolean delUser(int id) {
		boolean flag = false;
		try {
			dao.delById(User.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addUser(User user) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * TODO 用户登录
	 */
	@Override
	public User loginUserInfo(String userName, String userPwd,String id) {
		hql = "from User where email = '"+userName+"' and passWord='" + userPwd + "' and sellIsNo = "+id;
		User user = (User) dao.loadObject(hql);
		if( user != null && user.getVillageId() != null && user.getVillageId()!=0 && !user.getVillageId().equals(""))
		{	
			Village v =(Village) dao.loadById(Village.class, user.getVillageId());
			user.setVillageName(v.getName());
		}
		String hql = "from Images i where i.compositeId="+user.getId()+" and i.type = 4";
		List<Images> images = dao.query(hql);
		user.setImagesList(images);
		return user;
	}

	/**
	 * TODO 判断用户名是否已经存在
	 * 
	 * @return
	 */
	@Override
	public boolean isUser(String userName) {
		boolean flag = false;
		hql = "from User where nickName = '" + userName + "'";
		Object object = dao.loadObject(hql);
		if (object != null) {
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * TODO 判断用户名是否已经存在
	 * 
	 * @return
	 */
	@Override
	public boolean isEmail(String email) {
		boolean flag = false;
		hql = "from User where email = '" + email + "'";
		Object object = dao.loadObject(hql);
		if (object != null) {
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * TODO 客户端用户注册实现接口
	 */
	@Override
	public User registerUserInfo(User user) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		User userInfo =  (User) dao.loadById(User.class,user.getId());
		return userInfo;
	}
	public User login(String email)
	{
		hql = "from User where email = '"+email+"' ";
		User user = (User) dao.loadObject(hql);
		return user;
	}
	/**
	 * TODO 根据用户名和email进行查询对应的对象
	 */
	@Override
	public User getUserInfo(String userName, String password) {
		hql = "from User where email = '" + userName + "' and passWord = '"+PiaoJuTong.Md5(password)+"' ";
		User user = (User) dao.loadObject(hql);
		return user;
	}
	
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<User> selGuarantee(String tee, String log, String dim,
			String pageNo, String pageNum)
			{
		String hql = "from User where villageId = "+tee+" order by ACOS(SIN(("+dim+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+dim+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+log+" * 3.1415) / 180 - (log * 3.1415) / 180 ) ) * 6380 asc";
		List<User> list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		List<User> obj = new ArrayList<User>();
		for(int i = 0 ;i<list.size();i++)
		{
			User u = new User();
			u = list.get(i);
			u.setAddtime(list.get(i).getAddtime());
			u.setIntegral(list.get(i).getIntegral());
			u.setDim(list.get(i).getDim());
			u.setDoorplate(list.get(i).getDoorplate());
			u.setEmail(list.get(i).getEmail());
			u.setHouseholder(list.get(i).getHouseholder());
			u.setId(list.get(i).getId());
			u.setImgUrl(list.get(i).getImgUrl());
			u.setLog(list.get(i).getLog());
			u.setName(list.get(i).getName());
			u.setNickName(list.get(i).getNickName());
			u.setNumber(list.get(i).getNumber());
			u.setPassWord("");
			u.setPhone(list.get(i).getPhone());
			u.setSellerPassWord("");
			u.setSellIsNo(list.get(i).getSellIsNo());
			u.setSex(list.get(i).getSex());
			u.setType("");
			List<Message> listE = dao.query("from Message where userId="+list.get(i).getId());
			u.setInfo(listE.size());//消息数量
			if(list.get(i).getVillageId() == null || list.get(i).getVillageId().equals("") || list.get(i).getVillageId()==0)
			{
				u.setVillageName("暂无小区");
			}
			else
			{
				Village v = (Village) dao.loadById(Village.class,list.get(i).getVillageId());
				u.setVillageName(v.getName());
			}
			obj.add(u);
		}
		return list;
	}

	@Override
	public boolean updateUser(String id, String villageId,String log,String dim) {
		try {
			int i = dao.update("update User set villageId = "+villageId+",log='"+log+"',dim='"+dim+"'  where id = "+id);
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addAddress(Address add) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(add);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Address> selAddress(String userId,String thedefault,String pageNo,String pageNum) 
	{
		if(null == pageNo){
			pageNo = "1";
		}
		if(null == pageNum){
			pageNum = "1";
		}
		List<Address> list = null;
		hql="from Address where 1 = 1";
		if(userId != null && !userId.equals(""))
		{
			hql+=" and userId="+userId;
		}
		if(thedefault != null && !thedefault.equals(""))
		{
			hql+=" and thedefault = "+thedefault;
		}
		hql += " order by thedefault desc,addtime desc";
		if(pageNo != null && !pageNo.equals(""))
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		}
		return list;
	}

	@Override
	public boolean addOrder(Order add) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(add);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean addMessage(Message message) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(message);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Message> selMyMessage(String userId,String friendId) {
		String hql = "from Message where userId="+userId+" and friendId="+friendId;
		List<Message> list = dao.query(hql);
		return list;
	}

	@Override
	public List<Custom> selCustom(String parameter,String pageNo,String pageNum) {
		String hql = "from Custom where villageId="+parameter;
		List<Custom> list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		return list;
	}

	@Override
	public boolean addConsumption(Consumption con) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(con);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Integer updateUserMoney(Integer integral , Integer id) {
		try {
			User u = (User) dao.loadById(User.class, id);
			int i = dao.update("update User set integral = "+(integral+u.getIntegral())+" where id = "+id);
			if(i>0)
			{
				return integral+u.getIntegral();
			}
			else
			{
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean updateUserInfo(String id, String iphone, String nickName, 
			String name, String villageId, String householder, String doorplate, 
			String age, String emotion, String job, String interest, String interestplace, String signature,String sex) {
		try {
			String hql = "from Images i where i.compositeId="+Integer.parseInt(id)+" and i.type = 4";
			List<Images> images = dao.query(hql);
			for(Images i : images){
				dao.delById(Images.class, i.getId());
			}
			
			int a = 0;
			if(null != age && !"".equals(age)){
				a = Integer.parseInt(age);
			}
			int i = dao.update("update User set sex = "+Integer.parseInt(sex)+", age = "+a+",emotion = '"+emotion+"',job = '"+job+"',interest = '"+interest+"',interestplace = '"+interestplace+"',signature = '"+signature+"',phone = '"+iphone+"',nickName='"+nickName+"',villageId="+villageId+",doorplate='"+doorplate+"' where id = "+id);
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Consumption> selMyMember(String sellerId,String consumption,String addtime,String overtime,String villageId)
	{
		String hql = "from Consumption where sellId="+sellerId;
		if(null != consumption && !consumption.equals(""))
		{
			hql += " and consumption = "+consumption;
		}
		List<Consumption> list = dao.query(hql);
		return list;
	}

	@Override
	public User seluserId(Integer valueOf) 
	{
		User user = (User) dao.loadById(User.class, valueOf);
		if( user != null && user.getVillageId() != null && user.getVillageId()!=0 && !user.getVillageId().equals(""))
		{	
			Village v =(Village) dao.loadById(Village.class, user.getVillageId());
			user.setVillageName(v.getName());
		}
		return user;
	}

	@Override
	public int selSumAry(String parameter) {
		String hql = "select sum(money) from Consumption where sellId="+parameter;
		int list = dao.countQuery(hql);
		return list;
	}

	@Override
	public User myNumber(String parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mall selMallId(Integer valueOf) {
		Mall user = (Mall) dao.loadById(Mall.class, valueOf);
		return user;
	}

	@Override
	public boolean updateMall(Integer valueOf, int t) {
		try {
			int i = dao.update("update Mall set number = "+t+" where id = "+valueOf);
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public BusinessMan loginUserMan(Integer id, String parameter,
			String parameter2) {
		String hql = "from BusinessMan where sellerId="+id+" and name = '"+parameter+"' and parameter2 = '"+parameter2+"'";
		List<BusinessMan> list = dao.query(hql);
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<User> selUserMan(Integer valueOf) {
		String hql = "from User where manId = "+valueOf;
		List<User> list = dao.query(hql);
		return list;
	}

	@Override
	public boolean delAddress(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Address.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateAddress(String parameter, int parseInt) {
		try {
			dao.update("update Address set thedefault = 0 ");
			int i = dao.update("update Address set thedefault = 1 where id = "+parseInt);
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Consumption> selMyMemberSellerId(String sellerId) {
		String hql = "from Consumption where sellId="+sellerId;
		List<Consumption> list = dao.query(hql);
		return list;
	}

	@Override
	public String updateUserIntegral(Mall ma, Integer num, Integer userId) {
		// TODO Auto-generated method stub
		Integer temp = ma.getIntegral();
		if(ma.getNumber() < num){
			return "暂无此商品，等待补充货源！";
		}
		ma.setNumber(ma.getNumber()-num);
		User u = (User) dao.loadById(User.class, userId);
		if(temp*num > u.getIntegral()){
			return "您的当前积分不够，请获得更多积分！";
		}
		u.setIntegral(u.getIntegral() - temp*num);
		dao.saveOrUpdate(ma);
		dao.saveOrUpdate(u);
		return null;
	}

	@Override
	public void freezeToggle(String userId) {
		// TODO Auto-generated method stub
		User u = (User) dao.loadById(User.class, Integer.parseInt(userId));
		if(null != u.getIsFreeze() && u.getIsFreeze() == 1){
			u.setIsFreeze(0);
		}else{
			u.setIsFreeze(1);
		}
		dao.saveOrUpdate(u);
	}

	@Override
	public void useWin(String winId) {
		// TODO Auto-generated method stub
		Winning w = (Winning) dao.loadById(Winning.class, Integer.parseInt(winId));
		w.setWhether("是");
		dao.saveOrUpdate(w);
	}

	@Override
	public void report(String shareId,String type) {
		// TODO Auto-generated method stub
		Share share = (Share) dao.loadById(Share.class, Integer.parseInt(shareId));
		share.setReport(Integer.parseInt(type));
		dao.saveOrUpdate(share);
	}

	@Override
	public User updateLogo(String id, String logo) {
		// TODO Auto-generated method stub
		User u = (User) dao.loadById(User.class, Integer.parseInt(id));
		u.setImgUrl(logo);
		if( u != null && u.getVillageId() != null && u.getVillageId()!=0 && !u.getVillageId().equals(""))
		{
			Village v =(Village) dao.loadById(Village.class, u.getVillageId());
			u.setVillageName(v.getName());
		}
		dao.saveOrUpdate(u);
		String hql = "from Images i where i.compositeId="+Integer.parseInt(id)+" and i.type = 4";
		List<Images> images = dao.query(hql);
		u.setImagesList(images);
		return u;
	}
	
	@Override
	public User selUserById(String id) {
		// TODO Auto-generated method stub
		User u = (User) dao.loadById(User.class, Integer.parseInt(id));
		String hql = "from Images i where i.compositeId="+Integer.parseInt(id)+" and i.type = 4";
		List<Images> images = dao.query(hql);
		u.setImagesList(images);
		return u;
	}
	
	@Override
	public SystemAgrs findTel() {
		// TODO Auto-generated method stub
		return (SystemAgrs) dao.loadById(SystemAgrs.class, 1);
	}

	@Override
	public void saveOrUpdate(Object obj) {
		// TODO Auto-generated method stub
		dao.saveOrUpdate(obj);
	}

	@Override
	public User updateAvatars(String id, String logo, Map<String, Object> map) {
		// TODO Auto-generated method stub
		User u = (User) dao.loadById(User.class, Integer.parseInt(id));
		u.setImgUrl(logo);
		dao.saveOrUpdate(u);
		
		if(u.getSellIsNo() == 1){
			hql = "select s from User u,Sellermanager sm,Seller s where u.sellerId = sm.id and sm.sellerId = s.id and u.id ="+Integer.parseInt(id);
			Seller s = (Seller) dao.loadObject(hql);
			s.setLogo(logo);
			dao.saveOrUpdate(s);
		}
		
		return u;
		
	}

	@Override
	public void recommendIntegral(String recommended) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.email ="+recommended;
		User u = (User) dao.loadObject(hql);
		u.setIntegral(u.getIntegral()+100);
		dao.saveOrUpdate(u);
	}

	@Override
	public void updateToken(String userId, String token, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "from UserToken ut where ut.token='"+token+"'";
		UserToken ut =  (UserToken) dao.loadObject(hql);
		if( null == ut ){
			ut = new UserToken();
		}
		if( null != userId && !"".equals(userId)){
			ut.setUserId(Integer.parseInt(userId));
		}
		ut.setToken(token);
		ut.setAddTime(BaseUtils.getNowStringDateTime(new Date()));
		dao.saveOrUpdate(ut);
		map.put("code", 1);
		map.put("message", "更新成功");
	}

	@Override
	public void myPush(String userId, String path, String content,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selPush(String userId, String cityId, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String time = DateUtil.getTenMinute();
		
		String hql ="from UserMsg um where um.addTime >='"+time+"' and ( um.userId = 0 ";
		
		if( null != userId && !"".equals(userId) ){
			hql += " or um.userId = "+userId;
		}
		
		if( null != cityId && !"".equals(cityId) ){
			hql += " or um.cityId = "+cityId;
		}
		
		hql += ")";
		
		List<UserMsg> ums = dao.query(hql);
		
		map.put("code", 1);
		map.put("list", ums);
		map.put("msg", "查询成功");
		
		
	}

}
