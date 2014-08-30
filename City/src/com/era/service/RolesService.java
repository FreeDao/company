package com.era.service;

import java.util.List;

import com.era.orm.Roles;

public interface RolesService
{
	/**
	 * 根据id查询角色的详细
	 * @param id
	 * @return
	 */
	public Roles selRolesById(int id);
	/**
	 * 添加角色
	 * @param ion
	 * @return
	 */
	public boolean rolesAdd(Roles ion);
	/**
	 * 查询所有的角色
	 * @return
	 */
	public List<Roles> selRoles(int pageNo, int pageSize);
	
	/**
	 * 更新id
	 * @param roles
	 * @return
	 */
	public boolean rolesByIdUpdate(Roles roles);
	/**
	 * 查询角色的总条数
	 * @return
	 */
	public int numberRoles();
	/**
	 * 查询全部的权限
	 * @return
	 */
	public List<Roles> allRoles();
}
