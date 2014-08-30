package com.era.serviceImpl;

import com.era.dao.BaseDAO;
import com.era.orm.User;
import com.era.orm.UserFeedBack;
import com.era.service.UserService;

public class UserServiceImpl implements UserService 
{
	private BaseDAO dao;
	
	@Override
	public User login(String userName, String passWord)
	{
		User user = null;
		try {
			String hql = "";
			hql = "from User where email = '"+userName+"' and passWord='" + passWord + "'";
			user = (User) dao.loadObject(hql);
		} catch (Exception e) {
			return null;
		}
		return user;
	}

	public User loginQq(String email)
	{
		String hql = "";
		hql = "from User where email = '"+email+"' ";
		User user = (User) dao.loadObject(hql);
		return user;
	}

	@Override
	public boolean addUser(User user) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	@Override
	public User selUserId(int id) 
	{
		User user = (User) dao.loadById(User.class,id);
		return user;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public boolean addUserFeedBack(UserFeedBack back) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(back);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
