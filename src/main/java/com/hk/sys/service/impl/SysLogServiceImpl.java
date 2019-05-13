package com.hk.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.common.exception.ServiceException;
import com.hk.common.utils.PageUtil;
import com.hk.common.vo.PageObject;
import com.hk.sys.dao.SysLogDao;
import com.hk.sys.entity.SysLog;
import com.hk.sys.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService{
	
	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public PageObject<SysLog> doFindLog(String username, Integer pageCurrent) {
		int rows=sysLogDao.getRowCount(username);
		if(rows==0)
			throw new ServiceException("没有找到数据");
		int pageSize=12;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysLog> records=sysLogDao.doFingLog(username,startIndex,pageSize);
		PageObject<SysLog> po = PageUtil.newPageObject(pageCurrent, rows, pageSize, records);
		return po;
	}

}
