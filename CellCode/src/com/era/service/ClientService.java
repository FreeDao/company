package com.era.service;

import java.util.List;

import com.era.orm.Client;

public interface ClientService {
	/**
	 * 查询客户端
	 * @return
	 */
	public Client getClient(Integer marketId,Integer cityId);
}
