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
import com.marck.common.model.Comment;
import com.marck.common.model.Commodity;
import com.marck.common.model.Img;
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
		
		try {
			if(imgs != null){
				//图片上传
				String md5Code = "";
				for (int i = 0; i < imgs.size(); i++) {
					md5Code = CommonUtil.Md5(imgs.get(i));
					File f = new File(filepath+"/"+md5Code+"."+imgsContentType.get(i).substring(imgsContentType.get(i).indexOf("/")+1));
					if(!f.exists()){
						f.createNewFile();
					}
					
					FileUtils.copyFile(imgs.get(i), f);
					
					  if( i == 0){
						  seller.setLogo(projectUrl + "/"+uploadFolder+"/"+md5Code+"."+imgsContentType.get(i).substring(imgsContentType.get(i).indexOf("/")+1));
						  hdb.saveOrUpdate(seller);
					  }else{
						  Img img = new Img();
						  img.setUrl(projectUrl + "/"+uploadFolder+"/"+md5Code+"."+imgsContentType.get(i).substring(imgsContentType.get(i).indexOf("/")+1));
						  String name = imgsFileName.get(i);
						  img.setBrief(name.substring(0, name.indexOf(".")));
						  img.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
						  img.setPid(seller.getId());
						  img.setMenuId(seller.getMenuId());
						  hdb.saveOrUpdate(img);
					  }
					  //n.setLogo("http://www.tcshenghuo.org:8080/Greening/uploadImg/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
				}
			}else{
				if( !CommonUtil.validParams(seller.getId()) ){
					Seller s = (Seller) hdb.find(Seller.class, seller.getId());
					seller.setLogo(s.getLogo());
				}
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
		
		commodity.setSellerId(sellers.get(0).getId());
		
		try {
			if(imgs != null){
				//图片上传
				String md5Code = "";
				for (int i = 0; i < imgs.size(); i++) {
					md5Code = CommonUtil.Md5(imgs.get(i));
					File f = new File(filepath+"/"+md5Code+"."+imgsContentType.get(i).substring(imgsContentType.get(i).indexOf("/")+1));
					if(!f.exists()){
						f.createNewFile();
					}
					
					FileUtils.copyFile(imgs.get(i), f);
					
					  if( i == 0){
						  commodity.setLogo(projectUrl + "/"+uploadFolder+"/"+md5Code+"."+imgsContentType.get(i).substring(imgsContentType.get(i).indexOf("/")+1));
						  hdb.saveOrUpdate(commodity);
					  }else{
						  Img img = new Img();
						  img.setUrl(projectUrl + "/"+uploadFolder+"/"+md5Code+"."+imgsContentType.get(i).substring(imgsContentType.get(i).indexOf("/")+1));
						  String name = imgsFileName.get(i);
						  img.setBrief(name.substring(0, name.indexOf(".")));
						  img.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
						  img.setPid(commodity.getId());
						  img.setMenuId(commodity.getMenuId());
						  hdb.saveOrUpdate(img);
					  }
					  //n.setLogo("http://www.tcshenghuo.org:8080/Greening/uploadImg/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
				}
			}else{
				if( !CommonUtil.validParams(commodity.getId()) ){
					Commodity c = (Commodity) hdb.find(Commodity.class, commodity.getId());
					commodity.setLogo(c.getLogo());
				}
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
			map.put("msg", "查询成功");
			map.put("code", 1);
			map.put("Seller", seller);
		}else{
			map.put("msg", "还未创建商铺");
			map.put("code", 0);
		}
	}

}
