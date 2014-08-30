package com.era.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.service.VillageService;
import com.opensymphony.xwork2.ActionSupport;

public class VillageAction extends ActionSupport implements ServletRequestAware{

	private VillageService villageService;
	private HttpServletRequest request;
	private Map<String,Object> map  = new HashMap<String,Object>();
	private Integer pageNo;
	private Integer pageNum;
	
	/**
	 * 查询小区广告
	 * @return
	 */
	public String selVillageAd(){
		try {
			
			String villageId = request.getParameter("villageId");
			villageService.selVillageAd(villageId,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("responseCode", "1");
			map.put("msg", "查询出错");
		}
		return SUCCESS;
	}
	
	/**
	 * 客服查询小区保修
	 * @return
	 */
	public String selGuarantee(){
		try {
			
			String userId = request.getParameter("userId");
			
			villageService.selGuarantee(userId,pageNo,pageNum,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("responseCode", "1");
			map.put("msg", "查询出错");
		}
		return SUCCESS;
	}
	
	/**
	 * 完成保修
	 * @return
	 */
	public String completeGuarantee(){
		try {
			
			String userId = request.getParameter("userId");
			String guaranteeId = request.getParameter("guaranteeId");
			
			villageService.completeGuarantee(userId,guaranteeId,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("responseCode", "1");
			map.put("msg", "查询出错");
		}
		return SUCCESS;
	}
	
	/**
	 * 评价报修
	 * @return
	 */
	public String feedbackGuarantee(){
		try {
			
			String guaranteeId = request.getParameter("guaranteeId");
			String type = request.getParameter("type");
			
			villageService.feedbackGuarantee(guaranteeId,type,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("responseCode", "1");
			map.put("msg", "查询出错");
		}
		return SUCCESS;
	}

	public VillageService getVillageService() {
		return villageService;
	}

	public void setVillageService(VillageService villageService) {
		this.villageService = villageService;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public Integer getPageNo() {
		return null == pageNo ? 0 : pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageNum() {
		return null == pageNum ? 0 : pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	
}
