package com.era.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Seller;
import com.era.service.SellerService;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SellerAction extends ActionSupport implements ModelDriven<Seller>,
ServletRequestAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private Seller model = new Seller();
	
	private SellerService sellerService;
	private List<Seller> listSeller;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String nameOne;
	
	/**
	 * 查询用户有多少条商户
	 * @return
	 */
	public String selSeller()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String sellerId=(String) request.getSession().getAttribute("sellerId");
		pagecount = sellerService.numberSeller(request.getParameter("name"),sellerId);
		nameOne = request.getParameter("name");
		if(pagecount<7){
			pageCount=1;
		}else{
			pageCount = pagecount/7;
			int i  = pagecount%7;
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
		listSeller = sellerService.selSeller(request.getParameter("name"),sellerId,pagenum,7);
		return SUCCESS;
	}
	
	/**
	 * 跳转到修改个人信息
	 * @return
	 */
	public String updatesellerPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		model = sellerService.selOneSeller(Integer.valueOf(request.getParameter("SellerId")));
		return SUCCESS;
	}

	public String updateSeller() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		String data="";
		
		String titile = request.getParameter("titile");
		String phone = request.getParameter("phone");
		String brief = request.getParameter("brief");
		String preferential = request.getParameter("preferential");
		Map<String, String> json = alertInFo.getGeocoderLatitude(request.getParameter("address"));
		if(json == null || json.equals(""))
		{
			data = "{msg:\"3\"}";
			response.getWriter().write(data);
			return null;
		}
		String address = request.getParameter("address");
		String id_str = request.getParameter("sellerid");
		int id = Integer.valueOf(id_str);
		boolean bool = sellerService.updateSeller(id,titile,phone,brief,preferential,address,json.get("lng"),json.get("lat"));
		if(bool)
		{
			data = "{msg:\"1\"}";
			response.getWriter().write(data);
		}
		else
		{
			data = "{msg:\"2\"}";
			response.getWriter().write(data);
		}
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	@Override
	public Seller getModel() {
		return model;
	}

	public SellerService getSellerService() {
		return sellerService;
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

	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	public List<Seller> getListSeller() {
		return listSeller;
	}

	public void setListSeller(List<Seller> listSeller) {
		this.listSeller = listSeller;
	}

	public String getNameOne() {
		return nameOne;
	}

	public void setNameOne(String nameOne) {
		this.nameOne = nameOne;
	}

}
