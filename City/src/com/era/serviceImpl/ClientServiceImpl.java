package com.era.serviceImpl;

import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Client;
import com.era.orm.Convenient;
import com.era.service.ClientService;

public class ClientServiceImpl implements ClientService
{
	private BaseDAO dao;

	@SuppressWarnings("unchecked")
	public List<Client> getClient(Integer marketId, Integer cityId) 
	{
		String hql = "";
		hql = "from Client where marketId=" + marketId + " and cityId="
				+ cityId;
		List<Client> list = dao.query(hql);
		return list;
	}

	public BaseDAO getDao() 
	{
		return dao;
	}

	public void setDao(BaseDAO dao) 
	{
		this.dao = dao;
	}

	@Override
	public List<Object> selClient(String name, int pageNo, int pageSize)
	{
		String hql = "";
		if(name == null || name.equals(""))
		{
			hql="from Client ct,City cy ,Market ma where ct.cityId = cy.id and ct.marketId=ma.id";
		}
		else
		{
			hql="from Client ct , City cy ,Market ma  where ct.cityId = cy.id and ct.marketId=ma.id and ct.name like '%"+name+"%'";
		}
		List<Object> list=dao.query(hql, pageNo, pageSize);
		return list;
	}

	public Client selClient(int id){
		Client client=new Client();
		client=(Client) dao.loadById(Client.class, id);
		return client;
	}
	
	@SuppressWarnings("null")
	@Override
	public Integer numberClient(String name) 
	{
		String hql="";
		if(name == null || name.equals(""))
		{
			hql="select count(*) from Client ct,City city ,Market ma where ct.cityId = city.id and ct.marketId=ma.id";
		}
		else
		{
			hql="select count(*) from Client ct,City cy ,Market ma where ct.cityId = cy.id and ct.marketId=ma.id and ct.name like '%"+name+"%'";
		}
		int number=dao.countQuery(hql);
		return number;
	}

	@Override
	public boolean addClient(Client client) 
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(client);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delClient(int id) 
	{
		boolean flag = false;
		try {
			dao.delById(Client.class, id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
