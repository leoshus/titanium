package com.sdw.soft.common.auth.security.manager;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author syd
 * @Date 2013年12月26日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class ExtAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if(configAttributes == null){
			return ;
		}
		//所请求的资源拥有的权限(一个资源对多个权限)   
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while(iterator.hasNext()){
			ConfigAttribute configAttribute = iterator.next();
			//访问所请求资源所需要的权限   
			String needPermission = configAttribute.getAttribute();
			//用户所拥有的权限authentication 
			for(GrantedAuthority ga : authentication.getAuthorities()){
				if(needPermission.equals(ga.getAuthority())){
					return ;
				}
			}
		}
		throw new AccessDeniedException("Access Denied");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
