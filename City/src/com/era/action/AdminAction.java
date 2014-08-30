package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;


import com.era.orm.Admin;
import com.era.orm.City;
import com.era.orm.Convenient;
import com.era.orm.Roles;
import com.era.service.AdminService;
import com.era.service.PopleService;
import com.era.service.ProductService;
import com.era.service.RolesService;
import com.era.util.BaseUtils;
import com.era.util.PiaoJuTong;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminAction extends ActionSupport implements ModelDriven<Admin>,
ServletRequestAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	              
	HttpServletRequest request;
	AdminService adminService;
	RolesService rolesService;
	List<Admin> list;
	List<Roles> listRoles;
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	public Map<String,Object> map = new HashMap<String, Object>();
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private ProductService productService;
	private PopleService popleService;
	private List<City> listCity;
	
	Admin model = new Admin();
	Roles roles = new Roles();
	private Convenient con = new Convenient();
	PopleAction ion = new PopleAction();
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
	 * 登陆
	 * @return
	 * @throws IOException
	 */
	public String adminLogin() throws IOException
	{
		try 
		{
			if(request.getSession().getAttribute("userid") == null)
			{
				if(request.getParameter("name") == null || request.getParameter("pwd") == null)
				{
					map.put("msg", "用户名和密码为空请您重新输入!");
					return SUCCESS;
				}
				else
				{
					model =adminService.adminLogin(request.getParameter("name"),PiaoJuTong.Md5(request.getParameter("pwd")));
					if(model == null)
					{
						map.put("msg", "用户名密码错误请重新出入!");
						return SUCCESS;
					}
					roles = rolesService.selRolesById(model.getRoles());
					if(roles == null)
					{
						map.put("msg", "未赋予权限请联系管理员!");
						return SUCCESS;
					}
					request.getSession().setAttribute("roleAction",roles.getRolesAction());
					request.getSession().setAttribute("userid", model.getId());
					request.getSession().setAttribute("userName", model.getNickName());
					request.getSession().setAttribute("super", model.getIsSuper());
					request.getSession().setAttribute("cityId", model.getCityId());
					map.put("msg", "登陆成功");
				}
			}
			else
			{
				map.put("msg", "登陆成功");
			}
		} catch (Exception e) {
			map.put("msg", "登陆异常!");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到主页
	 * @return
	 */
	public String pagesLogin()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		return SUCCESS;
	}
	
	/**
	 * 查询管理员
	 * @return
	 */
	public String selAdmin()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		pagecount = adminService.numberAdmin(city);
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
		list = adminService.selAdmin(city,pagenum,15);
		return SUCCESS;
	}
	
	/**
	 * 删除管理员
	 * @return
	 * @throws IOException 
	 */
	public String delAdmin() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		
		model = adminService.selIdAdmin(Integer.valueOf(request.getParameter("id")));
		if(model == null)
		{
			alertInFo.alertReturn("删除异常！");
			selAdmin();
			return SUCCESS;
		}
		if(model.getIsSuper()==1)
		{
			alertInFo.alertReturn("您不能删除超级管理员");
			selAdmin();
			return SUCCESS;
		}
		boolean bool = adminService.delAdmin(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selAdmin();
		}
		else
		{
			alertInFo.alertReturn("删除失败！");
		}
		return SUCCESS;
	}
	
	/**
	 * 添加管理员
	 * @return
	 * @throws IOException 
	 */
	public String addAdmin() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		String data = "";
		try {
			List<Admin> list = adminService.selAdminLogin(request.getParameter("name"));
			if(list.size()>0)
			{
				data = "{mess:\"1\"}";
			}
			model.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			model.setAdminName(request.getParameter("name"));
			model.setEmail(request.getParameter("email"));
			model.setIsSuper(Integer.valueOf(request.getParameter("issuper")));
			model.setNickName(request.getParameter("nickname"));
			model.setPassword(PiaoJuTong.Md5(request.getParameter("password")));
			model.setPhone(request.getParameter("phone"));
			model.setQq(request.getParameter("qq"));
			model.setRoles(Integer.valueOf(request.getParameter("systemid")));
			System.out.println(request.getParameter("cityId"));
			model.setCityId(Integer.valueOf(request.getParameter("cityId")));
			boolean bool = adminService.addAdmin(model);
			if(bool)
			{
				data = "{mess:\"2\"}";
			}
			else
			{
				data = "{mess:\"3\"}";
			}
		} catch (Exception e) {
			data = "{mess:\"3\"}";
			e.printStackTrace();
		}
		response.getWriter().write(data);
		return null;
	}
	
	/**
	 * 跳转到添加管理员
	 * @return
	 */
	public String addPages()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		listRoles = rolesService.allRoles();
		listCity = adminService.selCity();
		return SUCCESS;
	}
	
	/**
	 * 跳转到修改密码
	 */
	public String updatePassWordPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		return SUCCESS;
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws IOException 
	 */
	public String updatePassWord() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		else
		{
			int suserid = (Integer) request.getSession().getAttribute("userid");
			model = adminService.selIdAdmin(suserid);
			if(!model.getPassword().equals(PiaoJuTong.Md5(request.getParameter("opasswordjiu"))))
			{
				alertInFo.alertReturn("密码不正确!");
				return SUCCESS;
			}
			else
			{
				boolean bool = adminService.updatePassWord(PiaoJuTong.Md5(request.getParameter("npassword")), 
						suserid);
				if(bool)
				{
					alertInFo.alertReturn("修改密码成功!");
					return SUCCESS;
				}
				else
				{
					alertInFo.alertReturn("修改密码失败!");
					return SUCCESS;
				}
			}
		}
	}
	
	/**
	 * 添加便民
	 * @return
	 * @throws IOException 
	 */
	public String addConvenientId() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			con.setId(null);
		}
		else
		{
			con.setId(Integer.valueOf(request.getParameter("id")));
		}
		con.setName(request.getParameter("adminName"));
		con.setCityId(Integer.valueOf(request.getParameter("city")));
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
			FileOutputStream fos = new FileOutputStream("/home/pyxx/cityImg/" + "/"+getPictureFileName());
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
		}
		con.setImgUrl("http://171.34.32.126:8080/City/images/"+getPictureFileName());
		boolean bool = popleService.addPople(con);
		if(bool)
		{
			alertInFo.alertReturn("添加成功！");
		}
		else
		{
			alertInFo.alertReturn("添加成功！");
		}
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	@Override
	public Admin getModel()
	{
		return model;
	}
	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public RolesService getRolesService() {
		return rolesService;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public void setRolesService(RolesService rolesService) {
		this.rolesService = rolesService;
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
	public List<Admin> getList() {
		return list;
	}
	public void setList(List<Admin> list) {
		this.list = list;
	}
	public List<Roles> getListRoles() {
		return listRoles;
	}
	public void setListRoles(List<Roles> listRoles) {
		this.listRoles = listRoles;
	}
	public Convenient getCon() {
		return con;
	}
	public void setCon(Convenient con) {
		this.con = con;
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
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public PopleService getPopleService() {
		return popleService;
	}
	public void setPopleService(PopleService popleService) {
		this.popleService = popleService;
	}
	public PopleAction getIon() {
		return ion;
	}
	public void setIon(PopleAction ion) {
		this.ion = ion;
	}
	public List<City> getListCity() {
		return listCity;
	}
	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}
}
