package com.gre.in.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.common.BaseAction;
import com.gre.common.PageUtil;
import com.gre.common.model.Business;
import com.gre.common.model.Company;
import com.gre.common.model.Exhibition;
import com.gre.common.model.Job;
import com.gre.common.model.Law;
import com.gre.common.model.Pharmacy;
import com.gre.common.model.Recruit;
import com.gre.common.model.Trade;
import com.gre.common.model.Type;
import com.gre.in.service.QueryService;

@Component("queryAction")
@Scope("prototype")
public class QueryAction extends BaseAction{

	private Map<String, Object> map = new HashMap<String, Object>();
	
	@Autowired
	private QueryService queryService;
	
	/**
	 * 查询医药商机信息列表
	 * @return
	 */
	public String selBusiness(){
		try {
			String type = request.getParameter("type");
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findBusinessList(type,pageNow,limit);
			
			JSONArray ja = JSONArray.fromObject(pu.getData());
			
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("results", ja);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询所有医药商机出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询招聘信息列表
	 * @return
	 */
	public String selRecruit(){
		try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllListByObject(Recruit.class,pageNow,limit);
			
			JSONArray ja = JSONArray.fromObject(pu.getData());
			
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("results", ja);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询招聘信息出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}

	/**
	 * 查询求职信息列表
	 * @return
	 */
	public String selJob(){
		try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllListByObject(Job.class,pageNow,limit);
			
			JSONArray ja = JSONArray.fromObject(pu.getData());
			
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("results", ja);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询求职信息出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询特药企业信息列表
	 * @return
	 */
	public String selCompany(){
		try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllListByObject(Company.class,pageNow,limit);
			
			JSONArray ja = JSONArray.fromObject(pu.getData());
			
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("results", ja);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询特药企业出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询行业动态信息列表
	 * @return
	 */
	public String selTrade(){
		try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllListByObject(Trade.class,pageNow,limit);
			
			JSONArray ja = JSONArray.fromObject(pu.getData());
			
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("results", ja);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询行业动态出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询药房动态信息列表
	 * @return
	 */
	public String selPharmacy(){
		try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllListByObject(Pharmacy.class,pageNow,limit);
			
			JSONArray ja = JSONArray.fromObject(pu.getData());
			
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("results", ja);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询药房动态出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询展览展会信息列表
	 * @return
	 */
	public String selExhibition(){
		try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllListByObject(Exhibition.class,pageNow,limit);
			
			JSONArray ja = JSONArray.fromObject(pu.getData());
			
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("results", ja);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询展览展会信息出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询招标信息列表
	 * @return
	 */
	public String selInvitation(){
		try {
			String type = request.getParameter("type");
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findInvitationList(type,pageNow,limit);
			
			JSONArray ja = JSONArray.fromObject(pu.getData());
			
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("results", ja);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询招标信息出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}

	/**
	 * 查询招标信息列表
	 * @return
	 */
	public String selLaw(){
		try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllListByObject(Law.class, pageNow, limit);
			
			JSONArray ja = JSONArray.fromObject(pu.getData());
			
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("results", ja);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询招标信息出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询类型信息列表
	 * @return
	 */
	public String selType(){
		try {
			List<Type> types = (List<Type>) queryService.findAllListByObject(Type.class);
			
			JSONArray ja = JSONArray.fromObject(types);
			
			map.put("results", ja);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询类型信息出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询类型信息列表
	 * @return
	 */
	public String test(){
		try {
			map.put("results", "ok");
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询类型信息出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
