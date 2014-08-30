package com.era.service;

import java.util.List;

import com.era.orm.Backmessage;

public interface BackMessageService {
	
	/*
	 * 用户反馈信息
	 */
	public boolean putBackMessage(Backmessage backmessage);

	public List<Backmessage> selPutBackMessage(String page, String rows);

	public int numberPutBackMessage();

	public boolean delPutBackMessage(int parseInt);

}
