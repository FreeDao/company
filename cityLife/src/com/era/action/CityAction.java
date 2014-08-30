package com.era.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Area;
import com.era.orm.City;
import com.era.orm.CustomType;
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

	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;

	/**
	 * TODO 查询获取城市列表
	 */
	public void getCity() {
		try {
			List<City> list = cityService.getCityAll();
			JSONArray array = JSONArray.fromObject(list);
			result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
		} catch (Exception e) {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}
	
//	/**
//	 * two
//	 */
//	public void getCityInfo()
//	{
//		String cityId_str = request.getParameter("cityId");
//		String cityName_str = request.getParameter("cityName");
//		String agoDate_str = request.getParameter("agoDate");
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
	
	/**
	 * 查询获取城市对应区域
	 */
	public void getArea()
	{
		try {
			List<Area> list_area = areaService.getAreaById(Integer.valueOf(request.getParameter("cityId")));
			JSONArray array = JSONArray.fromObject(list_area);
			result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
			BaseUtils.responseInfo(result);
		} catch (Exception e) {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
	}

	/**
	 * 查询用户自定义类别
	 */
	public void getCustomType()
	{
		try {
			List<CustomType> list_custom = areaService.getCustomById(Integer.valueOf(request.getParameter("marketId")));
			JSONArray array = JSONArray.fromObject(list_custom);
			result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
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

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}
}
