package com.hk.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.common.vo.JsonResult;
import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysAdmin;
import com.hk.sys.service.SysAdminService;

@RequestMapping("/admin/")
@Controller
public class SysAdminController {
	
	@Autowired
	private SysAdminService sysAdminService;
	
	@RequestMapping("doAdminEditUI")
	public String doAdminEditUI() {
		return "sys/adminedit";
	}
	
	@RequestMapping("doAdminAdd")
	public String doAdminAdd() {
		return "sys/adminadd";
	}
	
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username,String password) {
		//通过subjectt提交用户信息，交给shiro框架进行验证
		System.out.println(username+"  "+password);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		subject.login(token);
		return new JsonResult("login ok");
	}
	
	@RequestMapping("doPwdUI")
	public String doPwd() {
		return "sys/changepwd";
	}
	
	@RequestMapping("updatePwd")
	@ResponseBody
	public JsonResult updatePwd(String username,String oldpw,String newpw1,String newpw2) {
		sysAdminService.updatePwd(username,oldpw,newpw1,newpw2);
		return new JsonResult("密码更新成功");
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
		PageObject<SysAdmin> po=sysAdminService.doFindPageObjects(username,pageCurrent);
		return new JsonResult(po);
	}
	
	@RequestMapping("dodeleteUI")
	@ResponseBody
	public JsonResult dodeleteUI(Integer...ids) {
		sysAdminService.dodeleteUI(ids);
		return new JsonResult("删除成功");
	}
	
	@RequestMapping("addAdmin")
	@ResponseBody
	public JsonResult addAdmin(String username,String pwd1,String pwd2) {
		sysAdminService.insertObject(username,pwd1,pwd2);
		return new JsonResult("添加成功");
	}
	
	//修改状态
	@RequestMapping("doStatus")
	@ResponseBody
	public JsonResult doStatus(Integer id,Integer vaild) {
		sysAdminService.doStatus(id,vaild);
		return new JsonResult("修改成功");
	}
	
}
