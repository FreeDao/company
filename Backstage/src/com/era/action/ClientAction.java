package com.era.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Client;
import com.era.service.ClientService;
import com.era.util.BaseUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ClientAction extends ActionSupport implements ModelDriven<Client>,
		ServletRequestAware {

	private HttpServletRequest request;
	private Client model = new Client();
	private ClientService clientService;
	private String result;// 用于组装json

	/**
	 * 查询客户端
	 */
	public void getClient() {
		String marketId_str = request.getParameter("marketId");
		String cityId_str = request.getParameter("cityId");
		System.out.println("--getClient--marketId_str-------->" + marketId_str+"<---------");
		System.out.println("--getClient--cityId_str----------------->" + cityId_str+"<---------");
		
		if (marketId_str != null && cityId_str != null) {
			
			int marketId = Integer.parseInt(marketId_str);
			int cityId = Integer.parseInt(cityId_str);
			
			Client client = clientService.getClient(marketId, cityId);
			if (client != null) {
				JSONArray array = JSONArray.fromObject(client);
				result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
			} else {
				result = "{\"responseCode\":\"" + -2 + "\"}";
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

	@Override
	public Client getModel() {
		return model;
	}

	public void setModel(Client model) {
		this.model = model;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
