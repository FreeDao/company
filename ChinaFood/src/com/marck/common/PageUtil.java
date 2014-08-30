package com.marck.common;

import java.util.List;

/**
 * 用于分页的工具类
 * @author 莫取网名
 */
public class PageUtil {
	
	//默认每页显示条数,全局变量便于修改
	public static Integer globalLimit = 10;
	
	public List<?> data;		//返回list数据
	public int total = 0; 		//总记录
	public int pageTotal = 1; // 总页数
	public int pageNow = 1;		//当前页数
	public int limit = 10;		//每页显示记录数量
	public int navigateNum = 8;	//导航页码数量
	public int[] navigatePages; //导航页码
	public String url = "";
	
	public boolean hasPre = false;   //是否有上一页
	public boolean hasNext = false;  //是否有下一页
	public boolean isFrist = false; //是否是首页
	public boolean isLast = false;  //是否是最后一页
	
	public PageUtil(){
	}
	
	public PageUtil(int total,int pageNow){
		init(total,pageNow);
	}
	
	public PageUtil(int total,int pageNow,int limit){
		setLimit(limit);
		init(total,pageNow);
	}
	
	public void init(int total,int pageNow){
		this.total = total;
		//设置总页数
		this.pageTotal = (total-1)/limit+1;
		
		//纠错
		if(pageNow < 1){
			this.pageNow = 1;
		}else if(pageNow > this.pageTotal){
			this.pageNow = this.pageTotal;
		}else{
			this.pageNow = pageNow;
		}
		
		//导航页计算
		getNavigate();
		
		//检测边界问题
		check();
	}
	
	public void check(){
		isFrist = pageNow == 1;
		isLast = pageNow == pageTotal;
		hasPre = pageNow > 1;
		hasNext = pageNow < pageTotal;
	}
	
	
	public void getNavigate(){
		if(pageTotal <= navigateNum){
			navigatePages = new int[pageTotal];
			for(int i = 0 ; i < pageTotal ; i++){
				navigatePages[i] = i+1;
			}
		}else {
			navigatePages = new int[navigateNum];
			int start = pageNow - navigateNum/2;
			int end = pageNow + navigateNum/2;
			if(start <= 0){
				for(int i = 0 ; i < navigateNum ; i++){
					navigatePages[i] = i+1;
				}
			}else if(end > pageTotal){
				end = pageTotal;
				for(int i = navigateNum - 1 ; i >= 0  ; i-- ){
					navigatePages[i] = end--;
				}
			}else{
				for(int i = 0 ; i < navigateNum ; i++ ){
					navigatePages[i] = start++;
				}
			}
		}
	}

	public List<?> getData() {
		return data;
	}

	public int getTotal() {
		return total;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public int getPageNow() {
		return pageNow;
	}

	public int getLimit() {
		return limit;
	}

	public int getNavigateNum() {
		return navigateNum;
	}

	public int[] getNavigatePages() {
		return navigatePages;
	}

	public boolean getHasPre() {
		return hasPre;
	}

	public boolean getHasNext() {
		return hasNext;
	}

	public boolean getIsFrist() {
		return isFrist;
	}

	public boolean getIsLast() {
		return isLast;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setNavigateNum(int navigateNum) {
		this.navigateNum = navigateNum;
	}

	public void setNavigatePages(int[] navigatePages) {
		this.navigatePages = navigatePages;
	}

	public void setHasPre(boolean hasPre) {
		this.hasPre = hasPre;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public void setIsFrist(boolean isFrist) {
		this.isFrist = isFrist;
	}

	public void setIsLast(boolean isLast) {
		this.isLast = isLast;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	}
