package com.hk.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.common.vo.JsonResult;
import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysFight;
import com.hk.sys.entity.SysSeat;
import com.hk.sys.service.SysFightService;

@RequestMapping("/fight/")
@Controller
public class SysFightController {
	
	@Autowired
	SysFightService sysFightService;
	
	@RequestMapping("doFightUI")
	public String doFightUI() {
		return "sys/fight";
	}
	
	@RequestMapping("doFightEditUI")
	public String doSeatEditUI() {
		return "sys/fightedit";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String fromAdd,String toAdd,Integer pageCurrent) {
		PageObject<SysFight> pageObjects = sysFightService.doFindbyAdd(fromAdd, toAdd, pageCurrent);
		return new JsonResult(pageObjects);
	}
	
	@RequestMapping("doStatus")
	@ResponseBody
	public JsonResult doStatus(String fightId,Integer status) {
		sysFightService.updateStatus(fightId,status);
		return new JsonResult("update ok");
	}
	
	@RequestMapping("doSaveObjects")
	@ResponseBody
	public JsonResult doSaveObjects(SysFight sysFight,SysSeat sysSeat) {
		sysFightService.saveObjects(sysFight,sysSeat);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("dodeleteUI")
	@ResponseBody
	public JsonResult dodeleteUI(Integer...ids) {
		sysFightService.deleteObjects(ids);
		return new JsonResult("删除成功");
	}
	
	@RequestMapping("updateFind")
	@ResponseBody
	public JsonResult updateFindUI(Integer id) {
		SysFight obj = sysFightService.updateFind(id);
		System.out.println(obj);
		return new JsonResult(obj);
	}
	
	@RequestMapping("doUpdateFight")
	@ResponseBody
	public JsonResult doUpdateFight(SysFight sysFight) {
		sysFightService.updateFight(sysFight);
		return new JsonResult("航班信息更新成功");
	}
}
