package com.era.service;

import java.util.List;

import com.era.orm.Images;
import com.era.orm.Product;
import com.era.orm.Seller;

public interface ProductService 
{
	/**
	 * 计算商户产品条数
	 * @return
	 */
	public int numProduct(String name,int sellerid);
	
	/**
	 * 查询商户产品
	 * @param name
	 * @param sellerid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Object> selProduct(String name,int sellerid,int pageNo, int pageSize);
	/**
	 * 查询产品的图片
	 * @param id
	 * @return
	 */
	public List<Object> selImages(Integer id,String name,int pageNo, int pageSize);
	/**
	 * 查询产品图片条数
	 * @return
	 */
	public Integer numProductImage(Integer id,String name);
	/**
	 * 删除产品
	 * @return
	 */
	public boolean delProduct(Integer id);
	/**
	 * 查询图片
	 * @param name
	 * @param sellerid
	 * @return
	 */
	public int numImagesOrr(String name,int sellerid);
	/**
	 * 查询商户产品
	 * @param name
	 * @param sellerid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Object> selImagesOrr(String name,int sellerid,int pageNo, int pageSize);
	/**
	 * 删除图片
	 * @return
	 */
	public boolean delImages(Integer id);
	/**
	 * 查询商户所有
	 * @param sellerId
	 * @return
	 */
	public List<Seller> selSellerAll(String sellerId);
	/**
	 * 添加商户图片
	 * @param iamges
	 * @return
	 */
	public boolean addImages(Images iamges);
	/**
	 * 通过ID查询产品
	 * @param id
	 * @return
	 */
	public Product selProductId(Integer id);
	/**
	 * 添加产品
	 * @return
	 */
	public boolean addProduct(Product product);

	/**
	 * 市场主产看商户
	 * @param typeId
	 * @return
	 */
	public List<Object> selSellers(Integer typeId);
}