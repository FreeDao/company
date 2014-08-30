package com.era.service;

import java.util.List;

import com.era.orm.Collect;

public interface CollectService {
	/**
	 * 查询收藏
	 * 
	 * @return
	 */
	public List<Object> selCollect(int pageNo, int pageSize);

	/**
	 * 查询条数
	 * 
	 * @return
	 */
	public int numberCollect();

	/**
	 * 删除收藏
	 * 
	 * @param id
	 * @return
	 */
	public boolean delCollect(int collectId);
	
	public boolean delCollect(int userId,int sellerId);

	/**
	 * 查询用户的收藏条数
	 * 
	 * @param sellerId
	 * @return
	 */
	public int countCollect(int userId);

	/**
	 * 判断用户收藏是否重复
	 * 
	 * @param userId
	 * @return
	 */
	public Collect repeatCollect(int userId, int sellerId);

	/**
	 * 添加收藏
	 * 
	 * @param collect
	 * @return
	 */
	public Collect putCollect(Collect collect);

	/**
	 * 用户查询收藏
	 * 
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Collect> getCollect(String userId, String pageNo, String pageNum,String type);
}
