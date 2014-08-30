package com.era.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Collect;
import com.era.service.CollectService;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CollectAction extends ActionSupport implements
		ModelDriven<Collect>, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private CollectService collectService;
	private Integer userId;// 用户id
	private Integer sellerId;// 商户id
	private String result;// 用于组装json
	private int pageNo;
	private int pageNum;



	private List<Object> list;
	
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	
	Collect model = new Collect();
	
	/**
	 * 查询收藏
	 * @return
	 */
	public String selCollect()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		pagecount = collectService.numberCollect(city);
		if(pagecount<15){
			pageCount=1;
		}else{
			pageCount = pagecount/15;
			int i  = pagecount%15;
			if(i>0)
			{
				pageCount+=1;
			}
		}
		if (pagenum < 1) {
			pagenum = 1;
		}
		if (pagenum > pagecount) {
			if(pagecount == 0)
			{
				
			}
			else
			{
				pagenum = pageCount;
			}
		}
		list = collectService.selCollect(city,pagenum,15);
		return SUCCESS;
	}
	
	/**
	 * 删除收藏
	 * @return
	 * @throws IOException 
	 */
	public String delCollect() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = collectService.delCollect(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selCollect();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
