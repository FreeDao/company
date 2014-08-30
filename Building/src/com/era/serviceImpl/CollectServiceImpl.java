package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Collect;
import com.era.orm.SupplyAndDemand;
import com.era.orm.Type;
import com.era.orm.User;
import com.era.service.CollectService;
import com.era.util.BaseUtils;

public class CollectServiceImpl implements CollectService 
{
	private BaseDAO dao;
	public List<Object> selectCollect(String userId,String supplyId,String ishave,String informationId,String pageNo,String pageNum)
	{
		List<Collect> list = null;
		String infoId = null;
		String relId = null;
		List<Object> listSupp = new ArrayList<Object>();
		String hql = "from Collect where 1 = 1";
		try 
		{
			if(userId == null||userId.equals(""))
			{
			}else
			{
				hql +=" and userId = "+ Integer.valueOf(userId);
			}
			if(supplyId==null||supplyId.equals(""))
			{
			}else
			{
				hql += " and supplyId = "+Integer.valueOf(supplyId);
			}
			if(ishave == null || ishave.equals(""))
			{
			}else
			{
				hql += " and ishave = " + ishave;
			}
			if(informationId == null || informationId.equals(""))
			{
			}else
			{
				hql += " and informationId = " +informationId;
			}
			list = dao.query(hql, Integer.valueOf(pageNo), Integer.valueOf(pageNum));
			for (int i = 0; i < list.size(); i++) 
			{
				Collect seller = list.get(i);
				if(seller.getInformationId() == null || seller.getInformationId().equals(""))
				{
					
				}
				else
				{
					infoId += infoId+seller.getInformationId();
				}
				if(seller.getRelevanceId() == null || seller.getRelevanceId().equals(""))
				{
					
				}
				else
				{
					relId+=relId+seller.getRelevanceId();
				}
			}
			if(Integer.valueOf(ishave) == 2)
			{
				listSupp = dao.query("from Information where id in ("+ infoId+")");
			}
			if(Integer.valueOf(ishave) == 1)
			{
				List<SupplyAndDemand> listDemand = null;
				if(Integer.valueOf(supplyId) == 1)
				{
					//供应
					listDemand = dao.query("from SupplyAndDemand where id in (1,2) and statue = 1");
				}
				if(Integer.valueOf(supplyId) == 2)
				{
					//求购
					listDemand = dao.query("from SupplyAndDemand where id in ("+relId+") and statue = 2"); 
				}
				if(Integer.valueOf(supplyId) == 3)
				{
					listDemand = dao.query("from SupplyAndDemand where id in ("+relId+") and statue = 3"); 
				}
				for (int i = 0; i < listDemand.size(); i++) 
				{
					SupplyAndDemand outdoor = new SupplyAndDemand();
					SupplyAndDemand seller = listDemand.get(i);
					outdoor.setAddress(seller.getAddress());
					outdoor.setCorporate(seller.getCorporate());
					outdoor.setEmail(seller.getEmail());
					outdoor.setInfo(seller.getInfo());
					outdoor.setPhone(seller.getPhone());
					outdoor.setAddTime(seller.getAddTime());
					outdoor.setReleaseTime(seller.getReleaseTime());
					outdoor.setBuyNum(seller.getBuyNum());
					User user = (User) dao.loadById(User.class, list.get(i).getUserId());
					if(user == null || user.equals(""))
					{
						outdoor.setQq("");
					}
					else
					{
						outdoor.setQq(user.getQq());
					}
					
					outdoor.setId(seller.getId());
					outdoor.setCityId(seller.getCityId());
					if(seller.getLogo()==null)
					{
						outdoor.setLogo("");
					}else
					{
						outdoor.setLogo(seller.getLogo());
					}
					outdoor.setSellerId(seller.getSellerId());
					outdoor.setStatue(seller.getStatue());
					outdoor.setType(seller.getType());
					outdoor.setUserId(seller.getUserId());
					outdoor.setTitle(seller.getTitle());
					outdoor.setContent(seller.getContent());
					outdoor.setPrice(seller.getPrice());
					outdoor.setIsfinish(seller.getIsfinish());
					if(seller.getMatureTime()==null)
					{
						outdoor.setMatureTime("");
					}else
					{
						outdoor.setMatureTime(seller.getMatureTime());
					}
					outdoor.setOrderNum(seller.getOrderNum());
					outdoor.setTypeId(seller.getTypeId());
					Type Ctype =(Type) dao.loadById(Type.class, seller.getTypeId());
					if(Ctype == null || Ctype.equals(""))
					{
						outdoor.setTypeName("");
					}
					else
					{
						String name = Ctype.getName();
						outdoor.setTypeName(name);
					}
					
					if(seller.getProductcontent()==null)
					{
						outdoor.setProductcontent("");
					}else
					{
						outdoor.setProductcontent(seller.getProductcontent());
					}
					String hql_sellerImgs = "select imgUrl from  Image where type = 1 and compositeId = "+list.get(i).getId();
					List<String> list_sellerImgs = dao.querySQL(hql_sellerImgs);			
					String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
					str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
					outdoor.setImage(str_sellerimgs);
					listSupp.add(outdoor);
				}
			
			}
		}catch (Exception e) 
		{
			return null;
		}
		return listSupp;
	}
	/**
	 * 添加收藏
	 */
	public boolean addCollect(Collect comment) 
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(comment);
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 判断收藏是否存在
	 */
	public boolean isCollect(String userId,String supplyId,String ishave,String informationId,String relevanceId)
	{
		boolean flag = false;
		try 
		{
			String hql = "from Collect where userId = " + Integer.valueOf(userId) + " and ishave = " + Integer.valueOf(ishave);
			if(ishave.equals("1"))
			{
				if(supplyId==null||supplyId.equals(""))
				{
					
				}
				else if(supplyId.equals("1"))
				{
					hql += " and supplyId = 1 and relevanceId = "+Integer.valueOf(relevanceId);
				}
				else if(supplyId.equals("2"))
				{
					hql += " and supplyId = 2 and relevanceId = "+Integer.valueOf(relevanceId);
				}
				else if(supplyId.equals("3"))
				{
					hql += " and supplyId = 3 and relevanceId = "+Integer.valueOf(relevanceId);
				}
			}else if(ishave.equals("2"))
			{
				hql += " and informationId = " + Integer.valueOf(informationId);
			}
			Collect comment = (Collect)dao.loadObject(hql);
			if(comment!=null)
			{
				flag=true;
			}else
			{
				flag = false;
			}
		} catch (Exception e)
		{
			flag=false;
		}
		return flag;
	}
	/**
	 * 删除收藏
	 */
	public boolean delCollect(String userId,String supplyId,String ishave,String informationId,String relevanceId)
	{
		boolean flag = false;
		try {
			String hql = "from Collect where userId = "+Integer.valueOf(userId) + " and ishave = " + Integer.valueOf(ishave);
			if(ishave.equals("1"))
			{
				hql += " and supplyId = "+Integer.valueOf(supplyId)+" and relevanceId = "+Integer.valueOf(relevanceId);
			}else if(ishave.equals("2"))
			{
				hql += " and informationId = "+Integer.valueOf(informationId);
			}
			Collect comment = (Collect)dao.loadObject(hql);
			dao.delById(Collect.class, comment.getId());
			flag = true;
		} catch (Exception e)
		{
			flag = false;
		}
		return flag;
	}

	
	public BaseDAO getDao() 
	{
		return dao;
	}
	public void setDao(BaseDAO dao) 
	{
		this.dao = dao;
	}
}
