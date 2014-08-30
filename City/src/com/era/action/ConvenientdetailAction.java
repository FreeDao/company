package com.era.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.City;
import com.era.orm.Convenient;
import com.era.orm.ConvenientDetail;
import com.era.orm.Product;
import com.era.service.CityService;
import com.era.service.ConvenientdetailService;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ConvenientdetailAction  extends ActionSupport implements ModelDriven<ConvenientDetail>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ConvenientDetail model = new ConvenientDetail();
	Product product = new Product();
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	
	HttpServletRequest request;
	private List<Object> list;
	private List<Convenient> ConvenientList;
	private CityService cityService;
	private List<City> listCity;
	private ConvenientdetailService convenientdetailService;
	private Map<String,Object> map = new HashMap<String, Object>();
	
	/**
	 * 通过城市id查询便民
	 * @return
	 */
	public String cityConvenient()
	{
		List<Convenient> list = convenientdetailService.selConveientCity(request.getParameter("city"));
		map.put("msg", list);
		return SUCCESS;
	}
	
	/**
	 * 查询所有的便民详情
	 * @return
	 */
	public String selConvenientDetail()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		pagecount = convenientdetailService.numberConvenientdetail(request.getParameter("name"),city);
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
		list = convenientdetailService.selConvenientdetail(request.getParameter("name"),city,pagenum,15);
		return SUCCESS;
	}
	/**
	 * 删除便民详情
	 * @return
	 * @throws IOException
	 */
	public String delConvenientDetail() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = convenientdetailService.delConvenientdetail(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selConvenientDetail();
		}
		else
		{
			alertInFo.alertReturn("删除异常!");
			selConvenientDetail();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加便民详情
	 * @return
	 * @throws IOException 
	 */
	public String addConvenientDetail() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			model.setId(null);
		}
		else
		{
			model.setId(Integer.valueOf(request.getParameter("id")));
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		String data = "";
		model.setCityId(Integer.valueOf(request.getParameter("ctiy")));
		model.setName(request.getParameter("name"));
		model.setPhone(request.getParameter("phone"));
		Map<String, String> json = alertInFo.getGeocoderLatitude(request.getParameter("address"));
		if(json == null || json.equals(""))
		{
			data = "{mess:\"1\"}";
			response.getWriter().write(data);
			return null;
		}
		model.setLog(json.get("lng"));
		model.setDim(json.get("lat"));
		model.setConvenientId(Integer.valueOf(request.getParameter("convenient")));
		model.setAddress(request.getParameter("address"));
		boolean bool = convenientdetailService.addConvenientdetail(model);
		if(bool)
		{
			data = "{mess:\"2\"}";
		}
		else
		{
			data = "{mess:\"3\"}";
		}
		response.getWriter().write(data);
		return null;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		model = null;
		ConvenientList = cityService.allProduct();
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
		return SUCCESS;
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String updaePage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		model = convenientdetailService.selConvenientdetailId(Integer.valueOf(request.getParameter("id")));
		ConvenientList = cityService.allProduct();
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public ConvenientDetail getModel() {
		return model;
	}


	public List<Object> getList() {
		return list;
	}


	public void setList(List<Object> list) {
		this.list = list;
	}


	public ConvenientdetailService getConvenientdetailService() {
		return convenientdetailService;
	}


	public void setConvenientdetailService(
			ConvenientdetailService convenientdetailService) {
		this.convenientdetailService = convenientdetailService;
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
	public Product getProduct() {
		return product;
	}
	public List<Convenient> getConvenientList() {
		return ConvenientList;
	}
	public void setConvenientList(List<Convenient> convenientList) {
		ConvenientList = convenientList;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


}
