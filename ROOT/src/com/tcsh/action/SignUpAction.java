package com.tcsh.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tcsh.common.BaseAction;
import com.tcsh.common.PageUtil;
import com.tcsh.model.local.SignUp;
import com.tcsh.model.local.User;
import com.tcsh.service.SignUpService;

@Component("signUpAction")
@Scope("prototype")
public class SignUpAction extends BaseAction{

	@Autowired
	private SignUpService signUpService;
	
	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	public String index(){
		try {
			User user = (User) request.getSession().getAttribute("userSession");
			pu = signUpService.findSignUpList(user,pageNow,limit);
			pu.setUrl("bg/signUp!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("商户查询出错！");
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
