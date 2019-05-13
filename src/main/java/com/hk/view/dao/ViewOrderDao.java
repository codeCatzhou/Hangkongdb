package com.hk.view.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hk.sys.entity.SysOrder;
import com.hk.view.vo.ViewOrderFight;

public interface ViewOrderDao {

	int insertOrder(SysOrder sysOrder);

	int findAll(String username);

	List<ViewOrderFight> findOrder(@Param("username")String username,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);

	int updateStatus(@Param("fightId")String fightId, @Param("seatId")String seatId);
	
	//根据订单id获取订单信息
	SysOrder findFightById(Integer orderId);

	void deleteOrder(Integer orderId);

}
