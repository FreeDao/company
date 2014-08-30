package com.era.service;

import java.util.List;

import com.era.orm.SellerManager;

public interface SellerManagService 
{
	/**
	 * 添加商户管理
	 * @param manager
	 * @return
	 */
	public boolean addSellerManager(SellerManager manager);
	/**
	 * 查询商户会员
	 * @return
	 */
	public List<Object> listSellerManager(String seller,int city,int pageNo,int pageNum);
	
	/**
	 * 计算会员后台总数
	 * @return
	 */
	public Integer numberSellerManager(String seller,int city);
	/**
	 * 删除商户会员
	 * @param id
	 * @return
	 */
	public boolean delSellerManager(Integer id);
	
	/**
	 * 通过ID查询实体类
	 * @param id
	 * @return
	 */
	public SellerManager selSellerManager(int id);
	/**
	 * 查询所有商户信息
	 * @return
	 */
	public List<Object> findSeller();

}
