package com.hk.sys.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.common.annotation.RequiredLog;
import com.hk.common.exception.ServiceException;
import com.hk.common.utils.PageUtil;
import com.hk.common.vo.PageObject;
import com.hk.sys.dao.SysAdminDao;
import com.hk.sys.entity.SysAdmin;
import com.hk.sys.service.SysAdminService;

@Service
public class SysAdminServiceImpl implements SysAdminService{
	
	@Autowired
	private SysAdminDao sysAdminDao;
	
	@RequiredLog("修改密码")
	@Override
	public int updatePwd(String username, String oldpw, String newpw1, String newpw2) {
		if(username==null || username=="")
			throw new ServiceException("获取数据异常,等待系统修复");
		//验证信息
		if(oldpw==null||oldpw.length()==0)
			throw new ServiceException("请输入原密码");
		if(newpw1==null||newpw1.length()==0)
			throw new ServiceException("新密码不能为空");
		if(newpw2==null||newpw2.length()==0)
			throw new ServiceException("确认密码不能为空");
		if(!newpw1.equals(newpw2))
			throw new ServiceException("两次输入的新密码不同");
		if(oldpw.equals(newpw1))
			throw new ServiceException("新密码不能与旧密码相同");
		//验证密码
		SysAdmin admin = sysAdminDao.findUserByUserName(username);
		String salt = admin.getSalt();
		SimpleHash sHash = new SimpleHash("MD5", oldpw, salt);
		String hex = sHash.toHex();
		String adminpw = admin.getPassword();
		if(!adminpw.equals(hex))
			throw new ServiceException("原密码错误");
		//信息正确重新加密
		salt = UUID.randomUUID().toString();//产生新的盐值
		sHash = new SimpleHash("MD5", newpw1, salt);
		String password=sHash.toHex();
		int rows=0;
		try {
			rows=sysAdminDao.updatePwd(username,password,salt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("更新异常，等待修复");
		}
		return rows;
	}

	@Override
	public PageObject<SysAdmin> doFindPageObjects(String username, Integer pageCurrent) {
		int rows=sysAdminDao.getRowCount(username);//获取总记录数
		if(rows==0)
			throw new ServiceException("没用找到数据");
		int pageSize=10;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysAdmin> records=sysAdminDao.findAllAdmin(username,startIndex,pageSize);//查找
		System.out.println(records);
		PageObject<SysAdmin> po = PageUtil.newPageObject(pageCurrent, rows, pageSize, records);
		return po;
	}
	
	@RequiredLog("删除管理员")
	@Override
	public int dodeleteUI(Integer... ids) {
		if(ids==null||ids.length==0)
			throw new ServiceException("请先选中");
		int rows=sysAdminDao.deletObject(ids);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	
	@RequiredLog("添加管理员")
	@Override
	public int insertObject(String username, String pwd1, String pwd2) {
		if(username==null || username=="")
			throw new ServiceException("账号不能为空");
		if(pwd1==null || pwd1=="")
			throw new ServiceException("密码不能为空");
		if(pwd2==null || pwd2=="")
			throw new ServiceException("确认密码不能为空");
		if(!pwd1.equals(pwd2))
			throw new ServiceException("两次输入密码不同");
		int row=sysAdminDao.findUserName(username);
		if(row==1)
			throw new ServiceException("该账户已存在");
		String salt = UUID.randomUUID().toString();
		SimpleHash sHash = new SimpleHash("md5", pwd1, salt);
		String password = sHash.toHex();
		int cre=sysAdminDao.insertObject(username,password,salt);
		if(cre==0)
			throw new ServiceException("数据异常");
		return cre;
	}
	
	@RequiredLog("修改管理员状态")
	@Override
	public void doStatus(Integer id, Integer vaild) {
		if(id<=0)
			throw new ServiceException("获取信息异常");
		sysAdminDao.doStatus(id,vaild);
	}

}
