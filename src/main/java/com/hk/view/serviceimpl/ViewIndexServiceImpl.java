package com.hk.view.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.common.exception.ServiceException;
import com.hk.common.utils.PageUtil;
import com.hk.common.vo.PageObject;
import com.hk.sys.entity.SysFight;
import com.hk.sys.entity.SysOrder;
import com.hk.sys.entity.SysUser;
import com.hk.view.dao.ViewFightDao;
import com.hk.view.dao.ViewOrderDao;
import com.hk.view.dao.ViewOrderOutDao;
import com.hk.view.dao.ViewSeatDao;
import com.hk.view.dao.ViewUserDao;
import com.hk.view.service.ViewIndexService;
import com.hk.view.vo.ViewCountSeat;
import com.hk.view.vo.ViewFightSeat;
import com.hk.view.vo.ViewOrderFight;
import com.hk.view.vo.ViewOrderOut;

@Service
public class ViewIndexServiceImpl implements ViewIndexService{

	@Autowired
	private ViewUserDao viewUserDao;
	
	@Autowired
	private ViewFightDao viewFightDao;
	
	@Autowired
	private ViewSeatDao viewSeatDao;
	
	@Autowired
	private ViewOrderDao viewOrderDao;
	
	@Autowired
	private ViewOrderOutDao viewOrderOutDao;
	
	@Override
	public void insertUser(SysUser sysUser) {
		int row=viewUserDao.checkUser(sysUser.getUsername());
		if(row==1)
			throw new ServiceException("该账号已存在，请修改");
		//加密
		String salt = UUID.randomUUID().toString();
		SimpleHash sHash = new SimpleHash("MD5",sysUser.getPassword(),salt);
		sysUser.setPassword(sHash.toHex());
		sysUser.setSalt(salt);
		try {
			viewUserDao.insertUser(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("注册异常");
		}
	}

	@Override
	public void goLogin(String username, String password) {
		int row=viewUserDao.checkUser(username);
		if(row==0)
			throw new ServiceException("账号错误或者不存在");
		SysUser sysUser=viewUserDao.findByUsername(username);
		String salt = sysUser.getSalt();
		SimpleHash sHash = new SimpleHash("MD5",password,salt);
		String hex = sHash.toHex();
		if(!hex.equals(sysUser.getPassword()))
			throw new ServiceException("密码错误");
	}

	@Override
	public SysUser readUser(String username) {
		int row=viewUserDao.checkUser(username);
		if(row==0)
			throw new ServiceException("系统异常，等待修复");
		SysUser sysUser=viewUserDao.findByUsername(username);
		return sysUser;
	}

	@Override
	public int upByUsername(SysUser sysUser) {
		if(sysUser==null)
			throw new ServiceException("获取数据异常，等待修复");
		int row=viewUserDao.upByUserbname(sysUser);
		if(row==0)
			throw new ServiceException("数据录入异常，等待修复");
		return row;
	}

	@Override
	public int upPwd(String username, String oldpwd, String newpwd1, String newpwd2) {
		if(oldpwd==null||oldpwd.length()==0)
			throw new ServiceException("请输入原密码");
		if(newpwd1==null||newpwd1.length()==0)
			throw new ServiceException("新密码不能为空");
		if(newpwd2==null||newpwd2.length()==0)
			throw new ServiceException("确认密码不能为空");
		if(!newpwd1.equals(newpwd2))
			throw new ServiceException("两次输入的新密码不同");
		if(oldpwd.equals(newpwd1))
			throw new ServiceException("新密码不能与旧密码相同");
		SysUser sysUser=viewUserDao.findByUsername(username);
		String salt = sysUser.getSalt();
		SimpleHash sHash = new SimpleHash("MD5", oldpwd, salt);
		String hex = sHash.toHex();
		String adminpw = sysUser.getPassword();
		if(!adminpw.equals(hex))
			throw new ServiceException("原密码错误");
		//录入
		salt = UUID.randomUUID().toString();//产生新的盐值
		sHash = new SimpleHash("MD5", newpwd1, salt);
		String password=sHash.toHex();
		int rows=viewUserDao.updatePwd(username,password,salt);
		if(rows==0)
			throw new ServiceException("信息录入异常，等待修复");
		return rows;
	}

	@Override
	public PageObject<ViewFightSeat> goSearch(String fromAdd, String toAdd, String startTime,Integer pageCurrent) {
		startTime+="%";//用于查询数据库时间
		int rows=viewFightDao.findFight(fromAdd, toAdd, startTime);
		if(rows==0)
			throw new ServiceException("没有找到记录");
		int pageSize=8;
		int startIndex=(pageCurrent-1)*pageSize;
		List<ViewFightSeat> records = viewFightDao.findFightByAdd(fromAdd,toAdd,startTime,startIndex,pageSize);
		PageObject<ViewFightSeat> po = PageUtil.newPageObject(pageCurrent, rows, pageSize, records);
		return po;
	}

	@Override
	public ViewFightSeat gofightUI(String fightId) {
		if(fightId=="" || fightId==null)
			throw new ServiceException("信息异常，等待修复");
		ViewFightSeat obj=viewFightDao.findFightSeatByFightId(fightId);
		if(obj==null)
			throw new ServiceException("信息异常，等待修复");
		return obj;
	}
	
	//生成订单
	@Override
	public int creatOrder(SysOrder sysOrder, Integer seatType, String password) {
		//验证数据
		if(sysOrder==null || password==null || password=="" || seatType==0 || seatType==null)
			throw new ServiceException("信息异常，等待修复");
		//验证密码
		SysUser sysUser = viewUserDao.findByUsername(sysOrder.getUsername());
		SimpleHash sHash = new SimpleHash("MD5", password, sysUser.getSalt());
		String hex = sHash.toHex();
		String userpw = sysUser.getPassword();
		if(!userpw.equals(hex))
			throw new ServiceException("密码错误");
		//验证座位数量
		int rows=1,seats=0,medo=1;//seats设置座位数，medo用户判断退票是否重新生成
		if(seatType==1) {
			//查找是否有退票
			ViewOrderOut out=viewOrderOutDao.findSeatId(sysOrder.getFightId(),"F");
			do{
				if(out!=null) {
					sysOrder.setSeatId(out.getSeatId());//将找到的座位设置到订单中
					medo=viewOrderOutDao.deleteById(out.getId());//并删除退单记录中的
					out=null;
				}else {
					seats=viewSeatDao.findFnumberByFightId(sysOrder.getFightId());
					if(seats<=0)
						throw new ServiceException("客官不好意思，一等座没有了");
					sysOrder.setSeatId("F"+seats);//生成座位号
					rows=viewSeatDao.updateFsByFightId(sysOrder.getFightId());
					medo=1;//防止死循环
				}
			}while(medo==0);//退票号删除失败则生成新的座位号
		}
		if(seatType==2) {
			//查找是否有退票
			ViewOrderOut out=viewOrderOutDao.findSeatId(sysOrder.getFightId(),"S");
			do{
				if(out!=null) {
					sysOrder.setSeatId(out.getSeatId());//将找到的座位设置到订单中
					medo=viewOrderOutDao.deleteById(out.getId());//并删除退单记录中的
					out=null;
				}else {
					seats=viewSeatDao.findSnumberByFightId(sysOrder.getFightId());
					if(seats<=0)
						throw new ServiceException("客官不好意思，一等座没有了");
					sysOrder.setSeatId("S"+seats);//生成座位号
					rows=viewSeatDao.updateSsByFightId(sysOrder.getFightId());
					medo=1;//防止死循环
				}
			}while(medo==0);//退票号删除失败则生成新的座位号
		}
		if(seatType==3) {
			//查找是否有退票
			ViewOrderOut out=viewOrderOutDao.findSeatId(sysOrder.getFightId(),"O");
			System.out.println(out);
			do{
				if(out!=null) {
					sysOrder.setSeatId(out.getSeatId());//将找到的座位设置到订单中
					medo=viewOrderOutDao.deleteById(out.getId());//并删除退单记录中的
					out=null;
				}else {
					seats=viewSeatDao.findOnumberByFightId(sysOrder.getFightId());
					if(seats<=0)
						throw new ServiceException("客官不好意思，一等座没有了");
					sysOrder.setSeatId("O"+seats);//生成座位号
					rows=viewSeatDao.updateOsByFightId(sysOrder.getFightId());
					medo=1;//防止死循环
				}
			}while(medo==0);//退票号删除失败则生成新的座位号
		}
		if(rows==0)
			throw new ServiceException("信息异常，等待修复");
		//添加订单
		rows=viewOrderDao.insertOrder(sysOrder);
		if(rows==0)
			throw new ServiceException("信息异常，等待修复");
		return rows;
	}

	@Override
	public PageObject<ViewOrderFight> findOrder(String username, Integer pageCurrent) {
		if(username==null || username=="")
			throw new ServiceException("信息异常，等待修复");
		//找到用户所有订单
		int rows=viewOrderDao.findAll(username);
		int pageSize=8;
		int startIndex=(pageCurrent-1)*pageSize;
		//获取所有订单信息
		List<ViewOrderFight> records=viewOrderDao.findOrder(username,startIndex,pageSize);
		PageObject<ViewOrderFight> po = PageUtil.newPageObject(pageCurrent, rows, pageSize, records);
		return po;
	}

	@Override
	public ViewCountSeat findCountSeat(String fightId) {
		ViewCountSeat count = new ViewCountSeat();
		count.setFightId(fightId);
		count.setCountf(viewOrderOutDao.findCount(fightId,"F"));
		count.setCounts(viewOrderOutDao.findCount(fightId,"S"));
		count.setCounto(viewOrderOutDao.findCount(fightId,"O"));
		return count;
	}

	@Override
	public void reticket(Integer orderId) {
		if(orderId==0 || orderId==null)
			throw new ServiceException("数据传递异常");
		SysOrder orders=viewOrderDao.findFightById(orderId);
		if(orders==null)
			throw new ServiceException("数据异常，等待修复");
		if(orders.getStatus()==0)
			throw new ServiceException("该订单已失效，无法操作");
		SysFight fight=viewFightDao.checkFight(orders.getFightId());
		if(fight.getStatus()==1)
			throw new ServiceException("该航班已出发无法退票，请联系客服");
		//获取当前时间并判断是否在退票的时间范围内(起飞前40分钟)
		long date = new Date().getTime();
		if((fight.getStartTime().getTime()-2400000)<date)
			throw new ServiceException("已超过退票时间，无法退票，请联系客服");
		int rows;
		rows=viewOrderDao.updateStatus(orders.getFightId(),orders.getSeatId());
		rows=viewOrderOutDao.insertOrderOut(orders.getFightId(),orders.getSeatId());
		if(rows==0)
			throw new ServiceException("订单状态修改错误，请联系管理员");
	}

	@Override
	public void resetpw(String username, String identity) {
		if(username==null || username =="")
			throw new ServiceException("账号不能为空");
		if(identity==null || identity =="")
			throw new ServiceException("身份证号不能为空");
		SysUser sysUser=viewUserDao.findUser(username);
		if(sysUser==null)
			throw new ServiceException("账户不存在或被禁用");
		if(!sysUser.getIdentity().equals(identity))
			throw new ServiceException("身份证号码错误");
	}

	@Override
	public void inpwd(String username, String newpwd1, String newpwd2) {
		if(username==null || username =="")
			throw new ServiceException("数据异常");
		if(newpwd1==null||newpwd1.length()==0)
			throw new ServiceException("新密码不能为空");
		if(newpwd2==null||newpwd2.length()==0)
			throw new ServiceException("确认密码不能为空");
		if(!newpwd1.equals(newpwd2))
			throw new ServiceException("两次输入的新密码不同");
		String salt = UUID.randomUUID().toString();//产生新的盐值
		SimpleHash sHash = new SimpleHash("MD5", newpwd1, salt);
		String password=sHash.toHex();
		int rows=viewUserDao.updatePwd(username,password,salt);
		if(rows==0)
			throw new ServiceException("数据异常");
	}

	@Override
	public void deleteOrder(Integer orderId) {
		if(orderId==0 || orderId==null)
			throw new ServiceException("数据传递异常");
		viewOrderDao.deleteOrder(orderId);
	}

}
