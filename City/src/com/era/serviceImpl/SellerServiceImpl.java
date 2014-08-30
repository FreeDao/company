package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Admin;
import com.era.orm.Area;
import com.era.orm.CustomType;
import com.era.orm.Images;
import com.era.orm.Market;
import com.era.orm.Room;
import com.era.orm.Seller;
import com.era.service.SellerService;
import com.era.util.BaseUtils;
import com.era.util.PiaoJuTong;

public class SellerServiceImpl implements SellerService{
	private BaseDAO dao;
	
	@Override
	public int numberSeller(String name,int city,String typeId)
	{
		String hql = "select count(*) from Seller sel,Market ket where 1 = 1 and sel.typeId= ket.id ";
		
		if(name == null || name.equals(""))
		{
			
		}
		else
		{
			hql+=" and sel.titile like '%"+name+"%'";
		}
		if(city == 0)
		{
			
		}
		else
		{
			hql+=" and ket.cityId = "+city;
		}
		if(typeId == null || typeId.equals(""))
		{
			
		}
		else
		{
			hql+=" and  sel.typeId = "+typeId;
		}
		int number = dao.countQuery(hql);
		return number;
	}
	
	@Override
	public List<Object> selSeller(String name,int city,String typeId, int pageNo, int pageSize)
	{
		String hql = "from Seller sel,Market ket where 1 = 1 and sel.typeId= ket.id ";
		
		if(name == null || name.equals(""))
		{
			
		}
		else
		{
			hql+=" and sel.titile like '%"+name+"%'";
		}
		if(city == 0)
		{
			
		}
		else
		{
			hql+=" and ket.cityId = "+city;
		}
		if(typeId == null || typeId.equals(""))
		{
			
		}
		else
		{
			hql+=" and  sel.typeId = "+typeId;
		}
		
		List<Object> list = dao.query(hql,pageNo, pageSize);
		return list;
	}
	

	@Override
	public boolean delSeller(int id)
	{
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
	public boolean addSeller(Seller seller)
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(seller);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public Seller selSellerId(int id) 
	{
		Seller user = new Seller();
		user = (Seller) dao.loadById(Seller.class,id);
		return user;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public boolean moveType(Integer typeId, Integer selId,Integer cityId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			String hql = "update Seller s set s.typeId = "+typeId+",s.cityId="+cityId+" where id="+selId;
			dao.update(hql);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}

	@Override
	public Market getTypeName(Integer typeId) {
		// TODO Auto-generated method stub
		return (Market) dao.loadById(Market.class, typeId);
	}

	@Override
	public Area getAreaById(Integer areaId) {
		// TODO Auto-generated method stub
		return (Area) dao.loadById(Area.class, areaId);
	}

	@Override
	public List<Object> selRoom(int pageNo, int pageSize,String id) {
		// TODO Auto-generated method stub
	/*	String hql="from Room r,Seller s,Images i where r.sellerId = s.id and r.imageId = i.id";
		List<Object> list = dao.query(hql,pageNo, pageSize);*/
		String sql="select r.*,s.titile,i.imgUrl from typehotel r left join  imges i ON r.imageId = i.id left join  seller s ON  r.sellerId = s.id where 1=1 ";
		if(null != id && !"".equals(id)){
			sql += " and s.id = "+ Integer.parseInt(id);
		}
		return dao.searchBySql(sql, pageNo, pageSize);
	}

	@Override
	public int numberRoom(String id) {
		// TODO Auto-generated method stub
		String sql="select count(*) from typehotel r left join  imges i ON r.imageId = i.id left join  seller s ON  r.sellerId = s.id where 1=1 ";
		if(null != id && !"".equals(id)){
			sql += " and s.id = "+ Integer.parseInt(id);
		}
		int number = dao.countBySql(sql);
		return number;
	}

	@Override
	public List<Object> selHotel() {
		// TODO Auto-generated method stub
		String sql = "select s.id,s.titile from seller s where s.typeId in (select m.id from market m WHERE m.type='酒店')";
		return dao.searchBySql(sql);
	}

	@Override
	public void saveOrUpdateObj(Object o) {
		// TODO Auto-generated method stub
		dao.saveOrUpdate(o);
	}

	@Override
	public boolean delRoom(Integer id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			dao.delById(Room.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Admin selAdminId(int userId) {
		Admin user = new Admin();
		user = (Admin) dao.loadById(Admin.class,userId);
		return user;
	}

	@Override
	public void upSellerToggle(String id,String sort) {
		// TODO Auto-generated method stub
		Seller s = (Seller) dao.loadById(Seller.class, Integer.parseInt(id));
		if( "0".equals(sort) ){
			s.setSort(null);
		}else{
			s.setSort(Integer.parseInt(sort));
			s.setSortTime(BaseUtils.getNowDate("yyyy-MM-dd HH:mm:ss"));
		}
		dao.saveOrUpdate(s);
	}

	@Override
	public CustomType selCustomTypeById(Integer customTypeId) {
		// TODO Auto-generated method stub
		return (CustomType) dao.loadById(CustomType.class, customTypeId);
	}

}
