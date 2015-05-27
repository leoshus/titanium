/**
 * @author shangyd
 * @Date 2015年5月27日 下午10:22:10
 * Copyright (c) 2015
 **/
package com.sdw.soft.main.activemq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdw.soft.main.activemq.vo.Notify;
/**
 * 消费者进行消费
 */
public class DemoMessageListener implements MessageListener {

	private static final Logger logger = LoggerFactory.getLogger(DemoMessageListener.class);
	
	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = (ObjectMessage)message;
		try {
			Notify notify = (Notify)objectMessage.getObject();
			logger.info("consumer the title =" + notify.getTitle() +",content = " + notify.getContent());
		} catch (JMSException e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
	}

}
