package com.era.service;

import java.util.List;

import com.era.orm.Seller;

public interface CityLifeService 
{
	/**
	 * 查询所有酒店
	 * @return
	 */
	public List<Seller> selSeller(String dim,String lat,String type,String pageNo,String pageNum);
}
