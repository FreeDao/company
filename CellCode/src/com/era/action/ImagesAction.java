package com.era.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Images;
import com.era.service.ImagesService;
import com.era.util.alertInFo;
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
				pagenum = pagecount;
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
		boolean bool = imagesService.delImages(Integer.valueOf(request.getParameter("id")));
		if(bool)
		{
			alertInFo.alertReturn("删除成功");
		}
		else
		{
			alertInFo.alertReturn("删除异常");
		}
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

}
