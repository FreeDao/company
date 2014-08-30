package com.era.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Convenient;
import com.era.orm.ConvenientDetail;
import com.era.service.ConvenientDetailService;
import com.era.service.ConvenientService;
import com.era.util.BaseUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ConvenientDetailAction extends ActionSupport implements
		ModelDriven<ConvenientDetail>, ServletRequestAware {
	private ConvenientDetail model = new ConvenientDetail();
	private HttpServletRequest request;
	private ConvenientDetailService convenientDetailService;
	private ConvenientService convenientService;
	private String result;// 用于组装json

	/**
	 * 查询便民详情
	 */
	@SuppressWarnings("unused")
	public void getConvenientDetail() {
		String cityId_str = request.getParameter("cityId");
		String convenientId_str = request.getParameter("convenientId");
		String pageNo_str = request.getParameter("pageNo");
		String pageNum_str = request.getParameter("pageNum");
		

		System.out.println("----cityId_str------------->" + cityId_str);
		System.out.println("----convenientId_str------------->" + convenientId_str);
		System.out.println("----pageNo_str------------->" + pageNo_str);
		System.out.println("----pageNum_str------------->" + pageNum_str);
		
		if(cityId_str != null){	
			int cityId = Integer.parseInt(cityId_str);
			int pageNo = pageNo_str != null ? Integer.parseInt(pageNo_str):1;
			int pageNum = pageNum_str != null ? Integer.parseInt(pageNum_str):BaseUtils.DEFAULT_PAGENUM;
		
			//查询显示便民类型
			List<Convenient> list_Convenient = convenientService.getConvenient(cityId);
			
			System.out.println("----list_Convenient ------------->" + list_Convenient.size()+"<----------");
			
			if (list_Convenient != null && list_Convenient.size() > 0) {
				
				int convenientId;
				
				if(convenientId_str != null){
					convenientId = Integer.parseInt(convenientId_str);
				}else{
					convenientId = list_Convenient.get(0).getId();
				}	
				
				System.out.println("----convenientId ------------->" + convenientId +"<----------");
				
				String[] str = {"常用电话","党政机关","医疗卫生","学校教育","315诚信联盟","金融机构","新闻媒体","合作伙伴"};
				
				
				//查询显示便民类型对应下面具体内容
				List<ConvenientDetail> list_detail = convenientDetailService.getConvenientDetail(cityId,convenientId, pageNo, pageNum);
				
				System.out.println("----list_detail ------------->" + list_detail.size()+"<----------");
				
				int number = convenientDetailService.countConvenientDetail(cityId, convenientId);
				
				System.out.println("----number ------------->" + number+"<----------");
				
				double paginalNumber = Math.ceil((double) number / pageNum);
				
				JSONArray arrayConvenient = JSONArray.fromObject(list_Convenient);
				
				JSONArray arrayConvenientDetail = new JSONArray();				
				if (list_detail != null && list_detail.size() > 0) {
					
					arrayConvenientDetail = JSONArray.fromObject(list_detail);
					
					result = "{\"responseCode\":\"" + 0+ "\",\"result\":" + arrayConvenient.toString()+ ",\"results\":" + arrayConvenientDetail.toString() + "}";
				} else {
					if ((double) pageNo > paginalNumber && number > 0) {
						result = "{\"responseCode\":\"" + -1 + "\"}";
					} else {
						if(list_Convenient != null && list_Convenient.size() > 0){
							result = "{\"responseCode\":\"" + 0+ "\",\"result\":" + arrayConvenient.toString()+ ",\"results\":\"\"}";
						}else{
							result = "{\"responseCode\":\"" + -2 + "\"}";	
						}
					}
				}
			}else{
				result = "{\"responseCode\":\"" + 1 + "\"}";	
			}
		}else{
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		
		BaseUtils.responseInfo(result);
	}
	
	
	
	public void getConvenientDetailList(){
		
		String cityId_str = request.getParameter("cityId");
		String convenientId_str = request.getParameter("convenientId");
		String pageNo_str = request.getParameter("pageNo");
		String pageNum_str = request.getParameter("pageNum");
		int pageNo = pageNo_str != null ? Integer.parseInt(pageNo_str):1;
		int pageNum = pageNum_str != null ? Integer.parseInt(pageNum_str):BaseUtils.DEFAULT_PAGENUM;
		
		if(cityId_str != null){	
			int cityId = Integer.parseInt(cityId_str);
			List<Convenient> list_Convenient = convenientService.getConvenient(cityId);
			
			if (list_Convenient != null && list_Convenient.size() > 0) {
				int convenientId;
				if(convenientId_str != null){
					convenientId = Integer.parseInt(convenientId_str);
				}else{
					convenientId = list_Convenient.get(0).getId();
				}				
				
				List<ConvenientDetail> list = convenientDetailService.getConvenientDetail(cityId,convenientId, pageNo, pageNum);
				int number=convenientDetailService.countConvenientDetail(cityId, convenientId);
				double paginalNumber = Math.ceil((double) number / pageNum);
				if (list != null && list.size() > 0) {
					JSONArray arrayConvenientDetail = JSONArray.fromObject(list);
					JSONArray arrayConvenient = JSONArray.fromObject(list_Convenient);
					result = "{\"responseCode\":\"" + 0+ "\",\"result\":" + arrayConvenient.toString()+ ",\"results\":" + arrayConvenientDetail.toString() + "}";
				} else {
					if ((double) pageNo > paginalNumber && number > 0) {
						result = "{\"responseCode\":\"" + -1 + "\"}";
					} else {
						result = "{\"responseCode\":\"" + -2 + "\"}";
					}
				}
			}else{
				result = "{\"responseCode\":\"" + 1 + "\"}";	
			}
		}else{
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}
	
	public ConvenientDetail getModel() {
		return model;
	}

	public void setModel(ConvenientDetail model) {
		this.model = model;
	}

	public ConvenientDetailService getConvenientDetailService() {
		return convenientDetailService;
	}

	public void setConvenientDetailService(
			ConvenientDetailService convenientDetailService) {
		this.convenientDetailService = convenientDetailService;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ConvenientService getConvenientService() {
		return convenientService;
	}

	public void setConvenientService(ConvenientService convenientService) {
		this.convenientService = convenientService;
	}
}
