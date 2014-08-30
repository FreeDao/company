package com.era.serviceImpl;

import com.era.dao.BaseDAO;
import com.era.orm.BusmarsetManager;
import com.era.service.UserService;

public class UserServiceImpl implements UserService
{
	private BaseDAO dao;
	
	@Override
	public boolean updateUser(BusmarsetManager manager)
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
	public BusmarsetManager login(String userNmae, String passWord) 
	{
		BusmarsetManager manager = new BusmarsetManager();
		manager = (BusmarsetManager) dao.loadObject("from BusmarsetManager where userName = '"+userNmae+"' and userPwd = '"+passWord+"'");
		return manager;
	}

	@Override
	public BusmarsetManager selUserId(int id)
	{
		BusmarsetManager manager = (BusmarsetManager) dao.loadById(BusmarsetManager.class, id);
		return manager;
	}
	
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
}
