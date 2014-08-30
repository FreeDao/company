package com.era.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private Map<String,Object> map  = new HashMap<String,Object>();

	private List<Object> list;
	
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;

	Collect model = new Collect();

	/**
	 * 查询收藏
	 * 
	 * @return
	 */
	public String selCollect() {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		pagecount = collectService.numberCollect();
		if (pagecount < 15) {
			pageCount = 1;
		} else {
			pageCount = pagecount / 15;
			int i = pagecount % 15;
			if (i > 0) {
				pageCount += 1;
			}
		}
		if (pagenum < 1) {
			pagenum = 1;
		}
		if (pagenum > pagecount) {
			if (pagecount == 0) {

			} else {
				pagenum = pagecount;
			}
		}
		list = collectService.selCollect(pagenum, 15);
		return SUCCESS;
	}

	/**
	 * TODO 1、添加收藏
	 */
	public void putCollect() {
		String sellerId_str = request.getParameter("sellerId");
		String userId_str = request.getParameter("userId");

		if (sellerId_str != null && userId_str != null) {
			int sellerId = Integer.parseInt(sellerId_str);
			int userId = Integer.parseInt(userId_str);
			System.out.println("----userId------------->" + userId);
			System.out.println("----sellerId------------->" + sellerId);
			if (sellerId > 0 && userId > 0) {
				// 判断是否重复收藏
				Collect collect = collectService.repeatCollect(userId, sellerId);
				if (collect != null) {
					result = "{\"responseCode\":\"" + 2 + "\"}";
				} else {// 没重复收藏
					model.setSellerId(sellerId);
					model.setUserId(userId);
					model.setType(Integer.valueOf(request.getParameter("type")));
					model.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
					System.out.println("----model------------->" + model);
					Collect ct = collectService.putCollect(model);
					if (ct != null) {
						JSONArray array = JSONArray.fromObject(ct);
						result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
					} else {
						result = "{\"responseCode\":\"" + 1 + "\"}";
					}
				}
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		} else {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 2、取消(删除)收藏
	 */
	public void delCollect() {
		String collectId_str = request.getParameter("collectId");
		if (collectId_str != null) {
			int collectId = Integer.parseInt(collectId_str);
			boolean flag = collectService.delCollect(collectId);
			if (flag) {
				result = "{\"responseCode\":\"" + 0 + "\"}";
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		} else {
			String userId_str = request.getParameter("userId");
			String sellerId_str = request.getParameter("sellerId");
			if (userId_str != null && sellerId_str != null) {
				int userId = Integer.parseInt(userId_str);
				int sellerId = Integer.parseInt(sellerId_str);
				boolean torf = collectService.delCollect(userId, sellerId);
				if (torf) {
					result = "{\"responseCode\":\"" + 0 + "\"}";
				} else {
					result = "{\"responseCode\":\"" + 1 + "\"}";
				}
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 3、查询个人收藏
	 */
	public String getCollect() {
		try {
			String userId_str = request.getParameter("userId");
			String pageNo_str = request.getParameter("pageNo");
			String pageNum_str = request.getParameter("pageNum");
			String type = request.getParameter("type");
			List list = collectService.getCollect(userId_str, pageNo_str,pageNum_str,type);
			if(list.size()>1)
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
			else
			{
				map.put("responseCode", "-2");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("list", list);
		}
		return SUCCESS;
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

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
