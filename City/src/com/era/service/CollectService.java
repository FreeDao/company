package com.era.service;

import java.util.List;

import com.era.orm.Collect;

public interface CollectService
{
	/**
	 * 查询收藏
	 * @return
	 */
	public List<Object> selCollect(int city,int pageNo, int pageSize);
	
	/**
	 * 查询条数
	 * @return
	 */
	public int numberCollect(int city);
	/**
	 * 删除收藏
	 * @param id
	 * @return
	 */
	public boolean delCollect(int id);
}
