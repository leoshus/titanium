package com.sdw.soft.common.auth.security.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @author syd
 * @Date 2013年12月26日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class ExtSecurityFilter extends AbstractSecurityInterceptor implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(ExtSecurityFilter.class);
	
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	
	@Override
	public void destroy() {
		logger.debug("ExtSecurityInterceptor destroy...");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		 logger.debug("ExtSecurityInterceptor doFilter...");
	        FilterInvocation fi = new FilterInvocation(req, resp, chain);
	        InterceptorStatusToken token = super.beforeInvocation(fi);
	        try {
	            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
	        } finally {
	            super.afterInvocation(token, null);
	        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		 logger.debug("ExtSecurityInterceptor init...");
	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

}
