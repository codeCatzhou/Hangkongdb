package com.hk.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hk.sys.entity.SysOrder;

public interface SysOrderDao {

	int getRowCount(@Param("username")String username);

	List<SysOrder> fingAllByUsername(@Param("username")String username, @Param("startIndex")int startIndex, @Param("pageSize")int pageSize);

	int findByFightId(String fightId);

	void deleteOrders(@Param("ids")Integer... ids);

}
