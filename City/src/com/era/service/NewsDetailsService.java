package com.era.service;

import java.util.List;

import com.era.orm.City;
import com.era.orm.NewsDetails;
import com.era.orm.Tock;

public interface NewsDetailsService {
	
	/**
	 * 查询多条新闻资讯
	 * @param cityId
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<NewsDetails> getNewsDetailsListService(int cityId,int pageNo,int pageNum);

	/**
	 * 查询单条新闻资讯
	 * @param cityId
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<NewsDetails> getAloneNewsDetailsListService(int cityId,int id);
	/**
	 * 通过标题查询
	 * @param title
	 * @return
	 */
	public List<Object> selNews(String title,int cityId,int pageNo,int pageNum);
	/**
	 * 查询新闻的总条数
	 * @return
	 */
	public int numberNews(String title,int cityId);
	/**
	 * 通过ID删除新闻
	 * @param id
	 * @return
	 */
	public boolean delNews(int id);
	/**
	 * 添加新闻
	 * @param news
	 * @return
	 */
	public boolean addNews(NewsDetails news);
	/**
	 * 通过ID查询新闻
	 * @param id
	 * @return
	 */
	public NewsDetails selNewsId(int id);
	/**
	 * 城市名称
	 * @param id
	 * @return
	 */
	public City selCityId(int id);
	/**
	 * 查询所有手机的唯一编码
	 */
	public List<Tock> listTockAll(String cityName);
}
