package com.tcsh.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tcsh.common.BaseAction;
import com.tcsh.common.DateUtil;
import com.tcsh.common.PageUtil;
import com.tcsh.model.local.Common;
import com.tcsh.model.local.Information;
import com.tcsh.model.local.News;
import com.tcsh.model.local.Product;
import com.tcsh.model.local.SignUp;
import com.tcsh.model.remot.City;
import com.tcsh.model.remot.Imges;
import com.tcsh.model.remot.Recommend;
import com.tcsh.model.remot.Seller;
import com.tcsh.service.QueryService;


@Component("queryAction")
@Scope("prototype")
public class QueryAction extends BaseAction{

	@Autowired
	private QueryService queryService;
	
	private SignUp signUp;
	
	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	/**
	 * 系统首页
	 * @return
	 */
	public String index(){
		try {
			List<Object> objs = queryService.findTopList(1,6);
			request.setAttribute("objs", objs);
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 商家注册
	 * @return
	 */
	public String signUp(){
		try {
			String type = request.getParameter("type");
			List<City> cities = (List<City>) queryService.findRemotCityList();
			String temp = request.getHeader("User-Agent");
			request.setAttribute("cities", cities);
			if(temp.indexOf("Mobile") != -1){
				return "mobile";
			}else{
				if( null != type && !"".equals(type) ){
					return "ie";
				}else{
					return "pc";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法signUp出错");
		}
		return ERROR;
	}
	
	/**
	 * 添加商户信息
	 * @return
	 */
	public String addSeller(){
		try {
//			type = request.getParameter("type");
			signUp.setAddTime(DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));
			queryService.saveSeller(signUp);
			request.setAttribute("msg", "add");
			
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}finally{
			return signUp();
		}
	}
	
	/**
	 * 产品(同城生活介绍)
	 * @return
	 */
	public String tcsh(){
		try {
			Common c = (Common) queryService.findUniqueObj(Common.class);
			request.setAttribute("tcsh", c);
			return "tcsh";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 行业资讯
	 * @return
	 */
	public String information(){
		try {
			pu =  queryService.findObjOrder(Information.class, 1, 2,"addTime");
			request.setAttribute("informationTime", pu.data);
			pu =  queryService.findObjOrder(Information.class, 1, 6,"addTime");
			request.setAttribute("informationTop", pu.data);
			pu =  queryService.findObjOrder(News.class, 1, 6,"addTime");
			request.setAttribute("news", pu.data);
			pu =  queryService.findObjOrder(Product.class, 1, 6,"addTime");
			request.setAttribute("product", pu.data);
			return "information";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 公司信息
	 * @return
	 */
	public String company(){
		try {
			Common c = (Common) queryService.findUniqueObj(Common.class);
			request.setAttribute("company", c);
			return "company";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 招贤纳士
	 * @return
	 */
	public String job(){
		try {
			Common c = (Common) queryService.findUniqueObj(Common.class);
			request.setAttribute("job", c);
			return "job";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 具体内容
	 * @return
	 */
	public String content(){
		try {
			String name = request.getParameter("className");
			String id = request.getParameter("id");
			Object obj = null;
			if( null != id){
				obj = queryService.findObjById(name,Integer.parseInt(id));
			}
			if("News".equals(name)){
				request.setAttribute("position", "新闻内容");
			}else if("Product".equals(name)){
				request.setAttribute("position", "产品内容");
			}else if("Information".equals(name)){
				request.setAttribute("position", "行业资讯内容");
			}
			request.setAttribute("object", obj);
			return "content";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法content出错");
		}
		return ERROR;
	}
	
	/**
	 * 查询热门优惠
	 * @return
	 */
	public String hot(){
		try {
			pu = queryService.findObjByType(pageNow,limit,2);
			pu.setUrl("query!hot");
			return "hot";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 查询人气美食
	 * @return
	 */
	public String cate(){
		try {
			pu = queryService.findObjByType(pageNow,limit,1);
			pu.setUrl("query!cate");
			return "cate";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 查询市场
	 * @return
	 */
	public String market(){
		try {
			pu = queryService.findMarket(pageNow,12);
			pu.setUrl("query!market");
			return "market";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 查询市场商户列表
	 * @return
	 */
	public String seller(){
		try {
			String id = request.getParameter("id");
			pu = queryService.findSellerById(pageNow,limit,Integer.parseInt(id));
			pu.setUrl("query!seller");
			request.setAttribute("id", id);
			return "seller";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 查询应用汇
	 * @return
	 */
	public String app(){
		try {
			pu = queryService.findRemotObj(Recommend.class, pageNow, 5);
			pu.setUrl("query!app");
			return "app";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	/**
	 * 查询商户详情
	 * @return
	 */
	public String detail(){
		try {
			String id = request.getParameter("id");
			Seller s = (Seller) queryService.findRemotUniqueObj(Seller.class,Integer.parseInt(id));
			List<Imges> imgs = queryService.findRemotAllImg(Integer.parseInt(id));
			request.setAttribute("imgs", imgs);
			request.setAttribute("seller", s);
			return "detail";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("方法index出错");
		}
		return ERROR;
	}
	
	public SignUp getSignUp() {
		return signUp;
	}

	public void setSignUp(SignUp signUp) {
		this.signUp = signUp;
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

}
