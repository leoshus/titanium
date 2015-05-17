/**
 * @author shangyd
 * @Date 2015年5月17日 上午10:41:33
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="demo")
public class DemoController {

	@RequestMapping(value="index")
	public String demo(){
		
		return "demo/index";
	}
}
