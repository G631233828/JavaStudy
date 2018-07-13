package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String helloBoot(){
		
		return "hello";
	}

	@RequestMapping("/test")
	public String test() {
	    System.out.println("12313123");

	    return "success";
	}
}
