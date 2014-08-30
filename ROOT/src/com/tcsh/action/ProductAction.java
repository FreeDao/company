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
import com.tcsh.model.local.App;
import com.tcsh.model.local.Img;
import com.tcsh.model.local.Product;
import com.tcsh.model.local.Top;
import com.tcsh.service.ProductService;


@Component("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction{

	@Autowired
	private ProductService productService;

	private PageUtil pu;
	private Integer pageNow;
	private Integer limit = 10;
	
	private List<File> files;
	private List<String> filesContentType;
	private List<String> filesFileName;
	
	private Product product;
	
	/**
	 * 查询所有产品信息
	 * @return
	 */
	public String index(){
		try {
			pu = productService.findProductList(pageNow,limit);
			pu.setUrl("bg/product!index");
			return "index";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("ProductAction中方法index出错");
		}
		return ERROR;
	}

	/**
	 * 删除指定产品信息
	 * @return
	 */
	public String delProduct(){
		try {
			productService.delObjById(Product.class,product.getId());
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("ProductAction中方法delProduct出错");
			return index();
		}
	}
	
	/**
	 * 跳转到添加和修改页面
	 * @return
	 */
	public String goAddOrEdit(){
		try {
			if( null != product){
				product = (Product) productService.findObjById(Product.class,product.getId());
			}
			return "addOrEdit";
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("ProductAction中方法goAddOrEdit出错");
		}
		return ERROR;
	}
	
	/**
	 * 添加或修改信息
	 * @return
	 */
	public String addOrEdit(){
		try {
			Product e = new Product();
			if( null != product.getId() ){
				e = (Product) productService.findObjById(Product.class, product.getId());
				product.setAddTime(e.getAddTime());
				product.setLogo(e.getLogo());
				List<Img> imgs = productService.findAllImgByPid(product.getId());
				for(Img i : imgs){
					productService.delObj(i);
				}
			}else{
				product.setAddTime(DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));
				product.setLogo(projectUrl+"/img/zw.jpg");
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
						  product.setLogo(projectUrl + "/uploadImg/"+now+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
						  productService.saveOrUpdateObj(product);
					  }else{
						  Img img = new Img();
						  img.setImgUrl(projectUrl + "/uploadImg/"+now+"."+filesContentType.get(i).substring(filesContentType.get(i).indexOf("/")+1));
						  img.setName(filesFileName.get(i));
						  img.setParentId(product.getId());
						  img.setType(0);
						  productService.saveOrUpdateObj(img);
					  }
					  //n.setLogo("http://www.tcshenghuo.org:8080/Greening/uploadImg/"+now+"."+fileContentType.substring(fileContentType.indexOf("/")+1));
				}
			}else{
				productService.saveOrUpdateObj(product);
			}
			request.setAttribute("msg", 1);
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("msg", 0);
			log.debug("ProductAction中方法addOrEdit出错");
			return index();
		}
	}

	/**
	 * 置顶开关
	 * @return
	 */
	public String topToggle(){
		try {
			List<Top> tops = productService.findTop(product.getId());
			if(tops.size() != 0){
				for(Top t : tops){
					productService.delObj(t);
				}
			}else{
				Top top = new Top();
				top.setAddTime(DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));
				top.setParentId(product.getId());
				top.setType(2);
				productService.saveOrUpdateObj(top);
			}
			return index();
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", 0);
			log.debug("ProductAction中方法topToggle出错");
			return index();
		}
	}
	
	/**
	 * 上传APP
	 * @return
	 */
	public String uploadApp(){
		try {
			String name =request.getParameter("name");
			
			App app = new App();
			
			app.setName(name);
			
			if(null != files){
				//上传图片存放路径
				String filepath=request.getRealPath("/uploadImg");
				//文件上传.
				for (int i = 0; i < files.size(); i++) {
					File f = new File(filepath+"/"+filesFileName.get(i));
					if(!f.exists()){
						f.createNewFile();
					}
					
					FileUtils.copyFile(files.get(i), f);

					app.setUrl(projectUrl + "/uploadImg/"+filesFileName.get(i));
					
				}
			}
			
			productService.saveOrUpdateObj(app);
			
			request.setAttribute("app", app);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("上传app出错");
		}
		return "uploadApp";
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
