package com.test;


import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.hk.sys.dao.SysAdminDao;
import com.hk.sys.dao.SysFightDao;
import com.hk.sys.dao.SysLogDao;
import com.hk.sys.dao.SysOrderDao;
import com.hk.sys.dao.SysSeatDao;
import com.hk.sys.dao.SysUserDao;
import com.hk.sys.entity.SysAdmin;
import com.hk.sys.entity.SysFight;
import com.hk.sys.entity.SysLog;
import com.hk.sys.entity.SysSeat;
import com.hk.sys.entity.SysUser;
import com.hk.view.dao.ViewFightDao;
import com.hk.view.dao.ViewOrderDao;
import com.hk.view.dao.ViewOrderOutDao;
import com.hk.view.dao.ViewSeatDao;
import com.hk.view.dao.ViewUserDao;
import com.hk.view.vo.ViewCountSeat;
import com.hk.view.vo.ViewFightSeat;
import com.hk.view.vo.ViewOrderFight;
import com.hk.view.vo.ViewOrderOut;

public class Testproblom extends TestBase{
	
	@Test
	public void test1() {
		SysFightDao bean = ctx.getBean("sysFightDao",SysFightDao.class);
		List<SysFight> res = bean.dofindbyadd("", "", 0, 3);
		System.out.println(res);
	}
	
	@Test
	public void test2() {
		SysFightDao bean = ctx.getBean("sysFightDao",SysFightDao.class);
		int res = bean.getRowCount("", "");
		System.out.println(res);
	}
	
	@Test
	public void test3() {
		SysSeatDao bean = ctx.getBean("sysSeatDao",SysSeatDao.class);
		List<SysSeat> res = bean.findSeat("", 0, 5);
		System.out.println(res);
	}
	
	@Test
	public void test4() {
		SysSeatDao bean = ctx.getBean("sysSeatDao",SysSeatDao.class);
		int res = bean.getRowCount("");
		System.out.println(res);
	}
	
	
	@Test
	public void test6() {
		SysFightDao bean = ctx.getBean("sysFightDao",SysFightDao.class);
		int res = bean.updateStatus("BS33109",1);
		System.out.println(res);
	}
	
	
	@Test
	public void test5() {
		@SuppressWarnings("unused")
		SysFightDao bean = ctx.getBean("sysFightDao",SysFightDao.class);
		SysFight sysFight=new SysFight();
		sysFight.setFightId("CGB123");
		sysFight.setFromAdd("hh");
		sysFight.setToAdd("mm");
/*		sysFight.setStartTime(null);
		bean.insertfight(sysFight);*/
	}
	
	@Test
	public void test7() {
		SysUserDao bean = ctx.getBean("sysUserDao",SysUserDao.class);
		List<SysUser> res = bean.findAllUser("", 0, 3);
		System.out.println(res);
	}
	
	@Test
	public void test8() {
		SysUserDao bean = ctx.getBean("sysUserDao",SysUserDao.class);
		int rows = bean.getRowCount("");
		System.out.println(rows);
	}
	
	@Test
	public void test9() {
		Date date = new Date();
		System.out.println(date);
	}
	
	@Test
	public void test10() {
		SysFightDao bean = ctx.getBean("sysFightDao",SysFightDao.class);
		SysFight oas = bean.updateFind(12);
		System.out.println(oas);
	}
	
	@Test
	public void test11() {
		SysAdminDao bean = ctx.getBean("sysAdminDao",SysAdminDao.class);
		SysAdmin oas = bean.findUserByUserName("admin");
		System.out.println(oas);
	}
	
	@Test
	public void test12() {
		ViewUserDao bean = ctx.getBean("viewUserDao",ViewUserDao.class);
		int row = bean.checkUser("zhangsan");
		System.out.println(row);
	}
	
	@Test
	public void test12_1() {
		ViewUserDao bean = ctx.getBean("viewUserDao",ViewUserDao.class);
		SysUser row = bean.findUser("zhangsan");
		System.out.println(row);
	}
	
	@Test
	public void test13() {
		ViewUserDao bean = ctx.getBean("viewUserDao",ViewUserDao.class);
		SysUser sysUser=new SysUser();
		sysUser.setUsername("tomcat");
		sysUser.setPassword("4ebd394fbd25e495e0753a7dc9889a8e");
		sysUser.setSalt("7adb778c-e7d3-4dd3-a3c5-5f80a158006d");
		sysUser.setName("蔡徐坤");
		sysUser.setIdentity("123456789012345678");
		sysUser.setTel("13666666666");
		sysUser.setEmail("sss@123.com");
		sysUser.setStatus(1);
		bean.insertUser(sysUser);
	}
	
	@Test
	public void test14() {
		ViewUserDao bean = ctx.getBean("viewUserDao",ViewUserDao.class);
		SysUser sysUser = bean.findByUsername("tomcs");
		System.out.println(sysUser.toString());
	}
	
	@Test
	public void test15() {
		ViewFightDao bean = ctx.getBean("viewFightDao",ViewFightDao.class);
		int row = bean.findFight("上海", "北京", "2019-03-30%");
		System.out.println(row);
	}
	
	@Test
	public void test16() {
		ViewFightDao bean = ctx.getBean("viewFightDao",ViewFightDao.class);
		List<ViewFightSeat> rows = bean.findFightByAdd("", "", "2019-04%", 0, 2);
		for (ViewFightSeat viewFightSeat : rows) {
			System.out.println(viewFightSeat);
		}
	}
	
	@Test
	public void test17() {
		ViewFightDao bean = ctx.getBean("viewFightDao",ViewFightDao.class);
		ViewFightSeat obj = bean.findFightSeatByFightId("BX41709");
		System.out.println("? "+obj);
	}
	
	@Test
	public void test18() {
		ViewSeatDao bean = ctx.getBean("viewSeatDao",ViewSeatDao.class);
		int row = bean.findFnumberByFightId("BS33109");
		System.out.println(row);
	}
	
	@Test
	public void test19() {
		ViewOrderDao bean = ctx.getBean("viewOrderDao",ViewOrderDao.class);
		int row = bean.findAll("tomcs");
		System.out.println(row);
	}
	
	@Test
	public void test20() {
		ViewOrderDao bean = ctx.getBean("viewOrderDao",ViewOrderDao.class);
		List<ViewOrderFight> orders = bean.findOrder("tomcs", 0, 4);
		for (ViewOrderFight cs : orders) {
			System.out.println(cs+"\n");
		}
	}
	
	@Test
	public void test21() {
		ViewOrderOutDao bean = ctx.getBean("viewOrderOutDao",ViewOrderOutDao.class);
		ViewOrderOut order = bean.findSeatId("SSS", "S");
		int yu = bean.deleteById(order.getId());
		System.out.println(order+"\n"+yu);
	}
	
	@Test
	public void test22() {
		ViewOrderOutDao bean = ctx.getBean("viewOrderOutDao",ViewOrderOutDao.class);
		ViewCountSeat count = new ViewCountSeat();
		count.setFightId("SSS");
		count.setCountf(bean.findCount("SSS","F"));
		count.setCounts(bean.findCount("SSS","S"));
		count.setCounto(bean.findCount("SSS","O"));
		System.out.println(count);
	}
	
	@Test
	public void test23() {
		ViewFightDao bean = ctx.getBean("viewFightDao",ViewFightDao.class);
		SysFight fight = bean.checkFight("AAA");
		System.out.println(fight.getStartTime().getTime());
		System.out.println(fight.getStartTime().getTime()+3000000);
	}
	
	@Test
	public void test24() {
		ViewOrderDao bean = ctx.getBean("viewOrderDao",ViewOrderDao.class);
		int n = bean.updateStatus("AAA", "S30");
	}
	
	@Test
	public void test25() {
		ViewOrderOutDao bean = ctx.getBean("viewOrderOutDao",ViewOrderOutDao.class);
		bean.insertOrderOut("AAA", "S12");
	}
	
	@Test
	public void test26() {
		SysLogDao bean = ctx.getBean("sysLogDao",SysLogDao.class);
		SysLog log = new SysLog();
		log.setCreatedTime(new Date());
		log.setOperation("测试");
		log.setUsername("程序猿");
		bean.insertObject(log);
	}
	
	@Test
	public void test27() {
		SysAdminDao bean = ctx.getBean("sysAdminDao",SysAdminDao.class);
		int row = bean.getRowCount("");
		System.out.println(row);
	}
	
	@Test
	public void test28() {
		SysAdminDao bean = ctx.getBean("sysAdminDao",SysAdminDao.class);
		List<SysAdmin> row = bean.findAllAdmin("", 0, 8);
		System.out.println(row);
	}
	
	@Test
	public void test29() {
		SysAdminDao bean = ctx.getBean("sysAdminDao",SysAdminDao.class);
		int row = bean.findUserName("ad");
		System.out.println(row);
	}
	
	@Test
	public void test31() {
		ViewOrderDao bean = ctx.getBean("viewOrderDao",ViewOrderDao.class);
		bean.deleteOrder(1);
	}
}
