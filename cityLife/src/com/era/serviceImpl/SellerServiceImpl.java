package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.util.LimitedInputStream;

import net.sf.json.JSONArray;

import com.era.dao.BaseDAO;
import com.era.orm.Comment;
import com.era.orm.Images;
import com.era.orm.Product;
import com.era.orm.Seller;
import com.era.service.SellerService;
import com.era.util.BaseUtils;
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
			s.setAddtime(object[10]+"");
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
	public List getSellerInfoList(String cityId, String logNum, String dimNum,String pageNo, String pageNum, String keyStr, String typeId, String areaId,String product,String customTypeId)
	{
		List<Seller> listSeller = new ArrayList<Seller>();
		try {
			String hql = "SELECT s.type,s.id,s.brief,s.addtime,s.log,s.dim,s.preferential,s.level,s.phone,s.logo,s.titile,s.address,s.sort  FROM seller s where 1 = 1";
			if( null != customTypeId && !"".equals(customTypeId)){
				hql += " and s.customTypeId = "+Integer.parseInt(customTypeId);
			}
			if(typeId == null || typeId.equals(""))
			{
				
			}
			else
			{
				hql+=" and s.typeId = "+typeId;
			}
			if(areaId == null || areaId.equals(""))
			{
				
			}
			else
			{
				hql+=" and s.areaId = "+areaId;
			}
			if(product == null || product.equals(""))
			{
				
			}
			else
			{
				hql+=" and  s.productId = "+product;
			}
			
			if(cityId == null || cityId.equals(""))
			{
				
			}
			else
			{
				hql+=" and  s.cityId = "+cityId;
			}
			
			if(logNum == null || logNum.equals("") || dimNum == null || dimNum.equals(""))
			{
				hql+="  order by s.addtime DESC";
			}
			else
			{
				if(typeId == null || typeId.equals(""))
				{
					hql+=" order by ACOS(SIN(("+dimNum+" * 3.1415) / 180 ) *SIN((s.dim * 3.1415) / 180 ) + COS(("+dimNum+" * 3.1415) / 180 ) * COS(( s.dim * 3.1415) / 180 ) *COS(("+logNum+" * 3.1415) / 180 - (s.log * 3.1415) / 180 ) ) * 6380 asc";
				}
				else
				{
					hql+=" order by s.sort desc, s.sortTime desc, ACOS(SIN(("+dimNum+" * 3.1415) / 180 ) *SIN((s.dim * 3.1415) / 180 ) + COS(("+dimNum+" * 3.1415) / 180 ) * COS(( s.dim * 3.1415) / 180 ) *COS(("+logNum+" * 3.1415) / 180 - (s.log * 3.1415) / 180 ) ) * 6380 asc";
				}
				
			}
			List<Object> list = dao.searchBySql(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
			
			for(int i=0;i<list.size();i++)
			{
				Seller se = new Seller();
				Object[] object = (Object[]) list.get(i);
				se.setAddtime(object[3]+"");
				se.setBrief(object[2]+"");
				String hql_sellerImgs = "select i.imgUrl from Images as i where i.type = 1 and compositeId = "+object[1]+" order by i.sort desc";
				List<String> list_sellerImgs = dao.query(hql_sellerImgs);			
				String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
				str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
				se.setSellerImgs(str_sellerimgs);
				if(object[7] == null || object[7].equals(""))
				{
					se.setLevel(Double.parseDouble("0"));
				}
				else
				{
					se.setLevel(Double.parseDouble(object[7]+""));
				}
				se.setLogo(object[9]+"");
				se.setTitile(object[10]+"");
				se.setId(Integer.parseInt(object[1].toString()));
				se.setPhone(object[8]+"");
				se.setPreferential(object[6]+"");
				se.setDim(object[5]+"");
				
				se.setLog(object[4]+"");
				se.setAddress(object[11]+"");
				if(object[12] == null || object[12].equals(""))
				{
					se.setSort(0);
				}
				else
				{
					se.setSort(1);
				}
				if(object[1] == null || object[1].equals(""))
				{
					
				}
				else
				{
					hql = "from Product p where p.seller = "+object[1];
					List<Product> ps = dao.query(hql);
					if(ps.size() > 0){
						Product p = ps.get(0);
						hql = "from Images i where i.type = 2 and compositeId = "+p.getId()+" order by i.sort desc";
						List<Images> is = dao.query(hql);
						String temp = "";
						int j = 1;
						for(Images img : is){
							if(j == 1){
								temp += img.getImgUrl();
								j++;
							}else{
								temp += ","+img.getImgUrl();
							}
						}
						se.setName(p.getName());
						se.setProductImgs(temp);
					}else{
						se.setName("");
						se.setProductImgs("");
					}
					
					/*String hql_pro = "select  GROUP_CONCAT(imgUrl),p.name from product p,imges i where i.type = 2 and i.compositeId=p.id and p.seller = "+object[1]+"  GROUP BY p.id order by i.sort ";
					List<Object> t = dao.searchBySql(hql_pro);
					if(t == null || t.equals("") || t.size()<1)
					{
						se.setName("");
						se.setProductImgs("");
					}
					else
					{
						Object[] object1 =  (Object[]) t.get(0);
						se.setName(object1[1]+"");
						se.setProductImgs(object1[0]+"");
					}*/
				}
				listSeller.add(se);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listSeller;
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
			
			s.setLog(sl.getLog());
			s.setDim(sl.getDim());
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
				s.setProductId(Integer.parseInt(productId+""));
			}else{
				list_proImgs.add("");
				s.setProductId(-1);
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
			
			s.setAddtime(BaseUtils.getNowStringDateTime(sl.getAddtime()));
			
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
}
