package com.marck.in.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.DateUtil;
import com.marck.common.model.Img;
import com.marck.common.model.User;
import com.marck.in.service.ImgService;

@Component("imgAction")
@Scope("prototype")
public class ImgAction extends BaseAction{

	@Autowired
	private ImgService imgService;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	private Img img;
	private Integer pageNo;
	private Integer pageNum;
	
	private File file;
	private String fileContentType;
	private String fileFileName;
	
	/**
	 * 获取图片集合
	 */
	public String list(){
		try {
			
			if(null == img  || CommonUtil.validParams(img.getPid(),img.getMenuId())){
				map.put("msg", "缺少必传参数");
				map.put("code", 0);
				return SUCCESS;
			}
			
			imgService.setImgList(img,map,pageNo,pageNum);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询图片列表异常",e);
			map.put("msg", "查询异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 后台图片上传
	 * @return
	 */
	public String upload(){
		try {
			
			String filepath=request.getRealPath("/"+tempFolder);
			String brief = request.getParameter("imgBrief");
			String fileId = request.getParameter("fileId");
			
			List<Img> imgs = (List<Img>) request.getSession().getAttribute("tempImg");
			Integer menuId = (Integer) request.getSession().getAttribute("menuId");
			
			imgs = imgService.uploadImg(file,fileFileName,filepath,brief,fileId,imgs,menuId,map,projectUrl,tempFolder);
			
			request.getSession().setAttribute("tempImg", imgs);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("上传图片异常",e);
			map.put("msg", "上传异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	/**
	 * 后台删除临时图片
	 * @return
	 */
	public String delTemp(){
		try {
			String fileId = request.getParameter("fileId");
			String imgId = request.getParameter("imgId");
			User u = (User) request.getSession().getAttribute("userSession");
			String filepath=request.getRealPath("/"+uploadFolder);
			
			if( null == u){
				map.put("code", 0);
				return SUCCESS;
			}
			
			if( !CommonUtil.validParams(imgId)){
				imgService.delImg(Integer.parseInt(imgId),filepath,uploadFolder);
				map.put("code", 2);
				return SUCCESS;
			}
			
			List<Img> imgs = (List<Img>) request.getSession().getAttribute("tempImg");
			
			if( null == imgs){
				map.put("code", 1);
				return SUCCESS;
			}
			
			Iterator<Img> img = imgs.iterator();
			
			while(img.hasNext()){
				Img i = img.next();
				
				if(i.getFileId().equals(fileId)){
					img.remove();
				}
			}
			
			request.getSession().setAttribute("tempImg", imgs);
			map.put("code", 1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("删除临时图片异常",e);
			map.put("msg", "删除临时图片异常");
			map.put("code", 0);
		}
		return SUCCESS;
	}
	
	public Integer getPageNo() {
		return pageNo;
	
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageNum() {
		return pageNum;
	
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Map<String, Object> getMap() {
		return map;//null == map?"":map;
	
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Img getImg() {
		return img;
	
	}

	public void setImg(Img img) {
		this.img = img;
	}

	public File getFile() {
		return file;
	
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


}
