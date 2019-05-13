package com.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

public class TestDurid extends TestBase{
	
	@Test
	public void testDurid() throws SQLException {
		DataSource ds = ctx.getBean("dataSource",DataSource.class);
		System.out.println(ds.getConnection());
	}
}
