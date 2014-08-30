package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Convenient;
import com.era.orm.ConvenientDetail;
import com.era.service.ConvenientdetailService;

public class ConvenientdetailServiceImpl implements ConvenientdetailService
{
	private BaseDAO dao;
	
	@Override
	public List<Object> selConvenientdetail(String name,int cityId,int pageNo, int pageSize)
	{
		String hql = "";
		if(cityId == 0)
		{
			if(name == null || name.equals(""))
			{
				hql = "from ConvenientDetail detail,City city where city.id = detail.cityId";
			}
			else
			{
				hql = "from ConvenientDetail detail,City city where city.id = detail.cityId and name like '%"+name+"%'";
			}
		}
		else
		{
			if(name == null || name.equals(""))
			{
				hql = "from ConvenientDetail detail,City city where city.id = detail.cityId and detail.cityId = "+cityId;
			}
			else
			{
				hql = "from ConvenientDetail detail,City city where city.id = detail.cityId and name like '%"+name+"%' and detail.cityId = "+cityId;
			}
		}
		List<Object> list = dao.query(hql,pageNo, pageSize);
		return list;
	}

	@Override
	public Integer numberConvenientdetail(String name,int cityId) 
	{
		String hql="";
		if(cityId==0)
		{
			if(name == null || name.equals(""))
			{
				hql = "select count(*) from ConvenientDetail detail,City city where city.id = detail.cityId";
			}
			else
			{
				hql = "select count(*) from ConvenientDetail detail,City city where city.id = detail.cityId and name like '%"+name+"%'";
			}
		}
		else
		{
			if(name == null || name.equals(""))
			{
				hql = "select count(*) from ConvenientDetail detail,City city where city.id = detail.cityId and detail.cityId = "+cityId;
			}
			else
			{
				hql = "select count(*) from ConvenientDetail detail,City city where city.id = detail.cityId and name like '%"+name+"%'  and detail.cityId = "+cityId;
			}
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public ConvenientDetail selConvenientdetailId(int id)
	{
		ConvenientDetail user = new ConvenientDetail();
		user = (ConvenientDetail) dao.loadById(ConvenientDetail.class,id);
		return user;
	}

	@Override
	public boolean addConvenientdetail(ConvenientDetail detail) 
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(detail);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delConvenientdetail(int id)
	{
		boolean flag = false;
		try {
			dao.delById(ConvenientDetail.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public List<Convenient> selConveientCity(String type) 
	{
		 List<Convenient> list = dao.query("from Convenient where cityId = "+type);
		return list;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
}
