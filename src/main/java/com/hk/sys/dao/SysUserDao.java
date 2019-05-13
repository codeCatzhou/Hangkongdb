package com.hk.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hk.sys.entity.SysUser;

public interface SysUserDao {
	//查询页面并分页
	List<SysUser> findAllUser(@Param("username")String username,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);
	//查询记录总数
	/*查询所有记录条数*/
	int getRowCount(@Param("username")String username);
	/**更新用户账号状态*/
	int updateStatus(@Param("username")String username, @Param("status")Integer status);
	/** 添加用户信息*/
	int insertUser(SysUser sysUser);
	/**查找是否有重复用户名*/
	int findUser(@Param("username")String username);
	/*根据id删除用户*/
	int deleteUserByIds(@Param("ids")Integer... ids);
}
