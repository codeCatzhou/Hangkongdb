package com.hk.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.hk.common.annotation.RequiredLog;
import com.hk.sys.dao.SysLogDao;
import com.hk.sys.entity.SysAdmin;
import com.hk.sys.entity.SysLog;

@Service
@Aspect
public class SysLogAspect {
	
	@Autowired
	private SysLogDao sysLogDao;
	
	@Pointcut("@annotation(com.hk.common.annotation.RequiredLog)")
	public void logPointCut() {}
	
	@Around("logPointCut()")
	public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable {
		Object result = jp.proceed();
		saveLog(jp);//执行日志获取
		return result;
	}

	private void saveLog(ProceedingJoinPoint jp) throws Exception {
		//获取登录的管理员账号
		SysAdmin admin = (SysAdmin) SecurityUtils.getSubject().getPrincipal();
		String username = admin.getUsername();
		//获取操作名称
		Object target = jp.getTarget();
		MethodSignature ms = (MethodSignature) jp.getSignature();
		String methodName = ms.getMethod().getName();
		Method targetMethod = target.getClass().getDeclaredMethod(methodName, ms.getParameterTypes());
		RequiredLog rLog = targetMethod.getDeclaredAnnotation(RequiredLog.class);
		String operation=methodName;
		if(rLog!=null&&!StringUtils.isEmpty(rLog.value())) {
			operation=rLog.value();
		}
		SysLog log = new SysLog();
		log.setUsername(username);
		log.setOperation(operation);
		log.setCreatedTime(new Date());
		sysLogDao.insertObject(log);
	}
}
