package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.common.SystemAgrs;
import com.era.orm.Images;
import com.era.orm.Product;
import com.era.orm.Seller;
import com.era.service.ProductService;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>,
ServletRequestAware
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Integer String = null;
	
	private HttpServletRequest request;
	
	private Product model = new Product();
	private Images imges = new Images();
	
	private ProductService productService;
	private List<Object> listProduct;
	private List<Object> listImagesOrr;
	private List<Seller> listSeller;
	private List<Object> listSellers;
	private List<Object> listImage;
	private Map<String,Object> map = new HashMap<String,Object>();
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	
	private Integer sellId;
	
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String nameOne;
	private int ide;
	
	/**
	 * 查询商户产品
	 * @return
	 */
	public String selProductNum()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		int sellerId= (Integer) request.getSession().getAttribute("sellerId");
		pagecount = productService.numProduct(request.getParameter("name"),sellerId);
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
		listProduct = productService.selProduct(request.getParameter("name"),sellerId,pagenum,7);
		return SUCCESS;
	}
	/**
	 * 跳转到产品添加图片
	 * @return
	 */
	public String addProductImagesPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		ide=Integer.valueOf(request.getParameter("id"));
		return SUCCESS;
	}
	/**
	 * 添加产品图片
	 * @return
	 * @throws IOException 
	 */
	public String addtiew() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String idt = null;
		if(request.getParameter("ttt") == null || request.getParameter("ttt").equals(""))
		{
			idt = request.getParameter("id");
		}
		else
		{
			idt = request.getParameter("ttt");
		}
		ide=Integer.valueOf(idt);
		imges.setCompositeId(Integer.valueOf(idt));
		imges.setType(2);
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
					SystemAgrs.uploadPath+request.getSession().getAttribute("userId")+getPictureFileName());
			
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
		}
		imges.setImgUrl("http://api.pymob.cn:8080/City/qrurl/"+request.getSession().getAttribute("userId")+getPictureFileName());
		boolean bool = productService.addImages(imges);
		if(bool)
		{
			alertInFo.alertReturn("添加成功");
		}
		else
		{
			alertInFo.alertReturn("添加异常");
		}
		return SUCCESS;
	}
	/**
	 * 查询产品图片
	 * @return
	 */
	public String selProductImage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		pagecount = productService.numProductImage(Integer.valueOf(request.getParameter("id")),request.getParameter("name"));
		nameOne = request.getParameter("name");
		sellId = Integer.valueOf(request.getParameter("id"));
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
		listImage = productService.selImages(Integer.valueOf(request.getParameter("id")),request.getParameter("name"),pagenum,7);
		return SUCCESS;
	}
	/**
	 * 删除产品
	 * @return
	 * @throws IOException 
	 */
	public String delProduct() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		boolean bool = productService.delProduct(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selProductNum();
		}
		else
		{
			selProductNum();
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 查询商户图片
	 * @return
	 */
	public String selImages()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		Integer sellerId=(Integer) request.getSession().getAttribute("sellerId");
		
		pagecount = productService.numImagesOrr(request.getParameter("name"),sellerId);
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
		listImagesOrr = productService.selImagesOrr(request.getParameter("name"),sellerId,pagenum,5);
		return SUCCESS;
	}
	
	/**
	 * 删除图片
	 * @return
	 * @throws IOException 
	 */
	public String delImages() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		boolean bool = productService.delImages(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			selProductNum();
			
		}
		else
		{
			alertInFo.alertReturn("删除异常");
			selProductNum();
		}
		return SUCCESS;
	}
	/**
	 * 进入添加商户图片
	 * @return
	 */
	public String addImages()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		Integer typeId = (Integer) request.getSession().getAttribute("sellerId");
		listSellers = productService.selSellers(typeId);
		return SUCCESS;
	}
	/**
	 * 进入添加产品
	 * @return
	 */
	public String addProductPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		return SUCCESS;
	}
	/**
	 * 进入修改产品
	 * @return
	 */
	public String updateProductPage()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		if(request.getParameter("id")==null||request.getParameter("id").equals(""))
		{
			sellId = Integer.valueOf(request.getParameter("sellerId"));
		}
		else
		{
			model = productService.selProductId(Integer.valueOf(request.getParameter("id")));
		}
//		String sellerId=(String) request.getSession().getAttribute("sellerId");
//		listSeller = productService.selSellerAll(sellerId);
		return SUCCESS;
	}
	/**
	 * 添加或修改产品
	 * @return
	 * @throws IOException 
	 */
	public String addProduct() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		try {
			String data="";
			Product pr = new Product();
			HttpServletResponse response = ServletActionContext.getResponse();
			if(request.getParameter("id")==null||request.getParameter("id").equals(""))
			{
				pr.setId(null);
			}
			else
			{
				pr.setId(Integer.valueOf(request.getParameter("id")));
			}
			pr.setName(request.getParameter("product"));
			pr.setSeller(Integer.valueOf(request.getParameter("compositeId")));
			sellId = Integer.valueOf(request.getParameter("compositeId"));
			boolean bool = productService.addProduct(pr);
			if(bool)
			{
				data = "{msg:\"1\"}";
				response.getWriter().write(data);
			}
			else
			{
				data = "{msg:\"2\"}";
				response.getWriter().write(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加图片
	 * @return
	 * @throws IOException 
	 */
	public String addImagesPage() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		imges.setCompositeId(Integer.valueOf(request.getParameter("sellerTitle")));
		imges.setType(1);
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		String t = format.format(new Date());
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
			System.out.println(t+getPictureFileName());
			FileOutputStream fos = new FileOutputStream(
					SystemAgrs.uploadPath+request.getSession().getAttribute("userId")+t+getPictureFileName());
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
		}
		imges.setImgUrl("http://api.pymob.cn:8080/City/qrurl/"+request.getSession().getAttribute("userId")+t+getPictureFileName());
		boolean bool = productService.addImages(imges);
		if(bool)
		{
			alertInFo.alertReturn("添加成功");
		}
		else
		{
			alertInFo.alertReturn("添加异常");
		}
		return addImages();
	}
	@Override
	public void setServletRequest(HttpServletRequest request) 
	{
		this.request = request;
	}

	@Override
	public Product getModel() 
	{
		return model;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setModel(Product model) {
		this.model = model;
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

	public List<Object> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Object> listProduct) {
		this.listProduct = listProduct;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Object> getListImagesOrr() {
		return listImagesOrr;
	}

	public void setListImagesOrr(List<Object> listImagesOrr) {
		this.listImagesOrr = listImagesOrr;
	}

	public List<Seller> getListSeller() {
		return listSeller;
	}

	public void setListSeller(List<Seller> listSeller) {
		this.listSeller = listSeller;
	}

	public Images getImges() {
		return imges;
	}

	public void setImges(Images imges) {
		this.imges = imges;
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

	public List<Object> getListImage() {
		return listImage;
	}

	public void setListImage(List<Object> listImage) {
		this.listImage = listImage;
	}

	public int getIde() {
		return ide;
	}

	public void setIde(int ide) {
		this.ide = ide;
	}
	public Integer getSellId() {
		return sellId;
	}
	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}
	public List<Object> getListSellers() {
		return listSellers;
	}
	public void setListSellers(List<Object> listSellers) {
		this.listSellers = listSellers;
	}

}
