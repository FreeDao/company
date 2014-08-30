package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Activities;
import com.era.orm.City;
import com.era.orm.Collect;
import com.era.orm.Convenient;
import com.era.orm.Market;
import com.era.orm.Product;
import com.era.orm.Room;
import com.era.service.CityService;

public class CityServiceImpl implements CityService {
	private String hql = "";
	private BaseDAO dao;

	/**
	 * 查询所有城市
	 */
	@Override
	public List<City> getCityAll() {
		hql = "from City";
		List<City> list = dao.query(hql);
		return list;
	}

	/**
	 * 查询单个城市
	 */
	@Override
	public List<City> getCityById(int id) {
		if(id == 0)
		{
			hql = "from City";
		}
		else
		{
			hql = "from City where id=" + id;
		}
		List<City> list = dao.query(hql);
		return list;
	}

	@Override
	public List<City> selCity(String city, int pageNo, int pageSize)
	{
		if(city == null || city.equals(""))
		{
			hql = "from City";
		}
		else
		{
			hql = "from City where cityName='"+ city+"'";
		}
		List<City> list = dao.query(hql);
		return list;
	}

	@Override
	public int numberCity(String city) 
	{
		if(city == null || city.equals(""))
		{
			hql = "select count(*) from City";
		}
		else
		{
			hql = "select count(*) from City where cityName='%"+ city+"%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<Convenient> allProduct()
	{
		List<Convenient> list = dao.query("from Convenient");
		return list;
	}
	
	@Override
	public boolean delCity(int id)
	{
		boolean flag = false;
		try {
			dao.delById(City.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean addCity(City city)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(city);
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

	@Override
	public List<Market> getMarketType() {
		// TODO Auto-generated method stub
		String hql = "from Market ma where ma.id not IN (select m.id from Market m,BusmarSetManager bm where bm.bmsmId = m.id)";
		List<Market> ms = dao.query(hql);
		return ms;
	}

	@Override
	public List<Room> getRoomAll() {
		hql = "from Room";
		List<Room> list = dao.query(hql);
		return list;
	}

	@Override
	public List<Activities> setListAct() {
		hql = "from Activities where lucky = 0";
		List<Activities> list = dao.query(hql);
		return list;
	}
}
