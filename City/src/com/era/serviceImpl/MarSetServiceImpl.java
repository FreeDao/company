package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.BusmarSet;
import com.era.service.MarSetService;

public class MarSetServiceImpl implements MarSetService
{
	private BaseDAO dao;
	@Override
	public boolean addMarSet(BusmarSet marKet)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(marKet);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delMarSet(Integer id)
	{
		boolean flag = false;
		try {
			dao.delById(BusmarSet.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public BusmarSet marSetId(int id) 
	{
		BusmarSet marSet = new BusmarSet();
		marSet = (BusmarSet) dao.loadById(BusmarSet.class,id);
		return marSet;
	}
	
	@Override
	public List<Object> listMarSet(String name, int cityId,int pageNo, int pageSize)
	{
		String hql = "";
		if(cityId == 0)
		{
			if(name == null || name.equals(""))
			{
				hql = "from BusmarSet sete,Market ket where sete.marketId = ket.id";
			}
			else
			{
				hql = "from BusmarSet sete,Market ket where sete.bmsUserName like '%"+name+"%' and  sete.marketId = ket.id";
			}
		}
		else
		{
			if(name == null || name.equals(""))
			{
				hql = "from BusmarSet sete,Market ket where sete.marketId = ket.id and ket.cityId = "+cityId;
			}
			else
			{
				hql = "from BusmarSet sete,Market ket where sete.bmsUserName like '%"+name+"%' and  sete.marketId = ket.id and ket.cityId = "+cityId;
			}
		}
		
		List<Object> list = dao.query(hql,pageNo,pageSize);
		return list;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public int numberAdmin(String name, int cityId) {
		String hql = "";
		if(cityId == 0)
		{
			if(name == null || name.equals(""))
			{
				hql = "select count(*) from BusmarSet sete,Market ket where sete.marketId = ket.id";
			}
			else
			{
				hql = "select count(*) from BusmarSet sete,Market ket where sete.bmsUserName like '%"+name+"%' and  sete.marketId = ket.id";
			}
		}
		else
		{
			if(name == null || name.equals(""))
			{
				hql = "select count(*) from BusmarSet sete,Market ket where sete.marketId = ket.id   and ket.cityId = "+cityId;
			}
			else
			{
				hql = "select count(*) from BusmarSet sete,Market ket where sete.bmsUserName like '%"+name+"%' and  sete.marketId = ket.id  and ket.cityId = "+cityId;
			}
		}
		int number = dao.countQuery(hql);
		return number;
	}
}
