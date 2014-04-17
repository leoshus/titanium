package com.sdw.soft.test.web.action;

import java.util.HashMap;
import java.util.Map;

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
	@RequestMapping(value="hello2")
	public String helloJson(Model model){
		Map<String,String> messages = new HashMap<String,String>();
		messages.put("id", "123");
		messages.put("username", "Tom");
		messages.put("password", "admin123");
		messages.put("email", "test@test.com");
		model.addAttribute("messages", messages);
		return "hello";
	}
}
