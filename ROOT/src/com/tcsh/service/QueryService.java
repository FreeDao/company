package com.tcsh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcsh.common.PageUtil;
import com.tcsh.dao.HDB;
import com.tcsh.dao.RDB;
import com.tcsh.model.local.Common;
import com.tcsh.model.local.Information;
import com.tcsh.model.local.News;
import com.tcsh.model.local.Product;
import com.tcsh.model.local.SignUp;
import com.tcsh.model.local.Top;
import com.tcsh.model.local.User;
import com.tcsh.model.remot.City;
import com.tcsh.model.remot.Imges;
import com.tcsh.model.remot.Seller;


@Component("queryService")
public class QueryService {

	@Autowired
	private HDB hdb;
	@Autowired
	private RDB rdb;

	/**
	 * 查询出指定条数
	 * @return
	 */
	public PageUtil findObj(Class clazz, Integer i, Integer j) {
		// TODO Auto-generated method stub
		return hdb.findAll(clazz, i, j);
	}

	/**
	 * 根据对象找出唯一的记录
	 * @param class1
	 * @return
	 */
	public Object findUniqueObj(Class clazz) {
		// TODO Auto-generated method stub
		String hql = "from "+clazz.getName();
		return hdb.findUniqueHql(hql);
	}

	/**
	 * 根据指定排序查询数据
	 * @param class1
	 * @param i
	 * @param j
	 * @param string
	 * @return
	 */
	public PageUtil findObjOrder(Class clazz, Integer i, Integer j,
			String order) {
		// TODO Auto-generated method stub
		String hql = "from "+clazz.getName()+" c order by c."+order+" desc";
		return hdb.findHql(hql, i, j);
	}

	/**
	 * 通过id查找对象
	 * @param name
	 * @param parseInt
	 * @return 
	 */
	public Object findObjById(String name, Integer id) {
		// TODO Auto-generated method stub
		String hql = "from "+name+" t where t.id="+id;
		return hdb.findUniqueHql(hql);
	}

	/**
	 * 查询远程数据库
	 * @param class1
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil findRemotObj(Class clazz, Integer pageNow,
			Integer limit) {
		// TODO Auto-generated method stub
		return rdb.findAll(clazz,pageNow,limit);
	}

	/**
	 * 查询热门优惠
	 * @param class1
	 * @param pageNow
	 * @param limit
	 * @return
	 */
	public PageUtil findObjByType(Integer pageNow,
			Integer limit,Integer type) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.productId="+type;
		return rdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 查询唯一的对象
	 * @param class1
	 * @param id
	 * @return
	 */
	public Object findRemotUniqueObj(Class clazz, Integer id) {
		// TODO Auto-generated method stub
		String hql = "from "+clazz.getName()+" t where t.id="+id;
		return rdb.findUniqueHql(hql);
	}

	/**
	 * 查询指定id的所有图片
	 * @param id
	 * @return
	 */
	public List<Imges> findRemotAllImg(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Imges i where i.compositeId="+id+" and i.type=1";
		return (List<Imges>) rdb.findHqlList(hql, 1, 5);
	}

	/**
	 * 查询所有的市场
	 * @param pageNow
	 * @param limit
	 * @param i
	 * @return
	 */
	public PageUtil findMarket(Integer pageNow, Integer limit) {
		// TODO Auto-generated method stub
		String sql = "select m.*,(select s.logo from seller s where s.typeId=m.id order by s.sort asc limit 0,1) url from market m where m.cityId=18";
		return rdb.findSql(sql, pageNow, limit);
	}

	/**
	 * 查询市场下的商户
	 * @param pageNow
	 * @param limit
	 * @param parseInt
	 * @return
	 */
	public PageUtil findSellerById(Integer pageNow, Integer limit, Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Seller s where s.typeId = "+id;
		return rdb.findHql(hql, pageNow, limit);
	}

	/**
	 * 查询置顶的前6条信息
	 * @param i
	 * @param j
	 * @return
	 */
	public List<Object> findTopList(Integer pageNow, Integer limit) {
		// TODO Auto-generated method stub
		List<Top> tops = (List<Top>) hdb.findAllList(Top.class, pageNow, limit);
		List<Object> objs = new ArrayList<Object>();
		for(Top t : tops){
			switch (t.getType()) {
			case 0:
				objs.add(hdb.find(Information.class, t.getParentId()));
				break;
			case 1:
				objs.add(hdb.find(News.class, t.getParentId()));
				break;
			case 2:
				objs.add(hdb.find(Product.class, t.getParentId()));
				break;
			default:
				break;
			}
		}
		return objs;
	}

	/**
	 * 查询所有的城市列表
	 * @return
	 */
	public List<?> findRemotCityList() {
		// TODO Auto-generated method stub
		return rdb.findAll(City.class);
	}

	/**
	 * 添加商户信息
	 * @param sinUp
	 */
	public void saveSeller(SignUp sinUp) {
		// TODO Auto-generated method stub
		hdb.saveOrUpdate(sinUp);
	}

}
