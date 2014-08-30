package com.tcsh.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tcsh.common.BaseAction;
import com.tcsh.common.DateUtil;
import com.tcsh.common.PageUtil;
import com.tcsh.model.local.Img;
import com.tcsh.model.local.News;
import com.tcsh.model.local.Top;
import com.tcsh.service.NewsService;


@Component("newsAction")
@Scope("prototype")
public class NewsAction extends BaseAction{

	@Autowired
	private NewsService newsService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit = 10;
	
	private List<File> files;
	private List<String> filesContentType;
	private List<String> filesFileName;
	
	private News news;
	
	/**
	 * 查询所有新闻信息
	 * @return
	 */
	public String index(){
		try {
			pu = newsService.findNewsList(pageNow,limit);
			pu.setUrl("bg/news!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("NewsAction中方法index出错");
		}
		return ERROR;
	}

	/**
	 * 删除指定新闻信息
	 * @return
	 */
	public String delNews(){
		try {
			newsService.delObjById(News.class,news.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("NewsAction中方法delNews出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != news){
				news = (News) newsService.findObjById(News.class,news.getId());
			}
			return "addOrEdit";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("NewsAction中方法goAddOrEdit出错");
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEdit(){
		try {
			News e = new News();
			if( null != news.getId() ){
				e = (News) newsService.findObjById(News.class, news.getId());
				news.setAddTime(e.getAddTime());
				news.setLogo(e.getLogo());
				List<Img> imgs = newsService.findAllImgByPid(news.getId());
				for(Img i : imgs){
					newsService.delObj(i);
				}
			}else{
				news.setAddTime(DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));
				news.setLogo(projectUrl+"/img/zw.jpg");
			}
			
			if(null != files){
				//上传图片存放路径
				String filepath=request.getRealPath("/uploadImg");
				//图片上传
				String now = DateUtil.getNowString();
				for (int i = 0; i < files.size(); i++) {
					System.out.println(filepath+"/"+now+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
					File f = new File(filepath+"/"+now+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
					if(!f.exists()){
						f.createNewFile();
					}
					
					FileUtils.copyFile(files.get(i), f);
					
					  if( i == 0){
						  news.setLogo(projectUrl + "/uploadImg/"+now+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
						  newsService.saveOrUpdateObj(news);
					  }else{
						  Img img = new Img();
						  img.setImgUrl(projectUrl + "/uploadImg/"+now+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
						  img.setName(filesFileName.get(i));
						  img.setParentId(news.getId());
						  img.setType(0);
						  newsService.saveOrUpdateObj(img);
					  }
					  //n.setLogo("http://www.tcshenghuo.org:8080/Greening/uploadImg/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
				}
			}else{
				newsService.saveOrUpdateObj(news);
			}
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("msg", 0);
			log.debug("NewsAction中方法addOrEdit出错");
			return index();
		}
	}

	/**
	 * 置顶开关
	 * @return
	 */
	public String topToggle(){
		try {
			List<Top> tops = newsService.findTop(news.getId());
			if(tops.size() != 0){
				for(Top t : tops){
					newsService.delObj(t);
				}
			}else{
				Top top = new Top();
				top.setAddTime(DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));
				top.setParentId(news.getId());
				top.setType(1);
				newsService.saveOrUpdateObj(top);
			}
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("NewsAction中方法topToggle出错");
			return index();
		}
	}
	
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<String> getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}

	public List<String> getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
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
	
}
