package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Admin;
import com.era.orm.City;
import com.era.service.AdminService;

public class AdminServiceImpl implements AdminService
{
	private BaseDAO dao;
	
	@Override
	public Admin adminLogin(String userName, String passWord)
	{
		Admin user = new Admin();
		user = (Admin) dao.loadObject("from Admin where adminName ='"+userName+"' and password = '"+passWord+"'");
		return user;
	}

	@Override
	public List<Admin> selAdminLogin(String userName) {
		List<Admin> list = dao.query("from Admin where adminName ='"+userName+"'");
		return list;
	}

	@Override
	public int numberAdmin(int cityId)
	{
		int number;
		if(cityId == 0)
		{
			number = dao.countQuery("select count(*) from Admin");
		}
		else
		{
			number = dao.countQuery("select count(*) from Admin where cityId = "+cityId);
		}
		return number;
	}
	@Override
	public List<Admin> selAdmin(int cityId,int pageNo, int pageSize)
	{
		List<Admin> list = null;
		if(cityId == 0)
		{
			list = dao.query("from Admin",pageNo, pageSize);
		}
		else
		{
			list = dao.query("from Admin where cityId = "+cityId,pageNo, pageSize);
		}
		return list;
	}
	
	@Override
	public boolean delAdmin(int id)
	{
		boolean flag = false;
		try {
			dao.delById(Admin.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public Admin selIdAdmin(int id)
	{
		Admin user = new Admin();
		user = (Admin) dao.loadById(Admin.class,id);
		return user;
	}
	
	@Override
	public boolean addAdmin(Admin admin)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(admin);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean updatePassWord(String password, int id) 
	{
		String hql="update Admin set password = '"+password+"' where id = '"+id+"'";
		boolean flag = false;
		try{
			dao.update(hql);
			flag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<City> selCity() {
		List<City> list = dao.query("from City");
		return list;
	}
}
