package com.era.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Busmarset;
import com.era.orm.City;
import com.era.orm.Guarantee;
import com.era.orm.Market;
import com.era.service.CityService;
import com.era.service.MarketService;
import com.era.util.BaseUtils;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MarketAction extends ActionSupport implements ModelDriven<Market>,
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	private MarketService marketService;
	private CityService cityService;
	private List<Object> list;
	private List<City> listCity;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String typeOne;
	private String result;
	private Map<String,Object> map  = new HashMap<String,Object>();

	Market model = new Market();

	/**
	 * 查询显示对应区域下面 所有市场类型列表
	 * 
	 */
	public String getMarketList() {
		try {
			List<Market> list = marketService.getMarketList(request.getParameter("villageId"),request.getParameter("pageNo"),request.getParameter("pageNum"),request.getParameter("marketType"));
			if(list == null ||list.equals(""))
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "注册异常");
		}
		return SUCCESS;
	}
	/**
	 * 通过市场名称查询市场
	 * @throws UnsupportedEncodingException 
	 */
	public void selMark() throws UnsupportedEncodingException
	{
		String markName = "";
		String pageNo = request.getParameter("pageNo");
		String pageNum = request.getParameter("pageNum");
		String cityId = request.getParameter("cityId");
		
		if(request.getParameter("markName") == null || request.getParameter("markName").equals(""))
		{
			
		}
		else
		{
			if(BaseUtils.isChinaese(request.getParameter("markName")))
			{
				markName = new String(request.getParameter("markName").getBytes("ISO-8859-1"),"UTF-8");
			}
			else
			{
				markName = request.getParameter("markName");
			}
		}
		List<Market> listMark = marketService.selLikeMark(markName,cityId,pageNo,pageNum);
		JSONArray array = JSONArray.fromObject(listMark);
		if(listMark.size()>0 || listMark != null)
		{
			result = "{\"responseCode\":\"" + 0 + "\",\"results\":" + array + "}";
		}
		else
		{
			result = "{\"responseCode\":\"" + 0 + "\",\"def\":\""+ 0 + "\",\"results\":" + array + "}";
		}
		BaseUtils.responseInfo(result);
	}
	/**
	 * 查询获取每个类型下面对应的注入商家是谁
	 */
	public void getMarketSeller(){
		String typeId_str = request.getParameter("typeId");
		if(typeId_str != null){
			Integer typeId = Integer.parseInt(typeId_str);
			Busmarset b = marketService.getBusMarketSet(typeId);
			if(b!=null){
				JSONArray array = JSONArray.fromObject(b);
				result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
			}else{
				result = "{\"responseCode\":\"" + -2 + "\"}";	
			}
		}else{
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * 查询市场类型
	 * 
	 * @return
	 */
	public String selMarket() {
		try {
			list = marketService.selMarket(request.getParameter("type"));
			map.put("list", list);
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("responseCode", 1);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 删除市场类型
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delMarket() throws IOException {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		boolean bool = marketService.delMarket(Integer.valueOf(request
				.getParameter("id")));
		if (bool) {
			alertInFo.alertReturn("删除成功");
			selMarket();
		} else {
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String addMarketPage() {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		listCity = cityService.getCityAll();
		return SUCCESS;
	}

	/**
	 * 我要报修
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addGuarantee() throws UnsupportedEncodingException
	{
		try {
			Guarantee tee = new Guarantee();
			String bife = "";
			String address ="";
			String userId = request.getParameter("userId");
			String iphone = request.getParameter("iphone");
			String outAddtime = request.getParameter("outAddtime");
			String isNo = request.getParameter("isNo");
			String villageId = request.getParameter("villageId");
			if(BaseUtils.isChinaese(request.getParameter("bife")))
			{
				bife = new String(request.getParameter("bife").getBytes("ISO-8859-1"),"UTF-8");
			}
			else
			{
				bife = request.getParameter("bife");
			}
			if(BaseUtils.isChinaese(request.getParameter("address")))
			{
				address = new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8");
			}
			else
			{
				address = request.getParameter("address");
			}
			tee.setUserId(Integer.parseInt(userId));
			tee.setAddress(address);
			tee.setIphone(iphone);
			tee.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			tee.setOutAddtime(outAddtime);
			tee.setIsNo(Integer.valueOf(isNo));
			tee.setBife(bife);
			tee.setVillageId(Integer.valueOf(villageId));
			boolean bool = marketService.addGuarantee(tee);
			if(bool)
			{
				map.put("responseCode","0");
			}
			else
			{
				map.put("responseCode", "1");
				map.put("msg", "注册异常");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "注册异常");
		}
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Market getModel() {
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
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
