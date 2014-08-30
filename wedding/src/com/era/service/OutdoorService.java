package com.era.service;

import java.util.List;

import com.era.orm.Outdoor;

public interface OutdoorService 
{
	/**
	 * 查询全部的实景
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<Outdoor> outdoorAll(String pageNo,String pageNum,String userId);
}
