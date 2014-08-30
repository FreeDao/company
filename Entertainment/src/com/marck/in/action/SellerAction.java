package com.marck.in.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.model.Commodity;
import com.marck.common.model.Img;
import com.marck.common.model.Seller;
import com.marck.in.service.ImgService;
import com.marck.in.service.SellerService;

@Component("sellerAction")
@Scope("prototype")
public class SellerAction extends BaseAction{

	@Autowired
	private SellerService sellerService;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Seller seller;
	private Commodity commodity;
	
	private List<File> imgs;
	private List<String> imgsContentType;
	private List<String> imgsFileName;
	 
	/**
	 * 添加修改商铺
	 */
	public String addOrUpdateSeller(){
		try {
			
			if(null == seller  || CommonUtil.validParams(seller.getUserId(),seller.getName(),seller.getMenuId(),seller.getProvinceId(),seller.getCityId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			String filepath=request.getRealPath("/"+uploadFolder);
			
			sellerService.addOrUpdateSeller(seller,imgs,imgsContentType,imgsFileName,filepath,projectUrl,uploadFolder,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加修改商铺异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 添加修改供求商品
	 */
	public String addOrUpdateSupplyDemand(){
		try {
			
			if(null == commodity  || CommonUtil.validParams(commodity.getUserId(),commodity.getTitle(),commodity.getEndTime(),commodity.getMenuId(),commodity.getSupplyDemand())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			String filepath=request.getRealPath("/"+uploadFolder);
			
			sellerService.addOrUpdateSupplyDemand(commodity,imgs,imgsContentType,imgsFileName,filepath,projectUrl,uploadFolder,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加修改供求异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询用户商铺信息
	 */
	public String selSellerByUser(){
		try {
			
			if(null == seller  || CommonUtil.validParams(seller.getUserId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			sellerService.selSellerByUser(seller,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询商铺异常",e);
			map.put("msg", "查询异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询用户商铺信息
	 */
	public String changeSupplyDemandStatus(){
		try {
			
			if(null == commodity  || CommonUtil.validParams(commodity.getId(),commodity.getStatus())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			sellerService.changeStatus(commodity,map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("改变供求状态异常",e);
			map.put("msg", "操作异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 供求商品区域查询
	 * @return
	 */
	public String selRegionByCommodity(){
		
		return projectUrl;
	}
	
	public Map<String, Object> getMap() {
		return map;//null == map?"":map;
	
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Seller getSeller() {
		return seller;
	
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Commodity getCommodity() {
		return commodity;
	
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public List<File> getImgs() {
		return imgs;
	
	}

	public void setImgs(List<File> imgs) {
		this.imgs = imgs;
	}

	public List<String> getImgsContentType() {
		return imgsContentType;
	
	}

	public void setImgsContentType(List<String> imgsContentType) {
		this.imgsContentType = imgsContentType;
	}

	public List<String> getImgsFileName() {
		return imgsFileName;
	
	}

	public void setImgsFileName(List<String> imgsFileName) {
		this.imgsFileName = imgsFileName;
	}
	
	

}
