package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Comment;
import com.era.orm.Comment;
import com.era.orm.Images;
import com.era.service.CommentService;
import com.era.util.BaseUtils;

public class CommentServiceImpl implements CommentService {
	private BaseDAO dao;
	private String hql = "";

	@Override
	public List<Comment> selComment(String name,int pageNo, int pageSize) 
	{
		String hql = "from Comment where 1=1";
		if(name == null || name.equals(""))
		{
			
		}
		else
		{
			hql+=" and conent like '%"+name+"%'";
		}
		hql+=" order by addtime desc";
		List<Comment> list = dao.query(hql, pageNo, pageSize);
		return list;
	}

	@Override
	public int numberComment(String name) {
		String hql = "select count(*) from Comment where 1 = 1";
		if(name == null || name.equals(""))
		{
			
		}
		else
		{
			hql+=" and conent like '%"+name+"%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public int countComment(int sellerId) {
		hql ="select count(*) from Comment as c where c.sellerId="+sellerId;
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean putComment(Comment comment) {
		boolean flag = false;
		try {
			dao.save(comment);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean delComment(int id) {
		boolean flag = false;
		try {
			dao.delById(Comment.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Comment> getComment(int sellerId, int pageNo, int pageNum) {
		hql = "from Comment where sellerId=" + sellerId+ " order by addtime desc";
		List<Comment> list = dao.query(hql, pageNo, pageNum);
		return list;
	}
	
	/**
	 * 查询获取对应商家评论列表
	 */
	@Override
	public List<Comment> getCommentInfo(String type,int sellerId, int pageNo, int pageNum) {
		hql = "select c.id,c.conent,c.addtime,c.level,c.user from Comment as c where c.sellerId="+sellerId+ " and c.type="+type+" order by c.addtime desc";
		List<Object[]> list = dao.query(hql,pageNo,pageNum);
		List<Comment> list_com = new ArrayList();
		for(Object[] object : list){
			Comment co = new Comment();
			co.setId(Integer.parseInt(object[0]+""));
			String user = object[4]+"";
			if(BaseUtils.isIp(user)){
				user = "游客";
			}else{
				user = user+"";
			}			
			co.setNickName(user);
			co.setLevel(Double.parseDouble(object[3]+""));
			co.setConent(object[1]+"");
			co.setAddtime(object[2]+"");
			list_com.add(co);
		}
		return list_com;
	}
	

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
