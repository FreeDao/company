package com.gre.bg.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.TradeService;
import com.gre.common.BaseAction;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Trade;

@Component("tradeAction")
@Scope("prototype")
public class TradeAction extends BaseAction{

	@Autowired
	private TradeService tradeService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private Trade trade;
	
	/**
	 * 查询所有行业动态信息
	 * @return
	 */
	public String index(){
		try {
			pu = tradeService.findAllList(Trade.class,pageNow,limit);
			pu.setUrl("bg/trade!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}

	/**
	 * 删除指定行业动态信息
	 * @return
	 */
	public String delTrade(){
		try {
			tradeService.delObjById(Trade.class,trade.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("TradeAction中方法delTrade出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != trade ){
				trade = (Trade) tradeService.findObjById(Trade.class,trade.getId());
			}
			return "addOrEdit";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEdit(){
		try {
			if( null != trade.getId() && !"".equals(trade.getId()) ){
				Trade c = (Trade) tradeService.findObjById(Trade.class, trade.getId());
				trade.setAddTime(c.getAddTime());
			}else{
				trade.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			}
			tradeService.saveOrUpdateObj(trade);
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("TradeAction中方法addOrEdit出错");
			return index();
		}
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
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
