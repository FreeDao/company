package com.era.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.City;
import com.era.orm.SupplyAndDemand;
import com.era.orm.Type;
import com.era.service.SupplyAndDemandService;
import com.era.util.BaseUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SupplyAndDemandAction  extends ActionSupport implements ModelDriven<SupplyAndDemand>,
ServletRequestAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private SupplyAndDemand model = new SupplyAndDemand();
	private SupplyAndDemandService supplyAndDemandService;
	private Map<String,Object> map  = new HashMap<String,Object>();
	/**
	 * 查询供求信息
	 */
	public String selSupply()
	{
		List<SupplyAndDemand> list = null;
		try 
		{
			list = supplyAndDemandService.selSupply(request.getParameter("typeId"), request.getParameter("cityId"),request.getParameter("supply"),request.getParameter("isfinish"),request.getParameter("userId"),request.getParameter("type"),request.getParameter("pageNo"),request.getParameter("pageNum") );
			if(list.size()<1)
			{
				map.put("responseCode", "2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("result", list);
			}
		} catch (Exception e) 
		{
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 发布求购信息
	 * @return
	 */
	public String addDemandInfo()
	{
		City city = null;
		Type type = null;
		try {
			//标题
			String title = request.getParameter("title");
			String cityName = "";//城市名称
			//通过城市名称查询城市Id
			if(BaseUtils.isChinaese(request.getParameter("cityName")))
			{
//			cityName = new String(request.getParameter("cityName").getBytes("ISO-8859-1"),"UTF-8");
			cityName = new String(request.getParameter("cityName").getBytes("ISO-8859-1"));
			}else
			{
				cityName = request.getParameter("cityName");
			}
			city = supplyAndDemandService.geCityIdByName(cityName);
			int cityId = 0;
			if(city!=null)
			{
				cityId = city.getId();
			}
			//通过类型名称查询类型Id
			String typeName = "";
			if(BaseUtils.isChinaese(request.getParameter("typeName")))
			{
//			typeName = new String(request.getParameter("typeName").getBytes("ISO-8859-1"),"UTF-8");
			typeName = new String(request.getParameter("typeName").getBytes("ISO-8859-1"));
			}else
			{
				typeName = request.getParameter("typeName");
			}
			type = supplyAndDemandService.getTypeIdByName(typeName);
			int typeId = 0;
			if(type!=null)
			{
				typeId = type.getId();
			}
			String orderNum = request.getParameter("orderNum");
			String price = request.getParameter("price");
			String sellerid_str = request.getParameter("sellerId");
			int sellerId = Integer.valueOf(sellerid_str);
			SupplyAndDemand demand = new SupplyAndDemand();
			demand.setUserId(Integer.valueOf(request.getParameter("userId")));
			demand.setContent(title);
			demand.setCityId(cityId);
			demand.setTypeId(typeId);
			demand.setOrderNum(orderNum);
			demand.setPrice(price);
			demand.setSellerId(sellerId);
			boolean bool = supplyAndDemandService.addDemandInfo(demand);
			if(bool)
			{
				map.put("responseCode", "0");
			}else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e) 
		{
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	/**
	 * 判断是否完成供求
	 * @return
	 */
	public String deleteOrUpdate()
	{
		try 
		{
			boolean bool = supplyAndDemandService.deleteOrUpdate(request.getParameter("isfinish"),request.getParameter("userId"),request.getParameter("supplyId"),request.getParameter("relevanceId"));
			if(bool)
			{
				map.put("responseCode", "0");
			}else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e)
		{
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	public HttpServletRequest getRequest() 
	{
		return request;
	}

	public void setRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	public SupplyAndDemandService getSupplyAndDemandService() 
	{
		return supplyAndDemandService;
	}

	public void setSupplyAndDemandService(
			SupplyAndDemandService supplyAndDemandService) 
	{
		this.supplyAndDemandService = supplyAndDemandService;
	}

	public Map<String, Object> getMap() 
	{
		return map;
	}

	public void setMap(Map<String, Object> map) 
	{
		this.map = map;
	}

	public SupplyAndDemand getModel() 
	{
		return model;
	}

	public void setModel(SupplyAndDemand model) 
	{
		this.model = model;
	}
}
