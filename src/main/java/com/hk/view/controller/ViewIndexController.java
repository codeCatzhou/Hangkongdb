package com.hk.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.common.vo.JsonResult;
import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysOrder;
import com.hk.sys.entity.SysUser;
import com.hk.view.service.ViewIndexService;
import com.hk.view.vo.ViewCountSeat;
import com.hk.view.vo.ViewFightSeat;
import com.hk.view.vo.ViewOrderFight;

@RequestMapping("/view/")
@Controller
public class ViewIndexController {
	
	@Autowired
	private ViewIndexService viewIndexService;
	
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "sys/page";
	}
	
	@RequestMapping("goLogin")
	@ResponseBody
	public JsonResult goLogin(String username,String password) {
		viewIndexService.goLogin(username,password);
		return new JsonResult("login ok");
	}
	
	@RequestMapping("goSign")
	@ResponseBody
	public JsonResult goSign(SysUser sysUser) {
		viewIndexService.insertUser(sysUser);
		return new JsonResult("注册成功");
	}
	
	//获取用户数据
	@RequestMapping("readUser")
	@ResponseBody
	public JsonResult readUser(String username) {
		return new JsonResult(viewIndexService.readUser(username));
	}
	
	//修改用户数据
	@RequestMapping("upByUsername")
	@ResponseBody
	public JsonResult upByUsername(SysUser sysUser) {
		System.out.println(sysUser);
		viewIndexService.upByUsername(sysUser);
		return new JsonResult("更新成功");
	}
	
	@RequestMapping("upPwd")
	@ResponseBody
	public JsonResult upPwd(String username,String oldpwd,String newpwd1,String newpwd2) {
		viewIndexService.upPwd(username,oldpwd,newpwd1,newpwd2);
		return new JsonResult("更新成功");
	}
	
	@RequestMapping("goSearch")
	@ResponseBody
	public JsonResult goSearch(String fromAdd,String toAdd,String startTime,Integer pageCurrent) {
		PageObject<ViewFightSeat> po = viewIndexService.goSearch(fromAdd,toAdd,startTime,pageCurrent);
		return new JsonResult(po);
	}
	
	@RequestMapping("gofightUI")
	@ResponseBody
	public JsonResult gofightUI(String fightId) {
		ViewFightSeat obj=viewIndexService.gofightUI(fightId);
		return new JsonResult(obj);
	}
	
	//生成订单
	@RequestMapping("creatOrder")
	@ResponseBody
	public JsonResult creatOrder(SysOrder sysOrder,Integer seatType,String password) {
		viewIndexService.creatOrder(sysOrder,seatType,password);
		return new JsonResult("create order ok");
	}
	
	//查询订单
	@RequestMapping("goFindOrder")
	@ResponseBody
	public JsonResult goFindOrder(String username,Integer pageCurrent) {
		PageObject<ViewOrderFight> po =viewIndexService.findOrder(username,pageCurrent);
		return new JsonResult(po);
	}
	
	@RequestMapping("goOrderOutUI")
	@ResponseBody
	public JsonResult goOrderOutUI(String fightId) {
		ViewCountSeat count = viewIndexService.findCountSeat(fightId);
		return new JsonResult(count);
	}
	
	//退票
	@RequestMapping("reticket")
	@ResponseBody
	public JsonResult reticket(Integer orderId) {
		viewIndexService.reticket(orderId);
		return new JsonResult("退票成功");
	}
	
	//重设密码验证信息
	@RequestMapping("resetpw")
	@ResponseBody
	public JsonResult resetpw(String username,String identity) {
		viewIndexService.resetpw(username,identity);
		return new JsonResult("验证成功，请重新设置密码");
	}
	@RequestMapping("inpwd")
	@ResponseBody
	public JsonResult inpwd(String username,String newpwd1,String newpwd2) {
		viewIndexService.inpwd(username,newpwd1,newpwd2);
		return new JsonResult("修改成功");
	}
	//删除订单
	@RequestMapping("deleteOrder")
	@ResponseBody
	public JsonResult deleteOrder(Integer orderId) {
		viewIndexService.deleteOrder(orderId);
		return new JsonResult("删除成功");
	}
	
}
