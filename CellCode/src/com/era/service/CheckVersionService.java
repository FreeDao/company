package com.era.service;

import com.era.orm.Checkversion;

public interface CheckVersionService {
	
	public Checkversion getNewVersion(Integer appType,String versionNum);

}
