package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Roles;
import com.era.service.RolesService;

public class RolesServiceImpl implements RolesService
{
	private BaseDAO dao;

	@Override
	public Roles selRolesById(int id)
	{
		Roles ion = (Roles) dao.loadObject("from Roles where id="+id);
		return ion;
	}

	@Override
	public boolean rolesAdd(Roles ion)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(ion);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Roles> selRoles(int pageNo, int pageSize) 
	{
		List<Roles> list = dao.query("from Roles",pageNo, pageSize);
		return list;
	}
	
	public boolean rolesByIdUpdate(Roles roles)
	{
		boolean flag = false;
		try {
			String hql = "update Roles set rolesName='" + roles.getRolesName()
					+ "',rolesAction='" + roles.getRolesAction()
					+ "' where id='"
					+ roles.getId() + "'";
			dao.update(hql);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public int numberRoles()
	{
		int number = dao.countQuery("select count(*) from Roles");
		return number;
	}

	@Override
	public List<Roles> allRoles()
	{
		List<Roles> list = dao.query("from Roles");
		return list;
	}
	
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
