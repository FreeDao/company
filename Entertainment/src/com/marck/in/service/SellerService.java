package com.marck.in.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marck.common.CommonUtil;
import com.marck.common.DateUtil;
import com.marck.common.PageUtil;
import com.marck.common.dao.HDB;
import com.marck.common.geocoding.Geocoding;
import com.marck.common.model.Comment;
import com.marck.common.model.Commodity;
import com.marck.common.model.Img;
import com.marck.common.model.Region;
import com.marck.common.model.Seller;
import com.marck.common.model.User;

@Component("sellerService")
@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
public class SellerService {
	
	@Autowired
	private HDB hdb;

	/**
	 * 添加商品
	 * @param seller
	 * @param imgs
	 * @param imgsContentType
	 * @param imgsFileName
	 * @param projectUrl 
	 * @param filepath 
	 * @param uploadFolder 
	 * @param map
	 */
	public void addOrUpdateSeller(Seller seller, List<File> imgs,
			List<String> imgsContentType, List<String> imgsFileName,
			String filepath, String projectUrl, String uploadFolder, Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		String hql = "from Seller s where s.userId ="+seller.getUserId();
		
		Seller s = (Seller) hdb.findUniqueHql(hql);
		
		seller.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
		seller.setCategory(0);
		seller.setIsTop(2);
		String temp_addr = "";
		Region province =  (Region) hdb.find(Region.class, seller.getProvinceId()) ;
		temp_addr += province.getName();
		Region city =  (Region) hdb.find(Region.class, seller.getCityId()) ;
		temp_addr += city.getName();
		
		Geocoding.getLatLng(temp_addr, map);
		seller.setLng((Double) map.get("lng"));
		seller.setLat((Double) map.get("lat"));
		seller.setAreaId(Integer.parseInt(seller.getCityId()+"01"));
		
		if(!CommonUtil.validParams(s)){
			 seller.setAddTime(s.getAddTime());
			 seller.setId(s.getId());
			 hql = "from Img i where i.menuId="+s.getMenuId()+" and i.pid="+s.getId();
			 List<Img> is = (List<Img>) hdb.findHql(hql);
			 for(Img i : is){
				 hdb.delete(i);
			 }
		}
		
		try {
			if(imgs != null){
				//图片上传
				String md5Code = "";
				for (int i = 0; i < imgs.size(); i++) {
					
					md5Code = CommonUtil.Md5(imgs.get(i));
					
					String path = CommonUtil.checkPath(projectUrl);
					
					File f = new File(path+"/"+md5Code+imgsFileName.get(i).substring(imgsFileName.get(i).lastIndexOf(".")));
					if(!f.exists()){
						f.createNewFile();
						FileUtils.copyFile(imgs.get(i), f);
					}
					
				  if( i == 0){
					  seller.setLogo(projectUrl + "/download!img?name="+md5Code+imgsFileName.get(i).substring(imgsFileName.get(i).lastIndexOf(".")));
					  if(CommonUtil.validParams(s)){
						  hdb.saveOrUpdate(seller);
					  }
				  }else{
					  Img img = new Img();
					  img.setUrl(projectUrl + "/download!img?name="+md5Code+imgsFileName.get(i).substring(imgsFileName.get(i).lastIndexOf(".")));
					  String name = imgsFileName.get(i);
					  img.setBrief(name.substring(0, name.indexOf(".")));
					  img.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
					  img.setPid(seller.getId());
					  img.setMenuId(seller.getMenuId());
					  hdb.saveOrUpdate(img);
				  }
				}
			}
			
			seller.setName(CommonUtil.changeIos8859ToUtf8(seller.getName()));
			seller.setAddress(CommonUtil.changeIos8859ToUtf8(seller.getAddress()));
			seller.setSellerBrief(CommonUtil.changeIos8859ToUtf8(seller.getSellerBrief()));
			seller.setContact(CommonUtil.changeIos8859ToUtf8(seller.getContact()));
			
		    if(CommonUtil.validParams(s)){
			  hdb.saveOrUpdate(seller);
		    }else{
		      hdb.merge(seller);
		    }
			map.put("msg", "操作成功");
			map.put("code", 1);
			map.put("Seller", seller);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("msg", "图片上传出错");
			map.put("code", 0);
		}
	}

	/**
	 * 添加或修改供求
	 * @param commodity
	 * @param imgs
	 * @param imgsContentType
	 * @param imgsFileName
	 * @param filepath
	 * @param projectUrl
	 * @param uploadFolder
	 * @param map
	 */
	public void addOrUpdateSupplyDemand(Commodity commodity, List<File> imgs,
			List<String> imgsContentType, List<String> imgsFileName,
			String filepath, String projectUrl, String uploadFolder,
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		List<Seller> sellers = (List<Seller>) hdb.findHql("from Seller s where s.userId="+commodity.getUserId());
		if( sellers.size() == 0){
			map.put("mgs", "没有商铺，请先创建商铺");
			map.put("code", 0);
			return;
		}
		
		Commodity c = (Commodity) hdb.find(Commodity.class, commodity.getId());
		
		commodity.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
		commodity.setSellerId(sellers.get(0).getId());
		commodity.setStatus(1);
		
		if(!CommonUtil.validParams(c)){
			 commodity.setAddTime(c.getAddTime());
			 String hql = "from Img i where i.menuId="+c.getMenuId()+" and i.pid="+c.getId();
			 List<Img> is = (List<Img>) hdb.findHql(hql);
			 for(Img i : is){
				 hdb.delete(i);
			 }
		}
		
		try {
			if(imgs != null){
				//图片上传
				String md5Code = "";
				for (int i = 0; i < imgs.size(); i++) {
					md5Code = CommonUtil.Md5(imgs.get(i));
					
					String path = CommonUtil.checkPath(projectUrl);
					
					File f = new File(path+"/"+md5Code+imgsFileName.get(i).substring(imgsFileName.get(i).lastIndexOf(".")));
					if(!f.exists()){
						f.createNewFile();
						FileUtils.copyFile(imgs.get(i), f);
					}
					
					  if( i == 0){
						  commodity.setLogo(projectUrl + "/download!img?name="+md5Code+imgsFileName.get(i).substring(imgsFileName.get(i).lastIndexOf(".")));
						  if(CommonUtil.validParams(c)){
							  hdb.saveOrUpdate(commodity);
						  }
					  }else{
						  Img img = new Img();
						  img.setUrl(projectUrl + "/download!img?name="+md5Code+imgsFileName.get(i).substring(imgsFileName.get(i).lastIndexOf(".")));
						  String name = imgsFileName.get(i);
						  img.setBrief(name.substring(0, name.indexOf(".")));
						  img.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
						  img.setPid(commodity.getId());
						  img.setMenuId(commodity.getMenuId());
						  hdb.saveOrUpdate(img);
					  }
				}
			}
			
			commodity.setTitle(CommonUtil.changeIos8859ToUtf8(commodity.getTitle()));
			commodity.setContent(CommonUtil.changeIos8859ToUtf8(commodity.getContent()));
			
			if(CommonUtil.validParams(c)){
				hdb.saveOrUpdate(commodity);
			}else{
				hdb.merge(commodity);
			}
			map.put("msg", "操作成功");
			map.put("code", 1);
			map.put("Commodity", commodity);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("msg", "图片上传出错");
			map.put("code", 0);
		}
		
	}

	/**
	 * 更具用户查询商铺信息
	 * @param seller
	 * @param map
	 */
	public void selSellerByUser(Seller seller, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "select s from User u,Seller s where u.id = s.userId and s.userId="+seller.getUserId();
		List<Seller> sellers = (List<Seller>) hdb.findHql(hql);
		if(sellers.size() > 0){
			Seller s = sellers.get(0);
			String sql = "select (select r.name from region r where r.id="+s.getProvinceId()+") provinceName,(select r.name from region r where r.id="+s.getCityId()+") cityName,(select r.name from region r where r.id="+s.getAreaId()+") areaName,(select t.name from type t where t.id="+s.getTypeId()+")typeName,(select t.name from type t where t.id="+s.getSubTypeId()+") subTypeName from dual";
			
			Object[] obj = (Object[]) hdb.findUniqueSql(sql);
			s.setProvinceName((String) obj[0]);
			s.setCityName((String)obj[1]);
			s.setAreaName((String)obj[2]);
			s.setTypeName((String)obj[3]);
			s.setSubTypeName((String)obj[4]);
			
			map.put("msg", "查询成功");
			map.put("code", 1);
			map.put("Seller", s);
		}else{
			map.put("msg", "还未创建商铺");
			map.put("code", 0);
		}
	}

	/**
	 * 改变供求信息状态
	 * @param commodity
	 * @param map
	 */
	public void changeStatus(Commodity commodity, Map<String, Object> map) {
		// TODO Auto-generated method stub
		Commodity c = (Commodity) hdb.find(Commodity.class, commodity.getId());
		c.setStatus(commodity.getStatus());
		hdb.saveOrUpdate(c);
		map.put("msg", "操作成功");
		map.put("code",1);
	}

}
