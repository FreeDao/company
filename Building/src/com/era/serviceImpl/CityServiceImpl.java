package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.City;
import com.era.orm.Type;
import com.era.service.CiytService;

public class CityServiceImpl implements CiytService 
{
	private BaseDAO dao;

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<City> allCity() {
		List<City> list = dao.query("from City");
		return list;
	}

	@Override
	public List<Type> allType() {
		List<Type> list = dao.query("from Type");
		return list;
	}
}
