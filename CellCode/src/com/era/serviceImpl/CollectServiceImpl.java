package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Collect;
import com.era.orm.Seller;
import com.era.service.CollectService;
import com.era.util.BaseUtils;

public class CollectServiceImpl implements CollectService {
	private String hql = "";
	private BaseDAO dao;

	@Override
	public List<Object> selCollect(int pageNo, int pageSize) {
		List<Object> list = dao.query("select coll.id,coll.addtime,user.nickName,sell.titile from Collect coll,Seller sell,User user where coll.sellerId = sell.id and coll.userId = user.id order by coll.addtime desc",
						pageNo, pageSize);
		return list;
	}

	@Override
	public int numberCollect() {
		int number = dao
				.countQuery("select count(*) from Collect coll,Seller sell,User user where coll.sellerId = sell.id and coll.userId = user.id");
		return number;
	}

	/**
	 * TODO 根据收藏表ID进行删除
	 */
	@Override
	public boolean delCollect(int collectId) {
		boolean flag = false;
		try {
			dao.delById(Collect.class, collectId);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}		
		return flag;
	}
	
	/**
	 * TODO 根据商家ID和用户ID进行删除
	 */
	@Override
	public boolean delCollect(int userId, int sellerId) {
		boolean flag = false;
		hql = "from Collect where userId="+userId+" and sellerId = "+sellerId;
		Collect collect = (Collect)dao.loadObject(hql);
		if(collect != null){
			flag = delCollect(collect.getId());
		}else{
			flag = false;
		}
		System.out.println("collectId "+collect.getId());
		return flag;
	}

	
	/**
	 * 根据用户Id统计收藏
	 */
	@Override
	public int countCollect(int userId) {
		hql = "select count(*) from Collect where userId=" + userId;
		int number = dao.countQuery(hql);
		return number;
	}

	/**
	 * TODO 判断是否重复收藏
	 */
	@Override
	public Collect repeatCollect(int userId, int sellerId) {
		hql = "from Collect where userId=" + userId + " and sellerId="+ sellerId;
		Collect collect = (Collect)dao.loadObject(hql);
		return collect;
	}

	/**
	 * TODO 用户添加收藏
	 */
	@Override
	public Collect putCollect(Collect collect) {
		boolean flag = false;
		Collect cl = null;
		try {
			dao.save(collect);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		if (flag) {
			hql = "from Collect where userId=" + collect.getUserId()
					+ " and sellerId=" + collect.getSellerId()
					+ " order by addtime desc";
			System.out.println("putCollect----->"+hql+"<--------------");
			cl = (Collect)dao.loadObject(hql);
		}
		return cl;
	}

	/**
	 * 根据用户Id查询收藏
	 */
	@Override
	public List getCollect(String userId, String pageNo, String pageNum,String type) {
		hql = "from Collect as c where c.userId=" + userId + " and c.type = "+type+" order by addtime desc";
		
		List<Collect> list = dao.query(hql,Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		List list_seller = new ArrayList();
		
		for(Collect collect : list){
			
			System.out.println("seller_Id "+collect.getSellerId());
			
			hql = "from Seller where id = "+collect.getSellerId();
			
			Seller sl = (Seller)dao.loadObject(hql);		
			
			System.out.println("hql for Seller "+hql);
			
			Seller s = new Seller();
			int sellerId = sl.getId();
			s.setId(sellerId);
			s.setTitile(sl.getTitile());
			s.setLogo(sl.getLogo());
			s.setPhone(sl.getPhone());
			String hql_pro = "select name from Product where seller ="+sellerId;
			String pro_name = (String)dao.loadObject(hql_pro);
			s.setName(pro_name);
			String hql_proImgs = "select i.imgUrl from Images as i where i.type = 2 and compositeId = "+sellerId;
			List<String> list_proImgs = dao.query(hql_proImgs);
			
			String str_proImgs = list_proImgs.toString().replace("[]", "").replace("[", "").replace("]","");
			str_proImgs = BaseUtils.del_space(str_proImgs);
			
			s.setProductImgs(str_proImgs);
			
			s.setDim(sl.getDim());
			s.setLog(sl.getLog());
			s.setBrief(sl.getBrief());
			String hql_sellerImgs = "select i.imgUrl from Images as i where i.type = 1 and compositeId = "+sellerId;;
			List<String> list_sellerImgs = dao.query(hql_sellerImgs);
			
			String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
			str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
			
			s.setSellerImgs(str_sellerimgs);
			s.setPreferential(sl.getPreferential());
			s.setAddtime(sl.getAddtime());
			s.setAddress(sl.getAddress());
			s.setLevel(sl.getLevel());
			s.setCollectId(collect.getId()+"");			
			
			list_seller.add(s);
		}
		return list_seller;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
}
