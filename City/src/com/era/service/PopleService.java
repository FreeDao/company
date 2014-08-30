package com.era.service;

import java.util.List;

import com.era.orm.City;
import com.era.orm.Convenient;
import com.era.orm.ConvenientDetail;

public interface PopleService 
{
	/**
	 * 查询便民
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Object> selPople(String name,int cityId,int pageNo, int pageSize);
	/**
	 * 所有便民的条数
	 * @param name
	 * @return
	 */
	public Integer numberPople(String name,int cityId);
	/**
	 * 通过Id删除便民
	 * @param id
	 * @return
	 */
	public Boolean delPople(int id);
	/**
	 * 添加便民
	 * @param convenitent
	 * @return
	 */
	public Boolean addPople(Convenient convenitent);
	/**
	 * 通过ID查询便民
	 * @return
	 */
	public Convenient selPopleId(int id);
	
	/**
	 * 查看便民详情
	 * @param id
	 * @return
	 */
	public ConvenientDetail selConvenientId(int id);
	/**
	 * 查询城市
	 * @param id
	 * @return
	 */
	public City selCityId(int id);
}
