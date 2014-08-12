/**
 * @Date 2014年8月12日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author syd
 */
public class StopWatchHandlerInterceptor extends HandlerInterceptorAdapter {

	public NamedThreadLocal<Long> startTime = new NamedThreadLocal<Long>("stopwatch---strattime"); 

	public Long getstartTime(){
		return startTime.get();
	}
	
	public void setStartTime(Long time){
		startTime.set(time);
	}
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long start = System.currentTimeMillis();
		startTime.set(start);
		System.out.println(String.format("%s start time %d millis",request.getRequestURI(),start));
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long endTime = System.currentTimeMillis();
		long consumeTime = endTime - startTime.get();
		if(consumeTime > 500){
			System.out.println(String.format("%s consume %d millis",request.getRequestURI(),consumeTime));
		}
		System.out.println(String.format("%s consume %d millis",request.getRequestURI(),consumeTime));
		super.afterCompletion(request, response, handler, ex);
	}
}
