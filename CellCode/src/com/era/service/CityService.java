package com.era.service;

import java.util.List;

import com.era.orm.Advertisement;
import com.era.orm.City;
import com.era.orm.Village;

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
	 * @param dim 
	 * @param log 
	 * @param pageNum 
	 * @param pageNo 
	 * @param string 
	 * @return
	 */
	public List<Village> selVillage(String keyword, String parameter, String log, String dim, String pageNo, String pageNum);

	/**
	 * 查询小区广告
	 * @param parseInt
	 * @return
	 */
	public List<Advertisement> findAdvertisement(int parseInt);
}
