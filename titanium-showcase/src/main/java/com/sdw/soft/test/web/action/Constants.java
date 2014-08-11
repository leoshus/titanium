/**
 * @Date 2014年8月11日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.test.web.action;

import org.springframework.core.NamedThreadLocal;

/**
 * 
 * @author syd
 */
public class Constants {
	
	public static final NamedThreadLocal<String> localConstants = new NamedThreadLocal<String>("local constants");
	
	public String getLocalConstants(){
		return localConstants.get();
	}
	public void setLocalConstants(String str){
		localConstants.set(str);
	}
}
