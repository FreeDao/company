package com.era.serviceImpl;

import com.era.dao.BaseDAO;
import com.era.service.PutCommentService;

public class PutCommentServiceImpl implements PutCommentService {
	private String hql = "";
	private BaseDAO dao;

	/**
	 * 查询商户的所有评论条数
	 */
	@Override
	public String numberComment(int sellerId) {
		hql = "select count(*) from Comment where sellerId=" + sellerId;
		int s = dao.countQuery(hql);
		return null;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
