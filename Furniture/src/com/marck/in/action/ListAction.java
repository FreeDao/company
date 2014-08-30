package com.marck.in.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.model.Commodity;
import com.marck.common.model.Info;
import com.marck.common.model.Seller;
import com.marck.in.service.ListService;

@Component("listAction")
@Scope("prototype")
public class ListAction extends BaseAction{

	@Autowired
	private ListService listService;
	
	private Info info;
	private Seller seller;
	private Commodity commodity;
	
	private Integer pageNo;
	private Integer pageNum;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 查询咨询列表
	 */
	public String info(){
		try {
			
			if( null == info || CommonUtil.validParams(info.getMenuId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			listService.setInfoList(info,map,pageNo,pageNum);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询咨询列表异常",e);
			map.put("msg", "查询咨询列表异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询商家（企业，公司）列表
	 */
	public String seller(){
		try {
			
			if( null == seller || CommonUtil.validParams(seller.getMenuId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			listService.setSellerList(seller,map,pageNo,pageNum);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询商家列表异常",e);
			map.put("msg", "查询商家列表异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询商品列表
	 * @return
	 */
	public String commodity(){
		try {
			
			if( null == commodity || CommonUtil.validParams(commodity.getMenuId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			listService.setCommodityList(commodity,map,pageNo,pageNum);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询商品列表异常",e);
			map.put("msg", "查询商品列表异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getMap() {
		return map;//null == map?"":map;
	
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Info getInfo() {
		return info;//null == info?"":info;
	
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public Seller getSeller() {
		return seller;//null == seller?"":seller;
	
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Commodity getCommodity() {
		return commodity;//null == commodity?"":commodity;
	
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Integer getPageNo() {
		return pageNo;//null == pageNo?"":pageNo;
	
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageNum() {
		return pageNum;//null == pageNum?"":pageNum;
	
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	} 
	
	
}
