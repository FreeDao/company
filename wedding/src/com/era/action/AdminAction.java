package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Admin;
import com.era.orm.Area;
import com.era.orm.Feature;
import com.era.orm.Image;
import com.era.orm.User;
import com.era.service.AdminService;
import com.era.util.BaseUtils;
import com.era.util.PiaoJuTong;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminAction extends ActionSupport implements ModelDriven<Admin>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private Admin model = new Admin();
	private AdminService adminService;
	private User user = new User();
	private List<User> listUser;
	private List<Object> listFeature;
	private Area area = new Area();
	private List<Area> listArea;
	private Feature feature;
	
	private int pagenum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String nameOne;
	private String nameFeature;
	private String nameArea;
	private String goods;
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	private String ide;
	private List<Image> listImage;
	
	/**
	 * 登录
	 * @return
	 * @throws IOException 
	 */
	public String loginAdmin() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		String data="";
		try{
			if(request.getSession().getAttribute("userId") == null)
			{
				model = adminService.login(request.getParameter("userName"),PiaoJuTong.Md5(request.getParameter("passWord")));
				if(model == null)
				{
					data = "{msg:\"1\"}";
					response.getWriter().write(data);
					return null;
				}
				request.getSession().setAttribute("userId", model.getId());
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			data = "{msg:\"2\"}";
			response.getWriter().write(data);
			return null;
		}
		data = "{msg:\"3\"}";
		response.getWriter().write(data);
		return null;
	}
	/**
	 * 跳转到主页
	 * @return
	 */
	public String pageUser()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		return SUCCESS;
	}
	/**
	 * 退出
	 */
	@SuppressWarnings("rawtypes")
	public String UserLoginOut() throws Exception
	{
		request.getSession().invalidate();
		Enumeration e = request.getSession().getAttributeNames();
		while (e.hasMoreElements()) 
		{
			String sessionName = (String) e.nextElement();
			request.getSession().removeAttribute(sessionName);
		}
		return SUCCESS;
	}
	/**
	 * 用户信息
	 * @return
	 */
	public String selPageUser()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		model = adminService.selAdimId((Integer) request.getSession().getAttribute("userId"));
		return SUCCESS;
	}
	/**
	 * 跳转到修改密码
	 * @return
	 */
	public String updatePassPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		return SUCCESS;
	}
	/**
	 * 修改所有信息
	 * @return
	 * @throws Exception 
	 */
	public String updatePass() throws Exception
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		String data="";
		model = adminService.selAdimId((Integer) request.getSession().getAttribute("userId"));
		model.setId(model.getId());
		if(request.getParameter("passWord") == null || request.getParameter("passWord").equals(""))
		{
			model.setPassWord(model.getPassWord());
			model.setQq(request.getParameter("qq"));
			model.setIphone(request.getParameter("phone"));
		}
		else
		{
			if(PiaoJuTong.Md5(request.getParameter("oPassWord")).equals(model.getPassWord()))
			{
				model.setPassWord(PiaoJuTong.Md5(request.getParameter("passWord")));
			}
			else
			{
				data = "{msg:\"2\"}";
				response.getWriter().write(data);
			}
			model.setQq(model.getQq());
			model.setIphone(model.getIphone());
		}
		model.setAddtime(model.getAddtime());
		model.setUserName(model.getUserName());
		boolean bool = adminService.updateUser(model);
		if(bool)
		{
			if(request.getParameter("oPassWord") == null ||request.getParameter("oPassWord").equals(""))
			{
				data = "{msg:\"1\"}";
				response.getWriter().write(data);
				return null;
			}
			else
			{
				UserLoginOut();
				data = "{msg:\"1\"}";
				response.getWriter().write(data);
				return null;
			}
		}
		else
		{
			data = "{msg:\"2\"}";
			response.getWriter().write(data);
		}
		return null;
	}
	/**
	 * 跳转到修改个人信息
	 * @return
	 */
	public String updateMe()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		model = adminService.selAdimId((Integer) request.getSession().getAttribute("userId"));
		return SUCCESS;
	}
	/**
	 * 查询所有的用户
	 * @return
	 */
	public String selUserAll()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		pagecount = adminService.numUserAll(request.getParameter("name"));
		nameOne = request.getParameter("name");
		if(pagecount<7){
			pageCount=1;
		}else{
			pageCount = pagecount/7;
			int i  = pagecount%7;
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
		listUser = adminService.selUserAll(request.getParameter("name"),pagenum,7);
		return SUCCESS;
	}
	/**
	 * 删除用户
	 * @return
	 * @throws IOException 
	 */
	public String delUser() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		boolean bool = adminService.delUser(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selUserAll();
		}
		else
		{
			selUserAll();
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	/**
	 * 所有景点
	 * @return
	 */
	public String selfeatureAll()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		pagecount = adminService.numfeatureAll(request.getParameter("name"),request.getParameter("good"));
		nameFeature = request.getParameter("name");
		goods = request.getParameter("good");
		if(pagecount<3){
			pageCount=1;
		}else{
			pageCount = pagecount/3;
			int i  = pagecount%3;
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
		listFeature = adminService.selfeatureAll(request.getParameter("name"),request.getParameter("good"),pagenum,3);
		return SUCCESS;
	}
	/**
	 * 添加景点
	 * @return
	 * @throws IOException 
	 */
	public String addFeature() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		Feature feature = new Feature();
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			
		}
		else
		{
			feature.setId(Integer.valueOf(request.getParameter("id")));
		}
		feature.setFeatureName(request.getParameter("featureName"));
		Map<String, String> json = alertInFo.getGeocoderLatitude(request.getParameter("address"));
		if(json == null || json.equals(""))
		{
			alertInFo.alertReturn("地图不存在此地址!");
			return SUCCESS;
		}
		feature.setAddress(request.getParameter("address"));
		feature.setLot(json.get("lng"));
		feature.setDim(json.get("lat"));
		feature.setPrice(Integer.valueOf(request.getParameter("price")));
		feature.setTime(request.getParameter("time"));
		feature.setLeven(Integer.valueOf(request.getParameter("leven")));
		feature.setBife(request.getParameter("bife"));
		feature.setHumanity(request.getParameter("humanity"));
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
				"/upadeImage" + "/"+getPictureFileName()));
				
				FileInputStream fis = new FileInputStream(getPicture());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
						}
			}
		feature.setLogo("http://www.tcshenghuo.org:8806/JunKeting"+"/upadeImage" + "/"+getPictureFileName());
		feature.setAreaId(Integer.valueOf(request.getParameter("areaId")));
		feature.setGroom(Integer.valueOf(request.getParameter("good")));
		boolean bool = adminService.updateFeature(feature);
		if(bool)
		{
			alertInFo.alertReturn("添加成功");
		}
		else
		{
			alertInFo.alertReturn("添加失败");
		}
		return SUCCESS;
	}
	
	/**
	 * 删除景点
	 * @return
	 * @throws IOException 
	 */
	public String delFeature() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		boolean bool = adminService.delFeature(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selfeatureAll();
		}
		else
		{
			selfeatureAll();
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String upadeFeaturePage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			
		}
		else
		{
			feature = adminService.selFeatureId(Integer.valueOf(request.getParameter("id")));
		}
		listArea = adminService.selAreaAll();
		return SUCCESS;
	}
	/**
	 * 添加更多图片
	 * @return
	 * @throws IOException 
	 */
	public String addImage() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
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
			"/upadeImage" + "/"+getPictureFileName()));
			
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
		}
		Image image = new Image();
		image.setType(1);
		ide = request.getParameter("compositeId");
		image.setCompositeId(Integer.valueOf(ide));
		image.setImgUrl("http://www.tcshenghuo.org:8806/JunKeting"+
				"/upadeImage" + "/"+getPictureFileName());
		boolean bool = adminService.updateImage(image);
		if(bool)
		{
			alertInFo.alertReturn("上传成功");
		}
		else
		{
			alertInFo.alertReturn("上传异常");
		}
		return SUCCESS;
	}
	/**
	 * 跳转到添加图片
	 * @return
	 */
	public String addImagePage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		ide  = request.getParameter("id");
		return SUCCESS;
	}
	/**
	 * 查询图片
	 * @return
	 */
	public String selImageAll()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		pagecount = adminService.numImageAll(request.getParameter("id"));
		if(pagecount<3){
			pageCount=1;
		}else{
			pageCount = pagecount/3;
			int i  = pagecount%3;
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
		listImage = adminService.selImageAll(request.getParameter("id"),pagenum,3);
		return SUCCESS;
	}
	
	/**
	 * 删除景点
	 * @return
	 * @throws IOException 
	 */
	public String delImage() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		boolean bool = adminService.delImage(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selImageAll();
		}
		else
		{
			selImageAll();
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	/**
	 * 查询所有地区
	 * @return
	 */
	public String selAreaAllId()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		pagecount = adminService.numAreaAll(request.getParameter("name"));
		nameArea = request.getParameter("name");
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
		listArea = adminService.selAreaAll(request.getParameter("name"),pagenum,5);
		return SUCCESS;
	}
	/**
	 * 删除地区
	 * @return
	 * @throws IOException 
	 */
	public String delArea() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		boolean bool = adminService.delArea(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selAreaAllId();
		}
		else
		{
			selAreaAllId();
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	/**
	 * 跳转进入修改地区
	 * @return
	 */
	public String updaeAreaPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		area = adminService.selAreaId(Integer.valueOf(request.getParameter("id")));
		return SUCCESS;
	}
	/**
	 * 跳转进入添加地区
	 * @return
	 */
	public String addAreaPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		return SUCCESS;
	}
	/**
	 * 更新地区
	 * @return
	 * @throws IOException 
	 */
	public String updateArea() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			
		}
		else
		{
			area.setId(Integer.valueOf(request.getParameter("id")));
		}
		area.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
		area.setAreaName(request.getParameter("areaName"));
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
			"/upadeImage" + "/"+getPictureFileName()));
			
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
		}
		area.setIcon("http://www.tcshenghuo.org:8806/JunKeting"+
				"/upadeImage" + "/"+getPictureFileName());
		boolean bool = adminService.updateArea(area);
		if(bool)
		{
			selAreaAllId();
			alertInFo.alertReturn("上传成功");
		}
		else
		{
			selAreaAllId();
			alertInFo.alertReturn("上传异常");
		}
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}
	@Override
	public Admin getModel() {
		return model;
	}
	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getListUser() {
		return listUser;
	}
	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
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
	public String getNameFeature() {
		return nameFeature;
	}
	public void setNameFeature(String nameFeature) {
		this.nameFeature = nameFeature;
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
	public List<Object> getListFeature() {
		return listFeature;
	}
	public void setListFeature(List<Object> listFeature) {
		this.listFeature = listFeature;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public List<Area> getListArea() {
		return listArea;
	}
	public void setListArea(List<Area> listArea) {
		this.listArea = listArea;
	}
	public Feature getFeature() {
		return feature;
	}
	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	public String getIde() {
		return ide;
	}
	public void setIde(String ide) {
		this.ide = ide;
	}
	public List<Image> getListImage() {
		return listImage;
	}
	public void setListImage(List<Image> listImage) {
		this.listImage = listImage;
	}
	public String getNameArea() {
		return nameArea;
	}
	public void setNameArea(String nameArea) {
		this.nameArea = nameArea;
	}

}
