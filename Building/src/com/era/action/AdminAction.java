package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Admin;
import com.era.orm.Collect;
import com.era.orm.Image;
import com.era.orm.Information;
import com.era.orm.Regionallocations;
import com.era.orm.Seller;
import com.era.orm.SupplyAndDemand;
import com.era.orm.Type;
import com.era.orm.User;
import com.era.service.AdminService;
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
	private HttpServletRequest request;
	private Admin model = new Admin();
	private Seller seller;
	private SupplyAndDemand supply;
	private AdminService adminService;
	private User user = new User();
	private List<User> listUser;
	private int pagenum;
	private String nameArea;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String nameOne;
	private String goods;
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	private String ide;
	private List<Image> listImage;
	private List<Object> area_list;
	private List<Object> city_list;
	private List<Collect> Collect_list;
	private List<Information> information_list;
	private List<Type> type_list;
	private List<Object> type_list1;
	private List<Object> seller_list1;
	private List<Object> user_list;
	private List<SupplyAndDemand> list_supply;
	@SuppressWarnings("rawtypes")
	private List seller_list;
	private int flag = 0;
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
			alertInFo.alertReturn("删除成功");
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
	 * 查询所有的地区
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String selArea() throws UnsupportedEncodingException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String areaName=request.getParameter("area");
		if(areaName!=null)
		{
			if(BaseUtils.isChinaese(areaName))
			{
				areaName = new String(areaName.getBytes("ISO-8859-1"),"UTF-8");
			}
		}
		try 
		{
			pagecount = adminService.numberArea();
			if (pagecount < 15)
			{
				pageCount = 1;
			}else 
			{
				pageCount = pagecount / 15;
				int i = pagecount % 15;
				if (i > 0) 
				{
					pageCount += 1;
				}
			}
			if (pagenum < 1) 
			{
				pagenum = 1;
			}
			if (pagenum > pagecount) 
			{
				if (pagecount == 0)
				{
					
				} 
				else 
				{
					pagenum = pageCount;
				}
			}
			area_list =adminService.selArea(areaName, pagenum, 15);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(area_list!=null)
		{
			return SUCCESS;
		}
		return ERROR;
	}
	/**
	 * 删除地区
	 * @return
	 */
	public String delArea()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String id_str = request.getParameter("areaId");
		try 
		{
			boolean bool=false;
			if(id_str!=null)
			{
				bool = adminService.delArea(Integer.parseInt(id_str));
			}
			if(bool)
			{
				alertInFo.alertReturn("删除成功");
				selArea();
				return SUCCESS;
			}
			else
			{
				alertInFo.alertReturn("删除异常");
				return "error";
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
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
		city_list = adminService.selCity();
		return SUCCESS;
	}
	
	/**
	 * 添加区域
	 * @return
	 * @throws IOException
	 */
	public String addArea() throws IOException
	{
		Regionallocations area = new Regionallocations();
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String areaName = request.getParameter("areaName");
		String cityId = request.getParameter("city");
		area.setAreaRegion(areaName);
		area.setAreaId(Integer.valueOf(cityId));
		boolean bool = adminService.isArea(areaName);
		if(bool)
		{
			alertInFo.alertReturn("该地区已经存在");
			selArea();
			return "error";
		}else
		{
			adminService.addArea(area);
			alertInFo.alertReturn("添加成功");
			selArea();
			return SUCCESS;
		}
	}
	
	/**
	 * 查询收藏
	 * @return
	 */
	public String selCollect() 
	{
		if (request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String id = request.getParameter("id");
		pagecount = adminService.numberComment();
		if (pagecount < 7) 
		{
			pageCount = 1;
		} else 
		{
			pageCount = pagecount / 7;
			int i = pagecount % 7;
			if (i > 0) 
			{
				pageCount += 1;
			}
		}
		if (pagenum < 1) 
		{
			pagenum = 1;
		}
		if (pagenum > pagecount) 
		{
			if (pagecount == 0) 
			{
			} else 
			{
				pagenum = pageCount;
			}
		}
		if(flag == 0)
		{
			Collect_list = adminService.selComment(id, pagenum, 7);
		}else
		{
			Collect_list = adminService.selComment("", pagenum, 7);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除收藏
	 * @return
	 */
	public String delCollect() 
	{
		if (request.getSession().getAttribute("userId") == null) 
		{
			return "error";
		}
		try {
			boolean bool = adminService.delComment(Integer.valueOf(request.getParameter("id")));
			if (bool) 
			{
				alertInFo.alertReturn("删除成功!");
				flag = 1;
				selCollect();
				return SUCCESS;
			} else {
				alertInFo.alertReturn("删除失败!");
				flag = 1;
				selCollect();
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	/**
	 * 查询资讯
	 * @return
	 */
	public String selInformation() 
	{
		if (request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String title = request.getParameter("title");
		pagecount = adminService.numberInformation();
		if (pagecount < 2) 
		{
			pageCount = 1;
		} else 
		{
			pageCount = pagecount / 2;
			int i = pagecount % 2;
			if (i > 0) 
			{
				pageCount += 1;
			}
		}
		if (pagenum < 1) 
		{
			pagenum = 1;
		}
		if (pagenum > pagecount) 
		{
			if (pagecount == 0) 
			{
			} else 
			{
				pagenum = pageCount;
			}
		}
		if(flag == 0)
		{
			information_list = adminService.selInformation(title, pagenum, 2);
		}else
		{
			information_list = adminService.selInformation("", pagenum, 2);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除资讯
	 * @return
	 */
	public String delInformation() 
	{
		if (request.getSession().getAttribute("userId") == null) 
		{
			return "error";
		}
		try {
			boolean bool = adminService.delInformation(Integer.valueOf(request.getParameter("id")));
			if (bool) 
			{
				alertInFo.alertReturn("删除成功!");
				flag = 1;
				selInformation();
				return SUCCESS;
			} else {
				alertInFo.alertReturn("删除失败!");
				flag = 1;
				selInformation();
				return ERROR;
			}
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	public String addInformation() throws IOException
	{
		Information information = new Information();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String review = request.getParameter("abstractInfo");
		
		Date today=new Date();
		SimpleDateFormat fe=new SimpleDateFormat("hhmmss");
		String time=fe.format(today);
		
		if(getPictureFileName() != null)
		{
			String StreamFos = getPictureFileName().substring(getPictureFileName().indexOf("."));
			long picturefis = getPicture().length();
			if (picturefis > 100000) 
			{
				alertInFo.alertReturn("你上传的文件过大！");
				return SUCCESS;
			}
			if (!StreamFos.equals(".gif") && !StreamFos.equals(".jpg")
			&& !StreamFos.equals(".bmp")) 
			{
				alertInFo.alertReturn("你上传的文件过大！");
				return SUCCESS;
			}
			@SuppressWarnings("deprecation")
			FileOutputStream fos = new FileOutputStream(ServletActionContext.getRequest().getRealPath(
			"/images" + "/"+ time +getPictureFileName()));
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) 
			{
				fos.write(buffer, 0, len);
			}
		}
		
		information.setTitle(title);
		information.setComment(content);
		information.setReview(review);
		information.setLogo("http://www.tcshenghuo.org:8806/Building/images/"+time+getPictureFileName());
		
		Boolean bool = adminService.addInformation(information);
		
		if(bool)
		{
			alertInFo.alertReturn("添加成功!");
			flag = 1;
			selInformation();
			return SUCCESS;
		}else
		{
			alertInFo.alertReturn("添加失败!");
			selInformation();
			return ERROR;
		}
	}
	
	public String selType() throws UnsupportedEncodingException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String name=request.getParameter("name");
		if(name!=null)
		{
			if(BaseUtils.isChinaese(name))
			{
				name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
			}
		}
		try 
		{
			pagecount = adminService.numberType();
			if (pagecount < 15)
			{
				pageCount = 1;
			}else 
			{
				pageCount = pagecount / 15;
				int i = pagecount % 15;
				if (i > 0) 
				{
					pageCount += 1;
				}
			}
			if (pagenum < 1) 
			{
				pagenum = 1;
			}
			if (pagenum > pagecount) 
			{
				if (pagecount == 0)
				{
					
				} 
				else 
				{
					pagenum = pageCount;
				}
			}
			type_list =adminService.selType(name, pagenum, 15);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if(type_list!=null)
		{
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String delType() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		boolean bool = adminService.delType(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
			flag = 1;
			selType();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
			flag = 1;
			selType();
		}
		return SUCCESS;
	}
	
	public String addType() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String typeName = request.getParameter("typeName");
		Type type = new Type();
		type.setName(typeName);
		boolean bool = adminService.addType(type);
		if(bool)
		{
			alertInFo.alertReturn("添加成功！");
			flag = 1;
			selType();
		}else{
			alertInFo.alertReturn("添加失败！");
			flag = 1;
			selType();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加更多图片
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
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
		image.setImgUrl("http://www.tcshenghuo.org:8806/Building"+
				"/upadeImage" + "/"+getPictureFileName());
		boolean bool = adminService.updateImage(image);
		if(bool)
		{
			alertInFo.alertReturn("上传成功");
			flag = 1;
			selImageAll();
		}
		else
		{
			alertInFo.alertReturn("上传异常");
			flag = 1;
			selImageAll();
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
		if(flag == 0)
		{
			listImage = adminService.selImageAll(request.getParameter("id"),pagenum,5);
		}
		else
		{
			listImage = adminService.selImageAll("",pagenum,5);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除图片
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
			alertInFo.alertReturn("删除成功");
			flag = 1;
			selImageAll();
		}
		else
		{
			flag = 1;
			alertInFo.alertReturn("删除异常");
			selImageAll();
		}
		return SUCCESS;
	}
	
	
	public String selSeller()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		pagecount = adminService.numSeller(request.getParameter("sellerName"));
		if(pagecount<4){
			pageCount=1;
		}else{
			pageCount = pagecount/4;
			int i  = pagecount%4;
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
		if(flag == 0)
		{
			seller_list = adminService.selSeller(request.getParameter("sellerName"),pagenum,4);
		}
		else
		{
			seller_list = adminService.selSeller("",pagenum,4);
		}
		return SUCCESS;
	}
	
	public String delSeller() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		boolean bool = adminService.delSeller(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
			flag = 1;
			selSeller();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
			flag = 1;
			selSeller();
		}
		return SUCCESS;
	}
	public String upadeSellerPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String id = request.getParameter("id");
		seller = adminService.selSellerById(id);
		city_list = adminService.selCity();
		type_list1 = adminService.selType();
		if(seller == null)
		{
			return ERROR;
		}
		return SUCCESS;
	}
	@SuppressWarnings("deprecation")
	public String addSeller() throws IOException
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
		String sellerName = request.getParameter("sellerName");
		String cityId = request.getParameter("city");
		String typeId = request.getParameter("type");
		String userId = request.getParameter("userId");
		String content = request.getParameter("content");
		String address = request.getParameter("address");
		String sellerOwner = request.getParameter("sellerOwner");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		Seller seller = new Seller();
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			seller.setId(null);
		}else
		{
			seller.setId(Integer.valueOf(request.getParameter("id")));
		}
		seller.setSellerName(sellerName);
		seller.setCityId(Integer.valueOf(cityId));
		seller.setTypeId(Integer.valueOf(typeId));
		seller.setContent(content);
		seller.setAddress(address);
		seller.setSellerOwner(sellerOwner);
		seller.setPhone(phone);
		seller.setEmail(email);
		seller.setUserId(Integer.valueOf(userId));
		seller.setImgUrl("http://www.tcshenghuo.org:8806/Building"+
				"/upadeImage" + "/"+getPictureFileName());
		boolean bool = adminService.addSeller(seller);
		if(bool)
		{
			alertInFo.alertReturn("成功");
		}else{
			alertInFo.alertReturn("失败");
		}
		return SUCCESS;
	}
	
	public String addSellerPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		city_list = adminService.selCity();
		type_list1 = adminService.selType();
		user_list = adminService.selUser();
		return SUCCESS;
	}
	
	public String addSupplyPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		} 
		city_list = adminService.selCity();
		type_list1 = adminService.selType();
		user_list = adminService.selUser();
		seller_list1 = adminService.selSeller();
		return SUCCESS;
	}
	
	public String updateSupplyPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String id = request.getParameter("id");
		city_list = adminService.selCity();
		type_list1 = adminService.selType();
		supply = adminService.selSupplyById(id);
		user_list = adminService.selUser();
		seller_list1 = adminService.selSeller();
		return SUCCESS;
	}
	
	public String delSupply() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		boolean bool = adminService.delSupply(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
			flag = 1;
			selSupply();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
			flag = 1;
			selSupply();
		}
		return SUCCESS;
	}
	
	
	public String selSupply()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		pagecount = adminService.numSupply(request.getParameter("title"));
		if(pagecount<2){
			pageCount=1;
		}else{
			pageCount = pagecount/2;
			int i  = pagecount%2;
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
		if(flag == 0)
		{
			list_supply = adminService.selSupply(request.getParameter("title"),pagenum,2);
		}else
		{
			list_supply = adminService.selSupply("",pagenum,2);
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	public String addSupply() throws IOException
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
			alertInFo.alertReturn("你上传的文件类型不对！");
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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String price = request.getParameter("price");
		String address = request.getParameter("address");
		String cityId = request.getParameter("city");
		String typeId = request.getParameter("typeId");
		String statue = request.getParameter("statue");
		String sellerId = request.getParameter("sellerId");
		String orderNum = request.getParameter("orderNum");
		String type = request.getParameter("type");
		String matureTime = request.getParameter("matureTime");
		String productcontent = request.getParameter("productcontent");
		String buyNum = request.getParameter("buyNum");
		Seller seller = adminService.selSellerById(sellerId);
		User user = adminService.selUserId(seller.getUserId());
		SupplyAndDemand supply = new SupplyAndDemand();
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			supply.setId(null);
		}else
		{
			supply.setId(Integer.valueOf(request.getParameter("id")));
		}
		supply.setTitle(title);
		supply.setContent(content);
		supply.setPrice(price);
		supply.setAddress(address);
		supply.setCityId(Integer.valueOf(cityId));
		supply.setTypeId(Integer.valueOf(typeId));
		if( null == statue || "".equals(statue)){
			supply.setStatue(0);
		}else{
			supply.setStatue(Integer.valueOf(statue));
		}
		supply.setOrderNum(orderNum);
		supply.setBuyNum(buyNum);
		supply.setType(Integer.valueOf(type));
		supply.setMatureTime(matureTime);
		supply.setProductcontent(productcontent);
		supply.setLogo("http://api.tcshenghuo.org:8806/Building"+
				"/upadeImage" + "/"+getPictureFileName());
		supply.setCorporate(seller.getSellerName());
		supply.setInfo(user.getQq());
		supply.setPhone(seller.getPhone());
		supply.setQq("");
		supply.setEmail(seller.getEmail());
		supply.setUserId(user.getId());
		supply.setIsfinish(1);
		supply.setSellerId(Integer.valueOf(sellerId));
		
		boolean bool = adminService.addSupply(supply);
		if(bool)
		{
			alertInFo.alertReturn("成功");
		}else{
			alertInFo.alertReturn("失败");
		}
		return SUCCESS;
	}
	
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
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
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
	public void setModel(Admin model) {
		this.model = model;
	}
	public List<Object> getArea_list() {
		return area_list;
	}
	public void setArea_list(List<Object> area_list) {
		this.area_list = area_list;
	}
	public List<Object> getCity_list() {
		return city_list;
	}
	public void setCity_list(List<Object> city_list) {
		this.city_list = city_list;
	}
	public List<Collect> getCollect_list() {
		return Collect_list;
	}
	public void setCollect_list(List<Collect> collect_list) {
		Collect_list = collect_list;
	}
	public List<Information> getInformation_list() {
		return information_list;
	}
	public void setInformation_list(List<Information> information_list) {
		this.information_list = information_list;
	}
	public List<Type> getType_list() {
		return type_list;
	}
	public void setType_list(List<Type> type_list) {
		this.type_list = type_list;
	}
	public String getNameArea() {
		return nameArea;
	}
	public void setNameArea(String nameArea) {
		this.nameArea = nameArea;
	}
	@SuppressWarnings("rawtypes")
	public List getSeller_list() {
		return seller_list;
	}
	@SuppressWarnings("rawtypes")
	public void setSeller_list(List seller_list) {
		this.seller_list = seller_list;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public List<Object> getType_list1() {
		return type_list1;
	}
	public void setType_list1(List<Object> type_list1) {
		this.type_list1 = type_list1;
	}
	public List<Object> getSeller_list1() {
		return seller_list1;
	}
	public void setSeller_list1(List<Object> seller_list1) {
		this.seller_list1 = seller_list1;
	}
	public List<Object> getUser_list() {
		return user_list;
	}
	public void setUser_list(List<Object> user_list) {
		this.user_list = user_list;
	}
	public SupplyAndDemand getSupply() {
		return supply;
	}
	public void setSupply(SupplyAndDemand supply) {
		this.supply = supply;
	}
	public List<SupplyAndDemand> getList_supply() {
		return list_supply;
	}
	public void setList_supply(List<SupplyAndDemand> list_supply) {
		this.list_supply = list_supply;
	}
}
