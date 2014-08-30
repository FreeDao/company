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
import com.marck.common.model.CommodityType;
import com.marck.common.model.Img;
import com.marck.common.model.Info;
import com.marck.common.model.Region;
import com.marck.common.model.Commodity;
import com.marck.common.model.Seller;
import com.marck.common.model.Type;
import com.marck.common.model.User;

@Component("commodityService")
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class CommodityService {

	@Autowired
	private HDB hdb;

	/**
	 * 查询商品列表
	 * @param menuId
	 * @param queryValue
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil getCommodityList(User user,Integer menuId, String queryValue,
			Integer pageNow, Integer limit) {
		// TODO Auto-generated method stub
		String hql = "from Commodity s where s.menuId="+menuId;
		
		if(user.getRole() != 0){
			Seller seller = (Seller) hdb.findUniqueHql("from Seller s where s.userId = "+user.getId());
			if( null == seller ){
				return null;
			}
			hql += " and s.sellerId ="+seller.getId();
		}
		
		if( null != queryValue && !"".equals(queryValue) ){
			hql += " and s.title like '%"+queryValue+"%' or s.price like '%"+queryValue+"%' or s.num like '%"+queryValue+"%' or s.preference like '%"+queryValue+"%' or s.addTime like '%"+queryValue+"%' ";
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
	 * 查询商品
	 * @param commodity
	 * @param imgs 
	 * @param menuId 
	 * @return
	 */
	public Commodity getCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		return (Commodity) hdb.find(Commodity.class, commodity.getId());
	}

	/**
	 * 修改货更新商品
	 * @param commodity
	 * @param menuId
	 * @param img
	 * @param imgContentType
	 * @param imgFileName
	 * @param filepath
	 * @param projectUrl
	 * @param uploadFolder
	 * @param imgs 
	 * @param colors 
	 * @param sizes 
	 * @return
	 * @throws IOException 
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public boolean addOrUpdateCommodity(Commodity commodity, Integer menuId, File img,
			String imgContentType, String imgFileName, String filepath,
			String projectUrl, String uploadFolder, List<Img> imgs, String[] sizes, String[] colors) throws IOException {
		// TODO Auto-generated method stub
		boolean isNew = false;
		if(CommonUtil.validParams(commodity.getId())){
			commodity.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			commodity.setMenuId(menuId);
			commodity.setLogo(projectUrl+ "/img/zw.jpg");
			isNew = true;
		}else{
			Commodity s = (Commodity) hdb.find(Commodity.class, commodity.getId());
			commodity.setLogo(s.getLogo());
			commodity.setAddTime(s.getAddTime());
			commodity.setMenuId(s.getMenuId());
			hdb.deleteSql("delete from commoditytype where commodityId="+commodity.getId());
		}
		
		Map<String , Object> map = new HashMap<String, Object>();
		
		//供求信息设置状态
		if(!CommonUtil.validParams(commodity.getSupplyDemand())){
			commodity.setStatus(1);
		}
		
		//未传入城市和区域不计算
		if(!CommonUtil.validParams(commodity.getProvinceId(),commodity.getCityId(),commodity.getAreaId())){
			String hql = "from Region r where r.id in ("+commodity.getProvinceId()+","+commodity.getCityId()+","+commodity.getAreaId()+") order by r.level asc";
			
			List<Region> regions = (List<Region>) hdb.findHql(hql);
			String address = "";
			
			for(Region r : regions){
				address += r.getName();
			}
			
			address += commodity.getAddress();
			
			if(Geocoding.getLatLng(address, map)){
				commodity.setLng((Double)map.get("lng"));
				commodity.setLat((Double)map.get("lat"));
			}
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
			
			commodity.setLogo(projectUrl + "/download!img?name="+md5Code+imgFileName.substring(imgFileName.lastIndexOf(".")));

		}
		
		if(isNew){
			hdb.saveOrUpdate(commodity);
		}else{
			hdb.merge(commodity);
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
				
				i.setPid(commodity.getId());
				i.setUrl(projectUrl + "/download!img?name="+md5Code+i.getUrl().substring(i.getUrl().lastIndexOf(".")));
				hdb.saveOrUpdate(i);
			}
		}
		
		//保存商品尺码
		if( null != sizes){
			for( int i = 0 ; i < sizes.length ;i++ ){
				CommodityType ct = new CommodityType();
				ct.setCommodityId(commodity.getId());
				ct.setTypeId(Integer.parseInt(sizes[i]));
				hdb.saveOrUpdate(ct);
			}
		}
		
		//保存商品颜色
		if( null != colors ){
			for( int i = 0 ; i < colors.length ;i++ ){
				CommodityType ct = new CommodityType();
				ct.setCommodityId(commodity.getId());
				ct.setTypeId(Integer.parseInt(colors[i]));
				hdb.saveOrUpdate(ct);
			}
		}
		
		return isNew;
	}

	/**
	 * 删除商品
	 * @param commodity
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void delCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		commodity = (Commodity) hdb.find(Commodity.class, commodity.getId());
		String hql = "from Comment c where c.menuId = "+commodity.getMenuId()+" and c.pid="+commodity.getId();
		List<Comment> comments = (List<Comment>) hdb.findHql(hql);
		for(Comment comment: comments){
			hdb.delete(comment);
		}
		hdb.delete(commodity);
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

	/**
	 * 查询图片集
	 * @param commodity
	 * @param imgs
	 * @param menuId
	 * @return
	 */
	public List<Img> getImgs(Commodity commodity, List<Img> imgs, Integer menuId) {
		// TODO Auto-generated method stub
		String hql = "from Img i where i.pid="+commodity.getId()+" and i.menuId ="+menuId;
		return (List<Img>) hdb.findHql(hql);
	}

	/**
	 * 查询商家列表
	 * @return
	 */
	public List<Seller> selSellerList() {
		// TODO Auto-generated method stub
		return (List<Seller>) hdb.findAll(Seller.class);
	}
	
	/**
	 * 查询商家
	 * @param user 
	 * @return
	 */
	public Seller selSeller(User user) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.userId="+user.getId();
		return (Seller) hdb.findUniqueHql(hql);
	}

	/**
	 * 查询颜色
	 * @return
	 */
	public List<Type> selColors() {
		// TODO Auto-generated method stub
		return (List<Type>) hdb.findHql("from Type t where t.type = 3");
	}
	
	/**
	 * 查询尺码
	 * @return
	 */
	public List<Type> selSizes() {
		// TODO Auto-generated method stub
		return (List<Type>) hdb.findHql("from Type t where t.type = 2");
	}

	/**
	 * 查询指定商品的颜色
	 * @param commodity
	 * @return
	 */
	public List<Type> selCommodityColors(Commodity commodity) {
		// TODO Auto-generated method stub
		return (List<Type>) hdb.findHql("select t from CommodityType ct,Type t where ct.typeId = t.id and t.type = 3 and ct.commodityId ="+commodity.getId());
	}

	/**
	 * 查询指定商品尺码
	 * @param commodity
	 * @return
	 */
	public List<Type> selCommoditySizes(Commodity commodity) {
		// TODO Auto-generated method stub
		return (List<Type>) hdb.findHql("select t from CommodityType ct,Type t where ct.typeId = t.id and t.type = 2  and ct.commodityId ="+commodity.getId());
	}
}
