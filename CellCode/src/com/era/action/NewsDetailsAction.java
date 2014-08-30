package com.era.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.NewsDetails;
import com.era.service.NewsDetailsService;
import com.era.util.BaseUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class NewsDetailsAction extends ActionSupport implements
		ModelDriven<NewsDetails>, ServletRequestAware {
	private NewsDetails newsDetails = new NewsDetails();
	private HttpServletRequest request;
	private NewsDetailsService newsDetailsService;
	
	private Map<String,Object> map  = new HashMap<String,Object>();
	private String result;// 用户接收返回的json

	/**
	 * 查询社区头条
	 */
	public String selNewsDetail() 
	{
		List<NewsDetails> news_list = null;
		try {
			news_list = newsDetailsService.getNewsDetailsListService(request.getParameter("notice"),request.getParameter("villageId"), request.getParameter("pageNo"), request.getParameter("pageNum"));
			if(news_list == null ||news_list.equals(""))
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", news_list);
			}
			map.put("list",news_list);
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	
	
//	/**
//	 * 查询获取 “标题” 新闻资讯
//	 */
//	public void getHeadNews() {
//		String newsId_str = request.getParameter("newsId");
//		String cityId_str = request.getParameter("cityId");
//		String pageNo_str = request.getParameter("pageNo");
//		String pageNum_str = request.getParameter("pageNum");
//		
//		System.out.println("---newsId_str---->" + newsId_str + "<---------");
//		System.out.println("---cityId_str---->" + cityId_str + "<---------");
//		System.out.println("---pageNo_str---->" + pageNo_str + "<---------");
//		System.out.println("---pageNum_str---->" + pageNum_str + "<---------");
//
//		if (cityId_str!=null && newsId_str != null ) {
//			int newsId = Integer.parseInt(newsId_str);
//			int cityId = Integer.parseInt(cityId_str);
//			
//			int pageNo = pageNo_str != null ? Integer.parseInt(pageNo_str) : 1;//默认当前第一页数据
//			int pageNum = pageNum_str != null ? Integer.parseInt(pageNum_str) : 4;//默认返回4条数据
//			
//			
//			System.out.println("---newsId---->" + newsId + "<---------");
//			System.out.println("---cityId---->" + cityId + "<---------");
//			System.out.println("---pageNo---->" + pageNo + "<---------");
//			System.out.println("---pageNum---->" + pageNum + "<---------");
//			
//			if (newsId == -1) {// All
//				List<NewsDetails> news_list = newsDetailsService.getHeadNewsDetailsListService(cityId, pageNo, pageNum);
//				JSONArray array = new JSONArray();
//				if (news_list != null && news_list.size() > 0) {
//					for(NewsDetails nd : news_list){
//						
//						JSONObject json = new JSONObject();
//						json.put("id",nd.getId());
//						json.put("title",nd.getTitle());
//						json.put("addtime",BaseUtils.getNowStringDateTime(nd.getAddtime()));
//						json.put("author",nd.getAuthor());
//						json.put("conent",nd.getConent());
//						json.put("imgsList",nd.getImgsList());
//						
//						array.add(json);
//					}
//					
//					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
//					
//				} else {
//					result = "{\"responseCode\":\"" + -2 + "\"}";
//				}
//			} else {//one
//				NewsDetails ns = newsDetailsService.getAloneNewsDetailsListService(cityId, newsId);
//				if (ns != null) {					
//					JSONArray array = JSONArray.fromObject(ns);
//					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
//				} else {
//					result = "{\"responseCode\":\"" + -2 + "\"}";
//				}
//			}
//		} else {
//			result = "{\"responseCode\":\"" + 1 + "\"}";
//		}
//		BaseUtils.responseInfo(result);
//	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public NewsDetails getModel() {
		return newsDetails;
	}

	public NewsDetails getNewsDetails() {
		return newsDetails;
	}

	public void setNewsDetails(NewsDetails newsDetails) {
		this.newsDetails = newsDetails;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public NewsDetailsService getNewsDetailsService() {
		return newsDetailsService;
	}

	public void setNewsDetailsService(NewsDetailsService newsDetailsService) {
		this.newsDetailsService = newsDetailsService;
	}


	public Map<String, Object> getMap() {
		return map;
	}


	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
