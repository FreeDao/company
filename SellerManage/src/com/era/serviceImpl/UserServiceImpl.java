package com.era.serviceImpl;

import com.era.dao.BaseDAO;
import com.era.orm.SellerManager;
import com.era.service.UserService;

public class UserServiceImpl implements UserService
{
	private BaseDAO dao;
	
	@Override
	public boolean updateUser(SellerManager manager)
	{
		boolean flag = false;
		try{
			dao.saveOrUpdate(manager);
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public SellerManager login(String userNmae, String passWord) 
	{
		SellerManager manager = new SellerManager();
		manager = (SellerManager) dao.loadObject("from SellerManager where userName = '"+userNmae+"' and userPwd = '"+passWord+"'");
		return manager;
	}

	@Override
	public SellerManager selUserId(int id)
	{
		SellerManager manager = (SellerManager) dao.loadById(SellerManager.class, id);
		return manager;
	}
	
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
}
