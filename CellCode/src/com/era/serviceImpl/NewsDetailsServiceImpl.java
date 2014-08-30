package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.Nestable;

import com.era.dao.BaseDAO;
import com.era.orm.NewsDetails;
import com.era.service.NewsDetailsService;
import com.era.util.BaseUtils;

public class NewsDetailsServiceImpl implements NewsDetailsService {
	private String hql = "";
	private BaseDAO dao;

	/**
	 * 查询多条新闻资讯 impl
	 */
	@Override
	public List<NewsDetails> getNewsDetailsListService(String noto,String cityId, String pageNo,String pageNum) {
		hql = "from NewsDetails where villageId = " + cityId+ " and isHead = "+noto+" order by addtime desc";
		List<NewsDetails> list = null;
		if(pageNo == null || pageNo.equals("") || pageNum==null ||pageNum.equals(""))
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql, Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		List list_news = new ArrayList();
		
		for(NewsDetails nd : list){
			NewsDetails n = new NewsDetails();
			int newsId = nd.getId();
			n.setId(nd.getId());
			n.setTitle(nd.getTitle());
			n.setAddtime(nd.getAddtime());			
			n.setAuthor(nd.getAuthor());
			n.setConent(nd.getConent());
			String hql1= "select i.imgUrl from Images as i where i.type = 5 and compositeId = "+newsId;
			List<String> imgsList = dao.query(hql1);
			String str_imgsList = imgsList.toString().replace("[]","").replace("[", "").replace("]", ""); 
			str_imgsList = BaseUtils.del_space(str_imgsList);
			n.setImgsList(str_imgsList);
			list_news.add(n);
		}
		return list_news;
	}
	/**
	 * 查询单条资讯详情 impl
	 */
	@Override
	public NewsDetails getAloneNewsDetailsListService(int cityId, int id) {
		hql = "from NewsDetails where cityId = " + cityId + " and id=" + id;
		
		NewsDetails nd = (NewsDetails)dao.loadObject(hql);
		
		NewsDetails n = new NewsDetails();
		int newsId = nd.getId();
		n.setId(nd.getId());
		n.setTitle(nd.getTitle());
		n.setAddtime(nd.getAddtime());			
		n.setAuthor(nd.getAuthor());
		n.setConent(nd.getConent());
		String hql1= "select i.imgUrl from Images as i where i.type = 3 and compositeId = "+newsId;
		System.out.println("hql1 "+hql1);
		List<String> imgsList = dao.query(hql1);
		n.setImgsList(imgsList.toString().replace("[]","").replace("[", "").replace("]", ""));

		return n;
	}
	
	
	
//	/**
//	 * 查询新闻导航标题新闻
//	 */
//	@Override
//	public List<NewsDetails> getHeadNewsDetailsListService(int cityId, int pageNo,int pageNum) {
//		
//		String hql1 = "select id,title,addtime,author,conent from NewsDetails where cityId = "+ cityId +" and isHead = 1 ORDER BY addtime DESC";
//		
//		String hql2 = "select id,title,addtime,author,conent from NewsDetails where cityId = " + cityId+ " order by addtime desc";
//		
//		
//		List<Object[]> list = dao.query(hql1, pageNo, pageNum);
//		
//		/*
//		 * 1、如果
//		 * 
//		 */
//		if(list == null || list.size()==0|| list.isEmpty()){
//			
//			list = dao.query(hql2, pageNo, pageNum);
//		}
//		
//		List list_news = new ArrayList();
//		
//		for(Object[] object : list){				
//			NewsDetails n = new NewsDetails();
//			int newsId = Integer.parseInt(object[0]+"");
//			
//			n.setId(newsId);
//			n.setTitle(object[1]+"");
//			n.setAddtime(BaseUtils.getNowStringDateTime(object[2]+""));			
//			n.setAuthor(object[3]+"");
//			n.setConent(object[4]+"");
//			String hql3= "select i.imgUrl from Images as i where i.type = 3 and compositeId = "+newsId;
//			System.out.println("hql1 "+hql3);
//			List<String> imgsList = dao.query(hql3);
//			String str_imgsList = imgsList.toString().replace("[]","").replace("[", "").replace("]", ""); 
//			str_imgsList = BaseUtils.del_space(str_imgsList);
//			n.setImgsList(str_imgsList);
//			list_news.add(n);
//		}
//		
//		return list_news;
//	}

	/**
	 * 统计当前对应城市的所有新闻资讯条数
	 */
	@Override
	public int countNewsDetail(int cityId) {
		hql = "select count(*) from NewsDetails where cityId="+cityId;
		int number = dao.countQuery(hql);
		return number;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
