package com.hk.sys.service;


import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysSeat;

public interface SysSeatService {
	
	PageObject<SysSeat> findSeat(String fightId,Integer pageCurrent);

	SysSeat findSeatByFightId(String fightId);

	int doUpdateSeat(SysSeat sysSeat);
}
