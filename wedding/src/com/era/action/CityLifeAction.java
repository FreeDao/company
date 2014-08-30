package com.era.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Seller;
import com.era.service.CityLifeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CityLifeAction  extends ActionSupport implements ModelDriven<Seller>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Map<String,Object> map = new HashMap<String,Object>();
	private Seller model = new Seller();
	private CityLifeService cityLifeService;
	
	/**
	 * 查询酒店
	 * @return
	 */
	public String hotelAll()
	{
		try 
		{
			List<Seller> listSeller = cityLifeService.selSeller(request.getParameter("dim"),request.getParameter("lat"),request.getParameter("type"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			if(listSeller.size()>0)
			{
				map.put("def", "0");
			}
			else
			{
				map.put("def", "1");
			}
				map.put("responseCode","0");
				JSONArray array = JSONArray.fromObject(listSeller);
				map.put("lists", array);
		} 
		catch (Exception e) 
		{
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	public Map<String, Object> getMap() {
		return map;
	}


	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


	@Override
	public Seller getModel() {
		return model;
	}


	public CityLifeService getCityLifeService() {
		return cityLifeService;
	}


	public void setCityLifeService(CityLifeService cityLifeService) {
		this.cityLifeService = cityLifeService;
	}

}
