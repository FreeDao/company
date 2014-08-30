package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Images;
import com.era.service.ImagesService;

public class ImagesServiceImpl implements ImagesService{
	private BaseDAO dao;
	
	@Override
	public int numberImage(String id,String type)
	{
		String hql="";
		if(id == null || id.equals("") || type.equals("0") || type == "0")
		{
			hql = "select count(*) from Images";
		}
		else
		{
			hql="select count(*) from Images where compositeId = "+Integer.valueOf(id)+" and type = "+Integer.valueOf(type);
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<Images> selImage(String id,String type, int pageNo, int pageSize)
	{
		String hql="";
		if(id == null || id.equals("") || type.equals("0") || type == "0")
		{
			hql = "from Images";
		}
		else
		{
			hql="from Images where compositeId = "+Integer.valueOf(id)+" and type = "+Integer.valueOf(type);
		}
		List<Images> list = dao.query(hql,pageNo, pageSize);
		return list;
	}

	@Override
	public boolean delImages(int id)
	{
		boolean flag = false;
		try {
			dao.delById(Images.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean addImages(Images images)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(images);
			flag = true;
		} catch (Exception e) {
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
}
