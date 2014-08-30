package com.era.service;

import java.util.List;

import com.era.orm.City;
import com.era.orm.Type;

public interface CiytService
{
	/**
	 * 查询所有的地区
	 * @return
	 */
	public List<City> allCity();
	/**
	 * 查询所有的类型
	 * @return
	 */
	public List<Type> allType();
}
