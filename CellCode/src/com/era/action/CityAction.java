package com.era.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Advertisement;
import com.era.orm.Area;
import com.era.orm.City;
import com.era.orm.Village;
import com.era.service.AreaService;
import com.era.service.CityService;
import com.era.util.BaseUtils;
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
	
//	/**
//	 * two
//	 */
//	public void getCityInfo() {
//		String cityId_str = request.getParameter("cityId");
//		String cityName_str = request.getParameter("cityName");
//		String agoDate_str = request.getParameter("agoDate");
//
//		System.out.println("---------getCity---------cityId_str---------->"+ cityId_str);
//
//		agoDate_str = agoDate_str!=null ? agoDate_str:"1";
//		
//		if (cityId_str == null && cityName_str == null) {
//			boolean torf = cityService.compareTwoDate(agoDate_str);
//			if(torf){
//				// 查询所有城市
//				List<City> list = cityService.getCityAll();
//				
//				//查询最后一次操作时间
//				String lastTime = cityService.getCityDate();
//				
//				if (list != null && list.size() > 0) {
//					JSONArray array = JSONArray.fromObject(list);
//					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + ",\"lastTime\":\""+ lastTime + "\"}";
//				} else {
//					result = "{\"responseCode\":\"" + -2 + "\"}";
//				}
//			}else{
//				result = "{\"responseCode\":\"" + -3 + "\"}";
//			}
//		} else if ("-1".equals(cityId_str)) {
//			boolean torf = cityService.compareTwoDate(agoDate_str);
//			if(torf){
//				// 查询所有城市
//				List<City> list = cityService.getCityAll();
//				String lastTime = cityService.getCityDate();
//				if (list != null && list.size() > 0) {
//					JSONArray array = JSONArray.fromObject(list);
//					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + ",\"lastTime\":\""+ lastTime + "\"}";
//				} else {
//					result = "{\"responseCode\":\"" + -2 + "\"}";
//				}
//			}else{
//				result = "{\"responseCode\":\"" + -3 + "\"}";
//			}
//		} else if (cityName_str != null || cityId_str != null) {
//			boolean yorn = cityService.compareTwoDate(agoDate_str);
//			if(yorn){
//				if (cityName_str != null) {
//					boolean torf = BaseUtils.isChinaese(cityName_str);
//					System.out.println("torf--->" + torf + "<---");
//					if (torf) {
//						try {
//							// 用于IOS客户端传递过来的用户名
//							cityName_str = new String(cityName_str.getBytes("ISO-8859-1"), "UTF-8");
//							System.out.println("---isChinaese---userName--IOS-->"+ cityName_str + "<--------------");
//							// 用于网页传递过来的用户名
//							// cityName_str = new
//							// String(cityName_str.getBytes("ISO-8859-1"));
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					} else {
//						cityName_str = cityName_str+"";
//					}
//				}
//				int cityId = cityId_str != null ? Integer.parseInt(cityId_str) : 0;
//				// 查询单个城市
//				City city = cityService.getCity(cityName_str, cityId);
//				String lastTime = cityService.getCityDate();
//				if (city != null) {
//					JSONArray array = JSONArray.fromObject(city);
//					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + ",\"lastTime\":\""+ lastTime + "\"}";
//				} else {
//					result = "{\"responseCode\":\"" + -2 + "\"}";
//				}
//			}else{
//				result = "{\"responseCode\":\"" + -3 + "\"}";
//			}
//		} else {
//			result = "{\"responseCode\":\"" + 1 + "\"}";
//		}
//
//		BaseUtils.responseInfo(result);
//	}
//	
//	/**
//	 * 查询获取城市对应区域
//	 */
//	public void getArea(){
//		String cityId_str = request.getParameter("cityId");//城市id
//		String areaName_str = request.getParameter("areaName");//区域名称
//		String agoDate_str = request.getParameter("agoDate");//上次时间
//		if(cityId_str!=null && agoDate_str!=null){
//			int cityId = Integer.parseInt(cityId_str);
//			if(("1").equals(agoDate_str)){//第一次，查询全部
//				//TODO 查询对应的行政区域
//				List<Area> list_area = areaService.getAreaById(cityId);
//				
//				JSONArray array_to_area = new JSONArray();
//				JSONObject object_to_area = new JSONObject();
//				if(list_area!=null){
//					for(Area a : list_area){
//						object_to_area.put("id",a.getId());
//						object_to_area.put("areaName",a.getAreaName());
//						array_to_area.add(object_to_area);
//					}
//				}else{
//					object_to_area.put("id","-1");
//					object_to_area.put("areaName","全部");
//					array_to_area.add(object_to_area);
//				}
//				
//				//获取最后一次操作时间
//				String lastTime = areaService.getAreaDate();
//				if(list_area.size() == 0)
//				{
//					result = "{\"responseCode\":\"" + -2 + "\",\"area\":"+array_to_area.toString()+",\"lastTime\":\""+ lastTime + "\"}";
//				}
//				else
//				{
//					result = "{\"responseCode\":\"" + 0 + "\",\"area\":"+array_to_area.toString()+",\"lastTime\":\""+ lastTime + "\"}";
//				}
//				
//			}else{//不是第一次，先判断时间差，再进行查询
//				
//				boolean torf = areaService.compareTwoDate(agoDate_str);
//				if(torf){
//					//TODO 查询对应的行政区域
//					List<Area> list_area = areaService.getAreaById(cityId);
//					
//					JSONArray array_to_area = new JSONArray();
//					JSONObject object_to_area = new JSONObject();
//					if(list_area!=null){
//						for(Area a : list_area){
//							object_to_area.put("id",a.getId());
//							object_to_area.put("areaName",a.getAreaName());
//							array_to_area.add(object_to_area);
//						}
//					}else{
//						object_to_area.put("id","-1");
//						object_to_area.put("areaName","全部");
//						array_to_area.add(object_to_area);
//					}
//					
//					//获取最后一次操作时间
//					String lastTime = areaService.getAreaDate();
//					
//					result = "{\"responseCode\":\"" + 0 + "\",\"area\":"+array_to_area.toString()+",\"lastTime\":\""+ lastTime + "\"}";
//				}else{
//					result = "{\"responseCode\":\"" + -3 + "\"}";
//				}
//			}
//		}else{
//			result = "{\"responseCode\":\"" + 1 + "\"}";
//		}	
//		
//		BaseUtils.responseInfo(result);
//	}
//	
	/**
	 * 查询小区
	 * @return
	 */
	public String selVillage()
	{
		try {
			List<Village> list = cityService.selVillage(request.getParameter("keyword"),request.getParameter("cityId"),request.getParameter("log"),request.getParameter("dim"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			map.put("responseCode", "0");
			map.put("list", list);
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
	public String selCity() {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		pagecount = cityService.numberCity(request.getParameter("city"));
		cityOne = request.getParameter("city");
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
		list = cityService.selCity(request.getParameter("city"), pagenum, 15);
		return SUCCESS;
	}

	/**
	 * 删除城市
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delCity() throws IOException {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		boolean bool = cityService.delCity(Integer.valueOf(request
				.getParameter("id")));
		if (bool) {
			selCity();
		} else {
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
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
	public String addCity() throws IOException {
		city.setCityName(request.getParameter("city"));
		boolean bool = cityService.addCity(city);
		if (bool) {
			alertInFo.alertReturn("添加成功");
			selCity();
		} else {
			alertInFo.alertReturn("添加异常");
		}
		return SUCCESS;
	}

	public String advertisement(){
		try {
			List<Advertisement> as = cityService.findAdvertisement(Integer.parseInt(request.getParameter("villageId")));
			map.put("lists", as);
			map.put("code", "0");
			map.put("msg", "查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			map.put("code", "0");
			map.put("msg", "查询失败");
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
}
