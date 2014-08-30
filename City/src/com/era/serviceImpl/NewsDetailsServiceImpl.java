package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.City;
import com.era.orm.NewsDetails;
import com.era.orm.Tock;
import com.era.service.NewsDetailsService;

public class NewsDetailsServiceImpl implements NewsDetailsService {
	private String hql = "";
	private BaseDAO dao;

	/**
	 * 查询多条新闻资讯 impl
	 */
	@Override
	public List<NewsDetails> getNewsDetailsListService(int cityId, int pageNo,
			int pageNum) {
		hql = "from NewsDetails where cityId = " + cityId+ " order by addtime desc";
		List<NewsDetails> list = dao.query(hql, pageNo, pageNum);

		return list;
	}
	/**
	 * 查询单条资讯详情 impl
	 */
	@Override
	public List<NewsDetails> getAloneNewsDetailsListService(int cityId, int id) {
		hql = "from NewsDetails where cityId = " + cityId + " and id=" + id;
		List<NewsDetails> list =dao.query(hql);
		return list;
	}
	
	@Override
	public List<Object> selNews(String title,int cityId, int pageNo,
			int pageNum)
	{
		if(cityId == 0)
		{
			if(title == null || title.equals(""))
			{
				hql="from NewsDetails news,City city where news.cityId = city.id order by news.addtime desc";
			}
			else
			{
				hql="from NewsDetails news,City city where news.cityId = city.id and news.title like '%"+title+"%' order by news.addtime desc";
			}
		}
		else
		{
			if(title == null || title.equals(""))
			{
				hql="from NewsDetails news,City city where news.cityId = city.id and news.cityId = "+cityId+" order by news.addtime desc";
			}
			else
			{
				hql="from NewsDetails news,City city where news.cityId = city.id and news.title like '%"+title+"%' and news.cityId = "+cityId+" order by news.addtime desc";
			}
		}
		List<Object> list =dao.query(hql, pageNo, pageNum);
		return list;
	}
	@Override
	public int numberNews(String title,int cityId)
	{
		if(cityId == 0)
		{
			if(title == null || title.equals(""))
			{
				hql = "select count(*) from NewsDetails news,City city where news.cityId = city.id";
			}
			else
			{
				hql="select count(*) from NewsDetails news,City city where news.cityId = city.id and news.title like '%"+title+"%'";
			}
		}
		else
		{
			if(title == null || title.equals(""))
			{
				hql = "select count(*) from NewsDetails news,City city where news.cityId = city.id  and news.cityId = "+cityId+"";
			}
			else
			{
				hql="select count(*) from NewsDetails news,City city where news.cityId = city.id and news.title like '%"+title+"%'  and news.cityId = "+cityId+"";
			}
		}
		int number = dao.countQuery(hql);
		return number;
	}
	
	@Override
	public boolean delNews(int id) {
		boolean flag = false;
		try {
			dao.delById(NewsDetails.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean addNews(NewsDetails news) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(news);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public NewsDetails selNewsId(int id) {
		NewsDetails user = new NewsDetails();
		user = (NewsDetails) dao.loadById(NewsDetails.class,id);
		return user;
	}
	
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
	@Override
	public City selCityId(int id) {
		City user = new City();
		user = (City) dao.loadById(City.class,id);
		return user;
	}
	@Override
	public List<Tock> listTockAll(String cityName)
	{
		List<Tock> list =dao.query("from Tock where city = '"+cityName+"'");
		return list;
	}
}
