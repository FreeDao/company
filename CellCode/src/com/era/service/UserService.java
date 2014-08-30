package com.era.service;

import java.util.List;
import java.util.Map;

import com.era.orm.BusinessMan;
import com.era.orm.Consumption;
import com.era.orm.Custom;
import com.era.orm.Address;
import com.era.orm.Images;
import com.era.orm.Mall;
import com.era.orm.Message;
import com.era.orm.Order;
import com.era.orm.SystemAgrs;
import com.era.orm.User;

public interface UserService
{
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> selUser(String id,String userName,int pageNo, int pageSize); 
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
	public int numberUser(String id,String userName);
	
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
	public List<Address> selAddress(String userId,String thedefault,String pageNo,String pageNum);
	public boolean addAddress(Address add);
	public boolean addOrder(Order add);
	public boolean addMessage(Message message);
	public List<Message> selMyMessage(String parameter, String friendId);
	public List<Custom> selCustom(String parameter,String pageNo,String pageNum);
	public boolean addConsumption(Consumption con);
	public Integer updateUserMoney(Integer valueOf, Integer id);
	public boolean updateUserInfo(String id, String iphone, String nickName,
			String name, String villageId, String householder, String doorplate, String age, String emotion, String job, String interest, String interestplace, String signature, String sex);
	public List<Consumption> selMyMember(String sellerId,String consumption,String addtime,String overtime,String villageId);
	public User seluserId(Integer valueOf);
	public int selSumAry(String parameter);
	public User myNumber(String parameter);
	public Mall selMallId(Integer valueOf);
	public boolean updateMall(Integer valueOf, int i);
	public BusinessMan loginUserMan(Integer id, String parameter,
			String parameter2);
	public List<User> selUserMan(Integer valueOf);
	public boolean delAddress(int parseInt);
	public boolean updateAddress(String parameter, int parseInt);
	public List<Consumption> selMyMemberSellerId(String sellerId);
	
	/**
	 * 检查用户积分
	 * @param ma 
	 * @param num 份数
	 * @param userId  
	 * @return
	 */
	public String updateUserIntegral(Mall ma, Integer num, Integer userId);
	/**
	 * 冻结账户开关
	 * @param userId
	 */
	public void freezeToggle(String userId);
	public void useWin(String winId);
	/**
	 * 举报
	 * @param shareId
	 */
	public void report(String shareId,String type);
	/**
	 * 查找400电话
	 * @return
	 */
	public SystemAgrs findTel();
	/**
	 * 更新保存对象
	 * @param images
	 */
	public void saveOrUpdate(Object obj);
	/**
	 * 更新logo并返回对象
	 * @param id
	 * @param logo
	 * @return
	 */
	public User updateLogo(String id, String logo);
	/**
	 * 查询用户
	 * @param id
	 * @return
	 */
	public User selUserById(String id);
	/**
	 * 更新用户头像
	 * @param id
	 * @param logo 
	 * @param map
	 */
	public User updateAvatars(String id, String logo, Map<String, Object> map);
	/**
	 * 添加推荐人积分
	 * @param recommended
	 */
	public void recommendIntegral(String recommended);
	/**
	 * 更新用户token
	 * @param userId
	 * @param token
	 * @param map
	 */
	public void updateToken(String userId, String token, Map<String, Object> map);
	/**
	 * 我的推送(ios)
	 * @param userId
	 * @param content 
	 * @param path 
	 * @param map
	 */
	public void myPush(String userId, String path, String content, Map<String, Object> map);
	/**
	 * 查询推送信息(安卓)
	 * @param userId
	 * @param cityId
	 * @param map
	 */
	public void selPush(String userId, String cityId, Map<String, Object> map);
}
