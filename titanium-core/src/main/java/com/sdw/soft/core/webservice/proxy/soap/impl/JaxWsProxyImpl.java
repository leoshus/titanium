package com.sdw.soft.core.webservice.proxy.soap.impl;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.sdw.soft.core.webservice.proxy.soap.IJaxWsProxy;
import com.sdw.soft.core.webservice.test.soap.IUserInfoService;

public class JaxWsProxyImpl implements IJaxWsProxy {

	private String address;
	
	private String operate;
	
	public void setAddress(String address) {
		this.address = address;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	@Override
	public String invoke(String reqXml) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setAddress(address);
		factoryBean.setServiceClass(IUserInfoService.class);
		IUserInfoService userService = (IUserInfoService)factoryBean.create();
		Method method = userService.getClass().getDeclaredMethod(operate, String.class);
		return (String) method.invoke(userService, reqXml);
	}

}
