package com.gre.bg.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.InvitationService;
import com.gre.common.BaseAction;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Invitation;

@Component("invitationAction")
@Scope("prototype")
public class InvitationAction extends BaseAction{

	@Autowired
	private InvitationService invitationService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private Invitation invitation;
	
	/**
	 * 查询所有商业信息
	 * @return
	 */
	public String index(){
		try {
			pu = invitationService.findAllList(Invitation.class,pageNow,limit);
			pu.setUrl("bg/invitation!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}

	/**
	 * 删除指定商业信息
	 * @return
	 */
	public String delInvitation(){
		try {
			invitationService.delObjById(Invitation.class,invitation.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("InvitationAction中方法delInvitation出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != invitation ){
				invitation = (Invitation) invitationService.findObjById(Invitation.class,invitation.getId());
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
			if( null != invitation.getId() && !"".equals(invitation.getId()) ){
				Invitation i = (Invitation) invitationService.findObjById(Invitation.class, invitation.getId());
				invitation.setAddTime(i.getAddTime());
			}else{
				invitation.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			}
			invitationService.saveOrUpdateObj(invitation);
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("InvitationAction中方法addOrEdit出错");
			return index();
		}
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

	public Invitation getInvitation() {
		return invitation;
	}

	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}
	
}
