package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	private List<Object> listImage;
	private Map<String,Object> map = new HashMap<String,Object>();
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	
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
		String sellerId=(String) request.getSession().getAttribute("sellerId");
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
	public String addProductImages() throws IOException
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		ide=Integer.valueOf(request.getParameter("id"));
		imges.setCompositeId(Integer.valueOf(request.getParameter("id")));
		imges.setType(2);
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
				alertInFo.alertReturn("你上传格式不对请上传Gig,Jpg,Bmp的格式！");
			return SUCCESS;
			}

			String md5Code = "";
			md5Code = com.era.util.CommonUtil.Md5(picture);
			
			FileOutputStream fos = new FileOutputStream(
					SystemAgrs.uploadPath+md5Code+pictureFileName.substring(pictureFileName.lastIndexOf(".")));
			
			imges.setImgUrl("http://tcshenghuo.org/City/qrurl/"+md5Code+pictureFileName.substring(pictureFileName.lastIndexOf(".")));
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
		}
		boolean bool = productService.addImages(imges);
		String sellerId=(String) request.getSession().getAttribute("sellerId");
		if(bool)
		{
			listSeller = productService.selSellerAll(sellerId);
			alertInFo.alertReturn("添加成功");
		}
		else
		{
			listSeller = productService.selSellerAll(sellerId);
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
		ide =Integer.valueOf(request.getParameter("id"));
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
		String sellerId=(String) request.getSession().getAttribute("sellerId");
		pagecount = productService.numImagesOrr(request.getParameter("name"),sellerId);
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
		listImagesOrr = productService.selImagesOrr(request.getParameter("name"),sellerId,pagenum,7);
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
			selImages();
		}
		else
		{
			alertInFo.alertReturn("删除异常");
			selImages();
		}
		return SUCCESS;
	}
	/**
	 * 进入添加图片
	 * @return
	 */
	public String addImages()
	{
		if(request.getSession().getAttribute("userId") == null)
		{
			return "error";
		}
		String sellerId=(String) request.getSession().getAttribute("sellerId");
		listSeller = productService.selSellerAll(sellerId);
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
		String sellerId=(String) request.getSession().getAttribute("sellerId");
		listSeller = productService.selSellerAll(sellerId);
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
		model = productService.selProductId(Integer.valueOf(request.getParameter("id")));
		String sellerId=(String) request.getSession().getAttribute("sellerId");
		listSeller = productService.selSellerAll(sellerId);
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
		if(request.getParameter("id")==null||request.getParameter("id").equals(""))
		{
			model.setId(null);
		}
		else
		{
			model.setId(Integer.valueOf(request.getParameter("id")));
		}
		model.setName(request.getParameter("product"));
		model.setSeller(Integer.valueOf(request.getParameter("compositeId")));
		boolean bool = productService.addProduct(model);
		String sellerId=(String) request.getSession().getAttribute("sellerId");
		listSeller = productService.selSellerAll(sellerId);
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
		String sellerId=(String) request.getSession().getAttribute("sellerId");
		imges.setCompositeId(Integer.valueOf(request.getParameter("compositeId")));
		imges.setType(1);
		if(getPictureFileName() != null)
		{
			String StreamFos = getPictureFileName().substring(getPictureFileName().indexOf("."));
			long picturefis = getPicture().length();
			if (picturefis > 100000) {
				listSeller = productService.selSellerAll(sellerId);
				alertInFo.alertReturn("你上传的文件过大！");
				return SUCCESS;
			}
			
			if (!StreamFos.equals(".gif") && !StreamFos.equals(".jpg")
			&& !StreamFos.equals(".bmp")) {
				listSeller = productService.selSellerAll(sellerId);
				alertInFo.alertReturn("你上传格式不对请上传Gig,Jpg,Bmp的格式！");
			return SUCCESS;
			}
			System.out.println(getPictureFileName());
			String md5Code = "";
			md5Code = com.era.util.CommonUtil.Md5(picture);
			
			FileOutputStream fos = new FileOutputStream(
					SystemAgrs.uploadPath+md5Code+pictureFileName.substring(pictureFileName.lastIndexOf(".")));
			
			imges.setImgUrl("http://tcshenghuo.org/City/qrurl/"+md5Code+pictureFileName.substring(pictureFileName.lastIndexOf(".")));
			
			FileInputStream fis = new FileInputStream(getPicture());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
					}
		}
		boolean bool = productService.addImages(imges);
		listSeller = productService.selSellerAll(sellerId);
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

}
