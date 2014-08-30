package com.marck.in.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.model.Address;
import com.marck.common.model.Commodity;
import com.marck.common.model.Shop;
import com.marck.common.model.UserOrder;
import com.marck.in.service.ShopService;

@Component("shopAction")
@Scope("prototype")
public class ShopAction extends BaseAction{

	@Autowired
	private ShopService shopService;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Integer pageNo;
	private Integer pageNum;
	
	private Shop shop;
	private Address address;
	private Commodity commodity;
	private UserOrder userOrder;
	
	/**
	 * 用户添加修改收货地址
	 */
	public String addOrUpdateAddr(){
		try {
			
			if(null == address  || CommonUtil.validParams(address.getUserId(),address.getProvinceId(),address.getCityId(),address.getAreaId(),address.getAddress(),address.getName(),address.getTel())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.addOrUpdateUserAddr(address,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加修改用户地址异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询用户收货地址列表
	 */
	public String selAddrList(){
		try {
			
			if(null == address  || CommonUtil.validParams(address.getUserId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.selAddrList(address,map,pageNo,pageNum);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询用户地址异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除收货地址
	 */
	public String delAddr(){
		try {
			
			if(null == address  || CommonUtil.validParams(address.getId(),address.getUserId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.delAddr(address,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除用户地址异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 设置默认地址
	 * @return
	 */
	public String setDefautAddress(){
		try {
			if(null == address  || CommonUtil.validParams(address.getId(),address.getUserId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.setDefualtAddress(address,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("设置用户地址异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 获取商品属性类别
	 */
	public String selCommodityType(){
		try {
			
			if(null == commodity  || CommonUtil.validParams(commodity.getId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.selCommodityType(commodity,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("获取尺码,颜色列表异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 获取用户购物车列表
	 */
	public String selShopList(){
		try {
			
			if(null == shop  || CommonUtil.validParams(shop.getUserId(),shop.getMenuId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.selShopList(shop,map,pageNo,pageNum);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("获取购物车列表异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 添加商品进购物车
	 */
	public String addShop(){
		try {
			
			if(null == shop  || CommonUtil.validParams(shop.getUserId(),shop.getMenuId(),shop.getCommodityId(),shop.getNum())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.addShop(shop,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加商品进购物车异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除购物车商品
	 */
	public String delShop(){
		try {
			
			if(null == shop  || CommonUtil.validParams(shop.getIds(),shop.getUserId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.delShop(shop,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除购物车商品异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询用户订单列表
	 * @return
	 */
	public String selOrderList(){
		try {
			
			if(null == userOrder  || CommonUtil.validParams(userOrder.getUserId(),userOrder.getStatus())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.selUserOrder(userOrder,map,pageNo,pageNum);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("查询用户订单列表异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 添加用户订单
	 * @return
	 */
	public String addOrder(){
		try {
			
			if(null == userOrder  || CommonUtil.validParams(userOrder.getUserId(),userOrder.getAddressId(),userOrder.getTotal(),userOrder.getExpress(),userOrder.getShops())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.addUserOrder(userOrder,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("添加订单列表异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 直接购买
	 * @return
	 */
	public String buy(){
		try {
			
			if(null == userOrder  || CommonUtil.validParams(userOrder.getUserId(),userOrder.getAddressId(),userOrder.getTotal(),userOrder.getExpress(),userOrder.getMenuId(),userOrder.getCommodityId(),userOrder.getNum())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.buy(userOrder,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("添加订单列表异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除订单
	 * @return
	 */
	public String delOrder(){
		try {
			
			if(null == userOrder  || CommonUtil.validParams(userOrder.getIds(),userOrder.getUserId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			shopService.delOrder(userOrder,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除购物车商品异常",e);
			map.put("msg", "操作异常");
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

	public Commodity getCommodity() {
		return commodity;
	
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Address getAddress() {
		return address;
	
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getPageNo() {
		return pageNo;
	
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageNum() {
		return pageNum;
	
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Shop getShop() {
		return shop;
	
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public UserOrder getUserOrder() {
		return userOrder;
	
	}

	public void setUserOrder(UserOrder userOrder) {
		this.userOrder = userOrder;
	}

}
