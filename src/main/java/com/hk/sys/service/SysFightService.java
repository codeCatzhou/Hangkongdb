package com.hk.sys.service;

import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysFight;
import com.hk.sys.entity.SysSeat;

public interface SysFightService {

	/**查询所有航班信息*/
	PageObject<SysFight> doFindbyAdd(String fromAdd,String toAdd,Integer pageCurrent);
	//更新航班状态
	int updateStatus(String fightId, Integer status);
	//新增航班信息
	int saveObjects(SysFight sysFight, SysSeat sysSeat);
	//删除
	int deleteObjects(Integer... ids);
	//根据id查询出要修改的航班状态
	SysFight updateFind(Integer id);
	//更新航班信息
	int updateFight(SysFight sysFight);
}
