package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Comment;
import com.era.service.CommentService;

public class CommentServiceImpl implements CommentService 
{
	private BaseDAO dao;
	

	@Override
	public boolean addComment(Comment comment)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(comment);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	@Override
	public List<Comment> listComment(int id,String pageNo,String pageNum) 
	{
		List<Comment> list = null;
		if(pageNo == null ||pageNo.equals(""))
		{
			list = dao.query("from Comment where featureId = "+id);
		}
		else
		{
			list = dao.query("from Comment where featureId = "+id,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		}
		return list;
	}
	
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
}
