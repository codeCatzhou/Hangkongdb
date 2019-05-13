package com.hk.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.common.vo.JsonResult;
import com.hk.sys.service.SysOrderServce;

@RequestMapping("/order/")
@Controller
public class SysOrderController {
	
	@Autowired
	private SysOrderServce sysOrderServce;
	
	@RequestMapping("doOrderUI")
	public String doOrderUI() {
		return "sys/order";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		return new JsonResult(sysOrderServce.fingAllByUsername(username,pageCurrent));
	}
	
	@RequestMapping("deleteUI")
	@ResponseBody
	public JsonResult deleteUI(Integer...ids) {
		sysOrderServce.deleteOrders(ids);
		return new JsonResult("删除成功");
	}
}
