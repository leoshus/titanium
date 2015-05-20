/**
 * @author shangyd
 * @Date 2015年5月20日 下午9:46:54
 * Copyright (c) 2015
 **/
package com.sdw.soft.test.shiro;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroDemo {

	private static final Logger logger = LoggerFactory.getLogger(ShiroDemo.class);
	
	@Test
	public void test01(){
		//获取SecurityManager工厂 使用ini配置文件初始化SecurityManager
		IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//获取SecurityManager实例 并绑定SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//得到Subject及创建的用户名/密码 验证token(即 用户身份/凭证)
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("tom","123");
		try {//登录 即身份验证
			subject.login(token);
		} catch (Exception e) {
			//身份验证失败
			e.printStackTrace();
		}
		Assert.assertEquals(true, subject.isAuthenticated());
		subject.logout();//退出登录
	}
}
