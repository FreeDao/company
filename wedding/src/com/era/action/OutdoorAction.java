package com.era.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Outdoor;
import com.era.service.OutdoorService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OutdoorAction extends ActionSupport implements ModelDriven<Outdoor>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	private Outdoor model = new Outdoor();
	
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	
	private OutdoorService outdoorService;
	private Map<String,Object> map  = new HashMap<String,Object>();
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	
	/**
	 * 查询全部的实景
	 * @return
	 */
	public String outdoorAll()
	{
		try {
			List<Outdoor> list = outdoorService.outdoorAll(request.getParameter("pageNo"),request.getParameter("pageNum"),request.getParameter("userId"));
			if(list == null || list.equals(""))
			{
				map.put("responseCode", "1");
				return SUCCESS;
			}
			if(list.size()>0)
			{
				map.put("def", "0");
			}
			else
			{
				map.put("def", "1");
			}
			JSONArray array = JSONArray.fromObject(list);
			map.put("responseCode", "0");
			map.put("lists",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	@Override
	public Outdoor getModel() {
		return model;
	}

	public OutdoorService getOutdoorService() {
		return outdoorService;
	}

	public void setOutdoorService(OutdoorService outdoorService) {
		this.outdoorService = outdoorService;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
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

}
