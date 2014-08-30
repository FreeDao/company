package com.era.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Collect;
import com.era.orm.Seller;
import com.era.service.CollectService;
import com.era.service.SellerService;
import com.era.util.BaseUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CollectAction extends ActionSupport implements
		ModelDriven<Collect>, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private CollectService collectService;
	private SellerService sellerService;
	private String result;// 用于组装json

	private List<Object> list;

	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;

	Collect model = new Collect();


	/**
	 * TODO 1、添加收藏
	 */
	public void putCollect() {
				Collect collect = collectService.repeatCollect(Integer.valueOf(request.getParameter("userId")),Integer.valueOf(request.getParameter("sellerId")));
				if (collect != null)
				{
					result = "{\"responseCode\":\"" + 2 + "\"}";
				}
				else 
				{// 没重复收藏
					model.setSellerId(Integer.valueOf(request.getParameter("sellerId")));
					model.setUserId(Integer.valueOf(request.getParameter("userId")));
					model.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
					boolean ct = collectService.putCollect(model);
					if (ct) {
						JSONArray array = JSONArray.fromObject(model);
						result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
					} else {
						result = "{\"responseCode\":\"" + 1 + "\"}";
					}
				}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 2、取消(删除)收藏
	 */
	public void delCollect() {
		String collectId_str = request.getParameter("collectId");
			int collectId = Integer.parseInt(collectId_str);
			boolean flag = collectService.delCollect(collectId);
			if (flag) {
				result = "{\"responseCode\":\"" + 0 + "\"}";
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 3、查询个人收藏
	 */
	public void getCollect() {
		try {
			List list = collectService.getCollect(Integer.valueOf(request.getParameter("userId")),Integer.valueOf(request.getParameter("pageNo")),Integer.valueOf(request.getParameter("pageNum")));
			JSONArray array = JSONArray.fromObject(list);
			result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
		} catch (Exception e) {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Collect getModel() {
		return model;
	}

	public CollectService getCollectService() {
		return collectService;
	}

	public void setCollectService(CollectService collectService) {
		this.collectService = collectService;
	}

	public SellerService getSellerService() {
		return sellerService;
	}

	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
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
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
