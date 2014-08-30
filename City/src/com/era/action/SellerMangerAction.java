package com.era.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.SellerManager;
import com.era.service.SellerManagService;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SellerMangerAction extends ActionSupport implements ModelDriven<SellerManager>,
ServletRequestAware
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private SellerManagService sellerManagService;
	private List<Object> listSeller;
	
	private SellerManager model = new SellerManager();
	private double pageNum;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	
	/**
	 * 添加或修改商户会员
	 * @return
	 * @throws IOException 
	 */
	public String addsellSerManag() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		java.util.Date date = new java.util.Date();
		Timestamp te = new Timestamp(date.getTime());
		if( null != request.getParameter("id") && !"".equals(request.getParameter("id"))){
			model.setId(Integer.valueOf(request.getParameter("id")));
		}
		model.setEmail(request.getParameter("email"));
		model.setQq(request.getParameter("qq"));
		model.setTelePhone(request.getParameter("phone"));
		model.setUserName(request.getParameter("userName"));
		model.setUserPwd(request.getParameter("passWord"));
		model.setSellerId(Integer.valueOf(request.getParameter("sellerId")));
		model.setAddTime(te);
		boolean bool = sellerManagService.addSellerManager(model);
		if(bool)
		{
			alertInFo.alertReturn("成功");
			selSellSerManag();
		}
		else
		{
			alertInFo.alertReturn("添加异常");
			selSellSerManag();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String sellerManagPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		listSeller = sellerManagService.findSeller();
		return SUCCESS;
	}
	
	/**
	 * 跳转更新商户
	 * @return
	 */
	public String updateSellerManagerPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		model = sellerManagService.selSellerManager(Integer.valueOf(request.getParameter("id")));
		listSeller = sellerManagService.findSeller();
		return SUCCESS;
	}
	
	/**
	 * 查询商户会员
	 * @return
	 */
	public String selSellSerManag()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		pagecount = sellerManagService.numberSellerManager(request.getParameter("userName"),city);
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
				pagenum = pagecount;
			}
		}
		listSeller = sellerManagService.listSellerManager(request.getParameter("userName"),city,pagenum,15);
		return SUCCESS;
	}
	
	/**
	 * 删除商户会员
	 * @return
	 * @throws IOException 
	 */
	public String delSellSerManag() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = sellerManagService.delSellerManager(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selSellSerManag();
		}
		else
		{
			alertInFo.alertReturn("删除失败");
			selSellSerManag();
		}
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) 
	{
		this.request = request;
	}
	@Override
	public SellerManager getModel() 
	{
		return model;
	}

	
	public SellerManagService getSellerManagService() {
		return sellerManagService;
	}

	public void setSellerManagService(SellerManagService sellerManagService) {
		this.sellerManagService = sellerManagService;
	}

	public double getPageNum() {
		return pageNum;
	}

	public void setPageNum(double pageNum) {
		this.pageNum = pageNum;
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

	public List<Object> getListSeller() {
		return listSeller;
	}

	public void setListSeller(List<Object> listSeller) {
		this.listSeller = listSeller;
	}
	
}
