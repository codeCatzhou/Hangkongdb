package com.hk.sys.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.hk.common.annotation.RequiredLog;
import com.hk.common.exception.ServiceException;
import com.hk.common.utils.PageUtil;
import com.hk.common.vo.PageObject;
import com.hk.sys.dao.SysUserDao;
import com.hk.sys.entity.SysUser;
import com.hk.sys.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public PageObject<SysUser> findAllUsers(String username, Integer pageCurrent) {
		int rows = sysUserDao.getRowCount(username);
		if(rows==0)
			throw new ServiceException("数据异常，请重试");
		int pageSize = 8;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysUser> records = sysUserDao.findAllUser(username, startIndex, pageSize);
		//将身份信息简单加密显示
		encrypt(records);
		PageObject<SysUser> po = PageUtil.newPageObject(pageCurrent, rows, pageSize, records);
		return po;
	}

	private void encrypt(List<SysUser> records) {
		String join="**********";
		String dm=null;
		String mb=null;
		for (SysUser sysUser : records) {
			dm=sysUser.getIdentity().substring(0, 4);//截取前四位
			mb=sysUser.getIdentity().substring(14);//截取后四位
			sysUser.setIdentity(dm+join+mb);
		}
	}
	
	@RequiredLog("更新用户账号状态")
	@Override
	public int updateStatus(String username, Integer status) {
		int rows = sysUserDao.updateStatus(username,status);
		return rows;
	}
	
	@RequiredLog("添加用户")
	@Override
	public int insertUser(SysUser sysUser) {
		System.out.println(sysUser);
		if(StringUtils.isEmpty(sysUser.getUsername()))
			throw new ServiceException("用户名不能为空");
		//验证用户名是否重复
		int cy=sysUserDao.findUser(sysUser.getUsername());
		if(cy!=0)
			throw new ServiceException("用户名重复，请修改");
		//加密
		String salt = UUID.randomUUID().toString();
		sysUser.setSalt(salt);
		SimpleHash sHash = new SimpleHash("md5", sysUser.getPassword(), salt);
		sysUser.setPassword(sHash.toHex());//转16进制
		int row =sysUserDao.insertUser(sysUser);
		return row;
	}
	
	@RequiredLog("删除用户")
	@Override
	public int deleteUserByIds(Integer... ids) {
		if(ids==null || ids.length==0)
			throw new ServiceException("请先选择");
		int rows =sysUserDao.deleteUserByIds(ids);
		if(rows == 0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}

}
