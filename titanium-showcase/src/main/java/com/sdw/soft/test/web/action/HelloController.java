package com.sdw.soft.test.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sdw.soft.core.webservice.proxy.soap.IJaxWsProxy;

@Controller
@RequestMapping(value="/hello")
public class HelloController {
	
	@Autowired
	private IJaxWsProxy jaxWsProxy;
	
	@RequestMapping(value="hello")
	public String hello(HttpServletRequest request,Model model){
		String message = "";
		try {
			Constants.localConstants.set("test ThreadLocal constants");
			request.getSession().setAttribute("msg", "test ThreadLocal constants");
			System.out.println(">>>>>>>>>>>>>>>>>" + Constants.localConstants.get());
			message = jaxWsProxy.invoke("123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("hello", message);
		return "hello";
	}
	/**
	 * 俩次ajax请求 servlet容器会分配俩个线程  所以通过ThreadLocal变量无法在两次请求中进行通信
	 * @param model
	 * @return
	 */
	@RequestMapping("/fetchContants")
	public String fetchContants(HttpServletRequest request,Model model){
		model.addAttribute("msg", Constants.localConstants.get());
		System.out.println("============"+Constants.localConstants.get()+","+request.getSession().getAttribute("msg"));
		return null;
	}
	@RequestMapping(value="hello2")
	public String helloJson(Model model){
		Map<String,String> messages = new HashMap<String,String>();
		messages.put("id", "123");
		messages.put("username", "Tom");
		messages.put("password", "admin123");
		messages.put("email", "test@test.com");
		String message = "";
		try {
			message = jaxWsProxy.invoke("1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		messages.put("webServiceTest", message);
		model.addAttribute("messages", messages);
		return "hello";
	}
}
