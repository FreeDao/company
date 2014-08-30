package com.era.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.era.orm.Mall;
import com.era.orm.Order;
import com.era.orm.Seller;
import com.era.orm.Share;
import com.era.orm.ShopType;
import com.era.orm.ShopTypeTwo;
import com.era.orm.Video;

public interface SellerService {
	/**
	 * 获取商户的所有条数
	 * 
	 * @return
	 */
	public int numberSeller(String name);

	/**
	 * 获取全部的商户
	 * 
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Seller> selSeller(String name, int pageNo, int pageSize);

	/**
	 * 通过ID删除用户
	 * 
	 * @param id
	 * @return
	 */
	public boolean delSeller(int id);
	public List<Order> selMyOrder(String userId, String buy);

	/**
	 * 通过商家id查商家
	 * 
	 * @param id
	 * @return
	 */
	public List<Seller> selSellerById(int id);
	/**
	 * 客户端 查询所有商家信息
	 * @param cityName
	 * @param log
	 * @param dim
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List getSellerInfo(String cityId,String log,String dim,int pageNo,int pageNum,String keyStr,int typeId);
	
	/**
	 * TODO 升级版客户端查询所有商家信息
	 * 2013.07.01
	 * 1、根据传递进来的经度和纬度，计算附近商家距离，然后返回；
	 * 2、根据城市下面的对应的具体区域查询显示返回结果
	 * @param cityId
	 * @param log
	 * @param dim
	 * @param pageNo
	 * @param pageNum
	 * @param keyStr
	 * @param typeId
	 * @param villageId 
	 * @param sort 
	 * @return
	 */
	public List getSellerInfoList(String yiji,String erji,String log,String dim,String pageNo,String pageNum,String typeId,String shop, String villageId, String sort);
	
	
	/**
	 * 客户端 查询单个商家信息
	 * @param cityName
	 * @param log
	 * @param dim
	 * @param keyStr
	 * @param typeId
	 * @return
	 */
	public Seller getSellerOneInfo(int sellerId);
	
	/**
	 * 客户端 统计对应商家
	 * @param cityName
	 * @param log
	 * @param dim
	 * @param keyStr
	 * @return
	 */
	public Integer countSeller(String cityId,String log,String dim,String keyStr,int typeId);
	
	/**
	 * TODO 升级版客户端统计对应商家信息总条数
	 * 2013.07.04
	 * 1、添加区域id
	 * @param cityId
	 * @param log
	 * @param dim
	 * @param keyStr
	 * @param typeId
	 * @param areaId
	 * @return
	 */
	public Integer countSellerInfo(String cityId,String log,String dim,String keyStr,int typeId,int areaId,String productId);

	public List<ShopType> selShopType();

	public List<ShopTypeTwo> selShopTypeTwo(String parameter);

	public List<Mall> selMall(String cityId, String pageNo, String pageNum);

	public List<Video> selVideo(String type, String log, String dim,
			String villageId, String pageNo, String pageNum);

	public boolean addVideo(Video video);

	public List<Share> selShare(String userId,String type,String parameter, String parameter2,
			String parameter3, String parameter4, String parameter5);

	public void getSellerById(String id, Map<String, Object> map);

	public void updateSeller(String id, String name, String tel,
			String villageId, String address, String productBrief,
			String sellerBrief, String recruit, List<File> product,
			List<String> productContentType, List<File> seller,
			List<String> sellerContentType, String projectUrl, String filepath) throws IOException;

	public void praise(String shareId, String userId);
	
}
