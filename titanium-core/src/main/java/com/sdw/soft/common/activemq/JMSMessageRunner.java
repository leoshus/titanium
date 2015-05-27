/**
 * @author shangyd
 * @Date 2015年5月27日 下午9:14:44
 * Copyright (c) 2015
 **/
package com.sdw.soft.common.activemq;

import java.io.Serializable;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送消息线程
 */
public class JMSMessageRunner implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(JMSMessageRunner.class);
	
	private static final Long SEND_INTERVAL = new Long(10 * 1000);
	private String brokerUrl;
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageProducer messageProducer;
	
	public JMSMessageRunner(String brokerUrl){
		this.brokerUrl = brokerUrl;
	}
	
	@Override
	public void run() {
		while(true){
			JMSMessageSender jmsSender = JMSMessageSender.instance();
			if(!jmsSender.getQueues().isEmpty()){
				try {
					connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,brokerUrl);
					connection = connectionFactory.createConnection();
					connection.start();
					session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
					Map<String,Queue> copyContent = jmsSender.copyAndClearBuffer();
					Set<String> queueNames = copyContent.keySet();
					if(!queueNames.isEmpty()){
						for(String queueName : queueNames){//遍历所有的队列
							destination = session.createQueue(queueName);
							messageProducer = session.createProducer(destination);
							messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
							Queue<Map> contents = copyContent.get(queueName);
							if(contents != null && contents.size() > 0 && !contents.isEmpty()){
								for(Map content : contents){//遍历同一队列下的数据
									ObjectMessage message = session.createObjectMessage();
									if(content != null && content.size() > 0 && !content.isEmpty()){
										Set<String> keys = content.keySet();
										for(String key : keys){
											Object value = content.get(key);
											if(value instanceof String){
												message.setStringProperty(key, (String)value);
											}else if(value instanceof Serializable){
												message.setObject((Serializable)value);
											}
										}
										messageProducer.send(message);
										session.commit();
										logger.info("--------------send message successsfully-------------------");
									}else{
										logger.info("消息体丢失... ...");
									}
									
								}
							}else{
								logger.info("消息体丢失... ...");
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage(),e);
				}
			}
			
			try {
				Thread.sleep(SEND_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
