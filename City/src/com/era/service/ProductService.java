package com.era.service;

import java.util.List;

import com.era.orm.Activities;
import com.era.orm.Product;
import com.era.orm.Recommend;
import com.era.orm.RecommendType;

public interface ProductService
{
	/**
	 * 获取全部产品条数或通过ID获取单个商户的产品
	 * @param id
	 * @return
	 */
	public int numberProduct(String id);
	/**
	 * 应用汇条数
	 * @param name
	 * @return
	 */
	public int selRecommendNum(String name,int cityId);
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
	 * 添加产品
	 * @param pro
	 * @return
	 */
	public boolean addProduct(Product pro);
	/**
	 * 通过ID查询产品
	 * @return
	 */
	public Product selProductId(int id);
	/**
	 * 查询应用汇
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Recommend> selRecommend(String name,int cityId,int pageNo, int pageSize);
	/**
	 * 删除应用汇
	 * @param valueOf
	 * @return
	 */
	public boolean delRecommend(Integer valueOf);
	/**
	 * 通过ID查询应用汇
	 * @param parameter
	 * @return
	 */
	public Recommend selRecommendId(String parameter);
	/**
	 * 查询应用汇类型
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<RecommendType> selRecommendType(int pageNo, int pageSize);
	/**
	 * 添加应用汇
	 * @param rec
	 * @return
	 */
	public boolean addRecommend(Recommend rec);
	/**
	 * 活动条数
	 * @return
	 */
	public int numberAct();
	/**
	 * 活动
	 * @param parameter
	 * @param pagenum
	 * @param i
	 * @return
	 */
	public List<Activities> selAct(int pagenum, int i);
}
