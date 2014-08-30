package com.era.service;

import java.util.List;

import com.era.orm.City;
import com.era.orm.CityUser;
import com.era.orm.Custom;
import com.era.orm.Village;
import com.era.util.LoginUser;

public interface CityService {
	/**
	 * 查询所有城市
	 * @param villageId 
	 * @return
	 */
	public List<City> getCityAll();
	
	/**
	 * 查询单个城市城市
	 * @return
	 */
	public City getCity(String cityName,int cityId);
	
//	public City getCityById(String cityName);
	
	/**
	 * 获取城市添加时间的最后一个时间
	 * @return
	 */
	public String getCityDate();
	
	/**
	 * 计算两个时间差
	 * @return
	 */
	public boolean compareTwoDate(String agoDate);
	
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
	 * 查询小区
	 * @param parameter
	 * @return
	 */
	public List<Village> selVillage(String parameter);

	public List<Village> selVillageOr(String parameter, Integer valueOf,
			Integer valueOf2);

	public int numberVillageOr(String parameter);

	public boolean delVillageOr(int parseInt);

	public boolean addVillage(Village vill);

	public Village selVillageById(String parameter);

	public void addOrUpdateCity(LoginUser lu,  String id,String name,
			String iphone, String villageId);

	public void delObj(Class<?> class1, Integer parseInt);

	public List<CityUser> selCityUser(Integer page, Integer rows);

	public int numberCityUser();

	public String addOrUpdateCityUser(String id, String userName,
			String passWord, String cityId);

	public String findVillageById(String id);

	public String findCityById(String id);
}
