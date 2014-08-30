package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.SellerManager;
import com.era.service.SellerManagService;

public class SellerManagerServiceImpl implements SellerManagService
{
	private BaseDAO dao;

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

	@Override
	public boolean addSellerManager(SellerManager manager) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(manager);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Object> listSellerManager(String seller,int city, int pageNo,
			int pageNum) {
		String hql = "";
		if(city == 0)
		{
			if(seller == null || seller.equals(""))
			{
				hql="from SellerManager sel,Seller er where er.id=sel.sellerId";
			}
			else
			{
				hql="from SellerManager sel,Seller er where er.titile = '"+seller+"' and er.id=sel.sellerId";
			}
		}
		else
		{
			if(seller == null || seller.equals(""))
			{
				hql="from SellerManager sel,Seller er where er.id=sel.sellerId and er.cityId =  "+city;
			}
			else
			{
				hql="from SellerManager sel,Seller er where er.titile = '"+seller+"' and er.id=sel.sellerId and er.cityId =  "+city;
			}
		}
		List<Object> list =dao.query(hql, pageNo, pageNum);
		return list;
	}

	@Override
	public Integer numberSellerManager(String seller,int city) {
		String hql = "";
		if(city == 0)
		{
			if(seller == null || seller.equals(""))
			{
				hql="select count(*) from SellerManager sel,Seller er where er.id=sel.sellerId";
			}
			else
			{
				hql="select count(*) from SellerManager sel,Seller er where sel.userName = '"+seller+"' and er.id=sel.sellerId";
			}
		}
		else
		{
			if(seller == null || seller.equals(""))
			{
				hql="select count(*) from SellerManager sel,Seller er where er.id=sel.sellerId and er.cityId =  "+city;
			}
			else
			{
				hql="select count(*) from SellerManager sel,Seller er where sel.userName = '"+seller+"' and er.id=sel.sellerId and er.cityId =  "+city;
			}
		}
		int number = dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean delSellerManager(Integer id) {
		boolean flag = false;
		try {
			dao.delById(SellerManager.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public SellerManager selSellerManager(int id) {
		SellerManager sellermanager = new SellerManager();
		sellermanager = (SellerManager) dao.loadById(SellerManager.class, id);
		return sellermanager;
	}

	@Override
	public List<Object> findSeller() {
		// TODO Auto-generated method stub
		return dao.listAll("Seller");
	}
}
