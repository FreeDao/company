package com.marck.in.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.CommonUtil;
import com.marck.common.PageUtil;
import com.marck.common.dao.HDB;
import com.marck.common.model.Comment;
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
		for( Info i : (List<Info>)pu.getData()){
			String sql = "select (select r.name from region r where r.id="+i.getProvinceId()+") provinceName,(select r.name from region r where r.id="+i.getCityId()+") cityName,(select r.name from region r where r.id="+i.getAreaId()+") areaName,(select t.name from type t where t.id="+i.getTypeId()+")typeName,(select t.name from type t where t.id="+i.getSubTypeId()+") subTypeName from dual";
			Object[] obj = (Object[]) hdb.findUniqueSql(sql);
			i.setProvinceName((String) obj[0]);
			i.setCityName((String)obj[1]);
			i.setAreaName((String)obj[2]);
			i.setTypeName((String)obj[3]);
			i.setSubTypeName((String)obj[4]);
		}
		map.put("heads", pu.getData());
		String temp_id = "";
		int s = 1;
		for(Info i : (List<Info>)pu.getData()){
			if( s == 1){
				temp_id += i.getId();
				s++;
			}else{
				temp_id += "," + i.getId();
			}
		}
		hql = infoCondition(info,temp_id,hql);
		
		pu = hdb.findHql(hql, pageNo, pageNum);
		for( Info i : (List<Info>)pu.getData()){
			String sql = "select (select r.name from region r where r.id="+i.getProvinceId()+") provinceName,(select r.name from region r where r.id="+i.getCityId()+") cityName,(select r.name from region r where r.id="+i.getAreaId()+") areaName,(select t.name from type t where t.id="+i.getTypeId()+")typeName,(select t.name from type t where t.id="+i.getSubTypeId()+") subTypeName from dual";
			Object[] obj = (Object[]) hdb.findUniqueSql(sql);
			i.setProvinceName((String) obj[0]);
			i.setCityName((String)obj[1]);
			i.setAreaName((String)obj[2]);
			i.setTypeName((String)obj[3]);
			i.setSubTypeName((String)obj[4]);
		}
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
	 * @param sort 排序方式默认，1：价格最低，2：人气最高。3：评价最高，4：离我最近，5:最新发布
	 */
	public void setSellerList(Seller seller, Map<String, Object> map, Integer pageNo,
			Integer pageNum, String sort) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.isTop = 1 and s.menuId="+seller.getMenuId();
		PageUtil pu = hdb.findHql(hql, 1, 5);
		for( Seller s : (List<Seller>)pu.getData()){
			String sql = "select (select r.name from region r where r.id="+s.getProvinceId()+") provinceName,(select r.name from region r where r.id="+s.getCityId()+") cityName,(select r.name from region r where r.id="+s.getAreaId()+") areaName,(select t.name from type t where t.id="+s.getTypeId()+")typeName,(select t.name from type t where t.id="+s.getSubTypeId()+") subTypeName from dual";
			Object[] obj = (Object[]) hdb.findUniqueSql(sql);
			s.setProvinceName((String) obj[0]);
			s.setCityName((String)obj[1]);
			s.setAreaName((String)obj[2]);
			s.setTypeName((String)obj[3]);
			s.setSubTypeName((String)obj[4]);
		}
		map.put("heads", pu.getData());
		String temp_id = "";
		int i = 1;
		for(Seller s : (List<Seller>)pu.getData()){
			if( i == 1){
				temp_id += s.getId();
				i++;
			}else{
				temp_id += "," + s.getId();
			}
		}
		
		String sql = sellerCondition(temp_id,seller,hql,sort);
		pu = hdb.findSql(sql,Seller.class, pageNo, pageNum);
		for( Seller s : (List<Seller>)pu.getData()){
			sql = "select (select r.name from region r where r.id="+s.getProvinceId()+") provinceName,(select r.name from region r where r.id="+s.getCityId()+") cityName,(select r.name from region r where r.id="+s.getAreaId()+") areaName,(select t.name from type t where t.id="+s.getTypeId()+")typeName,(select t.name from type t where t.id="+s.getSubTypeId()+") subTypeName from dual";
			Object[] obj = (Object[]) hdb.findUniqueSql(sql);
			s.setProvinceName((String) obj[0]);
			s.setCityName((String)obj[1]);
			s.setAreaName((String)obj[2]);
			s.setTypeName((String)obj[3]);
			s.setSubTypeName((String)obj[4]);
		}
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
		String hql = "";
		String temp_id = "";
		PageUtil pu = new PageUtil();
		List<Commodity> commodities = new ArrayList<Commodity>();
		
		//在查询自己供求信息的情况下是不需要置顶的
		if(CommonUtil.validParams(commodity.getUserId())){
			hql = "from Commodity c where c.isTop = 1 and c.menuId="+commodity.getMenuId();
			pu = hdb.findHql(hql, 1, 5);
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
			
			for( Commodity c : (List<Commodity>)pu.getData()){
				String sql = "select (select r.name from region r where r.id="+c.getProvinceId()+") provinceName,(select r.name from region r where r.id="+c.getCityId()+") cityName,(select r.name from region r where r.id="+c.getAreaId()+") areaName,(select t.name from type t where t.id="+c.getTypeId()+")typeName,(select t.name from type t where t.id="+c.getSubTypeId()+") subTypeName from dual";
				Object[] obj = (Object[]) hdb.findUniqueSql(sql);
				c.setProvinceName((String) obj[0]);
				c.setCityName((String)obj[1]);
				c.setAreaName((String)obj[2]);
				c.setTypeName((String)obj[3]);
				c.setSubTypeName((String)obj[4]);
			}
			map.put("heads", pu.getData());
			
			int i = 1;
			for(Commodity c : (List<Commodity>)pu.getData()){
				if( i == 1){
					temp_id += c.getId();
					i++;
				}else{
					temp_id += "," + c.getId();
				}
			}
		}
		
		//普通list信息
		hql = commodityCondition(temp_id,commodity,hql,map);
		
		if(!CommonUtil.validParams(map.get("msg"))){
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
		for( Commodity c : (List<Commodity>)pu.getData()){
			String sql = "select (select r.name from region r where r.id="+c.getProvinceId()+") provinceName,(select r.name from region r where r.id="+c.getCityId()+") cityName,(select r.name from region r where r.id="+c.getAreaId()+") areaName,(select t.name from type t where t.id="+c.getTypeId()+")typeName,(select t.name from type t where t.id="+c.getSubTypeId()+") subTypeName from dual";
			Object[] obj = (Object[]) hdb.findUniqueSql(sql);
			c.setProvinceName((String) obj[0]);
			c.setCityName((String)obj[1]);
			c.setAreaName((String)obj[2]);
			c.setTypeName((String)obj[3]);
			c.setSubTypeName((String)obj[4]);
		}
		map.put("lists", pu.getData());
		map.put("msg", "查询成功");
		map.put("code", 1);
		map.put("hasNext", pu.getHasNext());
	}

	/**
	 * 资讯条件拼装
	 * @param info
	 * @param temp_id
	 * @param hql
	 * @return
	 */
	private String infoCondition(Info info, String temp_id, String hql) {
		// TODO Auto-generated method stub
		hql = "from Info i where i.menuId="+info.getMenuId();
		
		if(!CommonUtil.validParams(temp_id)){
			hql += " and i.id not in ("+temp_id+")";
		}
		if(!CommonUtil.validParams(info.getTypeId())){
			hql += " and i.typeId="+info.getTypeId();
		}
		if(!CommonUtil.validParams(info.getSubTypeId())){
			hql += " and i.subTypeId="+info.getSubTypeId();
		}
		if(!CommonUtil.validParams(info.getProvinceId())){
			hql += " and i.provinceId="+info.getProvinceId();
		}
		if(!CommonUtil.validParams(info.getCityId())){
			hql += " and i.cityId="+info.getCityId();
		}
		if(!CommonUtil.validParams(info.getAreaId())){
			hql += " and i.areaId="+info.getAreaId();
		}
		return hql;
	}
	
	/**
	 * 商家条件拼装hql
	 * @param seller
	 * @param hql
	 * @param sort  排序方式默认，1：价格最低，2：人气最高。3：评价最高，4：离我最近，5:最新发布
	 * @return 
	 */
	private String sellerCondition(String temp_id, Seller seller, String sql, String sort) {
		// TODO Auto-generated method stub
		sql = "select s.* from seller s where s.menuId="+seller.getMenuId();
		
		if(!CommonUtil.validParams(temp_id)){
			sql += " and s.id not in ("+temp_id+")";
		}
		if(!CommonUtil.validParams(seller.getCategory())){
			sql += " and s.category="+seller.getCategory();
		}
		if(!CommonUtil.validParams(seller.getTypeId())){
			sql += " and s.typeId="+seller.getTypeId();
		}
		if(!CommonUtil.validParams(seller.getSubTypeId())){
			sql += " and s.subTypeId="+seller.getSubTypeId();
		}
		if(!CommonUtil.validParams(seller.getProvinceId())){
			sql += " and s.provinceId="+seller.getProvinceId();
		}
		if(!CommonUtil.validParams(seller.getCityId())){
			sql += " and s.cityId="+seller.getCityId();
		}
		if(!CommonUtil.validParams(seller.getAreaId())){
			sql += " and s.areaId="+seller.getAreaId();
		}
		
		if(!CommonUtil.validParams(sort)){
			switch (Integer.parseInt(sort)) {
			case 2:
				sql += " order by (select count(*) from comment c where c.menuId=s.menuId and c.pid = s.id ) desc ";
				break;
			case 3:
				sql += " order by (select sum(c.level) from comment c where c.menuId=s.menuId and c.pid = s.id ) desc ";
				break;
			case 5:
				sql += " order by s.addTime desc ";
				break;
			default:
				sql += " order by ACOS(SIN(("+seller.getLat()+" * 3.1415) / 180 ) *SIN((s.lat * 3.1415) / 180 ) + COS(("+seller.getLat()+" * 3.1415) / 180 ) * COS(( s.lat * 3.1415) / 180 ) *COS(("+seller.getLng()+" * 3.1415) / 180 - (s.lng * 3.1415) / 180 ) ) * 6380 asc,s.addTime DESC ";
				break;
			}
		}
		
		return sql;
	}
	
	/**
	 * 商品条件拼装hql
	 * @param temp_id 
	 * @param commodity
	 * @param hql
	 * @param map 
	 */
	private String commodityCondition(String temp_id, Commodity commodity, String hql, Map<String, Object> map) {
		// TODO Auto-generated method stub
		hql = "from Commodity c where  c.menuId="+commodity.getMenuId();
		
		if(!CommonUtil.validParams(temp_id)){
			hql += " and c.id not in ("+temp_id+") ";
		}
		if(!CommonUtil.validParams(commodity.getSupplyDemand())){
			hql +=" and c.supplyDemand="+commodity.getSupplyDemand();
		}
		if(!CommonUtil.validParams(commodity.getCategory())){
			hql += " and c.category="+commodity.getCategory();
		}
		if(!CommonUtil.validParams(commodity.getTypeId())){
			hql += " and c.typeId="+commodity.getTypeId();
		}
		if(!CommonUtil.validParams(commodity.getSubTypeId())){
			hql += " and c.subTypeId="+commodity.getSubTypeId();
		}
		if(!CommonUtil.validParams(commodity.getProvinceId())){
			hql += " and c.provinceId="+commodity.getProvinceId();
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
				map.put("msg", "没有商铺，请先填写商铺信息");
				map.put("code", 0);
			}else{
				hql += " and c.sellerId="+seller.getId();
			}
		}
		if(!CommonUtil.validParams(commodity.getLng(),commodity.getLat())){
			hql += " order by ACOS(SIN(("+commodity.getLat()+" * 3.1415) / 180 ) *SIN((c.lat * 3.1415) / 180 ) + COS(("+commodity.getLat()+" * 3.1415) / 180 ) * COS(( c.lat * 3.1415) / 180 ) *COS(("+commodity.getLng()+" * 3.1415) / 180 - (c.lng * 3.1415) / 180 ) ) * 6380 asc,c.addTime DESC ";
		}
		return hql;
	}

}
