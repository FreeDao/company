package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.City;
import com.era.orm.Seller;
import com.era.orm.SupplyAndDemand;
import com.era.orm.Type;
import com.era.orm.User;
import com.era.service.SupplyAndDemandService;
import com.era.util.BaseUtils;

public class SupplyAndDemandServiceImpl implements SupplyAndDemandService 
{
	private BaseDAO dao;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<SupplyAndDemand> selSupply(String typeId,String cityId,String supply,String isfinish,String userId,String type,String pageNo,String pageNum) 
	{
		List<SupplyAndDemand> list = null;
		List list_seller = new ArrayList();
		//type 1为供求2为矿产 必传
		String hql = "from SupplyAndDemand where type = "+type;
		try
		{
			if(typeId == null ||typeId.equals(""))
			{
			}
			else
			{
				hql += " and typeId = "+typeId;
			}
			if(cityId == null || cityId.equals(""))
			{
			}
			else
			{
				hql += " and cityId = "+cityId;
			}
			if(supply == null || supply.equals(""))
			{
			}
			else
			{
				if(Integer.valueOf(supply) == 1)
				{
					//供
					hql +=" and statue = 1";
				}
				else if(Integer.valueOf(supply) ==2)
				{
					//求
					hql +=" and statue = 2";
				}
			}
			if(isfinish == null || isfinish.equals(""))
			{
			}
			else
			{
				if(Integer.valueOf(isfinish) == 1)
				{
					//正在
					hql +=" and isfinish = 1 and userId="+userId;
				}
				else
				{
					//完成
					hql +=" and isfinish = 2 and userId="+userId;
				}
			}
			if(pageNum == null || pageNum.equals(""))
			{
				list = dao.query(hql);
			}
			else
			{
				list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
			}
			for(int i=0;i<list.size();i++)
			{
				SupplyAndDemand outdoor = new SupplyAndDemand();
				System.out.println(list.get(i).getSellerId());
				Seller seller =(Seller) dao.loadObject("from Seller where id = "+list.get(i).getSellerId());
				if(seller == null || seller.equals(""))
				{
					outdoor.setAddress("");
					outdoor.setCorporate("");
					outdoor.setEmail("");
					outdoor.setInfo("");
					outdoor.setPhone("");
				}
				else
				{
					outdoor.setAddress(seller.getAddress());
					outdoor.setCorporate(seller.getSellerName());
					outdoor.setEmail(seller.getEmail());
					outdoor.setInfo(seller.getSellerOwner());
					outdoor.setPhone(seller.getPhone());
				}
			
				User user = (User) dao.loadById(User.class, list.get(i).getUserId());
				if(user == null || user.equals(""))
				{
					outdoor.setQq("");
				}
				else
				{
					outdoor.setQq(user.getQq());
				}
				
				outdoor.setId(list.get(i).getId());
				outdoor.setCityId(list.get(i).getCityId());
				if(list.get(i).getLogo()==null)
				{
					outdoor.setLogo("");
				}else
				{
					outdoor.setLogo(list.get(i).getLogo());
				}
				outdoor.setSellerId(list.get(i).getSellerId());
				outdoor.setStatue(list.get(i).getStatue());
				outdoor.setType(list.get(i).getType());
				outdoor.setUserId(list.get(i).getUserId());
				outdoor.setTitle(list.get(i).getTitle());
				if(list.get(i).getContent() == null || list.get(i).getContent().equals(""))
				{
					outdoor.setContent("");
				}
				else
				{
					outdoor.setContent(list.get(i).getContent());
				}
				
				outdoor.setPrice(list.get(i).getPrice());
				outdoor.setBuyNum(list.get(i).getBuyNum());
				if(list.get(i).getReleaseTime() == null || list.get(i).getReleaseTime().equals(""))
				{
					outdoor.setReleaseTime("");
				}
				else
				{
					outdoor.setReleaseTime(list.get(i).getReleaseTime());
				}
				
				outdoor.setAddTime(list.get(i).getAddTime());
				outdoor.setIsfinish(list.get(i).getIsfinish());
				if(list.get(i).getMatureTime()==null)
				{
					outdoor.setMatureTime("");
				}else
				{
					outdoor.setMatureTime(list.get(i).getMatureTime());
				}
				outdoor.setOrderNum(list.get(i).getOrderNum());
				outdoor.setTypeId(list.get(i).getTypeId());
				Type Ctype =(Type) dao.loadById(Type.class, list.get(i).getTypeId());
				if(Ctype == null || Ctype.equals(""))
				{
					outdoor.setTypeName("");
				}
				else
				{
					String name = Ctype.getName();
					outdoor.setTypeName(name);
				}
				if(list.get(i).getProductcontent()==null)
				{
					outdoor.setProductcontent("");
				}else
				{
					outdoor.setProductcontent(list.get(i).getProductcontent());
				}
				String hql_sellerImgs = "select imgUrl from  Image where type = 1 and compositeId = "+list.get(i).getId();
				List<String> list_sellerImgs = dao.querySQL(hql_sellerImgs);			
				String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
				str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
				outdoor.setImage(str_sellerimgs);
				list_seller.add(outdoor);
		}
	} catch (Exception e) 
	{
		list = null;
	}
	return list_seller;
}
	/**
	 * 供求管理
	 */
	public boolean deleteOrUpdate(String isfinish, String userId,
			String supplyId, String relevanceId)
	{
		boolean bool = false;
		String hql = "";
		try {
			if(isfinish.equals("1"))
			{
				hql += "update SupplyAndDemand set isfinish = 2 where id = "+Integer.valueOf(relevanceId);
				dao.update(hql);
				bool = true;
			}
			else if(isfinish.equals("2"))
			{
				dao.delById(SupplyAndDemand.class, Integer.valueOf(relevanceId));
				bool = true;
			}else
			{
				bool = false;
			}
		} catch (Exception e) 
		{
			bool = false;
		}
		return bool;
	}
	
	public boolean addSupply(SupplyAndDemand supplyAndDemand)
	{
		boolean flag = false;
		try 
		{
			dao.saveOrUpdate(supplyAndDemand);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public boolean addDemandInfo(SupplyAndDemand demand)
	{
		boolean flag = false;
		try 
		{
			dao.saveOrUpdate(demand);
			flag = true;
		} catch (Exception e)
		{
			flag = false;
		}
		return flag;
	}
	
	public City geCityIdByName(String cityName)
	{
		String hql = "from City where cityName = '" + cityName + "'";
		try 
		{
			return (City)dao.loadObject(hql);
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Type getTypeIdByName(String typeName)
	{
		String hql = "from Type where name = '"+typeName+"'";
		try 
		{
			Type type = (Type)dao.loadObject(hql);
			return type;
		} catch (Exception e) 
		{
			return null;
		}
	}
	
	
	public Seller getAddressBySellerId(int sellerId)
	{
		Seller seller = null;
		try 
		{
			seller =(Seller) dao.loadById(Seller.class, sellerId);
			return seller;
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public BaseDAO getDao() {
		return dao;
	}
	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
