package com.era.service;

import com.era.orm.BusmarsetManager;

public interface UserService 
{
	/**
	 * 商户登陆
	 * @param agentname
	 * @param agentpass
	 * @return
	 */
	public BusmarsetManager login(String userNmae,String passWord);
	/**
	 * 通过ID查询个人商户信息
	 * @param id
	 * @return
	 */
	public BusmarsetManager selUserId(int id);
	/**
	 * 更新商户基本信息
	 * @param manager
	 * @return
	 */
	public boolean updateUser(BusmarsetManager manager);
}
