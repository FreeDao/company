package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Convenient;
import com.era.service.ConvenientService;

public class ConvenientServiceImpl implements ConvenientService {
	private String hql = "";
	private BaseDAO dao;

	@Override
	public List<Convenient> getConvenient(int cityId) {
		hql = "from Convenient where cityId=" + cityId;
		List<Convenient> list = dao.query(hql);
		return list;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
