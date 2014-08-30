package com.era.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.BusmarSet;
import com.era.orm.City;
import com.era.orm.Market;
import com.era.service.CityService;
import com.era.service.MarSetService;
import com.era.service.MarketService;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MarSetAction  extends ActionSupport implements ModelDriven<BusmarSet>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BusmarSet model = new BusmarSet();
	
	private HttpServletRequest request;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private List<Object> listSet;
	private MarketService marketService;
	private MarSetService marSetService;
	private List<Market> listMarket;
	private String nameOne;
	private List<City> listCity;
	private CityService cityService;
	
	/**
	 * 查询市场入住
	 * @return
	 */
	public String selBusmarSet()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		nameOne = request.getParameter("name");
		pagecount = marSetService.numberAdmin(request.getParameter("name"),city);
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
		listSet = marSetService.listMarSet(request.getParameter("name"),city,pagenum,15);
		return SUCCESS;
	}
	/**
	 * 添加修改市场入住
	 * @return
	 * @throws IOException 
	 */
	public String addBusmarSet() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		BusmarSet model1 = new BusmarSet();
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			model.setId(null);
		}
		else
		{
			model1.setId(Integer.valueOf(request.getParameter("id")));
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
		model1.setAddTime(ts);
		model1.setBmsUserName(request.getParameter("bmsUserName"));
		model1.setTelephone(request.getParameter("Telephone"));
		model1.setBmsIntroduction(request.getParameter("bmsIntroduction"));
		model1.setMarketId(Integer.valueOf(request.getParameter("marketId")));
		boolean bool = marSetService.addMarSet(model1);
		if(bool)
		{
			alertInFo.alertReturn("添加修改成功");
			selBusmarSet();
		}
		else
		{
			alertInFo.alertReturn("添加修改异常");
			selBusmarSet();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除市场入住
	 * @return
	 * @throws IOException 
	 */
	public String delBusmarSet() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = marSetService.delMarSet(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
			selBusmarSet();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
			selBusmarSet();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到修改市场入住
	 * @return
	 */
	public String updateBusmarSetPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		listMarket = marketService.allMarket();
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
		model = marSetService.marSetId(Integer.valueOf(request.getParameter("id")));
		return SUCCESS;
	}
	/**
	 * 跳转到添加市场入住
	 * @return
	 */
	public String addBusmarSetPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		model = null;
		listMarket = marketService.allMarket();
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	@Override
	public BusmarSet getModel() {
		return model;
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



	public MarSetService getMarSetService() {
		return marSetService;
	}


	public MarketService getMarketService() {
		return marketService;
	}
	public void setMarketService(MarketService marketService) {
		this.marketService = marketService;
	}
	public void setMarSetService(MarSetService marSetService) {
		this.marSetService = marSetService;
	}
	public List<Market> getListMarket() {
		return listMarket;
	}
	public void setListMarket(List<Market> listMarket) {
		this.listMarket = listMarket;
	}
	public List<Object> getListSet() {
		return listSet;
	}
	public String getNameOne() {
		return nameOne;
	}
	public void setNameOne(String nameOne) {
		this.nameOne = nameOne;
	}
	public void setListSet(List<Object> listSet) {
		this.listSet = listSet;
	}
	public List<City> getListCity() {
		return listCity;
	}
	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}
	public CityService getCityService() {
		return cityService;
	}
	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

}
