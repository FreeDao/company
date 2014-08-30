package com.era.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.util.LimitedInputStream;
import org.apache.commons.io.FileUtils;

import net.sf.json.JSONArray;

import com.era.dao.BaseDAO;
import com.era.orm.Busmarset;
import com.era.orm.Comment;
import com.era.orm.Images;
import com.era.orm.Mall;
import com.era.orm.Market;
import com.era.orm.Product;
import com.era.orm.Seller;
import com.era.orm.Sellermanager;
import com.era.orm.Share;
import com.era.orm.ShopType;
import com.era.orm.ShopTypeTwo;
import com.era.orm.User;
import com.era.orm.Video;
import com.era.orm.Village;
import com.era.service.SellerService;
import com.era.util.BaseUtils;
import com.era.util.DateUtil;
import com.era.util.LoginUser;
import com.jspsmart.upload.Request;
import com.sun.org.apache.bcel.internal.generic.Select;

public class SellerServiceImpl implements SellerService {
	private BaseDAO dao;
	private String hql = "";

	@Override
	public int numberSeller(String name,String shopId) {

		String hql = "select count(*) from Seller where 1 = 1";
		if (name == null || name.equals("")) 
		{
			
		} else {
			hql += " and titile like '%" + name + "%'";
		}
		if(shopId == null || shopId.equals(""))
		{
			
		}
		else
		{
			hql += " and shop ="+shopId;
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<Seller> selSeller(String name,String shopId, int pageNo, int pageSize)
	{
		String hql = "from Seller where 1 = 1";
		if (name == null || name.equals("")) 
		{
			
		}
		else 
		{
			hql += " and titile like '%" + name + "%'";
		}
		if(shopId == null || shopId.equals(""))
		{
			
		}
		else
		{
			hql += " and shop ="+shopId;
		}
		hql+=" order by addtime desc";
		List<Seller> list = dao.query(hql, pageNo, pageSize);
		return list;
	}

	@Override
	public List<Seller> selSellerById(int id) {
		hql="from Seller where id="+id;
		List<Seller> list=dao.query(hql);
		return list;
	}

	@Override
	public void delSeller(int id) {
		try {
			Sellermanager sm = (Sellermanager) dao.query("from Sellermanager where sellerId = "+id).get(0);
			List<User> us = dao.query("from User where sellerId="+sm.getId());
			for(User u : us){
				dao.delById(User.class, u.getId());
			}
		} catch (Exception e) {
		}
		dao.delById(Seller.class, id);
		
	}
	
	/**
	 * TODO 客户端查询获取商家信息
	 */
	@Override
	public List getSellerInfo(String cityId, String logNum, String dimNum, int pageNo,int pageNum,String keyStr,int typeId) {
		/**
		 * select * from seller where dim > 29 -1 and dim < 29 +1 and log > 109 -1 and log < 109 +1 and cityId = (select id from city where cityName='重庆') and titile like '%%' and typeId = 1   
		 */
		StringBuffer sbf = null;
		if(logNum != null && dimNum != null){
//			sbf = new StringBuffer("select * from seller where dim > "+dimNum+" -1 and dim < "+dimNum+" +1 and log > "+logNum+" -1 and log < "+logNum+" +1 and cityId = ");
			sbf = new StringBuffer("select * from seller where cityId = ");
			if(cityId!=null){
				if(keyStr != null){
					sbf.append(cityId).append(" and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append(cityId);
				}
			}else{
				if(keyStr != null){
					sbf.append("(select id from city where cityName='重庆') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='重庆')");
				}
			}
		}else{
			sbf = new StringBuffer("select * from seller where cityId = ");
			if(cityId!=null){
				if(keyStr != null){
					sbf.append(cityId).append(" and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append(cityId);
				}
			}else{
				if(keyStr != null){
					sbf.append("(select id from city where cityName='重庆') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='重庆')");
				}
			}
		}	
		
		if(typeId > 0){
			sbf.append(" and typeId = ").append(typeId);
		}else{
			sbf.append("");
		}
		
		System.out.println("sbf.toString() 1 "+sbf.toString());
		
		if(logNum != null && dimNum != null){
			if(typeId == -1)
			{
				sbf.append(" order by ACOS(SIN(("+dimNum+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+dimNum+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+logNum+" * 3.1415) / 180 - (log * 3.1415) / 180 ) ) * 6380 asc,addtime DESC");
			}
			else
			{
				sbf.append(" order by sort DESC, ACOS(SIN(("+dimNum+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+dimNum+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+logNum+" * 3.1415) / 180 - (log * 3.1415) / 180 ) ) * 6380 asc");
			}
		}else{
			sbf.append(" order by addtime DESC");
		}
		
		sbf.append(" limit ").append((pageNo - 1) * pageNum).append(",").append(pageNum);
		
		System.out.println("sbf.toString() 2 "+sbf.toString());
		
		//Hibernate 查询
//		List<Seller> list = dao.query(sbf.toString(), pageNo, pageNum);
		
		//通过 原生态sql查询
		List<Object[]> list = dao.querySQL(sbf.toString());
		
		System.out.println("list.size() "+list.size());
				
		List list_seller = new ArrayList();
		
		for(int i=0;i<list.size();i++){
			Object[] object = list.get(i);
			
			Seller s = new Seller();
			int sellerId = Integer.parseInt(object[0].toString());
			s.setId(sellerId);
			s.setTitile(object[1]+"");
			s.setLogo(object[2]+"");
			s.setPhone(object[3]+"");
			
			String hql_pro = "select p.name from Seller as s,Product as p where s.id = p.seller and s.id = "+sellerId;
			String pro_name = (String)dao.loadObject(hql_pro);
			
//			s.setName(pro_name);
			
			String productId_hql = "select p.id from Seller as s,Product as p where s.id = p.seller and s.id = "+sellerId;
			
			Object productId = dao.loadObject(productId_hql);
			
			List<String> list_proImgs = new ArrayList<String>();
			String str_proImgs = "";
			if(productId != null){
				String hql_proImgs = "select i.imgUrl from Images as i where i.type = 2 and i.compositeId = "+Integer.parseInt(productId+"");
				
				list_proImgs = dao.query(hql_proImgs);
				
				str_proImgs = list_proImgs.toString().replace("[]", "").replace("[", "").replace("]","");
				
			}else{
				list_proImgs.add("");
			}
			
			str_proImgs = BaseUtils.del_space(str_proImgs);
			
//			System.out.println("----------------->str_proImgs ------------------------>"+str_proImgs+"<-------------------");
			
			s.setProductImgs(str_proImgs);
			s.setDim(object[6]+"");
			s.setLog(object[5]+"");
			s.setBrief(object[8]+"");
			String hql_sellerImgs = "select i.imgUrl from Images as i where i.type = 1 and compositeId = "+sellerId;;
			List<String> list_sellerImgs = dao.query(hql_sellerImgs);			
			String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
			str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
			s.setSellerImgs(str_sellerimgs);
			s.setPreferential(object[9]+"");
//			s.setAddtime(object[10]+"");
			s.setAddress(object[11]+"");
			if(object[18] == null || object[18].equals(""))
			{
				s.setSort(0);
			}
			else
			{
				s.setSort((Integer) object[18]);
			}
			double lev_num = object[12] != null ? Double.parseDouble(object[12]+""):4.0;
			s.setLevel(lev_num);
			
			list_seller.add(s);
		}
			
		
//		for(Object[] object : list){
//			int id = Integer.parseInt(object[0].toString());
//			System.out.println("---->"+object[0].toString().trim()+"<------");
			
//			Seller s = new Seller();
			//查询产品服务图片
//			String hql1 = "select i.imgUrl from Seller as s,Product as p,Images as i where s.id = p.seller and p.id = i.compositeId and type = 2 and p.id = 1 and s.id = "+id;
//			List list_proImgs = dao.query(hql1);
			//查询商家图片
//			String hql2 ="select i.imgUrl from Seller as s,Images as i where s.id = i.compositeId and i.type = 1 and s.id = "+id;
//			List list_sellerImgs = dao.query(hql2);
			
//			s.setId(Integer.parseInt(object[0]+""));//id
			
//			System.out.println("---->"+s.getId()+"<------");
			
//			s.setLogo(object[2]+"");//商家logo
//			s.setTitile(object[1]+"");//商家名称
//			s.setLevel(Double.parseDouble(object[10]+""));//商家星级
//			s.setPreferential(object[7]+"");//商家优惠
//			s.setPhone(object[3]+"");//商家电话
//			s.setAddress(object[9]+"");//商家地址			
//			s.setName(object[11]+"");//产品服务
////		s.setProductImgs(list_proImgs.toString());//产品服务图片集合
//			s.setBrief(object[6]+"");//商家介绍
//			s.setSellerImgs(list_sellerImgs.toString());//商家图片集合
			
		return list_seller;
	}
	
	/**
	 * TODO 升级版客户端查询所有商家信息
	 * updateTime 2013.07.01 
	 * 1、根据传递进来的经度和纬度，计算附近商家距离，然后返回；
	 * 2、根据城市下面的对应的具体区域查询显示返回结果
	 * 
	 */
	/*
	 * 附近接口对应内容
	 * 1、最重要的是商家id
	 * 2、logo图
	 * 3、商家名称
	 * 4、距离（经度、纬度,依据经度、纬度计算出距离）
	 * 5、地址
	 * 6、优惠
	 * 7、电话
	 */
	
	/*
	 * 详情接口对应内容（查询全部）
	 */
	@Override
	public List getSellerInfoList(String log,String dim,String pageNo,String pageNum,String typeId,String shop) {
			StringBuffer sbf = null;
			String hql = "select * from Seller where 1=1";
			if(typeId == null || typeId.equals(""))
			{
				
			}
			else
			{
				hql+=" and typeId = "+typeId;
			}
			if(shop == null || shop.equals(""))
			{
				
			}
			else
			{
				hql+=" and productId = "+shop;
			}
			if(log != null && dim != null){
				hql+=" order by ACOS(SIN(("+dim+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+dim+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+log+" * 3.1415) / 180 - (log * 3.1415) / 180 ) ) * 6380 asc";
			}else{
				hql+="  order by addtime DESC";
			}
			hql+=" limit "+Integer.valueOf(pageNo)+","+Integer.valueOf(pageNum);
		List<Object[]> list = dao.querySQL(hql);
		List list_seller = new ArrayList();
		for(int i=0;i<list.size();i++){
			Object[] object = list.get(i);
			
			Seller s = new Seller();
			int sellerId = Integer.parseInt(object[0].toString());
			s.setId(sellerId);
			s.setTitile(object[1]+"");
			s.setLogo(object[2]+"");
			s.setPhone(object[3]+"");
			
			String hql_pro = "select p.name from Seller as s,Product as p where s.id = p.seller and s.id = "+sellerId;
			String pro_name = (String)dao.loadObject(hql_pro);
			
//			s.setName(pro_name);
			
			String productId_hql = "select p.id from Seller as s,Product as p where s.id = p.seller and s.id = "+sellerId;
			
			Object productId = dao.loadObject(productId_hql);
			
			List<String> list_proImgs = new ArrayList<String>();
			String str_proImgs = "";
			if(productId != null){
				String hql_proImgs = "select i.imgUrl from Images as i where i.type = 2 and i.compositeId = "+Integer.parseInt(productId+"");
				
				list_proImgs = dao.query(hql_proImgs);
				
				str_proImgs = list_proImgs.toString().replace("[]", "").replace("[", "").replace("]","");
				
			}else{
				list_proImgs.add("");
			}
			
			str_proImgs = BaseUtils.del_space(str_proImgs);
			
//			System.out.println("----------------->str_proImgs ------------------------>"+str_proImgs+"<-------------------");
			if(object[19] == null || object[19].equals(""))
			{
				s.setSort(0);
			}
			else
			{
				s.setSort((Integer) object[19]);
			}
			s.setProductImgs(str_proImgs);
			s.setDim(object[6]+"");
			s.setLog(object[5]+"");
			s.setBrief(object[8]+"");
			s.setRecruitment(object[17]+"");
			s.setPrice((Double)object[18]);
			s.setOtherPrice((Double)object[22]);
			String hql_sellerImgs = "select i.imgUrl from Images as i where i.type = 1 and compositeId = "+sellerId;;
			List<String> list_sellerImgs = dao.query(hql_sellerImgs);			
			String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
			str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
			s.setSellerImgs(str_sellerimgs);
			s.setPreferential(object[9]+"");
//			s.setAddtime(object[10]+"");
			s.setAddress(object[11]+"");
			double lev_num = object[12] != null ? Double.parseDouble(object[12]+""):4.0;
			s.setLevel(lev_num);
			
			list_seller.add(s);
		}	
		return list_seller;
	}
	
	/**
	 * 客户端 查询单个商家信息(查询显示当前商家全部的信息)
	 * @param cityName
	 * @param log
	 * @param dim
	 * @param keyStr
	 * @param typeId
	 * @return
	 */
	@Override
	public Seller getSellerOneInfo(int seller_id) {
		hql = "from Seller where id = "+seller_id;
		
		Seller sl = (Seller)dao.loadObject(hql);
	
		Seller s = new Seller();
		if(sl!=null){
			int sellerId = sl.getId();
			System.out.println(sellerId);		
			
			s.setId(sellerId);
			s.setTitile(sl.getTitile());
			s.setLogo(sl.getLogo());
			s.setPhone(sl.getPhone());
			
			String hql_pro = "select p.name from Seller as s,Product as p where s.id = p.seller and s.id = "+sellerId;
			String pro_name = (String)dao.loadObject(hql_pro);
			
//			s.setName(pro_name);
			
			String productId_hql = "select p.id from Seller as s,Product as p where s.id = p.seller and s.id = "+sellerId;
			
			Object productId = dao.loadObject(productId_hql);
			
			List<String> list_proImgs = new ArrayList<String>();
			String str_proImgs = "";
			if(productId != null){
				String hql_proImgs = "select i.imgUrl from Images as i where i.type = 2 and i.compositeId = "+Integer.parseInt(productId+"");
				
				list_proImgs = dao.query(hql_proImgs);
				
				str_proImgs = list_proImgs.toString().replace("[]", "").replace("[", "").replace("]","");
				//s.setProductId(Integer.parseInt(productId+""));
			}else{
				list_proImgs.add("");
				//s.setProductId(-1);
			}
			
			str_proImgs = BaseUtils.del_space(str_proImgs);
			
			s.setProductImgs(str_proImgs);
			
			// 距离
//			double log_num = Double.parseDouble(sl.getLog());
//			double dim_num = Double.parseDouble(sl.getDim());		
//			System.out.println("log_num "+log_num);
//			System.out.println("dim_num "+dim_num);
//					double disNum = BaseUtils.getDistance(log_num, dim_num, Double.parseDouble(logNum), Double.parseDouble(dimNum));		
//			System.out.println("distance :"+BaseUtils.getDistanceStr(disNum));		
//			s.setDistance(BaseUtils.getDistanceStr(disNum));
			
			s.setBrief(sl.getBrief());
			String hql_sellerImgs = "select i.imgUrl from Images as i where i.type = 1 and compositeId = "+sellerId;;
			List<String> list_sellerImgs = dao.query(hql_sellerImgs);			
			String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
			str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
			s.setSellerImgs(str_sellerimgs);
			s.setPreferential(sl.getPreferential());
			
//			s.setAddtime(BaseUtils.getNowStringDateTime(sl.getAddtime()));
			
			s.setAddress(sl.getAddress());
			
			String level_avg_sql = "select avg(level) as levelAvg from Comment where sellerId = "+sellerId;
			Double level_avg_dou = (Double)dao.loadObject(level_avg_sql);
			if(level_avg_dou!=null){
				level_avg_dou = Math.ceil(level_avg_dou);
			}else{
				level_avg_dou = 4.0;
			}		
			System.out.println("level_avg_dou :"+level_avg_dou);
			s.setLevel(level_avg_dou);
		}else{
			s = null;
		}
		return s;
	}
	
	/**
	 * TODO 客户端统计相关商家信息
	 */
	@Override
	public Integer countSeller(String cityId, String logNum, String dimNum,String keyStr,int typeId) {
		StringBuffer sbf = null;
		if(logNum != null && dimNum != null){
			sbf = new StringBuffer("select count(*) from seller where dim > "+dimNum+" -1 and dim < "+dimNum+" +1 and log > "+logNum+" -1 and log < "+logNum+" +1 and cityId = ");
			if(cityId!=null){
				if(keyStr != null){
					sbf.append(cityId).append(" and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append(cityId);
				}
			}else{
				if(keyStr != null){
					sbf.append("(select id from city where cityName='重庆') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='重庆')");
				}
			}
		}else{
			sbf = new StringBuffer("select count(*) from seller where cityId = ");
			if(cityId!=null){
				if(keyStr != null){
					sbf.append(cityId).append(" and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append(cityId);
				}
			}else{
				if(keyStr != null){
					sbf.append("(select id from city where cityName='重庆') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='重庆')");
				}
			}
		}
		if(typeId > 0){
			sbf.append(" and typeId = ").append(typeId);
		}else{
			sbf.append("");
		}
		if(logNum != null && dimNum != null){
			sbf.append(" order by ACOS(SIN(("+dimNum+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+dimNum+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+logNum+" * 3.1415) / 180 - (log * 3.1415) / 180 ) ) * 6380 asc,sort DESC");
		}else{
			sbf.append(" order by addtime DESC");
		}
		Integer number = dao.countBySql(sbf.toString());
		return number;
	}

	
	/**
	 * 升级版客户端统计对应商家信息总条数
	 * 2013.07.04
	 * 1、增加区域id
	 * @param cityId
	 * @param log
	 * @param dim
	 * @param keyStr
	 * @param typeId
	 * @param areaId
	 * @return
	 */
	@Override
	public Integer countSellerInfo(String cityId, String logNum, String dimNum,String keyStr, int typeId, int areaId,String productId) {
		StringBuffer sbf = null;
		if(logNum != null && dimNum != null){
			sbf = new StringBuffer("select count(*) from seller where dim > "+dimNum+" -1 and dim < "+dimNum+" +1 and log > "+logNum+" -1 and log < "+logNum+" +1 and cityId = ");
			if(cityId!=null){
				if(keyStr != null){
					sbf.append(cityId).append(" and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append(cityId);
				}
			}else{
				if(keyStr != null){
					sbf.append("(select id from city where cityName='重庆') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='重庆')");
				}
			}
		}else{
			sbf = new StringBuffer("select count(*) from seller where cityId = ");
			if(cityId!=null){
				if(keyStr != null){
					sbf.append(cityId).append(" and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append(cityId);
				}
			}else{
				if(keyStr != null){
					sbf.append("(select id from city where cityName='重庆') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='重庆')");
				}
			}
		}
		if(typeId > 0){
			sbf.append(" and typeId = ").append(typeId);
		}else{
			sbf.append("");
		}
		
		//TODO 区域id -1
		if(areaId > 0){
			sbf.append(" and areaId = ").append(areaId);//查询
		}else{
			sbf.append("");//查询全部
		}
		if(productId  == null || productId.equals("")){
			sbf.append("");//查询全部
		}else{
			sbf.append(" and productId = ").append(productId);//查询
		}
		System.out.println("sbf.toString() 1 "+sbf.toString());
		
		if(logNum != null && dimNum != null){
			sbf.append(" order by ACOS(SIN(("+dimNum+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+dimNum+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+logNum+" * 3.1415) / 180 - (log * 3.1415) / 180 ) ) * 6380 asc,addtime DESC");
		}else{
			sbf.append(" order by addtime DESC");
		}
		System.out.println("sbf.toString() 2 "+sbf.toString());
		
		Integer number = dao.countBySql(sbf.toString());
		return number;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<ShopType> selShopType() {
		List<ShopType> list = dao.query("from ShopType");
		return list;
	}

	@Override
	public List<ShopTypeTwo> selShopTypeTwo(String parameter) {
		String hql ="from ShopTypeTwo where 1 = 1 ";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql+=" and shopTypeId = "+parameter;
		}
		List<ShopTypeTwo> list = dao.query(hql);
		return list;
	}

	@Override
	public List<Mall> selMall() {
		List<Mall> list = dao.query("from Mall");
		return list;
	}

	@Override
	public List<Video> selVideo(String type, String log, String dim,
			String villageId, String pageNo, String pageNum) {
		String hql="from Video where 1=1";
		if(type == null || type.equals(""))
		{
			
		}
		else
		{
			hql+=" and type = "+type;
		}
		if(villageId == null || villageId.equals(""))
		{
			
		}
		else
		{
			hql+=" and villageId = "+villageId;
		}
		hql+=" order by ACOS(SIN(("+dim+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+dim+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+log+" * 3.1415) / 180 - (log * 3.1415) / 180 ) ) * 6380 asc";
		List<Video> list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		List<Video> list_seller = new ArrayList<Video>();
		for (int i = 0; i < list.size(); i++) 
		{
//			Video deo = new Video();
//			deo.setId(list.get(i).getId());
//			deo.setAddress(list.get(i).getAddress());
//			deo.setBife(list.get(i).getBife());
//			deo.setFacilities(list.get(i).getFacilities());
//			deo.setAddtime(list.get(i).getAddtime());
//			deo.setInfo(list.get(i).getInfo());
//			deo.setIphone(list.get(i).getIphone());
//			deo.setLayout(list.get(i).getLayout());
//			deo.setLogo(list.get(i).getLogo());
//			deo.setPrice(list.get(i).getPrice());
//			deo.setQuarters(list.get(i).getQuarters());
//			deo.setTitle(list.get(i).getTitle());
//			deo.setTraffic(list.get(i).getTraffic());
//			deo.setType(list.get(i).getType());
//			deo.setVillageId(list.get(i).getVillageId());
//			String hql_sellerImgs = "select i.imgUrl from Images as i where i.type = 3 and compositeId = "+list.get(i).getId();;
//			List<String> list_sellerImgs = dao.query(hql_sellerImgs);			
//			String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
//			str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
//			deo.setSellerImgs(str_sellerimgs);
//			list_seller.add(deo);
		}
		return list_seller;
	}

	@Override
	public boolean addVideo(Video video) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(video);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Share> selShare(String dim, String log,
			String villageId, String pageNo, String pageNum)
			{
		List<Share> listShare = new ArrayList<Share>();
		try {
			List<Share> list = dao.query("from Share where villageId = "+villageId,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
			for (int i = 0; i < list.size(); i++) 
			{
				Share share = new Share();
				share = list.get(i);
				share.setAddtime(share.getAddtime());
				share.setBife(share.getBife());
				share.setPraise(share.getPraise());
				share.setUserId(share.getUserId());
				User user = (User) dao.loadById(User.class, share.getUserId());
				if(user==null || user.equals(""))
				{
					
				}
				else
				{
					share.setUserName(user.getName());
					share.setUserImage(user.getImgUrl());
				}
				share.setVillageId(share.getVillageId());
				share.setImages(share.getImages());
				int number = dao.countBySql("select count(*) from comment where sellerId = "+share.getId()+" and type=2");
				share.setNumber(number);
				listShare.add(share);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listShare;
	}

	@Override
	public List<Video> selVideoBackstage(String parameter, String page,
			String rows) {
		String hql ="from Video where 1 = 1 ";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql+=" and title like '%"+parameter+"%'";
		}
		List<Video> list = dao.query(hql);
		return list;
	}

	@Override
	public int numberVideoBackstage(String parameter) {
		String hql ="select count(*) from video where 1 = 1 ";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql+=" and title like '%"+parameter+"%'";
		}
		int num = dao.countBySql(hql);
		return num;
	}

	@Override
	public boolean delVideoBackstage(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Video.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Mall> selMallBackstage(String parameter, Integer valueOf,
			Integer valueOf2) {
		String hql ="from Mall where 1 = 1 ";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql+=" and title like '%"+parameter+"%'";
		}
		List<Mall> list = dao.query(hql,valueOf,valueOf2);
		return list;
	}

	@Override
	public int numberMallBackstage(String parameter) {
		String hql ="select count(*) from Mall where 1 = 1 ";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql+=" and title like '%"+parameter+"%'";
		}
		int num = dao.countBySql(hql);
		return num;
	}

	@Override
	public boolean delMallBackstage(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Mall.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean delShareBackstage(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Share.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Object> selShareBackstage(String parameter, Integer valueOf,
			Integer valueOf2) {
		
		
		String hql ="select s.addtime,s.praise,s.bife,u.nickName,s.id from Share s,User u  where s.userId = u.id ";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql+=" and s.bife like '%"+parameter+"%'";
		}
		List<Object> list = dao.query(hql,valueOf,valueOf2);
		return list;
	}

	@Override
	public int numberShareBackstage(String parameter) {
		String hql ="select count(*)  from Share s,User u  where s.userId = u.id  ";
		if(parameter == null || parameter.equals(""))
		{
			
		}
		else
		{
			hql+=" and s.bife like '%"+parameter+"%'";
		}
		int num = dao.countBySql(hql);
		return num;
	}

	@Override
	public boolean addSeller(Seller s) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(s);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Market> selMarketAll() {
		List<Market> list = dao.query("from Market");
		return list;
	}

	@Override
	public List<ShopType> selShopIdY() {
		List<ShopType> list = dao.query("from ShopType");
		return list;
	}

	@Override
	public List<ShopTypeTwo> selShopIdE(String id) {
		List<ShopTypeTwo> list = dao.query("from ShopTypeTwo where shopTypeId = "+id);
		return list;
	}

	@Override
	public boolean addMall(Mall ma) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(ma);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Object> selMarketList() {
		// TODO Auto-generated method stub
		return dao.query("from Market m where m.id >= 13 ");
	}

	@Override
	public int numberMarketList() {
		// TODO Auto-generated method stub
		String sql ="select count(*)  from Market m";
		int num = dao.countBySql(sql);
		return num;
	}

	@Override
	public Object selSellerInfoById(Integer id) {
		// TODO Auto-generated method stub
		Sellermanager sm = (Sellermanager) dao.loadById(Sellermanager.class, id);
		return dao.loadById(Seller.class, sm.getSellerId());
	}

	@Override
	public Object selSellerLogo(Integer id) {
		// TODO Auto-generated method stub
		Sellermanager sm = (Sellermanager) dao.loadById(Sellermanager.class, id);
		Seller s = (Seller) dao.loadById(Seller.class, sm.getSellerId());
		return s.getLogo();
	}

	@Override
	public void addOrUpdateSeller(LoginUser lu, String title, String phone,
			String typeId, String address, String brief,String name,String recruitment,String log,String dim,String logo) {
		// TODO Auto-generated method stub
		Seller s = (Seller) selSellerInfoById(lu.getId());
		s.setTitile(title);
		s.setPhone(phone);
		s.setName(name);
		s.setRecruitment(recruitment);
		s.setLog(log);
		s.setDim(dim);
		if(null != typeId){
			s.setTypeId(Integer.parseInt(typeId));
		}
		s.setAddress(address);
		s.setBrief(brief);
		if( null != logo){
			s.setLogo(logo);
		}
		dao.saveOrUpdate(s);
	}

	@Override
	public List<Seller> selCommodity(Integer type,LoginUser lu, Integer page,
			Integer limit) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.shop = "+type;
		if(lu.getRole() == 2){
			hql += " and s.villageId in (select v.id from Village v where v.cityId ="+lu.getCityId()+")";
		}
		if(lu.getRole() == 3){
			hql += " and s.userId = "+lu.getId();
		}
		return dao.query(hql,page,limit);
	}

	@Override
	public int numberCommodity(Integer type,LoginUser lu) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.shop = "+type;
		if(lu.getRole() == 2){
			hql += " and s.villageId in (select v.id from Village v where v.cityId ="+lu.getCityId()+")";
		}
		if(lu.getRole() == 3){
			hql += " and s.villageId = "+lu.getVillageId();
		}
		return dao.query(hql).size();
	}

	@Override
	public List<Object> selVillage() {
		// TODO Auto-generated method stub
		return dao.query("from Village ");
	}

	@Override
	public List<Object> selShopId1() {
		// TODO Auto-generated method stub
		return dao.query("from ShopType ");
	}

	@Override
	public List<Object> selShopId2(String pid) {
		// TODO Auto-generated method stub
		String hql =  "from ShopTypeTwo s where 1=1 ";
		if(null != pid){
			hql+=" and s.shopTypeId="+pid;
		}
		return dao.query(hql);
	}

	@Override
	public void saveOrUpdateCommodity(String id, String title, String phone,
			String otherPrice, String price, String villageId, String shopId,
			String shopIdTwo, String brief,String level,String logo) {
		// TODO Auto-generated method stub
		Seller s;
		if(null == id){
			s = new Seller();
			s.setTitile(title);
			s.setPhone(phone);
			if( null != otherPrice){
				s.setOtherPrice(Double.parseDouble(otherPrice));
			}
			if(null != price){
				s.setPrice(Double.parseDouble(price));
			}
			s.setLevel(Double.parseDouble(level));
			s.setVillageId(Integer.parseInt(villageId));
			s.setShop(1);
			s.setSort(0);
			s.setShopId(Integer.parseInt(shopId));
			s.setShopIdTwo(Integer.parseInt(shopIdTwo));
			s.setBrief(brief);
			s.setLogo(logo);
			s.setAddtime(DateUtil.getNowDate("yyyy-MM-dd"));
		}else{
			s = (Seller) dao.loadById(Seller.class, id);
			s.setTitile(title);
			s.setPhone(phone);
			if( null != otherPrice){
				s.setOtherPrice(Double.parseDouble(otherPrice));
			}
			if(null != price){
				s.setPrice(Double.parseDouble(price));
			}
			s.setLevel(Double.parseDouble(level));
			s.setVillageId(Integer.parseInt(villageId));
			s.setShop(1);
			s.setSort(0);
			s.setShopId(Integer.parseInt(shopId));
			s.setShopIdTwo(Integer.parseInt(shopIdTwo));
			s.setBrief(brief);
			if( null != logo){
				s.setLogo(logo);
			}
		}
		dao.saveOrUpdate(s);
	}

	@Override
	public Seller getSeller(String id) {
		// TODO Auto-generated method stub
		return (Seller) dao.loadById(Seller.class, Integer.parseInt(id));
	}

	@Override
	public void saveOrUpdateSellerManager(Sellermanager ss) {
		// TODO Auto-generated method stub
		dao.save(ss);
	}

	@Override
	public Village selVillageById(Integer villageId) {
		// TODO Auto-generated method stub
		return (Village) dao.loadById(Village.class, villageId);
	}

	@Override
	public void saveOrUpdateObject(User u) {
		// TODO Auto-generated method stub
		dao.saveOrUpdate(u);
	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		Sellermanager sm = (Sellermanager) dao.query("from Sellermanager where sellerId = "+Integer.parseInt(id)).get(0);
		return (User) dao.query("from User where sellerId="+sm.getId()).get(0);
	}

	@Override
	public void saveOrUpdateImg(File file, String projectUrl, String filepath, String fileFileName, String fileContentType, String type, String compositeId) throws IOException {
		// TODO Auto-generated method stub
		 if (file != null) { 
	        	Images img = new Images();
				//图片上传
				String now = com.era.util.DateUtil.getNowString();
				File f = new File(filepath+"/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
				if(!f.exists()){
					f.createNewFile();
				}
				
				FileUtils.copyFile(file, f);
				
				String imgUrl = projectUrl + "/uploadImgs/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1);
				
				img.setImgUrl(imgUrl);
				img.setType(Integer.parseInt(type));
				img.setCompositeId(Integer.parseInt(compositeId));
				dao.saveOrUpdate(img);
	        }
	}

	@Override
	public List<Images> selImages(String type, String id) {
		// TODO Auto-generated method stub
		String hql = "from Images i where i.type="+Integer.parseInt(type)+" and i.compositeId = "+Integer.parseInt(id);
		return dao.query(hql);
	}

	@Override
	public int numberImages(String type, String id) {
		// TODO Auto-generated method stub
		String hql = "from Images i where i.type="+Integer.parseInt(type)+" and i.compositeId = "+Integer.parseInt(id);
		return dao.query(hql).size();
	}

	@Override
	public void delImg(int id) {
		// TODO Auto-generated method stub
		dao.delById(Images.class, id);
	}
}
