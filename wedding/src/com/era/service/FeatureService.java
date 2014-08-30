package com.era.service;

import java.util.List;

import com.era.orm.Checkversion;
import com.era.orm.Classifcation;
import com.era.orm.Company;
import com.era.orm.Feature;
import com.era.orm.Info;
import com.era.orm.Plan;

public interface FeatureService 
{
	/**
	 *	通过经纬度或地区查询
	 * @param dim
	 * @param lat
	 * @param areaId
	 * @return
	 */
	public List<Object> featureAll(String dim,String lat,String areaId,String groom,String pageNo,String pageNum);
	/**
	 * 通过ID查询景点
	 * @param id
	 * @return
	 */
	public Feature selFeatureId(int id);
	
	/**
	 * 查询所有婚庆类别
	 * @return
	 */
	public List<Classifcation> selClassifcation();
	
	/**
	 * 查询所有用的婚庆策划
	 */
	public List<Plan> selPlan(String classifcationId,String pageNo,String pageNum);
	/**
	 * 查询所有的资讯
	 * @param type
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<Info> selInfo(String type,String pageNo,String pageNum); 
	/**
	 * 查询所有的婚庆公司
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<Company> selCompany(String pageNo,String pageNum);
	/**
	 * 检查更新
	 * @param appType
	 * @param versionNum
	 * @return
	 */
	public Checkversion getNewVersion(String appType,String versionNum);

}
