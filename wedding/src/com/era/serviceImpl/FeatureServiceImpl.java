package com.era.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Checkversion;
import com.era.orm.Classifcation;
import com.era.orm.Company;
import com.era.orm.Feature;
import com.era.orm.Info;
import com.era.orm.Plan;
import com.era.service.FeatureService;
import com.era.util.BaseUtils;

public class FeatureServiceImpl implements FeatureService
{
	private BaseDAO dao;

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Feature selFeatureId(int id)
	{
		Feature user = (Feature) dao.loadById(Feature.class,id);
		return user;
	}

	@Override
	public List<Object> featureAll(String dim, String lat, String areaId,String groom,String pageNo,String pageNum)
	{
		List list_seller = new ArrayList();
		try {
			String hql = "from Feature";
			if(areaId == null || areaId.equals(""))
			{
				if(groom == null || groom.equals(""))
				{
					
				}
				else
				{
					hql +=" where groom = "+groom;
				}
			}
			else
			{
				hql +=" where areaId = "+areaId;
				if(groom == null || groom.equals(""))
				{
					
				}
				else
				{
					hql +=" and groom = "+groom;
				}
			}
			hql+=" order by ACOS(SIN(("+dim+" * 3.1415) / 180 ) *SIN((dim * 3.1415) / 180 ) + COS(("+dim+" * 3.1415) / 180 ) * COS(( dim * 3.1415) / 180 ) *COS(("+lat+" * 3.1415) / 180 - (lot * 3.1415) / 180 ) ) * 6380 asc";
			List<Feature> list = null;
			if(pageNum == null || pageNum.equals(""))
			{
				list = dao.query(hql,Integer.valueOf(pageNo),10);
			}
			else
			{
				list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
			}
			for(int i=0;i<list.size();i++)
			{
				Feature feature = new Feature();
				feature.setId(list.get(i).getId());
				feature.setAreaId(list.get(i).getAreaId());
				feature.setFeatureName(list.get(i).getFeatureName());
				feature.setAddress(list.get(i).getAddress());
				feature.setDim(list.get(i).getDim());
				feature.setLot(list.get(i).getLot());
				feature.setPrice(list.get(i).getPrice());
				feature.setTime(list.get(i).getTime());
				feature.setLeven(list.get(i).getLeven());
				feature.setBife(list.get(i).getBife());
				feature.setHumanity(list.get(i).getHumanity());
				feature.setObligate(list.get(i).getObligate());
				feature.setEtcaeteras(list.get(i).getEtcaeteras());
				feature.setLogo(list.get(i).getLogo());
				String hql_sellerImgs = "select imgUrl from  Image where type = 1 and compositeId = "+list.get(i).getId();
				List<String> list_sellerImgs = dao.querySQL(hql_sellerImgs);			
				String str_sellerimgs = list_sellerImgs.toString().replace("[]", "").replace("[", "").replace("]","");
				str_sellerimgs = BaseUtils.del_space(str_sellerimgs);
				feature.setImage(str_sellerimgs);
				list_seller.add(feature);
			}
		} catch (Exception e) {
			return null;
		}
		return list_seller;
	}

	@Override
	public List<Classifcation> selClassifcation() 
	{
		List<Classifcation> list = dao.query("from Classifcation");
		return list;
	}

	@Override
	public List<Plan> selPlan(String classifcationId, String pageNo,
			String pageNum) {
		
		String hql = "from Plan where 1 = 1";
		List<Plan> list= null;
		if(classifcationId == null || classifcationId.equals(""))
		{
			
		}
		else
		{
			hql+=" and classificationId="+classifcationId;
		}
		if(pageNum == null || pageNum.equals("") || pageNum == null || pageNum.equals(""))
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		}
		return list;
		
		
	}

	@Override
	public List<Info> selInfo(String type, String pageNo, String pageNum) {
		String hql = "from Info where 1 = 1";
		List<Info> list= null;
		
		if(type == null || type.equals(""))
		{
			
		}
		else
		{
			hql+=" and type="+type;
		}
		if(pageNum == null || pageNum.equals("") || pageNum == null || pageNum.equals(""))
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		}
		return list;
	}

	@Override
	public List<Company> selCompany(String pageNo, String pageNum) {

		List<Company> list = null;
		if(pageNum == null || pageNum.equals("") || pageNum == null || pageNum.equals(""))
		{
			list = dao.query("from Company");
		}
		else
		{
			list = dao.query("from Company",Integer.valueOf(pageNo),Integer.valueOf(pageNum));
		}
		return list;
	}

	@Override
	public Checkversion getNewVersion(String appType, String versionNum) {
		List<Checkversion> list = dao.query("from Checkversion where appType = "+appType+" and versionNum >'"+versionNum+"'");
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}

}
