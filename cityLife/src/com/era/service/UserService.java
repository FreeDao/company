package com.era.service;

import java.util.List;

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
	public User loginUserInfo(String userName,String userPwd);
	
	
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
}
