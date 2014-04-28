/**
 * @Date 2014年4月28日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.sys.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author syd
 */
@Controller
@RequestMapping("/pub")
public class SigninController {

	@RequestMapping("/landing")
	public String signin(){
		
		return "/pub/signin";
	}
}
