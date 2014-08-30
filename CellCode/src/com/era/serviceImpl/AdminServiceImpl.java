package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Admin;
import com.era.orm.Village;
import com.era.service.AdminService;
import com.era.util.PinYin;

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
	public int numberAdmin()
	{
		int number = dao.countQuery("select count(*) from Admin");
		return number;
	}
	@Override
	public List<Admin> selAdmin(int pageNo, int pageSize)
	{
		List<Admin> list = dao.query("from Admin",pageNo, pageSize);
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
	public void setVillagePinYin() {
		// TODO Auto-generated method stub
		List<Village> vs = dao.query("from Village v where v.py is null ",1,5000);
		PinYin py = new PinYin();
		for(Village v : vs){
			v.setPy(py.getStringPinYin(v.getName()));
			dao.saveOrUpdate(v);
		}
	}
	
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}


}
