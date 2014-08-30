package com.tcsh.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tcsh.common.BaseAction;
import com.tcsh.common.PageUtil;
import com.tcsh.model.local.User;
import com.tcsh.service.LuckService;

@Component("luckAction")
@Scope("prototype")
public class LuckAction extends BaseAction{

	@Autowired
	private LuckService luckService;
	
	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	public String index(){
		try {
			String num = request.getParameter("num");
			String phone = request.getParameter("phone");
			
			pu = luckService.findLuckList(num,phone,pageNow,limit);
			pu.setUrl("bg/luck!index");
			
			request.setAttribute("num", num);
			request.setAttribute("phone", phone);
			
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("中奖信息出错！");
		}
		return ERROR;
	}


	/**
	 * 已经发送
	 * @return
	 */
	public String send(){
		try{
			luckService.send(request.getParameter("id"));
			return index();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("发送中奖信息出错！");
		}
		return ERROR;
	}
	
	/**
	 * 已经发送
	 * @return
	 */
	public String add(){
		try{
			String num = request.getParameter("num");
			String time = request.getParameter("time");
			String phone = request.getParameter("phone");
			String win = request.getParameter("win");
			
			luckService.add(num,time,phone,win);
			
			return "add";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("发送中奖信息出错！");
		}
		return ERROR;
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
