package com.hk.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hk.sys.entity.SysSeat;

public interface SysSeatDao {
	List<SysSeat> findSeat(@Param("fightId")String fightId,@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("fightId")String fightId);
	
	//添加
	int insertSeat(SysSeat sysSeat);
	//删除
	int deleteByFightId(String fightId);

	SysSeat findSeatByFightId(String fightId);
	//更新
	int doUpdateSeat(SysSeat sysSeat);
}
