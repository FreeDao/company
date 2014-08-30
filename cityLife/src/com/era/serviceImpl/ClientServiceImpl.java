package com.era.serviceImpl;

import com.era.dao.BaseDAO;
import com.era.orm.Client;
import com.era.service.ClientService;

public class ClientServiceImpl implements ClientService {
	private BaseDAO dao;
	private String hql = "";

	@Override
	public Client getClient(Integer marketId, Integer cityId) {
		hql = "from Client where marketId=" + marketId + " and cityId="+ cityId;
		Client client = (Client)dao.loadObject(hql);
		return client;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
}
