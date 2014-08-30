package com.era.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.City;
import com.era.orm.Type;
import com.era.service.CiytService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CityAction extends ActionSupport implements ModelDriven<City>,
ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private City model = new City();
	private Map<String,Object> map  = new HashMap<String,Object>();
	private CiytService cityService;
	
	/**
	 * 所有的城市
	 * @return
	 */
	public String allCity()
	{
		try {
			List<City> listCity = cityService.allCity();
			JSONArray array = JSONArray.fromObject(listCity);
			map.put("responseCode", "0");
			map.put("result",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 查询所有的类型
	 * @return
	 */
	public String allType()
	{
		try {
			List<Type> listCity = cityService.allType();
			JSONArray array = JSONArray.fromObject(listCity);
			map.put("responseCode", "0");
			map.put("result",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
		
	}

	public City getModel() 
	{
		return model;
	}

	public HttpServletRequest getRequest() 
	{
		return request;
	}

	public void setRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	public void setModel(City model)
	{
		this.model = model;
	}


	public Map<String, Object> getMap() {
		return map;
	}


	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


	public CiytService getCityService() {
		return cityService;
	}


	public void setCityService(CiytService cityService) {
		this.cityService = cityService;
	}

}
