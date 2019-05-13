package com.hk.common.exception;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.common.vo.JsonResult;
@ControllerAdvice
public class GlobaExcptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult doHandleRuntimeException(RuntimeException e) {
		e.printStackTrace();
		return new JsonResult(e.getMessage());
	}
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult doHandleServiceException(ServiceException e) {
		e.printStackTrace();
		return new JsonResult(e);
	}
	
	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public JsonResult doHandleShiroException(
			ShiroException e){
		JsonResult r=new JsonResult();
		r.setState(0);//执行到此方法的肯定时出异常了
		if(e instanceof UnknownAccountException){
			r.setMessage("账户不存在");
		}else if(e instanceof LockedAccountException){
			r.setMessage("账户已经被禁用");
		}else if(e instanceof IncorrectCredentialsException){
			r.setMessage("密码不正确");
		}else {//还有其它异常
			r.setMessage(e.getMessage());
		}
		return r;
	}
	
}
