package com.era.service;

import java.util.List;

import com.era.orm.Client;

public interface ClientService {
	/**
	 * 查询客户端
	 * @return
	 */
	public List<Client> getClient(Integer marketId,Integer cityId);
	/**
	 * 查询所有的客户端
	 * @return
	 */
	public List<Object> selClient(String name,int pageNo, int pageSize); 
	
	/**
	 * 通过id查询客户端
	 * @param id
	 * @return
	 */
	public Client selClient(int id);
	/**
	 * 计算所有客户端的
	 * @return
	 */
	public Integer numberClient(String name);
	
	/**
	 * 添加客户端
	 * @param client
	 * @return
	 */
	public boolean addClient(Client client);
	
	/**
	 * 通过id删除客户端
	 * @param id
	 * @return
	 */
	public boolean delClient(int id);
	
}
