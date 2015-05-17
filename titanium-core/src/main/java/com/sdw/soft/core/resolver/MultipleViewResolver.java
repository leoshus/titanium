/**
 * @author shangyd
 * @Date 2015年5月17日 上午11:15:50
 * Copyright (c) 2015
 **/
package com.sdw.soft.core.resolver;

import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;
/**
 * 多视图解析适配器
 */
public class MultipleViewResolver extends AbstractCachingViewResolver implements Ordered {

	private static final Logger logger = Logger.getLogger(MultipleViewResolver.class);
	
	private Map<String,ViewResolver> resolvers ;
	private int order = Integer.MIN_VALUE;

	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public void setResolvers(Map<String, ViewResolver> resolvers) {
		this.resolvers = resolvers;
	}
	
	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		logger.info("multipleViewResolver log viewName =" + viewName);
		int index = viewName.indexOf(".");
		if(index == -1){
			return null;
		}
		String suffix = viewName.substring(index + 1);
		ViewResolver resolver = resolvers.get(suffix);
		if(resolver != null)
			return resolver.resolveViewName(viewName, locale);
		return null;
	}
	/*@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
	
		return null;
	}*/

}
