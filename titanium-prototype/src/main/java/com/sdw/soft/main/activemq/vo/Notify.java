/**
 * @author shangyd
 * @Date 2015年5月27日 下午10:07:37
 * Copyright (c) 2015
 **/
package com.sdw.soft.main.activemq.vo;

import java.io.Serializable;

public class Notify implements Serializable {

	private static final long serialVersionUID = -3140971785893747603L;

	private String title;
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
