package com.era.service;

import java.util.List;

import com.era.orm.Area;
import com.era.orm.BusmarSetManager;
import com.era.orm.CustomType;
import com.era.orm.Market;

public interface MarketService
{
	/**
	 * 查询所有的市场类型
	 * @return
	 */
	public List<Object> selMarket(String type,int cityId,int pageNo, int pageSize);
	/**
	 * 查看所有市场类型的条数
	 * @param type
	 * @return
	 */
	public int numberMarket(String type,int cityId);
	/**
	 * 通过ID删除市场类型
	 * @param id
	 * @return
	 */
	public boolean delMarket(int id);
	/**
	 * 添加市场类型
	 * @param market
	 * @return
	 */
	public boolean addMarket(Market market);
	/**
	 * 查询排序是否存在
	 * @return
	 */
	public int numberSort(int sort,int cityId);
	
	/**
	 * 通过id查询市场类型
	 * @return
	 */
	public Market marketId(int id);
	/**
	 * 查询全部的市场
	 * @return
	 */
	public List<Market> allMarket();
	/**
	 * 通过城市查询市场
	 * @param city
	 * @return
	 */
	public List<Market> selSellerMarket(Integer city);
	/**
	 * 查询排序
	 * @param id
	 * @param num
	 * @return
	 */
	public int numSort(int id,int num);
	/**
	 * 移动排序
	 * @return
	 */
	public Market serviceSort(String sort);
	/**
	 * 移动排序
	 * @return
	 */
	public boolean updateSort(Market market);
	/**
	 * 查询市场用户数量
	 * @param parameter
	 * @return
	 */
	public int numberMarketUser(String parameter);
	/**
	 * 查询出所有的市场用户
	 * @param parameter
	 * @param pagenum
	 * @param i
	 * @return
	 */
	public List<Object> selMarketUser(String parameter, int pagenum, int i);
	/**
	 * 查询数据库中是否已经存在此用户名
	 * @param username
	 * @return
	 */
	public boolean isHasUsername(String username);
	/**
	 * 保存市场对象
	 * @param bm
	 * @return
	 */
	public boolean addBusmarSetManager(BusmarSetManager bm);
	/**
	 * 删除对象
	 * @param username
	 * @return
	 */
	public boolean delMarketUser(int id);
	/**
	 * 检查类型是否存在
	 * @param parameter
	 * @return
	 */
	public boolean hasType(String parameter);
	/**
	 * 根据城市id查询区域
	 * @param valueOf
	 * @return
	 */
	public List<Area> selSellerArea(Integer id);
	/**
	 * 查询所有市场区域数量
	 * @param areaName 
	 * @return
	 */
	public int numberMarketArea(String areaName);
	/**
	 * 查询所有市场区域
	 * @param pagenum
	 * @param i
	 * @return
	 */
	public List<Object> selMarketArea(String areaName,int pagenum, int i);
	/**
	 * 查询用户自定义数量
	 * @return
	 */
	public int numberMarketCustomType(String typeName);
	/**
	 * 分页查找用户自定义类型列表
	 * @param pagenum
	 * @param i
	 * @return
	 */
	public List<Object> selMarketCustomType(String typeName,int pagenum, int i);
	/**
	 * 保存市场区域
	 * @param id
	 * @param cityId
	 * @param areaName
	 */
	public void saveOrUpdateMarketArea(String id, String cityId, String areaName);
	/**
	 * 删除区域
	 * @param valueOf
	 * @return
	 */
	public boolean delMarketArea(Integer id);
	/**
	 * 添加或者更新行业类别
	 * @param id
	 * @param marketId
	 * @param name
	 */
	public void saveOrUpdateMarketCustomType(String id, String marketId,
			String name);
	/**
	 * 删除行业类别
	 * @param valueOf
	 * @return
	 */
	public boolean delMarketCustomType(Integer valueOf);
	/**
	 * 根据id查询区域
	 * @param parseInt
	 */
	public Area selAreaById(int id);
	/**
	 * 根据id查询行业类别
	 * @param parseInt
	 * @return 
	 */
	public CustomType selCustomById(int id);
	/**
	 * 查询行业类别
	 * @param valueOf
	 * @return
	 */
	public List<CustomType> selCustomType(Integer id);
}
