package com.sdw.soft.test.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/hello")
public class HelloController {
	
	@RequestMapping(value="hello")
	public String hello(){
		
		return "hello";
	}
}
