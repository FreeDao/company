package com.gre.bg.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gre.bg.service.JobService;
import com.gre.common.BaseAction;
import com.gre.common.DateUtil;
import com.gre.common.PageUtil;
import com.gre.common.model.Job;

@Component("jobAction")
@Scope("prototype")
public class JobAction extends BaseAction{

	@Autowired
	private JobService jobService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit;
	
	private Job job;
	
	/**
	 * 查询所有求职信息
	 * @return
	 */
	public String index(){
		try {
			pu = jobService.findAllList(Job.class,pageNow,limit);
			pu.setUrl("bg/job!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}

	/**
	 * 删除指定求职信息
	 * @return
	 */
	public String delJob(){
		try {
			jobService.delObjById(Job.class,job.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("JobAction中方法delJob出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != job ){
				job = (Job) jobService.findObjById(Job.class,job.getId());
			}
			return "addOrEdit";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEdit(){
		try {
			if( null != job.getId() && !"".equals(job.getId()) ){
				Job j = (Job) jobService.findObjById(Job.class, job.getId());
				job.setAddTime(j.getAddTime());
			}else{
				job.setAddTime(DateUtil.getNowString("yyyy-MM-dd"));
			}
			jobService.saveOrUpdateObj(job);
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("JobAction中方法addOrEdit出错");
			return index();
		}
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
}
