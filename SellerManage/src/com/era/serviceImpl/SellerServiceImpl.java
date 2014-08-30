package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Seller;
import com.era.service.SellerService;

public class SellerServiceImpl implements SellerService
{
	private BaseDAO dao;

	@Override
	public int numberSeller(String name,String sellerid) 
	{
		String hql="";
		if(name == null || name.equals(""))
		{
			hql = "select count(*) from seller where id in(" + sellerid
				+ ")";
		}
		else
		{
			hql="select count(*) from seller where id in(" + sellerid
				+ ") and titile like '%"+name+"%'";
		}
		int count = dao.countBySql(hql);
		return count;
	}
 
	@Override
	public List<Seller> selSeller(String name,String sellerid, int pageNo, int pageSize) 
	{
		String sql = "";
		if(name == null || name.equals(""))
		{
			sql="from Seller where id in(" + sellerid
				+ ")";
		}
		else
		{
			sql="from Seller where id in(" + sellerid
				+ ") and titile like '%"+name+"%'";
		}
		List<Seller> list = dao.query(sql,pageNo, pageSize);
		return list;
	}
	
	public boolean updateSeller(int id, String titile, String phone,
			String brief, String preferential, String address,String lot,String dim) 
	{
		String hql = "update Seller set titile = '"+titile+"'," +
				 "phone = '"+phone+"',log='"+lot+"',dim='"+dim+"',brief = '"+brief+"'," +
				 "preferential = '"+preferential+"',address = '"+address+"' " +
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
}
