package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Activities;
import com.era.orm.City;
import com.era.orm.Product;
import com.era.orm.Recommend;
import com.era.orm.RecommendType;
import com.era.service.CityService;
import com.era.service.ProductService;
import com.era.util.PiaoJuTong;
import com.era.util.alertInFo;
import com.era.util.text;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	private ProductService productService;
	private List<Product> list;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String idOne;
	private int seller;
	private List<Recommend> listRecommend;
	private Recommend recommend = new Recommend();
	private List<RecommendType> listRecommendType;
	Product model = new Product();
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	private CityService cityService;
	private List<City> listCity;
	private List<Activities> listAct;
	
	Map<String,Object> map = new HashMap<String,Object>();
	
	/**
	 * 通过ID查询产品
	 * @return
	 */
	public String selProduct()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		idOne = request.getParameter("seller");
		pagecount = productService.numberProduct(request.getParameter("seller"));
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
		list = productService.selProductId(request.getParameter("seller"),pagenum,15);
		return SUCCESS;
	}
	/**
	 * 查询应用汇
	 * @return
	 */
	public String selRecommend()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		idOne = request.getParameter("name");
		pagecount = productService.selRecommendNum(request.getParameter("name"),city);
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
		listRecommend = productService.selRecommend(request.getParameter("name"),city,pagenum,15);
		return SUCCESS;
	}
	/**
	 * 跳转到添加应用汇
	 * @return
	 */
	public String addRecommendPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			recommend = null;
		}
		else
		{
			recommend = productService.selRecommendId(request.getParameter("id"));
		}
		int city = (Integer) request.getSession().getAttribute("cityId");
		listCity = cityService.getCityById(city);
		listRecommendType = productService.selRecommendType(0,0);
		return SUCCESS;
	}
	/**
	 * 添加应用汇
	 * @return
	 * @throws IOException 
	 */
	public String addRecommend() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		Recommend rec = new Recommend();
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			
		}
		else
		{
			rec.setId(Integer.valueOf(request.getParameter("id")));
		}
		rec.setIsCompany(Integer.parseInt(request.getParameter("isCompany")));
		rec.setBife(request.getParameter("bife"));
		rec.setIfNo(request.getParameter("ifNo"));
		rec.setName(request.getParameter("name"));
		rec.setIsmend(Integer.valueOf(request.getParameter("ismend")));
		rec.setTypeId(Integer.valueOf(request.getParameter("typeId")));
		rec.setAurl(request.getParameter("aurl"));
		rec.setIurl(request.getParameter("iurl"));
//		rec.setCityId(Integer.valueOf(request.getParameter("cityId")));
//		rec.setIsIos(Integer.valueOf(request.getParameter("isIos")));
		if(getPictureFileName() != null)
		{
			String StreamFos = getPictureFileName().substring(getPictureFileName().indexOf("."));
			long picturefis = getPicture().length();
			if (picturefis > 100000) {
				selRecommend();
			alertInFo.alertReturn("你上传的文件过大！");
			return SUCCESS;
			}

			if (!StreamFos.equals(".gif") && !StreamFos.equals(".jpg")
			&& !StreamFos.equals(".bmp")) {
				selRecommend();
			alertInFo.alertReturn("你上传的文件类型不正确，请选择gif,jpg,bmp格式的图片！");
			return SUCCESS;
			}

			FileOutputStream fos = new FileOutputStream(ServletActionContext.getRequest().getRealPath(
					"/qrurl/"+PiaoJuTong.Md5(getPicture())+StreamFos));
			
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
			rec.setLogo("http://api.pymob.cn:8080/City/qrurl/"+PiaoJuTong.Md5(getPicture())+StreamFos);
		}
		boolean bool = productService.addRecommend(rec);
		if(bool)
		{
			selRecommend();
		}
		else
		{
			selRecommend();
			alertInFo.alertReturn("添加失败");
		}
		return SUCCESS;
	}
	
	/**
	 * 删除应用汇
	 * @return
	 * @throws IOException 
	 */
	public String delRecommend() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = productService.delRecommend(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selRecommend();
		}
		else
		{
			selRecommend();
			alertInFo.alertReturn("删除失败");
		}
		return SUCCESS;
	}
	
	/**
	 * 删除产品
	 * @return
	 * @throws IOException 
	 */
	public String delProduct() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = productService.delProduct(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selProduct();
			alertInFo.alertReturn("删除成功");
		}
		else
		{
			selProduct();
			alertInFo.alertReturn("删除失败");
		}
		return SUCCESS;
	}
	
	/**
	 * 添加产品
	 * @return
	 * @throws IOException 
	 */
	public String addProduct() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			model.setId(null);
		}
		else
		{
			model.setId(Integer.valueOf(request.getParameter("id")));
		}
		idOne = request.getParameter("seller");
		model.setName(request.getParameter("name"));
		if(request.getParameter("image") == null || request.getParameter("image").equals(""))
		{
			
		}
		else
		{
			model.setImagesId(Integer.valueOf(request.getParameter("image")));
		}
		model.setSeller(Integer.valueOf(request.getParameter("seller")));
		boolean bool = productService.addProduct(model);
		if(bool)
		{
			selProduct();
			alertInFo.alertReturn("添加或修改成功");
		}
		else
		{
			selProduct();
			alertInFo.alertReturn("添加或修改异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addProductPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		model = null;
		seller = Integer.valueOf(request.getParameter("seller"));
		return SUCCESS;
	}
	
	/**
	 * 跳转到更新页面
	 * @return
	 */
	public String updateProductPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		seller = Integer.valueOf(request.getParameter("seller"));
		model= productService.selProductId(Integer.valueOf(request.getParameter("id")));
		
		return SUCCESS;
	}
	/**
	 * 添加活动
	 * @return
	 */
	public String addActivities()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		
		return SUCCESS;
	}
	/**
	 * 查询活动
	 * @return
	 */
	public String selAct()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		pagecount = productService.numberAct();
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
		listAct = productService.selAct(pagenum,15);
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Product getModel() {
		return model;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
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

	public String getIdOne() {
		return idOne;
	}

	public void setIdOne(String idOne) {
		this.idOne = idOne;
	}

	public int getSeller() {
		return seller;
	}

	public void setSeller(int seller) {
		this.seller = seller;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public List<Recommend> getListRecommend() {
		return listRecommend;
	}
	public Recommend getRecommend() {
		return recommend;
	}
	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
	}
	public void setListRecommend(List<Recommend> listRecommend) {
		this.listRecommend = listRecommend;
	}
	public List<RecommendType> getListRecommendType() {
		return listRecommendType;
	}
	public void setListRecommendType(List<RecommendType> listRecommendType) {
		this.listRecommendType = listRecommendType;
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
	public List<City> getListCity() {
		return listCity;
	}
	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}
	public CityService getCityService() {
		return cityService;
	}
	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}
	public List<Activities> getListAct() {
		return listAct;
	}
	public void setListAct(List<Activities> listAct) {
		this.listAct = listAct;
	}
}
