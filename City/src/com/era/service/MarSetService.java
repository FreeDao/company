package com.era.service;

import java.util.List;

import com.era.orm.BusmarSet;

public interface MarSetService 
{
	/**
	 * 添加市场入住
	 * @return
	 */
	public boolean addMarSet(BusmarSet marKet);
	/**
	 * 删除市场入住
	 * @param id
	 * @return
	 */
	public boolean delMarSet(Integer id);
	/**
	 * 查询市场入住
	 * @param name
	 * @return
	 */
	public List<Object> listMarSet(String name,int city,int pageNo, int pageSize);
	/**
	 * 查询条数
	 * @return
	 */
	public int numberAdmin(String name,int city);
	
	/**
	 * 通过id查询市场入住
	 * @param id
	 * @return
	 */
	public BusmarSet marSetId(int id);
}
