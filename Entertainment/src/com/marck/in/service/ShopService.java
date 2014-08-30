package com.marck.in.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.CommonUtil;
import com.marck.common.DateUtil;
import com.marck.common.PageUtil;
import com.marck.common.dao.HDB;
import com.marck.common.model.Address;
import com.marck.common.model.Commodity;
import com.marck.common.model.Order;
import com.marck.common.model.Shop;
import com.marck.common.model.Type;

@Component("shopService")
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ShopService {
	
	@Autowired
	private HDB hdb;

	/**
	 * 添加用户收货地址
	 * @param userAddr
	 * @param map
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void addOrUpdateUserAddr(Address userAddr, Map<String, Object> map) {
		// TODO Auto-generated method stub
		if(CommonUtil.validParams(userAddr.getIsDefualt())){
			userAddr.setIsDefualt(2);
		}
		hdb.saveOrUpdate(userAddr);
		map.put("msg", "操作成功");
		map.put("code", 1);
		map.put("userAddr", userAddr);
	}

	/**
	 * 查询用户收货地址
	 * @param userAddr
	 * @param map
	 * @param pageNo
	 * @param pageNum
	 */
	public void selAddrList(Address userAddr, Map<String, Object> map,
			Integer pageNo, Integer pageNum) {
		// TODO Auto-generated method stub
		String hql = "from UserAddr u where u.userId="+userAddr.getUserId();
		PageUtil pu = hdb.findHql(hql, pageNo, pageNum);
		map.put("msg", "查询成功");
		map.put("code", 1);
		map.put("lists", pu.getData());
		map.put("hasNext", pu.hasNext);
	}

	
	/**
	 * 删除用户收货地址
	 * @param userAddr
	 * @param map
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void delAddr(Address userAddr, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "from UserAddr u where u.userId="+userAddr.getUserId()+" and u.id ="+userAddr.getId();
		List<Address> uas = (List<Address>) hdb.findHql(hql); 
		if(uas.size() > 0){
			hdb.delete(uas.get(0));
			map.put("msg", "删除成功");
			map.put("code", 1);
		}else{
			map.put("msg", "删除失败，没有该地址或用户不正确");
			map.put("code", 0);
		}
	}

	/**
	 * 查询尺码列表
	 * @param commodity
	 * @param map
	 */
	public void selCommodityType(Commodity commodity, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "select t from Commodity c,Type t,CommodityType ct where c.id = ct.commodityId and t.id = ct.typeId and ct.commodityId = " + commodity.getId() + " and t.type="+commodity.getType();
		
		List<Type> sizes = (List<Type>) hdb.findHql(hql);
		
		map.put("msg", "查询成功");
		map.put("lists", sizes);
		map.put("code", 1);
	}

	/**
	 * 查询购物车列表
	 * @param shop
	 * @param map
	 */
	public void selShopList(Shop shop, Map<String, Object> map,Integer pageNo,Integer pageNum) {
		// TODO Auto-generated method stub
		String hql = "select new Shop(s.id, s.userId, s.commodityId,s.num,s.menuId,s.sizeId,s.colorId,s.addTime,c.title,c.price,c.content, concat((select se.tel from Seller se where se.id=c.sellerId ),c.tel),(select t.name from Type t where t.id=s.sizeId),(select t.name from Type t where t.id=s.colorId)) from Shop s,Commodity c where s.commodityId = c.id and s.userId="+shop.getUserId()+" and s.menuId="+shop.getMenuId();
		
		PageUtil pu = hdb.findHql(hql,pageNo,pageNum);
		
		map.put("lists", pu.getData());
		map.put("msg", "查询成功");
		map.put("code", 1);
	}

	/**
	 * 添加商品进购物车
	 * @param shop
	 * @param map
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void addShop(Shop shop, Map<String, Object> map) {
		// TODO Auto-generated method stub
		shop.setAddTime(DateUtil.getNowString("yyyy-MM-dd HH:mm:ss"));
		hdb.saveOrUpdate(shop);
		map.put("Shop", shop);
		map.put("msg", "添加成功");
		map.put("code", 1);
	}

	/**
	 * 删除购物车商品
	 * @param shop
	 * @param map
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void delShop(Shop shop, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "from Shop s where s.userId="+shop.getUserId()+" and s.id ="+shop.getId();
		List<Address> uas = (List<Address>) hdb.findHql(hql); 
		if(uas.size() > 0){
			hdb.delete(uas.get(0));
			map.put("msg", "删除成功");
			map.put("code", 1);
		}else{
			map.put("msg", "删除失败，没有该商品或用户不正确");
			map.put("code", 0);
		}
	}

	/**
	 * 查询用户所有订单
	 * @param order
	 * @param map
	 */
	public void selUserOrder(Order order, Map<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

}
