package com.hk.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.common.vo.JsonResult;
import com.hk.sys.entity.SysUser;
import com.hk.sys.service.SysUserService;

@RequestMapping("/user/")
@Controller
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("doUserUI")
	public String doUserUI() {
		return "sys/user";
	}
	
	@RequestMapping("doUserEditUI")
	public String doUserEditUI() {
		return "sys/useredit";
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		return new JsonResult(sysUserService.findAllUsers(username, pageCurrent));
	}
	
	@RequestMapping("doStatus")
	@ResponseBody
	public JsonResult doStatus(String username,Integer status) {
		sysUserService.updateStatus(username,status);
		return new JsonResult("更新成功");
	}
	
	@RequestMapping("insertUser")
	@ResponseBody
	public JsonResult insertUser(SysUser sysUser) {
		sysUserService.insertUser(sysUser);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("dodeleteUI")
	@ResponseBody
	public JsonResult dodeleteUI(Integer...ids) {
		System.out.println(ids);
		sysUserService.deleteUserByIds(ids);
		return new JsonResult("删除成功");
	}
	
	
}
