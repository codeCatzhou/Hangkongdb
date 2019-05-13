package com.hk.view.dao;

import org.apache.ibatis.annotations.Param;

import com.hk.sys.entity.SysUser;

public interface ViewUserDao {

	int checkUser(String username);

	int insertUser(SysUser sysUser);

	SysUser findByUsername(String username);

	int upByUserbname(SysUser sysUser);

	int updatePwd(@Param("username")String username,@Param("password")String password, @Param("salt")String salt);

	SysUser findUser(String username);
	
}
