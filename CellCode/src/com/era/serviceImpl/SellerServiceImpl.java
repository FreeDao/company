package com.era.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.util.LimitedInputStream;
import org.apache.commons.io.FileUtils;

import net.sf.json.JSONArray;

import com.era.dao.BaseDAO;
import com.era.orm.Address;
import com.era.orm.Comment;
import com.era.orm.Images;
import com.era.orm.Mall;
import com.era.orm.Order;
import com.era.orm.Product;
import com.era.orm.Seller;
import com.era.orm.Share;
import com.era.orm.ShopType;
import com.era.orm.ShopTypeTwo;
import com.era.orm.User;
import com.era.orm.UserObject;
import com.era.orm.Video;
import com.era.orm.Village;
import com.era.service.SellerService;
import com.era.util.BaseUtils;
import com.era.util.Geocoding;
import com.jspsmart.upload.Request;
import com.sun.org.apache.bcel.internal.generic.Select;

public class SellerServiceImpl implements SellerService {
	private BaseDAO dao;
	private String hql = "";

	@Override
	public int numberSeller(String name) {

		if (name == null || name.equals("")) {
			hql = "select count(*) from Seller";
		} else {
			hql = "select count(*) from Seller where titile like '%" + name + "%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public List<Seller> selSeller(String name, int pageNo, int pageSize) {
		if (name == null || name.equals("")) {
			hql = "from Seller";
		} else {
			hql = "from Seller where titile like '%" + name + "%'";
		}
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
	public boolean delSeller(int id) {
		boolean flag = false;
		try {
			dao.delById(Seller.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public List<Order> selMyOrder(String userId, String buy) {
		String hql = "from Order o where o.userId="+Integer.parseInt(userId)+" and o.buy = "+Integer.parseInt(buy);
		List<Order> list = dao.query(hql);
		for(Order o : list){
			Address a = (Address) dao.loadById(Address.class, o.getAddressId());
			o.setAddressName(a.getAddress());
			o.setName(a.getName());
			o.setIphone(a.getIphone());
			if("1".equals(buy)){
				Seller s = (Seller) dao.loadById(Seller.class, Integer.parseInt(o.getShopId()));
				o.setTitle(s.getTitile());
				o.setLogo(s.getLogo());
			}else{
				Mall mall = (Mall) dao.loadById(Mall.class, Integer.parseInt(o.getShopId()));
				o.setTitle(mall.getTitle());
				o.setLogo(mall.getLogo());
			}
			
		}
		/*List<Order> t =new ArrayList<Order>();
		for (int i = 0; i < list.size(); i++) 
		{
			Order o = list.get(i);
			if(list.get(i).getBuy() == 1)
			{
				System.out.println(list.get(i).getShopId());
				int tt = Integer.valueOf(list.get(i).getShopId());
				List<Seller> sellers =  dao.query("from Seller where id="+tt);
				Seller seller = sellers.get(0);
				o.setLogo(seller.getLogo());
				o.setTitle(seller.getTitile());
				if( null != seller.get && !"".equals(object[27])){
					o.setNumber((Integer)object[27]);
				}else{
					o.setNumber(0);
				}
				
			}
			else if(list.get(i).getBuy() == 2)
			{
				int tt = Integer.valueOf(list.get(i).getShopId());
				List<Object> listO =  dao.searchBySql("select * from mall where id="+tt);
				Object[] object = (Object[]) listO.get(0);
				o.setLogo(object[1]+"");
				o.setTitle(object[2]+"");
				o.setIntegral(0);
			}
			
			Address ad = (Address) dao.loadById(Address.class, list.get(i).getAddressId());
			o.setAddressName(ad.getAddress());
			o.setName(ad.getName());
			o.setIphone(ad.getIphone());
			t.add(o);
		}*/
		return list;
	}
	
	/**
	 * TODO 瀹㈡埛绔煡璇㈣幏鍙栧晢瀹朵俊鎭�
	 */
	@Override
	public List getSellerInfo(String cityId, String logNum, String dimNum, int pageNo,int pageNum,String keyStr,int typeId) {
		/**
		 * select * from seller where dim > 29 -1 and dim < 29 +1 and log > 109 -1 and log < 109 +1 and cityId = (select id from city where cityName='閲嶅簡') and titile like '%%' and typeId = 1   
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
					sbf.append("(select id from city where cityName='閲嶅簡') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='閲嶅簡')");
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
					sbf.append("(select id from city where cityName='閲嶅簡') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='閲嶅簡')");
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
		
		//Hibernate 鏌ヨ
//		List<Seller> list = dao.query(sbf.toString(), pageNo, pageNum);
		
		//閫氳繃 鍘熺敓鎬乻ql鏌ヨ
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
			
			s.setName(pro_name);
			
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				s.setAddtime(sdf.parse(object[10]+""));
			} catch (Exception e) {
				// TODO: handle exception
			}
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
			//鏌ヨ浜у搧鏈嶅姟鍥剧墖
//			String hql1 = "select i.imgUrl from Seller as s,Product as p,Images as i where s.id = p.seller and p.id = i.compositeId and type = 2 and p.id = 1 and s.id = "+id;
//			List list_proImgs = dao.query(hql1);
			//鏌ヨ鍟嗗鍥剧墖
//			String hql2 ="select i.imgUrl from Seller as s,Images as i where s.id = i.compositeId and i.type = 1 and s.id = "+id;
//			List list_sellerImgs = dao.query(hql2);
			
//			s.setId(Integer.parseInt(object[0]+""));//id
			
//			System.out.println("---->"+s.getId()+"<------");
			
//			s.setLogo(object[2]+"");//鍟嗗logo
//			s.setTitile(object[1]+"");//鍟嗗鍚嶇О
//			s.setLevel(Double.parseDouble(object[10]+""));//鍟嗗鏄熺骇
//			s.setPreferential(object[7]+"");//鍟嗗浼樻儬
//			s.setPhone(object[3]+"");//鍟嗗鐢佃瘽
//			s.setAddress(object[9]+"");//鍟嗗鍦板潃			
//			s.setName(object[11]+"");//浜у搧鏈嶅姟
////		s.setProductImgs(list_proImgs.toString());//浜у搧鏈嶅姟鍥剧墖闆嗗悎
//			s.setBrief(object[6]+"");//鍟嗗浠嬬粛
//			s.setSellerImgs(list_sellerImgs.toString());//鍟嗗鍥剧墖闆嗗悎
			
		return list_seller;
	}
	
	/**
	 * TODO 鍗囩骇鐗堝鎴风鏌ヨ鎵�湁鍟嗗淇℃伅
	 * updateTime 2013.07.01 
	 * 1銆佹牴鎹紶閫掕繘鏉ョ殑缁忓害鍜岀含搴︼紝璁＄畻闄勮繎鍟嗗璺濈锛岀劧鍚庤繑鍥烇紱
	 * 2銆佹牴鎹煄甯備笅闈㈢殑瀵瑰簲鐨勫叿浣撳尯鍩熸煡璇㈡樉绀鸿繑鍥炵粨鏋�
	 * 
	 */
	/*
	 * 闄勮繎鎺ュ彛瀵瑰簲鍐呭
	 * 1銆佹渶閲嶈鐨勬槸鍟嗗id
	 * 2銆乴ogo鍥�
	 * 3銆佸晢瀹跺悕绉�
	 * 4銆佽窛绂伙紙缁忓害銆佺含搴�渚濇嵁缁忓害銆佺含搴﹁绠楀嚭璺濈锛�
	 * 5銆佸湴鍧�
	 * 6銆佷紭鎯�
	 * 7銆佺數璇�
	 */
	
	/*
	 * 璇︽儏鎺ュ彛瀵瑰簲鍐呭锛堟煡璇㈠叏閮級
	 */
	@Override
	public List getSellerInfoList(String yiji,String erji,String log,String dim,String pageNo,String pageNum,String typeId,String shop,String villageId, String sort) {
			Village v = (Village) dao.loadById(Village.class,Integer.parseInt(villageId) );

			String hql = "select s.* from seller s,village v where s.villageId = v.id and v.cityId= "+v.getCityId();
			
			if( null != sort && !"".equals(sort) && "1".equals(sort)){
				Village village = (Village) dao.loadById(Village.class, Integer.parseInt(villageId));
				hql+= " and s.villageId in (select vi.id from village vi where round(6378.138*2*asin(sqrt(pow(sin( ("+village.getDim()+"*pi()/180-vi.dim*pi()/180)/2),2)+cos("+village.getDim()+"*pi()/180)*cos(vi.dim*pi()/180)* pow(sin( ("+village.getLog()+"*pi()/180-vi.log*pi()/180)/2),2)))*1000) < 3000 )";
			}
			
			if(typeId == null || typeId.equals(""))
			{
				
			}
			else
			{
				hql+=" and s.typeId = "+typeId;
			}
			if(shop == null || shop.equals(""))
			{
				
			}
			else
			{
				hql+=" and s.shop = "+shop;
				//shop：(0便民商户1购物站物品)
				if("1".equals(shop)){
					hql+= " and s.villageId ="+villageId;
				}
			}
			
			if(yiji == null || yiji.equals("") || yiji=="-1")
			{
				
			}
			else
			{
				hql+=" and s.shopId = "+yiji;
			}
			
			if(erji == null || erji.equals("") || erji == "-2")
			{
				
			}
			else
			{
				hql+=" and s.shopIdTwo = "+erji;
			}
			
			hql+=" order by ACOS(SIN(("+dim+" * 3.1415) / 180 ) *SIN((s.dim * 3.1415) / 180 ) + COS(("+dim+" * 3.1415) / 180 ) * COS(( s.dim * 3.1415) / 180 ) *COS(("+log+" * 3.1415) / 180 - (s.log * 3.1415) / 180 ) ) * 6380 asc";
			
			/*if(log != null && dim != null){
				hql+=" order by ACOS(SIN(("+dim+" * 3.1415) / 180 ) *SIN((s.dim * 3.1415) / 180 ) + COS(("+dim+" * 3.1415) / 180 ) * COS(( s.dim * 3.1415) / 180 ) *COS(("+log+" * 3.1415) / 180 - (s.log * 3.1415) / 180 ) ) * 6380 asc";
			}else{
				hql+="  order by s.addtime DESC";
			}*/
			hql+=" limit "+(Integer.valueOf(pageNo)-1)*+Integer.valueOf(pageNum)+","+Integer.valueOf(pageNum);
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
			s.setVillageId((Integer)object[4]);
			s.setName(object[24]+"");
			/*String hql_pro = "select p.name from Seller as s,Product as p where s.id = p.seller and s.id = "+sellerId;
			String pro_name = (String)dao.loadObject(hql_pro);
			s.setName(pro_name);*/
			
			//String productId_hql = "select p.id from Seller as s,Product as p where s.id = p.seller and s.id = "+sellerId;
			
			//Object productId = dao.loadObject(productId_hql);
			
			String hql_productImgs = "select i.imgUrl from Images as i where i.type = 2 and i.compositeId = "+sellerId;;
			List<String> list_productImgs = dao.query(hql_productImgs);			
			String str_productimgs = list_productImgs.toString().replace("[]", "").replace("[", "").replace("]","");
			str_productimgs = BaseUtils.del_space(str_productimgs);
			s.setProductImgs(str_productimgs);
			
			/*List<String> list_proImgs = new ArrayList<String>();
			String str_proImgs = "";
			if(productId != null){
				String hql_proImgs = "select i.imgUrl from Images as i where i.type = 2 and i.compositeId = "+Integer.parseInt(productId+"");
				
				list_proImgs = dao.query(hql_proImgs);
				
				str_proImgs = list_proImgs.toString().replace("[]", "").replace("[", "").replace("]","");
				
			}else{
				list_proImgs.add("");
			}
			
			str_proImgs = BaseUtils.del_space(str_proImgs);*/
			
//			System.out.println("----------------->str_proImgs ------------------------>"+str_proImgs+"<-------------------");
			if(object[19] == null || object[19].equals(""))
			{
				s.setSort(0);
			}
			else
			{
				s.setSort((Integer) object[19]);
			}
			s.setProductImgs(str_productimgs);
			s.setDim(object[6]+"");
			s.setLog(object[5]+"");
			s.setBrief(object[8]+"");
			s.setRecruitment(object[14]+"");
			String temp = "";
			if(!"".equals(object[15]) && null != object[15]){
				temp = object[15] + "";
				s.setPrice(Double.parseDouble(temp));
			}else{
				s.setPrice(null);
			}
			
			if(!"".equals(object[18]) && null != object[18]){
				temp = object[18] + "";
				s.setOtherPrice(Double.parseDouble(temp));
			}else{
				s.setOtherPrice(null);
			}
			
		
			String hql_sellerImgs = "select i.imgUrl from Images as i where i.type = 1 and compositeId = "+sellerId;;
			List<String> list_sellerImgs = dao.query(hql_sellerImgs);			
			String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
			str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
			s.setSellerImgs(str_sellerimgs);
			s.setPreferential(object[9]+"");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				s.setAddtime(sdf.parse(object[10]+""));
			} catch (Exception e) {
				// TODO: handle exception
			}
			//s.setAddtime(object[10]+"");
			s.setAddress(object[11]+"");
			double lev_num = object[12] != null ? Double.parseDouble(object[12]+""):4.0;
			s.setLevel(lev_num);
			
			list_seller.add(s);
		}	
		return list_seller;
	}
	
	/**
	 * 瀹㈡埛绔�鏌ヨ鍗曚釜鍟嗗淇℃伅(鏌ヨ鏄剧ず褰撳墠鍟嗗鍏ㄩ儴鐨勪俊鎭�
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
			
			s.setName(pro_name);
			
			String productId_hql = "select p.id from Seller as s,Product as p where s.id = p.seller and s.id = "+sellerId;
			
			Object productId = dao.loadObject(productId_hql);
			
			List<String> list_proImgs = new ArrayList<String>();
			String str_proImgs = "";
			if(productId != null){
				String hql_proImgs = "select i.imgUrl from Images as i where i.type = 2 and i.compositeId = "+Integer.parseInt(productId+"");
				
				list_proImgs = dao.query(hql_proImgs);
				
				str_proImgs = list_proImgs.toString().replace("[]", "").replace("[", "").replace("]","");
				s.setVillageId(Integer.parseInt(productId+""));
			}else{
				list_proImgs.add("");
				s.setVillageId(-1);
			}
			
			str_proImgs = BaseUtils.del_space(str_proImgs);
			
			s.setProductImgs(str_proImgs);
			
			// 璺濈
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
			
			s.setAddtime(sl.getAddtime());
			
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
	 * TODO 瀹㈡埛绔粺璁＄浉鍏冲晢瀹朵俊鎭�
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
					sbf.append("(select id from city where cityName='閲嶅簡') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='閲嶅簡')");
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
					sbf.append("(select id from city where cityName='閲嶅簡') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='閲嶅簡')");
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
	 * 鍗囩骇鐗堝鎴风缁熻瀵瑰簲鍟嗗淇℃伅鎬绘潯鏁�
	 * 2013.07.04
	 * 1銆佸鍔犲尯鍩焛d
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
					sbf.append("(select id from city where cityName='閲嶅簡') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='閲嶅簡')");
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
					sbf.append("(select id from city where cityName='閲嶅簡') and titile like '%").append(keyStr).append("%'");
				}else{
					sbf.append("(select id from city where cityName='閲嶅簡')");
				}
			}
		}
		if(typeId > 0){
			sbf.append(" and typeId = ").append(typeId);
		}else{
			sbf.append("");
		}
		
		//TODO 鍖哄煙id -1
		if(areaId > 0){
			sbf.append(" and areaId = ").append(areaId);//鏌ヨ
		}else{
			sbf.append("");//鏌ヨ鍏ㄩ儴
		}
		if(productId  == null || productId.equals("")){
			sbf.append("");//鏌ヨ鍏ㄩ儴
		}else{
			sbf.append(" and productId = ").append(productId);//鏌ヨ
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
	public List<Mall> selMall(String cityId, String pageNo, String pageNum) {
		//List<Mall> list = dao.query("from Mall where cityId = "+Integer.parseInt(cityId));
		Integer no = 0,num = 0;
		
		if( null == pageNo || "".equals(pageNo)){
			no = 1;
		}else{
			no = Integer.parseInt(pageNo);
		}
		
		if( null == pageNum || "".equals(pageNum)){
			num = 15;
		}else{
			num = Integer.parseInt(pageNum);
		}
		
		return dao.query("from Mall", no, num);
	}

	@Override
	public List<Video> selVideo(String type, String log, String dim,
			String villageId, String pageNo, String pageNum) {
		Village v = (Village) dao.loadById(Village.class, Integer.parseInt(villageId));
		
		String hql="select v from Video v,Village vi where v.villageId=vi.id ";
		if(type == null || type.equals(""))
		{
			
		}
		else
		{
			hql+=" and v.type = "+type;
		}
		
		hql+=" order by ACOS(SIN(("+v.getDim()+" * 3.1415) / 180 ) *SIN((vi.dim * 3.1415) / 180 ) + COS(("+v.getDim()+" * 3.1415) / 180 ) * COS(( vi.dim * 3.1415) / 180 ) *COS(("+v.getLog()+" * 3.1415) / 180 - (vi.log * 3.1415) / 180 ) ) * 6380 asc";
		List<Video> list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		
		return list;
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
	public List<Share> selShare(String userId,String type,String dim, String log,
			String villageId, String pageNo, String pageNum)
			{
		List<Share> listShare = new ArrayList<Share>();
		try { 
			List<Share> list = dao.query("select s from Share s,User u where s.userId = u.id and s.villageId = "+villageId+" and s.typeId="+type+" order by s.id desc",Integer.valueOf(pageNo),Integer.valueOf(pageNum));
			for (int i = 0; i < list.size(); i++) 
			{
				Share share = list.get(i);
				User user = (User) dao.loadById(User.class, share.getUserId());
				if(user==null || user.equals(""))
				{
					
				}
				else
				{
					share.setUserName(user.getNickName());
					share.setUserImage(user.getImgUrl());
					share.setUserSex(user.getSex());
				}
				int number = dao.countBySql("select count(*) from comment where sellerId = "+share.getId()+" and type=4 and level = 0");
				share.setNumber(number);
				
				if( null != userId && !"".equals(userId)){
					List<?> temp = dao.query("from UserObject uo where uo.type = 1 and uo.userId="+Integer.parseInt(userId)+" and uo.oid = "+share.getId());
					
					if(temp.size() > 0){
						share.setIsPraise(1);
					}
				}
				
				
				listShare.add(share);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listShare;
	}

	@Override
	public void getSellerById(String id,Map<String, Object> map) {
		// TODO Auto-generated method stub
		String hql = "select s from User u,Sellermanager sm,Seller s where u.sellerId = sm.id and sm.sellerId = s.id and u.id ="+Integer.parseInt(id);
		Seller seller = (Seller) dao.loadObject(hql);
		
		hql = "from Village v where v.id ="+seller.getVillageId();
		Village v = (Village) dao.loadObject(hql);
		seller.setVillageName(v.getName());
		
		String hql_productImgs = "select i.imgUrl from Images as i where i.type = 2 and i.compositeId = "+seller.getId();
		List<String> list_productImgs = dao.query(hql_productImgs);			
		String str_productimgs = list_productImgs.toString().replace("[]", "").replace("[", "").replace("]","");
		str_productimgs = BaseUtils.del_space(str_productimgs);
		seller.setProductImgs(str_productimgs);
		
		String hql_sellerImgs = "select i.imgUrl from Images as i where i.type = 1 and compositeId = "+seller.getId();
		List<String> list_sellerImgs = dao.query(hql_sellerImgs);			
		String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
		str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
		seller.setSellerImgs(str_sellerimgs);
		
		map.put("seller", seller);
		map.put("responseCode", "0");
		map.put("msg", "查询成功");
	}

	@Override
	public void updateSeller(String id, String name, String tel,
			String villageId, String address, String productBrief,
			String sellerBrief, String recruit, List<File> product,
			List<String> productContentType, List<File> seller,
			List<String> sellerContentType, String projectUrl, String filepath) throws IOException {
		// TODO Auto-generated method stub
		

		hql = "select s from User u,Sellermanager sm,Seller s where u.sellerId = sm.id and sm.sellerId = s.id and u.id ="+Integer.parseInt(id);
		Seller s = (Seller) dao.loadObject(hql);
		Map<String , Object> m = new HashMap<String, Object>();
		if(Geocoding.getLatLng(address, m)){
			s.setLog(m.get("lng")+"");
			s.setDim(m.get("lat")+"");
		}
		s.setTitile(name);//标题
		s.setPhone(tel);
		s.setVillageId(Integer.parseInt(villageId));
		s.setAddress(address);
		s.setName(productBrief);//产品简介
		s.setBrief(sellerBrief);
		s.setRecruitment(recruit);
		dao.saveOrUpdate(s);
		
		

		hql = "from Images i where i.type in (1,2) and i.compositeId="+s.getId();
		List<Images> images = dao.query(hql); 
		for(Images i : images){
			dao.delById(Images.class, i.getId());
		}
		
		int i = 0;
		for(File img : product){
			String contentType = productContentType.get(i).substring(productContentType.get(i).indexOf("/")+1);
			if("pjpeg".equals(contentType)){
				contentType = "jpg";
			}
			//图片上传
			String temp = BaseUtils.Md5(img);
			File f = new File(filepath+"/"+temp+"."+contentType);
			if(!f.exists()){
				f.createNewFile();
				FileUtils.copyFile(img, f);
			}
			
			Images im = new Images();
			im.setCompositeId(s.getId());
			im.setImgUrl(projectUrl + "/uploadImgs/"+temp+"."+contentType);
			im.setType(2);
			dao.saveOrUpdate(im);
			i++;
		}
		
		i = 0;
		for(File img : seller){
			String contentType = sellerContentType.get(i).substring(sellerContentType.get(i).indexOf("/")+1);
			if("pjpeg".equals(contentType)){
				contentType = "jpg";
			}
			//图片上传
			String temp = BaseUtils.Md5(img);
			File f = new File(filepath+"/"+temp+"."+contentType);
			if(!f.exists()){
				f.createNewFile();
				FileUtils.copyFile(img, f);
			}
			
			Images im = new Images();
			im.setCompositeId(s.getId());
			im.setImgUrl(projectUrl + "/uploadImgs/"+temp+"."+contentType);
			im.setType(1);
			dao.saveOrUpdate(im);
			i++;
		}
		
	}

	@Override
	public void praise(String shareId,String userId) {
		// TODO Auto-generated method stub
		Share s = (Share) dao.loadById(Share.class, Integer.parseInt(shareId));
		s.setPraise(s.getPraise()+1);
		dao.saveOrUpdate(s);
		UserObject uo = new UserObject();
		uo.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
		uo.setOid(Integer.parseInt(shareId));
		uo.setType(1);
		uo.setUserId(Integer.parseInt(userId));
		dao.saveOrUpdate(uo);
	}
}
