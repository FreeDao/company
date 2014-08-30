package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Activities;
import com.era.orm.Admin;
import com.era.orm.Area;
import com.era.orm.City;
import com.era.orm.CustomType;
import com.era.orm.Images;
import com.era.orm.Market;
import com.era.orm.Room;
import com.era.orm.Seller;
import com.era.orm.SellerManager;
import com.era.orm.Tock;
import com.era.service.CityService;
import com.era.service.MarketService;
import com.era.service.NewsDetailsService;
import com.era.service.SellerManagService;
import com.era.service.SellerService;
import com.era.util.BaseUtils;
import com.era.util.PiaoJuTong;
import com.era.util.alertInFo;
import com.era.util.text;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import freemarker.template.utility.DateUtil;

public class SellerAction  extends ActionSupport implements ModelDriven<Seller>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	private SellerService sellerSerivce;
	private MarketService marketService;
	private SellerManagService sellerManagService;
	private List<Object> listSeller;
	private CityService cityService;
	private List<City> listCity;
	private List<Market> listMarket;
	private List<Room> listRoom;
	private NewsDetailsService newsDetailsService;
	private List<Activities> listAct;
	private List<CustomType> listCt; 
	
	Seller model = new Seller();
	private SellerManager sellermanager = new SellerManager();
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String nameOne;
	private int typeOne;;
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	private String cityExt;
	private String typeide;
	private String hotelid;
	
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
		typeide = request.getParameter("typeid");
		
		int userId = (Integer) request.getSession().getAttribute("userid");
		Admin admin = sellerSerivce.selAdminId(userId);
		pagecount = sellerSerivce.numberSeller(request.getParameter("name"),admin.getCityId(),request.getParameter("typeid"));
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
		if (pagenum > pagecount)
		{
			if(pagecount == 0)
			{
				
			}
			else
			{
				pagenum = pageCount;
			}
		}
		typeOne = 1;
		nameOne = request.getParameter("name");
		listSeller = sellerSerivce.selSeller(request.getParameter("name"),admin.getCityId(),request.getParameter("typeid"),pagenum,15);
		listCity = cityService.getCityAll();
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
		model = null;
		listMarket = marketService.allMarket();
		int city = (Integer) request.getSession().getAttribute("cityId");
		listAct = cityService.setListAct();
		listCity = cityService.getCityById(city);
		return SUCCESS;
	}
	/**
	 * 更新商户
	 * @return
	 */
	public String updateSellerPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		listMarket = marketService.allMarket();
		model = sellerSerivce.selSellerId(Integer.valueOf(request.getParameter("id")));
		Market market = sellerSerivce.getTypeName(model.getTypeId());
		Area area = new Area();
		try {
			area = sellerSerivce.getAreaById(model.getAreaId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		CustomType ct = sellerSerivce.selCustomTypeById(model.getCustomTypeId());
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
		listAct = cityService.setListAct();
		request.setAttribute("ct", ct);
		request.setAttribute("ma", market);
		request.setAttribute("ar", area);
		return SUCCESS;
	}
	
	/**
	 * 移动商户类别
	 * @return
	 * @throws IOException 
	 */
	public String moveType() throws IOException{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = sellerSerivce.moveType(Integer.valueOf(request.getParameter("typeid")),Integer.valueOf(request.getParameter("selid")),Integer.parseInt(request.getParameter("city")));
		if(bool)
		{
			selSeller();
		}
		else
		{
			alertInFo.alertReturn("移动异常");
			selSeller();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加修改商户
	 * @return
	 * @throws Exception 
	 */
	public String addSeller() throws Exception
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		Seller sel = new Seller();
		String sort = request.getParameter("sort");
		String sortTime = request.getParameter("sortTime");
		if( null != sort && !"".equals(sort)){
			sel.setSort(Integer.parseInt(sort));
		}
		if( null != sortTime && !"".equals(sortTime)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sel.setSortTime(sdf.parse(sortTime));
		}
		
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			sel.setId(null);
		}
		else
		{
			sel.setId(Integer.valueOf(request.getParameter("id")));
		}
		if(getPictureFileName() != null)
		{
			String StreamFos = getPictureFileName().substring(getPictureFileName().indexOf("."));
			long picturefis = getPicture().length();
			if (picturefis > 100000) {
			alertInFo.alertReturn("你上传的文件过大！");
			return SUCCESS;
			}

			if (!StreamFos.equals(".gif") && !StreamFos.equals(".jpg")
			&& !StreamFos.equals(".bmp")) {
				alertInFo.alertReturn("你上传的文件过大！");
			return SUCCESS;
			}
			FileOutputStream fos = new FileOutputStream(ServletActionContext.getRequest().getRealPath(
					"/qrurl" + "/"+getPictureFileName()));
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
			sel.setLogo("http://api.pymob.cn:8080/City/qrurl/"+getPictureFileName());
		}
		else
		{
			sel.setLogo(request.getParameter("logo"));
		}
		String customType = request.getParameter("customType");
		if( null != customType && !"".equals(customType)){
			sel.setCustomTypeId(Integer.parseInt(customType));
		}else{
			sel.setCustomTypeId(0);
		}
		sel.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
		sel.setBrief(request.getParameter("brief"));
		if(null != request.getParameter("areaid") && !"".equals(request.getParameter("areaid"))){
			sel.setAreaId(Integer.parseInt(request.getParameter("areaid")));
		}
		if(request.getParameter("isHone") != "ok" || !"ok".equals(request.getParameter("isHone")))
		{
			sel.setImgesId(1);
		}
		sel.setProductId(Integer.valueOf(request.getParameter("act")));
		sel.setPhone(request.getParameter("phone"));
		sel.setPreferential(request.getParameter("preferential"));
		sel.setCityId(Integer.valueOf(request.getParameter("city")));
		if(request.getParameter("log") == null || request.getParameter("log").equals("") || request.getParameter("lat") == null || request.getParameter("lat").equals(""))
		{
			Map<String, String> json = alertInFo.getGeocoderLatitude(request.getParameter("address"));
			if(json == null || json.equals(""))
			{
				alertInFo.alertReturn("地图不存在此地址!");
				return SUCCESS;
			}
			sel.setLog(json.get("lng"));
			sel.setDim(json.get("lat"));
		}
		else
		{
			sel.setLog(request.getParameter("log"));
			sel.setDim(request.getParameter("lat"));
		}
		sel.setAddress(request.getParameter("address"));
		sel.setTitile(request.getParameter("title"));
		if( null != request.getParameter("level")){
			sel.setLevel(Double.parseDouble(request.getParameter("level")));
		}else{
			sel.setLevel(1.0);
		}
		sel.setHotelType(request.getParameter("hotelType"));
		if( null != request.getParameter("type") && !"".equals(request.getParameter("type"))){
			sel.setType(request.getParameter("type"));
		}
		if( null != request.getParameter("price") && !"".equals(request.getParameter("price"))){
			sel.setPrice(Double.parseDouble(request.getParameter("price")));
		}
		if(request.getParameter("typeid") == null || request.getParameter("typeid").equals(""))
		{
			
		}
		else
		{
			sel.setTypeId(Integer.valueOf(request.getParameter("typeid")));
		}
		boolean bool = sellerSerivce.addSeller(sel);
		if(bool)
		{
			if(request.getParameter("id") == null || request.getParameter("id").equals(""))
			{
				String sellid = String.valueOf(100093+sel.getId());
				java.util.Date date = new java.util.Date();
				Timestamp te = new Timestamp(date.getTime());
				SellerManager ss = new SellerManager();
				ss.setUserName(sellid);
				ss.setUserPwd(PiaoJuTong.Md5("123456"));
				ss.setQq("111111");
				ss.setTelePhone(model.getPhone());
				ss.setEmail("111111@qq.com");
				ss.setAddTime(te);
				ss.setSellerId(sel.getId());

				boolean boolOne = sellerManagService.addSellerManager(ss);
				if(boolOne)
				{
					selSeller();
				}
				else
				{
					alertInFo.alertReturn("添加异常!");
				}
			}
			if(request.getParameter("isHone").equals("ok") || request.getParameter("isHone") == "ok")
			{
					City city = newsDetailsService.selCityId(Integer.valueOf(request.getParameter("city")));
					List<Tock> listTock = newsDetailsService.listTockAll(city.getCityName());
					for (int i = 0; i < listTock.size(); i++) 
					{
			            PayLoad payLoad = new PayLoad();
			            System.out.println(request.getParameter("title"));
			            payLoad.addAlert(request.getParameter("title"));
			            payLoad.addBadge(1);
			            payLoad.addSound("default");
			            
			            PushNotificationManager pushManager = PushNotificationManager.getInstance();
			            pushManager.addDevice("iPhone", listTock.get(i).getTockId());
			            
			            String host= "gateway.push.apple.com";  //测试用的苹果推送服务器
			            int port = 2195;
			            String certificatePath = "D://tomcat//webapps//City//js//MyApnsCert_Pro.p12"; //刚才在mac系统下导出的证书
			              
			            String certificatePassword= "62504517";
			            
			            pushManager.initializeConnection(host, port, certificatePath,certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
			              
			            Device client = pushManager.getDevice("iPhone");
			            pushManager.sendNotification(client, payLoad); 
			            pushManager.stopConnection();
			            pushManager.removeDevice("iPhone");
					}
			}
			selSeller();
		}
		else
		{
			alertInFo.alertReturn("添加异常!");
		}
		return SUCCESS;
	}

	/**
	 * 跳转房间管理页面
	 * @return
	 */
	public String selRoomPage(){
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		pagecount = sellerSerivce.numberRoom(hotelid);
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
				pagenum = pageCount;
			}
		}
		List<Object> roomList = sellerSerivce.selRoom(pagenum,15,hotelid);
		listSeller = sellerSerivce.selHotel();
		request.setAttribute("room", roomList);
		return SUCCESS;
	}

	/**
	 * 跳转到添加房型页面
	 * @return
	 */
	public String addRoomPage(){
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		listSeller = sellerSerivce.selHotel();
		return SUCCESS;
	}
	
	/**
	 * 添加房型
	 * @return
	 * @throws IOException 
	 */
	public String addRoom() throws IOException{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		Room r = new Room();
		Images i = new Images();
		if(getPictureFileName() != null)
		{
			String StreamFos = getPictureFileName().substring(getPictureFileName().indexOf("."));
			long picturefis = getPicture().length();
			if (picturefis > 100000) {
			alertInFo.alertReturn("你上传的文件过大！");
			return SUCCESS;
			}

			if (!StreamFos.equals(".gif") && !StreamFos.equals(".jpg")
			&& !StreamFos.equals(".bmp")) {
			alertInFo.alertReturn("你上传的文件过大！");
			return SUCCESS;
			}

			FileOutputStream fos = new FileOutputStream(ServletActionContext.getRequest().getRealPath(
					"/qrurl" + "/"+getPictureFileName()));
			
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
			i.setType(4);
			i.setImgUrl("http://api.pymob.cn:8080/City/qrurl/"+getPictureFileName());
			text.pressImage("D://tomcat//webapps//City//images//00.png","D://tomcat//webapps//City//qrurl//"+getPictureFileName(), 300, 400,0.6f);
			sellerSerivce.saveOrUpdateObj(i);
			r.setImageId(i.getId());
		}
		r.setBife(request.getParameter("brief"));
		r.setName(request.getParameter("name"));
		r.setPrice(Double.parseDouble(request.getParameter("price")));
		r.setSellerId(Integer.parseInt(request.getParameter("sellerid")));
		sellerSerivce.saveOrUpdateObj(r);
		if(null != i){
			i.setCompositeId(r.getId());
			sellerSerivce.saveOrUpdateObj(i);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除房型
	 * @return
	 * @throws IOException 
	 */
	public String delRoom() throws IOException{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = sellerSerivce.delRoom(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selRoomPage();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 商户置顶
	 * @return
	 */
	public String upSeller(){
		try {
			sellerSerivce.upSellerToggle(request.getParameter("id"),request.getParameter("sort"));
			alertInFo.alertReturn("操作成功！");
			selSeller();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询行业类别
	 * @return
	 */
	public String selSellerCustomType(){
		try {
			listCt = marketService.selCustomType(Integer.valueOf(request.getParameter("marketId")));
		} catch (Exception e) {
			e.printStackTrace();
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

	public String getNameOne() {
		return nameOne;
	}

	public void setNameOne(String nameOne) {
		this.nameOne = nameOne;
	}

	public int getTypeOne() {
		return typeOne;
	}

	public void setTypeOne(int typeOne) {
		this.typeOne = typeOne;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public List<City> getListCity() {
		return listCity;
	}

	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getHotelid() {
		return hotelid;
	}

	public void setHotelid(String hotelid) {
		this.hotelid = hotelid;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public List<Market> getListMarket() {
		return listMarket;
	}

	public void setListMarket(List<Market> listMarket) {
		this.listMarket = listMarket;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public MarketService getMarketService() {
		return marketService;
	}

	public List<Object> getListSeller() {
		return listSeller;
	}

	public void setListSeller(List<Object> listSeller) {
		this.listSeller = listSeller;
	}

	public void setMarketService(MarketService marketService) {
		this.marketService = marketService;
	}

	public SellerManager getSellermanager() {
		return sellermanager;
	}

	public void setSellermanager(SellerManager sellermanager) {
		this.sellermanager = sellermanager;
	}

	public SellerManagService getSellerManagService() {
		return sellerManagService;
	}

	public void setSellerManagService(SellerManagService sellerManagService) {
		this.sellerManagService = sellerManagService;
	}

	public String getCityExt() {
		return cityExt;
	}

	public void setCityExt(String cityExt) {
		this.cityExt = cityExt;
	}

	public String getTypeide() {
		return typeide;
	}

	public void setTypeide(String typeide) {
		this.typeide = typeide;
	}

	public List<Room> getListRoom() {
		return listRoom;
	}

	public void setListRoom(List<Room> listRoom) {
		this.listRoom = listRoom;
	}

	public NewsDetailsService getNewsDetailsService() {
		return newsDetailsService;
	}

	public void setNewsDetailsService(NewsDetailsService newsDetailsService) {
		this.newsDetailsService = newsDetailsService;
	}

	public List<Activities> getListAct() {
		return listAct;
	}

	public void setListAct(List<Activities> listAct) {
		this.listAct = listAct;
	}

	public List<CustomType> getListCt() {
		return listCt;
	}

	public void setListCt(List<CustomType> listCt) {
		this.listCt = listCt;
	}

}
