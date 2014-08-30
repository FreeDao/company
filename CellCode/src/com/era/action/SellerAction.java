package com.era.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Images;
import com.era.orm.Mall;
import com.era.orm.Order;
import com.era.orm.Seller;
import com.era.orm.Share;
import com.era.orm.ShopType;
import com.era.orm.ShopTypeTwo;
import com.era.orm.Video;
import com.era.service.AreaService;
import com.era.service.SellerService;
import com.era.util.BaseUtils;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SellerAction  extends ActionSupport implements ModelDriven<Seller>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	private SellerService sellerSerivce;
	private AreaService areaService;
	private List<Seller> listSeller;
	private List<Share> listShare;
	
	private Map<String,Object> map  = new HashMap<String,Object>();
	
	Seller model = new Seller();
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	
	private String cityName;//城市名字
	private String cityId;//城市Id
	private String log;//经度
	private String dim;//纬度
	private String keyStr;//关键字
	private String result;
	
	private List<File> product;
	private List<String> productContentType;
	private List<String> productFileName;
	
	private List<File> seller;
	private List<String> sellerContentType;
	private List<String> sellerFileName;
	
	/**
	 * 查询所有的商户
	 * @return
	 */
	public String selSeller()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		pagecount = sellerSerivce.numberSeller(request.getParameter("name"));
		if(pagecount<15){
			pageCount=1;
		}else{
			pageCount = pagecount/15;
			int i  = pagecount%15;
			if(i>0)
			{
				pageCount+=1;
			}
		}
		if (pagenum < 1) {
			pagenum = 1;
		}
		if (pagenum > pagecount) {
			if(pagecount == 0)
			{
				
			}
			else
			{
				pagenum = pagecount;
			}
		}
		listSeller = sellerSerivce.selSeller(request.getParameter("name"),pagenum,15);
		return SUCCESS;
	}
	
	/**
	 * 通过id查询商户信息
	 * @return
	 */
	public String selSellerById(){
		try {
			String id = request.getParameter("id");
			
			sellerSerivce.getSellerById(id,map);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			map.put("responseCode", "1");
			map.put("msg", "查询出错");
		}
		return SUCCESS;
	}
	
	/**
	 * 更新商户信息
	 * @return
	 */
	public String updateSeller(){
		try {
			
			String id = request.getParameter("id");
			String name = BaseUtils.changeIos8859ToUtf8(request.getParameter("name"));
			String tel = request.getParameter("tel");
			String villageId = request.getParameter("villageId");
			String address = BaseUtils.changeIos8859ToUtf8(request.getParameter("address"));
			String productBrief = BaseUtils.changeIos8859ToUtf8(request.getParameter("productBrief"));
			String sellerBrief = BaseUtils.changeIos8859ToUtf8(request.getParameter("sellerBrief"));
			String recruit = BaseUtils.changeIos8859ToUtf8(request.getParameter("recruit"));
			String filepath=request.getRealPath("/uploadImgs");
			StringBuffer url = request.getRequestURL();
			String projectUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
			
			sellerSerivce.updateSeller(id,name,tel,villageId,address,productBrief,sellerBrief,recruit,product,productContentType,seller,sellerContentType,projectUrl,filepath);
			
			map.put("responseCode", "0");
			map.put("msg", "更新成功");
			
		} catch (Exception e) {
			// TODO: handle exception
			map.put("responseCode", "1");
			map.put("msg", "更新出错");
		}
		return SUCCESS;
	}
	
	/**
	 * 我的订单
	 * @return
	 */
	public String myOrder()
	{
		try {
			List<Order> list = sellerSerivce.selMyOrder(request.getParameter("userId"),request.getParameter("buy"));
			if(list.size()<1)
			{
				map.put("responseCode", "-2");
				map.put("msg", "暂无订单");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "订单异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 删除商户
	 * @return
	 * @throws IOException 
	 */
	public String delSeller() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = sellerSerivce.delSeller(Integer.valueOf(request.getParameter("did")));
		if(bool)
		{
			selSeller();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String sellerPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		return SUCCESS;
	}

	
	/**
	 * TODO 便民商户
	 */
	public String getSellerList()
	{
		try {
			List list = sellerSerivce.getSellerInfoList(request.getParameter("column"),request.getParameter("columnTwo"),request.getParameter("log"), request.getParameter("dim"), request.getParameter("pageNo"), request.getParameter("pageNum"),request.getParameter("typeId"),request.getParameter("shop"),request.getParameter("villageId"),request.getParameter("sort"));
			if(list.size()<1)
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	/**
	 * 小区
	 * @return
	 */
	public String selShopType()
	{
		try {
			List<ShopType> list = sellerSerivce.selShopType();
			if(list == null ||list.equals(""))
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 二级栏目
	 * @return
	 */
	public String selShopTypeTwo()
	{
		try {
			List<ShopTypeTwo> list = sellerSerivce.selShopTypeTwo(request.getParameter("shopId"));
			if(list == null ||list.equals(""))
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	/**
	 * 查询商城
	 * @return
	 */
	public String selMall()
	{
		try {
			String cityId = request.getParameter("cityId");
			List<Mall> list = sellerSerivce.selMall(cityId,request.getParameter("pageNo"),request.getParameter("pageNum"));
			if(list == null ||list.equals(""))
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	/**
	 * 查询租售房源
	 * @return
	 */
	public String selVideo()
	{
		try {
			String type = request.getParameter("type");//租房还是售房
			String log = request.getParameter("log");//
			String dim = request.getParameter("dim");//
			String villageId = request.getParameter("villageId");//小区ID
			String pageNo = request.getParameter("pageNo");
			String pageNum =  request.getParameter("pageNum");
			List<Video> list = sellerSerivce.selVideo(type,log,dim,villageId,pageNo,pageNum);
			if(list.size()<1)
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("responseCode", "0");
				map.put("list", list);
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询异常");
		}
		return SUCCESS;
	}
	/**
	 * 添加租售房消息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addVideo() throws UnsupportedEncodingException
	{
		try {
			String type = request.getParameter("personal");//1个人2经纪人
			String name = "";//小区名称
			String title = "";//标题
			String address = "";//地址
			String layout = "";//户型
			String price = request.getParameter("price");//1个人2经纪人
			String bife = "";//1个人2经纪人
			String info = "";//1个人2经纪人
			Map<String, String> json = alertInFo.getGeocoderLatitude(request.getParameter("address"));
			if(json == null || json.equals(""))
			{
				map.put("responseCode", "1");
				map.put("msg", "未找到该地址");
				return SUCCESS;
			}
			if ( BaseUtils.isChinaese(request.getParameter("name"))) {
				// 用于IOS客户端传递过来的用户名
				name = new String(
						request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				name = request.getParameter("name");
			}
			if ( BaseUtils.isChinaese(request.getParameter("title"))) {
				// 用于IOS客户端传递过来的用户名
				title = new String(
						request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				title = request.getParameter("title");
			}
			if ( BaseUtils.isChinaese(request.getParameter("address"))) {
				// 用于IOS客户端传递过来的用户名
				address = new String(
						request.getParameter("address").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				address = request.getParameter("address");
			}
			if ( BaseUtils.isChinaese(request.getParameter("layout"))) {
				// 用于IOS客户端传递过来的用户名
				layout = new String(
						request.getParameter("layout").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				layout = request.getParameter("layout");
			}
			if ( BaseUtils.isChinaese(request.getParameter("bife"))) {
				// 用于IOS客户端传递过来的用户名
				bife = new String(
						request.getParameter("bife").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				bife = request.getParameter("bife");
			}
			if ( BaseUtils.isChinaese(request.getParameter("info"))) {
				// 用于IOS客户端传递过来的用户名
				info = new String(
						request.getParameter("info").getBytes("ISO-8859-1"), "UTF-8");
			} else {
				info = request.getParameter("info");
			}
			
			Video video = new Video();
			video.setAddress(address);
			
			video.setLog(json.get("lng"));
			video.setDim(json.get("lat"));
			video.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			video.setBife(bife);
			video.setInfo(info);
			video.setLayout(layout);
			video.setTitle(title);
			video.setPrice(Integer.valueOf(price));
			video.setQuarters(name);
			video.setPersonal(Integer.valueOf(type));
			boolean bool = sellerSerivce.addVideo(video);
			if(bool)
			{
				map.put("responseCode", "0");
			}
			else
			{
				map.put("responseCode", "1");
			}
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "添加租售信息异常");
		}
		return SUCCESS;
	}
	/**
	 * 查询分享
	 * @return
	 */
	public String selShare()
	{
		try 
		{
			listShare = sellerSerivce.selShare(request.getParameter("userId"),request.getParameter("type"),request.getParameter("dim"),request.getParameter("log"),request.getParameter("villageId"),request.getParameter("pageNo"),request.getParameter("pageNum"));
			if(listShare.size()<1)
			{
				map.put("responseCode", "-2");
			}
			else
			{
				map.put("list", listShare);
				map.put("responseCode", "0");
			}
			
		} catch (Exception e) {
			map.put("responseCode", "1");
			map.put("msg", "查询分享");
		}
		return SUCCESS;
	}
	
	/**
	 * 点赞
	 * @return
	 */
	public String praise(){
		try 
		{
			String shareId = request.getParameter("shareId");
			String userId = request.getParameter("userId");
			
			sellerSerivce.praise(shareId,userId);
			
			map.put("responseCode", "0");
			map.put("msg", "点赞成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("responseCode", "1");
			map.put("msg", "点赞无效");
		}
		return SUCCESS;
	}
	
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Seller getModel() {
		return model;
	}

	public SellerService getSellerSerivce() {
		return sellerSerivce;
	}

	public void setSellerSerivce(SellerService sellerSerivce) {
		this.sellerSerivce = sellerSerivce;
	}

	public List<Seller> getListSeller() {
		return listSeller;
	}

	public void setListSeller(List<Seller> listSeller) {
		this.listSeller = listSeller;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getPagesum() {
		return pagesum;
	}

	public void setPagesum(int pagesum) {
		this.pagesum = pagesum;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getDim() {
		return dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}
	
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getKeyStr() {
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Share> getListShare() {
		return listShare;
	}

	public void setListShare(List<Share> listShare) {
		this.listShare = listShare;
	}

	public List<File> getProduct() {
		return product;
	}

	public void setProduct(List<File> product) {
		this.product = product;
	}

	public List<String> getProductContentType() {
		return productContentType;
	}

	public void setProductContentType(List<String> productContentType) {
		this.productContentType = productContentType;
	}

	public List<String> getProductFileName() {
		return productFileName;
	}

	public void setProductFileName(List<String> productFileName) {
		this.productFileName = productFileName;
	}

	public List<File> getSeller() {
		return seller;
	}

	public void setSeller(List<File> seller) {
		this.seller = seller;
	}

	public List<String> getSellerContentType() {
		return sellerContentType;
	}

	public void setSellerContentType(List<String> sellerContentType) {
		this.sellerContentType = sellerContentType;
	}

	public List<String> getSellerFileName() {
		return sellerFileName;
	}

	public void setSellerFileName(List<String> sellerFileName) {
		this.sellerFileName = sellerFileName;
	}
	
}
