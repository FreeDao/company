package com.tcsh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcsh.common.PageUtil;
import com.tcsh.dao.HDB;
import com.tcsh.model.local.SignUp;
import com.tcsh.model.local.User;

@Component("signUpService")
public class SignUpService {

	@Autowired
	private HDB hdb;

	/**
	 * 根据用户所属城市查询对应的报名商家
	 * @param user
	 * @param limit 
	 * @param pageNow 
	 * @return
	 */
	public PageUtil findSignUpList(User user, Integer pageNow, Integer limit) {
		// TODO Auto-generated method stub
		String hql ="from SignUp su where 1=1 ";
		if( null != user.getCityName() && !"".equals(user.getCityName())){
			hql += " and su.cityName='"+user.getCityName()+"'";
		}
		return hdb.findHql(hql, pageNow, limit);
	}
	
	
}
