package com.era.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Area;
import com.era.orm.BusmarSetManager;
import com.era.orm.City;
import com.era.orm.CustomType;
import com.era.orm.Market;
import com.era.service.CityService;
import com.era.service.MarketService;
import com.era.util.BaseUtils;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import common.BaseUnit;

public class MarketAction extends ActionSupport implements ModelDriven<Market>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	private MarketService marketService;
	private CityService cityService;
	private List<Object> list;
	private List<City> listCity;
	private List<Market> marketList;
	private List<Area> areaList;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String typeOne;
	private String cityNameOne;
	private Map<String,Object> map = new HashMap<String, Object>();
	private String username;
	
	Market model = new Market();
	
	/**
	 * 查询市场
	 * @return
	 */
	public String selMarket()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		pagecount = marketService.numberMarket(request.getParameter("type"),city);
		typeOne = request.getParameter("type");
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
		list = marketService.selMarket(request.getParameter("type"),city,pagenum,15);
		listCity = cityService.getCityAll();
		cityNameOne = request.getParameter("city");
		return SUCCESS;
	}
	
	/**
	 * 查询市场类型区域
	 * @return
	 */
	public String selMarketArea()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		String areaName = request.getParameter("areaName");
		pagecount = marketService.numberMarketArea(areaName);
		typeOne = request.getParameter("type");
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
		list = marketService.selMarketArea(areaName,pagenum,15);
		/*listCity = cityService.getCityAll();
		cityNameOne = request.getParameter("city");*/
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addMarketAreaPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		Area area = new Area();
		String id = request.getParameter("id");
		if( null != id && !"".equals(id)){
			area = marketService.selAreaById(Integer.parseInt(id));
		}
		listCity = cityService.getCityAll();
		request.setAttribute("area", area);
		return SUCCESS;
	}
	
	/**
	 * 添加区域
	 * @return
	 */
	public String addMarketArea()
	{
		try{
			if(request.getSession().getAttribute("userid") == null)
			{
				return "error";
			}
			String id = request.getParameter("id");
			String cityId = request.getParameter("cityId");
			String areaName = request.getParameter("areaName");
			
			marketService.saveOrUpdateMarketArea(id,cityId,areaName);
			map.put("mess", 1);
		}catch (Exception e) {
			// TODO: handle exception
			map.put("mess", 2);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除区域
	 * @return
	 * @throws IOException 
	 */
	public String delMarketArea() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = marketService.delMarketArea(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
			selMarketArea();
		}
		else
		{
			alertInFo.alertReturn("删除异常,如果商户已关联，请先修改商户区域关联");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询市场行业类别
	 * @return
	 */
	public String selMarketCustomType()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		String typeName = request.getParameter("typeName");
		pagecount = marketService.numberMarketCustomType(typeName);
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
		list = marketService.selMarketCustomType(typeName,pagenum,15);
		/*listCity = cityService.getCityAll();
		cityNameOne = request.getParameter("city");*/
		return SUCCESS;
	}
	
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addMarketCustomTypePage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		String id = request.getParameter("id");
		CustomType customType = new CustomType();
		Integer cityId = 0;
		if( null != id && !"".equals(id)){
			customType = marketService.selCustomById(Integer.parseInt(id));
			Market market =	marketService.marketId(customType.getMarketId());
			cityId = market.getCityId();
		}
		listCity = cityService.getCityAll();
		marketList = marketService.allMarket();
		
		request.setAttribute("customType", customType);
		request.setAttribute("cityId", cityId);
		return SUCCESS;
	}
	
	/**
	 * 添加行业类别
	 * @return
	 */
	public String addMarketCustomType()
	{
		try {
			if(request.getSession().getAttribute("userid") == null)
			{
				return "error";
			}
			String id = request.getParameter("id");
			String marketId = request.getParameter("marketId");
			String name = request.getParameter("name");
			
			marketService.saveOrUpdateMarketCustomType(id,marketId,name);
			map.put("mess", 1);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("mess", 2);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除行业类别
	 * @return
	 * @throws IOException 
	 */
	public String delMarketCustomType() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = marketService.delMarketCustomType(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
			selMarketCustomType();
		}
		else
		{
			alertInFo.alertReturn("删除异常,如果商户已关联，请先修改商户区域关联");
		}
		return SUCCESS;
	}
	
	public String selMarketUser(){
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		pagecount = marketService.numberMarketUser(username);
		typeOne = request.getParameter("type");
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
		list = marketService.selMarketUser(username,pagenum,15);
		return SUCCESS;
	}
	
	public String addMarketUserPage(){
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		List<Market> ms = cityService.getMarketType();
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
		request.setAttribute("ms", ms);
		return SUCCESS;
	}
	
	/**
	 * 添加市场用户
	 * @return
	 */
	public String addMarketUser(){
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		BusmarSetManager bm = new BusmarSetManager();
		HttpServletResponse response = ServletActionContext.getResponse();
		String data = "";
		try {
			boolean isHas = marketService.isHasUsername(username);
			boolean hasType = marketService.hasType(request.getParameter("type"));
			if(isHas){
				data = "{mess:\"1\"}";
			}else if(hasType){
				data = "{mess:\"4\"}";
			}else{
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date=new java.util.Date();  
				String str=sdf.format(date);  
				Timestamp ts = new Timestamp(format.parse(str).getTime());
				bm.setAddTime(ts);
				bm.setBmsmId(Integer.parseInt(request.getParameter("type")));
				bm.setEmail(request.getParameter("email"));
				bm.setTelePhone(request.getParameter("telphone"));
				bm.setQq(request.getParameter("qq"));
				bm.setUserPwd("e10adc3949ba59abbe56e057f20f883e");
				bm.setUserName(username);
				bm.setRoot(Integer.valueOf(request.getParameter("root")));
				boolean isSave = marketService.addBusmarSetManager(bm);
				if(isSave){
					data = "{mess:\"2\"}";
				}else{
					data = "{mess:\"3\"}";
				}
			}
			response.getWriter().write(data);
		} catch (Exception e) {
			data = "{mess:\"3\"}";
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除市场类型
	 * @return
	 * @throws IOException 
	 */
	public String delMarket() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = marketService.delMarket(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
			selMarket();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 删除市场类型
	 * @return
	 * @throws IOException 
	 */
	public String delMarketUser() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = marketService.delMarketUser(Integer.parseInt(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
			username = "";
			selMarketUser();
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
	public String addMarketPage()
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
	 * 通过地址查询市场
	 * @return
	 * @throws IOException 
	 */
	public String selSellerMarket() throws IOException
	{
		try {
			marketList = marketService.selSellerMarket(Integer.valueOf(request.getParameter("city")));
			if(marketList == null)
			{
				map.put("msg", 1);
			}
			else
			{
				map.put("msg", marketList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 通过地址查询区域
	 * @return
	 * @throws IOException 
	 */
	public String selSellerArea() throws IOException
	{
		try {
			areaList = marketService.selSellerArea(Integer.valueOf(request.getParameter("city")));
			if(areaList == null)
			{
				map.put("msg", 1);
			}
			else
			{
				map.put("msg", areaList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 添加市场类型
	 * @return
	 * 
	 * @throws IOException 
	 */
	public String addMarket() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		Market mark = new Market();
		HttpServletResponse response = ServletActionContext.getResponse();
		String data = "";
		try {
			if(request.getParameter("id") == null || request.getParameter("id").equals(""))
			{
				
			}
			else
			{
				model =marketService.marketId(Integer.valueOf(request.getParameter("id")));
				if(model == null || model.equals(""))
				{
					
				}
				else
				{
					mark.setIsShow(model.getIsShow());
					if(model.getSort() == Integer.valueOf(request.getParameter("sort")) || model.getSort().equals(Integer.valueOf(request.getParameter("sort"))))
					{
						
					}
					else
					{
						int number = marketService.numberSort(Integer.valueOf(request.getParameter("sort")),Integer.parseInt(request.getParameter("city")));
						if(number>0)
						{
							data = "{mess:\"1\"}";
							response.getWriter().write(data);
							return null;
						}
					}
				}
			}
			if(request.getParameter("id") == null || request.getParameter("id").equals(""))
			{
				mark.setId(null);
			}
			else
			{
				mark.setId(Integer.valueOf(request.getParameter("id")));
			}
			String applyType=request.getParameter("applyType");
			mark.setApplyType(Integer.parseInt(applyType));
			mark.setCityId(Integer.valueOf(request.getParameter("city")));
			mark.setSort(Integer.valueOf(request.getParameter("sort")));
			mark.setType(request.getParameter("type"));
			boolean bool = marketService.addMarket(mark);
			if(bool)
			{
				data = "{mess:\"2\"}";
			}
			else
			{
				data = "{mess:\"3\"}";
			}
			response.getWriter().write(data);
		} catch (Exception e) {
			data = "{mess:\"3\"}";
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 进入修改市场类型页面
	 * @return
	 */
	public String updateMarketPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
		model = marketService.marketId(Integer.valueOf(request.getParameter("id")));
		return SUCCESS;
	}
	/**
	 * 移动排序
	 * @return
	 * @throws IOException 
	 */
	public String updaeSort() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		Market ma = new Market();
		Market mat = new Market();
		Market market = marketService.serviceSort(request.getParameter("sort"));
		if(market != null)
		{
			mat.setId(Integer.valueOf(request.getParameter("sortId")));
			mat.setSort(market.getSort());
			boolean bool = marketService.updateSort(mat);
			if(bool)
			{
				alertInFo.alertReturn("移动成功");
			}
			else
			{
				alertInFo.alertReturn("移动异常");
				/*ma.setId(market.getId());
				ma.setSort(Integer.valueOf(request.getParameter("oneSortId")));
				boolean boo = marketService.updateSort(ma);
				if(boo)
				{
					alertInFo.alertReturn("移动成功");
					selMarket();
					return SUCCESS;
				}
				else
				{
					alertInFo.alertReturn("移动异常");
					selMarket();
					return SUCCESS;
				}*/
			}
		}
		else
		{
			mat.setId(Integer.valueOf(request.getParameter("sortId")));
			mat.setSort(Integer.valueOf(request.getParameter("sort")));
			boolean bool = marketService.updateSort(mat);
			if(bool)
			{
				alertInFo.alertReturn("移动成功");
				selMarket();
				return SUCCESS;
			}
		}
		selMarket();
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request=request;
	}

	@Override
	public Market getModel() 
	{
		return model;
	}

	public MarketService getMarketService() {
		return marketService;
	}

	public void setMarketService(MarketService marketService) {
		this.marketService = marketService;
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

	public String getTypeOne() {
		return typeOne;
	}

	public void setTypeOne(String typeOne) {
		this.typeOne = typeOne;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
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

	public Map<String, Object> getMap() {
		return map;
	}

	public List<Area> getAreaList() {
		return areaList;
	}



	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}



	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Market> getMarketList() {
		return marketList;
	}

	public void setMarketList(List<Market> marketList) {
		this.marketList = marketList;
	}

	public String getCityNameOne() {
		return cityNameOne;
	}

	public void setCityNameOne(String cityNameOne) {
		this.cityNameOne = cityNameOne;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
