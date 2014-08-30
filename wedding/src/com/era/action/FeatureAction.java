package com.era.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Checkversion;
import com.era.orm.Classifcation;
import com.era.orm.Company;
import com.era.orm.Feature;
import com.era.orm.Info;
import com.era.orm.Plan;
import com.era.service.FeatureService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FeatureAction extends ActionSupport implements ModelDriven<Feature>,
ServletRequestAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Feature model = new Feature();
	private FeatureService featureService;
	private Map<String,Object> map  = new HashMap<String,Object>();
	private List<Object> listFeature;
	/**
	 * 婚庆公司
	 * @return
	 */
	public String company()
	{
		try {
			List<Company> listCom = featureService.selCompany(request.getParameter("pageNo"),request.getParameter("pageNum"));
			if(listCom.size()>0)
			{
				map.put("def", "0");
			}
			else
			{
				map.put("def", "1");
			}
			JSONArray array = JSONArray.fromObject(listCom);
			map.put("responseCode", "0");
			map.put("lists",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 新闻资讯
	 * @return
	 */
	public String info()
	{
		try {
			List<Info> listInfo = featureService.selInfo(request.getParameter("type"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			if(listInfo.size()>0)
			{
				map.put("def", "0");
			}
			else
			{
				map.put("def", "1");
			}
			JSONArray array = JSONArray.fromObject(listInfo);
			map.put("responseCode", "0");
			map.put("lists",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		
		return SUCCESS;
	}
	/**
	 * 婚庆策划
	 * @return
	 */
	public String paln()
	{
		try {
			List<Plan> listPlan = featureService.selPlan(request.getParameter("classifcationId"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			if(listPlan.size()>0)
			{
				map.put("def", "0");
			}
			else
			{
				map.put("def", "1");
			}
			JSONArray array = JSONArray.fromObject(listPlan);
			map.put("responseCode", "0");
			map.put("lists",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 查询婚庆策划类别
	 * @return
	 */
	public String classifcation()
	{
		try {
			List<Classifcation> listClassifcation = featureService.selClassifcation();
			if(listClassifcation.size()>0)
			{
				map.put("def", "0");
			}
			else
			{
				map.put("def", "1");
			}
			JSONArray array = JSONArray.fromObject(listClassifcation);
			map.put("responseCode", "0");
			map.put("lists",array);
		} catch (Exception e) {
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 检查更新
	 * @return
	 */
	public String checkVersion()
	{
			Checkversion ck = featureService.getNewVersion(request.getParameter("appType"), request.getParameter("versionNum"));//appType对应客户端的类型，1 ios 2 android
			if(ck != null){				
				map.put("available", "1");
				map.put("force",ck.getIsUse());
				map.put("alert",ck.getComment());
				map.put("appUrl",ck.getAppUrl());
			}else{//没有更新
				map.put("available", "0");
			}	
			return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	@Override
	public Feature getModel() {
		return model;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public FeatureService getFeatureService() {
		return featureService;
	}

	public void setFeatureService(FeatureService featureService) {
		this.featureService = featureService;
	}

	public List<Object> getListFeature() {
		return listFeature;
	}

	public void setListFeature(List<Object> listFeature) {
		this.listFeature = listFeature;
	}

}
