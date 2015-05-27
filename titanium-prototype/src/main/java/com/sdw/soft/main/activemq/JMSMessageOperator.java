/**
 * @author shangyd
 * @Date 2015年5月27日 下午10:04:15
 * Copyright (c) 2015
 **/
package com.sdw.soft.main.activemq;

import java.util.HashMap;
import java.util.Map;

import com.sdw.soft.common.activemq.JMSMessageSender;
import com.sdw.soft.main.activemq.vo.Notify;

public class JMSMessageOperator {

	private static JMSMessageSender jmsSender;
	
	public void notifyUser(){
		jmsSender = JMSMessageSender.instance();
		Map<String,Notify> content = new HashMap<String,Notify>();
		Notify notify = new Notify();
		notify.setTitle("hello");
		notify.setContent("hello activeMQ!");
		content.put(JMSMessageSender.SEND_MESSAGE_TYPE, notify);
		jmsSender.addMessage("testQueue", content);
	}
}
