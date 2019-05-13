package com.hk.sys.service;

import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysUser;

public interface SysUserService {
	//加载用户信息
	PageObject<SysUser> findAllUsers(String username,Integer pageCurrent);
	//更新状态
	int updateStatus(String username, Integer status);
	//添加数据
	int insertUser(SysUser sysUser);
	//删除
	int deleteUserByIds(Integer... ids);

}
