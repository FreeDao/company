package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Comment;
import com.era.service.CommentService;

public class CommentServiceImpl implements CommentService
{
	private BaseDAO dao;
	

	@Override
	public List<Object> selComment(int cityId,int pageNo, int pageSize)
	{
		String hql = "";
		if(cityId == 0)
		{
			hql="select coll.id,coll.addtime,coll.user,sell.titile,coll.conent from Comment coll,Seller sell where coll.sellerId = sell.id order by coll.addtime desc";
		}
		else
		{
			hql="select coll.id,coll.addtime,coll.user,sell.titile,coll.conent from Comment coll,Seller sell where coll.sellerId = sell.id and sell.cityId = "+cityId+" order by coll.addtime desc";
		}
		
		List<Object> list = dao.query(hql,pageNo, pageSize);
		return list;
	}

	@Override
	public int numberComment(int cityId)
	{
		String hql = "";
		if(cityId == 0)
		{
			hql = "select count(*) from Comment coll,Seller sell where coll.sellerId = sell.id";
		}
		else
		{
			hql="select count(*) from Comment coll,Seller sell where coll.sellerId = sell.id and sell.cityId = "+cityId;
		}
		
		int number = dao.countQuery(hql);
		return number;
	}
	
	@Override
	public boolean delComment(int id)
	{
		boolean flag = false;
		try {
			dao.delById(Comment.class, id);
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
