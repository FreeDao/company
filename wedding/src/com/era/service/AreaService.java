package com.era.service;

import java.util.List;

import com.era.orm.Area;

public interface AreaService 
{
	/**
	 * 查询全部区域
	 * @return
	 */
	public List<Area> areaAll(String pageNo,String pageNum);
	/**
	 * 城市的条数
	 * @return
	 */
	public Integer numArea();
}
