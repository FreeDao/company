package com.era.service;

import java.io.File;
import java.util.List;

import com.era.orm.Busmarset;
import com.era.orm.Guarantee;
import com.era.orm.Market;
import com.era.orm.VillageManager;
import com.era.util.BusMarketQuery;
import com.era.util.LoginUser;

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
	public List<Market> getMarketList(String cityId,String pageNo,String pageNum,String type);

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

	public boolean addGuarantee(Guarantee tee);

	public int numberMarekt(String parameter, String parameter2);

	public boolean delMarekt(int parseInt);

	public void saveOrUpdateCommodity(LoginUser lu,String id, String title, String phone,
			String otherPrice, String price, String villageId, String shopId,
			String shopIdTwo, String brief,String level, String logo);

	public List<Guarantee> getGuaranteeList(String villageId, String page,
			String rows);

	public int numberGuarantee(String villageId);

	public List<BusMarketQuery> getBusMarketList(LoginUser lu, String page, String rows);

	public Integer numberBusMarekt(LoginUser lu);

	public boolean saveOrUpdateBusMarket(LoginUser lu, String id, String userName,
			String passWord, String bmsUserName, String telephone, String qq,
			String email, String city, String villageId);

	public void delBusMarket(Integer parseInt);

	public List<VillageManager> getVillageManagerList(LoginUser lu,
			String page, String rows);

	public Integer numberVillageManager(LoginUser lu);

	public boolean saveOrUpdateVillageManager(LoginUser lu, String id,
			String userName, String passWord, String villageId);

	public void delVillageManager(Integer parseInt);
	
}
