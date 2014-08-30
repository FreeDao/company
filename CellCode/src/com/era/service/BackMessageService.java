package com.era.service;

import com.era.orm.Backmessage;

public interface BackMessageService {
	
	/*
	 * 用户反馈信息
	 */
	public boolean putBackMessage(Backmessage backmessage);

}
