package com.era.service;

import java.util.List;

import com.era.orm.NewsDetails;

public interface NewsDetailsService {
	
	/**
	 * 查询多条新闻资讯
	 * @param cityId
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<NewsDetails> getNewsDetailsListService(String noto,String cityId,String pageNo,String pageNum);

	/**
	 * 查询单条新闻资讯
	 * @param cityId
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public NewsDetails getAloneNewsDetailsListService(int cityId,int id);
	
	/**
	 * 统计当前对应城市的所有新闻资讯
	 * @param cityId
	 * @return
	 */
	public int countNewsDetail(int cityId);
	
	/**
	 * 查询头部新闻资讯
	 * @param cityId
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
//	public List<NewsDetails> getHeadNewsDetailsListService(int cityId,int pageNo,int pageNum);
}
