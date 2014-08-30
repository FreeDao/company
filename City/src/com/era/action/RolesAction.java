package com.era.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Roles;
import com.era.service.RolesService;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RolesAction extends ActionSupport implements ModelDriven<Roles>,
ServletRequestAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	HttpServletRequest request;
	Roles model = new Roles();
	RolesService rolesService;
	private List<Roles> list;
	
	private String id;
	private String[] pro;
	private String roleName;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	
	/**
	 * 跳转角色页面
	 * @return
	 */
	public String pagesRoles()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		if (request.getParameter("id") != null) {
			// 显示详细字段信息
			id = request.getParameter("id");
			Roles aut = rolesService.selRolesById(Integer.valueOf(request.getParameter("id")));
			if (aut != null)
			{
				roleName = aut.getRolesName();
				if(aut.getRolesAction()!=null)
				{
					pro =aut.getRolesAction().split(",");
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 添加修改角色
	 * @return
	 */
	public String addRoles()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		String idlist[] = request.getParameterValues("rolestring");
		String rolesaction="";
		for (int i = 0; i < idlist.length; i++) {
			rolesaction += idlist[i]+",";
		}
		model.setRolesAction(rolesaction);
		model.setRolesName(request.getParameter("roleName"));
		if (request.getParameter("id") == null) {
			boolean flag = rolesService.rolesAdd(model);
			if (flag) {
				addActionMessage("新增角色成功");
				selRoles();
				return SUCCESS;
			}
		} else {
			model.setId(Integer.valueOf(request.getParameter("id")));
			boolean flag=rolesService.rolesByIdUpdate(model);
			if (flag) {
				addActionMessage("修改角色成功");
				selRoles();
				return SUCCESS;
			}
		}
		return ERROR;
	}
	
	/**
	 * 查询所有的角色
	 * @return
	 */
	public String selRoles()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		pagecount = rolesService.numberRoles();
		if(pagecount<15){
			pageCount=1;
		}else{
			pageCount = pagecount/15;
			int i  = pagecount%15;
			if(i>0)
			{
				pageCount+=1;
			}
		}
		if (pagenum < 1) {
			pagenum = 1;
		}
		if (pagenum > pagecount) {
			if(pagecount == 0)
			{
				
			}
			else
			{
				pagenum = pageCount;
			}
		}
		list = rolesService.selRoles(pagenum,15);
		return SUCCESS;
	}
	
	/**
	 * 跳转到添加角色
	 * @return
	 */
	public String pageRolesAdd()
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		return SUCCESS;
	}
	/**
	 * 新增角色
	 * @throws IOException 
	 */
	public String rolesAdd() throws IOException
	{
		if(request.getSession().getAttribute("userid") == null)
		{
			return "error";
		}
		model.setRolesName(request.getParameter("roleName"));
		boolean bool = rolesService.rolesAdd(model); 
		if(bool)
		{
			selRoles();
		}
		else
		{
			alertInFo.alertReturn("角色添加失败!");
		}
		return SUCCESS;		
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	@Override
	public Roles getModel() 
	{
		return model;
	}


	public RolesService getRolesService() {
		return rolesService;
	}


	public void setRolesService(RolesService rolesService) {
		this.rolesService = rolesService;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String[] getPro() {
		return pro;
	}


	public void setPro(String[] pro) {
		this.pro = pro;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Roles> getList() {
		return list;
	}

	public void setList(List<Roles> list) {
		this.list = list;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getPagesum() {
		return pagesum;
	}

	public void setPagesum(int pagesum) {
		this.pagesum = pagesum;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
