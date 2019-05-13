package com.hk.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hk.sys.entity.SysAdmin;

public interface SysAdminDao {
	
	SysAdmin findUserByUserName(String username);

	int updatePwd(@Param("username")String username, @Param("password")String password, @Param("salt")String salt);

	int getRowCount(@Param("username")String username);

	List<SysAdmin> findAllAdmin(@Param("username")String username, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);

	int deletObject(@Param("ids")Integer... ids);

	int findUserName(String username);

	int insertObject(@Param("username")String username, @Param("password")String password, @Param("salt")String salt);

	void doStatus(@Param("id")Integer id, @Param("vaild")Integer vaild);
}
