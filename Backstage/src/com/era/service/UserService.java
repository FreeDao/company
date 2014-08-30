package com.era.service;

import java.util.List;

import com.era.orm.Consumption;
import com.era.orm.Custom;
import com.era.orm.Address;
import com.era.orm.Message;
import com.era.orm.Order;
import com.era.orm.ShopType;
import com.era.orm.ShopTypeTwo;
import com.era.orm.User;

public interface UserService
{
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> selUser(String name,String id,String userName,int pageNo, int pageSize); 
	/**
	 * 通过ID删除用户
	 * @param id
	 * @return
	 */
	public boolean delUser(int id);
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);
	/**
	 * 查询用户总的条数
	 * @return
	 */
	public int numberUser(String name,String id,String userName);
	
	/**
	 * 客户端用户登录
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public User loginUserInfo(String userName,String userPwd,String seller);
	
	
	/**
	 * 根据用户名和email查询显示对应对象
	 * @param userName
	 * @param email
	 * @return
	 */
	public User getUserInfo(String userName,String email);
	/**
	 * 客户端判断用户名是否存在
	 * @param userName
	 * @return
	 */
	public boolean isUser(String userName);
	
	/**
	 * 客户端判断Email是否已经存在
	 * @param userName
	 * @return
	 */
	public boolean isEmail(String email);
	
	/**
	 * 客户端用户注册
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public User registerUserInfo(User user);
	/**
	 * 验证qq登陆
	 * @return
	 */
	public User login(String email);
	public List<User> selGuarantee(String tee, String log, String dim,
			String pageNo, String pageNum);
	public boolean updateUser(String id, String villageId,String log,String dim);
	public List<Address> selAddress(String name,int pageNo, int pageSize);
	public boolean addAddress(Address add);
	public boolean addOrder(Order add);
	public boolean addMessage(Message message);
	public List<Message> selMyMessage(String parameter);
	public List<Custom> selCustom(String parameter,String pageNo,String pageNum);
	public boolean addConsumption(Consumption con);
	public boolean updateUserMoney(Integer valueOf, Integer id);
	public List<Object> selOrder(String parameter, String parameter2,String addtime,String overtime,
			Integer valueOf, Integer valueOf2);
	public int numberOrder(String parameter, String parameter2,String addtime,String overtime);
	public boolean delOrder(int parseInt);
	public List<ShopType> selShoptype(String parameter, Integer valueOf,
			Integer valueOf2);
	public int numberShoptype(String parameter);
	public boolean delShoptype(int parseInt);
	public List<ShopTypeTwo> selShoptypetwo(String parameter,
			String parameter2, Integer valueOf, Integer valueOf2);
	public int numberShoptypetwo(String parameter, String parameter2);
	public boolean delShoptypetwo(int parseInt);
	public boolean addShoptype(ShopType shop);
	public boolean addShoptypetwo(ShopTypeTwo two);
	public List<ShopType> selShoptypeAll();
	public int numberAddress(String name);
	public boolean delAddress(int parseInt);
	public Integer selCustomTotal(String village);
}
