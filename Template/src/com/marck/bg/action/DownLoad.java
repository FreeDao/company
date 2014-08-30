package com.marck.bg.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.marck.common.BaseAction;
import com.marck.common.CommonUtil;

@Component("downLoad")
@Scope("prototype")
public class DownLoad extends BaseAction{

	private InputStream inputStream;
	private String contentType;
	  
    public String img() throws Exception {
    	String name = request.getParameter("name");
    	String path = CommonUtil.checkPath(projectUrl);
    	
    	File file = new File(path+"/"+name);
    	
    	inputStream = new FileInputStream(file);
    	contentType = "image/"+name.substring(name.lastIndexOf(".")+1);
        return "img";   
    }

    public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() throws Exception {
        return inputStream;   
    }

	public String getContentType() {
		return contentType;
	
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}   
	
}
