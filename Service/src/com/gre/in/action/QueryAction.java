package com.gre.in.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.common.BaseAction;
import com.gre.common.CommonUtil;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Cate;
import com.gre.common.model.Clothing;
import com.gre.common.model.Education;
import com.gre.common.model.Entertainment;
import com.gre.common.model.FeedBack;
import com.gre.common.model.Hairdressing;
import com.gre.common.model.Img;
import com.gre.common.model.Lease;
import com.gre.common.model.Medicine;
import com.gre.common.model.Service;
import com.gre.common.model.Travel;
import com.gre.common.model.UpdateInfo;
import com.gre.in.query.Query;
import com.gre.in.service.QueryService;

@Component("queryAction")
@Scope("prototype")
public class QueryAction extends BaseAction{

	@Autowired
	private QueryService queryService;
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 查询所有餐饮美食
	 * @return
	 */
	public String selAllCate(){
		 try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllList(Cate.class,pageNow,limit);
			
			List<Query> qs = new ArrayList<Query>(); 
			for(int i = 0 ; i < pu.getData().size() ; i++){
				Query q = new Query();
				Cate cate = (Cate) pu.getData().get(i);
				q.setId(cate.getId());
				q.setAddTime(cate.getAddTime());
				q.setContent(cate.getContent());
				q.setTitle(cate.getTitle());
				q.setQuotation(cate.getQuotation());
				q.setLogo(cate.getLogo());
				q.setLevel(cate.getLevel());
				q.setTel(cate.getTel());
				q.setAddr(cate.getAddr());
				
				List<Img> imgs = queryService.findImgList(cate.getId(),0);
				q.setImgs(imgs);
				qs.add(q);
			}
			
			JSONArray json = JSONArray.fromObject(qs);
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("responseCode", 0);
			map.put("results", json);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有服装
	 * @return
	 */
	public String selAllClothing(){
		 try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllList(Clothing.class,pageNow,limit);
			
			List<Query> qs = new ArrayList<Query>(); 
			for(int i = 0 ; i < pu.getData().size() ; i++){
				Query q = new Query();
				Clothing clothing = (Clothing) pu.getData().get(i);
				q.setId(clothing.getId());
				q.setAddTime(clothing.getAddTime());
				q.setContent(clothing.getContent());
				q.setTitle(clothing.getTitle());
				q.setQuotation(clothing.getQuotation());
				q.setLogo(clothing.getLogo());
				q.setLevel(clothing.getLevel());
				q.setTel(clothing.getTel());
				q.setAddr(clothing.getAddr());
				
				List<Img> imgs = queryService.findImgList(clothing.getId(),1);
				q.setImgs(imgs);
				qs.add(q);
			}
			
			JSONArray json = JSONArray.fromObject(qs);
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("responseCode", 0);
			map.put("results", json);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}

	/**
	 * 查询所有培训教育
	 * @return
	 */
	public String selAllEducation(){
		 try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllList(Education.class,pageNow,limit);
			
			List<Query> qs = new ArrayList<Query>(); 
			for(int i = 0 ; i < pu.getData().size() ; i++){
				Query q = new Query();
				Education education = (Education) pu.getData().get(i);
				q.setId(education.getId());
				q.setAddTime(education.getAddTime());
				q.setContent(education.getContent());
				q.setTitle(education.getTitle());
				q.setQuotation(education.getQuotation());
				q.setLogo(education.getLogo());
				q.setLevel(education.getLevel());
				q.setTel(education.getTel());
				q.setAddr(education.getAddr());
				
				List<Img> imgs = queryService.findImgList(education.getId(),2);
				q.setImgs(imgs);
				qs.add(q);
			}
			
			JSONArray json = JSONArray.fromObject(qs);
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("responseCode", 0);
			map.put("results", json);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有休闲娱乐
	 * @return
	 */
	public String selAllEntertainment(){
		 try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllList(Entertainment.class,pageNow,limit);
			
			List<Query> qs = new ArrayList<Query>(); 
			for(int i = 0 ; i < pu.getData().size() ; i++){
				Query q = new Query();
				Entertainment entertainment = (Entertainment) pu.getData().get(i);
				q.setId(entertainment.getId());
				q.setAddTime(entertainment.getAddTime());
				q.setContent(entertainment.getContent());
				q.setTitle(entertainment.getTitle());
				q.setQuotation(entertainment.getQuotation());
				q.setLogo(entertainment.getLogo());
				q.setLevel(entertainment.getLevel());
				q.setTel(entertainment.getTel());
				q.setAddr(entertainment.getAddr());
				
				List<Img> imgs = queryService.findImgList(entertainment.getId(),3);
				q.setImgs(imgs);
				qs.add(q);
			}
			
			JSONArray json = JSONArray.fromObject(qs);
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("responseCode", 0);
			map.put("results", json);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有美容美发
	 * @return
	 */
	public String selAllHairdressing(){
		 try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllList(Hairdressing.class,pageNow,limit);
			
			List<Query> qs = new ArrayList<Query>(); 
			for(int i = 0 ; i < pu.getData().size() ; i++){
				Query q = new Query();
				Hairdressing hairdressing = (Hairdressing) pu.getData().get(i);
				q.setId(hairdressing.getId());
				q.setAddTime(hairdressing.getAddTime());
				q.setContent(hairdressing.getContent());
				q.setTitle(hairdressing.getTitle());
				q.setQuotation(hairdressing.getQuotation());
				q.setLogo(hairdressing.getLogo());
				q.setLevel(hairdressing.getLevel());
				q.setTel(hairdressing.getTel());
				q.setAddr(hairdressing.getAddr());
				
				List<Img> imgs = queryService.findImgList(hairdressing.getId(),4);
				q.setImgs(imgs);
				qs.add(q);
			}
			
			JSONArray json = JSONArray.fromObject(qs);
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("responseCode", 0);
			map.put("results", json);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有租赁
	 * @return
	 */
	public String selAllLease(){
		 try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllList(Lease.class,pageNow,limit);
			
			List<Query> qs = new ArrayList<Query>(); 
			for(int i = 0 ; i < pu.getData().size() ; i++){
				Query q = new Query();
				Lease lease = (Lease) pu.getData().get(i);
				q.setId(lease.getId());
				q.setAddTime(lease.getAddTime());
				q.setContent(lease.getContent());
				q.setTitle(lease.getTitle());
				q.setQuotation(lease.getQuotation());
				q.setLogo(lease.getLogo());
				q.setLevel(lease.getLevel());
				q.setTel(lease.getTel());
				q.setAddr(lease.getAddr());
				
				List<Img> imgs = queryService.findImgList(lease.getId(),5);
				q.setImgs(imgs);
				qs.add(q);
			}
			
			JSONArray json = JSONArray.fromObject(qs);
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("responseCode", 0);
			map.put("results", json);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有医疗
	 * @return
	 */
	public String selAllMedicine(){
		 try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllList(Medicine.class,pageNow,limit);
			
			List<Query> qs = new ArrayList<Query>(); 
			for(int i = 0 ; i < pu.getData().size() ; i++){
				Query q = new Query();
				Medicine medicine = (Medicine) pu.getData().get(i);
				q.setId(medicine.getId());
				q.setAddTime(medicine.getAddTime());
				q.setContent(medicine.getContent());
				q.setTitle(medicine.getTitle());
				q.setQuotation(medicine.getQuotation());
				q.setLogo(medicine.getLogo());
				q.setLevel(medicine.getLevel());
				q.setTel(medicine.getTel());
				q.setAddr(medicine.getAddr());
				
				List<Img> imgs = queryService.findImgList(medicine.getId(),6);
				q.setImgs(imgs);
				qs.add(q);
			}
			
			JSONArray json = JSONArray.fromObject(qs);
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("responseCode", 0);
			map.put("results", json);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有便民服务
	 * @return
	 */
	public String selAllService(){
		 try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllList(Service.class,pageNow,limit);
			
			List<Query> qs = new ArrayList<Query>();
			for(int i = 0 ; i < pu.getData().size() ; i++){
				Query q = new Query();
				Service service = (Service) pu.getData().get(i);
				q.setId(service.getId());
				q.setAddTime(service.getAddTime());
				q.setContent(service.getContent());
				q.setTitle(service.getTitle());
				q.setQuotation(service.getQuotation());
				q.setLogo(service.getLogo());
				q.setLevel(service.getLevel());
				q.setTel(service.getTel());
				q.setAddr(service.getAddr());
				
				List<Img> imgs = queryService.findImgList(service.getId(),7);
				q.setImgs(imgs);
				qs.add(q);
			}
			
			JSONArray json = JSONArray.fromObject(qs);
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("responseCode", 0);
			map.put("results", json);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有旅游
	 * @return
	 */
	public String selAllTravel(){
		 try {
			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer limit = null;
			if( null != request.getParameter("limit")){
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			PageUtil pu = queryService.findAllList(Travel.class,pageNow,limit);
			
			List<Query> qs = new ArrayList<Query>(); 
			for(int i = 0 ; i < pu.getData().size() ; i++){
				Query q = new Query();
				Travel travel = (Travel) pu.getData().get(i);
				q.setId(travel.getId());
				q.setAddTime(travel.getAddTime());
				q.setContent(travel.getContent());
				q.setTitle(travel.getTitle());
				q.setQuotation(travel.getQuotation());
				q.setLogo(travel.getLogo());
				q.setLevel(travel.getLevel());
				q.setTel(travel.getTel());
				q.setAddr(travel.getAddr());
				
				List<Img> imgs = queryService.findImgList(travel.getId(),8);
				q.setImgs(imgs);
				qs.add(q);
			}
			
			JSONArray json = JSONArray.fromObject(qs);
			if(pu.hasNext){
				map.put("hasNext", 1);
			}else{
				map.put("hasNext", 0);
			}
			map.put("responseCode", 0);
			map.put("results", json);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询出错！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 提交反馈信息
	 * @return
	 */
	public String saveFeedBack(){
		try {
			String content = "";
			if(null != content){
				content = CommonUtil.changeIos8859ToUtf8(request.getParameter("content"));
			}
			String email = request.getParameter("email");
			
			FeedBack fb = new FeedBack();
			System.out.println(DateUtil.getNowString("yyyy-MM-dd"));
			fb.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			fb.setContent(content);
			fb.setEmail(email);
			
			queryService.saveOrUpdateObject(fb);
			
			map.put("responseCode", 0);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("保存反馈信息错误！");
			map.put("responseCode", 1);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询是否需要更新信息
	 * @return
	 */
	public String isUpdate(){
		try {
			String version = request.getParameter("version");
			int platform = Integer.parseInt(request.getParameter("platform"));
			
			 UpdateInfo ui  = queryService.findUpdateInfo(version,platform);
			 
			 if( null == ui ){
				 ui = queryService.findUpdateInfo(platform);
				 if(null == ui){
					 ui = new UpdateInfo();
					 ui.setContent("初始化");
					 ui.setIsForced(0);
					 ui.setPlatForm(platform);
					 ui.setVersion("0.0");
					 queryService.saveOrUpdateObject(ui);
				 }
				 map.put("version", ui.getVersion());
				 map.put("content", ui.getContent());
				 map.put("url", ui.getUrl());
				 map.put("isForced", ui.getIsForced());
				 map.put("responseCode", 0);
			 }else{
				 map.put("responseCode", 1);
			 }
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询更新功能出错！");
			map.put("responseCode", 2);
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
