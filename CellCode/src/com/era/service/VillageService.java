package com.era.service;

import java.util.Map;

public interface VillageService 
{
	/**
	 * 查询指定小区广告
	 * @param villageId
	 * @param map
	 */
	public void selVillageAd(String villageId, Map<String, Object> map);

	/**
	 * 根据客服id查询当前小区报修 
	 * @param userId
	 * @param pageNum 
	 * @param pageNo 
	 * @param map
	 */
	public void selGuarantee(String userId, Integer pageNo, Integer pageNum, Map<String, Object> map);

	/**
	 * 完成保修
	 * @param guaranteeId
	 * @param guaranteeId 
	 * @param map
	 */
	public void completeGuarantee(String userId, String guaranteeId, Map<String, Object> map);

	/**
	 * 评此次报修
	 * @param guaranteeId
	 * @param type
	 * @param map
	 */
	public void feedbackGuarantee(String guaranteeId, String type,
			Map<String, Object> map);
	
}
