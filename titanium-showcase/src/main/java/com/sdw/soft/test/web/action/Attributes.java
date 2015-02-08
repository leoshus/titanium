/**
 * @Date 2014年9月22日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.test.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sdw.soft.test.vo.User;

/**
 * 
 * @author syd
 */
@Controller
@RequestMapping(value="/attributes")
@SessionAttributes("user")
public class Attributes {

	//@ModelAttribute("message")
	public String setCommonData1(@RequestParam String param,Model model){
		//model.addAttribute("message", param);
		return param;
	}
	@ModelAttribute("user")
	public User setCommonData(@RequestParam String param,Model model){
		return new User("Rose","123",23);
	}
	@RequestMapping(value="/hello")
	public String hello(){
		
		return "attributes";
	}
	
	
	@RequestMapping(value="/attributes")
	@ModelAttribute("message")
	public String attributes(){
		
		return "helloattribute";				
	}
	
	
	@ModelAttribute
	public User fetchUser(){
		
		return new User();
	}
	
	@RequestMapping(value="/onParam")
	public String onParam(@ModelAttribute("user") User user){
		user.setUsername("Tom");
		return "attributes";
	}
	
	@RequestMapping(value="/complete")
	public String complete(SessionStatus sessionStatus){
		sessionStatus.setComplete();//清除@SessionAttributes在Session中存储的对象信息
		return "complete";
	}
}
