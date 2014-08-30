package com.era.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Seller;
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
	

	/**
	 * TODO 客户端获取商家列表（附近、详情）
	 */
	public void getSellerList(){
		
		try {
			List list = sellerSerivce.getSellerInfoList(request.getParameter("cityId"), request.getParameter("log"), request.getParameter("dim"), request.getParameter("pageNo"), request.getParameter("pageNum"), keyStr,request.getParameter("typeId"),request.getParameter("areaId"),request.getParameter("productId"),request.getParameter("customTypeId"));
			JSONArray array = JSONArray.fromObject(list);
			result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";	
		} catch (Exception e) {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}
	
	
	/**
	 *  TODO 升级版 客户端获取商家列表（附近、详情）
	 */
	public void getSellerListInfo(){
		
//		cityId = request.getParameter("cityId");
//		log = request.getParameter("log");
//		dim = request.getParameter("dim");
//		String pageNo_str = request.getParameter("pageNo");
//		String pageNum_str = request.getParameter("pageNum");
//		String keyStr_str = request.getParameter("keyStr");		
//		String typeId_str = request.getParameter("typeId");
//		String sellerId_str = request.getParameter("sellerId");
//		String areaId_str = request.getParameter("areaId");
//		String productId = request.getParameter("productId");
//		if(sellerId_str != null){
//			int sellerId = Integer.parseInt(sellerId_str);
//			if(sellerId == -1){							
//				if(cityId!=null){
//					if(keyStr_str != null){
//						//2、处理关键字
//						boolean torf_keyStr = BaseUtils.isChinaese(keyStr_str);
//						System.out.println("torf-----keyStr_str---->"+torf_keyStr+"<---");
//						if(torf_keyStr){
//							try {
//								//用于IOS客户端传递过来的用户名
//								keyStr = new String(keyStr_str.getBytes("ISO-8859-1"),"UTF-8");
//	
//							} catch (Exception e) {
//								e.printStackTrace();
//							}	
//						}else{
//							keyStr = keyStr_str+"";
//						}
//					}
//					
//					int pageNo = pageNo_str != null && !pageNo_str.isEmpty() ? Integer.parseInt(pageNo_str) : 1;//当前页
//					int pageNum = pageNum_str != null && !pageNo_str.isEmpty() ? Integer.parseInt(pageNum_str) : BaseUtils.DEFAULT_PAGENUM;//返回条数
//					int typeId = typeId_str != null && !typeId_str.isEmpty()? Integer.parseInt(typeId_str):-1;//市场类型
//					int areaId = areaId_str != null && !areaId_str.isEmpty() ? Integer.parseInt(areaId_str):-1;//行政区域
//					
//					//分页
//					int countNum = sellerSerivce.countSellerInfo(cityId, log, dim, keyStr,typeId,areaId,productId);					
//					System.out.println("----getSellerList---------countNum------->"+countNum+"<--------------");
//
//					double pageSize = Math.ceil((double) countNum / pageNum);
//					
////					//TODO 查询对应的行政区域
////					List<Area> list_area = areaService.getAreaById(Integer.parseInt(cityId));
//
//					List list_seller = sellerSerivce.getSellerInfoList(cityId, log, dim, pageNo, pageNum, keyStr,typeId,areaId,productId);
//					
//					if(list_seller !=null && list_seller.size() > 0){
////						JSONArray array_to_area = new JSONArray();
////						JSONObject object_to_area = new JSONObject();
////						if(list_area!=null){
////							for(Area a : list_area){
////								object_to_area.put("id",a.getId());
////								object_to_area.put("areaName",a.getAreaName());
////								array_to_area.add(object_to_area);
////							}
////						}else{
////							object_to_area.put("id","-1");
////							object_to_area.put("areaName","全部");
////							array_to_area.add(object_to_area);
////						}
//						List seller_list = new ArrayList();
//						
//						int size = list_seller.size();
//						System.out.println("size "+size);
//						for(int i=0;i<size;i++){
//							Seller sl = (Seller)list_seller.get(i);
//						
//							Seller s = new Seller();
//							s.setId(sl.getId());
//							s.setLogo(sl.getLogo());
//							s.setTitile(sl.getTitile());
//							s.setDistance(sl.getDistance());
//							s.setPreferential(sl.getPreferential());
//							s.setPhone(sl.getPhone());
//							s.setAddress(sl.getAddress());
//							
//							seller_list.add(s);
//						}						
//						JSONArray array_seller = JSONArray.fromObject(seller_list);
//						
////						result = "{\"responseCode\":\"" + 0 + "\",\"area\":"+array_to_area.toString()+",\"results\":"+ array_seller.toString() + "}";	
//						result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array_seller.toString() + "}";
//					}else{
//						if((double)pageNo > pageSize && countNum > 0){
//							result = "{\"responseCode\":\"" + -1 + "\"}";	
//						}else{
//							result = "{\"responseCode\":\"" + -2 + "\"}";
//						}
//					}
//				}else{
//					result = "{\"responseCode\":\"" + 1 + "\"}";
//				}
//			}else{//查询单个
//				Seller seller = sellerSerivce.getSellerOneInfo(sellerId);
//				
//				if(seller == null || ("null").equals(seller)){
//					result = "{\"responseCode\":\"" + -2 + "\"}";
//				}else{
//					JSONArray array = JSONArray.fromObject(seller);
//					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
//				}
//			}
//		}else{
//			result = "{\"responseCode\":\"" + 1 + "\"}";
//		}
//		BaseUtils.responseInfo(result);
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
}
