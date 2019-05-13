package com.hk.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hk.sys.entity.SysLog;

public interface SysLogDao {
	
	//查询总记录
	int getRowCount(@Param("username")String username);
	
	//查询所有记录
	List<SysLog> doFingLog(@Param("username")String username, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	void insertObject(SysLog log);

}
