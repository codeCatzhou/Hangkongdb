package com.hk.sys.service;

import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysOrder;

public interface SysOrderServce {

	PageObject<SysOrder> fingAllByUsername(String username,Integer pageCurrent);

	void deleteOrders(Integer... ids);

}
