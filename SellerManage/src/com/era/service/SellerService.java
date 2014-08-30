package com.era.service;

import java.util.List;

import com.era.orm.Seller;

public interface SellerService {
	/**
	 * 获取商户的所有条数
	 * @return
	 */
	public int numberSeller(String name,String sellerid);
	/**
	 * 获取全部的商户
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Seller> selSeller(String name,String sellerid,int pageNo, int pageSize);
	
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
	public boolean updateSeller(int id,String titile,String phone,String brief,String preferential,String address,String log,String dim);
	
}
