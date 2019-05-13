package com.hk.sys.service;


import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysLog;

public interface SysLogService {

	PageObject<SysLog> doFindLog(String username, Integer pageCurrent);

}
