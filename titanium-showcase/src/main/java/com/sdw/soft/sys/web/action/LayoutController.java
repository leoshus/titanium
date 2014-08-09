/**
 * @Date 2014年8月9日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.sys.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author syd
 */
@Controller
public class LayoutController {

	@RequestMapping("/layout")
	public ModelAndView toIndex(){
		
		return new ModelAndView("/layout/index");
	}
}
