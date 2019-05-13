package com.hk.view.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hk.sys.entity.SysFight;
import com.hk.view.vo.ViewFightSeat;

public interface ViewFightDao {

	List<ViewFightSeat> findFightByAdd(@Param("fromAdd")String fromAdd, @Param("toAdd")String toAdd, @Param("startTime")String startTime, 
			@Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);

	int findFight(@Param("fromAdd")String fromAdd, @Param("toAdd")String toAdd, @Param("startTime")String startTime);
	
	ViewFightSeat findFightSeatByFightId(String fightId);
	
	SysFight checkFight(String fightId);

}
