package com.hk.view.dao;

import org.apache.ibatis.annotations.Param;

import com.hk.view.vo.ViewOrderOut;

public interface ViewOrderOutDao {
	
	ViewOrderOut findSeatId(@Param("fightId")String fightId, @Param("seatId")String seatId);

	int deleteById(Integer id);
	
	//找到对应类型的座位数量
	int findCount(@Param("fightId")String fightId, @Param("seatId")String seatId);

	int insertOrderOut(@Param("fightId")String fightId, @Param("seatId")String seatId);
}
