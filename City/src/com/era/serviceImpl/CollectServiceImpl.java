package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Collect;
import com.era.service.CollectService;

public class CollectServiceImpl implements CollectService{
	
	private BaseDAO dao;
	
	@Override
	public List<Object> selCollect(int city,int pageNo, int pageSize)
	{
		String hql ="";
		if(city == 0)
		{
			hql = "select coll.id,coll.addtime,user.nickName,sell.titile from Collect coll,Seller sell,User user where coll.sellerId = sell.id and coll.userId = user.id order by coll.addtime desc";
		}
		else
		{
			hql = "select coll.id,coll.addtime,user.nickName,sell.titile from Collect coll,Seller sell,User user where coll.sellerId = sell.id and coll.userId = user.id and sell.cityId = "+city+" order by coll.addtime desc";
		}
		List<Object> list = dao.query(hql,pageNo, pageSize);
		return list;
	}
	

	@Override
	public int numberCollect(int city)
	{
		String hql ="";
		if(city == 0)
		{
			hql = "select count(*) from Collect coll,Seller sell,User user where coll.sellerId = sell.id and coll.userId = user.id";
		}
		else
		{
			hql = "select count(*) from Collect coll,Seller sell,User user where coll.sellerId = sell.id and coll.userId = user.id and sell.cityId = "+city+"";
		}
		
		int number = dao.countQuery(hql);
		return number;
	}
	
	@Override
	public boolean delCollect(int id) 
	{
		boolean flag = false;
		try {
			dao.delById(Collect.class, id);
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
