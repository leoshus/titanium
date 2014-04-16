package com.sdw.soft.test.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/hello")
public class HelloController {
	
	@RequestMapping(value="hello")
	public String hello(Model model){
		model.addAttribute("hello", "hello,Spring MVC...");
		return "hello";
	}
}
