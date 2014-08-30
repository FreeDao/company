package com.era.service;

import java.util.List;

/**
 * @author jiajiantao
 * @fileName  查询区域接口
 * 			  AreaService.java
 * @packageName com.era.service
 * @createTime 2013-7-4上午11:27:03
 * @updateTime 2013-7-4上午11:27:03
 * @version V1.0
 *
 */
public interface AreaService {
	
	/**
	 * 根据城市Id查询下面对应行政区域
	 * @param cityId
	 * @return
	 */
	public List getAreaById(int cityId);
	
	
	/**
	 * 查询所有区域当中最后一次操作时间
	 */
	public String getAreaDate();
	
	
	/**
	 * 计算两个时间差
	 * @return
	 */
	public boolean compareTwoDate(String agoDate);

}
