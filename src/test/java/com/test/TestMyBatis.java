package com.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

public class TestMyBatis extends TestBase{
	
	@Test
	public void testMyBatis() {
		SqlSessionFactory ssf = ctx.getBean("sqlSessionFactory",SqlSessionFactory.class);
		System.out.println(ssf);
	}
}
