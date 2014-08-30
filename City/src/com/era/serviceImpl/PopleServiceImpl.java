package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.City;
import com.era.orm.Convenient;
import com.era.orm.ConvenientDetail;
import com.era.service.PopleService;

public class PopleServiceImpl implements PopleService
{
	private BaseDAO dao;
	
	@Override
	public List<Object> selPople(String name,int cityId, int pageNo, int pageSize)
	{
		String hql = "";
		if(cityId == 0)
		{
			if(name == null || name.equals(""))
			{
				hql="from Convenient ent,City city where ent.cityId = city.id";
			}
			else
			{
				hql="from Convenient ent,City city,ConvenientDetail det where ent.cityId = city.id and ent.name like '%"+name+"%'";
			}
		}
		else
		{
			if(name == null || name.equals(""))
			{
				hql="from Convenient ent,City city where ent.cityId = city.id and ent.cityId = "+cityId;
			}
			else
			{
				hql="from Convenient ent,City city,ConvenientDetail det where ent.cityId = city.id and ent.name like '%"+name+"%'  and ent.cityId = "+cityId;
			}
		}
	
		List<Object> list =dao.query(hql, pageNo, pageSize);
		return list;
	}

	@Override
	public Integer numberPople(String name,int cityId)
	{
		String hql = "";
		if(cityId == 0)
		{
			if(name == null || name.equals(""))
			{
				hql="select count(*) from Convenient ent,City city where ent.cityId = city.id";
			}
			else
			{
				hql="select count(*) from Convenient ent,City city where ent.cityId = city.id  and ent.name like '%"+name+"%'";
			}
		}
		else
		{
			if(name == null || name.equals(""))
			{
				hql="select count(*) from Convenient ent,City city where ent.cityId = city.id and ent.cityId = "+cityId;
			}
			else
			{
				hql="select count(*) from Convenient ent,City city where ent.cityId = city.id  and ent.name like '%"+name+"%'  and ent.cityId = "+cityId;
			}
		}
		int number = dao.countQuery(hql);
		return number;
	}
	
	@Override
	public ConvenientDetail selConvenientId(int id)
	{
		ConvenientDetail user = new ConvenientDetail();
		user = (ConvenientDetail) dao.loadById(ConvenientDetail.class,id);
		return user;
	}
	
	@Override
	public City selCityId(int id)
	{
		City user = new City();
		user = (City) dao.loadById(City.class,id);
		return user;
	}

	@Override
	public Boolean delPople(int id)
	{
		boolean flag = false;
		try {
			dao.delById(Convenient.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Boolean addPople(Convenient convenitent) 
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(convenitent);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Convenient selPopleId(int id)
	{
		Convenient user = new Convenient();
		user = (Convenient) dao.loadById(Convenient.class,id);
		return user;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
}
