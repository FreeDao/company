package com.era.service;

import java.util.List;

import com.era.orm.Code;
import com.era.orm.Product;
import com.era.orm.User;

public interface ProductService
{
	/**
	 * 获取全部产品条数或通过ID获取单个商户的产品
	 * @param id
	 * @return
	 */
	public int numberProduct(String id);
	/**
	 * 查询全部的产品或通过ID获取单个商户产品
	 * @param id
	 * @return
	 */
	public List<Product> selProductId(String id,int pageNo, int pageSize);
	/**
	 * 通过ID删除产品
	 * @param id
	 * @return
	 */
	public boolean delProduct(int id);
	
	/**
	 * 查询获取所有的产品类型名称
	 * 
	 * @param sellerId
	 * @return
	 */
	public List getAllProductType(int proId);
	
	
	/**
	 * 查询产品类型下面的对应的图片
	 * 
	 * @param sellerId
	 * @return
	 */
	public List getProductTypeImgs(int proId,int proTypeId);
	/**
	 * 添加验证码
	 * @param code
	 * @return
	 */
	public boolean addCode(Code code);
	/**
	 * 查询验证码
	 * @return
	 */
	public Code selCodeSms(String iphone,String code);
	/**
	 * 添加用户
	 * @return
	 */
	public boolean addUserCode(User user);
	/**
	 * 验证手机是否存在
	 * @param phone
	 * @return
	 */
	public User selUserY(String phone);
	public int selCodeAddtime(String parameter,String data1);
}
