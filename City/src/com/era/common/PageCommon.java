package com.era.common;

/**
 * 用于分页处理
 */
public class PageCommon {
	public static final int PAGENUM = 8;
	private int nowPage = 0;
	private int countNum = 0;
	public int getPAGENUM() {
		return PAGENUM;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		if (nowPage <= 0)
			this.nowPage = 1;
		else
			this.nowPage = nowPage;
	}

	public int getNextPage() {
		return nowPage + 1;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getCountNum() {
		return countNum;
	}

	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}

	/**
	 * 返回当前页第一条记录数
	 * 
	 * @return
	 */
	public int getFireNum() {
		int num = (nowPage - 1) * PAGENUM;
		return num;
	}

	/**
	 * 返回当前最大记录数
	 * 
	 * @return
	 */
	public int getLastNum() {
		int lastNum = getFireNum() + PAGENUM;

		if (lastNum > countNum)
			return PAGENUM;
		else
			return lastNum;

	}

	/**
	 * 返回总页数
	 * 
	 * @return
	 */
	public int getAllPage() {
		int allPage = (countNum / PAGENUM) + 1;
		if (countNum <= 0)
			return 0;
		else if (countNum % PAGENUM == 0)
			return allPage - 1;
		else
			return allPage;
	}
}
