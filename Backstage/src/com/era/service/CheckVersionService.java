package com.era.service;

import java.util.List;

import com.era.orm.Checkversion;

public interface CheckVersionService {
	
	public Checkversion getNewVersion(Integer appType,String versionNum);

	public List<Checkversion> selCheckVersion(String name,String page, String rows);

	public int numberCheckVersion(String name);

	public boolean delCheckVersion(int parseInt);

	public boolean addCheckVersion(Checkversion check);

}
