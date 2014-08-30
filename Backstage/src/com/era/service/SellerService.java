package com.era.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.era.orm.Images;
import com.era.orm.Mall;
import com.era.orm.Market;
import com.era.orm.Seller;
import com.era.orm.Sellermanager;
import com.era.orm.Share;
import com.era.orm.ShopType;
import com.era.orm.ShopTypeTwo;
import com.era.orm.User;
import com.era.orm.Video;
import com.era.orm.Village;
import com.era.util.LoginUser;

public interface SellerService {
	/**
	 * 获取商户的所有条数
	 * 
	 * @return
	 */
	public int numberSeller(String name,String shopId);

	/**
	 * 获取全部的商户
	 * 
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Seller> selSeller(String name, String shopId,int pageNo, int pageSize);

	/**
	 * 通过ID删除用户
	 * 
	 * @param id
	 * @return
	 */
	public void delSeller(int id);

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
	 * @return
	 */
	public List getSellerInfoList(String log,String dim,String pageNo,String pageNum,String typeId,String shop);
	
	
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

	public List<Mall> selMall();

	public List<Video> selVideo(String type, String log, String dim,
			String villageId, String pageNo, String pageNum);

	public boolean addVideo(Video video);

	public List<Share> selShare(String parameter, String parameter2,
			String parameter3, String parameter4, String parameter5);

	public List<Video> selVideoBackstage(String parameter, String page,
			String rows);

	public int numberVideoBackstage(String parameter);

	public boolean delVideoBackstage(int parseInt);

	public List<Mall> selMallBackstage(String parameter, Integer valueOf,
			Integer valueOf2);

	public int numberMallBackstage(String parameter);

	public boolean delMallBackstage(int parseInt);

	public boolean delShareBackstage(int parseInt);

	public List<Object> selShareBackstage(String parameter, Integer valueOf,
			Integer valueOf2);

	public int numberShareBackstage(String parameter);

	public boolean addSeller(Seller s);

	public List<Market> selMarketAll();

	public List<ShopType> selShopIdY();

	public List<ShopTypeTwo> selShopIdE(String id);

	public boolean addMall(Mall ma);

	public List<Object> selMarketList();

	public int numberMarketList();

	public Object selSellerInfoById(Integer id);

	public Object selSellerLogo(Integer id);

	public void addOrUpdateSeller(LoginUser lu, String title, String phone,
			 String typeId, String address, String brief, String logo, String log, String dim, String logo2, String logo3);

	public List<Seller> selCommodity(Integer type,LoginUser lu, Integer valueOf,
			Integer valueOf2);

	public int numberCommodity(Integer type,LoginUser lu);

	public List<Object> selVillage();

	public List<Object> selShopId1();

	public List<Object> selShopId2(String pid);

	public void saveOrUpdateCommodity(String id, String title, String phone,
			String otherPrice, String price, String villageId, String shopId,
			String shopIdTwo, String brief, String level, String logo);

	public Seller getSeller(String id);

	public void saveOrUpdateSellerManager(Sellermanager ss);

	public Village selVillageById(Integer villageId);

	public void saveOrUpdateObject(User u);

	public User getUser(String id);

	public void saveOrUpdateImg(File file, String projectUrl, String filepath, String fileFileName, String fileContentType, String type, String compositeId) throws IOException;

	public List<Images> selImages(String type, String id);

	public int numberImages(String type, String id);

	public void delImg(int parseInt);

}
