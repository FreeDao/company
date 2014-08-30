package com.era.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Collect;
import com.era.service.CollectService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class CollectAction extends ActionSupport implements ModelDriven<Collect>,
ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collect model = new Collect();
	private CollectService collectService;
	private HttpServletRequest request;
	private Map<String,Object> map  = new HashMap<String,Object>();
	/**
	 * 查询收藏ok
	 * @return
	 */
	public String selectCollect()
	{
		try {
			List<Object> list = collectService.selectCollect(request.getParameter("userId"),request.getParameter("supplyId"),request.getParameter("ishave"),request.getParameter("informationId"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			if (list.size()<1)
			{
				map.put("responseCode", "2");
			}else
			{
				map.put("responseCode", "0");
				map.put("result",list);
			}
		} catch (Exception e) 
		{
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	/**
	 * 添加收藏
	 * @return
	 */
	public String addCollect()
	{
		try 
		{
			String supplyId = request.getParameter("supplyId");
			String ishave = request.getParameter("ishave");
			
			String informationId = request.getParameter("informationId");
			String relevanceId = request.getParameter("relevanceId");
			String userId = request.getParameter("userId");
			
			java.util.Date date = new java.util.Date();
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String tem = format1.format(date);
			
			model.setUserId(Integer.valueOf(userId));
			model.setAddtime(tem);
			
			if(ishave.equals("1"))
			{
				model.setInformationId(0);
				
				if(supplyId==null||supplyId.equals(""))
				{
					map.put("responseCode", "1");
					map.put("msg", "supplyId为空");
					return SUCCESS;
				}
				else
				{
					model.setSupplyId(Integer.valueOf(supplyId));
				}
				if(relevanceId==null||relevanceId.equals(""))
				{
					map.put("responseCode", "1");
					map.put("msg", "供求编号relevanceId为空");
					return SUCCESS;
				}
				else
				{
					model.setRelevanceId(Integer.valueOf(relevanceId));
				}
			}else if(ishave.equals("2"))
			{
				model.setSupplyId(0);
				if(informationId==null||informationId.equals(""))
				{
					map.put("responseCode", "1");
					map.put("msg", "informationId为空");
					return SUCCESS;
				}
				else
				{
					model.setInformationId(Integer.valueOf(informationId));
				}
			}
			boolean isComment = collectService.isCollect(userId,supplyId,ishave,informationId,relevanceId);
			if(isComment)
			{
				map.put("responseCode", "2");
			}else
			{
				boolean bool = collectService.addCollect(model);
				if(bool)
				{
					map.put("responseCode", "0");
				}else
				{
					map.put("responseCode", "1");
				}
			}
		} 
		catch (Exception e) 
		{
			map.put("responseCode", "1");
		}
		return SUCCESS;
	}
	
	/**
	 * 删除收藏
	 * @return
	 */
	public String deleteComment() 
	{
		try 
		{
			String userId = request.getParameter("userId");
			String supplyId = request.getParameter("supplyId");
			String ishave = request.getParameter("ishave");
			String informationId = request.getParameter("informationId");
			String relevanceId = request.getParameter("relevanceId");
			
			boolean bool = collectService.isCollect(userId,supplyId,ishave,informationId,relevanceId);
			
			if(bool)
			{
				boolean boo = collectService.delCollect(userId,supplyId,ishave,informationId,relevanceId);
				if(boo)
				{
					map.put("responseCode", "0");
				}else
				{
					map.put("responseCode", "1");
				}
			}else
			{
				map.put("responseCode", "2");
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

	public Collect getModel() 
	{
		return model;
	}

	public HttpServletRequest getRequest() 
	{
		return request;
	}

	public void setRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	public Map<String, Object> getMap() 
	{
		return map;
	}

	public void setMap(Map<String, Object> map) 
	{
		this.map = map;
	}

	public void setModel(Collect model) 
	{
		this.model = model;
	}
	public CollectService getCollectService() 
	{
		return collectService;
	}
	public void setCollectService(CollectService collectService) 
	{
		this.collectService = collectService;
	}

}
