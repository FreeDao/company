package com.era.service;

import java.util.List;

import com.era.orm.Activities;
import com.era.orm.City;
import com.era.orm.Convenient;
import com.era.orm.Market;
import com.era.orm.Room;

public interface CityService {
	/**
	 * 查询所有城市
	 * @return
	 */
	public List<City> getCityAll();
	
	/**
	 * 查询单个城市城市
	 * @return
	 */
	public List<City> getCityById(int id);
	/**
	 * 查询全部城市或单个城市
	 */
	public List<City> selCity(String city,int pageNo, int pageSize);
	/**
	 * 查询所有城市的条数
	 * @return
	 */
	public int numberCity(String city);
	/**
	 * 删除单个城市
	 * @param id
	 * @return
	 */
	public boolean delCity(int id);
	/**
	 * 添加城市
	 * @param city
	 * @return
	 */
	public boolean addCity(City city);
	
	/**
	 * 查询全部便民
	 * @return
	 */
	public List<Convenient> allProduct();

	/**
	 * 查询出未分配账号的类型
	 * @return
	 */
	public List<Market> getMarketType();

	/**
	 * 查询所有房间对象
	 * @return
	 */
	public List<Room> getRoomAll();

	public List<Activities> setListAct();
}
