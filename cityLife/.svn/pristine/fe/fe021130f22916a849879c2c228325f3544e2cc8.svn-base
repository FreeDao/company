package com.era.service;

import java.util.List;

import com.era.orm.Busmarset;
import com.era.orm.Market;

public interface MarketService {
	/**
	 * 查询所有的市场类型
	 * 
	 * @return
	 */
	public List<Object> selMarket(String type, int pageNo, int pageSize);

	/**
	 * 查看所有市场类型的条数
	 * 
	 * @param type
	 * @return
	 */
	public int numberMarket(String type);

	/**
	 * 通过ID删除市场类型
	 * 
	 * @param id
	 * @return
	 */
	public boolean delMarket(int id);

	/**
	 * 添加市场类型
	 * 
	 * @param market
	 * @return
	 */
	public boolean addMarket(Market market);

	/**
	 * 根据城市查询所有类型
	 * 
	 * @param cityId
	 * @return
	 */
	public List<Market> getMarketList(int cityId,int pageNo,int pageNum);

	/**
	 * 查询城市有多少个市场类型
	 * 
	 * @param cityId
	 * @return
	 */
	public int countMarket(int cityId);
	
	
	/**
	 * 根据市场类型id进行查询对应的入驻商家信息
	 * @param typeId
	 * @return
	 */
	public Busmarset getBusMarketSet(int typeId);
	/**
	 * 模糊查询市场
	 * @param markName
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<Market> selLikeMark(String markName,String cityId,String pageNo,String pageNum);
	
}
