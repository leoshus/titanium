/**
 * @author shangyd
 * @Date 2015年5月27日 下午9:13:38
 * Copyright (c) 2015
 **/
package com.sdw.soft.common.activemq;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMSMessageSender {

	private static final Logger logger = LoggerFactory.getLogger(JMSMessageSender.class);
	
	public static final String SEND_MESSAGE_TYPE = "MESSAGE_OBJECT";
	private static JMSMessageSender jmsSender;
	private static Map<String,Queue> queues;
	private static final String BROKER_URL = "tcp://192.168.183.126:61616";
	private JMSMessageSender(){
		queues = new ConcurrentHashMap<String, Queue>();
	}
	
	public Map<String, Queue> getQueues() {
		return queues;
	}

	private synchronized static void initSender(){
		if(jmsSender == null){
			jmsSender = new JMSMessageSender();
			Thread runner = new Thread(new JMSMessageRunner(BROKER_URL));
			runner.start();
		}
	}
	/**
	 * 将消息添加到临时队列
	 * @param queueName
	 * @param content
	 * @return
	 */
	public synchronized boolean addMessage(String queueName,Map content){
		if(content == null){
			return false;
		}
		if(queues.containsKey(queueName)){
			queues.get(queueName).add(content);
		}else{
			Queue<Map> queue = new ConcurrentLinkedQueue<Map>();
			queue.add(content);
			queues.put(queueName, queue);
		}
		return true;
	}
	
	public synchronized Map<String,Queue> copyAndClearBuffer(){
		Map<String,Queue> copy = new ConcurrentHashMap<String, Queue>(queues);
		queues.clear();
		return copy;
	}
	public static JMSMessageSender instance(){
		if(jmsSender == null){
			initSender();
		}
		return jmsSender;
	}
}
