package com.era.service;

import java.util.List;

import com.era.orm.Admin;
import com.era.util.LoginUser;

public interface AdminService
{
	/**
	 * 登陆
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public LoginUser adminLogin(String userName,String passWord,Integer type);
	/**
	 * 查询条数
	 * @return
	 */
	public int numberAdmin(String city);
	/**
	 * 查询全部管理员
	 * @return
	 */
	public List<Object> selAdmin(String city,String pageNo, String pageSize);
	/**
	 * 删除管理员
	 * @param id
	 * @return
	 */
	public boolean delAdmin(int id);
	/**
	 * 通过ID查询管理员
	 * @param id
	 * @return
	 */
	public Admin selIdAdmin(int id);
	/**
	 * 查询数据库中是否存在
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public List<Admin> selAdminLogin(String userName);
	/**
	 * 添加用户
	 * @return
	 */
	public boolean addAdmin(Admin admin);
	public int getTxlTotal();
}
