package com.hk.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.common.vo.JsonResult;
import com.hk.sys.service.SysLogService;

@RequestMapping("/log/")
@Controller
public class SysLogController {
	
	@Autowired
	private SysLogService sysLogService;
	
	@RequestMapping("doLogUI")
	public String doLogUI() {
		return "sys/log";
	}
	
	@RequestMapping("doFindLog")
	@ResponseBody
	public JsonResult doFindLog(String username,Integer pageCurrent) {
		return new JsonResult(sysLogService.doFindLog(username,pageCurrent));
	}
}
