package com.marck.bg.action;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.bg.service.SellerService;
import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.PageUtil;
import com.marck.common.model.Img;
import com.marck.common.model.Region;
import com.marck.common.model.Seller;
import com.marck.common.model.SystemAgrs;
import com.marck.common.model.Type;
import com.marck.common.model.User;

@Component("bgSellerAction")
@Scope("prototype")
public class SellerAction extends BaseAction {

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private String queryValue;
	
	private Seller seller;
	
	@Autowired
	private SellerService sellerService;

	private File img;
	private String imgContentType;
	private String imgFileName;
	
	/**
	 * 查询商家列表 
	 * @return
	 */
	public String selSellerList(){
		try{
			String resUrl = request.getRequestURI();
			Integer menuId = CommonUtil.validParams(request.getParameter("menuId")) ? (Integer)request.getSession().getAttribute("menuId"): Integer.parseInt(request.getParameter("menuId"));
			request.getSession().setAttribute("url", resUrl.substring(resUrl.indexOf("/",1)+1)+"?");
			request.getSession().setAttribute("menuId", menuId);
			pu = sellerService.getSellerList(menuId,queryValue,pageNow,limit);
			return "selSellerList";
		}catch (Exception e) {
			// TODO: handle exception
			log.error("查询商家列表出错！",e);
		}
		return ERROR;
	}

	/**
	 * 跳转到商家添加修改页面
	 * @return
	 */
	public String goAddOrUpdateSeller(){
		try {
			List<Img> imgs = new ArrayList<Img>();
			Integer menuId = (Integer) request.getSession().getAttribute("menuId");
			if( CommonUtil.validParams(seller) ){
				request.setAttribute("title", "添加商家");
			}else{
				imgs = sellerService.getImgs(seller,imgs,menuId);
				seller = sellerService.getSeller(seller);
				request.setAttribute("title", "修改商家");
			}
			SystemAgrs systemAgrs = (SystemAgrs) request.getSession().getAttribute("systemAgrs");
			if(systemAgrs.getModel() == 2){
				List<User> users = sellerService.selUser();
				request.setAttribute("users", users);
			}
			request.setAttribute("imgs", imgs);
			request.getSession().removeAttribute("tempImg");
			return "goAddOrUpdateSeller";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添砖到商家添加修改页面出错！",e);
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改商家
	 * @return
	 */
	public String addOrUpdateSeller(){
		try {
			
			String filepath=request.getRealPath("/"+uploadFolder);
			Integer menuId = (Integer) request.getSession().getAttribute("menuId");
			List<Img> imgs = (List<Img>) request.getSession().getAttribute("tempImg");
			
			if(sellerService.addOrUpdateSeller(seller,menuId,img,imgContentType,imgFileName,filepath,projectUrl,uploadFolder,imgs)){
				seller = null;
				request.setAttribute("msg", "添加成功，是否继续添加？");
			}else{
				request.setAttribute("msg", "修改成功，是否继续修改？");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加或者修改商家出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return goAddOrUpdateSeller();
	}
	
	/**
	 * 删除资讯
	 * @return
	 */
	public String delSeller(){
		try {
			sellerService.delSeller(seller);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除商家出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return selSellerList();
	}
	
	public PageUtil getPu() {
		return pu;
	
	}

	public void setPu(PageUtil pu) {
		this.pu = pu;
	}

	public Integer getPageNow() {
		return pageNow;
	
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getLimit() {
		return limit;
	
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getQueryValue() {
		return queryValue;
	
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}

	public File getImg() {
		return img;
	
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getImgFileName() {
		return imgFileName;
	
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public Seller getSeller() {
		return seller;
	
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	
}
