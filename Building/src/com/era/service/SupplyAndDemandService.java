package com.era.service;

import java.util.List;

import com.era.orm.City;
import com.era.orm.Seller;
import com.era.orm.SupplyAndDemand;
import com.era.orm.Type;

public interface SupplyAndDemandService 
{
	/**
	 * 查询供求信息
	 * @return
	 */
	public List<SupplyAndDemand> selSupply(String typeId,String cityId,String supply,String isfinish,String userId,String type,String pageNo,String pageNum);
	
	/**
	 * 发布供应信息
	 * @param supplyAndDemand
	 * @return
	 */
	public boolean addSupply(SupplyAndDemand supplyAndDemand);
	
	public boolean addDemandInfo(SupplyAndDemand demand);
	
	public boolean deleteOrUpdate(String isfinish,String userId,String supplyId,String relevanceId);
	
	/**
	 * 根据城市名称查询城市编号
	 * @param cityName
	 * @return
	 */
	public City geCityIdByName(String cityName);
	
	/**
	 * 根据类型名称查询类型编号
	 * @param typeName
	 * @return
	 */
	public Type getTypeIdByName(String typeName);
	
	/**
	 * 通过商户编号查询地址
	 * @param sellerId
	 * @return
	 */
	public Seller getAddressBySellerId(int sellerId);
	
	
}
