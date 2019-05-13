package com.hk.sys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hk.common.exception.ServiceException;
import com.hk.common.utils.PageUtil;
import com.hk.common.vo.PageObject;
import com.hk.sys.dao.SysOrderDao;
import com.hk.sys.entity.SysOrder;
import com.hk.sys.service.SysOrderServce;

@Service
public class SysOrderServiceImpl implements SysOrderServce{
	
	@Autowired
	private SysOrderDao sysOrderDao;

	@Override
	public PageObject<SysOrder> fingAllByUsername(String username,Integer pageCurrent) {
		int rows=sysOrderDao.getRowCount(username);
		if(rows==0)
			throw new ServiceException("没有找到数据");
		int pageSize=8;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysOrder> records=sysOrderDao.fingAllByUsername(username,startIndex,pageSize);
		encrypt(records);
		PageObject<SysOrder> po = PageUtil.newPageObject(pageCurrent, rows, pageSize, records);
		return po;
	}

	private void encrypt(List<SysOrder> records) {
		String join="**********";
		String dm=null;
		String mb=null;
		for (SysOrder sysOrder : records) {
			dm=sysOrder.getIdentity().substring(0, 4);
			mb=sysOrder.getIdentity().substring(14);
			sysOrder.setIdentity(dm+join+mb);
		}
	}

	@Override
	public void deleteOrders(Integer... ids) {
		if(ids==null || ids.length==0)
			throw new ServiceException("数据异常");
		sysOrderDao.deleteOrders(ids);
	}
}
