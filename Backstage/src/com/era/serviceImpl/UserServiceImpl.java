package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Address;
import com.era.orm.Consumption;
import com.era.orm.Custom;
import com.era.orm.Message;
import com.era.orm.Order;
import com.era.orm.ShopType;
import com.era.orm.ShopTypeTwo;
import com.era.orm.User;
import com.era.orm.Video;
import com.era.orm.Village;
import com.era.service.UserService;

public class UserServiceImpl implements UserService {
	private BaseDAO dao;
	private String hql = "";

	@Override
	public int numberUser(String name,String id, String userName) {
		String hql = "select count(*) from User where 1 = 1 and sellIsNo = "+id;
		if(name == null || name.equals(""))
		{
			
		}
		else
		{
			hql+=" and email like '%"+name+"%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<User> selUser(String name,String id, String userName, int pageNo,
			int pageSize) {
		String hql = "from User where 1 = 1 and sellIsNo = "+id;
		if(name == null || name.equals(""))
		{
			
		}
		else
		{
			hql+=" and email like '%"+name+"%'";
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
		hql = "from User where (nickName = '" + userName + "' or email = '"+userName+"') and passWord='" + userPwd + "' and sellIsNo = "+id;
		User user = (User) dao.loadObject(hql);
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
	public User getUserInfo(String userName, String email) {
		hql = "from User where nickName = '" + userName + "' and email = '"+email+"' ";
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
			/*String hql = "from User where villageId = "+tee+" order by ACOS(SIN(("+dim+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+dim+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+log+" * 3.1415) / 180 - (log * 3.1415) / 180 ) ) * 6380 asc";
			List<User> list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
			List<User> obj = new ArrayList<User>();
			for(int i = 0 ;i<list.size();i++)
			{
				User u = new User();
				u = list.get(i);
				u.setAddtime(list.get(i).getAddtime());
				u.setAge(list.get(i).getAge());
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
			}*/
			return null;
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
	public List<Address> selAddress(String name,int pageNo, int pageSize)
	{
		String hql = "";
		if(name == null ||name.equals(""))
		{
			hql = "from Address";
		}
		else
		{
			 hql = "from Address where name like '%"+name+"%'";
		}
		
		List<Address> list = dao.query(hql,pageNo,pageSize);
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
	public List<Message> selMyMessage(String parameter) {
		String hql = "from Message where friendId="+parameter;
		List<Message> list = dao.query(hql);
		return list;
	}

	@Override
	public List<Custom> selCustom(String parameter,String pageNo,String pageNum) {
		String hql = "from Custom where 1 = 1";
		if(null != parameter){
			hql += " and villageId="+parameter;
		}
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
	public boolean updateUserMoney(Integer valueOf, Integer id) {
		try {
			int i = dao.update("update User set age = "+valueOf+" where id = "+id);
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
	public List<Object> selOrder(String parameter, String parameter2,String addtime,String overtime,
			Integer valueOf, Integer valueOf2) 
			{
		String hql = "select o.addtime,o.price,u.nickName,a.address,o.id from Order o,Address a,User u where 1 = 1 and o.addressId = a.id and o.userId = u.id";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql += " and o.buy = "+parameter;
		}
		if(parameter2 == null || parameter2.equals(""))
		{
			
		}
		else
		{
			hql +=" and o.price like '%"+parameter2+"%'";
		}
		if(addtime == null || addtime.equals(""))
		{
			
		}
		else
		{
			hql+=" and o.addtime>='"+addtime+"' and o.addtime<='"+overtime+"'";
		}
		hql+=" order by o.addtime desc";
		List<Object> list = dao.query(hql,valueOf,valueOf2);
		return list;
	}

	@Override
	public int numberOrder(String parameter, String parameter2,String addtime,String overtime) 
	{
		String hql = "select count(*) from Order o,Address a,User u where 1 = 1 and o.addressId = a.id and o.userId = u.id";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql += " and buy = "+parameter;
		}
		if(parameter2 == null || parameter2.equals(""))
		{
			
		}
		else
		{
			hql +=" and price like '%"+parameter2+"%'";
		}
		if(addtime == null || addtime.equals(""))
		{
			
		}
		else
		{
			hql+=" and o.addtime>='"+addtime+"' and o.addtime<='"+overtime+"'";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean delOrder(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Order.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<ShopType> selShoptype(String parameter, Integer valueOf,
			Integer valueOf2) 
			{
		String hql="from ShopType where 1 = 1";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql+=" and type like '%"+parameter+"%'";
		}
		List<ShopType> list = dao.query(hql,valueOf,valueOf2);
		return list;
	}

	@Override
	public int numberShoptype(String parameter) {
		String hql="select count(*) from ShopType where 1 = 1";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql+=" and type like '%"+parameter+"%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean delShoptype(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(ShopType.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<ShopTypeTwo> selShoptypetwo(String parameter,
			String parameter2, Integer valueOf, Integer valueOf2) 
			{
		String hql="from ShopTypeTwo where 1 = 1";
		if(parameter2 == null || parameter2.equals(""))
		{
			
		}
		else
		{
			hql+=" and name like '%"+parameter2+"%'";
		}
		List<ShopTypeTwo> list = dao.query(hql,valueOf,valueOf2);
		return list;
	}

	@Override
	public int numberShoptypetwo(String parameter, String parameter2) 
	{
		String hql="select count(*) from ShopTypeTwo where 1 = 1";
		if(parameter2 == null || parameter2.equals(""))
		{
			
		}
		else
		{
			hql+=" and name like '%"+parameter2+"%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean delShoptypetwo(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(ShopTypeTwo.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean addShoptype(ShopType shop) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(shop);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addShoptypetwo(ShopTypeTwo two) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(two);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<ShopType> selShoptypeAll() {
		List<ShopType> list = dao.query("from ShopType");
		return list;
	}

	@Override
	public int numberAddress(String name) 
	{
		String hql ="";
		if(name == null ||name.equals(""))
		{
			hql = "select count(*) from Address";
		}
		else
		{
			 hql = "select count(*) from Address where name like '%"+name+"%'";
		}
		
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean delAddress(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Address.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public Integer selCustomTotal(String village) {
		// TODO Auto-generated method stub
		String hql = "from Custom where 1 = 1";
		if(null != village){
			hql += " and villageId="+village;
		}
		return dao.query(hql).size();
	}
}
