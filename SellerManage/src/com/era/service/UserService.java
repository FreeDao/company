package com.era.service;

import com.era.orm.SellerManager;

public interface UserService 
{
	/**
	 * 商户登陆
	 * @param agentname
	 * @param agentpass
	 * @return
	 */
	public SellerManager login(String userNmae,String passWord);
	/**
	 * 通过ID查询个人商户信息
	 * @param id
	 * @return
	 */
	public SellerManager selUserId(int id);
	/**
	 * 更新商户基本信息
	 * @param manager
	 * @return
	 */
	public boolean updateUser(SellerManager manager);
}
