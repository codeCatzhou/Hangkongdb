package com.hk.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.common.exception.ServiceException;
import com.hk.common.utils.PageUtil;
import com.hk.common.vo.PageObject;
import com.hk.sys.dao.SysSeatDao;
import com.hk.sys.entity.SysSeat;
import com.hk.sys.service.SysSeatService;

@Service
public class SysSeatServiceImpl implements SysSeatService{

	@Autowired
	private SysSeatDao sysSeatDao;
	
	@Override
	public PageObject<SysSeat> findSeat(String fightId,Integer pageCurrent) {
		int rows = sysSeatDao.getRowCount(fightId);
		if(rows==0)
			throw new ServiceException("没有找到数据，请检查输入信息是否有误");
		int pageSize = 8;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysSeat> records = sysSeatDao.findSeat(fightId, startIndex, pageSize);
		PageObject<SysSeat> po = PageUtil.newPageObject(pageCurrent, rows, pageSize, records);
		return po;
	}

	@Override
	public SysSeat findSeatByFightId(String fightId) {
		SysSeat sysSeat=new SysSeat();
		try {
			sysSeat=sysSeatDao.findSeatByFightId(fightId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统异常，请稍后");
		}
		return sysSeat;
	}

	@Override
	public int doUpdateSeat(SysSeat sysSeat) {
		int row=sysSeatDao.doUpdateSeat(sysSeat);
		if(row==0)
			throw new ServiceException("座位信息更新错误，已联系修复");
		return row;
	}


}
