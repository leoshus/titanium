package com.sdw.soft.core.webservice.proxy.soap;

import java.lang.reflect.InvocationTargetException;


public interface IJaxWsProxy {
	
	public String invoke(String reqXml) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
}
