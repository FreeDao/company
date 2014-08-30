package com.era.service;

import java.util.List;

import com.era.orm.Collect;

public interface CollectService 
{
	/**
	 * 查询收藏
	 * @param userId
	 * @param supplyId
	 * @param ishave
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<Object> selectCollect(String userId,String supplyId,String ishave, String informationId,String pageNo,String pageNum);
	
	/**
	 * 添加收藏
	 * @param comment
	 * @return
	 */
	public boolean addCollect(Collect comment);
	
	/**
	 * 判断收藏是否存在
	 * @param id
	 * @return
	 */
	public boolean isCollect(String userId,String supplyId,String ishave,String informationId,String relevanceId);
	/**
	 * 删除收藏
	 * @param id
	 * @return
	 */
	public boolean delCollect(String userId,String supplyId,String ishave,String informationId,String relevanceId);
}
