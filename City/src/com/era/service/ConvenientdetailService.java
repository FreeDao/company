package com.era.service;

import java.util.List;

import com.era.orm.Convenient;
import com.era.orm.ConvenientDetail;


public interface ConvenientdetailService
{
	/**
	 * 查询便民表详情
	 * @param name
	 * @return
	 */
	public List<Object> selConvenientdetail(String name,int cityId,int pageNo, int pageSize);
	/**
	 * 查询所有条数
	 * @param name
	 * @return
	 */
	public Integer numberConvenientdetail(String name,int cityId);
	/**
	 * 通过ID查询便民详情
	 * @param id
	 * @return
	 */
	public ConvenientDetail selConvenientdetailId(int id);
	/**
	 * 添加便民详情
	 * @return
	 */
	public boolean addConvenientdetail(ConvenientDetail detail);
	/**
	 * 删除便民详情
	 * @param id
	 * @return
	 */
	public boolean delConvenientdetail(int id);
	/**
	 * 查询所有城市信息
	 * @return
	 */
	public List<Convenient> selConveientCity(String type);
}
