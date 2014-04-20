package com.sdw.soft.core.webservice.test.soap.impl;

import javax.jws.WebService;

import com.sdw.soft.core.webservice.test.soap.IUserInfoService;
/**
 *  JAX-WS2.0的WebService接口定义类.
 * 
 * 使用JAX-WS2.0 annotation设置WSDL中的定义.
 * 使用WSResult及其子类类包裹返回结果.
 * 使用DTO传输对象隔绝系统内部领域对象的修改对外系统的影响.
 * 
 * name 指明wsdl中<wsdl:portType>元素的名称
 * @author hp
 *
 */
@WebService(name="UserInfoService",endpointInterface="com.sdw.soft.core.webservice.test.soap.IUserInfoService")
public class UserInfoServiceImpl implements IUserInfoService {

	@Override
	public String fetchUserInfo(String id) {
		if("123".equals(id)){
			return "id:123,username:tom,password:admin123,email:test@test.com";
		}else{
			
			return "未找到该用户信息...";
		}
	}

}
