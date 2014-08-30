package com.era.service;

import java.util.List;

import com.era.orm.Images;

public interface ImagesService 
{
	/**
	 * 查询全部的图片或通过ID查询部分图片条数
	 * @param id
	 * @return
	 */
	public int numberImage(String id,String type);
	/**
	 * 查询全部的图片
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Images> selImage(String id,String type,int pageNo, int pageSize);
	/**
	 * 通过ID删除图片
	 * @param id
	 * @return
	 */
	public boolean delImages(int id);
	
	/**
	 * 添加图片
	 * @return
	 */
	public boolean addImages(Images images);

}
