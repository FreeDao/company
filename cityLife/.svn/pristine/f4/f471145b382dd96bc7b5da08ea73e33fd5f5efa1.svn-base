package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.ConvenientDetail;
import com.era.service.ConvenientDetailService;

public class ConvenientDetailServiceImpl implements ConvenientDetailService {
	private String hql = "";
	private BaseDAO dao;

	@Override
	public List<ConvenientDetail> getConvenientDetail(int cityId, int convenientId,
			int pageNo, int pageNum) {
		hql="from ConvenientDetail where cityId="+cityId+" and convenientId="+convenientId;
		List<ConvenientDetail> list=dao.query(hql, pageNo, pageNum);
		return list;
	}

	@Override
	public int countConvenientDetail(int cityId, int convenientId) {
		hql = "select count(*) from ConvenientDetail where cityId="+cityId+" and convenientId=" + convenientId;
		int number = dao.countQuery(hql);
		return number;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
