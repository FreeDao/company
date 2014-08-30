package com.era.service;

import java.util.List;

import com.era.orm.Activities;
import com.era.orm.City;
import com.era.orm.Market;
import com.era.orm.Seller;

public interface SellerService {
	/**
	 * 获取商户的所有条数
	 * @return
	 */
	public int numberSeller(String name,int sellerid);
	/**
	 * 获取全部的商户
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Seller> selSeller(String name,int typeId,int pageNo, int pageSize);
	
	/**
	 * 更具id查询商户信息
	 * @param id
	 * @return
	 */
	public Seller selOneSeller(int id);
	
	/**
	 * 根据id修改商户信息
	 * @param id
	 * @param titile
	 * @param phone
	 * @param brief
	 * @param preferential
	 * @param address
	 * @return
	 */
	public boolean updateSeller(int id,String logo,String titile,String phone,String brief,String preferential,String address,String log,String dim,String prodetId);
	public List<Activities> setListAct();
	public int selCityId(Integer sellerId);
	public List<Object> seArea(int city);
	public Market selMarketId(Integer attribute);
	public boolean addSeller(Seller seller);
	
	/**
	 * 删除商户
	 * @param sellerId
	 * @return
	 */
	public boolean delSeller(String sellerId);
	
}
