package com.marck.in.service;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.marck.common.model.Seller;
import com.marck.common.model.Shop;
import com.marck.common.model.ShopType;
import com.marck.common.model.ShopUserOrder;
import com.marck.common.model.Type;
import com.marck.common.model.UserOrder;

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
		userAddr.setAddress(CommonUtil.changeIos8859ToUtf8(userAddr.getAddress()));
		userAddr.setName(CommonUtil.changeIos8859ToUtf8(userAddr.getName()));
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
		String hql = "from Address a where a.userId="+userAddr.getUserId();
		
		if(userAddr.getIsDefualt() == 1){
			hql += " and a.isDefualt = 1";
		}
		
		PageUtil pu = hdb.findHql(hql, pageNo, pageNum);
		
		for( Address a : (List<Address>)pu.getData()){
			String sql = "select (select r.name from region r where r.id="+a.getProvinceId()+") provinceName,(select r.name from region r where r.id="+a.getCityId()+") cityName,(select r.name from region r where r.id="+a.getAreaId()+") areaName from dual";
			Object[] obj = (Object[]) hdb.findUniqueSql(sql);
			a.setProvinceName((String) obj[0]);
			a.setCityName((String)obj[1]);
			a.setAreaName((String)obj[2]);
		}
		
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
	public void delAddr(Address address, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "from UserOrder uo where uo.addressId = "+address.getId();
		List<?> list = hdb.findHql(hql);
		if(list.size() > 0){
			map.put("msg", "删除失败，该地址已生成订单！");
			map.put("code", 0);
			return;
		}
		hql = "from Address a where a.id="+address.getId()+" and a.userId ="+address.getUserId();
		Address a = (Address) hdb.findUniqueHql(hql);
		if( null == a){
			map.put("msg", "删除失败，没有该地址或用户不正确！");
			map.put("code", 0);
		}else{
			hdb.delete(a);
			map.put("msg", "删除成功");
			map.put("code", 1);
		}
	}

	/**
	 * 查询尺码列表
	 * @param commodity
	 * @param map
	 */
	public void selCommodityType(Commodity commodity, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "select t from Commodity c,Type t,CommodityType ct where c.id = ct.commodityId and t.id = ct.typeId and ct.commodityId = " + commodity.getId();
		
		if(!CommonUtil.validParams(commodity.getType())){
			hql += " and t.type="+commodity.getType();
		}
		
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
		//String hql = "select new Shop(s.id, s.userId, s.commodityId,s.num,s.menuId,s.addTime,c.title,c.price,c.content, concat((select se.tel from Seller se where se.id=c.sellerId ),c.tel)) from Shop s,Commodity c where s.commodityId = c.id and s.userId="+shop.getUserId()+" and s.menuId="+shop.getMenuId();
		
		String hql = " from Shop s where s.userId="+shop.getUserId()+" and s.menuId="+shop.getMenuId();
		 
		if(!CommonUtil.validParams(shop.getStatus())){
			hql += " and s.status="+shop.getStatus();
		}
		
		PageUtil pu = hdb.findHql(hql,pageNo,pageNum);
		
		for(Shop s : (List<Shop>)pu.getData()){
			Commodity c = (Commodity) hdb.find(Commodity.class, s.getCommodityId());
			s.setBrief(c.getContent());
			s.setPrice(c.getPrice());
			s.setName(c.getTitle());
			s.setLogo(c.getLogo());
			if(CommonUtil.validParams(c.getSellerId())){
				s.setTel(c.getTel());
			}else{
				Seller seller = (Seller) hdb.find(Seller.class, c.getSellerId());
				s.setTel(seller.getTel());
			}
			hql = "select t from ShopType st,Type t where st.typeId = t.id and st.shopId ="+s.getId();
			s.setTypeList((List<Type>)hdb.findHql(hql));
		}
		
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
		shop.setStatus(1);
		hdb.saveOrUpdate(shop);
		List<Type> types = new ArrayList<Type>();
		if(!CommonUtil.validParams(shop.getTypes())){
			String[] temp = shop.getTypes().split(",");
			for(int i = 0 ; i < temp.length ; i++){
				ShopType st = new ShopType();
				st.setShopId(shop.getId());
				st.setTypeId(Integer.parseInt(temp[i]));
				hdb.saveOrUpdate(st);
				types.add((Type)hdb.find(Type.class, Integer.parseInt(temp[i])));
			}
		}
		shop.setTypeList(types);
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
		String hql = "from Shop s where s.userId="+shop.getUserId()+" and s.id in ("+shop.getIds()+")";
		List<Shop> uas = (List<Shop>) hdb.findHql(hql); 
		for(Shop s : uas){
			hql = "from ShopType st where st.shopId = "+s.getId();
			List<ShopType> shopTypes = (List<ShopType>) hdb.findHql(hql);
			for(ShopType st : shopTypes){
				hdb.delete(st);
			}
			hdb.delete(s);
		}
		map.put("msg", "删除成功");
		map.put("code", 1);
		
		if(uas.size() == 0){
			map.put("msg", "删除失败，没有该商品或用户不正确");
			map.put("code", 0);
		}
	}

	/**
	 * 查询用户所有订单
	 * @param order
	 * @param map
	 * @param pageNum 
	 * @param pageNo 
	 */
	public void selUserOrder(UserOrder userOrder, Map<String, Object> map, Integer pageNo, Integer pageNum) {
		// TODO Auto-generated method stub
		String hql = "from UserOrder uo where uo.userId = "+userOrder.getUserId();
		
		if(!CommonUtil.validParams(userOrder.getStatus())){
			hql += " and uo.status = "+userOrder.getStatus();
		}
		
		PageUtil pu = hdb.findHql(hql,pageNo,pageNum);
		
		for(UserOrder uo : (List<UserOrder>)pu.getData()){
			hql = "select s from ShopUserOrder suo,Shop s where suo.shopId = s.id and suo.userOrderId = "+uo.getId();
			List<Shop> shops = (List<Shop>) hdb.findHql(hql);
			for(Shop s : shops){
				Commodity c = (Commodity) hdb.find(Commodity.class, s.getCommodityId());
				s.setName(c.getTitle());
				s.setPrice(c.getPrice());
				s.setBrief(c.getContent());
				s.setTel(c.getTel());
				if(!CommonUtil.validParams(c.getSellerId())){
					Seller seller = (Seller) hdb.find(Seller.class, c.getSellerId());
					s.setTel(seller.getTel());
				}
				hql = "select t from ShopType st,Type t where st.typeId = t.id and st.shopId ="+s.getId();
				List<Type> types = (List<Type>) hdb.findHql(hql);
				s.setTypeList(types);
			}
			uo.setShopList(shops);
			hql = "from Address a where a.id ="+uo.getAddressId();
			Address ad = (Address) hdb.findUniqueHql(hql);
			uo.setAddressName(ad.getAddress());
			uo.setTel(ad.getTel());
			uo.setName(ad.getName());
		}
		
		map.put("lists", pu.getData());
		map.put("hasNext", pu.getHasNext());
		map.put("msg", "查询成功");
		map.put("code", 1);
		
	}

	/**
	 * 添加用户订单
	 * @param userOrder
	 * @param map
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void addUserOrder(UserOrder userOrder, Map<String, Object> map) {
		// TODO Auto-generated method stub
		userOrder.setAddTime(DateUtil.getNowString("yyyy-MM-dd HH:mm:ss"));
		userOrder.setStatus(1);
		userOrder.setExpress(CommonUtil.changeIos8859ToUtf8(userOrder.getExpress()));
		userOrder.setBrief(CommonUtil.changeIos8859ToUtf8(userOrder.getBrief()));
		userOrder.setCode(Calendar.getInstance().getTime().getTime()+"");
		hdb.saveOrUpdate(userOrder);
		
		String[] shops = userOrder.getShops().split(",");
		List<Shop> shopList = new ArrayList<Shop>();
		for(int i = 0 ; i < shops.length ; i++){
			Shop shop = (Shop) hdb.find(Shop.class, Integer.parseInt(shops[i]));
			shop.setStatus(2);
			hdb.saveOrUpdate(shop);
			String hql = "select t from ShopType st,Type t where st.typeId = t.id and st.shopId ="+shop.getId();
			List<Type> types = (List<Type>) hdb.findHql(hql);
			shop.setTypeList(types);
			shopList.add(shop);
			ShopUserOrder suo = new ShopUserOrder();
			suo.setShopId(Integer.parseInt(shops[i]));
			suo.setUserOrderId(userOrder.getId());
			hdb.saveOrUpdate(suo);
		}
		userOrder.setShopList(shopList);
		
		map.put("userOrder", userOrder);
		map.put("msg", "添加成功");
		map.put("code", 1);
	}

	/**
	 * 删除订单
	 * @param userOrder
	 * @param map
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void delOrder(UserOrder userOrder, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "from UserOrder uo where uo.userId="+userOrder.getUserId()+" and uo.id in ("+userOrder.getIds()+")";
		List<UserOrder> uos = (List<UserOrder>) hdb.findHql(hql); 
		for(UserOrder uo : uos){
			hql="from ShopUserOrder suo where suo.userOrderId = "+uo.getId();
			List<ShopUserOrder> shopUserOrders = (List<ShopUserOrder>) hdb.findHql(hql);
			for(ShopUserOrder suo : shopUserOrders){
				Shop s  = (Shop) hdb.find(Shop.class, suo.getShopId());
				hql = "from ShopType st where st.shopId = "+s.getId();
				List<ShopType> shopTypes = (List<ShopType>) hdb.findHql(hql);
				for(ShopType st : shopTypes){
					hdb.delete(st);
				}
				hdb.delete(s);
				hdb.delete(suo);
			}
			hdb.delete(uo);
		}
		map.put("msg", "删除成功");
		map.put("code", 1);
		
		if(uos.size() == 0){
			map.put("msg", "删除失败，没有该订单或用户不正确");
			map.put("code", 0);
		}
	}

	/**
	 * 设置默认地址
	 * @param address
	 * @param map
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void setDefualtAddress(Address address, Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		String hql = "from Address a where a.userId ="+address.getUserId();
		
		List<Address> as = (List<Address>) hdb.findHql(hql);
		
		if(as.size() == 0){
			map.put("msg", "设置失败，没有该地址或用户不正确");
			map.put("code", 0);	
			return;
		}
		
		for(Address a : as){
			if(address.getId() == a.getId()){
				a.setIsDefualt(1);
			}else{
				a.setIsDefualt(2);
			}
			hdb.saveOrUpdate(a);
		}
		
		map.put("msg", "设置成功");
		map.put("code", 1);
		
	}

	/**
	 * 用户直接购买
	 * @param userOrder
	 * @param map
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void buy(UserOrder userOrder, Map<String, Object> map) {
		// TODO Auto-generated method stub
		userOrder.setAddTime(DateUtil.getNowString("yyyy-MM-dd HH:mm:ss"));
		userOrder.setStatus(1);
		userOrder.setExpress(CommonUtil.changeIos8859ToUtf8(userOrder.getExpress()));
		userOrder.setBrief(CommonUtil.changeIos8859ToUtf8(userOrder.getBrief()));
		userOrder.setCode(Calendar.getInstance().getTime().getTime()+"");
		hdb.saveOrUpdate(userOrder);
		
		Shop shop = new Shop();
		shop.setUserId(userOrder.getUserId());
		shop.setCommodityId(userOrder.getCommodityId());
		shop.setNum(userOrder.getNum());
		shop.setMenuId(userOrder.getMenuId());
		shop.setAddTime(DateUtil.getNowString("yyyy-MM-dd HH:mm:ss"));
		shop.setStatus(2);
		hdb.saveOrUpdate(shop);
		
		List<Type> types = new ArrayList<Type>();
		if(!CommonUtil.validParams(userOrder.getTypes())){
			String[] temp = userOrder.getTypes().split(",");
			for(int i = 0 ; i < temp.length ; i++){
				ShopType st = new ShopType();
				st.setShopId(shop.getId());
				st.setTypeId(Integer.parseInt(temp[i]));
				hdb.saveOrUpdate(st);
				types.add((Type)hdb.find(Type.class, Integer.parseInt(temp[i])));
			}
		}
		shop.setTypeList(types);
		
		List<Shop> shopList = new ArrayList<Shop>();
		shopList.add(shop);
		userOrder.setShopList(shopList);
		
		ShopUserOrder suo = new ShopUserOrder();
		suo.setShopId(shop.getId());
		suo.setUserOrderId(userOrder.getId());
		hdb.saveOrUpdate(suo);
		
		map.put("userOrder", userOrder);
		map.put("msg", "添加成功");
		map.put("code", 1);
	}

}
