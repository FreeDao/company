package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.common.SystemAgrs;
import com.era.orm.Activities;
import com.era.orm.City;
import com.era.orm.Market;
import com.era.orm.Seller;
import com.era.service.SellerService;
import com.era.util.BaseUtils;
import com.era.util.CommonUtil;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SellerAction extends ActionSupport implements ModelDriven<Seller>,
ServletRequestAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private Seller model = new Seller();
	
	private SellerService sellerService;
	private List<Seller> listSeller;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String nameOne;
	private List<Activities> listAct;
	private List<Object> listObj;
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	
	/**
	 * 查询用户有多少条商户
	 * @return
	 */
	public String selSeller()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		Integer sellerId=(Integer) request.getSession().getAttribute("sellerId");
		pagecount = sellerService.numberSeller(request.getParameter("name"),sellerId);
		nameOne = request.getParameter("name");
		if(pagecount<5){
			pageCount=1;
		}else{
			pageCount = pagecount/5;
			int i  = pagecount%5;
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
		listSeller = sellerService.selSeller(request.getParameter("name"),sellerId,pagenum,5);
		return SUCCESS;
	}
	
	/**
	 * 跳转到修改个人信息
	 * @return
	 */
	public String updatesellerPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		listAct = sellerService.setListAct();
		model = sellerService.selOneSeller(Integer.valueOf(request.getParameter("SellerId")));
		return SUCCESS;
	}
	/**
	 * 跳转到添加商户信息
	 * @return
	 */
	public String addSelectPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		Integer sellerId=(Integer) request.getSession().getAttribute("sellerId");
		int city = sellerService.selCityId(sellerId);
		if(city == 0)
		{
			
		}
		else
		{
			listObj = sellerService.seArea(city);
		}
		listAct = sellerService.setListAct();
		return SUCCESS;
	}
	
	/**
	 *修改商户
	 * @return
	 * @throws IOException
	 */
	public String updateSeller() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		String data="";
		
		String titile = CommonUtil.changeIos8859ToUtf8(request.getParameter("titile"));
		String phone = request.getParameter("phone");
		String brief = request.getParameter("brief");
		String preferential = request.getParameter("preferential");
		String prodetId = request.getParameter("prodetId");
		Map<String, String> json = alertInFo.getGeocoderLatitude(CommonUtil.changeIos8859ToUtf8(request.getParameter("address")));
		if(json == null || json.equals(""))
		{
			/*data = "{msg:\"3\"}";
			response.getWriter().write(data);*/
			alertInFo.alertReturn("地图未找到位置");
			return SUCCESS;
		}
		String address = CommonUtil.changeIos8859ToUtf8(request.getParameter("address"));
		String id_str = request.getParameter("SellerId");
		int id = Integer.valueOf(id_str);
		Seller s = sellerService.selOneSeller(id);
		
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		String t = format.format(new Date());
		String logo = "";
		if(getPictureFileName() != null)
		{
			String StreamFos = getPictureFileName().substring(getPictureFileName().indexOf("."));
			long picturefis = getPicture().length();
			if (picturefis > 3000000) {
				alertInFo.alertReturn("你上传的文件过大");
				return SUCCESS;
			}
			
			if (!StreamFos.equals(".gif") && !StreamFos.equals(".jpg") && !StreamFos.equals(".png")
			&& !StreamFos.equals(".bmp")) {
				alertInFo.alertReturn("你上传格式不对请上传gif,jpg,bmp,png的格式");
			return SUCCESS;
			}
			System.out.println(t+getPictureFileName());
			FileOutputStream fos = new FileOutputStream(
					SystemAgrs.uploadPath+request.getSession().getAttribute("userId")+t+getPictureFileName());
					//"E:/"+request.getSession().getAttribute("userId")+t+getPictureFileName());
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
			logo = "http://api.pymob.cn:8080/City/qrurl/"+request.getSession().getAttribute("userId")+t+getPictureFileName();
		} else {
			logo = s.getLogo();
		}
		
		boolean bool = sellerService.updateSeller(id,logo,titile,phone,brief,preferential,address,json.get("lng"),json.get("lat"),prodetId);
		if(bool)
		{
			/*data = "{msg:\"1\"}";
			response.getWriter().write(data);*/
			alertInFo.alertReturn("修改成功");
		}
		else
		{
			/*data = "{msg:\"2\"}";
			response.getWriter().write(data);*/
			alertInFo.alertReturn("修改失败");
		}
		return updatesellerPage();
	}
	
	/**
	 * 添加商户
	 * @return
	 * @throws IOException
	 * @throws ParseException 
	 */
	public String addSeller() throws IOException, ParseException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		Seller seller = new Seller();
		seller.setTypeId((Integer) request.getSession().getAttribute("sellerId"));
		Market market = sellerService.selMarketId((Integer) request.getSession().getAttribute("sellerId"));
		seller.setCityId(market.getCityId());
		seller.setTitile(request.getParameter("titile"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date=new java.util.Date();  
		String str=sdf.format(date);  
		Timestamp ts = new Timestamp(format.parse(str).getTime());
		seller.setAddtime(ts);
		seller.setPhone(request.getParameter("phone"));
		seller.setBrief(request.getParameter("brief"));
		seller.setPreferential(request.getParameter("preferential"));
		seller.setProductId(0);
		seller.setAddress(request.getParameter("address"));
		Map<String, String> json = alertInFo.getGeocoderLatitude(request.getParameter("address"));
		if(json == null || json.equals(""))
		{
			alertInFo.alertReturn("地址不正确");
			selSeller();
			return SUCCESS;
		}
		seller.setLog(json.get("lng"));
		seller.setDim(json.get("lat"));
		SimpleDateFormat format1 = new SimpleDateFormat("HHmmss");
		String t = format1.format(new Date());
		if(getPictureFileName() != null)
		{
			String StreamFos = getPictureFileName().substring(getPictureFileName().indexOf("."));
			long picturefis = getPicture().length();
			if (picturefis > 3000000) {
				alertInFo.alertReturn("你上传的文件过大！");
				return SUCCESS;
			}

			if (!StreamFos.equals(".gif") && !StreamFos.equals(".jpg") && !StreamFos.equals(".png")
			&& !StreamFos.equals(".bmp")) {
				alertInFo.alertReturn("你上传格式不对请上传gif,jpg,bmp,png的格式！");
			return SUCCESS;
			}

			FileOutputStream fos = new FileOutputStream(
					SystemAgrs.uploadPath+request.getSession().getAttribute("userId")+t+getPictureFileName());
			
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
		}
		seller.setLogo("http://api.pymob.cn:8080/City/qrurl/"+request.getSession().getAttribute("userId")+t+getPictureFileName());
		boolean bool = sellerService.addSeller(seller);
		if(bool)
		{
			selSeller();
		}
		else
		{
			alertInFo.alertReturn("添加异常");
			selSeller();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除商户
	 * @return
	 * @throws IOException 
	 */
	public String delSeller() throws IOException {
		if(request.getSession().getAttribute("userId") == null) {
			return "error";
		}
		String sellerId = request.getParameter("SellerId");
		boolean bool = sellerService.delSeller(sellerId);
		if (bool) {
			alertInFo.alertReturn("删除商户成功");
		} else {
			alertInFo.alertReturn("删除异常，失败");
		}
		selSeller();
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	@Override
	public Seller getModel() {
		return model;
	}

	public SellerService getSellerService() {
		return sellerService;
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

	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}

	public List<Seller> getListSeller() {
		return listSeller;
	}

	public void setListSeller(List<Seller> listSeller) {
		this.listSeller = listSeller;
	}

	public String getNameOne() {
		return nameOne;
	}

	public void setNameOne(String nameOne) {
		this.nameOne = nameOne;
	}

	public List<Activities> getListAct() {
		return listAct;
	}

	public void setListAct(List<Activities> listAct) {
		this.listAct = listAct;
	}

	public List<Object> getListObj() {
		return listObj;
	}

	public void setListObj(List<Object> listObj) {
		this.listObj = listObj;
	}

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
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

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

}
