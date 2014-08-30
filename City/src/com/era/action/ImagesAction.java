package com.era.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Images;
import com.era.service.ImagesService;
import com.era.util.alertInFo;
import com.era.util.text;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ImagesAction extends ActionSupport implements ModelDriven<Images>,
ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private ImagesService imagesService;
	private List<Images> list;
	
	private int typeOne;
	private int comOne;
	private File picture;
	private String pictureContentType;
	private String pictureFileName;
	
	Images model = new Images();
	
	/**
	 * 查询所有的图片
	 * @return
	 */
	public String selImage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		pagecount = imagesService.numberImage(request.getParameter("id"),request.getParameter("type"));
		if(request.getParameter("id") == null || request.getParameter("id").equals(""))
		{
			
		}
		else
		{
			typeOne = Integer.valueOf(request.getParameter("type"));
			comOne = Integer.valueOf(request.getParameter("id"));
		}
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
		list = imagesService.selImage(request.getParameter("id"),request.getParameter("type"),pagenum,15);
		return SUCCESS;
	}
	
	/**
	 * 通过ID删除图片
	 * @return
	 * @throws IOException 
	 */
	public String delImages() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		boolean bool = imagesService.delImages(Integer.valueOf(request.getParameter("ide")));
		if(bool)
		{
			selImage();
			alertInFo.alertReturn("删除成功");
		}
		else
		{
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 添加图片
	 * @return
	 * @throws IOException 
	 */
	public String addImages() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		Images images = new Images();
		images.setCompositeId(Integer.valueOf(request.getParameter("com")));
		images.setType(Integer.valueOf(request.getParameter("type")));
		typeOne = Integer.valueOf(request.getParameter("type"));
		comOne = Integer.valueOf(request.getParameter("com"));
		String sort = request.getParameter("sort");
		if( null != sort && !"".equals(sort)){
			images.setSort(Integer.parseInt(sort));
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
			&& !StreamFos.equals(".bmp") && !StreamFos.equals(".GIF") && !StreamFos.equals(".JPG")
			&& !StreamFos.equals(".BMP") && !StreamFos.equals(".png") && !StreamFos.equals(".PNG")) {
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
		}
		
		images.setImgUrl("http://api.pymob.cn:8080/City/qrurl/"+getPictureFileName());
		text.pressImage("D://tomcat//webapps//City//images//00.png","D://tomcat//webapps//City//qrurl//"+getPictureFileName(), 300, 400,0.6f);
		boolean bool = imagesService.addImages(images);
		if(bool)
		{
			selImage();
			alertInFo.alertReturn("添加成功");
		}
		else
		{
			alertInFo.alertReturn("添加异常");
		}
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	public String addImagesPage()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		typeOne = Integer.valueOf(request.getParameter("type"));
		comOne = Integer.valueOf(request.getParameter("id"));
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	@Override
	public Images getModel() {
		return model;
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

	public ImagesService getImagesService() {
		return imagesService;
	}

	public void setImagesService(ImagesService imagesService) {
		this.imagesService = imagesService;
	}

	public List<Images> getList() {
		return list;
	}

	public void setList(List<Images> list) {
		this.list = list;
	}

	public int getTypeOne() {
		return typeOne;
	}

	public void setTypeOne(int typeOne) {
		this.typeOne = typeOne;
	}

	public int getComOne() {
		return comOne;
	}

	public void setComOne(int comOne) {
		this.comOne = comOne;
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
