package com.marck.bg.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
import com.marck.common.model.Info;
import com.marck.common.model.Region;
import com.marck.common.model.Seller;
import com.marck.common.model.Type;
import com.marck.common.model.User;

@Component("bgSellerService")
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SellerService {

	@Autowired
	private HDB hdb;

	/**
	 * 查询商家列表
	 * @param menuId
	 * @param queryValue
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil getSellerList(Integer menuId, String queryValue,
			Integer pageNow, Integer limit) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.menuId="+menuId;
		if( null != queryValue && !"".equals(queryValue) ){
			hql += " and s.name like '%"+queryValue+"%' or s.tel like '%"+queryValue+"%' or s.address like '%"+queryValue+"%' or s.contact like '%"+queryValue+"%' or s.email like '%"+queryValue+"%' or s.addTime like '%"+queryValue+"%' ";
			if("是".equals(queryValue)){
				hql += " or s.isTop = 1 ";
			}
			if("否".equals(queryValue)){
				hql += " or s.isTop <> 1 ";
			}
		}
		return hdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 查询商户
	 * @param seller
	 * @param imgs 
	 * @param menuId 
	 * @return
	 */
	public Seller getSeller(Seller seller) {
		// TODO Auto-generated method stub
		return (Seller) hdb.find(Seller.class, seller.getId());
	}

	/**
	 * 修改货更新商户
	 * @param seller
	 * @param menuId
	 * @param img
	 * @param imgContentType
	 * @param imgFileName
	 * @param filepath
	 * @param projectUrl
	 * @param uploadFolder
	 * @param imgs 
	 * @return
	 * @throws IOException 
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public boolean addOrUpdateSeller(Seller seller, Integer menuId, File img,
			String imgContentType, String imgFileName, String filepath,
			String projectUrl, String uploadFolder, List<Img> imgs) throws IOException {
		// TODO Auto-generated method stub
		boolean isNew = false;
		if(CommonUtil.validParams(seller.getId())){
			seller.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			seller.setMenuId(menuId);
			seller.setLogo(projectUrl+ "/img/zw.jpg");
			isNew = true;
		}else{
			Seller s = (Seller) hdb.find(Seller.class, seller.getId());
			seller.setLogo(s.getLogo());
			seller.setAddTime(s.getAddTime());
			seller.setMenuId(s.getMenuId());
		}
		
		Map<String , Object> map = new HashMap<String, Object>();
		
		
		String hql = "from Region r where r.id in ("+seller.getProvinceId()+","+seller.getCityId()+","+seller.getAreaId()+") order by r.level asc";
		
		List<Region> regions = (List<Region>) hdb.findHql(hql);
		String address = "";
		
		for(Region r : regions){
			address += r.getName();
		}
		
		address += seller.getAddress();
		
		if(Geocoding.getLatLng(address, map)){
			seller.setLng((Double)map.get("lng"));
			seller.setLat((Double)map.get("lat"));
		}
		
		if(img != null){
			//图片上传
			String md5Code = "";
			md5Code = CommonUtil.Md5(img);
			
			String path = CommonUtil.checkPath(projectUrl);
			
			File f = new File(path+"/"+md5Code+imgFileName.substring(imgFileName.lastIndexOf(".")));
			if(!f.exists()){
				f.createNewFile();
				FileUtils.copyFile(img, f);
			}
			
			seller.setLogo(projectUrl + "/download!img?name="+md5Code+imgFileName.substring(imgFileName.lastIndexOf(".")));

		}
		
		if(isNew){
			hdb.saveOrUpdate(seller);
		}else{
			hdb.merge(seller);
		}
		
		if( null != imgs){
			for(Img i : imgs){
				
				File temp = new File(i.getUrl());
				String md5Code = "";
				md5Code = CommonUtil.Md5(temp);
				String path = CommonUtil.checkPath(projectUrl);
				
				File f = new File(path+"/"+md5Code+i.getUrl().substring(i.getUrl().lastIndexOf(".")));
				if(!f.exists()){
					f.createNewFile();
					FileUtils.copyFile(temp, f);
				}
				
				i.setPid(seller.getId());
				i.setUrl(projectUrl + "/download!img?name="+md5Code+i.getUrl().substring(i.getUrl().lastIndexOf(".")));
				hdb.saveOrUpdate(i);
			}
		}
		
		return isNew;
	}

	/**
	 * 删除商户
	 * @param seller
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void delSeller(Seller seller) {
		// TODO Auto-generated method stub
		seller = (Seller) hdb.find(Seller.class, seller.getId());
		String hql = "from Comment c where c.menuId = "+seller.getMenuId()+" and c.pid="+seller.getId();
		List<Comment> comments = (List<Comment>) hdb.findHql(hql);
		for(Comment comment: comments){
			hdb.delete(comment);
		}
		hql = "from Commodity c where c.sellerId ="+seller.getId();
		List<Commodity> commodities = (List<Commodity>) hdb.findHql(hql);
		for(Commodity commodity : commodities){
			hdb.delete(commodity);
		}
		hdb.delete(seller);
	}

	public List<User> selUser() {
		// TODO Auto-generated method stub
		return (List<User>) hdb.findHql("from User u where u.phone <> 'admin' and u.id not in (select distinct(s.userId) from Seller s where s.userId is not null)");
	}

	/**
	 * 查询省
	 * @return
	 */
	public List<Region> selProvince() {
		// TODO Auto-generated method stub
		return (List<Region>) hdb.findHql("from Region r where r.level = 1 ");
	}

	/**
	 * 查询一级类别
	 * @return
	 */
	public List<Type> selType() {
		// TODO Auto-generated method stub
		return (List<Type>) hdb.findHql("from Type t where t.level = 1 ");
	}

	public List<Img> getImgs(Seller seller, List<Img> imgs, Integer menuId) {
		// TODO Auto-generated method stub
		String hql = "from Img i where i.pid="+seller.getId()+" and i.menuId ="+menuId;
		return (List<Img>) hdb.findHql(hql);
	}
	
}
