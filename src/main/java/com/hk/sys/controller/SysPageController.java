package com.hk.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class SysPageController {
	
	//登录页面
	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "sys/login";
	}
	
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "sys/index";
	}
	
	/*测试*/
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "sys/page";
	}
	
}
