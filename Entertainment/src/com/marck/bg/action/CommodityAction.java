package com.marck.bg.action;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.bg.service.CommodityService;
import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;
import com.marck.common.PageUtil;
import com.marck.common.model.Img;
import com.marck.common.model.Commodity;
import com.marck.common.model.Seller;
import com.marck.common.model.SystemAgrs;
import com.marck.common.model.Type;
import com.marck.common.model.User;

@Component("commodityAction")
@Scope("prototype")
public class CommodityAction extends BaseAction {

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private String queryValue;
	
	private Commodity commodity;
	
	@Autowired
	private CommodityService commodityService;

	private File img;
	private String imgContentType;
	private String imgFileName;
	
	/**
	 * 查询商品列表 
	 * @return
	 */
	public String selCommodityList(){
		try{
			String resUrl = request.getRequestURI();
			Integer menuId = CommonUtil.validParams(request.getParameter("menuId")) ? (Integer)request.getSession().getAttribute("menuId"): Integer.parseInt(request.getParameter("menuId"));
			request.getSession().setAttribute("url", resUrl.substring(resUrl.indexOf("/",1)+1)+"?");
			request.getSession().setAttribute("menuId", menuId);
			
			User user = (User) request.getSession().getAttribute("userSession");
			pu = commodityService.getCommodityList(user,menuId,queryValue,pageNow,limit);
			return "selCommodityList";
		}catch (Exception e) {
			// TODO: handle exception
			log.error("查询商品列表出错！",e);
		}
		return ERROR;
	}

	/**
	 * 跳转到商品添加修改页面
	 * @return
	 */
	public String goAddOrUpdateCommodity(){
		try {
			List<Img> imgs = new ArrayList<Img>();
			Integer menuId = (Integer) request.getSession().getAttribute("menuId");
			SystemAgrs systemAgrs = (SystemAgrs) request.getSession().getAttribute("systemAgrs");
			List<Type> scolors = new ArrayList<Type>();
			List<Type> ssizes = new ArrayList<Type>();
			
			if( CommonUtil.validParams(commodity) ){
				request.setAttribute("title", "添加商家");
			}else{
				imgs = commodityService.getImgs(commodity,imgs,menuId);
				commodity = commodityService.getCommodity(commodity);
				request.setAttribute("title", "修改商家");
				
				if(systemAgrs.getModel() == 3){
					scolors = commodityService.selCommodityColors(commodity);
					request.setAttribute("scolors", scolors);
					ssizes = commodityService.selCommoditySizes(commodity);
					request.setAttribute("ssizes", ssizes);
				}
				
			}
			
			if(systemAgrs.getModel() == 2 || systemAgrs.getModel() == 3 ){
				User user = (User) request.getSession().getAttribute("userSession");
				if(user.getRole() == 0){
					List<Seller> sellers = commodityService.selSellerList();
					request.setAttribute("sellers", sellers);
				}else{
					Seller seller = commodityService.selSeller(user);
					request.setAttribute("seller", seller);
				}
				
				if(systemAgrs.getModel() == 3){
					List<Type> types = commodityService.selColors();
					Iterator<Type> it = types.iterator();
					while (it.hasNext()) {
						Type t = it.next();
						for(Type type : scolors){
							if(t.getId() == type.getId()){
								it.remove();
							}
						}
					}
					request.setAttribute("colors", types);
					
					types = commodityService.selSizes();
					it = types.iterator();
					while (it.hasNext()) {
						Type t = it.next();
						for(Type type : ssizes){
							if(t.getId() == type.getId()){
								it.remove();
							}
						}
					}
					request.setAttribute("sizes", types);
				}
			}
			request.setAttribute("imgs", imgs);
			request.getSession().removeAttribute("tempImg");
			return "goAddOrUpdateCommodity";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("添砖到商品添加修改页面出错！",e);
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改商品
	 * @return
	 */
	public String addOrUpdateCommodity(){
		try {
			String[] sizes = request.getParameterValues("sizes");
			String[] colors = request.getParameterValues("colors");
			String filepath=request.getRealPath("/"+uploadFolder);
			Integer menuId = (Integer) request.getSession().getAttribute("menuId");
			List<Img> imgs = (List<Img>) request.getSession().getAttribute("tempImg");
			
			if(commodityService.addOrUpdateCommodity(commodity,menuId,img,imgContentType,imgFileName,filepath,projectUrl,uploadFolder,imgs,sizes,colors)){
				commodity = null;
				request.setAttribute("msg", "添加成功，是否继续添加？");
			}else{
				request.setAttribute("msg", "修改成功，是否继续修改？");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("添加或者修改商品出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return goAddOrUpdateCommodity();
	}
	
	/**
	 * 删除商品
	 * @return
	 */
	public String delCommodity(){
		try {
			commodityService.delCommodity(commodity);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除商品出错！",e);
			request.setAttribute("msg", "操作失败！");
		}
		return selCommodityList();
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

	public Commodity getCommodity() {
		return commodity;
	
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	
	
}
