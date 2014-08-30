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
	public List<Object> selComment(int pageNo, int pageSize) {
		String hql = "select coll.id,coll.addtime,user.nickName,sell.titile,coll.conent from Comment coll,Seller sell,User user where coll.sellerId = sell.id and coll.user = user.id order by coll.addtime desc";
		List<Object> list = dao.query(hql, pageNo, pageSize);
		return list;
	}

	@Override
	public int numberComment() {
		String hql = "select count(*) from Comment coll,Seller sell,User user where coll.sellerId = sell.id and coll.user = user.id order by coll.addtime desc";
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public int countComment(int sellerId) {
		hql ="select count(*) from Comment where sellerId="+sellerId;
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
	public List<Comment> getCommentInfo(int sellerId, int pageNo, int pageNum) {
		hql = "from Comment where sellerId="+sellerId+ " order by addtime desc";
		List<Comment> list = dao.query(hql,pageNo,pageNum);
		return list;
	}
	

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
