package com.marck.in.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.CommonUtil;
import com.marck.common.PageUtil;
import com.marck.common.dao.HDB;
import com.marck.common.model.Commodity;
import com.marck.common.model.Info;
import com.marck.common.model.Seller;
import com.marck.common.model.User;

@Component("listService")
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ListService {
	
	@Autowired
	private HDB hdb;

	/**
	 * 设置资讯列表
	 * @param menuId
	 * @param map
	 * @param pageNo
	 * @param pageNum
	 */
	public void setInfoList(Info info, Map<String, Object> map,
			Integer pageNo, Integer pageNum) {
		// TODO Auto-generated method stub
		//查询头列表
		String hql = "from Info i where i.isTop = 1 and  i.menuId="+info.getMenuId();
		PageUtil pu = hdb.findHql(hql,1,5);
		map.put("heads", pu.getData());
		hql = "from Info i where i.menuId="+info.getMenuId();
		pu = hdb.findHql(hql, pageNo, pageNum);
		map.put("lists", pu.getData());
		map.put("msg", "查询成功");
		map.put("code", 1);
		map.put("hasNext", pu.getHasNext());
	}

	/**
	 * 设置商家列表
	 * @param seller
	 * @param map
	 * @param pageNo
	 * @param pageNum
	 */
	public void setSellerList(Seller seller, Map<String, Object> map, Integer pageNo,
			Integer pageNum) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.isTop = 1 and s.menuId="+seller.getMenuId();
		PageUtil pu = hdb.findHql(hql, 1, 5);
		map.put("heads", pu.getData());
		hql = "from Seller s where s.menuId="+seller.getMenuId();
		
		sellerCondition(seller,hql);
		
		pu = hdb.findHql(hql, pageNo, pageNum);
		map.put("lists", pu.getData());
		map.put("msg", "查询成功");
		map.put("code", 1);
		map.put("hasNext", pu.getHasNext());
	}

	/**
	 * 设置商品列表
	 * @param commodity
	 * @param map
	 * @param pageNo
	 * @param pageNum
	 */
	public void setCommodityList(Commodity commodity, Map<String, Object> map,
			Integer pageNo, Integer pageNum) {
		//置顶的信息
		String hql = "from Commodity c where c.isTop = 1 and c.menuId="+commodity.getMenuId();
		PageUtil pu = hdb.findHql(hql, 1, 5);
		List<Commodity> commodities = (List<Commodity>) pu.getData();
		if(commodities.size() > 0)
		{
			for(Commodity c : commodities){
				if(!CommonUtil.validParams(c.getSellerId())){
					Seller s = (Seller) hdb.find(Seller.class, c.getSellerId());
					c.setAddress(s.getAddress());
					c.setCityId(s.getCityId());
					c.setAreaId(s.getAreaId());
					c.setLng(s.getLng());
					c.setLat(s.getLat());
					c.setTel(s.getTel());
					c.setContact(s.getContact());
					c.setCompany(s.getName());
				}
			}
			pu.setData(commodities);
		}
		map.put("heads", pu.getData());
		
		//普通list信息
		hql = "from Commodity c where c.menuId="+commodity.getMenuId();
		if(commodityCondition(commodity,hql)){
			map.put("msg", "没有商铺，请先填写商铺信息");
			map.put("code", 0);
			return;
		}
		pu = hdb.findHql(hql, pageNo, pageNum);
		commodities = (List<Commodity>) pu.getData();
		if(commodities.size() > 0)
		{
			for(Commodity c : commodities){
				if(!CommonUtil.validParams(c.getSellerId())){
					Seller s = (Seller) hdb.find(Seller.class, c.getSellerId());
					c.setAddress(s.getAddress());
					c.setCityId(s.getCityId());
					c.setAreaId(s.getAreaId());
					c.setLng(s.getLng());
					c.setLat(s.getLat());
					c.setTel(s.getTel());
					c.setContact(s.getContact());
					c.setCompany(s.getName());
				}
			}
			pu.setData(commodities);
		}
		map.put("lists", pu.getData());
		map.put("msg", "查询成功");
		map.put("code", 1);
		map.put("hasNext", pu.getHasNext());
	}

	/**
	 * 商家条件拼装hql
	 * @param seller
	 * @param hql
	 */
	private void sellerCondition(Seller seller, String hql) {
		// TODO Auto-generated method stub
		if(!CommonUtil.validParams(seller.getCategory())){
			hql += " and s.category="+seller.getCategory();
		}
		if(!CommonUtil.validParams(seller.getTypeId())){
			hql += " and s.typeId="+seller.getTypeId();
		}
		if(!CommonUtil.validParams(seller.getSubTypeId())){
			hql += " and s.subTypeId="+seller.getSubTypeId();
		}
		if(!CommonUtil.validParams(seller.getCityId())){
			hql += " and s.cityId="+seller.getCityId();
		}
		if(!CommonUtil.validParams(seller.getAreaId())){
			hql += " and s.areaId="+seller.getAreaId();
		}
		if(!CommonUtil.validParams(seller.getLng(),seller.getLat())){
			hql += " order by ACOS(SIN(("+seller.getLat()+" * 3.1415) / 180 ) *SIN((s.lat * 3.1415) / 180 ) + COS(("+seller.getLat()+" * 3.1415) / 180 ) * COS(( s.lat * 3.1415) / 180 ) *COS(("+seller.getLng()+" * 3.1415) / 180 - (s.lng * 3.1415) / 180 ) ) * 6380 asc,s.addTime DESC ";
		}
	}
	
	/**
	 * 商品条件拼装hql
	 * @param commodity
	 * @param hql
	 */
	private boolean commodityCondition(Commodity commodity, String hql) {
		// TODO Auto-generated method stub
		if(!CommonUtil.validParams(commodity.getCategory())){
			hql += " and c.category="+commodity.getCategory();
		}
		if(!CommonUtil.validParams(commodity.getTypeId())){
			hql += " and c.typeId="+commodity.getTypeId();
		}
		if(!CommonUtil.validParams(commodity.getSubTypeId())){
			hql += " and c.subTypeId="+commodity.getSubTypeId();
		}
		if(!CommonUtil.validParams(commodity.getCityId())){
			hql += " and c.cityId="+commodity.getCityId();
		}
		if(!CommonUtil.validParams(commodity.getAreaId())){
			hql += " and c.areaId="+commodity.getAreaId();
		}
		if(!CommonUtil.validParams(commodity.getEndTime())){
			hql += " and c.endTime > "+commodity.getEndTime();
		}
		if(!CommonUtil.validParams(commodity.getStatus())){
			hql += " and c.status="+commodity.getStatus();
		}
		if(!CommonUtil.validParams(commodity.getUserId())){
			Seller seller = (Seller) hdb.findUniqueHql("from Seller s where s.userId="+commodity.getUserId());
			if( null == seller ){
				return true;
			}else{
				hql += " and c.sellerId="+seller.getId();
			}
		}
		if(!CommonUtil.validParams(commodity.getLng(),commodity.getLat())){
			hql += " order by ACOS(SIN(("+commodity.getLat()+" * 3.1415) / 180 ) *SIN((c.lat * 3.1415) / 180 ) + COS(("+commodity.getLat()+" * 3.1415) / 180 ) * COS(( c.lat * 3.1415) / 180 ) *COS(("+commodity.getLng()+" * 3.1415) / 180 - (c.lng * 3.1415) / 180 ) ) * 6380 asc,c.addTime DESC ";
		}
		return false;
	}

}
