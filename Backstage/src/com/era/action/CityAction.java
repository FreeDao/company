package com.era.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Area;
import com.era.orm.City;
import com.era.orm.CityUser;
import com.era.orm.Custom;
import com.era.orm.Village;
import com.era.service.AreaService;
import com.era.service.CityService;
import com.era.util.BaseUtils;
import com.era.util.LoginUser;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CityAction extends ActionSupport implements ModelDriven<City>,
		ServletRequestAware {
	private City city = new City();
	private HttpServletRequest request;
	private CityService cityService;
	private String result;// 用户接收返回的json
	private List<City> list;
	private String cityOne;
	private AreaService areaService;
	private Map<String,Object> map  = new HashMap<String,Object>();
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	
	private String rows; 
	private String page;

	/**
	 * 删除物业信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delCustom() throws IOException {
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		cityService.delObj(Custom.class,Integer.parseInt(ids[i])); 
        } 
		return null;
	}
	
	/**
	 * 添加物业信息
	 * @return
	 */
	public String addCustom(){
		try{
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String iphone = request.getParameter("iphone");
			String villageId = request.getParameter("villageId");
			
			LoginUser lu = (LoginUser) request.getSession().getAttribute("LoginUser");
			
			if(lu.getRole() == 5){
				villageId = lu.getVillageId();
			}
			
			cityService.addOrUpdateCity(lu,id,name,iphone,villageId);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * TODO 查询获取城市列表
	 */
	public String selCityInfo() 
	{
		try {
			List<City> list = cityService.getCityAll();
			map.put("list", list);
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}

	/**
	 * 查询小区
	 * @return
	 */
	public String selVillage()
	{
		try {
			List<Village> list = cityService.selVillage(request.getParameter("cityId"));
			map.put("list", list);
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询小区
	 * @return
	 */
	public String selVillageById()
	{
		try {
			Village v = cityService.selVillageById(request.getParameter("villageId"));
			map.put("village", v);
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有所有城市
	 * 
	 * @return
	 */
	public String selCity() 
	{
		try
		{
			list=cityService.selCity(request.getParameter("name"),Integer.valueOf(page),Integer.valueOf(rows));
	   	 	int total=cityService.numberCity(request.getParameter("name"));
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除城市
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delCity() throws IOException {
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		cityService.delCity(Integer.parseInt(ids[i])); 
        } 
		return null;
	}

	/**
	 * 跳转到添加城市
	 * 
	 * @return
	 */
	public String addCityPage() {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		return SUCCESS;
	}

	/**
	 * 添加城市
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addCity() throws IOException 
	{
		try {
			String id = request.getParameter("id");
			String temp = cityService.findCityById(id);
			if( null != temp ){
				alertInFo.alertReturn(temp);
				return SUCCESS;
			}
			city.setId(Integer.parseInt(request.getParameter("id")));
			city.setCityWord(request.getParameter("cityWord"));
			city.setCityName(request.getParameter("city"));
			cityService.addCity(city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 客户端查询小区
	 * @return
	 */
	public String selVillageOr()
	{
		try {
			List<Village> list=cityService.selVillageOr(request.getParameter("name"),Integer.valueOf(page),Integer.valueOf(rows));
	   	 	int total=cityService.numberVillageOr(request.getParameter("name"));
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * city
	 * @return
	 */
	public String selVillageOrCity()
	{
		list = cityService.getCityAll();
		return SUCCESS;
	}
	
	/**
	 * 删除小区
	 * @return
	 */
	public String delVillageOr()
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		cityService.delVillageOr(Integer.parseInt(ids[i])); 
        } 
		return SUCCESS;
	}
	/**
	 * 添加小区
	 * @return
	 */
	public String addVillage()
	{
		try {
			String id = request.getParameter("id");
			String temp = cityService.findVillageById(id);
			if( null != temp ){
				alertInFo.alertReturn(temp);
				return SUCCESS;
			}
			Village vill = new Village();
			vill.setId(Integer.parseInt(id));
			vill.setAddress(request.getParameter("address"));
			vill.setName(request.getParameter("name"));
			vill.setCityId(Integer.valueOf(request.getParameter("city")));
			vill.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
				Map<String, String> json = alertInFo.getGeocoderLatitude(request.getParameter("address"));
				if(json == null || json.equals(""))
				{
					alertInFo.alertReturn("地图不存在此地址!");
					return SUCCESS;
				}
				vill.setLog(json.get("lng"));
				vill.setDim(json.get("lat"));
				cityService.addVillage(vill);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询城市账号
	 * @return
	 */
	public String selCityUser(){
		try {
			List<CityUser> list=cityService.selCityUser(Integer.valueOf(page),Integer.valueOf(rows));
	   	 	int total=cityService.numberCityUser();
	   	 	map.put("total", total);
	   	 	map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加更新城市账号
	 * @return
	 */
	public String addOrUpdateCityUser(){
		try {
			String id = request.getParameter("id");
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("passWord");
			String cityId = request.getParameter("cityId");
			String temp = cityService.addOrUpdateCityUser(id,userName,passWord,cityId);
			if( temp != null){
				alertInFo.alertReturn(temp);
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除城市账号
	 * @return
	 */
	public String delCityUser(){
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		cityService.delObj(CityUser.class, Integer.parseInt(ids[i])); 
        } 
		return SUCCESS;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public City getModel() {
		return city;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public List<City> getList() {
		return list;
	}

	public void setList(List<City> list) {
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

	public String getCityOne() {
		return cityOne;
	}

	public void setCityOne(String cityOne) {
		this.cityOne = cityOne;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}
}
