package com.hk.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.common.annotation.RequiredLog;
import com.hk.common.exception.ServiceException;
import com.hk.common.utils.PageUtil;
import com.hk.common.vo.PageObject;
import com.hk.sys.dao.SysFightDao;
import com.hk.sys.dao.SysOrderDao;
import com.hk.sys.dao.SysSeatDao;
import com.hk.sys.entity.SysFight;
import com.hk.sys.entity.SysSeat;
import com.hk.sys.service.SysFightService;

@Service
public class SysFightServiceImpl implements SysFightService{
	
	@Autowired
	SysFightDao sysFightDao;
	
	@Autowired
	SysSeatDao sysSeatDao;
	
	@Autowired
	SysOrderDao sysOrderDao;
	
	/**查询所有航班信息*/
	@Override
	public PageObject<SysFight> doFindbyAdd(String fromAdd, String toAdd,Integer pageCurrent) {
		int rows = sysFightDao.getRowCount(fromAdd,toAdd);
		if(rows==0)
			throw new ServiceException("没有找到数据，请检查输入信息是否有误");
		int pageSize=8;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysFight> records = sysFightDao.dofindbyadd(fromAdd, toAdd,startIndex,pageSize);
		PageObject<SysFight> po = PageUtil.newPageObject(pageCurrent, rows, pageSize, records);
		return po;
	}
	
	@RequiredLog("更新航班状态")
	@Override
	public int updateStatus(String fightId, Integer status) {
		if(fightId==null || fightId == "")
			throw new ServiceException("系统异常，请稍后");
		int row=sysFightDao.updateStatus(fightId,status);
		return row;
	}
	
	@RequiredLog("新增航班")
	@Override
	public int saveObjects(SysFight sysFight, SysSeat sysSeat) {
		/*验证航班编号*/
		if(sysFight.getFightId()=="")
			throw new ServiceException("航班编号不能为空");
		int row = sysFightDao.findByFightId(sysFight.getFightId());
		if(row==1)
			throw new ServiceException("该航班编号已存在，请重新填写");
		//验证地点
		if(sysFight.getFromAdd()=="" && sysFight.getToAdd()=="")
			throw new ServiceException("地点不能为空");
		//验证时间
		Date date = new Date();
		System.out.println(date);
		if(sysFight.getStartTime().getTime()<date.getTime())
			throw new ServiceException("新增航班出发时间不能小于当前时间");
		if(sysFight.getStartTime().getTime()>=sysFight.getArrTime().getTime())
			throw new ServiceException("航班出发时间不能大于到达时间");
		//验证座位
		if(sysSeat.getFprice()==0&&sysSeat.getFnumbers()>0)
			throw new ServiceException("一等座价格不能为0");
		if(sysSeat.getSprice()==0&&sysSeat.getSnumbers()>0)
			throw new ServiceException("二等座价格不能为0");
		if(sysSeat.getOprice()==0&&sysSeat.getOnumbers()>0)
			throw new ServiceException("三等座价格不能为0");
		//验证折扣
		if(sysSeat.getRebate()==1)
			throw new ServiceException("折扣信息错误");
		int rows;
		try {
			rows=sysFightDao.insertfight(sysFight);
			sysSeatDao.insertSeat(sysSeat);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统异常，修复中");
		}
		return rows;
	}
	
	//删除
	@RequiredLog("删除航班")
	@Override
	public int deleteObjects(Integer... ids) {
		List<SysFight> lists=sysFightDao.findByIds(ids);
		int row;
		for (SysFight sysFight : lists) {
			System.out.println(sysFight.getFightId());
			row =sysOrderDao.findByFightId(sysFight.getFightId());
			System.out.println(row);
			if(row!=0 )
				throw new ServiceException("选中航班中已有订单");
		}
		try {
			sysFightDao.deleteByIds(ids);
			for (SysFight sysFight : lists) {
				sysSeatDao.deleteByFightId(sysFight.getFightId());
			}
		} catch (Exception e) {
			throw new ServiceException("系统异常，请稍后");
		}
		return 1;
	}
	
	
	@Override
	public SysFight updateFind(Integer id) {
		SysFight sysFight = new SysFight();
		try {
			sysFight=sysFightDao.updateFind(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统异常，请稍后");
		}
		return sysFight;
	}
	
	@RequiredLog("更新航班信息")
	@Override
	public int updateFight(SysFight sysFight) {
		System.out.println(sysFight);
		//验证信息
		SysFight checkf=sysFightDao.updateFind(sysFight.getId());
		System.out.println(checkf.toString());
		if(checkf.getStatus()==1)
			throw new ServiceException("该航班已出发，不能修改");
		int obj = sysOrderDao.findByFightId(sysFight.getFightId());
		if(obj!=0)
			throw new ServiceException("该航班已生成订单，修改失效");
		int row=sysFightDao.updateFight(sysFight);
		return row;
	}

}
