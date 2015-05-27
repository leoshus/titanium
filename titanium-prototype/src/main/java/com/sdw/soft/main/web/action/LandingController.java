/**
 * @author shangyd
 * @Date 2015年5月17日 下午9:26:03
 * Copyright (c) 2015
 **/
package com.sdw.soft.main.web.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sdw.soft.main.activemq.JMSMessageOperator;

@Controller
@RequestMapping(value="/pub")
public class LandingController {

	private static final Logger logger = LoggerFactory.getLogger(LandingController.class);
	
	@Resource(name="jmsMessageOperator")
	private JMSMessageOperator operator;
	
	@RequestMapping(value="/landing")
	public String landing(){
		logger.info("landing ... ...");
		return "landing/login";
	}
	
	@RequestMapping(value="/index")
	public String forwardIndex(){
		operator.notifyUser();
		return "main/index";
	}
}
