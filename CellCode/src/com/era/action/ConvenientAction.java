package com.era.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Convenient;
import com.era.service.ConvenientService;
import com.era.util.BaseUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ConvenientAction extends ActionSupport implements
		ModelDriven<Convenient>, ServletRequestAware {
	private Convenient model = new Convenient();
	private HttpServletRequest request;
	private ConvenientService convenientService;
	private String result;// 用于组装json

	/**
	 * 查询城市便民类型
	 */
	public void getConvenient() {
		String cityId_str = request.getParameter("cityId");
		if (cityId_str != null) {
			int cityId = Integer.parseInt(cityId_str);
			if (cityId > 0) {
				List<Convenient> list = convenientService.getConvenient(cityId);
				if (list != null && list.size() > 0) {
					JSONArray array = JSONArray.fromObject(list);
					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
				} else {
					result = "{\"responseCode\":\"" + -2 + "\"}";
				}
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		} else {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}

		BaseUtils.responseInfo(result);
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Convenient getModel() {
		return model;
	}

	public void setModel(Convenient model) {
		this.model = model;
	}

	public ConvenientService getConvenientService() {
		return convenientService;
	}

	public void setConvenientService(ConvenientService convenientService) {
		this.convenientService = convenientService;
	}
}
