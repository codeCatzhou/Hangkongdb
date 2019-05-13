package com.hk.view.service;


import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysOrder;
import com.hk.sys.entity.SysUser;
import com.hk.view.vo.ViewCountSeat;
import com.hk.view.vo.ViewFightSeat;
import com.hk.view.vo.ViewOrderFight;

public interface ViewIndexService {
	//添加用户
	void insertUser(SysUser sysUser);
	//登录
	void goLogin(String username, String password);
	//获取用户数据
	SysUser readUser(String username);
	//更新用户资料
	int upByUsername(SysUser sysUser);
	//更新密码
	int upPwd(String username, String oldpwd, String newpwd1, String newpwd2);
	//获取所有航班信息
	PageObject<ViewFightSeat> goSearch(String fromAdd, String toAdd, String startTime,Integer pageCurrent);
	//获取航班详细信息
	ViewFightSeat gofightUI(String fightId);
	//生成订单
	int creatOrder(SysOrder sysOrder, Integer seatType, String password);
	//获取用户所有订单
	PageObject<ViewOrderFight> findOrder(String username, Integer pageCurrent);
	//获取退单表中的座位总数
	ViewCountSeat findCountSeat(String fightId);
	//退票
	void reticket(Integer orderId);
	//忘记密码
	void resetpw(String username, String identity);
	void inpwd(String username, String newpwd1, String newpwd2);
	//删除
	void deleteOrder(Integer orderId);
}
