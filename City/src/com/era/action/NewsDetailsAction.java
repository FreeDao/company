package com.era.action;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;
import javapns.exceptions.DuplicateDeviceException;
import javapns.exceptions.NullDeviceTokenException;
import javapns.exceptions.NullIdException;
import javapns.exceptions.UnknownDeviceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONException;

import com.era.orm.City;
import com.era.orm.NewsDetails;
import com.era.orm.Tock;
import com.era.service.CityService;
import com.era.service.NewsDetailsService;
import com.era.util.BaseUtils;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class NewsDetailsAction extends ActionSupport implements
		ModelDriven<NewsDetails>, ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NewsDetails newsDetails = new NewsDetails();
	private HttpServletRequest request;
	private NewsDetailsService newsDetailsService;
	private Map<String,Object> map = new HashMap<String, Object>();
	private CityService cityService;
	
	private List<Object> list;
	private List<City> listCity;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String titleOne;

	/**
	 * 查询所有的新闻
	 * @return
	 */
	public String selNews()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		pagecount = newsDetailsService.numberNews(request.getParameter("title"),city);
		titleOne = request.getParameter("title");
		if(pagecount<15){
			pageCount=1;
		}else{
			pageCount = pagecount/15;
			int i  = pagecount%15;
			if(i>0)
			{
				pageCount+=1;
			}
		}
		if (pagenum < 1) {
			pagenum = 1;
		}
		if (pagenum > pagecount) {
			if(pagecount == 0)
			{
				
			}
			else
			{
				pagenum = pageCount;
			}
		}
		list = newsDetailsService.selNews(request.getParameter("title"),city,pagenum,15);
		return SUCCESS;
	}
	/**
	 * 删除新闻
	 * @return
	 * @throws IOException 
	 */
	public String delNews() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = newsDetailsService.delNews(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
			selNews();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 添加修改新闻
	 * @return
	 * @throws Exception 
	 */
	public String addNews() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String data = "";
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			newsDetails.setId(null);
		}
		else
		{
			newsDetails.setId(Integer.valueOf(request.getParameter("id")));
		}
		newsDetails.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
		newsDetails.setAuthor(request.getParameter("author"));
		newsDetails.setCityId(Integer.valueOf(request.getParameter("city")));
		newsDetails.setConent(request.getParameter("conent"));
		newsDetails.setTitle(request.getParameter("title"));
		boolean bool = newsDetailsService.addNews(newsDetails);
		if(bool)
		{
			data = "{mess:\"1\"}";
			
		}
		else
		{
			data = "{mess:\"2\"}";
		}
		response.getWriter().write(data);
		return null;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addNewsPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		newsDetails = null;
		listCity = cityService.getCityById(city);
		return SUCCESS;
	}
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String updateNewsPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		newsDetails = newsDetailsService.selNewsId(Integer.valueOf(request.getParameter("id")));
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
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

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getPagesum() {
		return pagesum;
	}

	public void setPagesum(int pagesum) {
		this.pagesum = pagesum;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getTitleOne() {
		return titleOne;
	}
	public void setTitleOne(String titleOne) {
		this.titleOne = titleOne;
	}
	public List<City> getListCity() {
		return listCity;
	}
	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}
	public CityService getCityService() {
		return cityService;
	}
	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}
}
