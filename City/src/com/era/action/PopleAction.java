package com.era.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.City;
import com.era.orm.Convenient;
import com.era.orm.ConvenientDetail;
import com.era.service.CityService;
import com.era.service.PopleService;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PopleAction extends ActionSupport implements ModelDriven<Convenient>,
ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	private String nameOne;
	
	private Convenient model = new Convenient();
	private PopleService popleService;
	private CityService cityService;
	private List<Object> list;
	private List<City> listCity;
	private Map<String,Object> map = new HashMap<String, Object>();
	private ConvenientDetail detail = new ConvenientDetail();
	private City city = new City();
	
	/**
	 * 查询所有的便民
	 * @return
	 */
	public String selConvenient()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		pagecount = popleService.numberPople(request.getParameter("name"),city);
		nameOne = request.getParameter("name");
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
		list = popleService.selPople(request.getParameter("name"),city,pagenum,15);
		return SUCCESS;
	}
	
	/**
	 * 删除便民
	 * @return
	 * @throws IOException 
	 */
	public String delConvenient() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = popleService.delPople(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selConvenient();
			alertInFo.alertReturn("删除成功");
		}
		else
		{
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addConvenientPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		model = null;
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
		return SUCCESS;
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String updateConvenientPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
		model = popleService.selPopleId(Integer.valueOf(request.getParameter("id")));
		return SUCCESS;
	}
	
	/**
	 * 查看便民
	 * @return
	 */
	public String selConvenientId()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		detail = popleService.selConvenientId(Integer.valueOf(request.getParameter("id")));
		if(detail != null)
		{
			model = popleService.selPopleId(detail.getConvenientId());
			if(model != null)
			{
				city = popleService.selCityId(model.getCityId());
			}
		}
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Convenient getModel() {
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

	public PopleService getPopleService() {
		return popleService;
	}

	public void setPopleService(PopleService popleService) {
		this.popleService = popleService;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
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

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getNameOne() {
		return nameOne;
	}

	public void setNameOne(String nameOne) {
		this.nameOne = nameOne;
	}

	public ConvenientDetail getDetail() {
		return detail;
	}

	public void setDetail(ConvenientDetail detail) {
		this.detail = detail;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
