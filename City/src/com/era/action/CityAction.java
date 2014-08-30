package com.era.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.City;
import com.era.service.CityService;
import com.era.util.BaseUtils;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CityAction extends ActionSupport implements ModelDriven<City>,
		ServletRequestAware {
	private City city = new City();
	private HttpServletRequest request;
	private CityService cityService;
	private Integer cityId;// 城市id
	private String result;// 用户接收返回的json
	private List<City> list;
	private String cityOne;
	
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;

	public void getCity() {
		String cityId_str = request.getParameter("cityId");
		System.out.println("------------------cityId----------------->"+ cityId_str);
		cityId = cityId_str != null ? Integer.parseInt(cityId_str) : -1;
		if (cityId != null) {
			if (cityId == -1) {
				// 查询所有城市
				List<City> list = cityService.getCityAll();
				if (list != null && list.size() > 0) {
					JSONArray array = JSONArray.fromObject(list);
					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"
							+ array.toString() + "}";
				} else {
					result = "{\"responseCode\":\"" + 1 + "\"}";
				}
			} else {
				// 查询单个城市
				List<City> list = cityService.getCityById(cityId);
				if (list != null && list.size() > 0) {
					JSONArray array = JSONArray.fromObject(list);
					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"
							+ array.toString() + "}";
				} else {
					result = "{\"responseCode\":\"" + 1 + "\"}";
				}
			}
		} else {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}
	
	/**
	 * 查询所有所有城市
	 * @return
	 */
	public String selCity()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		pagecount = cityService.numberCity(request.getParameter("city"));
		cityOne = request.getParameter("city");
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
		list = cityService.selCity(request.getParameter("city"),pagenum,15);
		return SUCCESS;
	}
	
	/**
	 * 删除城市
	 * @return
	 * @throws IOException 
	 */
	public String delCity() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = cityService.delCity(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			 selCity();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加城市
	 * @return
	 */
	public String addCityPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		return SUCCESS;
	}
	
	/**
	 * 添加城市
	 * @return
	 * @throws IOException 
	 */
	public String addCity() throws IOException
	{
		city.setCityName(request.getParameter("city"));
		boolean bool = cityService.addCity(city);
		if(bool)
		{
			alertInFo.alertReturn("添加成功");
			selCity();
		}
		else
		{
			alertInFo.alertReturn("添加异常");
		}
		return SUCCESS;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	@Override
	public City getModel() {
		return city;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public List<City> getList() {
		return list;
	}

	public void setList(List<City> list) {
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

	public String getCityOne() {
		return cityOne;
	}

	public void setCityOne(String cityOne) {
		this.cityOne = cityOne;
	}

}
