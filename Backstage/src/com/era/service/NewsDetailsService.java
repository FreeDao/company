package com.era.service;

import java.util.List;

import com.era.orm.Village;
import com.era.orm.NewsDetails;
import com.era.util.LoginUser;

public interface NewsDetailsService {
	
	/**
	 * 查询多条新闻资讯
	 * @param cityId
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<NewsDetails> getNewsDetailsListService(String cityId,String pageNo,String pageNum);

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

	public List<NewsDetails> selNewsDetailBife(LoginUser lu, String parameter,
			String parameter2, String parameter3, String page, String rows);

	public int numberNewsDetailBife(LoginUser lu, String parameter, String parameter2,
			String parameter3);

	public boolean delNewsDetailBife(int parseInt);

	public boolean addNews(NewsDetails newsDetails);

	public List<Object> selViilage(String parameter);

	public NewsDetails loadNewsDetails(String id);

	public List<Object> selLoginUserVillage(LoginUser lu);
	
	/**
	 * 查询头部新闻资讯
	 * @param cityId
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
//	public List<NewsDetails> getHeadNewsDetailsListService(int cityId,int pageNo,int pageNum);
}
