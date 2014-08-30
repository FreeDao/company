package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Activities;
import com.era.orm.Market;
import com.era.orm.Seller;
import com.era.service.SellerService;

public class SellerServiceImpl implements SellerService
{
	private BaseDAO dao;

	@Override
	public int numberSeller(String name,int sellerid) 
	{
		System.out.println(sellerid);
		String hql="";
		if(name == null || name.equals(""))
		{
			hql ="select count(*) from seller where typeId = "+sellerid;
		}
		else
		{
			hql="select count(*) from seller where typeId = "+sellerid+" and titile like '%"+name+"%'";
		}
		int count = dao.countBySql(hql);
		return count;
	}
 
	@Override
	public List<Seller> selSeller(String name,int sellerid, int pageNo, int pageSize) 
	{
		String sql = "";
		if(name == null || name.equals(""))
		{
			sql="from Seller where typeId = "+sellerid;
		}
		else
		{
			sql="from Seller where typeId = "+sellerid+" and titile like '%"+name+"%'";
		}
		List<Seller> list = dao.query(sql,pageNo, pageSize);
		return list;
	}
	
	public boolean updateSeller(int id, String logo, String titile, String phone,
			String brief, String preferential, String address,String lot,String dim,String prodetId) 
	{
	String hql = "update Seller set titile = '"+titile+"'," +
				 "phone = '"+phone+"',logo = '"+logo+"',log='"+lot+"',dim='"+dim+"',brief = '"+brief+"'," +
				 "preferential = '"+preferential+"',address = '"+address+"',productId = "+prodetId +
				 "where id = '"+id+"'";
	boolean flag = false;
	try{
		dao.update(hql);
		flag = true;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
	}



	public Seller selOneSeller(int id) {
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
	public List<Activities> setListAct() {
		String hql = "";
		hql = "from Activities where lucky = 0";
		List<Activities> list = dao.query(hql);
		return list;
	}

	@Override
	public int selCityId(Integer sellerId)
	{
		int listCity = (Integer) dao.loadObject("select city.id from Market mar,City city where mar.id = "+sellerId+" and mar.cityId = city.id");
		
		return listCity;
	}

	@Override
	public List<Object> seArea(int city) {
		return null;
	}

	@Override
	public Market selMarketId(Integer attribute) {
		Market user = new Market();
		user = (Market) dao.loadById(Market.class,attribute);
		return user;
	}

	@Override
	public boolean addSeller(Seller seller) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(seller);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 删除商户
	 */
	@Override
	public boolean delSeller(String sellerId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			dao.delById(Seller.class, Integer.parseInt(sellerId));
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
}
