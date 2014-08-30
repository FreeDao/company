package com.era.service;

import java.util.List;

import com.era.orm.Admin;
import com.era.orm.Area;
import com.era.orm.CustomType;
import com.era.orm.Images;
import com.era.orm.Market;
import com.era.orm.Room;
import com.era.orm.Seller;

public interface SellerService 
{
	/**
	 * 获取商户的所有条数
	 * @return
	 */
	public int numberSeller(String name,int city,String typeId);
	/**
	 * 获取全部的商户
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Object> selSeller(String name,int city,String typeId,int pageNo, int pageSize);
	/**
	 * 通过ID删除用户
	 * @param id
	 * @return
	 */
	public boolean delSeller(int id);
	/**
	 * 添加商铺
	 */
	public boolean addSeller(Seller seller);
	/**
	 * 通过ID查询实体类
	 * @param id
	 * @return
	 */
	public Seller selSellerId(int id);
	
	public boolean moveType(Integer typeid, Integer selid,Integer cityid);
	public Market getTypeName(Integer typeId);
	/**
	 * 通过id获取area对象
	 * @param areaId
	 * @return
	 */
	public Area getAreaById(Integer areaId);
	/**
	 * 查询房间所有对象
	 * @param pagenum
	 * @param i
	 * @param id 
	 * @return
	 */
	public List<Object> selRoom(int pagenum, int i, String id);
	/**
	 * 查询房间总数
	 * @return
	 */
	public int numberRoom(String id);
	/**
	 * 查询出所有酒店
	 * @return
	 */
	public List<Object> selHotel();
	/**
	 * 保存对象
	 * @param i
	 * @return
	 */
	public void saveOrUpdateObj(Object o);
	/**
	 * 删除房间
	 * @param valueOf
	 * @return
	 */
	public boolean delRoom(Integer id);
	/**
	 * 通过ID查询用户
	 * @param userId
	 * @return
	 */
	public Admin selAdminId(int userId);
	/**
	 * 置顶商户
	 * @param parameter
	 * @param sort 
	 */
	public void upSellerToggle(String parameter, String sort);
	/**
	 * 根据id查询行业类别
	 * @param customTypeId
	 * @return
	 */
	public CustomType selCustomTypeById(Integer customTypeId);
}
