package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.City;
import com.era.orm.NewsDetails;
import com.era.orm.Village;
import com.era.service.CityService;
import com.era.service.NewsDetailsService;
import com.era.util.BaseUtils;
import com.era.util.LoginUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class NewsDetailsAction extends ActionSupport implements
		ModelDriven<NewsDetails>, ServletRequestAware {
	private NewsDetails newsDetails = new NewsDetails();
	private HttpServletRequest request;
	private NewsDetailsService newsDetailsService;
	
	private Map<String,Object> map  = new HashMap<String,Object>();
	private String result;// 用户接收返回的json
	
	 private String rows; 
		private String page;
		
		private File picture;
		private String pictureContentType;
		private String pictureFileName;

		List<Object> list;
		
	/**
	 * 查询社区头条
	 */
	public String selNewsDetail() 
	{
		List<NewsDetails> news_list = null;
		try {
			news_list = newsDetailsService.getNewsDetailsListService(request.getParameter("villageId"), request.getParameter("pageNo"), request.getParameter("pageNum"));
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
	
	/**
	 * 后台查询社区头条
	 * @return
	 */
	public String selNewsDetailBife()
	{
		try {
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			List<NewsDetails> list=newsDetailsService.selNewsDetailBife(lu,request.getParameter("title"),request.getParameter("cityId"),request.getParameter("conent"),page,rows);
	   	 	int total=newsDetailsService.numberNewsDetailBife(lu,request.getParameter("title"),request.getParameter("cityId"),request.getParameter("conent"));
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 删除社区头条
	 * @return
	 */
	public String delNewsDetailBife()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		newsDetailsService.delNewsDetailBife(Integer.parseInt(ids[i])); 
        } 
		return SUCCESS;
	}
	
	/**
	 * 添加社区头条或通知
	 * @return
	 * @throws Exception 
	 */
	public String addNews() throws Exception
	{
		String id = request.getParameter("id");
		String author = request.getParameter("author");
		String conent = request.getParameter("conent");
		String title = request.getParameter("title");
		String type = request.getParameter("isHead");
		LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
		
		if( null != id  && !id.equals("") )
		{
			newsDetails = newsDetailsService.loadNewsDetails(id);
		}
		
		if(lu.getRole() == 5){
			newsDetails.setVillageId(Integer.parseInt(lu.getVillageId()));
		}else{
			newsDetails.setVillageId(Integer.parseInt(request.getParameter("villageId")));
		}
		
		Date date = new Date();  
		Timestamp ts = new Timestamp(date.getTime());
		newsDetails.setIsHead(Integer.parseInt(type));
		newsDetails.setAddtime(ts);
		newsDetails.setAuthor(author);
		newsDetails.setConent(conent);
		newsDetails.setTitle(title);
		newsDetails.setUserId(lu.getId());
		newsDetails.setUserRole(lu.getRole());
		newsDetailsService.addNews(newsDetails);
		return SUCCESS;
	}
	/**
	 * 查询小区
	 * @return
	 */
	public String selVillageOrId()
	{
		list = newsDetailsService.selViilage(request.getParameter("villageId"));
		return SUCCESS;
	}
	
	/**
	 * 查询当前用户小区
	 * @return
	 */
	public String selLoginUserVillage(){
		LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
		list = newsDetailsService.selLoginUserVillage(lu);
		return SUCCESS;
	}
	
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

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}
	
}
