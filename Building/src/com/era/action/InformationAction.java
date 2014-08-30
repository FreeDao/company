package com.era.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Information;
import com.era.service.InformationService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class InformationAction extends ActionSupport implements ModelDriven<Information>,
ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Information model = new Information();
	
	private HttpServletRequest request;
	private Map<String,Object> map  = new HashMap<String,Object>();
	private InformationService informationService;
	private List<Information> listInformation;
	/**
	 * 所有的资讯
	 * @return
	 */
	public String allInformat()
	{
		try
		{
			listInformation = informationService.allInformat(request.getParameter("pageNo"),request.getParameter("pageNum"));
			JSONArray array = JSONArray.fromObject(listInformation);
			map.put("result",array);
			map.put("responseCode", "0");
		} catch (Exception e)
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

	@Override
	public Information getModel() 
	{
		return model;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public InformationService getInformationService() {
		return informationService;
	}

	public void setInformationService(InformationService informationService) {
		this.informationService = informationService;
	}
	public List<Information> getListInformation() {
		return listInformation;
	}
	public void setListInformation(List<Information> listInformation) {
		this.listInformation = listInformation;
	}

}
