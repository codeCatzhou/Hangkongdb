package com.hk.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.common.vo.JsonResult;
import com.hk.sys.entity.SysSeat;
import com.hk.sys.service.SysSeatService;

@RequestMapping("/seat/")
@Controller
public class SysSeatController {
	
	@Autowired
	private SysSeatService sysSeatService;
	
	@RequestMapping("doSeatUI")
	public String doSeatUI() {
		return "sys/seat";
	}
	
	@RequestMapping("doFindSeat")
	@ResponseBody
	public JsonResult doFindSeat(String fightId,Integer pageCurrent) {
		return new JsonResult(sysSeatService.findSeat(fightId, pageCurrent));
	}
	
	@RequestMapping("FindSeatByFightId")
	@ResponseBody
	public JsonResult findSeatByFightId(String fightId) {
		return new JsonResult(sysSeatService.findSeatByFightId(fightId));
	}
	
	@RequestMapping("doUpdateSeat")
	@ResponseBody
	public JsonResult doUpdateSeat(SysSeat sysSeat) {
		sysSeatService.doUpdateSeat(sysSeat);
		return new JsonResult("座位信息更新成功");
	}
}
