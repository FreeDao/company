package com.era.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Area;
import com.era.service.AreaService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AreaAction extends ActionSupport implements ModelDriven<Area>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Area area = new Area();
	private List<Area> listArea;
	private AreaService areaService;
	private Map<String,Object> map  = new HashMap<String,Object>();
	
	/**
	 * 查询所有区域
	 * @return
	 */
	public String areaAll()
	{
		try {
				listArea = areaService.areaAll(request.getParameter("pageNo"),request.getParameter("pageNum"));
				int number = areaService.numArea();
				if(listArea.size()>0)
				{
					map.put("def", "0");
				}
				else
				{
					map.put("def", "1");
				}
				JSONArray array = JSONArray.fromObject(listArea);
				map.put("num", number);
				map.put("responseCode", "0");
				map.put("lists",array);
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

	@Override
	public Area getModel()
	{
		return area;
	}

	public List<Area> getListArea() {
		return listArea;
	}

	public void setListArea(List<Area> listArea) {
		this.listArea = listArea;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
